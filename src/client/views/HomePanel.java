package client.views;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;


public class HomePanel extends JPanel {
	
	private JButton multiplayerBtn;
	private JButton leaderboardBtn;
	private JButton aboutBtn;
	private JButton exitBtn;
	
	/**
	 * Initializes the components and adds them to the panel.
	 */
	public HomePanel() {
		multiplayerBtn = new JButton("Multiplayer");
		leaderboardBtn = new JButton("Leaderboard");
		aboutBtn       = new JButton("About");
		exitBtn        = new JButton("Exit");
		
		setLayout(new GridLayout(4, 1));
		
		add(multiplayerBtn);
		add(leaderboardBtn);
		add(aboutBtn);
		add(exitBtn);
	}
	
	/**
	 * Adds an ActionListener to the <b>Multiplayer</b> button.
	 * @param l the ActionListener
	 */
	public void setMultiplayerBtnActionListener(ActionListener l) {
		multiplayerBtn.addActionListener(l);
	}
	
	/**
	 * Adds an ActionListener to the <b>Leaderboard</b> button.
	 * @param l the ActionListener
	 */
	public void setLeaderboardBtnActionListener(ActionListener l) {
		leaderboardBtn.addActionListener(l);
	}
	
	/**
	 * Adds an ActionListener to the <b>About</b> button.
	 * @param l the ActionListener
	 */
	public void setAboutBtnActionListener(ActionListener l) {
		aboutBtn.addActionListener(l);
	}
	
	/**
	 * Adds an ActionListener to the <b>Exit</b> button.
	 * @param l the ActionListener
	 */
	public void setExitBtnActionListener(ActionListener l) {
		exitBtn.addActionListener(l);
	}

}
