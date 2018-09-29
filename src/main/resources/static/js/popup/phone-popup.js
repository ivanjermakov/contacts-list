function checkedPhoneIndexes() {
	var numbers = document.getElementsByClassName("phone-number");

	var ids = [];

	//coz unable to loop HTMLCollection with foreach in ES5
	for (var i = 0; i < numbers.length; i++) {
		if (numbers[i].getElementsByTagName("input")[0].checked) {
			ids.push(numbers[i].classList[1]);
		}
	}

	return ids;
}

function openPhone() {
	var indexes = checkedPhoneIndexes();

	if (indexes.length === 0) {
		alert("Check any contact and try again.");
		return;
	}
	if (indexes.length > 1) {
		alert("Only one contact must be checked");
		return;
	}

	var phoneIndex = indexes[0];

	var targetContainer = document.body;
	var template = document.getElementById("phone-popup-template").innerHTML;

	var contactId = new URL(window.location).searchParams.get("id");
	if (contactId) {
		var number = JSON.parse(httpGetSync("/number/select?id=" + phoneIndex));

		targetContainer.innerHTML += Mustache.render(template, number);

		//filling complex forms
		if (number === true) document.getElementById("mobile").selected = true;
		if (number === false) document.getElementById("home").selected = false;
	} else {
		targetContainer.innerHTML += Mustache.render(template, Object.create(null));
	}
}

function getNumber() {
	var number = JSON.parse(httpGetSync("/number/init"));

	number.areaCode = document.getElementById("areaCode").value;
	number.operatorCode = document.getElementById("operatorCode").value;
	number.number = document.getElementById("number").value;
	if (document.getElementById("type").value === "Mobile") number.type = true;
	if (document.getElementById("type").value === "Home") number.type = false;
	number.comment = document.getElementById("comment").value;

	return number;
}

/*
function saveNumber() {
	var number = getNumber();

	var id = new URL(window.location).searchParams.get("id");
	if (id) {
		contact.id = id;
		httpPost("/number/edit", contact, function (response) {
		});
	} else {
		httpPost("/number/insert", contact, function (response) {
		});
	}
}
*/

function closePhonePopup() {
	document.getElementById("edit-phone").remove();
}