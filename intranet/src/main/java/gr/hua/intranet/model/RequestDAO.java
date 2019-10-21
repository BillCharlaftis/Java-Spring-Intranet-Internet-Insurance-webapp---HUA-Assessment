package gr.hua.intranet.model;

import java.util.ArrayList;
import java.util.List;

public interface RequestDAO {

	/**
	 * 
	 * Get All Requests.
	 */
	public List<Request> getAll();
	
	/**
	 * 
	 * getByid.
	 */
	public Request getById(int requestId);
	
	/**
	 * 
	 * Get All Requests with this AAM.
	 */
	public List<Request> getByAAM(int aam);
	
	/**
	 * 
	 * Get All pdfs.
	 */
	public ArrayList<PDFfile> getAllPDFS();
	
	/**
	 * 
	 * Accept Request with ID.
	 */
	public void acceptReq(int requestId);
	
	/**
	 * 
	 * Decline Request with ID.
	 */
	public void declineReq(int requestId);	
	
	/**
	 * 
	 * Upload pdf to databse.
	 * @param requestID
	 */
	public void PdfWriter(int requestID);
}
