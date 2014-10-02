package client.ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;


public class Home extends JPanel {
	
	private JButton multiplayer;
	private JButton leaderboard;
	private JButton about;
	private JButton exit;
	
	public Home() {
		multiplayer = new JButton("Multiplayer");
		leaderboard = new JButton("Leaderboard");
		about = new JButton("About");
		exit = new JButton("Exit");
		
		multiplayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Window.changePanel(Panels.MULTIPLAYER);
			}
		});
		
		leaderboard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Window.changePanel(Panels.LEADERBOARD);
			}
		});
		
		about.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Window.changePanel(Panels.ABOUT);
			}
		});
		
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Window.changePanel(Panels.EXIT);
			}
		});
		
		setLayout(new GridLayout(4, 1));
		
		add(multiplayer);
		add(leaderboard);
		add(about);
		add(exit);
	}

}
