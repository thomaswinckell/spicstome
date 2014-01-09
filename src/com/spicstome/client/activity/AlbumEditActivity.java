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
import com.spicstome.client.ui.UserViewLayout;

public class AlbumEditActivity extends UserActivity implements AlbumEditView.Presenter{
	
	AlbumEditView editview;
	AlbumDTO album;
	

	public AlbumEditActivity(AlbumEditPlace place, ClientFactory clientFactory) {
		super(place, clientFactory,(UserViewLayout)clientFactory.getAlbumEditView());

		System.out.println(place.album);
		
		this.album=place.album;
		
		clientFactory.getAlbumEditView().setAlbum(album);
		
		this.editview = clientFactory.getAlbumEditView();
		
		SpicsToMeServices.Util.getInstance().getFoldersAlbum(place.album, new AsyncCallback<List<FolderDTO>>() {

			@Override
			public void onSuccess(List<FolderDTO> result) {
				editview.setFolders(result);	
			}
			@Override
			public void onFailure(Throwable caught) {}	
		});
		
		SpicsToMeServices.Util.getInstance().getAlbumOwner(place.album, new AsyncCallback<StudentDTO>() {

			@Override
			public void onSuccess(StudentDTO result) {
				editview.setOwner(result.getName());
			}
			@Override
			public void onFailure(Throwable caught) {}			
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
			public void onSuccess(Long result) {
				
				
				
			}
			
			@Override
			public void onFailure(Throwable caught) {}
		});
		
		System.out.println("save !");
	}

}
