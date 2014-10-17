package common;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface Player extends Remote {
	
	/**
	 * Returns the player's name.
	 * @return player's name
	 */
	String getName() throws RemoteException;
	
	/**
	 * Returns the total number of games played.
	 * @return number of games played
	 */
	int getTotalGames() throws RemoteException;
	
	/**
	 * Returns the number of games won.
	 * @return number of games won
	 */
	int getWins() throws RemoteException;
	
	/**
	 * Returns the number of games lost.
	 * @return number of games lost
	 */
	int getLosses() throws RemoteException;
	
}
