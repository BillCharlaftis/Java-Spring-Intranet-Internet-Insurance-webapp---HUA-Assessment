package gr.hua.intranet.model;

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
	
	NLReciver Reciver = null;
	Representative customer;
	String Fname = "";
	String Lname = "";
	String Address= "";
	String Sbj = "";
	String Msg ="";
	File atch=null;
	
	
	public void fastMailSet(NLReciver Reciver,String sbj, String msg, File atch) {
		this.Reciver = Reciver;
		Fname = Reciver.getFname();
		Lname = Reciver.getLname();
		Address = Reciver.getMail();
		Sbj = sbj;
		Msg = msg;
		this.atch = atch;
		
		
	}
	
	public void fastMailSet(Representative customer,String sbj, String msg, File atch) {
		this.customer = customer;
		Fname = customer.getFname();
		Lname = customer.getLname();
		Address = customer.getMail();
		Sbj = sbj;
		Msg = msg;
		this.atch = atch;
		
	}
		
	public Representative getCustomer() {
		return customer;
	}

	public NLReciver getReciver() {
		return Reciver;
	}


	public String getFname() {
		return Fname;
	}


	public String getLname() {
		return Lname;
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


