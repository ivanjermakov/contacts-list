function displayContact(contact, address) {
	var targetContainer = document.getElementById("contacts-wrapper");
	var template = document.getElementById("contact-template").innerHTML;

	targetContainer.innerHTML += Mustache.render(template, Object.assign(contact, address));
}

displayContact({
	"id": null,
	"name": "Иван",
	"surname": "Ермаков",
	"patronymic": "Алекс",
	"sex": true,
	"birth": "06.06.2000",
	"nationality": "BEL",
	"maritalStatus": null,
	"website": "la.com",
	"email": "la@g.com",
	"workplace": "somewhere"
}, {
	"contactId": null,
	"country": "BEL",
	"region": "Minsk",
	"locality": "Ds st. 2109",
	"postcode": 1234567
});
