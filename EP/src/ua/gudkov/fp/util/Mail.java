package ua.gudkov.fp.util;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import ua.gudkov.fp.constant.Const;

/**
 * Implements mail sender. Contains only one public method to send email.
 * 
 * @author A.Gudkov
 *
 */
public final class Mail {

	/**
	 * Sends letter with given content to given mail.
	 * 
	 * @param mail
	 *            recipient mail
	 * @param subject
	 *            letter subject
	 * @param message
	 *            message
	 */
	public static void sendMail(String mail, String subject, String message)
			throws AddressException, MessagingException, UnsupportedEncodingException {
		Message msg = new MimeMessage(getSession());
		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("aleksej.gudkov@gmail.com"));
		msg.setSubject(subject);
		msg.setText(message);
		msg.setFrom(new InternetAddress(Const.Mail.FROM_ADDRESS, Const.Mail.FROM_NAME));
		Transport.send(msg);
	}

	/**
	 * Returns session.
	 * 
	 * @return session
	 */
	private static Session getSession() {
		Session session = Session.getDefaultInstance(getProperties(), new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(Const.Mail.USERNAME, Const.Mail.PASSWORD);
			}
		});
		return session;
	}

	/**
	 * Returns properties.
	 * 
	 * @return properties.
	 */
	private static Properties getProperties() {
		Properties properties = new Properties();
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.socketFactory.port", "465");
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.port", "465");
		return properties;
	}

}
