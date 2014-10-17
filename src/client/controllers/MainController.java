package client.controllers;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import client.models.Client;
import client.views.Window;


public class MainController {
	private Client client;
	private Window window;
	private LoginController loginController;
	private HomeController  homeController;
	
	// Panel codes. They are used by the controllers to change
	// the current view.
	public static final int LOGIN       = 0;
	public static final int HOME        = 1;
	public static final int MULTIPLAYER = 2;
	public static final int LEADERBOARD = 3;
	public static final int ABOUT       = 4;
	public static final int EXIT        = 5;
	
	/**
	 * Initializes the models and the sub-controllers.
	 */
	public MainController() {
		client = new Client();
		
		loginController = new LoginController(this);
		homeController  = new HomeController(this);
	}
	
	/**
	 * Starts the game (i.e. creates and initializes the view).
	 */
	public void start() {
		window = new Window();
		window.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				exit();
			}
		});
		changeView(LOGIN);
	}
	
	/**
	 * Returns a reference to the client.
	 * @return reference to the client
	 */
	public Client getClient() { return client; }
	
	/**
	 * Changes the current view(panel) with a new one.
	 * @param option new panel's code <em>(can be found at 
	 * <code>client.controllers.MainController</code>)</em>.
	 */
	public void changeView(int option) {
		final JPanel panel;
		
		// Get a reference to the view(panel) from the
		// corresponding sub-controller.
		switch(option) {
		case LOGIN: panel = loginController.getPanel(); break;
		case HOME:  panel = homeController.getPanel();  break;
		default: panel = null;
		}
		
		// Add that panel to the view, in order to be displayed.
		if (panel != null)
			SwingUtilities.invokeLater(new Runnable() {
				public void run() { window.displayPanel(panel); }
			});
	}
	
	/**
	 * Displays an error dialog in the view.
	 * @param message Message to be displayed.
	 */
	public void displayErrorMessage(String message) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() { window.displayErrorMessage(message); }
		});
	}
	
	/**
	 * Displays an informative dialog in the view.
	 * @param title Subject of the message.
	 * @param message Message to be displayed.
	 */
	public void displayInformativeMessage(String title, String message) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() { window.displayInformativeMessage(title, message); }
		});
	}
	
	/**
	 * Disposes the view, releasing all the resources by it.
	 */
	public void disposeView() {
		window.setVisible(false);
		window.dispose();
	}
	
	/**
	 * Closes the application.
	 */
	public void exit() {
		System.exit(0);
	}

	/**
	 * Main method of the game. It creates a MainController
	 * and uses it to start the game.
	 */
	public static void main(String[] args) {
		MainController mc = new MainController();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() { mc.start(); }
		});
	}

}
