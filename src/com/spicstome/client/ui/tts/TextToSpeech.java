package com.spicstome.client.ui.tts;

import java.util.ArrayList;

import com.allen_sauer.gwt.voices.client.Sound;
import com.allen_sauer.gwt.voices.client.SoundController;
import com.allen_sauer.gwt.voices.client.Sound.LoadState;
import com.allen_sauer.gwt.voices.client.handler.PlaybackCompleteEvent;
import com.allen_sauer.gwt.voices.client.handler.SoundHandler;
import com.allen_sauer.gwt.voices.client.handler.SoundLoadStateChangeEvent;
import com.google.gwt.http.client.URL;
import com.smartgwt.client.widgets.layout.VLayout;
import com.spicstome.client.dto.WordDTO;

public class TextToSpeech {

	private SoundController soundController;
	
	private static String SERVICE_PROTOCOLE = "http://";
	private static String SERVICE_URL = "/spicstome/textToSpeech";
	private static String SERVICE_PARAMS = "?ie=UTF-8&tl=fr";
	
	public TextToSpeech() {
		soundController = new SoundController();
	}
	
	private String getAudioFileURL(ArrayList<WordDTO> message) {
		
		String query = "?q=";
		boolean isFirstWord = true;
		
		for(WordDTO word : message) {
			
			if (isFirstWord)
				isFirstWord = false;
			else
				query += " ";
			
			query += word.getName();
		}
		
		String res = URL.encode(SERVICE_URL+query);
		
		System.out.println(res);
		
		return res;
	}
	
	public void playMessage(ArrayList<WordDTO> message) {
		
		final Sound sound = soundController.createSound(Sound.MIME_TYPE_AUDIO_MPEG_MP3,
				getAudioFileURL(message));
		
	    sound.addEventHandler(new SoundHandler() {

			@Override
			public void onPlaybackComplete(PlaybackCompleteEvent event) {
				
			}

			@Override
			public void onSoundLoadStateChange(
					SoundLoadStateChangeEvent event) {
				if (event.getLoadState() == LoadState.LOAD_STATE_SUPPORTED_AND_READY)
					sound.play();
			}	    	
	    });
	}
}
