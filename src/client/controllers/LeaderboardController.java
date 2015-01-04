package client.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingWorker;

import client.models.LeaderboardTableModel;
import client.views.LeaderboardPanel;


public class LeaderboardController {
	
	private final MainController mainController;
	private final LeaderboardPanel leaderboardPanel;
	private final LeaderboardTableModel leaderboardTableModel;
	
	/**
	 * Creates a new LeaderboardController that will be
	 * a sub-controller of the MainController.<br>
	 * It also sets up its view (the home panel).
	 * @param mc the MainController
	 */
	public LeaderboardController(MainController mc) {
		mainController = mc;
		
		// Initialize table model with an empty matrix, due to
		// the fact that "null" cannot be used.
		leaderboardTableModel = new LeaderboardTableModel(new Object[][] {});
		
		leaderboardPanel = new LeaderboardPanel(leaderboardTableModel);
		
		addActionListeners();
	}
	
	/**
	 * Adds action listeners to the panel's components.
	 */
	public void addActionListeners() {
		leaderboardPanel.setUpdateBtnActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				leaderboardPanel.setStatus("Updating...");
				updateTableData();
			}
		});
		
		leaderboardPanel.setBackBtnActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainController.changeView(MainController.HOME);
			}
		});
		
	}
	
	/**
	 * Tries to update the table data, sending a request
	 * to the server to obtain the players' statistics.
	 * This process happens in the background to avoid
	 * freezing the GUI.
	 */
	public void updateTableData() {
		new SwingWorker<Void, Void>() {
			String[] players;

			protected Void doInBackground() throws Exception {
				// Get the players' statistics from the server
				players = mainController.getClient().getPlayers();
				
				return null;
			}
			
			protected void done() {
				if (players != null) {
					String[][] data = new String[players.length][];
					
					// Each element of the array contains all the information
					// about a player. In order to use it inside the table,
					// it has to be processed first.
					for (int i = 0; i < players.length; i++)
						data[i] = players[i].split(" ");
					
					leaderboardTableModel.updateData(data);
					
					leaderboardPanel.setStatus("Data updated!");
				}
				else
					leaderboardPanel.setStatus("Update failed! Try again...");
			}
			
		}.execute();
		
		// Wait 1.5 seconds and then clear the status label
		new SwingWorker<Void, Void>() {
			protected Void doInBackground() throws Exception {
				Thread.sleep(1500);
				return null;
			}
			
			protected void done() {
				leaderboardPanel.setStatus("");
			}
			
		}.execute();
	}
	
	/**
	 * Returns a reference to its view(panel).
	 * @return reference to its view
	 */
	public LeaderboardPanel getPanel() { return leaderboardPanel; }

}
