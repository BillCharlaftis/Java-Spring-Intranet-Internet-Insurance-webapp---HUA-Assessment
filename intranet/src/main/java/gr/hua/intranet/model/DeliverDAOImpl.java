package gr.hua.intranet.model;

/**
 * 
 * Charlaftis Basileios it21370, Mitropoulos Ioannis it21338, Petsopoulos Tilemachos it21374
 */

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

public class DeliverDAOImpl implements DeliverDAO {

	private DataSource dataSource;
	private String FilePath;
	private PDFfile pdfFile = null;

	@Override
	public Deliver completeCertification(Company thisCompany, int duration) {
		String reqQuery = "insert into Requests (aam, brandname, repname, duration) values (?,?,?,?)";
		
		Deliver deliver = null;
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(reqQuery);
			ps.setInt(1, thisCompany.getAam());
			ps.setString(2, thisCompany.getBrandName());
			ps.setString(3, thisCompany.getRepName());
			ps.setInt(4, duration);
			rs = ps.executeUpdate();
			
			if (rs != 0) {
				String idQuery = "select last_insert_id() as last_id from Requests";
				Connection con2 = null;
				PreparedStatement ps2 = null;
				ResultSet rs2 = null;
				
				try {
					con2 = dataSource.getConnection();
					ps2 = con.prepareStatement(idQuery);
					rs2 = ps2.executeQuery();
					if (rs2.next()) {
						deliver = new Deliver();
						deliver.setReqID(rs2.getInt("last_id"));
					}

				} catch (SQLException e2) {
					e2.printStackTrace();
				} finally {
					try {
						rs2.close();
						ps2.close();
						con2.close();
					} catch (SQLException e2) {
						e2.printStackTrace();
					}
				}
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
		return deliver;
	}

	@Override
	public Deliver deliverCertification(Company thisCompany, int requestID) {
		Deliver deliver = new Deliver();
		
		String isDeliveredQuery = "update Requests set IsDelivered = true where RequestID = ? and aam = ? and IsDelivered = false and IsAccepted = true";
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(isDeliveredQuery);
			ps.setInt(1, requestID);
			ps.setInt(2, thisCompany.getAam());
			rs = ps.executeUpdate();
			
			if (rs != 0) {
				deliver.setReqID(requestID);
				deliver.setDelivered(true);
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
		return deliver;
	}

	@Override
	public PDFfile getPdfFile() {
		return pdfFile;
	}

	public void Loadpdf(int requestID) {
		String query = "select PDF from Requests where RequestID = ?";
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, requestID);
			rs = ps.executeQuery();
			rs.first();

			Blob Blb = rs.getBlob("PDF");

			pdfFile = new PDFfile();
			pdfFile.setId(requestID);
			pdfFile.setPdf(Blb);
			pdfFile.setRequestID(requestID);

			Blb.free();

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

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
}