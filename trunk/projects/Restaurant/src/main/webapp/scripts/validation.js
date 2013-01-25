function isDate(elem, errorElemId, irregularFormatDate, dateInThePast) {
	var str = elem.value;
	var re = /^\d{4}-\d{2}-\d{2}$/;
	if (!str.match(re)) {

		//elem.style.backgroundColor = "#FF3333";
		document.getElementById(errorElemId).innerHTML = irregularFormatDate;
		document.getElementById(errorElemId).style.color = "red";
	} else {

		if (new Date() > new Date(str)) {
			//elem.style.backgroundColor = "#FF3333";
			document.getElementById(errorElemId).innerHTML = dateInThePast;
			document.getElementById(errorElemId).style.color = "red";
		} else {
			//elem.style.backgroundColor = "#adff2f";
			document.getElementById(errorElemId).innerHTML = "";
		}
	}
}

function isNotNull(elem, errorElemId, messageForError) {
	var str = elem.value;
	if (str.length == 0) {
		//elem.style.backgroundColor = "#FF3333";
		document.getElementById(errorElemId).style.color = "red";
		document.getElementById(errorElemId).innerHTML = messageForError;
	} else {
		document.getElementById(errorElemId).innerHTML = "";
		//elem.style.backgroundColor = "#adff2f";
	}

}

function isLengthBetween(elem, min, max, errorElemId, messageForError) {
	var str = elem.value;
	if (str.length < min || str.length > max) {
		//elem.style.backgroundColor = "#FF3333";
		document.getElementById(errorElemId).style.color = "red";
		document.getElementById(errorElemId).innerHTML = messageForError;
	} else {
		document.getElementById(errorElemId).innerHTML = "";
		//elem.style.backgroundColor = "#adff2f";
	}
}

function isTelephone(elem, errorElemId, messageForError) {
	var str = elem.value;
	var re = /^\d{9}$/;
	if (!str.match(re)) {
		document.getElementById(errorElemId).innerHTML = messageForError;
		document.getElementById(errorElemId).style.color = "red";
	} else {
			//elem.style.backgroundColor = "#adff2f";
			document.getElementById(errorElemId).innerHTML = "";
		}
}