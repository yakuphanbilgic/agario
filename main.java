import javax.swing.JFrame;

public class main {

	public static void main(String[] args) {
		JFrame frame= new JFrame();
		DisplayGame panel= new DisplayGame();
		frame.setVisible(true);
		frame.add(panel);
		frame.setSize(640, 480);
		//frame.setLocation(200, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		

	}

}
