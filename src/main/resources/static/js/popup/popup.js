function dimBackground() {
	console.log("dim");
	document.getElementById("overlay").style.background = "rgba(0,0,0,0.5)"
}

function clearBackground() {
	console.log("clear");
	document.getElementById("overlay").style.backgroundColor = "transparent";
}