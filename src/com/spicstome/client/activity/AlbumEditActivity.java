package com.spicstome.client.activity;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.spicstome.client.ClientFactory;
import com.spicstome.client.dto.AlbumDTO;
import com.spicstome.client.dto.UserDTO;
import com.spicstome.client.dto.WordDTO;
import com.spicstome.client.dto.FolderDTO;
import com.spicstome.client.dto.PecsDTO;
import com.spicstome.client.dto.ReferentDTO;
import com.spicstome.client.dto.StudentDTO;
import com.spicstome.client.place.AlbumEditPlace;
import com.spicstome.client.services.SpicsToMeServices;
import com.spicstome.client.ui.AlbumEditView;
import com.spicstome.client.ui.UserViewImpl;

/**
 * activity which load an album with a given id and allow to modify it.
 * Modification on folder, word, orders, import ...
 */
public class AlbumEditActivity extends UserActivity implements AlbumEditView.Presenter{
	
	AlbumEditView editview;
	long idAlbum;

	public AlbumEditActivity(final AlbumEditPlace place, ClientFactory clientFactory) {
		super(place, clientFactory,(UserViewImpl)clientFactory.getAlbumEditView());

		this.editview = clientFactory.getAlbumEditView();
		this.idAlbum=place.idAlbum;

	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) 
	{
		super.start(containerWidget, eventBus);
		
		//general and example case
		if(idAlbum==1 || idAlbum==2)
		{
			SpicsToMeServices.Util.getInstance().getAlbum(idAlbum, new AsyncCallback<AlbumDTO>() {
				@Override
				public void onSuccess(AlbumDTO result) {
					
					StudentDTO falseStudent = new StudentDTO((long)-1);
					falseStudent.setAlbum(result);
					
					editview.setStudent(falseStudent);
					userView.setIsLoading(false);
				}

				@Override
				public void onFailure(Throwable caught) {}
			});
		}
		else
		{
			//student case
			SpicsToMeServices.Util.getInstance().getAlbumOwner(idAlbum, new AsyncCallback<StudentDTO>(){

				@Override
				public void onFailure(Throwable caught) {}

				@Override
				public void onSuccess(StudentDTO result) {

					editview.setStudent(result);	
					userView.setIsLoading(false);
				}
				
			});
		}
		
			
		
		//creation of the list of "others album available".
		SpicsToMeServices.Util.getInstance().getGeneralAndExampleAlbum(new AsyncCallback<List<AlbumDTO>>() {

			@Override
			public void onFailure(Throwable caught) {}

			@Override
			public void onSuccess(List<AlbumDTO> result) {
				
				final List<StudentDTO> mergedListStudent = new ArrayList<StudentDTO>();
				for(AlbumDTO album:result)
				{
					if(album.getId()!=idAlbum)
					{
						StudentDTO falseStudent = new StudentDTO((long)-1);
						falseStudent.setAlbum(album);
						mergedListStudent.add(falseStudent);
					}
						
				}
				
				//combination with the other student
				SpicsToMeServices.Util.getInstance().getCurrentUser( new AsyncCallback<UserDTO>() {
					
					@Override
					public void onFailure(Throwable caught) {}
					@Override
					public void onSuccess(UserDTO result) {
	
						if(result instanceof ReferentDTO)
						{
							ReferentDTO referent = (ReferentDTO)result;
							
							for(StudentDTO student : referent.getStudents())
							{
								if(student.getAlbum().getId()!=idAlbum)
									mergedListStudent.add(student);
							}		
							editview.setAllStudents(mergedListStudent);
						}

					}			
				});	
				
			}
		});
		
	}

