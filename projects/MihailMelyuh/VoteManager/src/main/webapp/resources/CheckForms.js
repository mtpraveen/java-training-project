function validateForm(form) {
	var element, elementName, value, type;

	var errorList = [];

	var errorText = {
		1 : "Not empty 'Email'",
		2 : "Not empty 'Password'",
		3 : "Do not select any variant",
		4 : "Not empty 'Question'",
		5 : "Not empty 'Variant'",
		6 : "Number of digits isn't equal 6",
		7 : "No selection"
	};

	var radioChecked = false;
	var radioIsExist = false;

	for ( var i = 0; i < form.elements.length; i++) {
		element = form.elements[i];
		elementName = element.nodeName.toLowerCase();
		value = element.value;
		if (elementName == "input") {
			type = element.type.toLowerCase();
			switch (type) {
			case "text":
				if (element.name == "email" && value == "") {
					errorList.push(1);
				} else if (element.name == "question" && value == "") {
					errorList.push(4);
				} else if (element.name == "variant" && value == "") {
					errorList.push(5);
				} else if (element.name == "telephone" && value.length != 6
						&& value.length != 0) {
					errorList.push(6);
				}
				break;
			case "password":
				if (element.name == "password" && value == "") {
					errorList.push(2);
				}
				break;
			case "radio":
				radioIsExist = true;
				if (element.checked == true) {
					radioChecked = true;
				}
				break;
			default:
				break;
			}
		}
	}

	if ((!radioChecked) && radioIsExist) {
		errorList.push(7);
	}

	if (!errorList.length)
		return true;

	var errorMsg = "Made the following errors:\n\n";
	for (i = 0; i < errorList.length; i++) {
		errorMsg += errorText[errorList[i]] + "\n";
	}
	alert(errorMsg);
	return false;
}
