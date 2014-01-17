package com.spicstome.client.ui.picker;

import java.util.LinkedHashMap;
import java.util.Set;

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
import com.spicstome.client.dto.AlbumDTO;
import com.spicstome.client.dto.StudentDTO;
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
    Set<StudentDTO> others;

	
	public PickerWindow(Set<StudentDTO> list,int width,int height) {
		super();
				
		setWidth(width);
		setHeight(height);
		
		this.others = list;
  
        setTitle("Import depuis autre album");
        setShowMinimizeButton(false);
        setIsModal(true);
        setShowModalMask(true);
        centerInPage();
        
        setDismissOnOutsideClick(true);
        
        
       
     
        
        comboBox.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				
				albumPanel.setAlbum(getAlbumWithOwner(Integer.valueOf(event.getValue().toString())));
			}
		});
        LinkedHashMap<Integer, String> map = new LinkedHashMap<Integer, String>(others.size());
        for(StudentDTO student : others)
        {
        	  map.put(new Integer(student.getId().toString()),student.getFirstName());
        }
           
       
        comboBox.setValueMap(map);
        comboBox.setValue(map.values().iterator().next());
        
        form.setFields(comboBox);

        InitAlbumPanel();
        
        /* setting first album */
        albumPanel.setAlbum(others.iterator().next().getAlbum());
  
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

	public AlbumDTO getAlbumWithOwner(int idOwner)
	{
		for(StudentDTO student : others)
		{
			if(student.getId()==idOwner)
				return student.getAlbum();
		}
		return null;
	}
	
	public void InitAlbumPanel()
	{
		
	}
	
	public void UpdateValidButton()
	{
		
	}
}
