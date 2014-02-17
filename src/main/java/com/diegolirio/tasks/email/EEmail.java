package com.diegolirio.tasks.email;

import org.apache.commons.mail.EmailException;

public interface EEmail {

	//http://noobjava.wordpress.com/2010/05/05/enviar-email-com-commons-mail/
	
	public void sendEmail(String subject, String message, String emailTo) throws EmailException;

	
}
