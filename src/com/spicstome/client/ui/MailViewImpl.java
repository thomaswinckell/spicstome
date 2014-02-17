package com.spicstome.client.ui;


import java.util.List;

import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.spicstome.client.dto.StudentDTO;
import com.spicstome.client.dto.UserDTO;
import com.spicstome.client.ui.panel.AlbumBookPanel;
import com.spicstome.client.ui.panel.Book;
import com.spicstome.client.ui.panel.MailMenuRightPanel;
import com.spicstome.client.ui.panel.RecipientPanel;
import com.spicstome.client.ui.widget.Crumb;
import com.spicstome.client.ui.widget.MailDropZone;



public class MailViewImpl extends UserViewImpl  implements MailView{
	
	
	AlbumBookPanel album;
	MailDropZone dropZone;
	MailMenuRightPanel menuRight;
	RecipientPanel recipient;
	HLayout horizontalLayout = new HLayout();
	VLayout mailLayout = new VLayout();

	
	public MailViewImpl()
	{
		super();

		addCrumb(new Crumb("Mail"){});

        album = new AlbumBookPanel(new Book(100));  
    	dropZone = new MailDropZone(album.book.imageSize);
    	menuRight = new MailMenuRightPanel(album);
    	recipient = new RecipientPanel();
        
    	mailLayout.addMember(recipient);
    	mailLayout.addMember(album);
    	mailLayout.addMember(dropZone);
    	
    	

    	
        horizontalLayout.addMember(mailLayout);
        horizontalLayout.addMember(menuRight);
        mainPanel.addMember(horizontalLayout);
	}

	
	
	@Override
	public void setStudent(StudentDTO owner) {
		
		album.setStudent(owner);
		dropZone.init();
		menuRight.updateFavorite();
    	menuRight.setIconsVisible(false);
		
	}



	@Override
	public void setRecipients(List<UserDTO> recipients) {
		
		recipient.setRecipients(recipients);
		
	}
}
