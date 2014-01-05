package com.spicstome.client.ui.widget;

import java.util.ArrayList;
import com.smartgwt.client.widgets.IconButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.events.ChangeEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangeHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tree.events.NodeClickEvent;
import com.spicstome.client.shared.Album;

public class ImagePickerWindow extends Window{

	public AlbumBookPanel albmBook;
	DynamicForm form = new DynamicForm();
	ComboBoxItem comboBox = new ComboBoxItem("owner","Album");
    IconButton validButton = new IconButton("");
    VLayout verticalLayout = new VLayout();
    HLayout bottomLayout = new HLayout();
    Book book;
	
	public ImagePickerWindow(ArrayList<Album> listAlbum) {
		super();
		
		setWidth(1000);
        setHeight(450);
        
        setTitle("Import image");
        setShowMinimizeButton(false);
        setIsModal(true);
        setShowModalMask(true);
        centerInPage();
        
        setDismissOnOutsideClick(true);
        
        
        comboBox.setValueMap("Général","Albert","Robert");
        comboBox.setValue("Générale");
        
        comboBox.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
			
		          //String selectedItem = (String) event.getValue();  
		          albmBook.setAlbum(new Album());
				
			}
		});
        
        form.setFields(comboBox);
        
        
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
        
        albmBook =  new AlbumBookPanel(book){
        	@Override
        	public boolean onFolderClick(NodeClickEvent event)
        	{
        		boolean res = super.onFolderClick(event);
        		
        		UpdateValidButton();
        			
        		return res;
        	}
        };
        
  
        validButton.setIcon("check.png");
        int iconsize=32;
        validButton.setIconSize(iconsize);

        
        
        
        validButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				
						
						destroy();					
			}
		});
        
        validButton.setVisible(false);
        
      
        bottomLayout.setHeight(iconsize);
        bottomLayout.setWidth100();
        bottomLayout.addMember(validButton);

        
        verticalLayout.addMember(form);
        verticalLayout.addMember(albmBook);
        verticalLayout.addMember(bottomLayout);
        
        addItem(verticalLayout);
       
	}
	

	
	public void UpdateValidButton()
	{
		validButton.setVisible(book.selectedImage!=null);
	}
}
