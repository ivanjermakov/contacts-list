var templates;

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

function load() {
	templates = JSON.parse(httpGetSync("/mail/template/init"));

	Object.keys(templates).forEach(function (templateName) {
		addTemplateOption(templateName);
	});
}

function addTemplateOption(templateName) {
	var opt = document.createElement('option');
	opt.value = templateName;
	opt.innerHTML = templateName;
	document.getElementById("template").appendChild(opt);
}

function selectTemplate() {
	var options = document.getElementById("template").getElementsByTagName("option");

	var id;
	for (var i = 0; i < options.length; i++) {
		if (document.getElementById("template").value === options[i].value) {
			id = i;
			break;
		}
	}

	if (id === 0) {
		document.getElementById("text").innerText = "";
	} else {
		document.getElementById("text").innerText = Object.values(templates)[id - 1];
	}
}