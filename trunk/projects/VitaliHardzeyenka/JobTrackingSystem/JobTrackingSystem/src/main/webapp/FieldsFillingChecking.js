
var passwordMinLength = 6; // min length of the password

/**
 * Checking the length of password.
 * @param formName - name of form with data
 * @param firstPassword - password
 * @param passwordLengthIndicator - password length indicator
 * @param submit - submit button
 */
function checkPasswordValid(formName, password, passwordRelabilityIndicator, submit) {
	passwordRelabilityIndicatorElement = document.getElementById(passwordRelabilityIndicator); // password length indicator
	passwordLength = document.forms[formName].password.value.length; // password symbols number
	indicatorOffset = 10 * passwordLength - 100; // calculate indicator offset by the password symbol number
	
	// If indicator offset not full, movie it.
	passwordRelabilityIndicatorElement.style.marginLeft = (indicatorOffset < 0) ? indicatorOffset + "px" : "0px";
	
	// Set the indicator color by password symbols number.
	if (passwordLength < 4) {
		passwordRelabilityIndicatorElement.style.background = "#f00";
    } else if ((passwordLength >= 4) && (passwordLength < 6)) {
    	passwordRelabilityIndicatorElement.style.background = "#FF9F00";
    } else if ((passwordLength >= 6) && (passwordLength < 8)) {
    	passwordRelabilityIndicatorElement.style.background = "#CBFE01";
    } else if ((passwordLength >= 8)) {
    	passwordRelabilityIndicatorElement.style.background = "#0EFE01";
    }
}

/**
 * Compare passwords.
 * @param formName - form name with need data
 * @param password - first typed password
 * @param repeatPassword - repeated password
 * @param passwordCoincidenceIndicator password concidence indicator
 * @param submit - button submit
 */
function isPaswordsEquals(formName, password, repeatPassword, passwordCoincidenceIndicator, submit) {
	passwordElement = document.forms[formName].password; // first password
	passwordLength = document.forms[formName].password.value.length; // first password symbols number
	repeatePasswordElement = document.forms[formName].repeatPassword; // repeate password
	passwordConcidenceIndicatorElement = document.getElementById(passwordCoincidenceIndicator); // passwords equals indicator 
	submitElement = document.forms[formName].submit; // submit button
	
	if (passwordElement.value == repeatePasswordElement.value) {
		passwordConcidenceIndicatorElement.style.border = "1px solid #446B01";
		passwordConcidenceIndicatorElement.style.background = "#E0FFB3";
		passwordConcidenceIndicatorElement.style.color = "#558701";
		passwordConcidenceIndicatorElement.innerHTML = "Passwords match";

		// Make button able if symbols number more or equals min need number.
		if (passwordLength >= passwordMinLength) {
			submitElement.disabled = 0;
		}
	} else {
		passwordConcidenceIndicatorElement.style.border = "1px solid #A40004";
		passwordConcidenceIndicatorElement.style.background = "#FFD7E9";
		passwordConcidenceIndicatorElement.style.color = "#D5172B";
		passwordConcidenceIndicatorElement.innerHTML = "Passwords do not match";
		submitElement.disabled = 1; // make button disable
	}
}

/**
 * Check if all fields in specified form are filling.
 * @param form - form form fields checking 
 * @returns {Boolean}
 */
function checkFormForEmptyFields(form) {
	var errorMessage = null;
	
	for (var i = 0; i < form.elements.length; i++) {
		if (null != form.elements[i].getAttribute("required")) {
			if (isEmpty(form.elements[i].value)) { 
				errorMessage += "  " + form.elements[i].name + "\\n";
			}			
		}
	}
	
	if (errorMessage != null) {
		alert("Fields are not filling:\\n" + errorMessage);
		return false;
	}
}

/**
 * Check if specified field is empty.
 * @param field - field for checking.
 * @returns {Boolean}
 */
function isEmpty(field) {	
	for (var i = 0; i < field.length; i++) {
		if (" " != field.charAt(i)) {
			return false;
		}
	}
	return true;
}

function emailChecking(formName, fieldName, emailReliabilityIndicator) {
	emailReliabilityIndicatorElement = document.getElementById(emailReliabilityIndicator); // email reliability indicator
	emailReliabilityIndicatorElement.style.background = "#0EFE01";
	var regularExpression = /^\w+([\.-]?\w+)*@(((([a-z0-9]{2,})|([a-z0-9][-][a-z0-9]+))[\.][a-z0-9])|([a-z0-9]+[-]?))+[a-z0-9]+\.([a-z]{2}|(com|net|org|edu|int|mil|gov|arpa|biz|aero|name|coop|info|pro|museum))$/i;

	if (regularExpression.test(eval("document.forms['"+formName+"']."+fieldName+".value"))) {
		emailReliabilityIndicatorElement.style.marginLeft = "0px";
		emailReliabilityIndicatorElement.style.background = "#0EFE01";
		return true;
	} else {
		emailReliabilityIndicatorElement.style.marginLeft = "0px";
		emailReliabilityIndicatorElement.style.background = "#f00";		
		return false; 
	}
}


