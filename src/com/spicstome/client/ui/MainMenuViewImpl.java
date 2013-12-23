package com.spicstome.client.ui;

import java.util.ArrayList;




import com.spicstome.client.place.AlbumManagementPlace;
import com.spicstome.client.place.MailPlace;
import com.spicstome.client.ui.widget.ImageTileGrid;
import com.spicstome.client.ui.widget.ImageRecord;
import com.spicstome.client.ui.widget.ImageTileGrid.Mode;

public class MainMenuViewImpl extends UserViewLayout implements MainMenuView
{

	
	
	ImageTileGrid imageList;
	
	
	public MainMenuViewImpl()
	{
		super();
		
		ArrayList<ImageRecord> modules = new ArrayList<ImageRecord>();
		modules.add(new ImageRecord(0,"Gestion album","albumlogo.png"));
		modules.add(new ImageRecord(1,"Gestion des utilisateurs","userlogo.png"));
		modules.add(new ImageRecord(2,"Mail","mail.png"));
		
		
		imageList = new ImageTileGrid(Mode.CLICK,200,150,100){
			@Override
			public void OnSelectChanged(ImageRecord object) {

				if(object.getAttributeAsInt(ImageRecord.PICTURE_ID)==0)
				{
					goTo(new AlbumManagementPlace());
				}

				if(object.getAttributeAsInt(ImageRecord.PICTURE_ID)==2)
				{
					goTo(new MailPlace());
				}
				
				
			}
		};
		
		imageList.setItems(modules);
		
		imageList.setHeight(200);

		mainPanel.addMember(imageList);
	}

	

	
}
