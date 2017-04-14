import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;

import javax.swing.*;

public class DisplayGame extends JPanel implements ActionListener{

	Ellipse2D.Double ball=new Ellipse2D.Double(0, 0, 25, 25);
	Ellipse2D.Double ball2= new Ellipse2D.Double(200, 200, 10, 10);
	double v=6;
	public DisplayGame() {
	Timer timer=new Timer(33,this);
	timer.start();
	
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2=(Graphics2D)g;

		setBackground(Color.GRAY);
		g2.setColor(Color.ORANGE);
		g2.fill(ball);
		g2.setColor(Color.RED);
		if(ball2==null)return;
		else
		g2.fill(ball2);
		
		//g2.fill(ball2);
		if(ball2==null) return;
		else if(ball.getBounds().intersects(ball2.getBounds())){
			ball.width=30;
			ball.height=30;
			//v=5;
			ball2=null;
			repaint();
		}

		

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
//		double a=(p.x-ball.x-10);
//		double b=(p.y-ball.y-10);
//		ball.x+=a*0.05;
//		ball.y+=b*0.05;
		repaint();
		
		
	}

}
