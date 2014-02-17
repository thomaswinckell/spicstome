package com.spicstome.client.ui.panel;

import com.smartgwt.client.widgets.IconButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.spicstome.client.dto.FolderDTO;
import com.spicstome.client.dto.PecsDTO;
import com.spicstome.client.dto.WordDTO;
import com.spicstome.client.ui.widget.FolderTree.AlbumTreeNode;
import com.spicstome.client.ui.window.SearchWordWindow;

public class MailMenuRightPanel extends MenuRightPanel{
	
	AlbumBookPanel album;
	IconButton favoriteIcon;

	public MailMenuRightPanel(final AlbumBookPanel album)
	{
		super();
		
		this.album=album;
		
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
						int orderInBook = getOrderInBook(nbPerDoublePage, selectedWord);
						int nPageLeft = 2*(orderInBook/nbPerDoublePage);
						int nInDoublePage = orderInBook-(nbPerPage*nPageLeft);

						album.folderTree.treeGrid.deselectAllRecords();
						AlbumTreeNode node = album.folderTree.getFolderNodeWithId(selectedWord.getFolder().getId());
						album.folderTree.treeGrid.selectRecord(node);
						album.folderTree.selectFolderNode=node;
						album.book.selectPage(nPageLeft);
						album.book.selectItem(nInDoublePage);
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
				album.favoriteFilter=!album.favoriteFilter;
				updateFavorite();
				if(album.getSelectedFolder()!=null)
					album.setFolderContent(album.getSelectedFolder());
			}
		});

		addIcon(help);
		addIcon(search);
		addIcon(favoriteIcon);

	}

	public void updateFavorite()
	{
		favoriteIcon.setIcon((album.favoriteFilter?"favorite_on.png":"favorite_off.png"));
	}
}
