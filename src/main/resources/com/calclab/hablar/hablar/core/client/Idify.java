package com.calclab.hablar.core.client;

import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;

/**
 * Selenium ids
 * 
 * Any chance to generate this class with GWT.create and remove it on
 * production?
 */
public class Idify {

    public static String id(final String text) {
	return text == null ? "" : text.replaceAll("[\\s]", "_");
    }

    public static String id(final String... strings) {
	String result = null;
	for (final String s : strings) {
	    result = result == null ? "" : result + "-";
	    result += id(s);
	}
	return result;
    }

    public static String id(final XmppURI jid) {
	return uriId(jid.toString());
    }

    public static String uriId(final String uri) {
	return uri == null ? "" : uri.replaceAll("[@/\\.]", "-");
    }

}
