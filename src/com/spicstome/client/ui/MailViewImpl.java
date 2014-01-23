package com.spicstome.client.ui;

import com.spicstome.client.dto.StudentDTO;
import com.spicstome.client.ui.panel.AlbumBookPanel;
import com.spicstome.client.ui.panel.Book;
import com.spicstome.client.ui.widget.Crumb;
import com.spicstome.client.ui.widget.DropZone;


public class MailViewImpl extends UserViewImpl  implements MailView{
	
	
	AlbumBookPanel album;
	DropZone dropZone;
	
	public MailViewImpl()
	{
		super();

		addCrumb(new Crumb("Mail"){});

        album = new AlbumBookPanel(new Book(100));
        
    	dropZone = new DropZone(album.book.imageSize);
        
        mainPanel.addMember(album);
        mainPanel.addMember(dropZone);
	}

	@Override
	public void setStudent(StudentDTO owner) {
		
		album.setStudent(owner);
		
	}
}
