package com.spicstome.client.ui;

import java.util.List;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.spicstome.client.dto.StudentDTO;
import com.spicstome.client.dto.UserDTO;
import com.spicstome.client.place.MailPlace;
import com.spicstome.client.ui.panel.AlbumBookPanel;
import com.spicstome.client.ui.panel.Book;
import com.spicstome.client.ui.panel.MailMenuRightPanel;
import com.spicstome.client.ui.panel.RecipientPanel;
import com.spicstome.client.ui.widget.Crumb;
import com.spicstome.client.ui.widget.MailDropZone;



public class NewMailViewImpl extends UserViewImpl  implements NewMailView{
	
	
	AlbumBookPanel album;
	MailDropZone dropZone;
	MailMenuRightPanel menuRight;
	RecipientPanel recipient;
	HLayout horizontalLayout = new HLayout();
	VLayout mailLayout = new VLayout();
	boolean isStudent;
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

        album = new AlbumBookPanel(new Book(100));  
    	dropZone = new MailDropZone(album.book.imageSize);
    	menuRight = new MailMenuRightPanel(album);
    	recipient = new RecipientPanel(){

			@Override
			public void onChangeRecipient(UserDTO user) {
				
				if(!isStudent)
				{
					if(user instanceof StudentDTO)
					{
						StudentDTO student = (StudentDTO) user;
						album.setStudent(student);
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



	@Override
	public void init() {
		
		dropZone.init();
		recipient.init();
		
	}
}
