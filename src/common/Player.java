package common;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface Player extends Remote {
	
	String getName() throws RemoteException;
	int getTotalGames() throws RemoteException;
	int getWins() throws RemoteException;
	int getLosses() throws RemoteException;
	
}
