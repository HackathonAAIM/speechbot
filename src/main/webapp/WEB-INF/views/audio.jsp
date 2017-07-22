<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Title of the document</title>
</head>

<body>
	<h2>Audio Input Stream</h2>
	<!--<input type="audio" accept="audio/*;capture=microphone">
	<input type="file" accept="image/*;capture=camera">-->

	<audio id="player" controls></audio>
	<script>
	  /* var player = document.getElementById('player');

	  var handleSuccess = function(stream) {
		if (window.URL) {
		  player.src = window.URL.createObjectURL(stream);
		} else {
		  player.src = stream;
		}
	  };

	  navigator.mediaDevices.getUserMedia({ audio: true, video: false })
		  .then(handleSuccess) */
		  
		  
		  var player = document.getElementById('player');

		  var handleSuccess = function(stream) {
		    if (window.URL) {
		      player.src = window.URL.createObjectURL(stream);
		    } else {
		      player.src = stream;
		    }
		    
		    
		    var context = new AudioContext();
		    var input = context.createMediaStreamSource(stream)
		    var processor = context.createScriptProcessor(1024,1,1);

		    source.connect(processor);
		    processor.connect(context.destination);

		    processor.onaudioprocess = function(e){
		      // Do something with the data, i.e Convert this to WAV
		      console.log(e.inputBuffer);
		    };
		    
		    
		  };

		  navigator.mediaDevices.getUserMedia({ audio: true, video: false })
		      .then(handleSuccess);
	</script>
</body>

</html>