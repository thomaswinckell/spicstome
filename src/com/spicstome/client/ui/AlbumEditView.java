package com.spicstome.client.ui;

import java.util.List;
import com.google.gwt.user.client.ui.IsWidget;
import com.spicstome.client.dto.AlbumDTO;
import com.spicstome.client.dto.ArticleDTO;
import com.spicstome.client.dto.FolderDTO;
import com.spicstome.client.dto.StudentDTO;

public interface AlbumEditView extends IsWidget{

	void setAllStudents(List<StudentDTO> list);
	void setStudent(StudentDTO owner);
	void insertFolder(FolderDTO folder);
	void deleteFolder(FolderDTO folder);
	void insertArticle(ArticleDTO articleDTO);
	void deleteArticle(ArticleDTO articleDTO);
	void updateArticle(FolderDTO folderDTO);
	void updateFolder(FolderDTO folderDTO);
	void updateAlbum(AlbumDTO album);
	
	public interface Presenter 
	{
		void reorder(ArticleDTO a);
		void reorder(FolderDTO f);
		void save(ArticleDTO a);
		void save(FolderDTO f);
		void copy(FolderDTO f,FolderDTO parent);
		void copy(ArticleDTO a,FolderDTO parent);
		void delete(ArticleDTO a);
		void delete(FolderDTO f);
		void move(FolderDTO child,FolderDTO parent);
		void move(ArticleDTO child,FolderDTO parent);
		void update(FolderDTO f);
		void update(ArticleDTO a);
		void get(FolderDTO f);
	}
}
