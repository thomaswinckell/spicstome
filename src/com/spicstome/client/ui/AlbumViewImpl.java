package com.spicstome.client.ui;

import com.spicstome.client.place.AlbumManagementPlace;
import com.spicstome.client.shared.Album;
import com.spicstome.client.ui.panel.AlbumBookPanel;
import com.spicstome.client.ui.panel.AlbumPanel;
import com.spicstome.client.ui.panel.Book;
import com.spicstome.client.ui.widget.Crumb;

public class AlbumViewImpl extends UserViewLayout  implements AlbumView{
	
	
	AlbumPanel albumPanel;
	
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

		
		this.albumPanel = new AlbumBookPanel(new Book(100));
		
		mainPanel.addMember(this.albumPanel);
	}

	@Override
	public void setAlbum(Album a) {
	
		albumPanel.setAlbum(a);
		
	}
}
