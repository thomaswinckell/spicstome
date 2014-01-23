package com.spicstome.client.ui.panel;

import java.util.ArrayList;
import java.util.List;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tree.events.NodeClickEvent;
import com.smartgwt.client.widgets.tree.events.NodeClickHandler;
import com.spicstome.client.dto.FolderDTO;
import com.spicstome.client.dto.PecsDTO;
import com.spicstome.client.dto.StudentDTO;
import com.spicstome.client.ui.widget.FolderTree;
import com.spicstome.client.ui.widget.FolderTree.AlbumTreeNode;

public abstract class AlbumPanel extends VLayout{

	protected HLayout titleLayout = new HLayout();
	protected Label title = new Label("");
	
	protected StudentDTO student;
		
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
		
		title.setHeight(30);
		titleLayout.addMember(title);
		

		setStyleName("bloc");
		verticalLayout.setHeight(300);
		verticalLayout.setWidth(400);
		verticalLayout.addMember(folderTree);

		horizontalLayout.addMember(verticalLayout);
		addMember(titleLayout);
		addMember(horizontalLayout);
	}
	
	public void setOwnerName(String name)
	{
		title.setContents("Album de "+name); 
	}
	
	public FolderDTO getSelectedFolder()
	{
		if(folderTree.selectFolderNode==null) return null;
		return (FolderDTO)folderTree.selectFolderNode.getFolderDTO();
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



	public void setStudent(StudentDTO student)
	{
		this.student=student;

		
		if(student.getAlbum().getId()==1)
			title.setContents("Album général");
		else if(student.getAlbum().getId()==2)
			title.setContents("Album exemple");
		else
			setOwnerName(student.getFirstName());
		
		if(student.getAlbum().getFolder()!=null)
		{
			List<FolderDTO> folders =  GetFoldersFolder(student.getAlbum().getFolder());
			
			this.folderTree.setFolders(folders);
		}
		else
		{
			this.folderTree.treeGrid.clear();
		}
		
	}

	public void onFolderClick(NodeClickEvent event)
	{
	
		folderTree.selectFolderNode = (AlbumTreeNode)event.getNode();		
		
	}
}




