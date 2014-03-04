package com.spicstome.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class NewMailPlace extends Place{
	
	public String recipientMail;
	public String htmlReceivedMail;
	
	public NewMailPlace(String recipientMail, String htmlReceivedMail) {
		this.recipientMail = recipientMail;
		this.htmlReceivedMail = htmlReceivedMail;
	}
	
	/**
	 * PlaceTokenizer knows how to serialize the Place's state to a URL token.
	 */
	public static class Tokenizer implements PlaceTokenizer<NewMailPlace> {

		@Override
		public String getToken(NewMailPlace place) {
			return "RecipientMail:"+place.recipientMail+":"+place.htmlReceivedMail;
		}

		@Override
		public NewMailPlace getPlace(String token) {
			String[] split = token.split(":",3);
			String  recipient = split[1];		
			String htmlReceivedMail = "";
			
			if (split.length > 2)
				htmlReceivedMail = split[2];
			
			return new NewMailPlace(recipient, htmlReceivedMail);
		}

	}
}
