import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.text.DecimalFormat;

import javax.swing.*;


public class DisplayGame extends JPanel implements ActionListener,KeyListener{
	private Rectangle outerArea;
	public static int WIDTH=840;
	public static int HEIGHT=680;
	private int numoffoods=100;
	private Players player1;
	private JViewport vPort;
	private Players player2;
	private Foods food;
	private Poisons poison;
	private Menu menu;
	public static enum STATE{
		MENU,
		GAME
	};
	public static STATE state=STATE.MENU;

	public DisplayGame() {
		this.addKeyListener(this);
		this.addMouseListener(new Menu());
		Timer timer=new Timer(40,this);
		menu= new Menu();
		player1= new Players();
		player2= new Players();
		poison= new Poisons(numoffoods/2);
		food= new Foods(numoffoods);
		Dimension newSize = new Dimension(4000,3000);
		outerArea= new Rectangle(0, 0, 4000, 3000);
		setPreferredSize(newSize);
		timer.start();
	}	
	public void setvPort(JViewport vPort) {
		this.vPort = vPort;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2=(Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		setBackground(Color.GRAY);
		if(state==STATE.MENU){
			menu.render(g2);
		}
		else if(state==STATE.GAME){
			poison.drawPoisons(g2);
			food.drawFood(g2);
			player1.drawPlayers(g2);
			player2.drawPlayers(g2);
			didBallIntersect();
			printInfoBall(g2);
			g2.draw(outerArea);
		}
	}

	public void didBallIntersect(){
		for (int i = 0; i < food.getFoods().length; i++) {
			if(food.getFoods()[i]!=null && player1.getPlayer().getBounds().intersects(food.getFoods()[i].getBounds())){
				food.getFoods()[i] = null;
				player1.increaseSize();
//				player1.getPlayer().width += 0.9;
//				player1.getPlayer().height += 0.9;
//				double velocity= player1.getVelocity();
//				velocity -= 0.03;
//				player1.setVelocity(velocity);
			}
		}
		for (int i = 0; i < poison.getPoisons().length; i++) {
			if(poison.getPoisons()[i]!=null && player1.getPlayer().getBounds().intersects(poison.getPoisons()[i].getBounds())){
				poison.getPoisons()[i]=null;
				player1.decreaseSize();
//				player1.getPlayer().width-=0.9;
//				player1.getPlayer().height-=0.9;
//				double velocity= player1.getVelocity();
//				velocity -= 0.03;
//				player1.setVelocity(velocity);
			}
		}

	}
	public void printInfoBall(Graphics2D g2){
		g2.setColor(Color.ORANGE);
		Font font= new Font("arial",Font.BOLD,15);
		g2.setFont(font);
		g2.drawString("SPEED: "+new DecimalFormat("##.##").format(player1.getVelocity()), 50, 50);
		g2.drawString("SIZE OF BALL: "+Math.floor(player1.getPlayer().height), 50, 63);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(state==STATE.GAME){
			Point mousePosition=getMousePosition();
			if(mousePosition==null)return;
			double dx = mousePosition.x - player1.getPlayer().x - player1.getPlayer().width/2;
			double dy = mousePosition.y - player1.getPlayer().y - player1.getPlayer().height/2;

			if(dx*dx+dy*dy >12){
				double angle=Math.atan2(dy, dx);
				if(mousePosition.getX()<player1.getPlayer().getBounds().getMinX()||mousePosition.getX()>player1.getPlayer().getBounds().getMaxX()||mousePosition.getY()<
						player1.getPlayer().getBounds().getMinY()||mousePosition.getY()>player1.getPlayer().getBounds().getMaxY()){
//					double a=(mousePosition.x-player1.getPlayer().x-10);
//					double b=(mousePosition.y-player1.getPlayer().y-10);
//					player1.getPlayer().x+=a*0.05;
//					player1.getPlayer().y+=b*0.05;
					player1.getPlayer().x+=(int)(player1.getVelocity()*Math.cos(angle));
					player1.getPlayer().y+=(int)(player1.getVelocity()*Math.sin(angle));
					Point view = new Point((int)player1.getPlayer().x-WIDTH/2,(int)player1.getPlayer().y-HEIGHT/2);
				
					vPort.setViewPosition(view);
					System.out.println(player1.getPlayer().x+""+player1.getPlayer().y);
//					double a=(p.x-ball.x-10);
//					double b=(p.y-ball.y-10);
//					ball.x+=a*0.05;
//					ball.y+=b*0.05;
				}


			}		
			repaint();	
		}
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}