package com.spicstome.client.ui;

import java.util.List;

import com.spicstome.client.dto.AlbumDTO;
import com.spicstome.client.dto.FolderDTO;
import com.spicstome.client.place.AlbumManagementPlace;
import com.spicstome.client.ui.panel.AlbumBookPanel;
import com.spicstome.client.ui.panel.Book;
import com.spicstome.client.ui.widget.Crumb;

public class AlbumViewImpl extends UserViewImpl  implements AlbumView{
	
	
	AlbumBookPanel albumBookPanel;
	
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

		
		this.albumBookPanel = new AlbumBookPanel(new Book(100));
		
		mainPanel.addMember(this.albumBookPanel);
	}

	@Override
	public void setAlbum(AlbumDTO a) {
	
		albumBookPanel.setAlbum(a);
		
	}

	@Override
	public void setFolders(List<FolderDTO> folders) {
		this.albumBookPanel.folderTree.setFolders(folders);
		
	}
}
