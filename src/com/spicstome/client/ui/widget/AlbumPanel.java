package com.spicstome.client.ui.widget;

import com.smartgwt.client.types.TreeModelType;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tree.Tree;
import com.smartgwt.client.widgets.tree.TreeGrid;
import com.smartgwt.client.widgets.tree.TreeGridField;
import com.smartgwt.client.widgets.tree.TreeNode;
import com.smartgwt.client.widgets.tree.events.NodeClickEvent;
import com.smartgwt.client.widgets.tree.events.NodeClickHandler;
import com.spicstome.client.shared.Album;

public abstract class AlbumPanel extends VLayout{


	Album album;
	protected HLayout titleLayout = new HLayout();
	
	TreeGrid treeGrid = new TreeGrid();
	HLayout horizontalLayout = new HLayout();
	VLayout verticalLayout = new VLayout();
	int selectFolderId;
	TreeNode selectFolderNode;
	Tree tree;

	public AlbumPanel()
	{
		super();


	    treeGrid.setShowOpenIcons(false);
	    treeGrid.setShowDropIcons(false);
	    treeGrid.setShowHeader(false);
	    treeGrid.setShowEdges(false);
	  
	    TreeGridField field = new TreeGridField();
	    field.setName("title");
	    field.setTreeField(true);
	    treeGrid.setFields(field);

	    treeGrid.setIconSize(30);
	    treeGrid.setWidth100();
	    treeGrid.setHeight100();
	    
	    //pas defini , ne sert qu'a masquer les bords
	    treeGrid.setStyleName("album");

	    treeGrid.addNodeClickHandler(new NodeClickHandler() {	
			@Override
			public void onNodeClick(NodeClickEvent event) {
					onFolderClick(event);
				}
		});
	    

	    setStyleName("album");
	    
	    verticalLayout.setWidth(350);
	    
	    
	   
	    verticalLayout.addMember(treeGrid);

	    horizontalLayout.addMember(verticalLayout);
	    addMember(titleLayout);
		addMember(horizontalLayout);
	  }

	public void setAlbum(Album album)
	{
		
		System.out.println("setAlbum Panel");
		this.album = album;
		
		selectFolderId=-1;
		selectFolderNode=null;
	
		tree = new Tree();
	    tree.setModelType(TreeModelType.PARENT);
	    tree.setIdField("id_folder");
	    tree.setParentIdField("parent");
	    tree.setTitleProperty("title");
	    tree.setIsFolderProperty("is_folder");
   
	    tree.setRootValue(1);
		
		TreeNode[] employeeData = new TreeNode[] {
				new AlbumTreeNode("2", "1", "Tout","tout.png"),
				new AlbumTreeNode("3", "2", "Qui ?","qui.gif"),
				new AlbumTreeNode("4", "2", "Quoi ?","quoi.gif"),
				new AlbumTreeNode("5", "2", "Comment ?","comment.gif")
		};

		tree.setData(employeeData);
		
		treeGrid.setData(tree);
		
		treeGrid.getData().openAll();
	}
	
	public static class AlbumTreeNode extends TreeNode 
	{
	    public AlbumTreeNode(String id_folder, String parent, String name,String image) 
	    {
	      setAttribute("id_folder", id_folder);
	      setAttribute("parent", parent);
	      setAttribute("title", name);
	      setAttribute("icon", image);
	      setAttribute("is_folder", true);
	    }
	}

	public boolean onFolderClick(NodeClickEvent event)
	{
		int newSelectedFolderId = Integer.valueOf(event.getNode().getAttribute("id_folder"));
		
		if(selectFolderId != newSelectedFolderId)
		{
			selectFolderId = newSelectedFolderId;
			selectFolderNode = event.getNode();
			
			return true;
		}
		
		return false;
	}
}




