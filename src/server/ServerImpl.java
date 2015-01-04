package server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import common.Player;
import common.Server;


public class ServerImpl extends UnicastRemoteObject implements Server {
	
	private List<PlayerImpl> players;
	private String filename = "players.dat";
	
	public ServerImpl() throws RemoteException {
		players = new ArrayList<PlayerImpl>();
		loadPlayers();
	}

	public boolean registerPlayer(String name, String password) throws RemoteException {
		// If there's already a player registered with this name, exit.
		for (int i = 0; i < players.size(); i++)
			if (players.get(i).getName().equals(name))
				return false;
		
		// Create a new player and add it to the list of registered players.
		players.add(new PlayerImpl(name, password));
		
		// Save the new player's profile to the text file
		saveNewPlayer(players.size() - 1);
		
		return true;
	}

	public Player login(String name, String password) throws RemoteException {
		// Find the player with this name and check if the password matches.
		PlayerImpl p = null;
		for (int i = 0; i < players.size(); i++) {
			p = players.get(i);
			if (p.getName().equals(name))
				if (p.checkPassword(password))
					return p;
		}
		
		// There's no player with that name or the password is incorrect.
		return null;
	}

	public String[] getPlayers() throws RemoteException {
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
	
	/**
	 * Save only a new player's profile to the text file,
	 * instead of all the profiles.
	 * @param index player's profile position in the array
	 */
	private void saveNewPlayer(int index) {
		File output = new File(filename);
		
		try {
			FileWriter outputWriter = new FileWriter(output, false);
			
			try {
				outputWriter.write(players.get(index).getName() + " ");
				outputWriter.write(players.get(index).getPassword() + " ");
				outputWriter.write(players.get(index).getTotalGames() + " ");
				outputWriter.write(players.get(index).getWins() + " ");
				outputWriter.write(players.get(index).getLosses());
				outputWriter.write("\n");
			} finally {
				outputWriter.close();
			}
		} catch (Exception e) {} 
	}
	
	/**
	 * Save all the player profiles to a text file.
	 */
	private void savePlayers() {
		File output = new File(filename);
		
		try {
			FileWriter outputWriter = new FileWriter(output, false);
			
			try {
				// Write each player profile to the text file,
				// on a separate line.
				for (int i = 0; i < players.size(); i++) {
					outputWriter.write(players.get(i).getName() + " ");
					outputWriter.write(players.get(i).getPassword() + " ");
					outputWriter.write(players.get(i).getTotalGames() + " ");
					outputWriter.write(players.get(i).getWins() + " ");
					outputWriter.write(players.get(i).getLosses());
					outputWriter.write("\n");
				}
			} finally {
				outputWriter.close();
			}
		} catch (Exception e) {} 
	}
	
	/**
	 * Load the previously created player profiles from
	 * the text file.
	 */
	private void loadPlayers() {
		File input = new File(filename);
		
		try {
			FileReader inputReader = new FileReader(input);
			BufferedReader br = new BufferedReader(inputReader);
			
			try {
				String player;
				while ((player = br.readLine()) != null) {
					String[] playerStats = player.split(" ");
					
					// Create a new player profile and set it stats
					players.add(new PlayerImpl(playerStats[0], playerStats[1]));
					players.get(players.size() - 1).setStats(Integer.parseInt(playerStats[2]),
															 Integer.parseInt(playerStats[3]),
															 Integer.parseInt(playerStats[4]));
				}
			} finally {
				br.close();
				inputReader.close();
			}
		} catch (Exception e) {}
	}

}
