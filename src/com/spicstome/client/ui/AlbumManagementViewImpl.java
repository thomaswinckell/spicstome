package com.spicstome.client.ui;


import java.util.ArrayList;
import java.util.List;

import com.spicstome.client.place.AlbumPlace;
import com.spicstome.client.shared.Album;
import com.spicstome.client.ui.widget.CRUDImageListPanel;
import com.spicstome.client.ui.widget.ImageListPanel;
import com.spicstome.client.ui.widget.ImageRecord;
import com.spicstome.client.ui.widget.ImageListPanel.Mode;



public class AlbumManagementViewImpl extends UserViewLayout  implements AlbumManagementView
{
	
	

	ImageListPanel imageList;

	
	public AlbumManagementViewImpl()
	{
		super();
		
	
		
		ArrayList<ImageRecord> modules = new ArrayList<ImageRecord>();
		modules.add(new ImageRecord(0,"Album general","Album general","albumlogo.png"));
		modules.add(new ImageRecord(1,"Album exemple","Album exemple","albumlogo.png"));
		modules.add(new ImageRecord(2,"Album de Albert","Album de Albert","albumlogo.png"));
		
		
		imageList = new CRUDImageListPanel(Mode.CLICK){
			@Override
			public void onVisualize() {
				listener.goTo(new AlbumPlace());
			};
		};
		
		
		imageList.setItems(modules);

		addMember(imageList);

	}

	@Override
	public void setAlbum(List<Album> list) {
			
	}



	

}
