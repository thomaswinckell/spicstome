package com.spicstome.client.ui;

import java.util.ArrayList;
import com.spicstome.client.place.AlbumManagementPlace;
import com.spicstome.client.place.MailPlace;
import com.spicstome.client.place.UsersManagementPlace;
import com.spicstome.client.ui.widget.ImageTileGrid;
import com.spicstome.client.ui.widget.ImageRecord;
import com.spicstome.client.ui.widget.ImageTileGrid.Mode;

public class MainMenuViewImpl extends UserViewImpl implements MainMenuView {
	
	ImageTileGrid imageList;	
	
	public MainMenuViewImpl() {
		super();
		
		ArrayList<ImageRecord> modules = new ArrayList<ImageRecord>();
		modules.add(new ImageRecord(0,"Gestion des albums","albumlogo.png"));
		modules.add(new ImageRecord(1,"Gestion des utilisateurs","userlogo.png"));
		modules.add(new ImageRecord(2,"Mail","mail.png"));
		modules.add(new ImageRecord(3,"Historique","history.png"));
		
		
		imageList = new ImageTileGrid(Mode.CLICK,250,150,100){
			@Override
			public void OnSelectChanged(ImageRecord object) {
				
				switch (object.getAttributeAsInt(ImageRecord.PICTURE_ID)) {
					case 0 :
						goTo(new AlbumManagementPlace());
						break;
					case 1 :
						goTo(new UsersManagementPlace());
						break;
					case 2 :
						goTo(new MailPlace());
						break;
				}
			}
		};
		
		imageList.setItems(modules);
		
		imageList.setHeight(200);

		mainPanel.addMember(imageList);
	}

	

	
}
