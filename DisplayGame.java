import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.util.Random;

import javax.swing.*;

public class DisplayGame extends JPanel implements ActionListener{

	Ellipse2D.Double ball=new Ellipse2D.Double(0, 0, 25, 25);
	Ellipse2D.Double foods[]= new Ellipse2D.Double[50];
	Color foodColors[]=new Color[50];
	
	
	
	boolean isGameInitialized=false;
	boolean isFoodColorInitialized=false;
	
	double v=6;
	public DisplayGame() {


		Timer timer=new Timer(30,this);

		timer.start();
	
		}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2=(Graphics2D)g;
		
		if(isGameInitialized==false)
		initializeGame();
		if(isFoodColorInitialized==false)
		randomFoodColorInitializer();
		drawFood(g2);
		
		didBallIntersect();
		
		setBackground(Color.GRAY);
		g2.setColor(Color.ORANGE);
		g2.fill(ball);
		g2.setColor(Color.RED);	

		
//		if(foods==null) return;
//		else if(ball.getBounds().intersects(ball2.getBounds())){
//			ball.width=30;
//			ball.height=30;
//			//v=5;
//			ball2=null;
//			repaint();
//		}
	}
	//CHECH IF BALL INTERSECTS WITH FOOD IF IT DOES MAKE BALL BIGGER
	public void didBallIntersect(){
		for (int i = 0; i < foods.length; i++) {
			if(foods[i]!=null && ball.getBounds().intersects(foods[i].getBounds())){
				foods[i]=null;
				ball.width+=0.8;
				ball.height+=0.8;
			}
		}
	}
	//FOOD COLOR INITIALIZER
	public void randomFoodColorInitializer(){
		Random a=new Random();
		
		for (int i = 0; i < foodColors.length; i++) {
			foodColors[i]=new Color(a.nextInt(255),a.nextInt(255),a.nextInt(255));
		}
		isFoodColorInitialized=true;
	}
	//DRAWING FOOD TO THE SCREEN
	public void drawFood(Graphics2D g2){

		for (int i = 0; i < foods.length; i++) {
			if(foods[i]!=null){
			g2.setColor(foodColors[i]);
			g2.fill(foods[i]);
			}
		}
	}
	//FOOD INITIALIZER
	public void initializeGame(){
		Random a=new Random();

		for (int i = 0; i < foods.length; i++) {
			foods[i]=new Ellipse2D.Double(a.nextInt(640), a.nextInt(480), 8, 8);
			
			}
		isGameInitialized=true;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Point p=getMousePosition();

		if(p==null)return;
		double dx = p.x - ball.x - ball.width/2;
		double dy = p.y - ball.y - ball.height/2;
		if(dx*dx+dy*dy >12){
		double a=Math.atan2(dy, dx);
		ball.x += v * Math.cos(a);
		ball.y += v * Math.sin(a);}
//		OLD MOVE METHOD		
//		double a=(p.x-ball.x-10);
//		double b=(p.y-ball.y-10);
//		ball.x+=a*0.05;
//		ball.y+=b*0.05;
		repaint();	
	}
}
