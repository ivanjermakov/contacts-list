var templates = [];

function openTemplate() {
	var ids = getCheckedIds();

	if (ids.length === 0) {
		alert("Check any contact and try again.");
		return;
	}

	var targetContainer = document.getElementById("popup");
	var template = document.getElementById("mail-template").innerHTML;

	targetContainer.innerHTML += Mustache.render(template, Object.create(null));

	templates = JSON.parse(httpGetSync("/mail/template/init"));

	console.log(templates);

	Object.keys(templates).forEach(function (templateName) {
		addTemplateOption(templateName);
	});

	dimBackground();
}

function addTemplateOption(templateName) {
	var opt = document.createElement('option');
	opt.value = templateName;
	opt.innerHTML = templateName;
	document.getElementById("template-select").appendChild(opt);
}

function closeTemplatePopup() {
	document.getElementById("template").remove();
	clearBackground();
}

function sendTemplate() {
	var templateName = document.getElementById("template-select").value;

	if (!templateName) {
		alert("Please select template");
		return;
	}

	httpPost("/mail/template/send?template=" + templateName, getCheckedIds(), function (response) {
	});

	closeTemplatePopup();
}