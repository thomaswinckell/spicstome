package com.spicstome.client.ui;

import java.util.Set;

import com.spicstome.client.dto.AlbumDTO;
import com.spicstome.client.dto.ArticleDTO;
import com.spicstome.client.dto.FolderDTO;
import com.spicstome.client.dto.StudentDTO;
import com.spicstome.client.place.AlbumManagementPlace;
import com.spicstome.client.ui.panel.AlbumEditPanel;
import com.spicstome.client.ui.widget.Crumb;

public class AlbumEditViewImpl extends UserViewImpl  implements AlbumEditView{
	
	
	AlbumEditPanel albumEditPanel;
	Crumb crumb;
	
	public AlbumEditViewImpl()
	{
		
		super();
		
		addCrumb(new Crumb("Les albums"){
			@Override
			public void onClickCrumb() {			
				goTo(new AlbumManagementPlace());
			}
		});
		
		crumb = new Crumb(""){
			@Override
			public void onClickCrumb() {}
		};
		
		addCrumb(crumb);
		
		this.albumEditPanel = new AlbumEditPanel(){

			@Override
			public void onSaveArticle(ArticleDTO articleDTO) {
	
				((AlbumEditView.Presenter)(listener)).save(articleDTO);	

			}

			@Override
			public void onSaveFolder(FolderDTO folderDTO) {
	
				((AlbumEditView.Presenter)(listener)).save(folderDTO);				

			}

			@Override
			public void onDeleteArticle(ArticleDTO a) {
				
				((AlbumEditView.Presenter)(listener)).delete(a);

			}

			@Override
			public void onDeleteFolder(FolderDTO f) {
				
				((AlbumEditView.Presenter)(listener)).delete(f);			
					
			}

			@Override
			public void onMoveFolder(FolderDTO child, FolderDTO parent) {
				
				((AlbumEditView.Presenter)(listener)).move(child,parent);
				
			}

			@Override
			public void onUpdateFolder(FolderDTO f) {
				
				((AlbumEditView.Presenter)(listener)).update(f);
				
			}

			@Override
			public void onUpdateArticle(ArticleDTO a) {
				
				((AlbumEditView.Presenter)(listener)).update(a);
			}

			@Override
			public void onLoadFolder(FolderDTO folder) {
				
				((AlbumEditView.Presenter)(listener)).get(folder);
			}

			@Override
			public void onReorderArticle(ArticleDTO article) {
				
				((AlbumEditView.Presenter)(listener)).reorder(article);
				
			}

			@Override
			public void onReorderFolder(FolderDTO folder) {
				
				((AlbumEditView.Presenter)(listener)).reorder(folder);
				
			}

			@Override
			public void onCopyFolder(FolderDTO folder,FolderDTO parent) {
				
				((AlbumEditView.Presenter)(listener)).copy(folder,parent);
				
			}

			@Override
			public void onCopyArticle(ArticleDTO article, FolderDTO parent) {
				((AlbumEditView.Presenter)(listener)).copy(article,parent);
				
			}

		
			
		};
		
		
		mainPanel.addMember(this.albumEditPanel);
	}


	@Override
	public void setAlbum(AlbumDTO album)
	{
		albumEditPanel.setAlbum(album);
	}

	@Override
	public void setOwner(String name) {
		
		albumEditPanel.comboBoxOwner.setValue(name);
		crumb.setCrumbTitle("Album de "+name+" (administration)");

	}

	@Override
	public void insertArticle(ArticleDTO articleDTO) {
		
		albumEditPanel.insertArticleIntoGrid(articleDTO);
	}

	@Override
	public void deleteArticle(ArticleDTO articleDTO) {

		albumEditPanel.removeArticleFromGrid(articleDTO);
	}


	@Override
	public void insertFolder(FolderDTO folder) {
		
		albumEditPanel.insertFolderIntoTree(folder);	
	}


	@Override
	public void deleteFolder(FolderDTO folder) {
		
		albumEditPanel.removeFolderFromTree(folder);		
	}


	@Override
	public void setOthersAlbum(Set<StudentDTO> list) {
		albumEditPanel.setOthersAlbum(list);
		
	}


	@Override
	public void updateArticle(FolderDTO folder) {

		albumEditPanel.updateArticleIntoGrid(folder);
	}


	@Override
	public void updateFolder(FolderDTO folderDTO) {
		
		albumEditPanel.updateFolderIntoTree(folderDTO);
	}

	
	
	
	
	
	
	
}
