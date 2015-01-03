package client.views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class AboutPanel extends JPanel {
	
	private String text;
	private Font font;
	private JTextArea textArea;
	private JButton backBtn;
	
	/**
	 * Initializes the components and adds them to the panel.
	 */
	public AboutPanel() {
		text = "This game has been developed by Ovidiu PODARIU.";
		font = new Font("Tahoma", Font.BOLD, 12);
		
		textArea = new JTextArea(text, 5, 5);
		textArea.setFont(font);
		textArea.setEditable(false);
		textArea.setBackground(Color.LIGHT_GRAY);
		
		backBtn = new JButton("Back");
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		add(textArea);
		add(backBtn);
		
		textArea.setAlignmentX(Component.LEFT_ALIGNMENT);
		backBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
	}
	
	/**
	 * Adds an ActionListener to the <b>Back</b> button.
	 * @param l the ActionListener
	 */
	public void setBackBtnActionListener(ActionListener l) {
		backBtn.addActionListener(l);
	}

}
