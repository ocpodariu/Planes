package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import common.Player;
import common.Server;


public class ServerImpl extends UnicastRemoteObject implements Server {
	
	private List<PlayerImpl> players;
	
	public ServerImpl() throws RemoteException {
		players = new ArrayList<PlayerImpl>();
	}

	public boolean registerPlayer(String name, String password) throws RemoteException {
		// If there's already a player registered with this name, exit.
		for (int i = 0; i < players.size(); i++)
			if (players.get(i).getName().equals(name))
				return false;
		
		// Create a new player and add it to the list of registered players.
		players.add(new PlayerImpl(name, password));
		
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

}
