package com.spicstome.client.ui.form;

import java.util.LinkedHashMap;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IconButton;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.fields.events.KeyPressEvent;
import com.smartgwt.client.widgets.form.fields.events.KeyPressHandler;
import com.smartgwt.client.widgets.grid.CellFormatter;
import com.smartgwt.client.widgets.grid.HoverCustomizer;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.spicstome.client.dto.ReferentDTO;
import com.spicstome.client.place.AddUserPlace;
import com.spicstome.client.services.SpicsToMeServices;
import com.spicstome.client.ui.UsersManagementViewImpl;

public class ReferentsEditForm extends HLayout {
	
	private DynamicForm form;
	private ComboBoxItem selectItem;
	private IconButton editButton, deleteButton;
	private LinkedHashMap<String, String> valueMap;
	private List<ReferentDTO> referents;
	
	public ReferentsEditForm() {
		
		super();
		
		setWidth("500px");
		setMargin(20);
		setLayoutAlign(Alignment.CENTER);
		
		form = new DynamicForm();
		form.setMargin(10);
		
		selectItem = new ComboBoxItem("referents", "<b>R&eacute;f&eacute;rents</b>");
		selectItem.setWidth(320);
		
		ListGrid pickListProperties = new ListGrid();  
        pickListProperties.setCellHeight(50);  
        pickListProperties.setCanHover(true);  
        pickListProperties.setShowHover(true);  
        pickListProperties.setCellFormatter(new CellFormatter() {  
            @Override  
            public String format(Object value, ListGridRecord record, int rowNum, int colNum) {
            	ReferentDTO referent = referents.get(rowNum);  
                String styleStr = "white-space:nowrap;overflow:hidden;";  
                String retStr = "<table>" +  
                        "<tr>" +
                        "<td align='right'><span style='" + styleStr + "width:50px;float:left;'>" +
                    		"<img src='images/"+ FormUtils.UPLOAD_IMAGE_PATH + referent.getImage().getFilename() + "' height='40px' width='40px'>" +
                    	"<span></td>" +  
                		"<td ><span style='" + styleStr + "width:170px;float:right;font-weight:bold;font-family:arial;font-size:18px;'>" + referent.getFirstName() + " " + referent.getName() + "<span></td>" +  
                        "</tr></table>";  
                return retStr;  
  
            }  
        });
        
        pickListProperties.setHoverCustomizer(new HoverCustomizer() {  
            @Override  
            public String hoverHTML(Object value, ListGridRecord record, int rowNum, int colNum) { 
            	ReferentDTO referent = referents.get(rowNum);         	
            	String styleStr = "white-space:nowrap;overflow:hidden;";  
                String retStr = "<table>" +  
                        "<tr>" +
                		"<td align='center'><span style='" + styleStr + "width:200px;font-weight:bold;font-family:arial;font-size:25px;'>" + referent.getFirstName() + " " + referent.getName() + "<span></td>" +
                        "</tr><tr>" +
                		"<td align='center'><span style='" + styleStr + "width:200px;'>" +
                    		"<img src='images/"+ FormUtils.UPLOAD_IMAGE_PATH + referent.getImage().getFilename() + "' max-width='200px'>" +
                    	"<span></td>" +   
                        "</tr></table>";  
                return retStr;  
            }  
        });
  
        selectItem.setPickListProperties(pickListProperties);
        
        selectItem.addKeyPressHandler(new KeyPressHandler() {
			@Override
			public void onKeyPress(KeyPressEvent event) {
				event.cancel();
			}        	
        });
		
		form.setFields(selectItem);
		
		editButton = new IconButton("");
		editButton.setIcon("edit.png");
		editButton.setIconSize(40);
		editButton.setTop("-30px");
		deleteButton = new IconButton("");
		deleteButton.setIcon("delete.png");
		deleteButton.setIconSize(40);
		
		valueMap = new LinkedHashMap<String, String>();
		
		addMember(form);
		addMember(editButton);
		addMember(deleteButton);
	}
	
	private void updateSelectItem() {
		
		selectItem.clearValue();
		valueMap.clear();
		
		String firstReferentId = null;
		for(ReferentDTO referent : referents) {
			
			if (firstReferentId == null)
				firstReferentId = referent.getId().toString();
				
			valueMap.put(referent.getId().toString(), referent.getFirstName()+" "+referent.getName());
		}
		
		if (firstReferentId != null) {
			selectItem.setDefaultValue(firstReferentId);
			selectItem.enable();
			editButton.enable();
			deleteButton.enable();
		}
		else {
			selectItem.setDefaultValue("Aucun");
			selectItem.disable();
			editButton.disable();
			deleteButton.disable();
		}
		
		selectItem.setValueMap(valueMap);
	}
	
	public void setReferents (List<ReferentDTO> mReferents, final UsersManagementViewImpl view) {
		
		referents = mReferents;
		
		updateSelectItem();
		
		editButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				Long idReferent = Long.parseLong(selectItem.getValueAsString());
				
				view.goTo(new AddUserPlace(idReferent.toString()));
			}				
		});
		
		deleteButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				SC.ask("Confirmation de suppression", "&Ecirc;tes-vous s&ucirc;r(e) de vouloir supprimer cet utilisateur ?", 
					new BooleanCallback() {
						@Override
						public void execute(Boolean confirm) {
							if (confirm) {
								final Long idReferent = Long.parseLong(selectItem.getValueAsString());
								
								if (idReferent == 1) {
									SC.warn("Cet utilisateur est l'unique administrateur. Il est donc impossible de le supprimer.");
								} else {				
									SpicsToMeServices.Util.getInstance().deleteUser(idReferent, new AsyncCallback<Boolean> () {
					
										@Override
										public void onFailure(Throwable caught) {
											System.out.println(caught);
										}
					
										@Override
										public void onSuccess(Boolean result) {
											for(ReferentDTO referent : referents) {
												if (referent.getId() == idReferent) {
													referents.remove(referent);
													break;
												}
											}
											updateSelectItem();
										}					
									});
								}
							}
						}					
				});
			}				
		});
	}
}