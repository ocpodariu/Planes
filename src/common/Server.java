package common;


public interface Server {
	
	boolean registerPlayer(String username, String password);
	Player login(String username, String password);
	String[] getPlayers();

}
