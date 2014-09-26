package common;


public interface Server {
	
	boolean registerPlayer(String name, String password);
	Player login(String name, String password);
	String[] getPlayers();

}
