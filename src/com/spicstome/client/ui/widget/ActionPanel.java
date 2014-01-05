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
	
	private boolean readAction = false;
	
	protected HLayout CRUDPanel = new HLayout();
	
	public ActionPanel(boolean readAction)
	{
		super();
		
		this.readAction=readAction;
		
		buttonNew.setVisible(true);
		buttonEdit.setVisible(false);
		buttonDelete.setVisible(false);	
		buttonView.setVisible(false);
		
		int iconsize=40;
		
		buttonNew.setIcon("new.png");
		buttonNew.setIconSize(iconsize);
		
		if(readAction)
		{	
			buttonView.setIcon("visualize.png");
			buttonView.setIconSize(iconsize);
		}
			
		buttonEdit.setIcon("edit.png");
		buttonEdit.setIconSize(iconsize);
		
		buttonDelete.setIcon("delete.png");
		buttonDelete.setIconSize(iconsize);

		if(readAction)
		{
			CRUDPanel.addMember(buttonView);
			CRUDPanel.setWidth(3*iconsize);
		}
		
		CRUDPanel.addMember(buttonEdit);
		CRUDPanel.addMember(buttonDelete);
		
		CRUDPanel.setWidth(2*iconsize);
		

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
		
		buttonDelete.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				onDelete();
				
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
		if(readAction)
			getButtonEdit().setVisible(b);
		
		getButtonDelete().setVisible(b);
		getButtonEdit().setVisible(b);
		getButtonView().setVisible(b);
	}
	
	public abstract void onVisualize();
	public abstract void onEdit();
	public abstract void onNew();
	public abstract void onDelete();
	
}
