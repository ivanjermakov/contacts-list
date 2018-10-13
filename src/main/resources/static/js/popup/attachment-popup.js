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
	dimBackground();

	document.getElementById("file-form").remove();
}

function openAttachmentCreate() {
	var targetContainer = document.getElementById("popup");
	var template = document.getElementById("attachment-popup-template").innerHTML;

	targetContainer.innerHTML += Mustache.render(template, Object.create(null));
	dimBackground();

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

	var attachmentId = document.getElementById("edit-attachment").className;
	if (attachmentId) {
		attachment.id = attachmentId;
		editedAttachments[attachment.id] = attachment;

		// show edited attachment on edit page
		deleteAttachment(attachmentId);
	} else {
		newAttachments.push(attachment);
		uploadAttachments.push(document.getElementById("file").files[0]);
	}

	displayAttachment(attachment);
	closeAttachmentPopup();
}

function deleteAttachment(id) {
	document.getElementsByClassName("attachment " + id)[0].remove();
}

function closeAttachmentPopup() {
	document.getElementById("edit-attachment").remove();
	clearBackground();
}