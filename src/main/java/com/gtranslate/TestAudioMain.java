package com.gtranslate;

import java.io.InputStream;


public class TestAudioMain {

	public static void main(String[] args) {
		/*Audio audio=Audio.getInstance();
		try {
			InputStream sound=audio.getAudio("My Name is Ron", Language.ENGLISH);
			audio.play(sound);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		Translator translator=Translator.getInstance();
		String text=translator.translate("I am Ron", Language.ENGLISH, Language.PORTUGUESE);
		System.out.println(text);
		
	}
}
