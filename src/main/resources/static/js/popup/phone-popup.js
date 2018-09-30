function openPhoneEdit() {
	var ids = checkedPhonesId();

	if (ids.length === 0) {
		alert("Check any phone number and try again.");
		return;
	}
	if (ids.length > 1) {
		alert("Only one phone number must be checked");
		return;
	}

	var phoneId = ids[0];

	var targetContainer = document.getElementById("popup");
	var template = document.getElementById("phone-popup-template").innerHTML;

	var number = JSON.parse(httpGetSync("/number/select?id=" + phoneId));

	targetContainer.innerHTML += Mustache.render(template, number);

	//filling complex forms
	if (number.type === true) document.getElementById("type").value = "mobile";
	if (number.type === false) document.getElementById("type").value = "home";
}

function openPhoneCreate() {
	var targetContainer = document.getElementById("popup");
	var template = document.getElementById("phone-popup-template").innerHTML;

	targetContainer.innerHTML += Mustache.render(template, Object.create(null));

	document.getElementsByClassName("apply")[1].innerText = "Create";
}

function getNumber() {
	var number = JSON.parse(httpGetSync("/number/init"));

	number.contactId = new URL(window.location).searchParams.get("id");

	number.areaCode = document.getElementById("areaCode").value;
	number.operatorCode = document.getElementById("operatorCode").value;
	number.number = document.getElementById("number").value;
	if (document.getElementById("type").value === "mobile") number.type = true;
	if (document.getElementById("type").value === "home") number.type = false;
	number.comment = document.getElementById("comment").value;

	return number;
}

function editNumber() {
	var number = getNumber();

	var numberId = document.getElementById("edit-phone").className;
	if (numberId) {
		number.id = numberId;
		editedNumbers[number.id] = number;
	} else {
		newNumbers.push(number);
	}

	closePhonePopup();
}

function closePhonePopup() {
	document.getElementById("edit-phone").remove();
}