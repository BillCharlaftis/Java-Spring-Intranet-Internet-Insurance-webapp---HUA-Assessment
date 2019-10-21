package gr.hua.intranet.model;

/**
 * 
 * Charlaftis Basileios it21370, Mitropoulos Ioannis it21338, Petsopoulos Tilemachos it21374
 */

import javax.validation.constraints.NotNull;

/**
 * 
 * Getters and Setters for Admin item.
 */
public class Admin {
	
	@NotNull
	private String AdminName;
	@NotNull
	private String AdminPassword;
	private Boolean Admin;
	
	public  String getAdminName() {
		return AdminName;
	}
	public void setAdminName(String AdminName) {
		this.AdminName = AdminName;
	}
	public  String getAdminPassword() {
		return AdminPassword;
	}
	public void setAdminPassword(String AdminPassword) {
		this.AdminPassword = AdminPassword;
	}
	public Boolean isAdmin() {
		return Admin;
	}
	public void setAdmin(Boolean admin) {
		Admin = admin;
	}
	@Override
	public String toString() {
		return "Admin [Admin=" + Admin + "]";
	}
}