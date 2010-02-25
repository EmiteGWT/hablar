package com.calclab.hablar.core.client.validators;

public class Validators {

    // Original regexp from http://www.regular-expressions.info/email.html
    public static final String REGEXP_VALID_JID = "^(?:([^@/<>'\\\"]+)@)?([^@/<>'\\\"]+)(?:/([^<>'\\\"]*))?$";
    protected static final String REGEXP_VALID_ROOM = "^[-a-zA-Z0-9 ]+$";

    public static Validator<String> hasNotSpaces(final String message) {
	return new Validator<String>() {
	    @Override
	    public String getMessage() {
		return message;
	    }

	    @Override
	    public boolean isValid(final String value) {
		return value.indexOf(' ') == -1;
	    }
	};
    }

    public static Validator<String> isValidJid(final String message) {
	return new Validator<String>() {

	    @Override
	    public String getMessage() {
		return message;
	    }

	    @Override
	    public boolean isValid(final String value) {
		return value.matches(REGEXP_VALID_JID);
	    }
	};
    }

    public static Validator<String> isValidRoomName(final String message) {
	return new Validator<String>() {
	    @Override
	    public String getMessage() {
		return message;
	    }

	    @Override
	    public boolean isValid(final String value) {
		return value.matches(REGEXP_VALID_ROOM);
	    }
	};
    }

    public static Validator<String> notEmpty(final String message) {
	return new Validator<String>() {
	    @Override
	    public String getMessage() {
		return message;
	    }

	    @Override
	    public boolean isValid(final String value) {
		return value.length() > 0;
	    }

	};
    }

}
