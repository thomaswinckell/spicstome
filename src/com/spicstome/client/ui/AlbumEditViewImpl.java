package com.spicstome.client.ui;

import java.util.List;
import com.spicstome.client.dto.AlbumDTO;
import com.spicstome.client.dto.WordDTO;
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
			public void onSaveWord(WordDTO wordDTO) {
	
				((AlbumEditView.Presenter)(listener)).save(wordDTO);	

			}

			@Override
			public void onSaveFolder(FolderDTO folderDTO) {
	
				((AlbumEditView.Presenter)(listener)).save(folderDTO);				

			}

			@Override
			public void onDeleteWord(WordDTO word) {
				
				((AlbumEditView.Presenter)(listener)).delete(word);

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
			public void onUpdateWord(WordDTO word) {
				
				((AlbumEditView.Presenter)(listener)).update(word);
			}

			@Override
			public void onLoadFolder(FolderDTO folder) {
				
				((AlbumEditView.Presenter)(listener)).get(folder);
			}

			@Override
			public void onReorderWord(WordDTO word) {
				
				((AlbumEditView.Presenter)(listener)).reorder(word);
				
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
			public void onCopyWord(WordDTO article, FolderDTO parent) {
				((AlbumEditView.Presenter)(listener)).copy(article,parent);
				
			}

			@Override
			public void onMoveWord(WordDTO child, FolderDTO parent) {
				
				((AlbumEditView.Presenter)(listener)).move(child,parent);
				
			}
			
		};
		
		
		mainPanel.addMember(this.albumEditPanel);
	}


	@Override
	public void setStudent(StudentDTO student) {
		
		albumEditPanel.setStudent(student);
		
		if(student.getAlbum().getId()==1)
			crumb.setCrumbTitle("Album général (administration)");
		else if(student.getAlbum().getId()==2)
			crumb.setCrumbTitle("Album exemple (administration)");
		else
			crumb.setCrumbTitle("Album de "+student.getFirstName()+" (administration)");
		
	}

	@Override
	public void insertWord(WordDTO wordDTO) {
		
		albumEditPanel.insertWordIntoGrid(wordDTO);
	}

	@Override
	public void deleteWord(WordDTO wordDTO) {

		albumEditPanel.removeWordFromGrid(wordDTO);
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
	public void setAllStudents(List<StudentDTO> list) {
		albumEditPanel.setAllStudents(list);
		
	}
	
	


	@Override
	public void updateWord(FolderDTO folder) {

		albumEditPanel.updateWordIntoGrid(folder);
	}


	@Override
	public void updateFolder(FolderDTO folderDTO) {
		
		albumEditPanel.updateFolderIntoTree(folderDTO);
	}


	@Override
	public void updateAlbum(AlbumDTO album) {
		albumEditPanel.updateAlbum(album);
		
	}



	
	
	
	
	
	
	
}
