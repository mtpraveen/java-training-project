package com.epam.training.logic;

/**
 * Types of messages that can be sent to server by client.
 * Types used for server recognize task it must to process with received from client data.
 * @author Gordeenko
 */
public enum MessageTypes {
	MESSAGE, // simple message, only text string
	LOG_ON, // account log on
	CREATE_ACCOUNT,  // create new account by client
	CHANGE_PASSWORD, // change password of existing account
	CREATE_APPLICATION,
	GET_REPORT,
	DISCONNECT_CLIENT, // disconnect client from server
	ADD_ACCOUNT, // create new account by administrator
	DELETE_ACCOUNT, // delete account by administrator
	STOP_SERVER; // stop server by administrator
}
