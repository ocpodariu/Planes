package common;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface Server extends Remote {
	
	boolean registerPlayer(String name, String password) throws RemoteException;
	Player login(String name, String password) throws RemoteException;
	String[] getPlayers() throws RemoteException;

}
