package client.models;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import common.Constants;
import common.Player;
import common.Server;


public class Client {
	
	private Server server = null;
	private Player player = null;
	
	/**
	 * Connects to the specified server and gets a remote reference
	 * to it.
	 * @param host server's address
	 * @return <em>true</em> if the connection was successful;
	 * 			<em>false</em>, otherwise
	 */
	public boolean connectToServer(String host) {
		Registry r = null;
		
		try {
			// Getting reference to server's registry
			r = LocateRegistry.getRegistry(host, Constants.RMI_PORT);
			
			// Getting reference to remote object
			server = (Server) r.lookup(Constants.RMI_NAME);
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Sends a request to the server to create a new account
	 * using the specified credentials.
	 * @param name account name
	 * @param password account's password
	 * @return <em>true</em> if the connection was successful;
	 * 			<em>false</em>, otherwise
	 */
	public boolean registerPlayer(String name, String password) {
		try {
			if (server.registerPlayer(name, password))
				return true;
			return false;
		} catch(Exception e) {
			return false;
		}
	}

	/**
	 * Sends a request to the server to login with the specified
	 * credentials.<br>
	 * If they are correct it gets a remote reference to the
	 * player's account.
	 * @param name account name
	 * @param password account's password
	 * @return <em>true</em> if the connection was successful;
	 * 			<em>false</em>, otherwise
	 */
	public boolean login(String name, String password) {
		try {
			player = server.login(name, password);
			if (player == null)
				return false;
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
}
