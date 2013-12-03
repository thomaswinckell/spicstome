package com.spicstome.client.ui;

import java.util.ArrayList;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;

public class AlbumListItemSelection extends ListItemSelection{

	PushButton button = new PushButton(new Image("images/check.png"));
	
	public AlbumListItemSelection(ArrayList<ItemSelection> list) {
		super(list);
		
		button.setVisible(false);
		verticalPanel.add(button);
	}
	
	@Override
	public void changeSelection(ItemSelection icon)
	{
		super.changeSelection(icon);
		
		button.setVisible(selectionList.size()>0);
	}
}
