package com.calclab.hablar.core.client.validators;

public class IsEmpty {

    private static final String REGEX = "^\\s*$";

    public static boolean is(final String string) {
	return string == null || string.matches(REGEX);
    }

    public static boolean not(final String string) {
	return !is(string);
    }

}
