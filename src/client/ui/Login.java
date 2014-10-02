package client.ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Login extends JPanel {
	
	private JLabel serverLbl;
	private JLabel usernameLbl;
	private JLabel passwordLbl;
	
	private JTextField serverTxt;
	private JTextField usernameTxt;
	private JTextField passwordTxt;
	
	private JButton connectBtn;
	private JButton registerBtn;
	private JButton loginBtn;	
	
	public Login() {
		serverLbl   = new JLabel("Server IP:");
		usernameLbl = new JLabel("User:");
		passwordLbl = new JLabel("Pass:");
		
		serverTxt   = new JTextField(10);
		usernameTxt = new JTextField(10);
		passwordTxt = new JTextField(10);
		
		connectBtn  = new JButton("Connect");
		registerBtn = new JButton("Register");
		loginBtn    = new JButton("Login");
		
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Window.changePanel(Panels.HOME);
			}
		});
		
		setLayout(new GridLayout(9, 1));
		
		add(serverLbl);
		add(serverTxt);
		add(connectBtn);
		add(usernameLbl);
		add(usernameTxt);
		add(passwordLbl);
		add(passwordTxt);
		add(registerBtn);
		add(loginBtn);		
	}

}
