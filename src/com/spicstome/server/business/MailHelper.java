package com.spicstome.server.business;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
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
import javax.mail.internet.MimePart;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import com.spicstome.client.dto.MailDTO;
import com.spicstome.client.dto.UserDTO;
import com.spicstome.client.dto.WordDTO;
import com.sun.mail.util.BASE64DecoderStream;

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

	public static ArrayList<MailDTO> mailsInbox(final UserDTO user){
		try{
			Session session = sessionFactory(user.getLogin(), user.getPassword());
			Store store = session.getStore("imaps");
			store.connect(SMTP, user.getEmail(), user.getPassword());
			System.out.println(store);
			Folder inbox = store.getFolder("Inbox");
			inbox.open(Folder.READ_ONLY);
			Message messages[] = inbox.getMessages();
			ArrayList<MailDTO> mails = new ArrayList<MailDTO>();
			for(int i=messages.length-1; i>=0; i--){ // LIFO
				
				if (messages[i].getSubject() != null && !messages[i].getSubject().isEmpty() && 
						(messages[i].getSubject().length() >= SUBJECT.length()) && 
						messages[i].getSubject().substring(0, SUBJECT.length()).equals(SUBJECT)) {
					
					if(messages[i].getHeader(MAIL_IMAGES_ID) != null && messages[i].getHeader(MAIL_SENDER_ID)!=null){
						final MailDTO mail = new MailDTO();
						Long idSender = Long.parseLong(messages[i].getHeader(MAIL_SENDER_ID)[0]);
						
						System.out.println("Sender ID : "+idSender);
						
						mail.setSender(new UserDTO(idSender));						
						mail.setReceivedDate(messages[i].getReceivedDate());
						
						Multipart content = (Multipart) messages[i].getContent();
						
						String HTML = new String((String) content.getBodyPart(content.getCount()-1).getContent());
						
						for (int j=0; j<content.getCount()-1; j++) {
							
							MimeBodyPart img = (MimeBodyPart) content.getBodyPart(j);
							
						    byte[] byteArray = IOUtils.toByteArray((InputStream) img.getContent());
						    byte[] encodeBase64 = Base64.encodeBase64(byteArray);
						    
						    String imgSrc = new String(encodeBase64, "UTF-8");
						    
						    String imgType = "data:image/" + FilenameUtils.getExtension(img.getFileName()) + ";base64,";
						    
						    HTML = HTML.replaceFirst("cid:[0-9]+", imgType+imgSrc);
						}
						
						mail.setMessageHTML(HTML);
						mails.add(mail);
					}
				}
				
				System.out.println("Message "+i+"/"+(messages.length-1));
			}
			return mails;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

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
