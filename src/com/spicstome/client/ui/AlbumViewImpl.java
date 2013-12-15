package com.spicstome.client.ui;


import com.spicstome.client.shared.Album;
import com.spicstome.client.ui.widget.AlbumComposite;

public class AlbumViewImpl extends UserViewLayout  implements AlbumView{
	
	
	AlbumComposite albumComposite;
	
	public AlbumViewImpl()
	{
		
        albumComposite = new AlbumComposite(new Album());
        
        addMember(albumComposite);
	}
}
