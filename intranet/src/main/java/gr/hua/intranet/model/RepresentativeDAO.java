package gr.hua.intranet.model;

import java.util.List;

import javax.servlet.http.HttpSession;

public interface RepresentativeDAO {

	/**
	 * 
	 * Create new Customer.
	 */	
	public void CreateCustomer(Representative representative, String session);
	
	/**
	 * 
	 * Get All Customers.
	 */	
	public List<Representative> getAllCustomers();
	
	/**
	 * 
	 * Get Customer with this AAM.
	 */	
	public Representative getCustomerByAAM(int aam);
	
	/**
	 * 
	 * Get Customer who added from browser with this session id.
	 */	
	public Representative getCustomerBySessionID(String session);
	
	/**
	 * 
	 * Authenticate Customer.
	 */	
	public Representative customerLogin(int AAM, int Username);
	
}
