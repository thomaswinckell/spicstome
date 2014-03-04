package com.spicstome.client.ui;

import java.util.ArrayList;
import java.util.List;

import com.smartgwt.client.widgets.layout.VLayout;
import com.spicstome.client.dto.LogDTO;
import com.spicstome.client.dto.StudentDTO;
import com.spicstome.client.dto.UserDTO;
import com.spicstome.client.dto.WordDTO;
import com.spicstome.client.place.MailPlace;
import com.spicstome.client.ui.panel.MailPanel;
import com.spicstome.client.ui.panel.SelectRecipientPanel;
import com.spicstome.client.ui.widget.Crumb;


public class NewMailViewImpl extends UserViewImpl  implements NewMailView{

	MailPanel mailPanel;
	SelectRecipientPanel selectionRecipientPanel;
	public boolean selectedRecipient = false;
	StudentDTO defaultStudent;

	
	public NewMailViewImpl()
	{
		super();

		addCrumb(new Crumb("Mail"){
			@Override
			public void onClickCrumb() {			
				goTo(new MailPlace());
			}
		});
		
		addCrumb(new Crumb("Nouveau mail"){});

		
       
    	selectionRecipientPanel = new SelectRecipientPanel(){

			@Override
			public void onSelectedRecipient(UserDTO user,String mail) {
				
				SelectRecipient(user,mail,"");

			}	
    	};
    	
    	mailPanel = new MailPanel() {
			
			@Override
			public void onSendMail(String recipientMail,ArrayList<WordDTO> words,ArrayList<String> correctedWords,LogDTO log){
				
				((NewMailView.Presenter)(listener)).sendMail(recipientMail, words,correctedWords);
				((NewMailView.Presenter)(listener)).saveLog(log);
				
				goTo(new MailPlace());
			}

			@Override
			public void onExpand(boolean b) {

				setMenuTopVisible(!b);
				
			}
		};
		
		mainPanel.addMember(selectionRecipientPanel);
		mainPanel.addMember(mailPanel);
    	
	}
	
	

	@Override
	public void setStudent(StudentDTO owner) {
		
		defaultStudent=owner;
		
		mailPanel.setStudent(owner);
		
		if(!isAdmin())
			mailPanel.album.setMyAlbumTitle();
		
		
	}



	@Override
	public void setRecipients(List<UserDTO> recipients) {	
		selectionRecipientPanel.setRecipients(recipients);
	}
	
	@Override
	public void init(userType type,String recipientMail,UserDTO recipient,String htmlReceivedMail) {
		
		super.init(type);
		mailPanel.init();
		selectionRecipientPanel.init();
		
		if(recipientMail.equals("?"))
		{
			SetIsSelectedRecipient(false);
		}
		else
		{
			SelectRecipient(recipient, recipientMail, htmlReceivedMail);
		}
		
		selectionRecipientPanel.setTextSearchVisible(isAdmin());
	}
	
	public void SetIsSelectedRecipient(boolean b)
	{
		selectedRecipient=b;
		
		mailPanel.setVisible(selectedRecipient);
		selectionRecipientPanel.setVisible(!selectedRecipient);
		
	}
	
	public void SelectRecipient(UserDTO user,String mail,String htmlReceivedMail)
	{
		if(user!=null)
		{
			mailPanel.setImageRecipient(user.getImage().getFilename());
			mailPanel.setRecipientLabel(user.getFirstName());
			
			if(isAdmin())
			{

				if(user instanceof StudentDTO)
				{
					StudentDTO student = (StudentDTO) user;
					mailPanel.setStudent(student);
				}
				else
				{
					mailPanel.setStudent(defaultStudent);	
				}
			}
		}
		else
		{
			mailPanel.setImageRecipient("default_user.png");
			mailPanel.setRecipientLabel(mail);
			
			if(isAdmin())
			{
				mailPanel.setStudent(defaultStudent);	
			}
		}
		
		SetIsSelectedRecipient(true);
		mailPanel.setRecipientMail(mail);
		mailPanel.startLog();		
		mailPanel.setReceivedMail(htmlReceivedMail);
	}


	
	
	
}
