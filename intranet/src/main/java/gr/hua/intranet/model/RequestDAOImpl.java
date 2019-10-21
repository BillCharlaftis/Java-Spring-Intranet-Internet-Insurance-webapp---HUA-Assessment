package gr.hua.intranet.model;

/**
 * 
 * Charlaftis Basileios it21370, Mitropoulos Ioannis it21338, Petsopoulos Tilemachos it21374
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import javax.sql.rowset.serial.SerialBlob;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import gr.hua.intranet.model.Request;

public class RequestDAOImpl implements RequestDAO {

	private DataSource dataSource;
	private ArrayList<PDFfile> pdfs = new ArrayList<PDFfile>();

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<Request> getAll() {
		String query = "select * from Requests";
		
		List<Request> requestList = new ArrayList<Request>();
		Connection con = null;
		PreparedStatement ps = null;		
		ResultSet rs = null;
		
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Request request = new Request();
				request.setAam(rs.getInt("aam"));
				request.setRequestId(rs.getInt("requestid"));
				request.setBrandName(rs.getString("brandname"));
				request.setRepName(rs.getString("repname"));
				request.setDuration(rs.getInt("duration"));
				request.setAccepted(rs.getBoolean("isaccepted"));
				request.setDelivered(rs.getBoolean("isdelivered"));
				request.setReturnValueAcc(request.isAccepted());
				request.setReturnValueDel(request.isDelivered());
				requestList.add(request);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return requestList;
	}

	@Override
	public Request getById(int requestId) {
		String query = "select * from Requests where requestid = ?";
		
		Request request = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, requestId);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				request = new Request();
				request.setAam(rs.getInt("aam"));
				request.setRequestId(rs.getInt("requestid"));
				request.setBrandName(rs.getString("brandname"));
				request.setRepName(rs.getString("repname"));
				request.setDuration(rs.getInt("duration"));
				request.setAccepted(rs.getBoolean("isaccepted"));
				request.setDelivered(rs.getBoolean("isdelivered"));
				request.setReturnValueAcc(request.isAccepted());
				request.setReturnValueDel(request.isDelivered());
				showpdf(rs.getInt("aam"), rs.getInt("requestid"));
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return request;
	}
	
	@Override
	public List<Request> getByAAM(int aam) {
		String query = "select * from Requests where AAM = ?";
		
		List<Request> requestList = new ArrayList<Request>();
		Connection con = null;
		PreparedStatement ps = null;		
		ResultSet rs = null;

		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, aam);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Request request = new Request();
				request.setAam(rs.getInt("aam"));
				request.setRequestId(rs.getInt("requestid"));
				request.setBrandName(rs.getString("brandname"));
				request.setRepName(rs.getString("repname"));
				request.setDuration(rs.getInt("duration"));
				request.setAccepted(rs.getBoolean("isaccepted"));
				request.setDelivered(rs.getBoolean("isdelivered"));
				request.setReturnValueAcc(request.isAccepted());
				request.setReturnValueDel(request.isDelivered());
				requestList.add(request);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return requestList;
	}

	@Override
	public void acceptReq(int requestId) {
		String query = "update Requests set IsAccepted = true where RequestID = ? and IsDelivered = false";
		
		Request request = new Request();
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, requestId);
			rs = ps.executeUpdate();
			
			if (rs != 0) {
				request.setAccepted(true);
				PdfWriter(requestId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void declineReq(int requestId) {
		String query = "update Requests set IsDelivered = true where RequestID = ? and IsAccepted = false";
		
		Request request = new Request();
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, requestId);
			rs = ps.executeUpdate();
			
			if (rs != 0) {
				request.setDelivered(true);
				
			} else {
				request.setDelivered(false);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	@SuppressWarnings("deprecation")
	public void PdfWriter(int requestID) {
		Request companyData = getById(requestID);

		String fileName = System.getProperty("user.home") + "/Desktop/" + companyData.getRequestId() + ".pdf";
		
		PDDocument doc = new PDDocument();
		PDPage page = new PDPage();

		doc.addPage(page);

		PDPageContentStream content;
		try {
			content = new PDPageContentStream(doc, page);

			content.beginText();
			content.setFont(PDType1Font.COURIER_BOLD, 26);
			content.moveTextPositionByAmount(220, 750);
			content.drawString("Certification");
			content.endText();

			content.beginText();
			content.setFont(PDType1Font.COURIER_BOLD, 16);
			content.moveTextPositionByAmount(80, 700);
			content.drawString("AAM: " + companyData.getAam());
			content.endText();

			content.beginText();
			content.setFont(PDType1Font.COURIER_BOLD, 16);
			content.moveTextPositionByAmount(80, 650);
			content.drawString("Name: " + companyData.getBrandName());
			content.endText();

			content.beginText();
			content.moveTextPositionByAmount(80, 600);
			content.drawString("Repositor Name: " + companyData.getRepName());
			content.endText();

			content.beginText();
			content.moveTextPositionByAmount(80, 550);
			content.drawString("Request ID: " + requestID);
			content.endText();

			content.beginText();
			content.moveTextPositionByAmount(80, 500);
			content.drawString("Your organisation is insured for " + companyData.getDuration() + " months");
			content.endText();

			content.close();
			doc.save(fileName);
			doc.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		String query = "Update Requests set pdf = ? where RequestID = ?";
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);

			Blob blob = new SerialBlob(File2ByteArray(fileName));
			ps.setBlob(1, blob);
			ps.setInt(2, requestID);
			rs = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private byte[] File2ByteArray(String FileName) {
		File file = new File(FileName);

		byte[] ReturnValue = new byte[(int) file.length()];

		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			fileInputStream.read(ReturnValue);
			fileInputStream.close();
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found.");
			e.printStackTrace();
		} catch (IOException e1) {
			System.out.println("Error Reading The File.");
			e1.printStackTrace();
		}
		file.delete();
		return ReturnValue;
	}

	public void showpdf(int aam, int requestID) {
		String query = "select * from CompanyFiles where aam = ?";
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, aam);
			rs = ps.executeQuery();

			while (rs.next()) {
				Blob Blb = rs.getBlob("File");

				PDFfile pdfFile = new PDFfile();
				pdfFile.setId(rs.getInt("Random_id"));
				pdfFile.setPdf(Blb);
				pdfFile.setRequestID(requestID);
				pdfs.add(pdfFile);

				Blb.free();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			System.out.println(e1);
		}
		try {
			rs.close();
			ps.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<PDFfile> getAllPDFS() {
		return pdfs;
	}
}
