package gr.hua.intranet.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

/**
 * 
 * Charlaftis Basileios it21370, Mitropoulos Ioannis it21338, Petsopoulos
 * Tilemachos it21374
 */

public class ConnectedCustomersDAOImpl implements ConnectedCustomersDAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void connected(int aam, String sessionID) {
		count(sessionID);

		String query = "insert into ConnectedCustomers (sessionID, aam) values (?,?)";

		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;

		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, sessionID);
			ps.setInt(2, aam);
			rs = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	public int checkConnCustomer(String sessionID) {
		String query = "select aam from ConnectedCustomers where sessionID = ?";

		int aam = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, sessionID);
			rs = ps.executeQuery();

			if (rs.next()) {
				aam = rs.getInt("aam");
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
		return aam;
	}
	
	private void count(String sessionID) {
		String query = "select count(*) as count from ConnectedCustomers where sessionID = ?";

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, sessionID);
			rs = ps.executeQuery();

			if (rs.next()) {

				if (rs.getInt("count") > 0) {
					delete(sessionID);
				}
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
	}

	@Override
	public void delete(String sessionID) {
		String query = "delete from ConnectedCustomers where sessionID = ?";
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, sessionID);
			int out = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
}
