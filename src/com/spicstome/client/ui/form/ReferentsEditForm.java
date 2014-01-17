package com.spicstome.client.ui.form;

import java.util.LinkedHashMap;
import java.util.List;

import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.spicstome.client.dto.ReferentDTO;
import com.spicstome.client.place.AddUserPlace;
import com.spicstome.client.ui.UsersManagementViewImpl;

public class ReferentsEditForm extends DynamicForm {
	
	SelectItem selectItem;
	ButtonItem buttonItem;
	LinkedHashMap<String, String> valueMap, imagesValueMap;
	
	public ReferentsEditForm() {
		selectItem = new SelectItem("referents", "R&eacute;f&eacute;rents");
		buttonItem = new ButtonItem("btn_edit_referent", "Editer");
		
		valueMap = new LinkedHashMap<String, String>();
		imagesValueMap = new LinkedHashMap<String, String>();
		
		selectItem.setImageURLPrefix(FormUtils.UPLOAD_IMAGE_PATH);
		
		setFields(selectItem, buttonItem);
	}
	
	public void setReferents (List<ReferentDTO> referents, final UsersManagementViewImpl view) {
		String firstReferentId = null;
		for(ReferentDTO referent : referents) {
			
			if (firstReferentId == null)
				firstReferentId = referent.getId().toString();
				
			valueMap.put(referent.getId().toString(), referent.getFirstName()+" "+referent.getName());
			imagesValueMap.put(referent.getId().toString(), referent.getImage().getFilename());
		}
		
		if (firstReferentId != null) {
			selectItem.setDefaultValue(firstReferentId);
			buttonItem.enable();
		}
		else {
			selectItem.setDefaultValue("Aucun");
			selectItem.disable();
			buttonItem.disable();
		}
		
		selectItem.setValueMap(valueMap);
		selectItem.setValueIcons(imagesValueMap);
		
		buttonItem.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
			
			@Override
			public void onClick(com.smartgwt.client.widgets.form.fields.events.ClickEvent event) {
				
				Long idReferent = Long.parseLong(selectItem.getValueAsString());
				
				view.goTo(new AddUserPlace(idReferent.toString()));
			}				
		});
	}
}