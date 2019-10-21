package gr.hua.intranet.model;

/**
 * 
 * Charlaftis Basileios it21370, Mitropoulos Ioannis it21338, Petsopoulos Tilemachos it21374
 */

public interface DeliverDAO {
	
	/**
	 * 
	 * Deliver Certification.
	 */
	public Deliver deliverCertification(Company thisCompany, int requestID);
	
	/**
	 * 
	 * Complete Certification.
	 */
	public Deliver completeCertification(Company thisCompany, int duration);
	
	public PDFfile getPdfFile();
	
	public void Loadpdf(int requestID);
}