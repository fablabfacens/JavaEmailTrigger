package com.sendemail;

import java.io.IOException;
import java.util.Properties;
import java.util.Set;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import model.entities.EmailMessage;

public class OutlookMailService implements MailService{

    public void sendMail(Set<EmailMessage> em, final String hostEmail, final String hostPassword) {
    	
    	for(EmailMessage i : em) {
	
	        Properties props = new Properties();
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.host", "smtp-mail.outlook.com");
	        props.put("mail.smtp.port", "587");
	
	        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(hostEmail, hostPassword);
	            }
	        });
	
	        try {
	            Message message = new MimeMessage(session);
	            message.setFrom(new InternetAddress(hostEmail));
	            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(i.getTo()));
	            message.setSubject(i.getSubject());
	
	            // creates a text for email
	            MimeBodyPart content = new MimeBodyPart();
	            content.setText("Dear " + i.getName() + ",\n" + i.getContent());
	
	            // creates an attachment
	            MimeBodyPart attachment = new MimeBodyPart();
	            String attachPath = "C:\\temp\\" + i.getName() + ".pdf"; //Put the attachment path here!
	            attachment.attachFile(attachPath);
	
	            // Creates the multipart for the email content and the attachment
	            Multipart multipart = new MimeMultipart();
	            multipart.addBodyPart(content);
	            multipart.addBodyPart(attachment);
	
	            // Defines the email content as multipart
	            message.setContent(multipart);
	
	            System.out.println("Sending...");
	            Transport.send(message);
	            System.out.println("Email with attachment sent successfully");
	        } 
        
        catch (MessagingException e) {
           	System.out.println(e.getMessage());
        	System.out.println();
        	e.printStackTrace();
        }
        
        catch(IOException e) {
        	System.out.println(e.getMessage());
        	System.out.println();
        	e.printStackTrace();
        }
    }
}
}