package com.calclab.hablar.selenium.chat;

import org.openqa.selenium.RenderedWebElement;
import org.openqa.selenium.support.ByIdOrName;

import com.calclab.hablar.core.client.Idify;
import com.calclab.hablar.selenium.PageObject;

public class ChatPageObject extends PageObject {

    public RenderedWebElement getHeader(final String uri) {
	final String pageId = Idify.uriId(uri);
	final String headerId = "gwt-debug-HeaderWidget-Chat-" + pageId;
	return findElement(new ByIdOrName(headerId));
    }

    public RenderedWebElement getList(final String uri) {
	final String pageId = Idify.uriId(uri);
	final String id = "gwt-debug-ChatWidget-list-Chat-" + pageId;
	return findElement(new ByIdOrName(id));
    }

    public RenderedWebElement getPage(final String uri) {
	final String pageId = Idify.uriId(uri);
	final String headerId = "gwt-debug-ChatWidget-Chat-" + pageId;
	return findElement(new ByIdOrName(headerId));
    }

    public RenderedWebElement getSend(final String uri) {
	final String pageId = Idify.uriId(uri);
	final String id = "gwt-debug-ChatWidget-send-Chat-" + pageId;
	return findElement(new ByIdOrName(id));
    }

    public RenderedWebElement getTalkBox(final String uri) {
	final String pageId = Idify.uriId(uri);
	final String id = "gwt-debug-ChatWidget-talkBox-Chat-" + pageId;
	return findElement(new ByIdOrName(id));
    }

    public void waitFor(final String uri, final String text) {
	waitFor(getList(uri), text);
    }
}
