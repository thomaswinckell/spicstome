package com.spicstome.client.ui.widget;

import java.util.List;
import com.smartgwt.client.types.TreeModelType;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.tree.Tree;
import com.smartgwt.client.widgets.tree.TreeGrid;
import com.smartgwt.client.widgets.tree.TreeGridField;
import com.smartgwt.client.widgets.tree.TreeNode;
import com.spicstome.client.dto.FolderDTO;

public class FolderTree extends HLayout
{
	
	public int selectFolderId;
	public TreeNode selectFolderNode;
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

		treeGrid.setIconSize(30);
		treeGrid.setWidth100();
		treeGrid.setHeight100();

		//pas defini , ne sert qu'a masquer les bords
		treeGrid.setStyleName("album");
		
		addMember(treeGrid);
	}
	
	public void setFolders(List<FolderDTO> folders)
	{
		selectFolderId=-1;
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
	    	
	    	String p;
	    	if(f.getFolder()==null)
	    		p="-1";
	    	else
	    		p=f.getFolder().getId().toString();
	    	
	    	employeeData[i]= new AlbumTreeNode(f.getId().toString(),p , f.getName(),"tout.png",f);
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


		/*
			    treeGrid.addFolderDropHandler(new FolderDropHandler() {
			    	  @Override
			    	  public void onFolderDrop(FolderDropEvent folderDropEvent) {



			    		  Canvas src = folderDropEvent.getSourceWidget();
			    		  folderDropEvent.getNodes()[0].setAttribute("title", "prout");
			    	      //System.out.println("drop into folder"+src.);

			    	  }
			    	});*/

		/*
			    treeGrid.addDropHandler(new DropHandler() {

					@Override
					public void onDrop(DropEvent event) {

						System.out.println("drop ");
						System.out.println(event.getSource());
						if(event.getSource() instanceof ImageRecord)
						{
							System.out.println("drop ImageRecord");
						}

					}
				});*/
	}
	
	public static class AlbumTreeNode extends TreeNode 
	{
	    public AlbumTreeNode(String id_folder, String parent, String name,String image,FolderDTO f) 
	    {
	      setAttribute("id_folder", id_folder);
	      setAttribute("parent", parent);
	      setAttribute("title", name);
	      setAttribute("icon", image);
	      setAttribute("is_folder", true);
	      setAttribute("data",f);
	    }
	}
}
