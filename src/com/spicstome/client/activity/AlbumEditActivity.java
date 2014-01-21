package com.spicstome.client.activity;

import java.util.HashSet;
import java.util.Set;










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

	public AlbumEditActivity(final AlbumEditPlace place, ClientFactory clientFactory) {
		super(place, clientFactory,(UserViewImpl)clientFactory.getAlbumEditView());

		this.editview = clientFactory.getAlbumEditView();

		SpicsToMeServices.Util.getInstance().getAlbum(place.idAlbum, new AsyncCallback<AlbumDTO>() {
			@Override
			public void onFailure(Throwable caught) {}
			@Override
			public void onSuccess(AlbumDTO result) {
			

				editview.setAlbum(result);
				
				SpicsToMeServices.Util.getInstance().getAlbumOwner(result.getId(), new AsyncCallback<StudentDTO>() {
					@Override
					public void onSuccess(StudentDTO result) {
						editview.setOwner(result.getFirstName());
					}
					@Override
					public void onFailure(Throwable caught) {}			
				});	
				
				SpicsToMeServices.Util.getInstance().getReferentConnected( new AsyncCallback<ReferentDTO>() {
					
					@Override
					public void onFailure(Throwable caught) {}
					@Override
					public void onSuccess(ReferentDTO result) {
						Set<StudentDTO> listWithoutCurrent = new HashSet<StudentDTO>();
						for(StudentDTO student : result.getStudents())
						{
							if(student.getAlbum().getId()!=place.idAlbum)
								listWithoutCurrent.add(student);
						}
						
						editview.setOthersAlbum(listWithoutCurrent);
						
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
						
						for(PecsDTO pecs:f.getContent())
						{
							pecs.getFolder().setId(result);
							
							if(pecs instanceof ArticleDTO)
							{
								save((ArticleDTO)pecs);
							}
							else
							{
								save((FolderDTO)pecs);
							}
						}
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
		SpicsToMeServices.Util.getInstance().updateFolder(parent, new AsyncCallback<Boolean>() {

			@Override
			public void onFailure(Throwable caught) {}
			@Override
			public void onSuccess(Boolean result) {}
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
		SpicsToMeServices.Util.getInstance().updateFolder(f,new AsyncCallback<Boolean>() {

			@Override
			public void onFailure(Throwable caught) {}

			@Override
			public void onSuccess(Boolean result) {}
		});
		
	}

}
