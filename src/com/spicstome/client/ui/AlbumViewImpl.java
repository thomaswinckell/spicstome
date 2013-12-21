package com.spicstome.client.ui;


import com.spicstome.client.place.AlbumManagementPlace;
import com.spicstome.client.place.AlbumPlace;
import com.spicstome.client.shared.Album;
import com.spicstome.client.ui.widget.AlbumPanel;
import com.spicstome.client.ui.widget.Crumb;
import com.spicstome.client.ui.widget.ImageListPanel;
import com.spicstome.client.ui.widget.ImageListPanel.Mode;
import com.spicstome.client.ui.widget.ImageRecord;

public class AlbumViewImpl extends UserViewLayout  implements AlbumView{
	
	
	AlbumPanel album;
	ImageListPanel dropZone;
	
	public AlbumViewImpl()
	{
		
		super();
		
		addCrumb(new Crumb("Les albums"){

			@Override
			public void onClickCrumb() {
				
				goTo(new AlbumManagementPlace());
				
			}
			
		});
		
		addCrumb(new Crumb("Album de Albert"){

			@Override
			public void onClickCrumb() {
				
				goTo(new AlbumPlace());
				
			}
			
		});
		
		dropZone = new ImageListPanel(Mode.DRAG_AND_DROP, 200, 170, 150){

			@Override
			public void selectChanged(ImageRecord imageRecord) {
				// TODO Auto-generated method stub
				
			}
			
		};
		
		dropZone.setWidth100();
		dropZone.setHeight(270);
		
		dropZone.setStyleName("album");
		
        album = new AlbumPanel(new Album());
        
        mainPanel.addMember(album);
        mainPanel.addMember(dropZone);
	}
}
