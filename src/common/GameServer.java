package common;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface GameServer extends Remote {
	
	/**
	 * Returns an array containing information about all the game rooms
	 * created. <br>
	 * Each element of the array contains the number of the room and
	 * the name of the player who created it, separated by a blank space (' ').
	 * @return an array containing room details
	 * @throws RemoteException
	 */
	String[] getRooms() throws RemoteException;
	
	/**
	 * Creates a new game room and adds the specified player to it.
	 * @param playerName name of the player calling this method
	 * @return number of the newly created room
	 * @throws RemoteException
	 */
	int createRoom(String playerName) throws RemoteException;
	
	/**
	 * Adds that player to the specified room, unless it's already full.
	 * @param roomNumber number of the room
	 * @param playerName name of the player calling this method
	 * @return - the room number, if successful; <br>
	 * 		   - {@link Constants#GAME_ROOM_FULL}, if the room is full; <br>
	 * 		   - {@link Constants#GAME_ROOM_NOT_FOUND}, if the room doesn't exist.
	 * @throws RemoteException
	 */
	int joinRoom(int roomNumber, String playerName) throws RemoteException;
	
	/**
	 * Removes that player from the specified room.
	 * @param roomNumber number of the room he's in
	 * @param playerName name of the player calling this method
	 * @throws RemoteException
	 */
	void leaveRoom(int roomNumber, String playerName) throws RemoteException;
	
	/**
	 * Returns the number of players who are currently in the specified room.
	 * @param roomNumber number of the room
	 * @return number of players
	 * @throws RemoteException
	 */
	int getNumberOfPlayers(int roomNumber) throws RemoteException;
	
	/**
	 * Returns an array containing the name and status of all players
	 * who are currently in the specified room. <br>
	 * Each element of the array contains information about a single player
	 * i.e its name and status separated by a blank space (' ').
	 * @param roomNumber number of the room
	 * @return an array containing player details
	 * @throws RemoteException
	 */
	String[] getPlayers(int roomNumber) throws RemoteException;
	
	/**
	 * Sets the player's status to indicate if he's ready to start
	 * the game or not.
	 * @param roomNumber number of the room he's in
	 * @param playerName name of the player calling this method
	 * @param status <em>true</em>, if he's ready to play;
	 * 				 <em>false</em>, otherwise.
	 * @throws RemoteException
	 */
	void setPlayerStatus(int roomNumber, String playerName, boolean status) throws RemoteException;
	
	/**
	 * Returns a player's status, indicating if he's ready to start
	 * the game or not.
	 * @param roomNumber number of the room he's in
	 * @param playerName name of the player
	 * @return <em>true</em>, if he's ready to play; <br>
	 * 		   <em>false</em>, otherwise.
	 * @throws RemoteException
	 */
	boolean getPlayerStatus(int roomNumber, String playerName) throws RemoteException;
	
	/**
	 * Starts a new game for the specified game room if it's full. <br>
	 * If the game's already started, it just returns a reference to it.
	 * @param roomNumber number of the room
	 * @return reference to the newly created game
	 * @throws RemoteException
	 */
	Game startGame(int roomNumber) throws RemoteException;

}
