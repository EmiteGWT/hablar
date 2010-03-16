package com.calclab.hablar.core.client.validators;

public class Empty {

    private static final String REGEX = "^\\s*$";

    public static boolean is(final String string) {
	return string == null || string.matches(REGEX);
    }

}
