function filePost(url, file, callback, error) {
	var formData = new FormData();
	formData.append("file", file);

	var xmlHttp = new XMLHttpRequest();
	xmlHttp.onreadystatechange = function () {
		if (xmlHttp.readyState === 4) {
			if (xmlHttp.status === 200) {
				callback(xmlHttp.responseText);
			} else {
				error(JSON.parse(xmlHttp.responseText));
			}
		}
	};

	xmlHttp.open("POST", url, true);
	xmlHttp.send(formData);
}

function filePostSync(url, file) {
	var formData = new FormData();
	formData.append("file", file);

	var xmlHttp = new XMLHttpRequest();
	xmlHttp.open("POST", url, false);
	xmlHttp.send(formData);

	return xmlHttp.responseText;
}

function httpGet(theUrl, callback, error) {
	var xmlHttp = new XMLHttpRequest();
	xmlHttp.onreadystatechange = function () {
		if (xmlHttp.readyState === 4) {
			if (xmlHttp.status === 200) {
				callback(xmlHttp.responseText);
			} else {
				error(JSON.parse(xmlHttp.responseText));
			}
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

function httpPost(url, obj, callback, error) {
	var xmlHttp = new XMLHttpRequest();
	xmlHttp.onreadystatechange = function () {
		if (xmlHttp.readyState === 4) {
			if (xmlHttp.status === 200) {
				callback(xmlHttp.responseText);
			} else {
				error(JSON.parse(xmlHttp.responseText));
			}
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