package com.spicstome.client.ui.widget;

import com.smartgwt.client.widgets.IconButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;




public abstract class CRUDImageListPanel extends ImageListPanel{

	protected ImageRecord selectedRecord;
	
	protected IconButton buttonDelete = new IconButton("");
	protected IconButton buttonEdit = new IconButton("");
	protected IconButton buttonView = new IconButton("");
	protected HLayout CRUDPanel = new HLayout();
	
	public CRUDImageListPanel(Mode mode) {
		super(mode,200,150,100);
		
		buttonEdit.setVisible(false);
		buttonDelete.setVisible(false);
		buttonView.setVisible(false);
		
		buttonEdit.setIcon("edit.png");
		buttonDelete.setIcon("delete.png");
		buttonView.setIcon("visualize.png");
		

		CRUDPanel.addMember(buttonView);
		CRUDPanel.addMember(buttonEdit);
		CRUDPanel.addMember(buttonDelete);
		
		buttonView.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				onVisualize();
				
			}
		});
		
		addMember(CRUDPanel);
	}

	@Override
	public void selectChanged(ImageRecord object) {
		if(object==selectedRecord)
		{
			tileGrid.deselectRecord(object);
			selectedRecord=null;
		}
		else
		{
			selectedRecord = object;
		}
		
		
		buttonDelete.setVisible(selectedRecord!=null);
		buttonEdit.setVisible(selectedRecord!=null);
		buttonView.setVisible(selectedRecord!=null);
		
		
	}
	
	public abstract void onVisualize();


	
}
