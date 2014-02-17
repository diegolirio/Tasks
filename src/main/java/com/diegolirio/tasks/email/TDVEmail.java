package com.diegolirio.tasks.email;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class TDVEmail implements EEmail {
	
	private final static String HOST_NAME =  "smtp.gmail.com";
	private final static String EMAIL_FROM =  "tdv.tisp@gmail.com";
	private final static String PASSWORD_AUTHENTICATOR =  "rdmgma@2967";
	private final static int SMTP_PORT =  465;
	
	private Email email;
	
	public TDVEmail() {
		this.email = new SimpleEmail();
		this.email.setHostName(HOST_NAME);
		this.email.setSmtpPort(SMTP_PORT);
		this.email.setAuthenticator(new DefaultAuthenticator(EMAIL_FROM, PASSWORD_AUTHENTICATOR));
		this.email.setSSLOnConnect(true);
	}
	
	public void sendEmail(String subject, String message, String emailTo) throws EmailException {
		this.email.setFrom(EMAIL_FROM);
		this.email.setSubject(subject);
		this.email.setMsg(message);
		this.email.addTo(emailTo);
		this.email.send();
		System.out.println("Email enviado com sucesso!");
	}
	
//	public static void main(String[] args) {
//		TDVEmail email = new TDVEmail();		
//		try {
//			email.sendEmail("Hello Commons Email", "Testando Commons Email", "diegolirio.dl@gmail.com");
//		} catch (EmailException e) {
//			e.printStackTrace();
//		}
//	}
	

	

}
