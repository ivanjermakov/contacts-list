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
}

function displayAttachment(attachment) {
	var targetContainer = document.getElementsByClassName("attachments")[0];
	var template = document.getElementById("attachment-template").innerHTML;

	targetContainer.innerHTML += (Mustache.render(template, attachment));
}

function load() {
	var id = new URL(window.location).searchParams.get("id");

	var contact = JSON.parse(httpGetSync("/contact/selectById?id=" + id));
	var address = JSON.parse(httpGetSync("/address/select?id=" + id));
	var numbers = JSON.parse(httpGetSync("/number/select?id=" + id));
	var attachments = JSON.parse(httpGetSync("/attachment/select?id=" + id));

	display(contact, address, numbers, attachments)
}

load();