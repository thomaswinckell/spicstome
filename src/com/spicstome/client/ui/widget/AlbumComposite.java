package com.spicstome.client.ui.widget;


import java.util.ArrayList;


import com.smartgwt.client.types.TreeModelType;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tree.Tree;
import com.smartgwt.client.widgets.tree.TreeGrid;
import com.smartgwt.client.widgets.tree.TreeNode;
import com.smartgwt.client.widgets.tree.events.NodeClickEvent;
import com.smartgwt.client.widgets.tree.events.NodeClickHandler;
import com.spicstome.client.shared.Album;

public class AlbumComposite extends VLayout{

	Album album;
	Book book;

	Label titre = new Label("");
	TreeGrid employeeTreeGrid = new TreeGrid();
	HLayout horizontalLayout = new HLayout();
	
	public AlbumComposite(Album album)
	{
		super();

		Tree employeeTree = new Tree();
	    employeeTree.setModelType(TreeModelType.PARENT);
	    employeeTree.setIdField("id_folder");
	    employeeTree.setParentIdField("parent");
	    employeeTree.setNameProperty("name");
	    
	    employeeTree.setRootValue(1);
	    
	    TreeNode[] employeeData = new TreeNode[] {
	  	      new AlbumTreeNode("2", "1", "Tout","tout.png"),
	  	      new AlbumTreeNode("3", "2", "Qui ?","qui.gif"),
	  	      new AlbumTreeNode("4", "2", "Quoi ?","quoi.gif"),
	  	      new AlbumTreeNode("5", "2", "Comment ?","comment.gif")
	  	      };
	    
	    employeeTree.setData(employeeData);

	    employeeTreeGrid.setWidth(300);
	    employeeTreeGrid.setHeight(200);
	    employeeTreeGrid.setShowOpenIcons(false);
	    employeeTreeGrid.setShowDropIcons(false);
	    employeeTreeGrid.setShowHeader(false);
	    employeeTreeGrid.setShowEdges(false);
	    
	    employeeTreeGrid.setData(employeeTree);
	    employeeTreeGrid.setIconSize(40);
	    employeeTreeGrid.getData().openAll();
	    
	    employeeTreeGrid.setStyleName("albumtree");

	    employeeTreeGrid.addNodeClickHandler(new NodeClickHandler() {
			
			@Override
			public void onNodeClick(NodeClickEvent event) {
				
				ArrayList<ImageRecord> articles = new ArrayList<ImageRecord>();
				
				int v = Integer.valueOf(event.getNode().getAttribute("id_folder"));
				
				switch(v)
				{
				case 3:
					articles.add(new ImageRecord(0,"Je","Je","albumlogo.png"));
					articles.add(new ImageRecord(0,"Tu","Tu","albumlogo.png"));
					articles.add(new ImageRecord(0,"Il","Il","albumlogo.png"));
					articles.add(new ImageRecord(0,"Nous","Je","albumlogo.png"));
					articles.add(new ImageRecord(0,"Vous","Tu","albumlogo.png"));
					articles.add(new ImageRecord(0,"Ils","Il","albumlogo.png"));
					articles.add(new ImageRecord(0,"Moi","Tu","albumlogo.png"));
					articles.add(new ImageRecord(0,"Toi","Il","albumlogo.png"));
					articles.add(new ImageRecord(0,"Autre","Il","albumlogo.png"));
				
					break;
				case 4:
					
				break;
				case 5:
					
				break;
				}
				
				book.setList(articles);
			}
		});
	    
	    
	    employeeTreeGrid.setBorder("");
	    
	    
	    book = new Book();
	    book.setList(new ArrayList<ImageRecord>());
	    
	    titre.setContents("Album de Albert");
	    
	    horizontalLayout.addMember(employeeTreeGrid);
	    horizontalLayout.addMember(book);
	    
	    addMember(titre);
		addMember(horizontalLayout);

	  }

	public static class AlbumTreeNode extends TreeNode {
	    public AlbumTreeNode(String id_folder, String parent, String name,String image) {
	      setAttribute("id_folder", id_folder);
	      setAttribute("parent", parent);
	      setAttribute("name", name);
	      setAttribute("icon", image);
	      
	      
	    }
	    
	  }

	
}




