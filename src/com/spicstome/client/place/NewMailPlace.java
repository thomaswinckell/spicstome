package com.spicstome.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class NewMailPlace extends Place{
	
	
	public NewMailPlace() {
		
	}
	
	/**
	 * PlaceTokenizer knows how to serialize the Place's state to a URL token.
	 */
	public static class Tokenizer implements PlaceTokenizer<NewMailPlace> {

		@Override
		public String getToken(NewMailPlace place) {
			return "mail";
		}

		@Override
		public NewMailPlace getPlace(String token) {
			return new NewMailPlace();
		}

	}
}
