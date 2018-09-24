function getSearchQuery() {
	var search = JSON.parse(httpGetSync("/search/init"));

	console.log(search);

	search.name = document.getElementById("name").value;
	search.surname = document.getElementById("surname").value;
	search.patronymic = document.getElementById("patronymic").value;

	search.birth = document.getElementById("birth").value;

	if (document.getElementById("isBefore").selectedIndex === 1) search.birthAfter = true;
	if (document.getElementById("isBefore").selectedIndex === 2) search.birthAfter = false;

	if (document.getElementById("sex").selectedIndex === 1) search.sex = true;
	if (document.getElementById("sex").selectedIndex === 2) search.sex = false;

	search.maritalStatus = document.getElementById("maritalStatus").value;

	search.nationality = document.getElementById("nationality").value;
	search.country = document.getElementById("country").value;
	search.region = document.getElementById("region").value;
	search.locality = document.getElementById("locality").value;
	search.postcode = document.getElementById("postcode").value;

	return search;
}

function displaySearchResults(searchQuery) {
	clearMainInfo();

	httpPost("/search/", searchQuery, function (json) {
		var searchResult = JSON.parse(json);

		console.log(searchResult);

		searchResult.forEach(function (contact) {
			var address = JSON.parse(httpGetSync("/address/select?id=" + contact.id));
			displayMainInfo(Object.assign(contact, address));
		})
	});
}

function search() {
	var search = getSearchQuery();

	displaySearchResults(search);
}