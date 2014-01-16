package com.spicstome.client.ui.picker;

import java.util.ArrayList;

import com.smartgwt.client.widgets.tree.events.NodeClickEvent;
import com.spicstome.client.dto.AlbumDTO;
import com.spicstome.client.ui.panel.AlbumPanel;

public class FolderPickerWindow extends PickerWindow{

	public FolderPickerWindow(ArrayList<AlbumDTO> listAlbum) {
		super(listAlbum, 400, 500);
		
	}
	@Override
	public void InitAlbumPanel()
	{
		albumPanel = new AlbumPanel() {
    		@Override
        	public void onFolderClick(NodeClickEvent event)
        	{
        		super.onFolderClick(event);
        		
        		UpdateValidButton();

        	}
		};
		
		validButton.setTitle("Importer ce dossier");
	}

	@Override
	public void UpdateValidButton()
	{
		validButton.setVisible(albumPanel.folderTree.selectFolderNode!=null);
	}
}
