package gr.hua.intranet.model;

/**
 * 
 * Charlaftis Basileios it21370, Mitropoulos Ioannis it21338, Petsopoulos Tilemachos it21374
 */

import java.util.ArrayList;

public interface AdminDAO {
	
	/**
	 * 
	 * Admin login.
	 */ 	
	public Admin login(String Admin_Name,String Admin_Password);
	
	/**
	 * 
	 * Create new User.
	 */	
	public void CreateUser(User user);

	/**
	 * 
	 * Read a specific User.
	 */	
	public User getUser(String username);

	/**
	 * 
	 * Update User.
	 */	
	public void updateUser(User user);

	/**
	 * 
	 * Delete a User.
	 */	
	public void deleteUser(String user);
	
	/**
	 * 
	 * Get All Users.
	 */	
	public ArrayList<User> getAll();
	
}