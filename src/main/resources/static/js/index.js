var CONTACTS_ON_PAGE = 10;

function addParamToUrl(param, value) {
	var url = window.location.protocol + "//" + window.location.host + window.location.pathname + "?" + param + "=" + value;
	window.history.pushState({path: url}, '', url);
}

function displayMainInfo(contactMainInfo) {
	var targetContainer = document.getElementById("contacts-wrapper");
	var template = document.getElementById("contact-template").innerHTML;

	targetContainer.innerHTML += Mustache.render(template, contactMainInfo);

	//fix empty fields
	var contacts = document.getElementById("contacts-wrapper").children;

	//coz unable to loop HTMLCollection with foreach in ES5
	for (var i = 0; i < contacts.length; i++) {
		var birth = contacts[i].getElementsByClassName("birth")[0];
		if (birth && birth.innerText === "") birth.remove();

		var address = contacts[i].getElementsByClassName("address")[0];
		if (address && address.innerText === "") address.remove();

		var workplace = contacts[i].getElementsByClassName("workplace")[0];
		if (workplace && workplace.innerText === "") workplace.remove();
	}
}

function clearMainInfo() {
	var contactsWrapper = document.getElementById("contacts-wrapper");
	while (contactsWrapper.firstChild) {
		contactsWrapper.removeChild(contactsWrapper.firstChild);
	}
}

function getCurrentPageNumber() {
	var page = new URL(window.location).searchParams.get("page");
	if (!page) {
		// 1st page by default
		page = 1;
	}
	return page;
}

function load() {
	var page = getCurrentPageNumber();
	// remove if first page
	if (page == 1) removePrevPageButton();
	// remove if last page
	if (JSON.parse(httpGetSync("/contactMainInfo/select?amount=" + CONTACTS_ON_PAGE + "&offset=" + page * CONTACTS_ON_PAGE)).length === 0) removeNextPageButton();
	var offset = (page - 1) * CONTACTS_ON_PAGE;

	httpGet("/contactMainInfo/select?amount=" + CONTACTS_ON_PAGE + "&offset=" + offset, function (json) {
		var contactsMainInfo = JSON.parse(json);

		contactsMainInfo.forEach(function (contactMainInfo) {
			displayMainInfo(contactMainInfo);
		});
	});
}

function removeNextPageButton() {
	var next = document.getElementById("next");
	if (next) next.remove();
}

function removePrevPageButton() {
	var prev = document.getElementById("prev");
	if (prev) prev.remove();
}

function loadNextPage() {
	var page = getCurrentPageNumber();
	page++;

	window.location.replace("/index.html?page=" + page);
}

function loadPrevPage() {
	var page = getCurrentPageNumber();
	page--;

	window.location.replace("/index.html?page=" + page);
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

	window.location.replace("/edit.html?id=" + ids[0]);
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