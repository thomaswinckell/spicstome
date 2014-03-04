package com.spicstome.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class ReceivedMailsPlace extends Place{

	public int startPosition, maxNbValidMails;
	public boolean isDescDirection;
	
	public final static int DEFAULT_MAX_NB_VALID_MAILS = 3;
	
	public ReceivedMailsPlace() {
		this.startPosition = 0;
		this.isDescDirection = true;
		this.maxNbValidMails = DEFAULT_MAX_NB_VALID_MAILS;
	}
	
	public ReceivedMailsPlace(int startPosition, boolean isDescDirection) {
		this.startPosition = startPosition;
		this.isDescDirection = isDescDirection;
		this.maxNbValidMails = DEFAULT_MAX_NB_VALID_MAILS;
	}

	public ReceivedMailsPlace(int startPosition, boolean isDescDirection, int maxNbValidMails) {
		this.isDescDirection = isDescDirection;
		this.startPosition = startPosition;
		this.maxNbValidMails = maxNbValidMails;
	}

	/**
	 * PlaceTokenizer knows how to serialize the Place's state to a URL token.
	 */
	public static class Tokenizer implements PlaceTokenizer<ReceivedMailsPlace> {

		@Override
		public String getToken(ReceivedMailsPlace place) {
			return "receivedmails:"+place.startPosition+":"+place.isDescDirection+":"+place.maxNbValidMails;
		}

		@Override
		public ReceivedMailsPlace getPlace(String token) {
			
			String[] split = token.split(":");
			String  startPosition = split[1];
			String  isDescDirection = split[2];
			String  maxNbValidMails = split[3];
			
			return new ReceivedMailsPlace(Integer.parseInt(startPosition), Boolean.parseBoolean(isDescDirection), Integer.parseInt(maxNbValidMails));
		}

	}
}
