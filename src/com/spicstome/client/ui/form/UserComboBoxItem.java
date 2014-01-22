package com.spicstome.client.ui.form;

import java.util.LinkedHashMap;
import java.util.List;

import com.smartgwt.client.event.Cancellable;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.form.fields.events.EditorEnterEvent;
import com.smartgwt.client.widgets.form.fields.events.EditorEnterHandler;
import com.smartgwt.client.widgets.form.fields.events.FocusEvent;
import com.smartgwt.client.widgets.form.fields.events.FocusHandler;
import com.smartgwt.client.widgets.form.fields.events.KeyPressEvent;
import com.smartgwt.client.widgets.form.fields.events.KeyPressHandler;
import com.smartgwt.client.widgets.grid.CellFormatter;
import com.smartgwt.client.widgets.grid.HoverCustomizer;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.spicstome.client.dto.UserDTO;

public class UserComboBoxItem extends ComboBoxItem {
	
	private List<UserDTO> users;
	private LinkedHashMap<String, String> valueMap;
	private boolean isEmpty;

	public UserComboBoxItem (String name, String title) {
		
		super(name, title);
		
		valueMap = new LinkedHashMap<String, String>();
		isEmpty = true;
		
		setWidth(320);
		
		ListGrid pickListProperties = new ListGrid();  
        pickListProperties.setCellHeight(50);  
        pickListProperties.setCanHover(true);  
        pickListProperties.setShowHover(true);  
        pickListProperties.setCellFormatter(new CellFormatter() {  
            @Override  
            public String format(Object value, ListGridRecord record, int rowNum, int colNum) {
            	if (rowNum < users.size()) {
	            	UserDTO user = users.get(rowNum);  
	                String styleStr = "white-space:nowrap;overflow:hidden;";  
	                String retStr = "<table>" +  
	                        "<tr>" +
	                        "<td align='right'><span style='" + styleStr + "width:50px;float:left;'>" +
	                    		"<img src='images/"+ FormUtils.UPLOAD_IMAGE_PATH + user.getImage().getFilename() + "' height='40px' width='40px'>" +
	                    	"<span></td>" +  
	                		"<td ><span style='" + styleStr + "width:170px;float:right;font-weight:bold;font-family:arial;font-size:18px;'>" + user.getFirstName() + " " + user.getName() + "<span></td>" +  
	                        "</tr></table>";  
	                return retStr;
            	} else {
            		return "";
            	}  
            }  
        });
        
        pickListProperties.setHoverCustomizer(new HoverCustomizer() {  
            @Override  
            public String hoverHTML(Object value, ListGridRecord record, int rowNum, int colNum) { 
            	UserDTO user = users.get(rowNum);         	
            	String styleStr = "white-space:nowrap;overflow:hidden;";  
                String retStr = "<table>" +  
                        "<tr>" +
                		"<td align='center'><span style='" + styleStr + "width:200px;font-weight:bold;font-family:arial;font-size:25px;'>" + user.getFirstName() + " " + user.getName() + "<span></td>" +
                        "</tr><tr>" +
                		"<td align='center'><span style='" + styleStr + "width:200px;'>" +
                    		"<img src='images/"+ FormUtils.UPLOAD_IMAGE_PATH + user.getImage().getFilename() + "' max-width='200px'>" +
                    	"<span></td>" +   
                        "</tr></table>";  
                return retStr;  
            }  
        });
  
        setPickListProperties(pickListProperties);
        
        /*addKeyPressHandler(new KeyPressHandler() {
			@Override
			public void onKeyPress(KeyPressEvent event) {
				event.cancel();
			}        	
        });*/
        
        setAddUnknownValues(false);
	}
	
	private void updateSelectItem() {
		
		clearValue();
		valueMap.clear();
		
		String firstReferentId = null;
		for(UserDTO user : users) {
			
			if (firstReferentId == null)
				firstReferentId = user.getId().toString();
				
			valueMap.put(user.getId().toString(), user.getFirstName()+" "+user.getName());
		}
		
		if (firstReferentId != null) {
			setDefaultValue(firstReferentId);
			enable();
			isEmpty = false;
		}
		else {
			setDefaultValue("Aucun");
			disable();
			isEmpty = true;
		}
		
		setValueMap(valueMap);
	}
	
	public boolean isEmpty() {
		return isEmpty;
	}
	
	public void setUsers (List<UserDTO> mUsers) {
		users = mUsers;
		
		updateSelectItem();
	}
	
	public void removeUser(Long idUser) {
		for(UserDTO user : users) {
			if (user.getId() == idUser) {
				users.remove(user);
				updateSelectItem();
				return;
			}
		}
	}
}
