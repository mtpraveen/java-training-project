package net.epam.java.autobase;

public class AutobaseException extends Exception {
	AutobaseException(String message) {
		super(message);
	}
}

class AuthorizationException extends AutobaseException {
	AuthorizationException(String message) {
		super(message);
	}
}

class addUserException extends AutobaseException {
	addUserException(String message) {
		super(message);
	}
}

class PermissionsException extends RuntimeException {
	PermissionsException(String message) {
		super(message);
	}
}