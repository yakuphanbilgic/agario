import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.ScrollPaneConstants;


public class main {

	public static boolean gamemode;
	public static void main(String[] args) {
		
		JFrame frame= new JFrame("NAM NAM NAM");
		JScrollPane pane= new JScrollPane();
		JViewport vport= new JViewport();
		DisplayGame panel= new DisplayGame();

		panel.menu.setDisplayGame(panel);
		vport.add(panel);
		frame.setVisible(true);
		pane.setViewport(vport);
		vport.add(panel);
		frame.add(pane);
		frame.setSize(DisplayGame.WIDTH, DisplayGame.HEIGHT);
		panel.setvPort(vport);
		pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		pane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		//frame.setLocation(200, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		

	}

}
