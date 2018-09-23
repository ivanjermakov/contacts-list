function httpGet(theUrl, callback) {
	var xmlHttp = new XMLHttpRequest();
	xmlHttp.onreadystatechange = function () {
		if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
			callback(xmlHttp.responseText);
		}
	};
	xmlHttp.open("GET", theUrl, true);
	xmlHttp.send(null);
}

function httpGetSync(url) {
	var xmlHttp = new XMLHttpRequest();
	xmlHttp.open("GET", url, false);
	xmlHttp.send(null);
	return xmlHttp.responseText;
}

function httpPost(url, obj, callback) {
	console.log(obj);

	var xmlHttp = new XMLHttpRequest();
	xmlHttp.onreadystatechange = function () {
		if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
			callback(xmlHttp.responseText);
		}
	};
	xmlHttp.open("POST", url, true);

	xmlHttp.setRequestHeader("Content-Type", "application/json");

	xmlHttp.send(JSON.stringify(obj));
}

function httpPostSync(url, obj) {
	var xmlHttp = new XMLHttpRequest();
	xmlHttp.open("POST", url, false);

	xmlHttp.setRequestHeader("Content-Type", "application/json");

	xmlHttp.send(obj);
	return xmlHttp.responseText;
}