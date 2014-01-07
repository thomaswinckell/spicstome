package com.spicstome.client.ui.panel;

import java.util.ArrayList;

import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.tree.events.NodeClickEvent;
import com.spicstome.client.dto.AlbumDTO;
import com.spicstome.client.ui.widget.ImageRecord;

public class AlbumBookPanel extends AlbumPanel{

	
	public Book book;
	public Label titre = new Label();
	
	public AlbumBookPanel(Book book) {
		super();
		
		
	    	
	    this.book = book;
	   
	    
	    horizontalLayout.addMember(book);
	    verticalLayout.setHeight(book.heightPage+40);
	    
	    titre.setContents("Album de Robert"); 
	    titre.setHeight(30);
	    titleLayout.addMember(titre);
	    
	}
	
	@Override
	public void setAlbum(AlbumDTO album)
	{
		super.setAlbum(album);
		
		book.setList(new ArrayList<ImageRecord>());
	}
	
	@Override
	public boolean onFolderClick(NodeClickEvent event)
	{
		ArrayList<ImageRecord> articles = new ArrayList<ImageRecord>();
		
		if(super.onFolderClick(event))
		{
			switch(folderTree.selectFolderId)
			{
			case 3:
				articles.add(new ImageRecord(0,"Je","albumlogo.png"));
				articles.add(new ImageRecord(0,"Tu","albumlogo.png"));
				articles.add(new ImageRecord(0,"Il","albumlogo.png"));
				articles.add(new ImageRecord(0,"Nous","albumlogo.png"));
				articles.add(new ImageRecord(0,"Vous","albumlogo.png"));
				articles.add(new ImageRecord(0,"Ils","albumlogo.png"));
				articles.add(new ImageRecord(0,"Moi","albumlogo.png"));
				articles.add(new ImageRecord(0,"Toi","albumlogo.png"));
				articles.add(new ImageRecord(0,"Autre","albumlogo.png"));
			
				break;
			case 4:
				articles.add(new ImageRecord(0,"Je","albumlogo.png"));
				articles.add(new ImageRecord(0,"Tu","albumlogo.png"));
			break;
			case 5:
				
			break;
			}
		
			book.setList(articles);
			
			return true;
		}
		
		return false;
	}

}
