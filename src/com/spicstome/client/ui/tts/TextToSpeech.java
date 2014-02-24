package com.spicstome.client.ui.tts;

import com.allen_sauer.gwt.voices.client.Sound;
import com.allen_sauer.gwt.voices.client.SoundController;
import com.allen_sauer.gwt.voices.client.Sound.LoadState;
import com.allen_sauer.gwt.voices.client.handler.PlaybackCompleteEvent;
import com.allen_sauer.gwt.voices.client.handler.SoundHandler;
import com.allen_sauer.gwt.voices.client.handler.SoundLoadStateChangeEvent;
import com.google.gwt.http.client.URL;

public class TextToSpeech {

	private SoundController soundController;
	
	private static String SERVICE_URL = "/spicstome/textToSpeech";
	
	public TextToSpeech() {
		soundController = new SoundController();
	}
	
	private String getAudioFileURL(String message) {
		
		String query = "?q="+message;
		
		String res = URL.encode(SERVICE_URL+query);
		
		return res;
	}
	
	public void playMessage(String message) {
		
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
