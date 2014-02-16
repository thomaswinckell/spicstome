package com.spicstome.client.ui;

import com.smartgwt.client.widgets.IconButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.spicstome.client.dto.FolderDTO;
import com.spicstome.client.dto.StudentDTO;
import com.spicstome.client.ui.panel.AlbumBookPanel;
import com.spicstome.client.ui.panel.Book;
import com.spicstome.client.ui.panel.MenuRightPanel;
import com.spicstome.client.ui.widget.Crumb;
import com.spicstome.client.ui.widget.MailDropZone;
import com.spicstome.client.ui.widget.FolderTree.AlbumTreeNode;
import com.spicstome.client.ui.window.SearchWordWindow;


public class MailViewImpl extends UserViewImpl  implements MailView{
	
	
	AlbumBookPanel album;
	MailDropZone dropZone;
	MenuRightPanel menuRight;
	HLayout horizontalLayout = new HLayout();
	VLayout mailLayout = new VLayout();
	IconButton favoriteIcon;
	
	public MailViewImpl()
	{
		super();

		addCrumb(new Crumb("Mail"){});

        album = new AlbumBookPanel(new Book(100));  
    	dropZone = new MailDropZone(album.book.imageSize);
    	menuRight = new MenuRightPanel();
        
    	mailLayout.addMember(album);
    	mailLayout.addMember(dropZone);
    	
    	int iconsize = 60;
    	
    	IconButton help = new IconButton("Aide");
    	help.setIcon("aide.gif");
    	help.setIconSize(iconsize);
    	
    	IconButton search = new IconButton("Rechercher");
    	search.setIcon("chercher.gif");
    	search.setIconSize(iconsize);
    	
    	search.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				FolderDTO root = album.student.getAlbum().getFolder();
						
				SearchWordWindow searchWindow = new SearchWordWindow(root){
					@Override
					public void onDestroy()
					{	
						album.setFolderContent(this.selectedWord.getFolder());
						
						int nbPerPage = album.book.nbColPerPage*album.book.nbRowPerPage;
						int nbPerDoublePage = 2*(nbPerPage);
						int nPageLeft = 2*(selectedWord.getOrder()/nbPerDoublePage);
						int nInDoublePage = selectedWord.getOrder()-(nbPerPage*nPageLeft);

						album.folderTree.treeGrid.deselectAllRecords();
						AlbumTreeNode node = album.folderTree.getFolderNodeWithId(selectedWord.getFolder().getId());
						album.folderTree.treeGrid.selectRecord(node);
						album.folderTree.selectFolderNode=node;
						album.book.selectPage(nPageLeft);
						album.book.selectItem(nInDoublePage);
					}
				};
				
				searchWindow.show();
				
			}
		});
    	
    	favoriteIcon  = new IconButton("Favoris");
    	favoriteIcon.setIcon("favorite_off.png");
    	favoriteIcon.setIconSize(iconsize);
    	
    	favoriteIcon.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				album.favoriteFilter=!album.favoriteFilter;
				updateFavorite();
				if(album.getSelectedFolder()!=null)
					album.setFolderContent(album.getSelectedFolder());
			}
		});
    	
    	menuRight.addIcon(help);
    	menuRight.addIcon(search);
    	menuRight.addIcon(favoriteIcon);

    	
        horizontalLayout.addMember(mailLayout);
        horizontalLayout.addMember(menuRight);
        mainPanel.addMember(horizontalLayout);
	}

	public void updateFavorite()
	{
		favoriteIcon.setIcon((album.favoriteFilter?"favorite_on.png":"favorite_off.png"));
	}
	
	@Override
	public void setStudent(StudentDTO owner) {
		
		album.setStudent(owner);
		dropZone.init();
		updateFavorite();
    	menuRight.setIconsVisible(false);
		
	}
}
