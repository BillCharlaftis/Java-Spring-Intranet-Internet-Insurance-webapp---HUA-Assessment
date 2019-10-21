package gr.hua.intranet.model;

/**
 * 
 * Charlaftis Basileios it21370, Mitropoulos Ioannis it21338, Petsopoulos Tilemachos it21374
 */

public interface ConnectedCustomersDAO {

	/**
	 * 
	 * @param aam
	 * @param sessionID
	 * add into ConnectedCustomers this customer
	 */
	public void connected(int aam, String sessionID);
	
	/*
	 * 
	 * Logout this customer
	 */
	public void delete(String sessionID);
	
	
	/**
	 * 
	 * Check Connected customer.
	 */	
	public int checkConnCustomer(String sessionID);	
}
