package client.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import client.views.HomePanel;



public class HomeController {
	
	private final MainController mainController;
	private final HomePanel homePanel;
	
	/**
	 * Creates a new HomeController that will be a
	 * sub-controller of the MainController.<br>
	 * It also sets up its view (the home panel).
	 * @param mc the MainController
	 */
	public HomeController(MainController mc) {
		mainController = mc;
		homePanel = new HomePanel();
		addActionListeners();
	}
	
	/**
	 * Adds action listeners to the panel's components.
	 */
	private void addActionListeners() {
		homePanel.setMultiplayerBtnActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainController.changeView(MainController.MULTIPLAYER);
			}
		});
		
		homePanel.setLeaderboardBtnActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainController.changeView(MainController.LEADERBOARD);
			}
		});
		
		homePanel.setAboutBtnActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainController.changeView(MainController.ABOUT);
			}
		});
		
		homePanel.setExitBtnActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainController.disposeView();
				mainController.exit();
			}
		});
	}
	
	/**
	 * Returns a reference to its view(panel).
	 * @return reference to its view
	 */
	public HomePanel getPanel() { return homePanel; }

}
