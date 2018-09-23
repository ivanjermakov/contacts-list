function displayMainInfo(contactMainInfo) {
	var targetContainer = document.getElementById("contacts-wrapper");
	var template = document.getElementById("contact-template").innerHTML;

	targetContainer.innerHTML += Mustache.render(template, contactMainInfo);
}

function clearMainInfo() {
	var contactsWrapper = document.getElementById("contacts-wrapper");
	while (contactsWrapper.firstChild) {
		contactsWrapper.removeChild(contactsWrapper.firstChild);
	}
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

	if (ids.length === 0) {
		alert("Check any contact and try again.");
		return;
	}
	if (ids.length > 1) {
		alert("Only one contact must be checked");
		return;
	}

	window.location.replace("/edit-contact.html?id=" + ids[0]);
}

function remove() {
	var ids = getCheckedIds();

	if (ids.length === 0) {
		alert("Check any contacts to delete and try again.");
		return;
	}

	if (!confirm("Are you sure want to delete selected contacts?")) return;

	httpPost("/contact/remove", ids, function () {
		// reload if removed
		clearMainInfo();
		load();
	});

}