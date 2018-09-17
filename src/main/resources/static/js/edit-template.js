function displayContact(contact, address) {
	var targetContainer = document.getElementsByClassName("content")[0];
	var template = document.getElementById("contact-template").innerHTML;

	targetContainer.innerHTML = Mustache.render(template, Object.assign(contact, address));
}

function displayPhoneNumber(phoneNumber) {
	var targetContainer = document.getElementsByClassName("phone-numbers")[0];
	var template = document.getElementById("phone-number-template").innerHTML;

	targetContainer.innerHTML += (Mustache.render(template, phoneNumber));
}

function displayAttachment(attachment) {
	var targetContainer = document.getElementsByClassName("attachments")[0];
	var template = document.getElementById("attachment-template").innerHTML;

	targetContainer.innerHTML += (Mustache.render(template, attachment));
}

displayContact({
	"id": null,
	"name": "Иван",
	"surname": "Ермаков",
	"patronymic": "Алекс",
	"sex": true,
	"birth": "06.06.2000",
	"nationality": "BEL",
	"maritalStatus": null
}, {
	"contactId": null,
	"country": "BEL",
	"region": "Minsk",
	"locality": "Ds st. 2109",
	"postcode": 1234567
});

displayPhoneNumber({
	"contactId": null,
	"areaCode": 123,
	"operatorCode": 12,
	"number": 1234567,
	"type": "Mobile",
	"comment": "Some random comment"
});

displayAttachment({
	"contactId": 1,
	"name": "file",
	"uploaded": "2018-09-07",
	"path": "lflf/a",
	"comment": "Some random comment"
});
