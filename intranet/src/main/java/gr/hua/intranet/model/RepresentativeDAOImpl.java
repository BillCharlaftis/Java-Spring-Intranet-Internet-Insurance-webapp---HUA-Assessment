package gr.hua.intranet.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class RepresentativeDAOImpl implements RepresentativeDAO{

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void CreateCustomer(Representative representative, String session) {
		String query = "INSERT INTO representative(FName, LName, AAM, Mail, Sex, Username, sessionID) values (?,?,?,?,?,?,?)";
		
		Connection con = null;
		PreparedStatement ps = null;
		
		int rs = 0;

		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, representative.getFname());
			ps.setString(2, representative.getLname());
			ps.setInt(3, representative.getAAM());
			ps.setString(4, representative.getMail());
			ps.setString(5, representative.getSex());
			ps.setInt(6, representative.getUsername());
			ps.setString(7, session);
			
			int out = ps.executeUpdate();
			
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
	public List<Representative> getAllCustomers() {
		String query = "select * from representative";
		
		List<Representative> customerList = new ArrayList<Representative>();
		Connection con = null;
		PreparedStatement ps = null;		
		ResultSet rs = null;
		
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()){
				Representative representative = new Representative();
				representative.setAAM(rs.getInt("aam"));
				representative.setFname(rs.getString("Fname"));
				representative.setLname(rs.getString("Lname"));
				representative.setMail(rs.getString("Mail"));
				representative.setSex(rs.getString("Sex"));
				representative.setRepresentative(true);
				
				customerList.add(representative);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		return customerList;
	}
	
	@Override
	public Representative getCustomerByAAM(int aam) {
		String query = "select * from representative where aam = ?";
		
		Representative representative = null;
		Connection con = null;
		PreparedStatement ps = null;		
		ResultSet rs = null;
		
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, aam);
			rs = ps.executeQuery();
			
			if(rs.next()){
				representative = new Representative();
				representative.setAAM(rs.getInt("aam"));
				representative.setFname(rs.getString("Fname"));
				representative.setLname(rs.getString("Lname"));
				representative.setMail(rs.getString("Mail"));
				representative.setSex(rs.getString("Sex"));
				representative.setUsername(rs.getInt("Username"));
				representative.setRepresentative(true);
				
			}else{
				representative = new Representative();
				representative.setRepresentative(false);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		return representative;
	}
	
	@Override
	public Representative getCustomerBySessionID(String session) {
		String query = "select * from representative where sessionID = ?";
		
		Representative representative = null;
		Connection con = null;
		PreparedStatement ps = null;		
		ResultSet rs = null;
		
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, session);
			rs = ps.executeQuery();
			
			while(rs.next()){
				representative = new Representative();
				representative.setAAM(rs.getInt("aam"));
				representative.setFname(rs.getString("Fname"));
				representative.setLname(rs.getString("Lname"));
				representative.setMail(rs.getString("Mail"));
				representative.setSex(rs.getString("Sex"));
				representative.setUsername(rs.getInt("Username"));
				representative.setRepresentative(true);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		return representative;
	}

	@Override
	public Representative customerLogin(int AAM, int Username) {
		String query = "select * from representative where AAM = ? AND Username = ?";
		
		Representative representative = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, AAM);
			ps.setInt(2, Username);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				representative = new Representative();
				representative.setAAM(AAM);
				representative.setFname(rs.getString("Fname"));
				representative.setLname(rs.getString("Lname"));
				representative.setMail(rs.getString("mail"));
				representative.setSex(rs.getString("sex"));
				representative.setUsername(Username);
				representative.setRepresentative(true);
				
			} else {
				representative = new Representative();
				representative.setRepresentative(true);
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
		return representative;
	}
}
