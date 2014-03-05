package com.spicstome.client.ui.panel;

import java.util.ArrayList;

import com.smartgwt.client.widgets.tree.events.NodeClickEvent;
import com.spicstome.client.dto.FolderDTO;
import com.spicstome.client.dto.PecsDTO;
import com.spicstome.client.dto.StudentDTO;
import com.spicstome.client.dto.WordDTO;
import com.spicstome.client.ui.widget.ImageRecord;

public class InteractiveAlbumBookPanel extends InteractiveAlbumPanel{

	public Book book;
	public boolean favoriteFilter=false;

	public InteractiveAlbumBookPanel(Book book, final MailPanel mailView) {
		super(mailView);

	    this.book = book;	    
	    horizontalLayout.addMember(book);
	    verticalLayout.setHeight(book.heightPage+40);

	    
	}
	
	@Override
	public void setStudent(StudentDTO student)
	{
		super.setStudent(student);	
		super.init();
		favoriteFilter=false;
		book.setList(new ArrayList<ImageRecord>());
	}
	
	
	@Override
	public void onFolderClick(NodeClickEvent event)
	{
		super.onFolderClick(event);
		FolderDTO folder = (FolderDTO)folderTree.selectFolderNode.getAttributeAsObject("data");
		setFolderContent(folder);
	}
	
	public void setFolderContent(FolderDTO folder)
	{
		ArrayList<ImageRecord> articles = new ArrayList<ImageRecord>();
		
		for(PecsDTO pecsDTO:folder.getContent())
		{
			if(pecsDTO instanceof WordDTO)
			{
				WordDTO word = (WordDTO)pecsDTO;
				if(!favoriteFilter || (favoriteFilter && word.getFavorite()==1))
					articles.add(new ImageRecord(word));
			}
		}
		book.setList(articles);
	}
}
