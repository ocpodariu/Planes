package server;

import common.Player;


public class PlayerImpl implements Player {
	
	private final String name;
	private final String password;
	private int totalGames;
	private int wins;
	private int losses;
	
	/**
	 * Creates a new Player with the specified name and
	 * password.
	 */
	public PlayerImpl(String name, String password) {
		this.name     = name;
		this.password = password;
	}

	/**
	 * Checks if the password is correct. 
	 * @return true if the password is correct; false otherwise
	 */
	public boolean checkPassword(String password) {
		return this.password == password;
	}
	
	/**
	 * @return player's name
	 */
	public String getName() { return name; }

	/**
	 * @return number of games played
	 */
	public int getTotalGames() { return totalGames; }

	/**
	 * @return number of games won
	 */
	public int getWins() { return wins; }

	/**
	 * @return number of games lost
	 */
	public int getLosses() { return losses; }

}
