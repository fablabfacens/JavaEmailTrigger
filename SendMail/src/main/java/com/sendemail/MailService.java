package com.sendemail;

import java.util.Set;

import model.entities.EmailMessage;

public interface MailService {

	void sendMail(Set<EmailMessage> em, final String hostEmail, final String hostPassword);
	
}
