package com.spicstome.client.ui.form;

import java.util.LinkedHashMap;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.spicstome.client.dto.ReferentDTO;
import com.spicstome.client.place.AddUserPlace;
import com.spicstome.client.services.SpicsToMeServices;
import com.spicstome.client.ui.UsersManagementViewImpl;

public class ReferentsEditForm extends DynamicForm {
	
	private SelectItem selectItem;
	private ButtonItem editButtonItem, deleteButtonItem;
	private LinkedHashMap<String, String> valueMap, imagesValueMap;
	private List<ReferentDTO> referents;
	
	public ReferentsEditForm() {
		selectItem = new SelectItem("referents", "R&eacute;f&eacute;rents");
		editButtonItem = new ButtonItem("btn_edit_referent", "Editer");
		deleteButtonItem = new ButtonItem("btn_delete_referent", "Supprimer");
		
		valueMap = new LinkedHashMap<String, String>();
		imagesValueMap = new LinkedHashMap<String, String>();
		
		selectItem.setImageURLPrefix(FormUtils.UPLOAD_IMAGE_PATH);
		
		setFields(selectItem, editButtonItem, deleteButtonItem);
	}
	
	private void updateSelectItem() {
		
		selectItem.clearValue();
		valueMap.clear();
		imagesValueMap.clear();
		
		String firstReferentId = null;
		for(ReferentDTO referent : referents) {
			
			if (firstReferentId == null)
				firstReferentId = referent.getId().toString();
				
			valueMap.put(referent.getId().toString(), referent.getFirstName()+" "+referent.getName());
			imagesValueMap.put(referent.getId().toString(), referent.getImage().getFilename());
		}
		
		if (firstReferentId != null) {
			selectItem.setDefaultValue(firstReferentId);
			selectItem.enable();
			editButtonItem.enable();
			deleteButtonItem.enable();
		}
		else {
			selectItem.setDefaultValue("Aucun");
			selectItem.disable();
			editButtonItem.disable();
			deleteButtonItem.disable();
		}
		
		selectItem.setValueMap(valueMap);
		selectItem.setValueIcons(imagesValueMap);
	}
	
	public void setReferents (List<ReferentDTO> mReferents, final UsersManagementViewImpl view) {
		
		referents = mReferents;
		
		updateSelectItem();
		
		editButtonItem.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				Long idReferent = Long.parseLong(selectItem.getValueAsString());
				
				view.goTo(new AddUserPlace(idReferent.toString()));
			}				
		});
		
		deleteButtonItem.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				final Long idReferent = Long.parseLong(selectItem.getValueAsString());
				
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
		});
	}
}