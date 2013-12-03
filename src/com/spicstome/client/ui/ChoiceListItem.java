package com.spicstome.client.ui;

import java.util.ArrayList;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ChoiceListItem extends ListItem{

	VerticalPanel choicePanel = new VerticalPanel();
	PushButton buttonSelect = new PushButton(new Image("images/check.png"));
	
	public ChoiceListItem(ArrayList<Item> list) {
		super(list);
		
		choicePanel.add(buttonSelect);
		
		buttonSelect.setVisible(false);
		
		verticalPanel.add(choicePanel);
	}
	
	@Override
	public void changeSelection(Item icon)
	{
		super.changeSelection(icon);
		
		boolean visible = selectionList.size()>0;

		
		buttonSelect.setVisible(visible);
	}

}
