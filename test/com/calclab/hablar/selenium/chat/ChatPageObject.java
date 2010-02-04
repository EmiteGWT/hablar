package com.calclab.hablar.selenium.chat;

import org.openqa.selenium.RenderedWebElement;
import org.openqa.selenium.support.ByIdOrName;

import com.calclab.hablar.chat.client.ui.ChatPresenter;
import com.calclab.hablar.selenium.PageObject;

public class ChatPageObject extends PageObject {

    public RenderedWebElement getHeader(String uri) {
	String pageId = ChatPresenter.createId(uri);
	String headerId = "gwt-debug-AccordionHeaderWidget-Chat-" + pageId;
	return findElement(new ByIdOrName(headerId));
    }

    public RenderedWebElement getList(String uri) {
	String pageId = ChatPresenter.createId(uri);
	String id = "gwt-debug-ChatWidget-list-Chat-" + pageId;
	return findElement(new ByIdOrName(id));
    }

    public RenderedWebElement getPage(String uri) {
	String pageId = ChatPresenter.createId(uri);
	String headerId = "gwt-debug-ChatWidget-Chat-" + pageId;
	return findElement(new ByIdOrName(headerId));
    }

    public RenderedWebElement getSend(String uri) {
	String pageId = ChatPresenter.createId(uri);
	String id = "gwt-debug-ChatWidget-send-Chat-" + pageId;
	return findElement(new ByIdOrName(id));
    }

    public RenderedWebElement getTalkBox(String uri) {
	String pageId = ChatPresenter.createId(uri);
	String id = "gwt-debug-ChatWidget-talkBox-Chat-" + pageId;
	return findElement(new ByIdOrName(id));
    }

    public void waitFor(String uri, final String text) {
	waitFor(getList(uri), text);
    }
}
