import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.text.DecimalFormat;

import javax.swing.*;


public class DisplayGame extends JPanel implements ActionListener{
	public static int WIDTH=840;
	public static int HEIGHT=680;
	private int numoffoods=100;
	private Ellipse2D.Double ball;
	private Foods food;
	private Poisons poison;
	private Menu menu;
	private DisplayGame a;
	private double v=4;

	
	public static enum STATE{
		MENU,
		GAME
	};
	public static STATE state=STATE.MENU;

	public DisplayGame() {
		
		this.addMouseListener(new Menu());
		Timer timer=new Timer(20,this);
		menu= new Menu();
		poison= new Poisons(numoffoods/2);
		food= new Foods(numoffoods);
		ball=new Ellipse2D.Double(WIDTH/2, HEIGHT/2, 25, 25);
		timer.start();
		
		}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2=(Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		setBackground(Color.GRAY);
		if(state==STATE.MENU){
		menu.render(g2);
			//g2.drawRect(50, 100, 50, 50);
			//state=STATE.GAME;


		}
		else if(state==STATE.GAME){
		poison.drawPoisons(g2);
		food.drawFood(g2);
		didBallIntersect();


		g2.setColor(Color.ORANGE);
		g2.fill(ball);
		printInfoBall(g2);
		}
	}

	public void didBallIntersect(){
		for (int i = 0; i < food.getFoods().length; i++) {
			if(food.getFoods()[i]!=null && ball.getBounds().intersects(food.getFoods()[i].getBounds())){
				food.getFoods()[i]=null;
				ball.width+=0.9;
				ball.height+=0.9;
				v-=0.05;
			}
		}
		for (int i = 0; i < poison.getPoisons().length; i++) {
			if(poison.getPoisons()[i]!=null && ball.getBounds().intersects(poison.getPoisons()[i].getBounds())){
				poison.getPoisons()[i]=null;
				ball.width-=0.9;
				ball.height-=0.9;
				v+=0.05;
			}
		}
		
	}
	public void printInfoBall(Graphics2D g2){
		Font font= new Font("arial",Font.BOLD,15);
		g2.setFont(font);
		g2.drawString("SPEED: "+new DecimalFormat("##.##").format(v), 50, 50);
		g2.drawString("SIZE OF BALL: "+Math.floor(ball.height), 50, 63);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(state==STATE.GAME){
			Point p=getMousePosition();
			if(p==null)return;
			double dx = p.x - ball.x - ball.width/2;
			double dy = p.y - ball.y - ball.height/2;

			if(dx*dx+dy*dy >12){
				double a=Math.atan2(dy, dx);

				for (int i = 0; i < food.getFoods().length; i++) {
					if(food.getFoods()[i]!=null){
						food.getFoods()[i].x -= v * Math.cos(a);
						food.getFoods()[i].y -= v * Math.sin(a);}

				}
				for (int i = 0; i < poison.getPoisons().length; i++) {
					if(poison.getPoisons()[i]!=null){
						poison.getPoisons()[i].x -= v * Math.cos(a);
						poison.getPoisons()[i].y -= v * Math.sin(a);}
				}
			}
			repaint();	
		}
	}
}