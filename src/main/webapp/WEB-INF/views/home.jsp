<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
	<spring:url value="/resources/css/bootstrap.min.css" var="bootstrapCss" />
	<spring:url value="/resources/css/style.css" var="styleCss" />
	<link href="${bootstrapCss}" rel="stylesheet" />
	<link href="${styleCss}" rel="stylesheet" />
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Banking sector</title>
    
  <style>
  ul { list-style: none; }
		#recordingslist audio { display: block; margin-bottom: 10px; }
  </style>
  </head>
<body>
    <section class="wrapper fluid-container">
      <div class="col-xs-12 audioContainer">
      <span class="close"></span>
        <div class="row">
          <div class="helpSection">
            <div class="helpText">
              <p>How can I help you?</p>
              <div class="">
              	<button id="startRec"  onclick="startRecording(this);" class="helpIcon"><img src="/ai-hackathon/resources/images/microphone-icon.png" alt=""/></button>
              	<button id="stopRec"  onclick="stopRecording(this);" class="helpIcon"  disabled><img src="/ai-hackathon/resources/images/microphone-icon.png" alt=""/></button>
              </div>
              <div class="divider"></div>
              <h2>Recordings</h2>
  				<ul id="recordingslist"></ul>
  
  				<h2>Log</h2>
  				<pre id="log"></pre>
              <h1>Speak Now...</h1>
            </div>
            
            
            
            
            
            <div class="listenText">
              <p>Listening...</p>
              <div class="listenIcon">
              
                <img src="/ai-hackathon/resources/images/listen.png" alt="listen icon">
              </div>
              <div class="divider"></div>
              <h2>I want to transfer funds...</h2>
            </div>
          </div>
        </div>
      </div>
    </section>
    
    
    <script>
    function __log(e, data) {
    log.innerHTML += "\n" + e + " " + (data || '');
  }

  var audio_context;
  var recorder;

  function startUserMedia(stream) {
    var input = audio_context.createMediaStreamSource(stream);
    __log('Media stream created.');

    // Uncomment if you want the audio to feedback directly
    //input.connect(audio_context.destination);
    //__log('Input connected to audio context destination.');
    
    recorder = new Recorder(input);
    __log('Recorder initialised.');
  }

  function startRecording(button) {
    recorder && recorder.record();
    button.disabled = true;
    button.nextElementSibling.disabled = false;
    __log('Recording...');
  }

  function stopRecording(button) {
    recorder && recorder.stop();
    button.disabled = true;
    button.previousElementSibling.disabled = false;
    __log('Stopped recording.');
    
    // create WAV download link using audio data blob
    createDownloadLink();
    
    recorder.clear();
  }

  function createDownloadLink() {
    recorder && recorder.exportWAV(function(blob) {
      var url = URL.createObjectURL(blob);
      var li = document.createElement('li');
      var au = document.createElement('audio');
      var hf = document.createElement('a');
      
      au.controls = true;
      au.src = url;
      hf.href = url;
      hf.download = new Date().toISOString() + '.wav';
      hf.innerHTML = hf.download;
      li.appendChild(au);
      li.appendChild(hf);
      recordingslist.appendChild(li);
    });
  }

  window.onload = function init() {
    try {
      // webkit shim
      window.AudioContext = window.AudioContext || window.webkitAudioContext;
      navigator.getUserMedia = navigator.getUserMedia || navigator.webkitGetUserMedia;
      window.URL = window.URL || window.webkitURL;
      
      audio_context = new AudioContext;
      __log('Audio context set up.');
      __log('navigator.getUserMedia ' + (navigator.getUserMedia ? 'available.' : 'not present!'));
    } catch (e) {
      alert('No web audio support in this browser!');
    }
    
    navigator.getUserMedia({audio: true}, startUserMedia, function(e) {
      __log('No live audio input: ' + e);
    });
  };
    </script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="/ai-hackathon/resources/dist/recorder.js"></script>
  </body>
</html>
