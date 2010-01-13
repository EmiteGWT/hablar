package com.calclab.hablar.client.chat;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ChatMessageFormatterTest {
    @Test
    public void formatURLs() {
	final String[] urls = new String[] { "http://emite.googlecode.com/", "ftp://debian.org/",
		"http://www.google.com/search?hl=es&rls=GGGL%2CGGGL%3A2006-28%2CGGGL%3Aes&q=emite&btnG=Buscar&lr=",
		"http://del.icio.us/search/?fr=del_icio_us&p=xmpp+gwt&type=all" };
	for (final String url : urls) {
	    assertEquals("<a href=\"" + url + "\" target=\"_blank\">" + url + "</a>", ChatMessageFormatter
		    .formatUrls(url));
	}
    }
}
