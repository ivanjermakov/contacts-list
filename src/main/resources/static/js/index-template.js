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

function getCheckedIds() {
	var ids = [];

	var wrapper = document.getElementById("contacts-wrapper");
	var contacts = wrapper.children;

	//coz unable to loop HTMLCollection with foreach in ES5
	for (var i = 0; i < contacts.length; i++) {
		if (contacts[i].getElementsByTagName("input")[0].checked) {
			ids.push(contacts[i].id);
		}
	}

	return ids;
}

function edit() {
	var ids = getCheckedIds();
	if (ids.length === 0) alert("Check any contact and try again.");
	if (ids.length > 1) alert("Only one contact must be checked");

	window.location.replace("/edit-contact.html?id=" + ids[0]);
}

load();