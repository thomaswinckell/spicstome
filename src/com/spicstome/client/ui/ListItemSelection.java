package com.spicstome.client.ui;

import java.util.ArrayList;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ListItemSelection extends Composite{

	VerticalPanel verticalPanel = new VerticalPanel();
	HorizontalPanel horizontalPanel = new HorizontalPanel();
	ArrayList<ItemSelection> list;
	ArrayList<ItemSelection> selectionList;
	
	
	public ListItemSelection(ArrayList<ItemSelection> list) {

		this.list=list;
		
		this.selectionList = new ArrayList<ItemSelection>();
		
		for (ItemSelection iconComposite : list) {
			
			horizontalPanel.add(iconComposite);
			iconComposite.setParent(this);

		}
		verticalPanel.add(horizontalPanel);
		
		
		initWidget(verticalPanel);
	}
	
	public void changeSelection(ItemSelection icon)
	{

			icon.Check();
			
			if(icon.check)
				selectionList.add(icon);
			else
				selectionList.remove(icon);
	
	}
	
	
}
