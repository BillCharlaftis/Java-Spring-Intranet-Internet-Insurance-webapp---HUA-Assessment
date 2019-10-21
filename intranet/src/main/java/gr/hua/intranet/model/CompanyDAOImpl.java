package gr.hua.intranet.model;

/**
 * 
 * Charlaftis Basileios it21370, Mitropoulos Ioannis it21338, Petsopoulos Tilemachos it21374
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

public class CompanyDAOImpl implements CompanyDAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public Company checkCompany(String adt, int aam) {
		String query = "select * from Companies where adt = ? AND aam = ?";
		
		Company company = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, adt);
			ps.setInt(2, aam);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				company = new Company();
				company.setAdt(adt);
				company.setAam(aam);
				company.setBrandName(rs.getString("brandname"));
				company.setAfm(rs.getInt("afm"));
				company.setRepName(rs.getString("RepName"));
				company.setPhoneNumber(rs.getInt("PhoneNumber"));
				company.setAddress(rs.getString("Address"));
				company.setCompany(true);
				
			} else {
				System.out.println("No Company found with aam = " + aam + " and adt = " + adt);
				company = new Company();
				company.setCompany(false);
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
		return company;
	}
}
