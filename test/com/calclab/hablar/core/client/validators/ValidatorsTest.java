package com.calclab.hablar.core.client.validators;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ValidatorsTest {

    @Test
    public void dontMatchOnlyAT() {
	final String email = "me@";
	assertFalse(email.matches(Validators.EMAIL_REGEXP));
    }

    @Test
    public void dontMatchOnlyNode() {
	final String email = "me";
	assertFalse(email.matches(Validators.EMAIL_REGEXP));
    }

    @Test
    public void matchLocalhostEmail() {
	final String email = "me@localhost";
	assertTrue(email.matches(Validators.EMAIL_REGEXP));
    }

    @Test
    public void matchSimpleEmail() {
	final String email = "me@example.com";
	assertTrue(email.matches(Validators.EMAIL_REGEXP));
    }

    @Test
    public void matchSubdomainEmail() {
	final String email = "test100@some.example.com";
	assertTrue(email.matches(Validators.EMAIL_REGEXP));
    }
}
