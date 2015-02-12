package server;

import java.io.File;
import java.io.FileNotFoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

import common.Constants;
import common.Game;



public class GameImpl extends UnicastRemoteObject implements Game {
	
	/** All maps are located in this folder */
    private static final String MAP_DIRECTORY = "maps\\";
    
    /** All map names have this prefix */
    private static final String MAP_FILE_PREFIX = "map";
    
    /** All map names have this suffix */
    private static final String MAP_FILE_EXTENSION = ".txt";
	
    /** Total number of maps available */
    private static final int MAP_COUNT = 10;
    
    /** Name of Player One. */
	private final String playerOne;
	
	/** Name of Player Two. */
	private final String playerTwo;
	
	/** 
	 * Player One's board. Each cell can have one of the following values:
	 *  	<br><b>0</b> - empty location;
	 * 		<br><b>1</b> - location occupied by plane;
	 * 		<br><b>2</b> - empty location already attacked by opponent;
	 * 		<br><b>3</b> - location occupied by plane already attacked by opponent.
	 */
	private int[][] boardP1;
	
	/** 
	 * Player Two's board. Each cell can have one of the following values:
	 *  	<br><b>0</b> - empty location;
	 * 		<br><b>1</b> - location occupied by plane;
	 * 		<br><b>2</b> - empty location already attacked by opponent;
	 * 		<br><b>3</b> - location occupied by plane already attacked by opponent.
	 */
	private int[][] boardP2;
	
	/**
	 * Indicates whose turn it is:
	 * 		<br><b>true</b> ->  Player One's turn;
	 * 		<br><b>false</b> -> Player Two's turn;
	 */
	private boolean turnStatus;
	
	
	
	
	
	/**
	 * Creates a new Game for the specified players.
	 * @param nameP1 name of first player
	 * @param nameP2 name of second player
	 * @throws RemoteException
	 */
	public GameImpl(String nameP1, String nameP2) throws RemoteException {
		// Initialize player names
		playerOne = nameP1;
		playerTwo = nameP2;
		
		// Initialize their boards
		boardP1 = new int[Constants.BOARD_HEIGHT][Constants.BOARD_WIDTH];
		boardP2 = new int[Constants.BOARD_HEIGHT][Constants.BOARD_WIDTH];
		loadMaps();
		
		// Randomly decide who attacks first
		if (Math.random() < 0.5)
			turnStatus = true;
		else
			turnStatus = false;
	}

	/**
	 * Load 2 random maps to initialize the players' boards.
	 */
	private void loadMaps() {
		int mapNrP1, mapNrP2;
		
        try {
        	// Choose two different map numbers
            mapNrP1 = (int)(MAP_COUNT * Math.random());
            do { 
            	mapNrP2 = (int)(MAP_COUNT * Math.random());
            } while (mapNrP2 == mapNrP1);
            
            int x, y;
            
            // Load Player One's map
            Scanner sc1 = new Scanner(new File(MAP_DIRECTORY + MAP_FILE_PREFIX + mapNrP1 + MAP_FILE_EXTENSION));
            
            while (sc1.hasNextInt()) {
                x = sc1.nextInt();
                if (sc1.hasNextInt()) {
                    y = sc1.nextInt();
                    boardP1[x][y] = 1;
                }
                else {
                    break;
                }
            }
            
            sc1.close();
            
            // Load Player Two's map
            Scanner sc2 = new Scanner(new File(MAP_DIRECTORY + MAP_FILE_PREFIX + mapNrP2 + MAP_FILE_EXTENSION));
            
            while (sc2.hasNextInt()) {
                x = sc2.nextInt();
                if (sc2.hasNextInt()) {
                    y = sc2.nextInt();
                    boardP2[x][y] = 1;
                }
                else {
                    break;
                }
            }
            
            sc2.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Map file not found!");
        }
	}

	@Override
	public String getOpponentName(String playerName) throws RemoteException {
		if (playerName.equals(playerOne))
			return playerTwo;
		else
			return playerOne;
	}

	@Override
	public int[][] getBoard(String playerName) throws RemoteException {
		if (playerName.equals(playerOne))
			return boardP1;
		else
			return boardP2;
	}

	@Override
	public boolean getTurnStatus(String playerName) throws RemoteException {
		if (playerName.equals(playerOne))
			return turnStatus;
		else
			return !turnStatus;
	}

	@Override
	public int attack(String playerName, int column, int line) throws RemoteException {
		// If Player One wants to attack
		if (playerName.equals(playerOne)) {
			// If it's his turn
			if (turnStatus) {
				// If he has hit an enemy plane
				if (boardP2[line][column] == 1) {
					// It's still his turn, so turnStatus doesn't change
					
					// Update opponent's board to indicate an attacked plane
					boardP2[line][column] = 3;
					
					return Constants.GAME_ATTACK_HIT;
				}
				// If he hasn't hit an enemy plane
				else {
					// It's Player Two's turn
					turnStatus = false;
					
					// Update opponent's board to indicate an attacked empty location
					boardP2[line][column] = 2;
					
					return Constants.GAME_ATTACK_MISS;
				}
			} 
			// If it's not his turn
			else
				return Constants.GAME_ATTACK_INVALID;
		}
		
		// If Player Two wants to attack
		else {
			// If it's his turn
			if (!turnStatus) {
				// If he has hit an enemy plane
				if (boardP1[line][column] == 1) {
					// It's still his turn, so turnStatus doesn't change
					
					// Update opponent's board to indicate an attacked plane
					boardP1[line][column] = 3;
					
					return Constants.GAME_ATTACK_HIT;
				}
				// If he hasn't hit an enemy plane
				else {
					// It's Player One's turn
					turnStatus = true;
					
					// Update opponent's board to indicate an attacked empty location
					boardP1[line][column] = 2;
					
					return Constants.GAME_ATTACK_MISS;
				}
			}
			// If it's not his turn
			else {
				return Constants.GAME_ATTACK_INVALID;
			}
		}
	}

}
