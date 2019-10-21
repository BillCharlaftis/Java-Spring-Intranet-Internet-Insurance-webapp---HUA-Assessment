package gr.hua.internet.model;

import java.io.File;

/**
 * 
 * Charlaftis Basileios it21370, Mitropoulos Ioannis it21338, Petsopoulos Tilemachos it21374
 */


/**
 * 
 * Getters and Setters for Mail item.
 */
public class mail {
	
	Representative customer = null;
	String Fname = "";
	String Lname = "";
	String Sex = "Ms";
	String Address= "";
	String Sbj = "";
	String Msg ="";
	File atch=null;
	
	
	public void fastMailSet(Representative customer,String sbj, String msg, File atch) {
		this.customer = customer;
		Fname = customer.getFname();
		Lname = customer.getLname();
		Address = customer.getMail();
		Sbj = sbj;
		Msg = msg;
		this.atch = atch;
		
		if(customer.getSex().equalsIgnoreCase("M")){
			Sex="Mr";
		}
		
	}
	
	public Representative getCustomer() {
		return customer;
	}


	public String getFname() {
		return Fname;
	}


	public String getLname() {
		return Lname;
	}


	public String getSex() {
		return Sex;
	}


	public String getAddress() {
		return Address;
	}


	public String getSbj() {
		return Sbj;
	}


	public String getMsg() {
		return Msg;
	}

	public File getAtch() {
		return atch;
	}
	
	
}


