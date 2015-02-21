package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import common.Constants;
import common.Game;
import common.GameServer;


public class GameServerImpl extends UnicastRemoteObject implements GameServer {
	
	/** All available game rooms */
	private List<GameRoom> rooms;

	public GameServerImpl() throws RemoteException {
		rooms = new ArrayList<GameRoom>();
	}

	@Override
	public String[] getRooms() throws RemoteException {
		String[] roomDetails = new String[rooms.size()];
		
		for (int i = 0; i < rooms.size(); i++)
			roomDetails[i] = rooms.get(i).getRoomNumber() + " " + rooms.get(i).getCreator();
		
		return roomDetails;
	}

	@Override
	public int createRoom(String playerName) throws RemoteException {
		rooms.add(new GameRoom(playerName));
		return rooms.get(rooms.size() - 1).getRoomNumber();
	}

	@Override
	public int joinRoom(int roomNumber, String playerName) throws RemoteException {
		GameRoom r = null;
		
		for (int i = 0; i < rooms.size(); i++) {
			r = rooms.get(i);
			if (r.getRoomNumber() == roomNumber)
				if (r.isFull())
					return Constants.GAME_ROOM_FULL;
				else {
					r.addPlayer(playerName);
					return r.getRoomNumber();
				}
		}
		
		return Constants.GAME_ROOM_NOT_FOUND;
	}

	@Override
	public void leaveRoom(int roomNumber, String playerName) throws RemoteException {
		GameRoom r = null;
		
		for (int i = 0; i < rooms.size(); i++) {
			r = rooms.get(i);
			if (r.getRoomNumber() == roomNumber)
				r.removePlayer(playerName);
		}
	}

	@Override
	public int getNumberOfPlayers(int roomNumber) throws RemoteException {
		GameRoom r = null;
		
		for (int i = 0; i < rooms.size(); i++) {
			r = rooms.get(i);
			if (r.getRoomNumber() == roomNumber)
				return r.getCurrNrPlayers();
		}
		
		return Constants.GAME_ROOM_NOT_FOUND;
	}

	@Override
	public String[] getPlayers(int roomNumber) throws RemoteException {
		GameRoom r = null;
		
		for (int i = 0; i < rooms.size(); i++) {
			r = rooms.get(i);
			if (r.getRoomNumber() == roomNumber)
				return r.getPlayers();
		}
		
		return null;
	}

	@Override
	public void setPlayerStatus(int roomNumber, String playerName, boolean status) throws RemoteException {
		GameRoom r = null;
		
		for (int i = 0; i < rooms.size(); i++) {
			r = rooms.get(i);
			if (r.getRoomNumber() == roomNumber)
				r.setPlayerStatus(playerName, status);
		}
	}

	@Override
	public boolean getPlayerStatus(int roomNumber, String playerName) throws RemoteException {
		GameRoom r = null;
		
		for (int i = 0; i < rooms.size(); i++) {
			r = rooms.get(i);
			if (r.getRoomNumber() == roomNumber)
				return r.getPlayerStatus(playerName);
		}
		
		return false;
	}

	@Override
	public Game startGame(int roomNumber) throws RemoteException {
		GameRoom r = null;
		
		for (int i = 0; i < rooms.size(); i++) {
			r = rooms.get(i);
			if (r.getRoomNumber() == roomNumber)
				return r.startGame();
		}
		
		return null;
	}

}