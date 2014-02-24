package com.spicstome.client.ui;

import java.util.ArrayList;

import com.spicstome.client.place.AlbumManagementPlace;
import com.spicstome.client.place.HistoryManagementPlace;
import com.spicstome.client.place.MailPlace;
import com.spicstome.client.place.UsersManagementPlace;
import com.spicstome.client.ui.widget.ImageTileGrid;
import com.spicstome.client.ui.widget.ImageRecord;
import com.spicstome.client.ui.widget.ImageTileGrid.Mode;

public class MainMenuViewImpl extends UserViewImpl implements MainMenuView {
	
	ImageTileGrid imageList;

	
	public MainMenuViewImpl() {
		super();

		imageList = new ImageTileGrid(Mode.CLICK,250,150,100){
			@Override
			public void OnSelectChanged(ImageRecord object) {
				
				redirect(object.getAttributeAsInt(ImageRecord.PICTURE_ID));
			}
		};

		imageList.setHeight(200);

		mainPanel.addMember(imageList);
	}
	
	public void redirect(int i)
	{
		if(admin)
		{
			switch(i) {
				case 0 :
					goTo(new AlbumManagementPlace());
					break;
				case 1 :
					goTo(new UsersManagementPlace());
					break;
				case 2 :
					goTo(new MailPlace());
					break;
				case 3 :
					goTo(new HistoryManagementPlace());
					break;
			}
		}
		else
		{
			switch(i) {
				case 0 :
					goTo(new AlbumManagementPlace());
					break;
				case 1 :
					goTo(new MailPlace());
					break;
			}
		}
		
	}
	
	@Override
	public void init(boolean admin)
	{
		
		super.init(admin);
		
		ArrayList<ImageRecord> modules = new ArrayList<ImageRecord>();
		
		if(admin)
		{
			modules.add(new ImageRecord(0,"Gestion des albums","albumlogo.png"));
			modules.add(new ImageRecord(1,"Gestion des utilisateurs","userlogo.png"));
			modules.add(new ImageRecord(2,"Mail","mail.png"));
			modules.add(new ImageRecord(3,"Historique","history.png"));
		}
		else
		{
			modules.add(new ImageRecord(0,"Voir les albums","albumlogo.png"));
			modules.add(new ImageRecord(1,"Mail","mail.png"));
		}
	
		
		imageList.clear();
		imageList.setItems(modules);
	}

	

	
}
