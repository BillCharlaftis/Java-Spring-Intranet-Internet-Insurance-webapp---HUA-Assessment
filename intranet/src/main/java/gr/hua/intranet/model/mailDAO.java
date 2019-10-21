package gr.hua.intranet.model;

/**
 * 
 * Charlaftis Basileios it21370, Mitropoulos Ioannis it21338, Petsopoulos Tilemachos it21374
 */

public interface mailDAO {
	
	public void SentConfirmation(Representative customer);
	
	public void SentNewsLater(String Reason, String msg);
	
	public void SentRegistrationCompletle(NLReciver customer);
	
	public void SentMail(Representative representative, String Reason, String msg);
	
}