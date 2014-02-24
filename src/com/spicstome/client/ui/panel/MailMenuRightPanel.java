package com.spicstome.client.ui.panel;

import com.smartgwt.client.widgets.IconButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.spicstome.client.dto.FolderDTO;
import com.spicstome.client.dto.PecsDTO;
import com.spicstome.client.dto.WordDTO;
import com.spicstome.client.ui.NewMailViewImpl;
import com.spicstome.client.ui.widget.FolderTree.AlbumTreeNode;
import com.spicstome.client.ui.window.SearchWordWindow;

public class MailMenuRightPanel extends MenuRightPanel{
	
	NewMailViewImpl mailView;
	IconButton favoriteIcon;

	public MailMenuRightPanel(final NewMailViewImpl mailView)
	{
		super();
		
		this.mailView=mailView;
		
		IconButton help = new IconButton("Aide");
		help.setIcon("aide.gif");
		help.setIconSize(iconsize);
		
		IconButton expand = new IconButton("Plein écran");
		expand.setIcon("expand.png");
		expand.setIconSize(iconsize);
		
		expand.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				mailView.expand(!mailView.expanded);
				
			}
		});

		IconButton search = new IconButton("Rechercher");
		search.setIcon("chercher.gif");
		search.setIconSize(iconsize);

		search.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				FolderDTO root = mailView.album.student.getAlbum().getFolder();

				SearchWordWindow searchWindow = new SearchWordWindow(root){
					@Override
					public void onDestroy()
					{	
						mailView.album.setFolderContent(this.selectedWord.getFolder());

						int nbPerPage = mailView.album.book.nbColPerPage*mailView.album.book.nbRowPerPage;
						int nbPerDoublePage = 2*(nbPerPage);
						int orderInBook = getOrderInBook(nbPerDoublePage, selectedWord);
						int nPageLeft = 2*(orderInBook/nbPerDoublePage);
						int nInDoublePage = orderInBook-(nbPerPage*nPageLeft);

						mailView.album.folderTree.treeGrid.deselectAllRecords();
						AlbumTreeNode node = mailView.album.folderTree.getFolderNodeWithId(selectedWord.getFolder().getId());
						mailView.album.folderTree.treeGrid.selectRecord(node);
						mailView.album.folderTree.selectFolderNode=node;
						mailView.album.book.selectPage(nPageLeft);
						mailView.album.book.selectItem(nInDoublePage);
					}

					public int getOrderInBook(int nbPerPage,WordDTO word)
					{
						int n=0;

						for(PecsDTO pecs:word.getFolder().getContent())
						{
							if(pecs instanceof WordDTO)
							{
								WordDTO w = (WordDTO)pecs;
								if(w.getOrder()<word.getOrder())
									n++;
							}
						}

						return n;

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
				mailView.album.favoriteFilter=!mailView.album.favoriteFilter;
				updateFavorite();
				if(mailView.album.getSelectedFolder()!=null)
					mailView.album.setFolderContent(mailView.album.getSelectedFolder());
			}
		});

		addIcon(help);
		addIcon(search);
		addIcon(favoriteIcon);
		addIcon(expand);

	}

	public void updateFavorite()
	{
		favoriteIcon.setIcon((mailView.album.favoriteFilter?"favorite_on.png":"favorite_off.png"));
	}
}