	@Override
	public void save(final WordDTO wordDTO) {
		//save word
		SpicsToMeServices.Util.getInstance().saveImage(wordDTO.getImage(), new AsyncCallback<Long>() {
			@Override
			public void onFailure(Throwable caught) {}
			@Override
			public void onSuccess(Long result) {
				
				wordDTO.getImage().setId(result);
							
				SpicsToMeServices.Util.getInstance().saveWord(wordDTO, new AsyncCallback<Long>() {
					@Override
					public void onSuccess(Long result) {
						
						wordDTO.setId(result);
						wordDTO.getFolder().getContent().add(wordDTO);
						editview.insertWord(wordDTO);
					}			
					@Override
					public void onFailure(Throwable caught) {}
				});
			}
		});	
		
	}

	@Override
	public void save(final FolderDTO f) {

		/* save folder */
		SpicsToMeServices.Util.getInstance().saveImage(f.getImage(), new AsyncCallback<Long>() {
			@Override
			public void onFailure(Throwable caught) {}
			@Override
			public void onSuccess(Long result) {
				
				f.getImage().setId(result);
				
				SpicsToMeServices.Util.getInstance().saveFolder(f,new AsyncCallback<Long>() {
					@Override
					public void onFailure(Throwable caught) {}
					@Override
					public void onSuccess(Long result) {
						
						f.setId(result);
						f.getFolder().getContent().add(f);
						editview.insertFolder(f);

					}
				});
			}
		});

		
	}

	@Override
	public void delete(final WordDTO word) {
		//delete word
		SpicsToMeServices.Util.getInstance().deleteWord(word.getId(), new AsyncCallback<Boolean>() {
			@Override
			public void onFailure(Throwable caught) {}
			@Override
			public void onSuccess(Boolean result) {
				
				word.getFolder().getContent().remove(word);
				editview.deleteWord(word);
			}
		});
	}

	@Override
	public void delete(final FolderDTO f) {
		//delete folder
		SpicsToMeServices.Util.getInstance().deleteFolder(f.getId(), new AsyncCallback<Boolean>() {
			@Override
			public void onFailure(Throwable caught) {}
			@Override
			public void onSuccess(Boolean result) {
				
				f.getFolder().getContent().remove(f);
				editview.deleteFolder(f);
			}
		});
		
	}

	@Override
	public void move(FolderDTO child, FolderDTO parent) {
		
		/* delete old linking */
		child.getFolder().getContent().remove(child);
		/* new linking */
		child.setFolder(parent);
		parent.getContent().add(child);
		
		SpicsToMeServices.Util.getInstance().updateFolder(child, new AsyncCallback<Boolean>() {

			@Override
			public void onFailure(Throwable caught) {}
			@Override
			public void onSuccess(Boolean result) {}
		});
		
	}
	
	@Override
	public void move(final WordDTO child, FolderDTO parent) {
		
		/* delete old linking */
		child.getFolder().getContent().remove(child);
		/* new linking */
		child.setFolder(parent);
		parent.getContent().add(child);
		child.setOrder(parent.getContent().size());
		
		SpicsToMeServices.Util.getInstance().updateWord(child, new AsyncCallback<Boolean>() {

			@Override
			public void onFailure(Throwable caught) {}
			@Override
			public void onSuccess(Boolean result) {
				editview.deleteWord(child);
			}
		});
		
	}

	@Override
	public void update(final FolderDTO f) {
		//update a folder (+image)
		SpicsToMeServices.Util.getInstance().updateImage(f.getImage(), new AsyncCallback<Long>() {

			@Override
			public void onFailure(Throwable caught) {}

			@Override
			public void onSuccess(Long result) {
				
				SpicsToMeServices.Util.getInstance().updateFolder(f, new AsyncCallback<Boolean>() {

					@Override
					public void onFailure(Throwable caught) {}
					@Override
					public void onSuccess(Boolean result) {
						
						editview.updateFolder(f);
						
						UpdateStudentDTO();
					}
				});			
			}		
		});	
	}

