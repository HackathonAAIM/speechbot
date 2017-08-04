package com.example.speech;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.threeten.bp.Duration;

import com.google.api.gax.retrying.RetrySettings;
import com.google.cloud.speech.v1.SpeechClient;
import com.google.cloud.speech.v1.SpeechSettings;
import com.google.cloud.speech.v1.RecognitionAudio;
import com.google.cloud.speech.v1.RecognitionConfig;
import com.google.cloud.speech.v1.RecognitionConfig.AudioEncoding;
import com.google.cloud.speech.v1.RecognizeResponse;
import com.google.cloud.speech.v1.SpeechRecognitionAlternative;
import com.google.cloud.speech.v1.SpeechRecognitionResult;
import com.google.protobuf.ByteString;

public class Quick {

	public static void main(String[] args) throws Exception {
		Logger.getLogger("").setLevel(Level.FINE);
		// TODO Auto-generated method stub
		// Instantiates a client
	 RecognitionConfig config = RecognitionConfig.newBuilder()
			        .setEncoding(AudioEncoding.LINEAR16)
			        .setSampleRateHertz(16000)
			        .setLanguageCode("en-US")
			        .build();
	 /*		SpeechSettings st = SpeechSettings.newBuilder().build().setRetrySettingsBuilder(RecognitionConfig.newBuilder()
		        .setEncoding(AudioEncoding.LINEAR16)
		        .setSampleRateHertz(16000)
		        .setLanguageCode("en-US")).build();*/
	    SpeechClient speech = SpeechClient.create();

	    // The path to the audio file to transcribe
	    String fileName = "./resources/audio.raw";

	    // Reads the audio file into memory
	    Path path = Paths.get(fileName);
	    byte[] data = Files.readAllBytes(path);
	    ByteString audioBytes = ByteString.copyFrom(data);

	    // Builds the sync recognize request
	   
	    RecognitionAudio audio = RecognitionAudio.newBuilder()
	        .setContent(audioBytes)
	        .build();
	    
	    /*RetrySettings val = RetrySettings.newBuilder().setMaxAttempts(10).setTotalTimeout(Duration.ofSeconds(60))
	    .setInitialRetryDelay(Duration.ofSeconds(30))
	    .setRetryDelayMultiplier(2).setMaxRetryDelay(Duration.ofMillis(Long.MAX_VALUE))
	    .setInitialRpcTimeout(Duration.ofSeconds(20)).setMaxRpcTimeout(Duration.ofSeconds(20)).build();*/
	    
	    // Performs speech recognition on the audio file
	    RecognizeResponse response = speech.recognize(config, audio);
	    List<SpeechRecognitionResult> results = response.getResultsList();

	    for (SpeechRecognitionResult result: results) {
	      List<SpeechRecognitionAlternative> alternatives = result.getAlternativesList();
	      for (SpeechRecognitionAlternative alternative: alternatives) {
	        System.out.printf("Transcription: %s%n", alternative.getTranscript());
	      }
	    }
	    speech.close();
	}

}
