import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Menu implements MouseListener{
	private Rectangle playButton = new Rectangle(DisplayGame.WIDTH/2-50,DisplayGame.HEIGHT/2,100,50);
	private Rectangle quitButton = new Rectangle(DisplayGame.WIDTH/2-50, DisplayGame.HEIGHT/2+100, 100, 50);
	private boolean enabled = true;
	public void render(Graphics2D g2){
		Font font= new Font("calibri", Font.BOLD,50);
		g2.setFont(font);
		g2.setColor(Color.YELLOW);
		g2.fillOval(DisplayGame.WIDTH/2-73, DisplayGame.HEIGHT/2-250, 150, 150);
		g2.setColor(Color.ORANGE);
		g2.drawString("Nam Nam Nam", DisplayGame.WIDTH/2-170, 300);
		g2.setColor(Color.BLACK);
		g2.drawString("Play", playButton.x, playButton.y+40);
		g2.drawString("Quit", quitButton.x, quitButton.y+40);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(enabled){
			int mx=e.getX();
			int my=e.getY();
			if(mx>=370&&mx<=470){
				if(my>=340&&my<=390){
					DisplayGame.state=DisplayGame.STATE.GAME;
					enabled=false;
				}
			}
			if(mx>=370&&mx<=470){
				if (my>=440&&my<490) {
					System.exit(1);
				}
			}
		}
			
		
		//840x680
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
