package com.spicstome.client.ui.panel;

import java.util.ArrayList;

import com.smartgwt.client.widgets.tree.events.NodeClickEvent;
import com.spicstome.client.dto.WordDTO;
import com.spicstome.client.dto.FolderDTO;
import com.spicstome.client.dto.PecsDTO;
import com.spicstome.client.dto.StudentDTO;
import com.spicstome.client.ui.widget.ImageRecord;

/**
 * AlbumBook panel is composed of a book , the
 * treegrid of his parent class allow to navigate in the book
 */
public class AlbumBookPanel extends AlbumPanel{

	public Book book;
	public boolean favoriteFilter=false;

	public AlbumBookPanel(Book book) {
		super();

	    this.book = book;	    
	    horizontalLayout.addMember(book);
	    verticalLayout.setHeight(book.heightPage+40);

	    
	}
	
	@Override
	public void setStudent(StudentDTO student)
	{
		super.setStudent(student);	
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
