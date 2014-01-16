package com.spicstome.client.ui.panel;

import java.util.ArrayList;
import java.util.List;

import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tree.events.NodeClickEvent;
import com.smartgwt.client.widgets.tree.events.NodeClickHandler;
import com.spicstome.client.dto.AlbumDTO;
import com.spicstome.client.dto.FolderDTO;
import com.spicstome.client.dto.PecsDTO;
import com.spicstome.client.ui.widget.FolderTree;

public abstract class AlbumPanel extends VLayout{

	protected HLayout titleLayout = new HLayout();
		
	HLayout horizontalLayout = new HLayout();
	VLayout verticalLayout = new VLayout();
	public FolderTree folderTree;

	public AlbumPanel()
	{
		super();


		folderTree = new FolderTree();

		folderTree.treeGrid.addNodeClickHandler(new NodeClickHandler() {	
			@Override
			public void onNodeClick(NodeClickEvent event) {
				onFolderClick(event);
			}
		});


		setStyleName("album");
		verticalLayout.setHeight(300);
		verticalLayout.setWidth(300);
		verticalLayout.addMember(folderTree);

		horizontalLayout.addMember(verticalLayout);
		addMember(titleLayout);
		addMember(horizontalLayout);
	}
	
	private List<FolderDTO> GetFoldersFolder(FolderDTO folder)
	{
		List<FolderDTO> res = new ArrayList<FolderDTO>();
		
		res.add(folder);

		for(PecsDTO p:folder.getContent())
		{
			if(p instanceof FolderDTO)
			{
				res.addAll(GetFoldersFolder((FolderDTO)p));
				
			}
		}	
		
		return res;
	}


	public void setAlbum(AlbumDTO album)
	{
		List<FolderDTO> folders =  GetFoldersFolder(album.getFolder());
		
		this.folderTree.setFolders(folders);
	}

	public void onFolderClick(NodeClickEvent event)
	{
	
		folderTree.selectFolderNode = event.getNode();		
		
	}
}




