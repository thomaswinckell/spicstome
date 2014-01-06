package com.spicstome.client.ui;

import com.spicstome.client.place.AlbumManagementPlace;
import com.spicstome.client.shared.Album;
import com.spicstome.client.ui.panel.AlbumEditPanel;
import com.spicstome.client.ui.panel.AlbumPanel;
import com.spicstome.client.ui.widget.Crumb;

public class AlbumEditViewImpl extends UserViewLayout  implements AlbumEditView{
	
	
	AlbumPanel albumPanel;
	
	public AlbumEditViewImpl()
	{
		
		super();
		
		addCrumb(new Crumb("Les albums"){
			@Override
			public void onClickCrumb() {			
				goTo(new AlbumManagementPlace());
			}
		});
		
		addCrumb(new Crumb("Album de Albert (edit)"){});

		
		this.albumPanel = new AlbumEditPanel();
		
		
		mainPanel.addMember(this.albumPanel);
	}

	@Override
	public void setAlbum(Album a) {
		
		this.albumPanel.setAlbum(a);
		
	}
}
