function getMail() {
	var mail = JSON.parse(httpGetSync("/mail/init"));

	mail.to = document.getElementById("to").value;
	mail.subject = document.getElementById("subject").value;
	mail.template = document.getElementById("subject").value;
	mail.text = document.getElementById("text").value;

	return mail;
}

function send() {
	var sendButton = document.getElementById("send");
	httpPost("/mail/send", getMail(), function (response) {
		sendButton.id = "send";
		sendButton.innerText = "Send";
		alert("Mail sent successfully")
	}, function (error) {
		sendButton.id = "send";
		sendButton.innerText = "Send";
		alert("Error sending mail.")
	});
	sendButton.id = "sending";
	sendButton.innerText = "Sending...";

}