function openAttachmentEdit() {
	var ids = checkedAttachmentsId();

	if (ids.length === 0) {
		alert("Check any attachment and try again.");
		return;
	}
	if (ids.length > 1) {
		alert("Only one attachment must be checked");
		return;
	}

	var phoneId = ids[0];

	var targetContainer = document.getElementById("popup");
	var template = document.getElementById("attachment-popup-template").innerHTML;

	var attachment = JSON.parse(httpGetSync("/attachment/select?id=" + phoneId));

	targetContainer.innerHTML += Mustache.render(template, attachment);

	document.getElementById("file-form").remove();
}

function openAttachmentCreate() {
	var targetContainer = document.getElementById("popup");
	var template = document.getElementById("attachment-popup-template").innerHTML;

	targetContainer.innerHTML += Mustache.render(template, Object.create(null));

	document.getElementsByClassName("apply")[1].innerText = "Create";
}

function getAttachment() {
	var attachment = JSON.parse(httpGetSync("/attachment/init"));

	attachment.contactId = new URL(window.location).searchParams.get("id");

	attachment.name = document.getElementById("file-name").value;
	attachment.comment = document.getElementById("comment").value;

	return attachment;
}

function editAttachment() {
	var attachment = getAttachment();

	var numberId = document.getElementById("edit-attachment").className;
	if (numberId) {
		attachment.id = numberId;
		editedAttachments[attachment.id] = attachment;
	} else {
		newAttachments.push(attachment);
		uploadAttachments.push(document.getElementById("file").files[0]);
	}

	closeAttachmentPopup();
}

function closeAttachmentPopup() {
	document.getElementById("edit-attachment").remove();
}