package com.spicstome.client.activity;

import java.util.ArrayList;
import java.util.List;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.spicstome.client.ClientFactory;
import com.spicstome.client.dto.AlbumDTO;
import com.spicstome.client.dto.ArticleDTO;
import com.spicstome.client.dto.FolderDTO;
import com.spicstome.client.dto.PecsDTO;
import com.spicstome.client.dto.ReferentDTO;
import com.spicstome.client.dto.StudentDTO;
import com.spicstome.client.place.AlbumEditPlace;
import com.spicstome.client.services.SpicsToMeServices;
import com.spicstome.client.ui.AlbumEditView;
import com.spicstome.client.ui.UserViewImpl;

public class AlbumEditActivity extends UserActivity implements AlbumEditView.Presenter{
	
	AlbumEditView editview;
	long idAlbum;

	public AlbumEditActivity(final AlbumEditPlace place, ClientFactory clientFactory) {
		super(place, clientFactory,(UserViewImpl)clientFactory.getAlbumEditView());

		this.editview = clientFactory.getAlbumEditView();
		this.idAlbum=place.idAlbum;
		
		
		if(idAlbum==1 || idAlbum==2)
		{
			SpicsToMeServices.Util.getInstance().getAlbum(place.idAlbum, new AsyncCallback<AlbumDTO>() {
				@Override
				public void onSuccess(AlbumDTO result) {
					
					StudentDTO falseStudent = new StudentDTO((long)-1);
					falseStudent.setAlbum(result);
					
					editview.setStudent(falseStudent);
				}

				@Override
				public void onFailure(Throwable caught) {}
			});
		}
		else
		{
			SpicsToMeServices.Util.getInstance().getAlbumOwner(place.idAlbum, new AsyncCallback<StudentDTO>(){

				@Override
				public void onFailure(Throwable caught) {}

				@Override
				public void onSuccess(StudentDTO result) {

					editview.setStudent(result);				
				}
				
			});
		}
		
			
		
		
		SpicsToMeServices.Util.getInstance().getGeneralAndExampleAlbum(new AsyncCallback<List<AlbumDTO>>() {

			@Override
			public void onFailure(Throwable caught) {}

			@Override
			public void onSuccess(List<AlbumDTO> result) {
				
				final List<StudentDTO> mergedListStudent = new ArrayList<StudentDTO>();
				for(AlbumDTO album:result)
				{
					if(album.getId()!=place.idAlbum)
					{
						StudentDTO falseStudent = new StudentDTO((long)-1);
						falseStudent.setAlbum(album);
						mergedListStudent.add(falseStudent);
					}
						
				}
				
				
				SpicsToMeServices.Util.getInstance().getReferentConnected( new AsyncCallback<ReferentDTO>() {
					
					@Override
					public void onFailure(Throwable caught) {}
					@Override
					public void onSuccess(ReferentDTO result) {
						

						for(StudentDTO student : result.getStudents())
						{
							if(student.getAlbum().getId()!=place.idAlbum)
								mergedListStudent.add(student);
							
						}
						
						editview.setAllStudents(mergedListStudent);
						
					}			
				});	
				
			}
		});
		
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) 
	{
		super.start(containerWidget, eventBus);
	}

	@Override
	public void save(final ArticleDTO a) {
		
		SpicsToMeServices.Util.getInstance().saveImage(a.getImage(), new AsyncCallback<Long>() {
			@Override
			public void onFailure(Throwable caught) {}
			@Override
			public void onSuccess(Long result) {
				
				a.getImage().setId(result);
							
				SpicsToMeServices.Util.getInstance().saveArticle(a, new AsyncCallback<Long>() {
					@Override
					public void onSuccess(Long result) {
						
						a.setId(result);
						a.getFolder().getContent().add(a);
						editview.insertArticle(a);
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
	public void delete(final ArticleDTO a) {
		
		SpicsToMeServices.Util.getInstance().deleteArticle(a.getId(), new AsyncCallback<Boolean>() {
			@Override
			public void onFailure(Throwable caught) {}
			@Override
			public void onSuccess(Boolean result) {
				
				a.getFolder().getContent().remove(a);
				editview.deleteArticle(a);
			}
		});
	}

	@Override
	public void delete(final FolderDTO f) {
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
	public void move(final ArticleDTO child, FolderDTO parent) {
		
		/* delete old linking */
		child.getFolder().getContent().remove(child);
		/* new linking */
		child.setFolder(parent);
		parent.getContent().add(child);
		
		SpicsToMeServices.Util.getInstance().updateArticle(child, new AsyncCallback<Boolean>() {

			@Override
			public void onFailure(Throwable caught) {}
			@Override
			public void onSuccess(Boolean result) {
				editview.deleteArticle(child);
			}
		});
		
	}

	@Override
	public void update(final FolderDTO f) {
		
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
	public void update(final ArticleDTO a) {
		
		SpicsToMeServices.Util.getInstance().updateImage(a.getImage(),new AsyncCallback<Long>() {

			@Override
			public void onFailure(Throwable caught) {}

			@Override
			public void onSuccess(Long result) {
				
				SpicsToMeServices.Util.getInstance().updateArticle(a, new AsyncCallback<Boolean>() {

					@Override
					public void onFailure(Throwable caught) {}
					@Override
					public void onSuccess(Boolean result) {
						SpicsToMeServices.Util.getInstance().getFolder(a.getFolder().getId(), new AsyncCallback<FolderDTO>() {

							@Override
							public void onFailure(Throwable caught) {}

							@Override
							public void onSuccess(FolderDTO result) {
								editview.updateArticle(result);		
								
							}
						});		
					}
				});
				
			}
		});
		
	
		
		
	}

	@Override
	public void get(FolderDTO f) {
		SpicsToMeServices.Util.getInstance().getFolder(f.getId(), new AsyncCallback<FolderDTO>() {

			@Override
			public void onFailure(Throwable caught) {}

			@Override
			public void onSuccess(FolderDTO result) {
				
				editview.updateArticle(result);			
			}
		});
		
	}

	@Override
	public void reorder(ArticleDTO a) {
		
		SpicsToMeServices.Util.getInstance().updateArticle(a, new AsyncCallback<Boolean>() {
			@Override
			public void onFailure(Throwable caught) {}
			@Override
			public void onSuccess(Boolean result) {}
		});
		
	}

	@Override
	public void reorder(FolderDTO f) {
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
	public void copy(ArticleDTO a, FolderDTO parent) {
		
		a.setOrder(parent.getContent().size());
		
		SpicsToMeServices.Util.getInstance().copyArticle(a,parent,new AsyncCallback<ArticleDTO>() {
			@Override
			public void onFailure(Throwable caught) {}
			
			@Override
			public void onSuccess(ArticleDTO result) {
				editview.insertArticle(result);
			}
		});
		
	}






}
