package gr.hua.internet.model;


public class Representative extends NLReciver{

	private int Username;
	private int AAM;
	private String Sex;
	private String sessionID;
	private boolean representative;

	public String getFname() {
		return super.getFname();
	}
	public void setFname(String fname) {
		super.setFName(fname);
	}
	public String getLname() {
		return super.getLname();
	}
	public void setLname(String lname) {
		super.setLname(lname);
	}
	public String getMail() {
		return super.getMail();
	}
	public void setMail(String mail) {
		super.setMail(mail);
	}
	
	public int getUsername() {
		return Username;
	}
	public void setUsername(int username) {
		Username = username;
	}
	
	public int getAAM() {
		return AAM;
	}
	public void setAAM(int aam) {
		AAM = aam;
	}
	public String getSex() {
		return Sex;
	}
	public void setSex(String sex) {
		Sex = sex;
	}
	public String getSessionID() {
		return sessionID;
	}
	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}
	public boolean isRepresentative() {
		return representative;
	}
	public void setRepresentative(boolean representative) {
		this.representative = representative;
	}

}
