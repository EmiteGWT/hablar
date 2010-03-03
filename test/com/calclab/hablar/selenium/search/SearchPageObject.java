package com.calclab.hablar.selenium.search;

import org.openqa.selenium.RenderedWebElement;
import org.openqa.selenium.support.ByIdOrName;
import org.openqa.selenium.support.FindBy;

import com.calclab.hablar.chat.client.ui.ChatPresenter;
import com.calclab.hablar.selenium.PageObject;

public class SearchPageObject extends PageObject {
    @FindBy(id = "gwt-debug-SearchWidget-term")
    private RenderedWebElement term;

    @FindBy(id = "gwt-debug-HeaderWidget-HablarSearch-1")
    private RenderedWebElement header;

    @FindBy(id = "gwt-debug-SearchWidget-message")
    private RenderedWebElement message;

    @FindBy(id = "gwt-debug-SearchWidget-search")
    private RenderedWebElement searchButton;

    @FindBy(id = "gwt-debug-HablarLogic-searchAction")
    private RenderedWebElement searchAction;

    @FindBy(id = "gwt-debug-SearchLogic-chat")
    private RenderedWebElement searchChatAction;

    public RenderedWebElement getAction() {
	return searchAction;
    }

    public RenderedWebElement getChat(final String jid) {
	return findElement(new ByIdOrName("gwt-debug-HeaderWidget-Chat-" + ChatPresenter.createId(jid)));
    }

    public RenderedWebElement getChatAction() {
	return searchChatAction;
    }

    public RenderedWebElement getHeader() {
	return header;
    }

    public RenderedWebElement getResultMenu(final String jid) {
	return findJid(jid);
    }

    public RenderedWebElement getSearchButton() {
	return searchButton;
    }

    public RenderedWebElement getTerm() {
	return term;
    }

    public RenderedWebElement Message() {
	return message;
    }

    public void waitForResult(final String resultsMsg) {
	waitFor(message, resultsMsg);
    }

    private RenderedWebElement findJid(final String jid) {
	return findElement(new ByIdOrName("gwt-debug-" + ChatPresenter.createId(jid) + "-search-menu"));
    }

}
