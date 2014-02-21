package com.spicstome.client.mvp;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;
import com.spicstome.client.place.AddUserPlace;
import com.spicstome.client.place.AlbumEditPlace;
import com.spicstome.client.place.AlbumManagementPlace;
import com.spicstome.client.place.AlbumPlace;
import com.spicstome.client.place.HistoryManagementPlace;
import com.spicstome.client.place.LogoutPlace;
import com.spicstome.client.place.MailPlace;
import com.spicstome.client.place.NewMailPlace;
import com.spicstome.client.place.MainMenuPlace;
import com.spicstome.client.place.LoginPlace;
import com.spicstome.client.place.UsersManagementPlace;

/**
 * PlaceHistoryMapper interface is used to attach all places which the
 * PlaceHistoryHandler should be aware of. This is done via the @WithTokenizers
 * annotation or by extending PlaceHistoryMapperWithFactory and creating a
 * separate TokenizerFactory.
 */
@WithTokenizers( { LoginPlace.Tokenizer.class,
					LogoutPlace.Tokenizer.class,
					MainMenuPlace.Tokenizer.class, 
					AlbumManagementPlace.Tokenizer.class,
					AlbumPlace.Tokenizer.class,
					AlbumEditPlace.Tokenizer.class,
					NewMailPlace.Tokenizer.class,
					MailPlace.Tokenizer.class,
					HistoryManagementPlace.Tokenizer.class,
					UsersManagementPlace.Tokenizer.class,
					AddUserPlace.Tokenizer.class})
public interface AppPlaceHistoryMapper extends PlaceHistoryMapper {
}
