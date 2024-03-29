package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import common.Player;


public class PlayerImpl extends UnicastRemoteObject implements Player {
	
	private final String name;
	private final String password;
	private int totalGames;
	private int wins;
	private int losses;
	
	/**
	 * Creates a new Player with the specified name and password.
	 */
	public PlayerImpl(String name, String password) throws RemoteException {
		this.name     = name;
		this.password = password;
	}
	
	/**
	 * Checks if the password is correct. 
	 * @return <em>true</em> if the password is correct;
	 * 			<em>false</em> otherwise
	 */
	public boolean checkPassword(String password) {
		return this.password.equals(password);
	}
	
	public String getName() throws RemoteException { return name; }

	public int getTotalGames() throws RemoteException { return totalGames; }

	public int getWins() throws RemoteException { return wins; }

	public int getLosses() throws RemoteException { return losses; }
	
	/**
	 * Method accessible only on the server side,
	 * used for saving the player profiles to the HDD.
	 * @return player's password
	 */
	public String getPassword() { return password; }
	
	/**
	 * Method accessible only on the server side,
	 * used for loading the player profiles from the HDD.
	 * @return player's password
	 */
	public void setStats(int totalGames, int wins, int losses) {
		this.totalGames = totalGames;
		this.wins       = wins;
		this.losses     = losses;
	}

}
