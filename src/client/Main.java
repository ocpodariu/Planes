package client;

import javax.swing.SwingUtilities;

import client.ui.Window;


public class Main {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() { new Window(); }
		});
	}

}
