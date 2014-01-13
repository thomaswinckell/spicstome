package com.spicstome.client.ui.panel;

import java.util.ArrayList;

import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.tree.events.NodeClickEvent;
import com.spicstome.client.dto.AlbumDTO;
import com.spicstome.client.dto.ArticleDTO;
import com.spicstome.client.dto.FolderDTO;
import com.spicstome.client.dto.PecsDTO;
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
		
	}
	
	@Override
	public boolean onFolderClick(NodeClickEvent event)
	{
		ArrayList<ImageRecord> articles = new ArrayList<ImageRecord>();
		
		if(super.onFolderClick(event))
		{
			FolderDTO folder = (FolderDTO)folderTree.selectFolderNode.getAttributeAsObject("data");
			
			
			for(PecsDTO pecsDTO:folder.getContent())
			{
				if(pecsDTO instanceof ArticleDTO)
				{
					System.out.println("ajout article");
					articles.add(new ImageRecord((ArticleDTO)pecsDTO));
				}
					
			}
		
			book.setList(articles);
			
			return true;
		}
		
		return false;
	}

}
