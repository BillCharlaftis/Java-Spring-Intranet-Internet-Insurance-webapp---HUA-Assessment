package gr.hua.intranet.model;

/**
 * 
 * Charlaftis Basileios it21370, Mitropoulos Ioannis it21338, Petsopoulos Tilemachos it21374
 */

public interface ConnectedUsersDAO {

	/**
	 * 
	 * @param role
	 * @param sessionID
	 * add into ConnectedUsers this user
	 */
	public void connected(String UserName, String role, String sessionID);
	
	/*
	 * 
	 * Logout this user
	 */
	public void delete(String sessionID);
	
	
	/**
	 * 
	 * Check Connected User.
	 */	
	public User checkConnUserRole(String sessionID);
	/**
	 * 
	 * Check Connected Administrator.
	 */
	public boolean checkConnUserAdmin(String sessionID);
}
