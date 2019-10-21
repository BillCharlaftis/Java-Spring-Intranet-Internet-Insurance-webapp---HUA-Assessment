package gr.hua.intranet.model;

public class Representative {

	private int Username;
	private int AAM;
	private String fname;
	private String Lname;
	private String Mail;
	private String Sex;
	private String sessionID;
	private boolean representative;

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return this.Lname;
	}

	public void setLname(String lname) {
		this.Lname = lname;
	}

	public String getMail() {
		return this.Mail;
	}

	public void setMail(String mail) {
		this.Mail = mail;
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
