package com.spicstome.client.ui;

import java.util.List;
import com.google.gwt.user.client.ui.IsWidget;
import com.spicstome.client.dto.AlbumDTO;
import com.spicstome.client.dto.WordDTO;
import com.spicstome.client.dto.FolderDTO;
import com.spicstome.client.dto.StudentDTO;

public interface AlbumEditView extends IsWidget{

	void setAllStudents(List<StudentDTO> list);
	void setStudent(StudentDTO owner);
	void insertFolder(FolderDTO folder);
	void deleteFolder(FolderDTO folder);
	void insertWord(WordDTO wordDTO);
	void deleteWord(WordDTO wordDTO);
	void updateWord(FolderDTO folderDTO);
	void updateFolder(FolderDTO folderDTO);
	void updateAlbum(AlbumDTO album);
	
	public interface Presenter 
	{
		void reorder(WordDTO a);
		void reorder(FolderDTO f);
		void save(WordDTO a);
		void save(FolderDTO f);
		void copy(FolderDTO f,FolderDTO parent);
		void copy(WordDTO a,FolderDTO parent);
		void delete(WordDTO word);
		void delete(FolderDTO f);
		void move(FolderDTO child,FolderDTO parent);
		void move(WordDTO child,FolderDTO parent);
		void update(FolderDTO f);
		void update(WordDTO word);
		void get(FolderDTO f);
	}
}
