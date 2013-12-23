package com.spicstome.client.ui;


import com.spicstome.client.shared.Album;
import com.spicstome.client.ui.widget.AlbumBookPanel;
import com.spicstome.client.ui.widget.Crumb;
import com.spicstome.client.ui.widget.ImageTileGrid;
import com.spicstome.client.ui.widget.ImageTileGrid.Mode;

public class MailViewImpl extends UserViewLayout  implements MailView{
	
	
	AlbumBookPanel album;
	ImageTileGrid dropZone;
	
	public MailViewImpl()
	{
		
		super();
		
		
		
		addCrumb(new Crumb("Mail"){});
		
		dropZone = new ImageTileGrid(Mode.DRAG_AND_DROP, 120, 120, 100){};
		
		dropZone.setWidth100();
		dropZone.setHeight(270);
		
		dropZone.setStyleName("album");
		
		dropZone.removeOnDragOver();
		
        album = new AlbumBookPanel(new Album());
        
        mainPanel.addMember(album);
        mainPanel.addMember(dropZone);
	}
}
