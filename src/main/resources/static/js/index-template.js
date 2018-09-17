function displayMainInfo(contactMainInfo) {
	var targetContainer = document.getElementById("contacts-wrapper");
	var template = document.getElementById("contact-template").innerHTML;

	targetContainer.innerHTML += Mustache.render(template, contactMainInfo);
}

function load() {
	httpGet("/contactMainInfo/select", function (json) {
		var contactsMainInfo = JSON.parse(json);

		contactsMainInfo.forEach(function (contactMainInfo) {
			displayMainInfo(contactMainInfo);
		})
	});
}

load();