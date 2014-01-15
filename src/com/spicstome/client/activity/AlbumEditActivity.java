package com.spicstome.client.activity;

import java.util.List;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.spicstome.client.ClientFactory;
import com.spicstome.client.dto.AlbumDTO;
import com.spicstome.client.dto.ArticleDTO;
import com.spicstome.client.dto.FolderDTO;
import com.spicstome.client.dto.StudentDTO;
import com.spicstome.client.place.AlbumEditPlace;
import com.spicstome.client.services.SpicsToMeServices;
import com.spicstome.client.ui.AlbumEditView;
import com.spicstome.client.ui.UserViewImpl;

public class AlbumEditActivity extends UserActivity implements AlbumEditView.Presenter{
	
	AlbumEditView editview;
	AlbumDTO album;
	

	public AlbumEditActivity(AlbumEditPlace place, ClientFactory clientFactory) {
		super(place, clientFactory,(UserViewImpl)clientFactory.getAlbumEditView());

		this.editview = clientFactory.getAlbumEditView();

		SpicsToMeServices.Util.getInstance().getAlbum(place.idAlbum, new AsyncCallback<AlbumDTO>() {

			@Override
			public void onFailure(Throwable caught) {
				
			}

			@Override
			public void onSuccess(AlbumDTO result) {
				album=result;
				editview.setAlbum(album);
				
				SpicsToMeServices.Util.getInstance().getFoldersAlbum(album.getId(), new AsyncCallback<List<FolderDTO>>() {

					@Override
					public void onSuccess(List<FolderDTO> result) {
						editview.setFolders(result);	
					}
					@Override
					public void onFailure(Throwable caught) {}	
				});
				
				SpicsToMeServices.Util.getInstance().getAlbumOwner(album.getId(), new AsyncCallback<StudentDTO>() {

					@Override
					public void onSuccess(StudentDTO result) {
						editview.setOwner(result.getName());
					}
					@Override
					public void onFailure(Throwable caught) {}			
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
	public void save(ArticleDTO a) {
		

		SpicsToMeServices.Util.getInstance().saveArticle(a, new AsyncCallback<Long>() {
			@Override
			public void onSuccess(Long result) {}			
			@Override
			public void onFailure(Throwable caught) {}
		});
		
	}

	@Override
	public void save(FolderDTO f) {
		SpicsToMeServices.Util.getInstance().saveFolder(f,new AsyncCallback<Long>() {
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub		
			}

			@Override
			public void onSuccess(Long result) {
				// TODO Auto-generated method stub			
			}
		});
		
	}

}