	@Override
	public void update(final WordDTO word) {
		//update word (+image)
		SpicsToMeServices.Util.getInstance().updateImage(word.getImage(),new AsyncCallback<Long>() {

			@Override
			public void onFailure(Throwable caught) {}

			@Override
			public void onSuccess(Long result) {
				
				SpicsToMeServices.Util.getInstance().updateWord(word, new AsyncCallback<Boolean>() {

					@Override
					public void onFailure(Throwable caught) {}
					@Override
					public void onSuccess(Boolean result) {
						SpicsToMeServices.Util.getInstance().getFolder(word.getFolder().getId(), new AsyncCallback<FolderDTO>() {

							@Override
							public void onFailure(Throwable caught) {}

							@Override
							public void onSuccess(FolderDTO result) {
								editview.updateWord(result);		
								
							}
						});		
					}
				});
				
			}
		});
		
	
		
		
	}

	@Override
	public void get(FolderDTO f) {
		//get the whole folder
		SpicsToMeServices.Util.getInstance().getFolder(f.getId(), new AsyncCallback<FolderDTO>() {

			@Override
			public void onFailure(Throwable caught) {}

			@Override
			public void onSuccess(FolderDTO result) {
				
				editview.updateWord(result);			
			}
		});
		
	}

	@Override
	public void reorder(WordDTO wordDTO) {
		//reorder one word
		SpicsToMeServices.Util.getInstance().updateWord(wordDTO, new AsyncCallback<Boolean>() {
			@Override
			public void onFailure(Throwable caught) {}
			@Override
			public void onSuccess(Boolean result) {}
		});
		
	}

	@Override
	public void reorder(FolderDTO f) {
		//reorder one folder
		SpicsToMeServices.Util.getInstance().updateFolderAndChild(f,new AsyncCallback<Boolean>() {

			@Override
			public void onFailure(Throwable caught) {}

			@Override
			public void onSuccess(Boolean result) {
				UpdateStudentDTO();
			}
		});
		
	}
	
	public void InsertFolder(FolderDTO f)
	{
		//insert one folder into the view
		editview.insertFolder(f);
		
		for(PecsDTO pecs:f.getContent())
		{
			if(pecs instanceof FolderDTO)
			{
				InsertFolder((FolderDTO)pecs);
			}
		}
	}

	
	@Override
	public void copy(final FolderDTO f,FolderDTO parent) {
		
		if(f.getFolder()==null)
		{
			/* importing all the folders of the root */
			
			for(PecsDTO pecs:f.getContent())
			{
				if(pecs instanceof FolderDTO)
				{
					SpicsToMeServices.Util.getInstance().copyFolder((FolderDTO)pecs,parent,new AsyncCallback<FolderDTO>() {
						@Override
						public void onFailure(Throwable caught) {}
						
						@Override
						public void onSuccess(FolderDTO result) {
							
							InsertFolder(result);
							
							UpdateStudentDTO();
						}
					});
				}			
			}
		}
		else
		{
			f.setOrder(parent.getContent().size());
			
			SpicsToMeServices.Util.getInstance().copyFolder(f,parent,new AsyncCallback<FolderDTO>() {
				@Override
				public void onFailure(Throwable caught) {}
				
				@Override
				public void onSuccess(FolderDTO result) {
					
					InsertFolder(result);
					UpdateStudentDTO();
				}
			});

		}
				
		
	}
	
	public void UpdateStudentDTO()
	{
		/* album update */
		SpicsToMeServices.Util.getInstance().getAlbum(idAlbum, new AsyncCallback<AlbumDTO>() {
			@Override
			public void onSuccess(AlbumDTO result) {
				
				editview.updateAlbum(result);
			}
			@Override
			public void onFailure(Throwable caught) {}			
		});
	}

	@Override
	public void copy(WordDTO word, FolderDTO parent) {
		//copy a word
		parent.getContent().add(word);
		word.setOrder(parent.getContent().size());
	
		SpicsToMeServices.Util.getInstance().copyWord(word,parent,new AsyncCallback<WordDTO>() {
			@Override
			public void onFailure(Throwable caught) {}
			
			@Override
			public void onSuccess(WordDTO result) {
				editview.insertWord(result);
			}
		});
		
	}






}
