package com.spicstome.client.ui.widget;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.IconButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.events.ChangeEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangeHandler;
import com.smartgwt.client.widgets.layout.VLayout;
import com.spicstome.client.shared.Album;

public class ImagePickerWindow extends Window{

	public AlbumBookPanel albmBook;
	DynamicForm form = new DynamicForm();
	ComboBoxItem comboBox = new ComboBoxItem("owner","Album");
    IconButton validButton = new IconButton("");
    VLayout verticalLayout = new VLayout();
	
	public ImagePickerWindow() {
		super();
		
		setWidth(1000);
        setHeight(450);
        
        setTitle("Import image");
        setShowMinimizeButton(false);
        setIsModal(true);
        setShowModalMask(true);
        centerInPage();
        
       
        
        
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
        
        
        Book book = new Book(50){
        	@Override
        	public void onSelectChangeBook(ImageRecord image)
        	{
        		//if(book)
        	}
        };
        
        albmBook =  new AlbumBookPanel(book);
        
  
        validButton.setIcon("check.png");
        int iconsize=32;
        validButton.setIconSize(iconsize);

        
        validButton.setLayoutAlign(Alignment.RIGHT);
        
        validButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
						destroy();					
			}
		});
        
        
        verticalLayout.addMember(form);
        verticalLayout.addMember(albmBook);
        verticalLayout.addMember(validButton);
        
        addItem(verticalLayout);
       
	}
	
	
}
