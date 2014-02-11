package com.spicstome.client.ui.panel;

import java.util.ArrayList;
import com.smartgwt.client.widgets.tree.events.NodeClickEvent;
import com.spicstome.client.dto.WordDTO;
import com.spicstome.client.dto.FolderDTO;
import com.spicstome.client.dto.PecsDTO;
import com.spicstome.client.dto.StudentDTO;
import com.spicstome.client.ui.widget.ImageRecord;

public class AlbumBookPanel extends AlbumPanel{

	public Book book;

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
		book.setList(new ArrayList<ImageRecord>());
	}
	
	@Override
	public void onFolderClick(NodeClickEvent event)
	{
		ArrayList<ImageRecord> articles = new ArrayList<ImageRecord>();
		
		super.onFolderClick(event);
		
		FolderDTO folder = (FolderDTO)folderTree.selectFolderNode.getAttributeAsObject("data");
		
		
		for(PecsDTO pecsDTO:folder.getContent())
		{
			if(pecsDTO instanceof WordDTO)
			{
				articles.add(new ImageRecord((WordDTO)pecsDTO));
			}
				
		}
	
		book.setList(articles);
			
	
	}

}
