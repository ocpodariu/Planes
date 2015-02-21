package common;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface Server extends Remote {
	
	/**
	 * Registers an account for a new player using the provided
	 * parameters.
	 * @param name name of the player
	 * @param password password of this account
	 * @return <em>true</em> if the registration was successful; <em>false</em> otherwise
	 */
	boolean registerPlayer(String name, String password) throws RemoteException;
	
	/**
	 * Checks a player's credentials and provides access to his account.
	 * @param name account name
	 * @param password account's password
	 * @return reference to a player's account if the credentials
	 * are correct; otherwise, return <em>null</em>.
	 */
	Player login(String name, String password) throws RemoteException;
	
	/**
	 * Creates an array with all the players' statistics and returns it.
	 * @return an array containing player statistics
	 */
	String[] getPlayers() throws RemoteException;
	
	/**
	 * Returns a reference to the GameServer.
	 * @return reference to game server
	 * @throws RemoteException
	 */
	GameServer connectToGameServer() throws RemoteException;

}
