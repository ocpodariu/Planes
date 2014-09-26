package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import common.Constants;
import common.Server;


public class Main {

	public static void main(String[] args) {
		Registry r = null;
		
		// Set up rmi registry
		try { 
			r = LocateRegistry.createRegistry(Constants.RMI_PORT);
		} catch (RemoteException e) {
			// Registry's already been created; get a reference to it
			try {
				r = LocateRegistry.getRegistry(Constants.RMI_PORT);
			} catch (RemoteException f) {
				/* No need to handle this exception because it will never be thrown */
			}
		}
		
		try {
			Server server = new ServerImpl();
			
			// Register the remote object
			try {
				r.rebind(Constants.RMI_NAME, server);
			} catch (Exception e) {
				// Cannot register remote object. Exit
				System.exit(1);
			}
			
		} catch (RemoteException e) {
			// Cannot create remote object. Exit
			System.exit(1);
		}

	}

}
