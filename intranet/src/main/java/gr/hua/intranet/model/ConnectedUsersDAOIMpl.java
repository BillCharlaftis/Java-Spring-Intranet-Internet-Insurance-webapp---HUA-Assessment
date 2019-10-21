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

public class ConnectedUsersDAOIMpl implements ConnectedUsersDAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/*
	 * 
	 * support / issue / administrator.
	 */
	private String connUsername;
	private User connUser;

	@Override
	public User checkConnUserRole(String sessionID) {
		connUsername = checkConnUser(sessionID);		
		connUser = matchUser(connUsername);
		
		return connUser;
	}

	private String checkConnUser(String sessionID) {
		String query = "select Username from ConnectedUsers where sessionID = ?";
		
		String username = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, sessionID);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				username = rs.getString("Username");
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
		return username;
	}

	private User matchUser(String username){		
		String query = "select role, FName, LName, PhoneNumber, Address, Mail, Sex from Users where username = ?";
				
		User user = new User();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, username);
			rs = ps.executeQuery();

			if (rs.next()) {
				user.setUsername(username);
				user.setFname(rs.getString("FName"));
				user.setLname(rs.getString("LName"));
				user.setPhoneNumber(rs.getLong("PhoneNumber"));
				user.setAddress(rs.getString("Address"));
				user.setMail(rs.getString("Mail"));
				user.setSex(rs.getString("Sex"));
				user.setRole(rs.getString("role"));
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
		return user;
	}
	
	@Override
	public void connected(String UserName, String role, String sessionID) {
		count(sessionID);
		
		String query = "insert into ConnectedUsers (sessionID, role, Username) values (?,?,?)";
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, sessionID);
			ps.setString(2, role);
			ps.setString(3, UserName);
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

	private void count(String sessionID) {
		String query = "select count(*) as count from ConnectedUsers where sessionID = ?";
				
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
		String query = "delete from ConnectedUsers where sessionID = ?";
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

	@Override
	public boolean checkConnUserAdmin(String sessionID) {
		String query = "select role from ConnectedUsers where sessionID = ?";
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, sessionID);
			rs = ps.executeQuery();

			if (rs.next()) {
				if(rs.getString("role").equals("admin")){
					try {
						rs.close();
						ps.close();
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				return true;
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
		return false;
	}
}
