package com.spicstome.client.ui.widget;

import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tree.events.NodeClickEvent;
import com.smartgwt.client.widgets.tree.events.NodeClickHandler;
import com.spicstome.client.shared.Album;

public abstract class AlbumPanel extends VLayout{


	Album album;
	protected HLayout titleLayout = new HLayout();
		
	HLayout horizontalLayout = new HLayout();
	VLayout verticalLayout = new VLayout();
	FolderTree folderTree;

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

	public void setAlbum(Album album)
	{
		this.album = album;
		
		folderTree.setAlbum(album);
		
	}
	
	

	public boolean onFolderClick(NodeClickEvent event)
	{
		int newSelectedFolderId = Integer.valueOf(event.getNode().getAttribute("id_folder"));
		
		if(folderTree.selectFolderId != newSelectedFolderId)
		{
			folderTree.selectFolderId = newSelectedFolderId;
			folderTree.selectFolderNode = event.getNode();
			
			return true;
		}
		
		return false;
	}
}




