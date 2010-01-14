package com.calclab.hablar.client.chat;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ChatMessageFormatterTest {

    @Test
    public void shouldFormatURL() {
	final String[] urls = new String[] { "http://emite.googlecode.com/", "ftp://debian.org/",
		"http://www.google.com/search?hl=es&rls=GGGL%2CGGGL%3A2006-28%2CGGGL%3Aes&q=emite&btnG=Buscar&lr=",
		"http://del.icio.us/search/?fr=del_icio_us&p=xmpp+gwt&type=all" };
	for (final String url : urls) {
	    String result = ChatMessageFormatter.format(url);
	    assertContains(result, "<a");
	    assertContains(result, "href=\"" + url + "\"");
	}
    }

    private void assertContains(String result, String expected) {
	assertTrue("Expected " + result + " to contain " + expected, result.indexOf(expected) > -1);
    }
}
