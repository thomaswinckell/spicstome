package com.spicstome.client.ui;

import java.util.List;

import com.spicstome.client.dto.AlbumDTO;
import com.spicstome.client.dto.FolderDTO;
import com.spicstome.client.place.AlbumManagementPlace;
import com.spicstome.client.ui.panel.AlbumEditPanel;
import com.spicstome.client.ui.widget.Crumb;

public class AlbumEditViewImpl extends UserViewLayout  implements AlbumEditView{
	
	
	AlbumEditPanel albumEditPanel;

	
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

		
		this.albumEditPanel = new AlbumEditPanel(){

			@Override
			public void onValidateChange() 
			{
				
				((AlbumEditView.Presenter)(listener)).save();
				listener.goTo(new AlbumManagementPlace());
			}
			
		};
		
		
		mainPanel.addMember(this.albumEditPanel);
	}

	@Override
	public void setAlbum(AlbumDTO a) {
		
		this.albumEditPanel.setAlbum(a);

	}

	@Override
	public void setFolders(List<FolderDTO> folders) {
		
		this.albumEditPanel.folderTree.setFolders(folders);
		
	}

	@Override
	public void setOwner(String name) {
		
		albumEditPanel.comboBoxOwner.setValue(name);
		
	}

	
	
	
	
	
	
	
}
