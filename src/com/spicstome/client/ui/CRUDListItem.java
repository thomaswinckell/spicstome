package com.spicstome.client.ui;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasHorizontalAlignment.HorizontalAlignmentConstant;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;

public class CRUDListItem extends ListItem{

	HorizontalPanel CRUDPanel = new HorizontalPanel();
	PushButton buttonDelete = new PushButton(new Image("images/delete.png"));
	PushButton buttonEdit = new PushButton(new Image("images/edit.png"));
	PushButton buttonView = new PushButton(new Image("images/visualize.png"));
	public CRUDListItem(ArrayList<Item> list) {
		super(list);
		
	
		buttonEdit.setVisible(false);
		buttonDelete.setVisible(false);
		buttonView.setVisible(false);
		

		
		CRUDPanel.add(buttonView);
		CRUDPanel.add(buttonEdit);
		CRUDPanel.add(buttonDelete);
		
	

		
		verticalPanel.add(CRUDPanel);
	}
	
	@Override
	public void changeSelection(Item icon)
	{
		super.changeSelection(icon);
		
		boolean visible = selectionList.size()>0;

		buttonDelete.setVisible(visible);
		buttonEdit.setVisible(visible);
		buttonView.setVisible(visible);
		
	}
}
