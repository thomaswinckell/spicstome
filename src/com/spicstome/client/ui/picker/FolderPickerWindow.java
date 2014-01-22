package com.spicstome.client.ui.picker;

import java.util.Set;
import com.smartgwt.client.widgets.tree.events.NodeClickEvent;
import com.spicstome.client.dto.StudentDTO;
import com.spicstome.client.ui.panel.AlbumPanel;

public class FolderPickerWindow extends PickerWindow{

	
	
	public FolderPickerWindow(Set<StudentDTO> list,Type type) {

		super(list,type, 500, 500);
		
	}
	
	@Override
	public void InitAlbumPanel()
	{
		
		if(type==Type.MOVE)
		{
			setTitle("Déplacer un article dans un dossier");
			validButton.setTitle("Déplacer dans ce dossier");
		}		
		else
		{
			setTitle("Importer un dossier depuis autre album");
			validButton.setTitle("Importer ce dossier");
		}
		
		albumPanel = new AlbumPanel() {
    		@Override
        	public void onFolderClick(NodeClickEvent event)
        	{
        		super.onFolderClick(event);
        		
        		UpdateValidButton();

        	}
		};
		
		
	}

	@Override
	public void UpdateValidButton()
	{
		validButton.setVisible(albumPanel.folderTree.selectFolderNode!=null);
	}
}
