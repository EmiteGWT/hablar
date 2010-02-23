package com.calclab.hablar.chat.client.ui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ChatMessageFormatterTest {
    @Test
    public void formatEmotiAfter() {
	final String message = ":) ";
	final String format = ChatMessageFormatter.preFormatEmoticons(message);
	assertFalse(format.equals(message));
    }

    @Test
    public void formatEmotiAlone() {
	final String message = ":)";
	final String format = ChatMessageFormatter.preFormatEmoticons(message);
	assertFalse(format.equals(message));
    }

    @Test
    public void formatEmotiEnd() {
	final String message = " :)";
	final String format = ChatMessageFormatter.preFormatEmoticons(message);
	assertFalse(format.equals(message));
    }

    @Test
    public void formatNewlines() {
	assertEquals("first line<br>\nsecond line<br>\n", ChatMessageFormatter
		.formatNewLines("first line\nsecond line\n"));
    }

    @Test
    public void formatSpaceMultiEmoti() {
	final String message = ":) :) :)";
	final String format = ChatMessageFormatter.preFormatEmoticons(message);
	assertFalse(format.equals(message));
    }

    @Test
    public void formatURLs() {
	final String[] urls = new String[] { "http://emite.googlecode.com/", "ftp://debian.org/"
	// commented while we find a way to sanitizing URLs and prevent CSS
	// "http://www.google.com/search?hl=es&rls=GGGL%2CGGGL%3A2006-28%2CGGGL%3Aes&q=emite&btnG=Buscar&lr=",
	// "http://del.icio.us/search/?fr=del_icio_us&p=xmpp+gwt&type=all"
	};
	for (final String url : urls) {
	    final String result = ChatMessageFormatter.formatUrls(url);
	    assertContains(result, "<a");
	    assertContains(result, "href=\"" + url + "\"");
	}
    }

    @Test
    public void onlySpaces() {
	final String message = "a:)a";
	final String format = ChatMessageFormatter.preFormatEmoticons(message);
	assertEquals(format, message);
    }

    @Test
    public void preserveXml() {
	final String message = "<message from='room@rooms.domain/other' to='user@domain/resource' "
		+ "type='groupchat'><body>the message body</body></message>";
	final String format = ChatMessageFormatter.preFormatEmoticons(message);
	assertEquals(format, message);
    }

    private void assertContains(final String result, final String expected) {
	assertTrue("Expected " + result + " to contain " + expected, result.indexOf(expected) > -1);
    }
}
