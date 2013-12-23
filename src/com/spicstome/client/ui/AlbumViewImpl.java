package com.spicstome.client.ui;


import com.smartgwt.client.widgets.Canvas;
import com.spicstome.client.place.AlbumManagementPlace;
import com.spicstome.client.shared.Album;
import com.spicstome.client.ui.widget.AlbumBookPanel;
import com.spicstome.client.ui.widget.AlbumEditPanel;
import com.spicstome.client.ui.widget.AlbumPanel;
import com.spicstome.client.ui.widget.Crumb;

public class AlbumViewImpl extends UserViewLayout  implements AlbumView{
	
	
	AlbumPanel album;
	
	public AlbumViewImpl()
	{
		
		super();
		
		addCrumb(new Crumb("Les albums"){
			@Override
			public void onClickCrumb() {			
				goTo(new AlbumManagementPlace());
			}
		});
		
		addCrumb(new Crumb("Album de Albert"){});

	}

	@Override
	public void setAlbum(Album a,String m) {
		
		Canvas[] children = mainPanel.getChildren();
		mainPanel.removeMembers(children);
		
		if(m=="EDIT")
			this.album = new AlbumEditPanel(new Album());
		else
			this.album = new AlbumBookPanel(new Album());
		
		
		
		mainPanel.addMember(this.album);
		
	}
}
