package com.calclab.hablar.client.chat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ChatMessageFormatterTest {

    @Test
    public void formatNewlines() {
	assertEquals("first line<br>\nsecond line<br>\n", ChatMessageFormatter.format("first line\nsecond line\n"));
    }

    @Test
    public void formatURLs() {
	final String[] urls = new String[] { "http://emite.googlecode.com/", "ftp://debian.org/"
	// commented while we find a way to sanitizing URLs and prevent CSS
	// "http://www.google.com/search?hl=es&rls=GGGL%2CGGGL%3A2006-28%2CGGGL%3Aes&q=emite&btnG=Buscar&lr=",
	// "http://del.icio.us/search/?fr=del_icio_us&p=xmpp+gwt&type=all"
	};
	for (final String url : urls) {
	    final String result = ChatMessageFormatter.format(url);
	    assertContains(result, "<a");
	    assertContains(result, "href=\"" + url + "\"");
	}
    }

    private void assertContains(final String result, final String expected) {
	assertTrue("Expected " + result + " to contain " + expected, result.indexOf(expected) > -1);
    }
}
