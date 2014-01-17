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

	@Override
	public void save(final FolderDTO f) {
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

}
