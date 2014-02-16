package com.spicstome.client.ui.window;

import java.util.LinkedHashMap;
import java.util.List;
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
import com.spicstome.client.dto.StudentDTO;
import com.spicstome.client.ui.panel.AlbumPanel;

/**
 * 
 * @author Maxime
 * A PickerWindow allow the user to choose something among a list of Album
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
    List<StudentDTO> others;

    public enum Type{IMPORT,MOVE};
	Type type;
	
	public PickerWindow(List<StudentDTO> list,Type type,int width,int height) {
		super();
				
		this.type=type;
		
		setWidth(width);
		setHeight(height);
		
		this.others = list;
		
  
        
        setShowMinimizeButton(false);
        setIsModal(true);
        setShowModalMask(true);
        centerInPage();
        
        setDismissOnOutsideClick(true);
        
        
        if(others.size()>1)
        {
        	  LinkedHashMap<Integer, String> map = new LinkedHashMap<Integer, String>(others.size());
             
        	 
        	  
        	  for(StudentDTO student : others)
              {
        		  if(student.getAlbum().getId()==1)
        			  map.put(1,"Album général");
        		  else if(student.getAlbum().getId()==2)
        			  map.put(2,"Album exemple");
        		  else
        			  map.put(new Integer(student.getAlbum().getId().toString()),student.getFirstName());
              }
        	  
        	
        	 
        	  
              
              comboBox.setValueMap(map);
              comboBox.setValue(map.values().iterator().next());
              
              form.setFields(comboBox);
              
              comboBox.addChangeHandler(new ChangeHandler() {

            	  @Override
            	  public void onChange(ChangeEvent event) {

            		  int choice = Integer.valueOf(event.getValue().toString());
            		  
        			  StudentDTO student = getStudentWithId(choice);
            		  albumPanel.setStudent(student);
            		  
            		 
            	  }
              });
        }
      


        InitAlbumPanel();
        
        /* setting first album */
      
    	albumPanel.setStudent(others.iterator().next());
       
  
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
   
        if(others.size()>1)
        	verticalLayout.addMember(form);
        
        verticalLayout.addMember(albumPanel);
        verticalLayout.addMember(bottomLayout);
        
        addItem(verticalLayout);
	}

	public StudentDTO getStudentWithId(int id)
	{
		
		for(StudentDTO student : others)
		{
			if(student.getAlbum().getId()==id)
				return student;
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
