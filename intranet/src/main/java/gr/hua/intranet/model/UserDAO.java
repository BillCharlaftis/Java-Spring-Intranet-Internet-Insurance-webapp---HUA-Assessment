package gr.hua.intranet.model;

/**
 * 
 * Charlaftis Basileios it21370, Mitropoulos Ioannis it21338, Petsopoulos Tilemachos it21374
 */

public interface UserDAO {

	/**
	 * 
	 * Authenticate User
	 */	
	public User login(String username,String password);
}