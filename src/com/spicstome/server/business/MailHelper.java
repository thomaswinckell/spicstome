package com.spicstome.server.business;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.spicstome.client.dto.UserDTO;
import com.spicstome.client.dto.WordDTO;

public abstract class MailHelper {

	private static final String MAIL_IMAGES_ID = "picID";
	private static final String MAIL_SENDER_ID = "senderID";
	private static final String IMAGE_MAIL_ID_SEPARATOR = ", ";
	private static final String IMAGE_MAIL_HEADER_CONTENT = "Content-ID";
	
	private static final String SMTP = "smtp.gmail.com";
	private static final String SUBJECT = "[SpicsToMe] ";

	public static Session sessionFactory(final String login, final String password){
		Properties prop = System.getProperties();
		prop.put("mail.smtp.host", SMTP);
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.ssl.enable", "true");

		Session session = Session.getInstance(prop, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				
				return new PasswordAuthentication(login, password);
			}
		});
		return session;
	}

	public static boolean sendMail(final UserDTO sender, String emailReceiver, ArrayList<WordDTO> words, 
			ArrayList<String> correctedWords) {
		
		try {
			
			Message message = new MimeMessage(sessionFactory(sender.getEmail(), sender.getPassword()));
			message.setFrom(new InternetAddress(sender.getEmail()));
			message.setRecipients(Message.RecipientType.TO, 
					new InternetAddress[]{new InternetAddress(emailReceiver)});
			Multipart mp = new MimeMultipart();
			
			String labelHTML = "";
			String imageHTML = "";
			String subject = SUBJECT;
			String header = "";
			
			for(int i=0; i<words.size(); i++){
				mp.addBodyPart(convertToMime(words.get(i)));
				labelHTML = labelHTML + "<td><h2>" + correctedWords.get(i) + "</h2></td>";
				imageHTML = imageHTML + "<td><img src=\"cid:" +  words.get(i).getImage().getId()  + "\" width=\"200px\" height=\"200px\" /><br/></td>";
				subject = subject + correctedWords.get(i) + " ";
				header = header + words.get(i).getId() + IMAGE_MAIL_ID_SEPARATOR;
			}
			
			labelHTML = "<tr>" + labelHTML + "</tr>";
			imageHTML = "<tr>" + imageHTML + "</tr>";
			
			message.setSubject(subject);
			message.setHeader(MAIL_IMAGES_ID, header);
			message.setHeader(MAIL_SENDER_ID, sender.getId().toString());

			MimeBodyPart htmlPart = new MimeBodyPart();
			htmlPart.setContent("<table width='100%' border='0'>" + labelHTML + imageHTML + "</table>", "text/html");
			mp.addBodyPart(htmlPart);

			message.setContent(mp);
			Transport.send(message);
			return true;
		} catch (Exception e) {
			System.err.print("erreur ! : " + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	/*public static List<Mail> mailsInbox(final AutisteUserLogin user){
		try{
			Session session = sessionFactory(user.getLogin(), user.getPassword());
			Store store = session.getStore("imaps");
			store.connect(ConfigurationManager.getInstance().getMailSMTP(), user.getLogin(), user.getPassword());
			System.out.println(store);
			Folder inbox = store.getFolder("Inbox");
			inbox.open(Folder.READ_ONLY);
			Message messages[] = inbox.getMessages();
			List<Mail> mails = new ArrayList<Mail>();
			for(int i=messages.length-1; i>=0; i--){ // LIFO
				if(messages[i].getHeader(MAIL_IMAGES_ID) != null && messages[i].getHeader(MAIL_SENDER_ID)!=null){
					Mail mail = new Mail();
					Long sender = Long.parseLong(messages[i].getHeader(MAIL_SENDER_ID)[0]);
					mail.setSender(Manager.getInstance().autisteUser(sender));
					mail.setContent(Manager.getInstance().objects(messages[i].getHeader(MAIL_IMAGES_ID)[0].split(IMAGE_MAIL_ID_SEPARATOR)));
					mails.add(mail);
				}
			}
			return mails;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}*/

	public static MimeBodyPart convertToMime(WordDTO word){
		MimeBodyPart imagePart = new MimeBodyPart();
		String src = "./images/upload/" + word.getImage().getFilename();
		System.out.println(src);
		DataSource fds = new FileDataSource(new File(src));
		try {
			imagePart.setDataHandler(new DataHandler(fds));
			imagePart.setHeader(IMAGE_MAIL_HEADER_CONTENT, "<"+ word.getImage().getId() + ">");
			imagePart.setFileName(word.getImage().getFilename());
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		return imagePart;
	}
}
