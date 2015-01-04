package client.views;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.table.TableModel;


public class LeaderboardPanel extends JPanel {
	
	private JTable  table;
	private JScrollPane scrollPane;
	private JButton updateBtn;
	private JButton backBtn;
	private JLabel  statusLbl;
	
	/**
	 * Initializes the components and adds them to the panel.
	 */
	public LeaderboardPanel(TableModel dm) {
		table = new JTable(dm);
		table.setFillsViewportHeight(true);
		scrollPane = new JScrollPane(table);
		
		updateBtn = new JButton("Update");
		backBtn   = new JButton("Back");
		
		statusLbl = new JLabel();
		
		setPreferredSize(new Dimension(400, 250));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		add(scrollPane);
		add(updateBtn);
		add(statusLbl);
		add(backBtn);
		
		scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		updateBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
		statusLbl.setAlignmentX(Component.LEFT_ALIGNMENT);
		backBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
	}
	
	/**
	 * Adds an ActionListener to the <b>Back</b> button.
	 * @param l the ActionListener
	 */
	public void setBackBtnActionListener(ActionListener l) {
		backBtn.addActionListener(l);
	}
	
	/**
	 * Adds an ActionListener to the <b>Update</b> button.
	 * @param l the ActionListener
	 */
	public void setUpdateBtnActionListener(ActionListener l) {
		updateBtn.addActionListener(l);
	}
	
	/**
	 * Updates the status of the data update process.
	 * @param text use one of the following values:
	 * 			<br><em>"Updating..."</em> - a request has been sent to the server;
	 * 			<br><em>"Data updated!"</em> - the update was successful;
	 * 			<br><em>"Update failed! Try again..."</em> - an error has occurred.
	 */
	public void setStatus(String text) {
		statusLbl.setText(text);
	}

}
