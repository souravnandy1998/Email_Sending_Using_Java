package com.email;

import java.io.File;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Preparig to send message...." );
        String message ="Hello, Dear,this is message for security check..";
        String subject="CodesArea:Confirmation";
        String to="mohuanandy1998@gmail.com";
        String from="swarupnandy19@gmail.com";
        //calling this method for sending plain text
        //sendEmail(message,subject,to,from);
        //calling this method for sending attachment
        sendAttach(message,subject,to,from);
    }
//responsible to send the message with attachment
	private static void sendAttach(String message, String subject, String to, String from) {
		//Variable for gmail
				String host="smtp.gmail.com";
				//get the system properties
				Properties properties=System.getProperties();
				System.out.println("PROPERTIES "+properties);
				//setting important information to properties object
				//host set
				properties.put("mail.smtp.host", host);
				properties.put("mail.smtp.port","465");
				properties.put("mail.smtp.ssl.enable","true");
				properties.put("mail.smtp.auth","true");
				//step 1: to get session object..
				Session session=Session.getInstance(properties,new Authenticator() {

					@Override
					protected PasswordAuthentication getPasswordAuthentication() {
						
						return new PasswordAuthentication("swarupnandy19@gmail.com","9330130926");
					}
					
				});
				session.setDebug(true);
				//Step 2:compose the message [text,multimedia]
				MimeMessage m=new MimeMessage(session);
				try {
					//from email
					m.setFrom(from);
					//adding recipient to message
					m.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
					//adding subject to message
					m.setSubject(subject);
					//attachment send
					//file path
					String path="C:\\Users\\SOURAV\\Music\\Soumi Mukhejee Income Tax Return File.pdf";
					MimeMultipart mimeMultipart=new MimeMultipart();
					//text
					//file
					MimeBodyPart textMime=new MimeBodyPart();
					MimeBodyPart fileMime=new MimeBodyPart();
					try {
						textMime.setText(message);
						File file=new File(path);
						fileMime.attachFile(file);
						mimeMultipart.addBodyPart(textMime);
						mimeMultipart.addBodyPart(fileMime);
					}
					catch(Exception e){
						e.printStackTrace();
					}
					m.setContent(mimeMultipart);
					//send
					//Step 3:send the message
					Transport.send(m);
					System.out.println("Sent success..................");
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}

	private static void sendEmail(String message, String subject, String to, String from) {
		//Variable for gmail
		String host="smtp.gmail.com";
		//get the system properties
		Properties properties=System.getProperties();
		System.out.println("PROPERTIES "+properties);
		//setting important information to properties object
		//host set
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port","465");
		properties.put("mail.smtp.ssl.enable","true");
		properties.put("mail.smtp.auth","true");
		//step 1: to get session object..
		Session session=Session.getInstance(properties,new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				
				return new PasswordAuthentication("swarupnandy19@gmail.com","9330130926");
			}
			
		});
		session.setDebug(true);
		//Step 2:compose the message [text,multimedia]
		MimeMessage m=new MimeMessage(session);
		try {
			//from email
			m.setFrom(from);
			//adding recipient to message
			m.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
			//adding subject to message
			m.setSubject(subject);
			//adding text to message
			m.setText(message);
			//send
			//Step 3:send the message
			Transport.send(m);
			System.out.println("Sent success..................");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
