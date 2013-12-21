package com.spicstome.client.ui;

import java.util.ArrayList;



import com.spicstome.client.place.AlbumManagementPlace;
import com.spicstome.client.ui.widget.ImageListPanel;
import com.spicstome.client.ui.widget.ImageRecord;
import com.spicstome.client.ui.widget.ImageListPanel.Mode;

public class MainMenuViewImpl extends UserViewLayout implements MainMenuView
{

	
	
	ImageListPanel imageList;
	
	
	public MainMenuViewImpl()
	{
		super();
		
		ArrayList<ImageRecord> modules = new ArrayList<ImageRecord>();
		modules.add(new ImageRecord(0,"Gestion album","albumlogo.png"));
		modules.add(new ImageRecord(1,"Gestion des utilisateurs","userlogo.png"));
		
		
		imageList = new ImageListPanel(Mode.CLICK,200,150,100){
			@Override
			public void selectChanged(ImageRecord object) {

				if(object.getAttributeAsInt(ImageRecord.PICTURE_ID)==0)
				{
					goTo(new AlbumManagementPlace());
				}
				
				
				
			}
		};
		
		imageList.setItems(modules);

		mainPanel.addMember(imageList);
	}

	

	
}
