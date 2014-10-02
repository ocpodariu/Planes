package client.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Leaderboard extends JPanel {
	
	private JLabel tempLbl;
	private JButton backBtn;
	
	public Leaderboard() {
		tempLbl = new JLabel("Not yet implemented.");
		
		backBtn = new JButton("Back");
		
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Window.changePanel(Panels.HOME);
			}
		});
		
		add(tempLbl);
		add(backBtn);
	}

}
