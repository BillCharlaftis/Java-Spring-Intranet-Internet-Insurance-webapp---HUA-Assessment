package gr.hua.intranet.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class NLReciverDAOImp implements NLReciverDAO {

	private DataSource dataSource;


	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public boolean CheckAndADD(NLReciver reciver) {
		Boolean isOk = true;

		if (reciver.getMail().contains("@")
				&& (reciver.getMail().contains(".com") || reciver.getMail().contains(".gr"))) {
			Add(reciver);
		} else {
			isOk = false;
		}
		return isOk;
	}

	@Override
	public void Add(NLReciver reciver) {
		String query = "INSERT INTO NewsletterRecivers(Fname, Lname, Mail) VALUES (?,?,?)";

		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;

		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, reciver.getFname());
			ps.setString(2, reciver.getLname());
			ps.setString(3, reciver.getMail());
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
}
