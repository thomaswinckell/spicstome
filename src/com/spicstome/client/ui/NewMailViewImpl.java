package com.spicstome.client.ui;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.spicstome.client.dto.LogDTO;
import com.spicstome.client.dto.StudentDTO;
import com.spicstome.client.dto.UserDTO;
import com.spicstome.client.dto.WordDTO;
import com.spicstome.client.place.MailPlace;
import com.spicstome.client.ui.panel.AlbumBookPanel;
import com.spicstome.client.ui.panel.Book;
import com.spicstome.client.ui.panel.MailMenuRightPanel;
import com.spicstome.client.ui.panel.RecipientPanel;
import com.spicstome.client.ui.panel.SendingPanel;
import com.spicstome.client.ui.widget.Crumb;
import com.spicstome.client.ui.widget.MailDropZone;



public class NewMailViewImpl extends UserViewImpl  implements NewMailView{
	
	
	AlbumBookPanel album;
	MailDropZone dropZone;
	MailMenuRightPanel menuRight;
	RecipientPanel recipient;
	SendingPanel sending;
	HLayout horizontalLayout = new HLayout();
	VLayout mailLayout = new VLayout();
	boolean isStudent;
	StudentDTO defaultStudent;
	long begin;
	
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

        album = new AlbumBookPanel(new Book(100));  
    	dropZone = new MailDropZone(album.book.imageSize);
    	menuRight = new MailMenuRightPanel(album);
    	sending = new SendingPanel(){

			@Override
			public void onSend() {
				
				getLogs();
				
				goTo(new MailPlace());
			}
    		
    	};
    	recipient = new RecipientPanel(){

			@Override
			public void onChangeRecipient(UserDTO user) {
				
				if(!isStudent)
				{
					if(user instanceof StudentDTO)
					{
						StudentDTO student = (StudentDTO) user;
						album.setStudent(student);
						dropZone.init();
					}
					else
					{
						album.setStudent(defaultStudent);
					}
				}
			}	
    	};
        
    	mailLayout.addMember(recipient);
    	mailLayout.addMember(album);
    	mailLayout.addMember(dropZone);
    	mailLayout.addMember(sending);
    	
        horizontalLayout.addMember(mailLayout);
        horizontalLayout.addMember(menuRight);
        mainPanel.addMember(horizontalLayout);
	}

	@Override
	public void setStudent(StudentDTO owner) {
		
		defaultStudent=owner;
		album.setStudent(owner);
		menuRight.updateFavorite();
    	menuRight.setIconsVisible(false);
		
	}



	@Override
	public void setRecipients(List<UserDTO> recipients) {	
		recipient.setRecipients(recipients);
	}

	@Override
	public void setIsStudent(boolean b) {
		
		isStudent=b;
		
	}

	public void getLogs()
	{
		
		
		long now = System.currentTimeMillis()-begin;
		int seconds = (int) (now/1000);
		
		System.out.println("mouvement= "+dropZone.getMovementCount()+" temps="+seconds+" seconds");
		


		LogDTO logDTO = new LogDTO((long)-1, null, recipient.mail.getValue().toString(), new Date(),
				dropZone.message.size(),
				seconds,dropZone.getMovementCount());
		
		((NewMailView.Presenter)(listener)).saveLog(logDTO);
	}

	@Override
	public void init() {
		
		dropZone.init();
		recipient.init();
		
		begin = System.currentTimeMillis();
	}
}
