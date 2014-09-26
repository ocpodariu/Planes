package server;

import java.util.ArrayList;
import java.util.List;

import common.Player;
import common.Server;


public class ServerImpl implements Server {
	
	private List<PlayerImpl> players;
	
	public ServerImpl() {
		players = new ArrayList<PlayerImpl>();
	}

	/**
	 * Registers an account for a new player using the provided
	 * parameters.
	 * @param name name of the player
	 * @param password password of this account
	 * @return true if the registration was successful; false otherwise
	 */
	public boolean registerPlayer(String name, String password) {
		// If there's already a player registered with this name, exit.
		for (int i = 0; i < players.size(); i++)
			if (players.get(i).getName() == name)
				return false;
		
		// Create a new player and add it to the list of registered players.
		players.add(new PlayerImpl(name, password));
		
		return true;
	}

	/**
	 * Checks a player's credentials and provides access to his account.
	 * @param name account name
	 * @param password account's password
	 * @return reference to a player's account if the credentials
	 * are correct; otherwise, return null.
	 */
	public Player login(String name, String password) {
		// Find the player with this name and check if the password matches.
		PlayerImpl p = null;
		for (int i = 0; i < players.size(); i++) {
			p = players.get(i);
			if (p.getName() == name)
				if (p.checkPassword(password))
					return p;
		}
		
		// There's no player with that name or the password is incorrect.
		return null;
	}

	/**
	 * Creates an array with all the players' statistics and returns it.
	 * @return an array containing player statistics
	 */
	public String[] getPlayers() {
		String[] playerStats = new String[players.size()];
		
		PlayerImpl p;
		for (int i = 0; i < playerStats.length; i++) {
			p = players.get(i);
			playerStats[i] = "";
			playerStats[i] += p.getName() + " " + p.getTotalGames()
					  + " " + p.getWins() + " " + p.getLosses(); 
		}
		
		return playerStats;
	}

}
