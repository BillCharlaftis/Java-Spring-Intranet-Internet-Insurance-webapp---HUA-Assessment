package gr.hua.intranet.model;

/**
 * 
 * Charlaftis Basileios it21370, Mitropoulos Ioannis it21338, Petsopoulos Tilemachos it21374
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.mail.AuthenticationFailedException;
import javax.sql.DataSource;

import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import gr.hua.intranet.model.Representative;

public class mailDAOImp implements mailDAO {

	private DataSource dataSource;
	@Inject
	private MailSender mailSender;

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	/*
	 * 
	 * Representative registration
	 */

	@Override
	public void SentConfirmation(Representative customer) {

		mail NewMail = new mail();

		NewMail.fastMailSet(customer, "Your registration confirmed", "Now you can log in to our Systems.\n"
				+ "Username: " + customer.getAAM() + "\n Password: " + customer.getUsername(), null);

		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(NewMail.Address);
		email.setSubject(NewMail.Sbj);
		email.setText(NewMail.Msg);

		// sends the e-mail
		mailSender.send(email);
	}

	@Override
	public void SentMail(Representative representative, String Reason, String msg) {

		mail NewMail = new mail();

		NewMail.fastMailSet(representative, "Newsletter: " + Reason, msg, null);

		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(NewMail.Address);
		email.setSubject(NewMail.Sbj);
		email.setText(NewMail.Msg);

		// sends the e-mail
		mailSender.send(email);
	}

	/*
	 * 
	 * Massive newsletter
	 */
	@Override
	public void SentNewsLater(String Reason, String msg) {

		String query = "SELECT * FROM NewsletterRecivers";

		Representative representative = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {

				representative = new Representative();
				representative.setMail(rs.getString("Mail"));
				SentMail(representative, Reason, msg);
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

	/*
	 * 
	 * Receiver Registration
	 */
	@Override
	public void SentRegistrationCompletle(NLReciver customer) {

		mail NewMail = new mail();

		NewMail.fastMailSet(customer, "Newsletter registration is successful.", "Mr/Ms. " + customer.getLname() + " "
				+ customer.getFname() + " from now on, you' ll receive newsletters.", null);
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(NewMail.Address);
		email.setSubject(NewMail.Sbj);
		email.setText(NewMail.Msg);

		// sends the e-mail
		try {
			mailSender.send(email);
		} catch (MailAuthenticationException e) {
			e.printStackTrace();
		}
	}
}