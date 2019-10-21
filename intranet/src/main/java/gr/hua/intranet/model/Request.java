package gr.hua.intranet.model;

/**
 * 
 * Charlaftis Basileios it21370, Mitropoulos Ioannis it21338, Petsopoulos Tilemachos it21374
 */

import java.io.File;

import javax.validation.constraints.NotNull;

/**
 * 
 * Getters and Setters for Request item.
 */
public class Request {
	
	private int requestId;
	private int aam;
	private String brandName;
	private String repName;
	@NotNull
	private int duration;
	private boolean isAccepted;
	private boolean isDelivered;
	private String returnValueAcc;
	private String returnValueDel;
	private File pdf;
		
	public int getRequestId() {
		return requestId;
	}
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
	public int getAam() {
		return aam;
	}
	public void setAam(int aam) {
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
	
	public String getReturnValueAcc() {
		return returnValueAcc;
	}
	public void setReturnValueAcc(Boolean isAccepted) {
		if(isAccepted){
			this.returnValueAcc = "Yes";
		}else{
			this.returnValueAcc = "No";
		}
	}
	public boolean isDelivered() {
		return isDelivered;
	}
	public void setDelivered(boolean isDelivered) {
		this.isDelivered = isDelivered;
	}
	public File getPdf() {
		return pdf;
	}
	public void setPdf(File pdf) {
		this.pdf = pdf;
	}
	
	public String getReturnValueDel() {
		return returnValueDel;
	}
	public void setReturnValueDel(boolean isDelivered) {
		if(isDelivered){
			this.returnValueDel= "Yes";
		}else{
			this.returnValueDel = "No";
		}
	}
	@Override
	public String toString() {
		return "Request [isAccepted()=" + isAccepted() + ", isDelivered()=" + isDelivered() + "]";
	}
}
