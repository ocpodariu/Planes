package client.views;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class LoginPanel extends JPanel {
	
	private JLabel serverLbl;
	private JLabel usernameLbl;
	private JLabel passwordLbl;
	
	private JTextField serverTxt;
	private JTextField usernameTxt;
	private JPasswordField passwordTxt;
	
	private JButton connectBtn;
	private JButton registerBtn;
	private JButton loginBtn;	
	
	/**
	 * Initializes the components and adds them to the panel.
	 */
	public LoginPanel() {
		serverLbl   = new JLabel("Server IP:");
		usernameLbl = new JLabel("User:");
		passwordLbl = new JLabel("Pass:");
		
		serverTxt   = new JTextField(10);
		usernameTxt = new JTextField(10);
		passwordTxt = new JPasswordField(10);
		
		connectBtn  = new JButton("Connect");
		registerBtn = new JButton("Register");
		loginBtn    = new JButton("Login");
		
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

	/**
	 * Adds an ActionListener to the <b>Connect</b> button.
	 * @param l the ActionListener
	 */
	public void setConnectBtnActionListener(ActionListener l) {
		connectBtn.addActionListener(l);
	}
	
	/**
	 * Adds an ActionListener to the <b>Register</b> button.
	 * @param l the ActionListener
	 */
	public void setRegisterBtnActionListener(ActionListener l) {
		registerBtn.addActionListener(l);
	}
	
	/**
	 * Adds an ActionListener to the <b>Login</b> button.
	 * @param l the ActionListener
	 */
	public void setLoginBtnActionListener(ActionListener l) {
		loginBtn.addActionListener(l);
	}
	
	/**
	 * Enables or disables the <b>Connect</b> button.
	 * @param b <em>true</em> enables it;
	 * 			<em>false</em> disables it
	 */
	public void setConnectBtnEnabled(boolean b) {
		connectBtn.setEnabled(b);
	}
	
	/**
	 * Enables or disables the <b>Register</b> button.
	 * @param b <em>true</em> enables it;
	 * 			<em>false</em> disables it
	 */
	public void setRegisterBtnEnabled(boolean b) {
		registerBtn.setEnabled(b);
	}
	
	/**
	 * Enables or disables the <b>Login</b> button.
	 * @param b <em>true</em> enables it;
	 * 			<em>false</em> disables it
	 */
	public void setLoginBtnEnabled(boolean b) {
		loginBtn.setEnabled(b);
	}
	
	/**
	 * Enables or disables the <b>Server</b> text field.
	 * @param b <em>true</em> enables it;
	 * 			<em>false</em> disables it
	 */
	public void setServerTextEnabled(boolean b) {
		serverTxt.setEnabled(b);
	}
	
	/**
	 * Enables or disables the <b>Username</b> text field.
	 * @param b <em>true</em> enables it;
	 * 			<em>false</em> disables it
	 */
	public void setUsernameTextEnabled(boolean b) {
		usernameTxt.setEnabled(b);
	}
	
	/**
	 * Enables or disables the <b>Password</b> text field.
	 * @param b <em>true</em> enables it;
	 * 			<em>false</em> disables it
	 */
	public void setPasswordTextEnabled(boolean b) {
		passwordTxt.setEnabled(b);
	}
	
	/**
	 * Returns the text contained in the <b>Server</b> text field.
	 * @return text contained in the <b>Server</b> text field
	 */
	public String getServerText() {
		return serverTxt.getText();
	}
	
	/**
	 * Returns the text contained in the <b>Username</b> text field.
	 * @return text contained in the <b>Username</b> text field
	 */
	public String getUsernameText() {
		return usernameTxt.getText();
	}
	
	/**
	 * Returns the text contained in the <b>Password</b> text field.
	 * @return text contained in the <b>Password</b> text field
	 */
	public String getPasswordText() {
		return String.valueOf(passwordTxt.getPassword());
	}
	
}
