package com.spicstome.client.ui;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import com.spicstome.client.dto.ArticleDTO;
import com.spicstome.client.dto.FolderDTO;
import com.spicstome.client.dto.ImageDTO;
import com.spicstome.client.dto.PecsDTO;
import com.spicstome.client.ui.panel.AlbumBookPanel;
import com.spicstome.client.ui.panel.Book;
import com.spicstome.client.ui.widget.Crumb;
import com.spicstome.client.ui.widget.DropZone;


public class MailViewImpl extends UserViewImpl  implements MailView{
	
	
	AlbumBookPanel album;
	DropZone dropZone;
	
	public MailViewImpl()
	{
		super();

		addCrumb(new Crumb("Mail"){});

        album = new AlbumBookPanel(new Book(100));
        

        FolderDTO f = new FolderDTO((long)0);
        f.setContent(new HashSet<PecsDTO>());
        f.setImage(new ImageDTO((long)0));
        f.getImage().setFilename("tout.png");
        f.setName("tout");
        ArticleDTO art = new ArticleDTO((long)0);
        art.setName("essai");
        f.getContent().add(art);
        List<FolderDTO> listFodler = new ArrayList<FolderDTO>();
        listFodler.add(f);
        album.folderTree.setFolders(listFodler);
        
    	dropZone = new DropZone(album.book.imageSize);
        
        mainPanel.addMember(album);
        mainPanel.addMember(dropZone);
	}
}
