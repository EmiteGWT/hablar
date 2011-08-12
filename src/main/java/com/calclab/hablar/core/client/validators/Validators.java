package com.calclab.hablar.core.client.validators;

import com.calclab.emite.core.client.xmpp.session.XmppSession;
import com.google.gwt.core.client.GWT;

public class Validators {

	// Original regexp from http://www.regular-expressions.info/email.html
	public static final String REGEXP_VALID_JID = "^(?:([^@/<>'\\\"]+)@)?([^@/<>'\\\"]+)(?:/([^<>'\\\"]*))?$";
	protected static final String REGEXP_VALID_ROOM = "^[-a-zA-Z0-9 ]+$";
	public static final String EMAIL_REGEXP = "[-!#$%&\'*+/=?_`{|}~a-z0-9^]+(\\.[-!#$%&\'*+/=?_`{|}~a-z0-9^]+)*@(localhost|([a-z0-9]([-a-z0-9]*[a-z0-9])?\\.)+[a-z0-9]([-a-z0-9]*[a-z0-9]))+";

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
				return value.matches(EMAIL_REGEXP);
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

	public static Validator<String> notCurrentUser(final XmppSession session, final String message) {
		return new Validator<String>() {
			@Override
			public String getMessage() {
				return message;
			}

			@Override
			public boolean isValid(final String value) {
				final String currentJid = session.getCurrentUserURI().getJID().toString();
				final int slashIndex = value.indexOf('/');
				final String valueJid = slashIndex > 0 ? value.substring(0, slashIndex) : value;
				GWT.log("JID VALUE: " + valueJid);
				return !valueJid.equalsIgnoreCase(currentJid);
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
