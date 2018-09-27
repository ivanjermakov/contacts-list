function getMail() {
	var mail = JSON.parse(httpGetSync("/mail/init"));

	mail.to = document.getElementById("to").value;
	mail.subject = document.getElementById("subject").value;
	mail.template = document.getElementById("subject").value;
	mail.text = document.getElementById("text").value;

	return mail;
}

function send() {
	httpPost("/mail/send", getMail(), function (response) {
		alert("message sent successfully.");
	})
}