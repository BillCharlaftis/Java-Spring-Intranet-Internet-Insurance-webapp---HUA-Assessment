package gr.hua.intranet.model;

import java.sql.Blob;
import java.sql.SQLException;

public class PDFfile {
	
	private int Id;
	private byte[] pdf;
	private int requestID;
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public byte[] getPdf() {
		return pdf;
	}
	public void setPdf(Blob blob) {
		
		try {
			int blobLength = (int) blob.length();
			this.pdf = blob.getBytes(1, blobLength);
			blob.free();
		} catch (SQLException e) {
			e.printStackTrace();
		}  
	}
	public int getRequestID() {
		return requestID;
	}
	public void setRequestID(int requestID) {
		this.requestID = requestID;
	}
}