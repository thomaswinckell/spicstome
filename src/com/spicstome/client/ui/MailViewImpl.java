package com.spicstome.client.ui;

import com.spicstome.client.shared.Album;
import com.spicstome.client.ui.widget.AlbumBookPanel;
import com.spicstome.client.ui.widget.Book;
import com.spicstome.client.ui.widget.Crumb;
import com.spicstome.client.ui.widget.DropZone;


public class MailViewImpl extends UserViewLayout  implements MailView{
	
	
	AlbumBookPanel album;
	DropZone dropZone;
	
	public MailViewImpl()
	{
		
		super();
		
		
		
		addCrumb(new Crumb("Mail"){});
		
	
		
		
		
        album = new AlbumBookPanel(new Book(100));
        album.setAlbum(new Album());
        
    	dropZone = new DropZone(album.book.imageSize);
        
        mainPanel.addMember(album);
        mainPanel.addMember(dropZone);
	}
}
