package common;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface Game extends Remote {
	
	/**
	 * Depending on which player calls this method, it returns
	 * the other player's name i.e. the opponent's name.
	 * @param playerName  name of the player calling this method
	 * @return the name of the opponent
	 * @throws RemoteException
	 */
	String getOpponentName(String playerName) throws RemoteException;
	
	/**
	 * Returns the board corresponding to the player which called
	 * the method.
	 * @param playerName name of the player calling this method
	 * @return an array of integers describing the board:
	 * 			<br><b>0</b> - empty location;
	 * 			<br><b>1</b> - location occupied by plane;
	 * 			<br><b>2</b> - empty location already attacked by opponent;
	 * 			<br><b>3</b> - location occupied by plane already attacked by opponent.
	 * @throws RemoteException
	 */
	int[][] getBoard(String playerName) throws RemoteException;
	
	/**
	 * Checks if it's the calling player's turn.
	 * @param playerName name of the player calling this method
	 * @return <b>true</b> it's his turn <br>
	 * 		   <b>false</b> it's his opponent's turn <br>
	 * @throws RemoteException
	 */
	boolean getTurnStatus(String playerName) throws RemoteException;
	
	/**
	 * Returns the score corresponding to the player which called
	 * the method.
	 * @param playerName name of the player calling this method
	 * @return player's score
	 * @throws RemoteException
	 */
	int getScore(String playerName) throws RemoteException;
	
	/**
	 * Depending on which player calls this method, it returns
	 * the other player's score i.e. the opponent's score.
	 * @param playerName name of the player calling this method
	 * @return opponent's score
	 * @throws RemoteException
	 */
	int getOpponentScore(String playerName) throws RemoteException;
	
	/**
	 * Attacks the specified location on the opponent's board and
	 * returns the result of the attack i.e. if he has hit
	 * an enemy plane or not.
	 * @param playerName name of the player calling this method
	 * @param column X-coordinate of the location
	 * @param line Y-coordinate of the location
	 * @return {@link Constants#GAME_ATTACK_INVALID} if it's not this player's turn <br>
	 * 		   {@link Constants#GAME_ATTACK_HIT} if the attack was successful <br>
	 * 		   {@link Constants#GAME_ATTACK_MISS} if the attack was unsuccessful <br>
	 * 		   
	 * @throws RemoteException
	 */
	int attack(String playerName, int column, int line) throws RemoteException;
	
}
