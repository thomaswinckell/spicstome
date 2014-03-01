package com.spicstome.client.ui;

import java.util.ArrayList;

import com.spicstome.client.place.NewMailPlace;
import com.spicstome.client.place.ReceivedMailsPlace;
import com.spicstome.client.ui.widget.Crumb;
import com.spicstome.client.ui.widget.ImageRecord;
import com.spicstome.client.ui.widget.ImageTileGrid;
import com.spicstome.client.ui.widget.ImageTileGrid.Mode;

public class MailViewImpl extends UserViewImpl  implements MailView
{

	ImageTileGrid imageList;
	
	public MailViewImpl()
	{
		super();
	
		
		addCrumb(new Crumb("Mail"){});
	
		
		ArrayList<ImageRecord> modules = new ArrayList<ImageRecord>();
		modules.add(new ImageRecord(0,"Lire mes mail","lire.gif"));
		modules.add(new ImageRecord(1,"Ecrire un mail","ecrire.gif"));

		
		
		imageList = new ImageTileGrid(Mode.CLICK,250,150,100){
			@Override
			public void OnSelectChanged(ImageRecord object) {
				
				switch (object.getAttributeAsInt(ImageRecord.PICTURE_ID)) {
					case 0 :
						goTo(new ReceivedMailsPlace());
						break;
					case 1 :
						goTo(new NewMailPlace("?"));
						break;
				
				}
			}
		};
		
		imageList.setItems(modules);
		
		imageList.setHeight(200);

		mainPanel.addMember(imageList);
	}

	
}
