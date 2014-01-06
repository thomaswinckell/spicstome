package com.spicstome.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class MailPlace extends Place{
	
	
	public MailPlace() {
		
	}
	
	/**
	 * PlaceTokenizer knows how to serialize the Place's state to a URL token.
	 */
	public static class Tokenizer implements PlaceTokenizer<MailPlace> {

		@Override
		public String getToken(MailPlace place) {
			return "mail";
		}

		@Override
		public MailPlace getPlace(String token) {
			return new MailPlace();
		}

	}
}
