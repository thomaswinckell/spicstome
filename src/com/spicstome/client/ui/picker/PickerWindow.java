package com.spicstome.client.ui.picker;

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
import com.spicstome.client.shared.Album;
import com.spicstome.client.ui.panel.AlbumPanel;

/**
 * 
 * @author Maxime
 * A PickerWindow allow the user to choose somthing among a list of Album
 * A validButton shows up in the bottomLayout
 * 
 */
public abstract class PickerWindow extends Window{

	public AlbumPanel albumPanel;
	DynamicForm form = new DynamicForm();
	ComboBoxItem comboBox = new ComboBoxItem("owner","Album");
    IconButton validButton = new IconButton("");
    VLayout verticalLayout = new VLayout();
    HLayout bottomLayout = new HLayout();


	
	public PickerWindow(ArrayList<Album> listAlbum,int width,int height) {
		super();
				
		setWidth(width);
		setHeight(height);
  
        setTitle("Import depuis autre album");
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
		          albumPanel.setAlbum(new Album());
				
			}
		});
        
        form.setFields(comboBox);

        InitAlbumPanel();
        
        albumPanel.setAlbum(new Album());
  
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
        verticalLayout.addMember(albumPanel);
        verticalLayout.addMember(bottomLayout);
        
        addItem(verticalLayout);
	}

	public void InitAlbumPanel()
	{
		
	}
	
	public void UpdateValidButton()
	{
		
	}
}
