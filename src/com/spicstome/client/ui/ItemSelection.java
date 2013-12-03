package com.spicstome.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ItemSelection extends Composite implements HasClickHandlers {

	VerticalPanel panel = new VerticalPanel();
	FocusPanel clickBox = new FocusPanel();
	Label label;
	Image image;
	ListItemSelection parent;
	boolean check=false;
	
	public ItemSelection(String srcImage,String text) 
	{
		label = new Label(text);
		image = new Image(srcImage);
	
		
		panel.add(image);
		panel.add(label);
		
		clickBox.add(panel);
		
		label.setStyleName("titleItemSelection");
		
		initWidget(clickBox);
		
		panel.setStyleName("itemSelectionUnchecked");
	}

	void setParent(ListItemSelection l)
	{
		
		parent=l;
		final ItemSelection me = this;
		
		clickBox.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {

				parent.changeSelection(me);
				
			}
		});
	}

	@Override
	public HandlerRegistration addClickHandler(ClickHandler handler) {
		
		if(parent!=null)
		{
			parent.changeSelection(this);
		}
		
		return null;
	}

	public void Check()
	{
		this.check=!check;
		
		if(check)
			panel.setStyleName("itemSelectionChecked");
		else
			panel.setStyleName("itemSelectionUnchecked");
	}
}
