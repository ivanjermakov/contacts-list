function editAvatarPopup() {
	var targetContainer = document.getElementById("popup");
	var template = document.getElementById("avatar-popup-template").innerHTML;

	targetContainer.innerHTML += Mustache.render(template, Object.create(null));
	dimBackground();
}

function getAvatar() {
	var avatar = JSON.parse(httpGetSync("/avatar/init"));

	avatar.contactId = new URL(window.location).searchParams.get("id");

	return avatar;
}

function editAvatar() {
	avatar = document.getElementById("file").files[0];

	closeAvatarPopup();
}

function closeAvatarPopup() {
	document.getElementById("edit-avatar").remove();
	clearBackground();
}