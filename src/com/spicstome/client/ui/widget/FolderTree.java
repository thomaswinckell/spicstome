package com.spicstome.client.ui.widget;

import java.util.List;




import com.smartgwt.client.types.TreeModelType;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tree.Tree;
import com.smartgwt.client.widgets.tree.TreeGrid;
import com.smartgwt.client.widgets.tree.TreeGridField;
import com.smartgwt.client.widgets.tree.TreeNode;
import com.spicstome.client.dto.FolderDTO;

public class FolderTree extends VLayout
{
	
	public AlbumTreeNode selectFolderNode;
	public Tree tree;
	public TreeGrid treeGrid = new TreeGrid();
	
	public FolderTree() 
	{
		setWidth100();
		setHeight100();

		treeGrid.setShowOpenIcons(false);
		treeGrid.setShowDropIcons(false);
		treeGrid.setShowHeader(false);
		treeGrid.setShowEdges(false);

		TreeGridField field = new TreeGridField();
		field.setName("title");
		field.setTreeField(true);
		treeGrid.setFields(field);

		treeGrid.setIconSize(50);
		treeGrid.setWidth100();
		treeGrid.setHeight100();

		treeGrid.setStyleName("bloc");
		
		addMember(treeGrid);
	}
	
	public void setFolders(List<FolderDTO> folders)
	{
		selectFolderNode=null;
	
		tree = new Tree();
	    tree.setModelType(TreeModelType.PARENT);
	    tree.setIdField("id_folder");
	    tree.setParentIdField("parent");
	    tree.setTitleProperty("title");
	    tree.setIsFolderProperty("is_folder");
   
	    tree.setRootValue(1);
		
	    //parcours des dossier de l'album
	   
	    
	    TreeNode[] employeeData = new TreeNode[folders.size()];
	    

	    for(int i=0;i<folders.size();i++)
	    {
	    	FolderDTO f = folders.get(i);

	    	employeeData[i]= new AlbumTreeNode(f);
	    }
	    
	  

		tree.setData(employeeData);
		
		treeGrid.setData(tree);
		
		treeGrid.getData().openAll();
	}
	
	
	public void AllowReorder()
	{
		//treeGrid.setCanEdit(true); 

		treeGrid.setCanReorderRecords(true);  
		// treeGrid.setCanAcceptDroppedRecords(true); 
		treeGrid.setCanReparentNodes(true);

		treeGrid.setShowOpenIcons(false);  

	
	}
	
	
	public AlbumTreeNode getFolderNodeWithId(long id)
	{
		TreeNode[] nodes = tree.getAllNodes();
		
		for(TreeNode node:nodes)
		{
			AlbumTreeNode atn = (AlbumTreeNode)node;
			if(atn.getFolderDTO().getId()==id)
			{
				return atn;
			}
		}
		
		System.out.println("non trouvé");
		
		return null;
	}
	
	public static class AlbumTreeNode extends TreeNode 
	{
	    
	    public AlbumTreeNode(FolderDTO folder)
	    {
	    	String p;
	    	if(folder.getFolder()==null)
	    		p="-1";
	    	else
	    		p=folder.getFolder().getId().toString();
	    	
	    	setAttribute("id_folder", folder.getId().toString());
	    	setAttribute("parent", p);
	    	setAttribute("title", folder.getName());
	    	setAttribute("icon", "upload/"+folder.getImage().getFilename());
	    	setAttribute("is_folder", true);
	    	setAttribute("data",folder);
	    }
	    
	    public FolderDTO getFolderDTO()
	    {
	    	return (FolderDTO) getAttributeAsObject("data");
	    }
	}
	
	
}
