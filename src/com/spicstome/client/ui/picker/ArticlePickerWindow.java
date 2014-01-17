package com.spicstome.client.ui.picker;

import java.util.Set;
import com.smartgwt.client.widgets.tree.events.NodeClickEvent;
import com.spicstome.client.dto.StudentDTO;
import com.spicstome.client.ui.panel.AlbumBookPanel;
import com.spicstome.client.ui.panel.Book;
import com.spicstome.client.ui.widget.ImageRecord;

public class ArticlePickerWindow extends PickerWindow {

    public Book book;
	
	public ArticlePickerWindow(Set<StudentDTO> list) {
		super(list, 1000, 450);
		
	}
	@Override
	public void InitAlbumPanel()
	{
		 book = new Book(50){
	        	@Override
	        	public void onSelectChangeBook(ImageRecord image)
	        	{
	        		super.onSelectChangeBook(image);
	        		
	        		UpdateValidButton();
	        	}
	        	
	        	@Override
	        	public void onChangePage()
	        	{
	        		super.onChangePage();
	        		
	        		UpdateValidButton();
	        	}
	        };
		
		albumPanel =  new AlbumBookPanel(book){
        	@Override
        	public void onFolderClick(NodeClickEvent event)
        	{
        		super.onFolderClick(event);       		
        		UpdateValidButton();
        	
        	}
        };
        
        
        validButton.setTitle("Importer cet article");
	}

	@Override
	public void UpdateValidButton()
	{
		validButton.setVisible(book.selectedImage!=null);
	}
}
