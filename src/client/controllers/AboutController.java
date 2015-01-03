package client.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import client.views.AboutPanel;


public class AboutController {
	
	private final MainController mainController;
	private final AboutPanel aboutPanel;
	
	/**
	 * Creates a new AboutController that will be a
	 * sub-controller of the MainController.<br>
	 * It also sets up its view (the about panel).
	 * @param mc the MainController
	 */
	public AboutController(MainController mc) {
		mainController = mc;
		aboutPanel = new AboutPanel();
		addActionListeners();
	}
	
	/**
	 * Adds action listeners to the panel's components.
	 */
	private void addActionListeners() {
		aboutPanel.setBackBtnActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainController.changeView(MainController.HOME);
			}
		});
	}
	
	/**
	 * Returns a reference to its view(panel).
	 * @return reference to its view
	 */
	public AboutPanel getPanel() { return aboutPanel; }

}
