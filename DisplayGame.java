import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;

import javax.swing.*;

public class DisplayGame extends JPanel implements ActionListener{
	int x;
	int y;
	Ellipse2D.Double ball=new Ellipse2D.Double(0, 0, 20, 20);
	public DisplayGame() {
	Timer timer=new Timer(20,this);
	timer.start();
	
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2=(Graphics2D)g;
		g2.fill(ball);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Point p=getMousePosition();
		if(p==null)return;
		double a=(p.x-ball.x-10);
		double b=(p.y-ball.y-10);
		ball.x+=a-0.0000005;
		ball.y+=b-0.0000005;
		repaint();
		
		
	}

}
