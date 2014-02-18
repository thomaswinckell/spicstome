package com.spicstome.client.ui.window;

import java.util.List;
import com.smartgwt.client.widgets.tree.events.NodeClickEvent;
import com.spicstome.client.dto.StudentDTO;
import com.spicstome.client.ui.panel.AlbumBookPanel;
import com.spicstome.client.ui.panel.Book;
import com.spicstome.client.ui.widget.ImageRecord;

public class WordPickerWindow extends PickerWindow {

    public Book book;
	
	public WordPickerWindow(List<StudentDTO> list) {
		super(list,Type.IMPORT, 1200, 600);
		
	}
	@Override
	public void InitAlbumPanel()
	{
		setTitle("Importer un article depuis autre album");
		
		 book = new Book(100){
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
