package gr.hua.intranet.model;

/**
 * 
 * Charlaftis Basileios it21370, Mitropoulos Ioannis it21338, Petsopoulos Tilemachos it21374
 */

import java.sql.Blob;
import javax.validation.constraints.NotNull;

/**
 * 
 * Getters and Setters for Deliver item.
 */
public class Deliver {
	private int requestID;
	private String aam;
	private String brandName;
	private String repName;
	@NotNull
	private int duration;
	private boolean isAccepted;
	private boolean isDelivered;
	private Blob pdf;
	
	
	public int getRequestID() {
		return requestID;
	}
	public void setRequestID(int requestID) {
		this.requestID = requestID;
	}
	public int getReqID(){
		return requestID;
	}
	public void setReqID(int requestID){
		this.requestID = requestID;
	}
	
	public String getAam() {
		return aam;
	}
	public void setAam(String aam) {
		this.aam = aam;
	}
	
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	
	public String getRepName() {
		return repName;
	}
	public void setRepName(String repName) {
		this.repName = repName;
	}
	
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public boolean isAccepted() {
		return isAccepted;
	}
	public void setAccepted(boolean isAccepted) {
		this.isAccepted = isAccepted;
	}
	
	public boolean isDelivered() {
		return isDelivered;
	}
	public void setDelivered(boolean isDelivered) {
		this.isDelivered = isDelivered;
	}
	
	public Blob getPdf() {
		return pdf;
	}
	public void setPdf(Blob pdf) {
		this.pdf = pdf;
	}

	
	@Override
	public String toString() {
		return "Deliver [requestID=" + requestID + "]";
	}
}