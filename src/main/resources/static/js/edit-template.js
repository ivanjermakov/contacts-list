function getContact() {
	var contact = JSON.parse(httpGetSync("/contact/init"));

	contact.name = document.getElementById("name").value;
	contact.surname = document.getElementById("surname").value;
	contact.patronymic = document.getElementById("patronymic").value;
	contact.birth = document.getElementById("birth").value;
	if (document.getElementById("male-radio").checked) contact.sex = true;
	if (document.getElementById("female-radio").checked) contact.sex = false;
	contact.nationality = document.getElementById("nationality").value;
	contact.maritalStatus = document.getElementById("maritalStatus").value;
	contact.website = document.getElementById("website").value;
	contact.email = document.getElementById("email").value;
	contact.workplace = document.getElementById("workplace").value;

	return contact;
}

function getAddress() {
	var address = JSON.parse(httpGetSync("/address/init"));

	address.country = document.getElementById("country").value;
	address.region = document.getElementById("region").value;
	address.locality = document.getElementById("locality").value;
	address.postcode = document.getElementById("postcode").value;

	return address;
}

function display(contact, address, numbers, attachments) {
	var targetContainer = document.getElementsByClassName("content")[0];
	var template = document.getElementById("contact-template").innerHTML;

	targetContainer.innerHTML = Mustache.render(template, Object.assign(contact, address));

	numbers.forEach(function (number) {
		displayNumber(number);
	});

	attachments.forEach(function (attachment) {
		displayAttachment(attachment);
	});

	// filling complex forms
	if (contact.sex != null) {
		if (contact.sex) {
			document.getElementById("male-radio").checked = true;
		} else {
			document.getElementById("female-radio").checked = true;
		}
	}

}

function displayNumber(phoneNumber) {
	var targetContainer = document.getElementsByClassName("phone-numbers")[0];
	var template = document.getElementById("phone-number-template").innerHTML;

	targetContainer.innerHTML += (Mustache.render(template, phoneNumber));

	// display complex forms
	var numbers = targetContainer.getElementsByClassName("phone-number");
	var last = numbers[numbers.length - 1];

	if (phoneNumber.type === true) {
		last.getElementsByClassName("type")[0].innerHTML += "Mobile";
	}
	if (phoneNumber.type === false) {
		last.getElementsByClassName("type")[0].innerHTML += "Home";
	}

}

function displayAttachment(attachment) {
	var targetContainer = document.getElementsByClassName("attachments")[0];
	var template = document.getElementById("attachment-template").innerHTML;

	targetContainer.innerHTML += (Mustache.render(template, attachment));
}

// TODO: count numbers and attachments
function save() {
	var contact = getContact();
	var address = getAddress();

	var id = new URL(window.location).searchParams.get("id");
	if (id) {
		contact.id = id;
		httpPost("/contact/edit", contact, function (response) {
			address.contactId = id;
			console.log(address);
			httpPost("/address/edit", address, function (response) {
				window.location.replace("/edit-contact.html?id=" + address.contactId);
			});
		});
	} else {
		httpPost("/contact/insert", contact, function (response) {
			address.contactId = response;
			httpPost("/address/insert", address, function (response) {
				window.location.replace("/edit-contact.html?id=" + address.contactId);
			});
		});
	}
}

function load() {
	var id = new URL(window.location).searchParams.get("id");

	if (id) {
		var contact = JSON.parse(httpGetSync("/contact/selectById?id=" + id));
		var address = JSON.parse(httpGetSync("/address/select?id=" + id));
		var numbers = JSON.parse(httpGetSync("/number/selectByContactId?id=" + id));
		var attachments = JSON.parse(httpGetSync("/attachment/select?id=" + id));

		display(contact, address, numbers, attachments);
	} else {
		var targetContainer = document.getElementsByClassName("content")[0];
		var template = document.getElementById("contact-template").innerHTML;

		targetContainer.innerHTML = Mustache.render(template, Object.create(null));

		document.getElementsByClassName("apply")[0].innerText = "Create";
	}

}

load();