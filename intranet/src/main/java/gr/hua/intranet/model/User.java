package gr.hua.intranet.model;

/**
 * 
 * Charlaftis Basileios it21370, Mitropoulos Ioannis it21338, Petsopoulos Tilemachos it21374
 */

import javax.validation.constraints.Size;

/**
 * 
 * Getters and Setters for User item.
 */
public class User {

	@Size(min = 4, max = 30)
	private String username;

	@Size(min = 6, max = 30)
	private String userpass;
	
	private String fname;
	private String lname;
	private long phoneNumber;
	private String address;
	private String mail;
	@Size(min = 1, max = 1)
	private String sex;
	private String role;
	private boolean canCheck;
	private boolean canPrint;
	private boolean canRecord;
	private boolean canEdit;
	private boolean canFileCheck;
	private boolean user;

	public boolean isUser() {
		return user;
	}

	public void setUser(boolean user) {
		this.user = user;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isCanCheck() {
		return canCheck;
	}

	public void setCanCheck(boolean canCheck) {
		this.canCheck = canCheck;
	}

	public boolean isCanPrint() {
		return canPrint;
	}

	public void setCanPrint(boolean canPrint) {
		this.canPrint = canPrint;
	}

	public boolean isCanRecord() {
		return canRecord;
	}

	public void setCanRecord(boolean canRecord) {
		this.canRecord = canRecord;
	}

	public boolean isCanEdit() {
		return canEdit;
	}

	public void setCanEdit(boolean canEdit) {
		this.canEdit = canEdit;
	}

	public boolean isCanFileCheck() {
		return canFileCheck;
	}

	public void setCanFileCheck(boolean canFileCheck) {
		this.canFileCheck = canFileCheck;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpass() {
		return userpass;
	}

	public void setUserpass(String userpass) {
		this.userpass = userpass;
	}

	@Override
	public String toString() {
		return role;
	}
}