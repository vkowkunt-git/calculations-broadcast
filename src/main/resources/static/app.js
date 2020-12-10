var ws = null;

//rules to follow when the connection is made
function setConnected(connected) {
	$("#connect").prop("disabled", connected);
    $("#user").prop("disabled", connected); //disabling the text area after the connection
	$("#disconnect").prop("disabled", !connected); //disabling the end connection button till a connection is made
	if (connected) {   //enabling the results only after the connection is made
            $("#results").show();
        }
        else {
            $("#results").hide();
        }
        $("#receive").html("");
}

//makes a connection and starts a new session
//creates a new socket using SockJs to subscribe to the topic generated by the message broker on the server side
function connect() {
    var socket = new SockJS('/calculations-websocket');
    ws = Stomp.over(socket);
    ws.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        ws.subscribe('/topic/calculations_subscriber', function (message) {
            receive(message.body); //receive method is invoked after receiving the messages from the server
        });
    });
}

//disconnects from the session
function disconnect() {
	if (ws != null) {
		ws.disconnect();
	}
	setConnected(false);
	console.log("Websocket is in disconnected state");
}

//sends data to the server
function sendData() {
    var operation_value;
    if(document.getElementById('+').checked){
        operation_value = document.getElementById('+').value;
    }else if(document.getElementById('-').checked){
        operation_value = document.getElementById('-').value;
    }else if(document.getElementById('*').checked){
        operation_value = document.getElementById('*').value;
    }else if(document.getElementById('/').checked){
        operation_value = document.getElementById('/').value;
    }else{
        alert("Please check an operation");
        return false;
    }
	var data = JSON.stringify({
	    'user':document.getElementById('user').value,
		'a' : document.getElementById('a').value,
		'b' : document.getElementById('b').value,
		'operation':operation_value
	})
	ws.send("/app/information", {},data);  //sends data to the server in a Json format
}

//formats and presents the message received to the client
function receive(message) {
   document.getElementById('receive').innerHTML = "";
	$("#receive").append(" " + message + " ");
}

//necessary validations
function validateName() {
  var x = document.forms["form-inline"]["user"].value;
  if (x == "") {
    alert("Name must be filled out");
    return false;
  }else{
    return true;
  }
}

//disconnects when the tab is abruptly shutdown
window.onbeforeunload = function(){
  disconnect();
};

$(function() {
	$("form").on('submit', function(e) {
		e.preventDefault();
	});
	$("#connect").click(function() {
	    if(validateName()){
	        connect(); //connects only if a name is provided
	    }
	});
	$("#disconnect").click(function() {
		disconnect();
	});
	$("#send").click(function() {
		sendData();
	});
});