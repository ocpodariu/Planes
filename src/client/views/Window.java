package client.views;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Window extends JFrame {
	
	private static final String TITLE = "Planes";
	private static final int X = 50;	// Top-left corner X-coordinate
	private static final int Y = 50;	// Top-left corner Y-coordinate
	
	/**
	 * Initializes the frame.
	 */
	public Window() {
		super(TITLE);
		setLocation(X, Y);
		setLayout(new FlowLayout());
		setResizable(false);
		setVisible(true);
	}
	
	/**
	 * Displays a panel. Changes the current view(panel)
	 * with a new one.
	 * @param panel Panel to be displayed.
	 */
	public void displayPanel(JPanel panel) {
		// Remove the previous panel
		getContentPane().removeAll();
		
		// Display the new one
		add(panel);
		
		pack();
	}
	
	/**
	 * Displays an error dialog.
	 * @param message Message to be displayed.
	 */
	public void displayErrorMessage(String message) {
		JOptionPane.showMessageDialog(this, message, "Error!",
			    JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * Displays an informative dialog.
	 * @param title Subject of the message.
	 * @param message Message to be displayed.
	 */
	public void displayInformativeMessage(String title, String message) {
		JOptionPane.showMessageDialog(this, message, title, 
				JOptionPane.INFORMATION_MESSAGE);
	}

}
