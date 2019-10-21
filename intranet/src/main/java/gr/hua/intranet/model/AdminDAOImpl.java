package gr.hua.intranet.model;

/**
 * 
 * Charlaftis Basileios it21370, Mitropoulos Ioannis it21338, Petsopoulos Tilemachos it21374
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class AdminDAOImpl implements AdminDAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public Admin login(String Admin_Name, String Admin_Password) {
		String query = "select * from Admin where Admin_Name = ? AND Admin_Password = ?";

		Admin admin = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, Admin_Name);
			ps.setString(2, Admin_Password);
			rs = ps.executeQuery();

			if (rs.next()) {
				admin = new Admin();
				admin.setAdminName(Admin_Name);
				admin.setAdminPassword(Admin_Password);
				admin.setAdmin(true);

			} else {
				admin = new Admin();
				admin.setAdmin(false);
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
		return admin;
	}

	@Override
	public void CreateUser(User user) {
		String query = "INSERT INTO Users(Username, Userpass, FName, LName, PhoneNumber, Address, Mail, Sex, Role, CanCheck, CanPrint, CanRecord, CanEdit, CanFileCheck) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getUserpass());
			ps.setString(3, user.getFname());
			ps.setString(4, user.getLname());
			ps.setLong(5, user.getPhoneNumber());
			ps.setString(6, user.getAddress());
			ps.setString(7, user.getMail());
			ps.setString(8, user.getSex());
			ps.setString(9, user.getRole());
			ps.setBoolean(10, user.isCanCheck());
			ps.setBoolean(11, user.isCanPrint());
			ps.setBoolean(12, user.isCanRecord());
			ps.setBoolean(13, user.isCanEdit());
			ps.setBoolean(14, user.isCanFileCheck());
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
	public User getUser(String username) {
		String query = "SELECT * FROM Users WHERE Username = ?";

		User usr = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, username);
			rs = ps.executeQuery();

			if (rs.next()) {
				usr = new User();
				usr.setUsername(username);
				usr.setUserpass(rs.getString("Userpass"));
				usr.setFname(rs.getString("FName"));
				usr.setLname(rs.getString("LName"));
				usr.setPhoneNumber(rs.getLong("PhoneNumber"));
				usr.setAddress(rs.getString("Address"));
				usr.setMail(rs.getString("Mail"));
				usr.setSex(rs.getString("Sex"));
				usr.setRole(rs.getString("role"));
				usr.setCanCheck(rs.getBoolean("CanCheck"));
				usr.setCanPrint(rs.getBoolean("CanPrint"));
				usr.setCanRecord(rs.getBoolean("CanRecord"));
				usr.setCanEdit(rs.getBoolean("CanEdit"));
				usr.setCanFileCheck(rs.getBoolean("CanFileCheck"));
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
		return usr;
	}

	@Override
	public void updateUser(User user) {
		deleteUser(user.getUsername());
		CreateUser(user);
	}

	@Override
	public void deleteUser(String username) {
		String query = "DELETE FROM Users WHERE Username=?";
		
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, username);
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
	public ArrayList<User> getAll() {
		String query = "SELECT* FROM Users";
		
		ArrayList<User> usrList = new ArrayList<User>();		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				User usr = new User();
				usr.setUsername(rs.getString("Username"));
				usr.setUserpass(rs.getString("Userpass"));
				usr.setFname(rs.getString("FName"));
				usr.setLname(rs.getString("LName"));
				usr.setPhoneNumber(rs.getLong("PhoneNumber"));
				usr.setAddress(rs.getString("Address"));
				usr.setMail(rs.getString("Mail"));
				usr.setSex(rs.getString("Sex"));
				usr.setRole(rs.getString("role"));
				usr.setCanCheck(rs.getBoolean("CanCheck"));
				usr.setCanPrint(rs.getBoolean("CanPrint"));
				usr.setCanRecord(rs.getBoolean("CanRecord"));
				usr.setCanEdit(rs.getBoolean("CanEdit"));
				usr.setCanFileCheck(rs.getBoolean("CanFileCheck"));
				usrList.add(usr);
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
		return usrList;
	}

	
}