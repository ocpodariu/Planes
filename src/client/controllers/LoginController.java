package client.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import client.views.LoginPanel;


public class LoginController {
	
	private final MainController mainController;
	private final LoginPanel loginPanel;
	
	/**
	 * Creates a new LoginController that will be a
	 * sub-controller of the MainController.<br>
	 * It also sets up its view (the login panel).
	 * @param mc the MainController
	 */
	public LoginController(MainController mc) {
		mainController = mc;
		loginPanel = new LoginPanel();
		initializePanel();
		addActionListeners();
	}
	
	/**
	 * Initializes the login panel (i.e. sets the components'
	 * states - some are disabled, some are not).
	 */
	private void initializePanel() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				loginPanel.setUsernameTextEnabled(false);
				loginPanel.setPasswordTextEnabled(false);
				loginPanel.setRegisterBtnEnabled(false);
				loginPanel.setLoginBtnEnabled(false);
			}
		});
	}
	
	/**
	 * Adds action listeners to the panel's components.
	 */
	private void addActionListeners()  {
		loginPanel.setConnectBtnActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String address = loginPanel.getServerText();

				// Try to connect to the server, in the background,
				// to avoid freezing the GUI.
				new SwingWorker<Void, Void>() {
					boolean successful = false;

					protected Void doInBackground() throws Exception {
						successful = mainController.getClient().connectToServer(address);
						return null;
					}
					
					protected void done() {
						if (successful) {
							mainController.displayInformativeMessage("Server", "Connected successfully.");					
							
							loginPanel.setServerTextEnabled(false);
							loginPanel.setConnectBtnEnabled(false);
							loginPanel.setUsernameTextEnabled(true);
							loginPanel.setPasswordTextEnabled(true);
							loginPanel.setRegisterBtnEnabled(true);
							loginPanel.setLoginBtnEnabled(true);
						}
						else
							mainController.displayErrorMessage("Cannot connect to the"
									+ " specified server. Please try again.");
					}
					
				}.execute();
			}
		});
		
		loginPanel.setRegisterBtnActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name     = loginPanel.getUsernameText();
				String password = loginPanel.getPasswordText();
				
				// If one of the fields is empty, there's no need to send
				// a request to the server.
				if (name.length() == 0 || password.length() == 0) return;
				
				// Try to register the player, in the background,
				// to avoid freezing the GUI.
				new SwingWorker<Void, Void>() {
					boolean successful = false;

					protected Void doInBackground() throws Exception {
						successful = mainController.getClient().registerPlayer(name, password);
						return null;
					}
					
					protected void done() {
						if (successful) {
							mainController.displayInformativeMessage("Register", "Registration successful.");
						}
						else
							mainController.displayErrorMessage("Cannot create a new"
									+ " account with that name. Please try again.");
					}
					
				}.execute();
			}
		});
		
		loginPanel.setLoginBtnActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name     = loginPanel.getUsernameText();
				String password = loginPanel.getPasswordText();
				
				// If one of the fields is empty, there's no need to send
				// a request to the server.
				if (name.length() == 0 || password.length() == 0) return;
				
				// Send a login request to the server, in the background,
				// to avoid freezing the GUI.
				new SwingWorker<Void, Void>() {
					boolean successful = false;

					protected Void doInBackground() throws Exception {
						successful = mainController.getClient().login(name, password);
						return null;
					}
					
					protected void done() {
						if (successful) {
							mainController.displayInformativeMessage("Login", "Login successful.");
							
							// Go to HOME screen
							mainController.changeView(MainController.HOME);
						}
						else
							mainController.displayErrorMessage("Invalid credentials.");
					}
					
				}.execute();
			}
		});
	}

	/**
	 * Returns a reference to its view(panel).
	 * @return reference to its view
	 */
	public LoginPanel getPanel() { return loginPanel; }
	
}
