package com.spicstome.client.ui.widget;

import com.smartgwt.client.widgets.IconButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;

public abstract class ActionPanel extends HLayout{

	protected IconButton buttonNew = new IconButton("");
	protected IconButton buttonDelete = new IconButton("");
	protected IconButton buttonEdit = new IconButton("");
	protected IconButton buttonView = new IconButton("");
	
	protected HLayout CRUDPanel = new HLayout();
	
	public ActionPanel()
	{
		super();
		
		buttonEdit.setVisible(false);
		buttonDelete.setVisible(false);
		buttonView.setVisible(false);
		
		buttonEdit.setIcon("edit.png");
		buttonDelete.setIcon("delete.png");
		buttonView.setIcon("visualize.png");
		buttonNew.setIcon("new.png");
		

		
		int iconsize=40;
		
		buttonEdit.setIconSize(iconsize);
		buttonDelete.setIconSize(iconsize);
		buttonView.setIconSize(iconsize);
		buttonNew.setIconSize(iconsize);
		

		CRUDPanel.addMember(buttonView);
		CRUDPanel.addMember(buttonEdit);
		CRUDPanel.addMember(buttonDelete);
		
		CRUDPanel.setWidth(3*iconsize);
		
		
		
		addMember(buttonNew);
		addMember(CRUDPanel);
		
		
		setHeight(iconsize);
		setWidth(4*iconsize);
		
		buttonView.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				onVisualize();
				
			}
		});
		
		buttonEdit.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				onEdit();
				
			}
		});
		
		buttonNew.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				onNew();
				
			}
		});
	}
	
	public IconButton getButtonNew() {
		return buttonNew;
	}

	public IconButton getButtonDelete() {
		return buttonDelete;
	}

	public IconButton getButtonEdit() {
		return buttonEdit;
	}

	public IconButton getButtonView() {
		return buttonView;
	}

	public void setActionVisible(boolean b)
	{
		getButtonDelete().setVisible(b);
		getButtonEdit().setVisible(b);
		getButtonView().setVisible(b);
	}
	
	public abstract void onVisualize();
	public abstract void onEdit();
	public abstract void onNew();
	
}
