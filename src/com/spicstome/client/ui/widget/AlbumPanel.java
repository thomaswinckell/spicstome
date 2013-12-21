package com.spicstome.client.ui.widget;


import java.util.ArrayList;


import com.smartgwt.client.types.TreeModelType;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tree.Tree;
import com.smartgwt.client.widgets.tree.TreeGrid;
import com.smartgwt.client.widgets.tree.TreeNode;
import com.smartgwt.client.widgets.tree.events.DataArrivedEvent;
import com.smartgwt.client.widgets.tree.events.DataArrivedHandler;
import com.smartgwt.client.widgets.tree.events.NodeClickEvent;
import com.smartgwt.client.widgets.tree.events.NodeClickHandler;
import com.spicstome.client.shared.Album;

public class AlbumPanel extends VLayout{

	Album album;
	Book book;

	Label titre = new Label();
	TreeGrid treeGrid = new TreeGrid();
	HLayout horizontalLayout = new HLayout();
	VLayout verticalLayout = new VLayout();

	int selectFolderId=-1;
	TreeNode selectFolderNode;
	
	Tree tree;
	
	ActionPanel actionFoldersPanel = new ActionPanel();
	
	public AlbumPanel(Album album)
	{
		super();
		
		this.setHeight(350);
		

		tree = new Tree();
	    tree.setModelType(TreeModelType.PARENT);
	    tree.setIdField("id_folder");
	    tree.setParentIdField("parent");
	    tree.setNameProperty("name");
	    
	    tree.setRootValue(1);
	    
	    TreeNode[] employeeData = new TreeNode[] {
	  	      new AlbumTreeNode("2", "1", "Tout","tout.png"),
	  	      new AlbumTreeNode("3", "2", "Qui ?","qui.gif"),
	  	      new AlbumTreeNode("4", "2", "Quoi ?","quoi.gif"),
	  	      new AlbumTreeNode("5", "2", "Comment ?","comment.gif")
	  	      };
	    
	    tree.setData(employeeData);


	    treeGrid.setShowOpenIcons(false);
	    treeGrid.setShowDropIcons(false);
	    treeGrid.setShowHeader(false);
	    treeGrid.setShowEdges(false);
	    
	    
	    //EDIT
	    treeGrid.setCanEdit(true);  
	    
	    treeGrid.setData(tree);
	    treeGrid.setIconSize(30);
	    
	    treeGrid.setWidth100();
	    
	    treeGrid.getData().openAll();
	    
	    //pas défini , ne sert qu'a masquer les bords
	    treeGrid.setStyleName("albumtree");
	    
	    treeGrid.addDataArrivedHandler(new DataArrivedHandler() {
	        public void onDataArrived(DataArrivedEvent event) {
	            treeGrid.getData().openAll();
	        }
	    });

	    treeGrid.addNodeClickHandler(new NodeClickHandler() {
			
			@Override
			public void onNodeClick(NodeClickEvent event) {
				
				ArrayList<ImageRecord> articles = new ArrayList<ImageRecord>();
				
				selectFolderId = Integer.valueOf(event.getNode().getAttribute("id_folder"));
				selectFolderNode = event.getNode();
				
				switch(selectFolderId)
				{
				case 3:
					articles.add(new ImageRecord(0,"Je","albumlogo.png"));
					articles.add(new ImageRecord(0,"Tu","albumlogo.png"));
					articles.add(new ImageRecord(0,"Il","albumlogo.png"));
					articles.add(new ImageRecord(0,"Nous","albumlogo.png"));
					articles.add(new ImageRecord(0,"Vous","albumlogo.png"));
					articles.add(new ImageRecord(0,"Ils","albumlogo.png"));
					articles.add(new ImageRecord(0,"Moi","albumlogo.png"));
					articles.add(new ImageRecord(0,"Toi","albumlogo.png"));
					articles.add(new ImageRecord(0,"Autre","albumlogo.png"));
				
					break;
				case 4:
					
				break;
				case 5:
					
				break;
				}
				
				actionFoldersPanel.setActionVisible(true);
				
				book.setList(articles);
			}
		});
	    
	   
	    
	    setStyleName("album");
	    
	    book = new Book();
	    book.setList(new ArrayList<ImageRecord>());
	    
	    titre.setContents("Album de Albert");
	    
	    titre.setHeight(30);
	    
	    verticalLayout.setWidth(300);
	    
	    
	    actionFoldersPanel.getButtonNew().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				if(selectFolderNode!=null)
				{
					tree.add(new AlbumTreeNode("42", selectFolderNode.getAttribute("id_folder"), "Foo","tout.png"), selectFolderNode);
					treeGrid.setData(tree);
				}
			
			}
		});
	    
	    verticalLayout.addMember(treeGrid);
	    verticalLayout.addMember(actionFoldersPanel);
	    
	    horizontalLayout.addMember(verticalLayout);
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




