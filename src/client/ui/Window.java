package client.ui;

import java.awt.FlowLayout;

import javax.swing.JFrame;


public class Window {
	
	private static final String TITLE = "Planes";
	private static final int X = 50;	// Top-left corner X-coordinate
	private static final int Y = 50;	// Top-left corner Y-coordinate
	
	private static JFrame frame;
	
	
	public Window() {
		frame = new JFrame(TITLE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(X, Y);
		
		frame.setLayout(new FlowLayout());
		
		frame.add(new Login());
		
		// temporary
		frame.pack();
		
		frame.setResizable(false);
		
		frame.setVisible(true);
	}
	
	/**
	 * Changes the current view(panel) with a new one.
	 * @param option new panel's code <em>(can be found at 
	 * <code>client.ui.Panels</code>)</em>.
	 */
	public static void changePanel(int option) {
		// Remove the previous panel
		frame.getContentPane().removeAll();
		
		// Display the new one
		switch(option) {
		case Panels.LOGIN: frame.add(new Login()); break;
		case Panels.HOME: frame.add(new Home()); break;
		case Panels.MULTIPLAYER: frame.add(new Multiplayer()); break;
		case Panels.LEADERBOARD: frame.add(new Leaderboard()); break;
		case Panels.ABOUT: frame.add(new About()); break;
		case Panels.EXIT: frame.setVisible(false); frame.dispose(); System.exit(1);
		}
		
		frame.pack();
	}

}
