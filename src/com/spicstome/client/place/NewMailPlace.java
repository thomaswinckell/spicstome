package com.spicstome.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class NewMailPlace extends Place{
	
	public String recipientMail;
	
	public NewMailPlace(String recipientMail) {
		this.recipientMail=recipientMail;
	}
	
	/**
	 * PlaceTokenizer knows how to serialize the Place's state to a URL token.
	 */
	public static class Tokenizer implements PlaceTokenizer<NewMailPlace> {

		@Override
		public String getToken(NewMailPlace place) {
			return "RecipientMail:"+place.recipientMail;
		}

		@Override
		public NewMailPlace getPlace(String token) {
			String[] split = token.split(":");
			String  recipient = split[1];
			
			return new NewMailPlace(recipient);
		}

	}
}
