package server;

import java.rmi.RemoteException;

import common.Game;


/**
 * Helper class used by the GameServer to better
 * organize its activity.
 */
class GameRoom {
	
	/** Maximum number of players that can join this room */
	private static final int MAX_NR_OF_PLAYERS = 2;
	
	/** Total number of rooms created so far */
	private static int count;
	
	/** Number of the room */
	private int roomNumber;
	
	/** Name of the player who created this room */
	private final String creator;
	
	/** Current number of players in this room */
	private int currNrPlayers;
	
	/** Names of the players currently in this room */
	private String[] playerNames;
	
	/**
	 * Statuses of the players currently in this room.
	 * (if they're ready or not to start the game)
	 */
	private boolean[] playerStatuses;
	
	/** Indicates whether the game has already started */
	private boolean gameStarted;
	
	/** The Game corresponding to this room */
	private Game game;
	
	/**
	 * Creates a new game room for this player and
	 * automatically adds him.
	 * @param playerName name of the player
	 */
	public GameRoom(String playerName) {
		roomNumber = count++;
		creator = playerName;

		playerNames    = new String[MAX_NR_OF_PLAYERS];
		playerStatuses = new boolean[MAX_NR_OF_PLAYERS];

		game = null;
		
		addPlayer(playerName);
	}
	
	/**
	 * Returns the room's number.
	 * @return number of the room
	 */
	public int getRoomNumber() { return roomNumber; }
	
	/**
	 * Returns the name of player who created the room.
	 * @return name of the player
	 */
	public String getCreator() { return creator; }
	
	/**
	 * Checks if the game room is empty.
	 * @return <em>true</em>, if it's empty; <br>
	 * 		   <em>false</em>, otherwise.
	 */
	public boolean isEmpty() { return currNrPlayers == 0; }
	
	/**
	 * Checks if the game room is full.
	 * @return <em>true</em>, if it's full; <br>
	 * 		   <em>false</em>, otherwise.
	 */
	public boolean isFull() { return currNrPlayers == MAX_NR_OF_PLAYERS; }
	
	/**
	 * Returns the number of players who are currently in this room.
	 * @return number of players
	 */
	public int getCurrNrPlayers() { return currNrPlayers; }
	
	/**
	 * Adds a player to the room, unless it's already full.<br>
	 * The default status of a player is "NOT READY".
	 * @param playerName name of the player
	 */
	public void addPlayer(String playerName) {
		if (!isFull()) {
			// Add player
			playerNames[currNrPlayers]    = playerName;
			playerStatuses[currNrPlayers] = false;
			
			// Update number of players
			currNrPlayers++;
		}
	}
	
	/**
	 * Removes the specified player from the room.
	 * @param playerName name of the player
	 */
	public void removePlayer(String playerName) {
		// Find the player
		for (int i = 0; i < currNrPlayers; i++)
			if (playerNames[i].equals(playerName)) {
				// "Remove" him
				playerNames[i] = null;
				
				// Update number of players
				currNrPlayers--;
				
				break;
			}
	}
	
	/**
	 * Changes a player's status to a new one.
	 * @param playerName name of the player
	 * @param status <em>true</em>, if he's ready to start the game;
	 * 				 <em>false</em>, otherwise.
	 */
	public void setPlayerStatus(String playerName, boolean status) {
		// Find the player
		for (int i = 0; i < currNrPlayers; i++)
			if (playerNames[i].equals(playerName)) {
				// Update his status
				playerStatuses[i] = status;
				
				break;
			}
	}
	
	/**
	 * Returns the status of a player.
	 * @param playerName name of the player
	 * @return <em>true</em>, if he's ready to start the game; <br>
	 * 		   <em>false</em>, otherwise.
	 */
	public boolean getPlayerStatus(String playerName) {
		// Find the player
		for (int i = 0; i < currNrPlayers; i++)
			if (playerNames[i].equals(playerName))
				// Return his status
				return playerStatuses[i];
		
		return false;
	}
	
	/**
	 * Returns an array containing the name and status of all players
	 * who are currently in the room. <br>
	 * Each element of the array contains information about a single player
	 * i.e its name and status separated by a blank space (' ').
	 * @return an array containing player details
	 * @return
	 */
	public String[] getPlayers() {
		if (!isEmpty()) {
			String[] players = new String[currNrPlayers];
			
			for (int i = 0; i < currNrPlayers; i++)
				players[i] = playerNames[i] + " " + playerStatuses[i];
			
			return players;
		}
		
		return null;
	}
	
	/**
	 * Checks if the game has already started
	 * @return <em>true</em>, if the game's started; <br>
	 * 		   <em>false</em>, otherwise.
	 */
	public boolean isGameStarted() { return gameStarted; }
	
	/**
	 * Starts a new game for the players in the room.<br>
	 * If the game has already been started, it returns a reference
	 * to it. Otherwise, it checks if there are enough players
	 * to start a new game i.e. if the room is full.
	 * @return reference to the game
	 * @throws RemoteException
	 */
	public Game startGame() throws RemoteException {
		if (isGameStarted())
			return game;
		else if (isFull()) {
			game = new GameImpl(playerNames[0], playerNames[1]);
			return game;
		}
		else
			return null;
	}

}
