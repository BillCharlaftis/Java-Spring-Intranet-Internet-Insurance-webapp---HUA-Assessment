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

public class UserDAOImpl implements UserDAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public User login(String username, String userpass) {
		String query = "select * from Users where username = ? AND userpass = ?";
		
		User user = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, userpass);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				user = new User();
				user.setUsername(username);
				user.setUserpass(userpass);
				user.setAddress(rs.getString("address"));
				user.setCanCheck(rs.getBoolean("cancheck"));
				user.setCanEdit(rs.getBoolean("canedit"));
				user.setCanFileCheck(rs.getBoolean("canfilecheck"));
				user.setCanPrint(rs.getBoolean("canprint"));
				user.setCanRecord(rs.getBoolean("canrecord"));
				user.setFname(rs.getString("fname"));
				user.setLname(rs.getString("lname"));
				user.setMail(rs.getString("mail"));
				user.setPhoneNumber(rs.getLong("phonenumber"));
				user.setRole(rs.getString("role"));
				user.setSex(rs.getString("sex"));
				user.setUser(true);
				
			} else {
				user = new User();
				user.setUser(false);
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
		return user;
	}
}