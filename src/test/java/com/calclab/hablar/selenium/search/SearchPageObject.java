package com.calclab.hablar.selenium.search;

import org.openqa.selenium.RenderedWebElement;
import org.openqa.selenium.support.ByIdOrName;
import org.openqa.selenium.support.FindBy;

import com.calclab.hablar.core.client.Idify;
import com.calclab.hablar.selenium.PageObject;

public class SearchPageObject extends PageObject {
    public static final String GWT_DEBUG_SEARCH_LOGIC_REMOVE_ITEM = "gwt-debug-SearchLogic-remove-item";

    public static final String GWT_DEBUG_SEARCH_LOGIC_ADD_ITEM = "gwt-debug-SearchLogic-add-item";

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

    @FindBy(id = GWT_DEBUG_SEARCH_LOGIC_REMOVE_ITEM)
    private RenderedWebElement searchRemoveBuddyAction;

    @FindBy(id = GWT_DEBUG_SEARCH_LOGIC_ADD_ITEM)
    private RenderedWebElement searchAddBuddyAction;

    @FindBy(id = "gwt-debug-SearchLogic-chat")
    private RenderedWebElement searchChatAction;

    public RenderedWebElement getAction() {
	return searchAction;
    }

    public RenderedWebElement getAddBuddyAction() {
	return searchAddBuddyAction;
    }

    public RenderedWebElement getChat(final String jid) {
	return findElement(new ByIdOrName("gwt-debug-HeaderWidget-Chat-" + Idify.uriId(jid)));
    }

    public RenderedWebElement getChatAction() {
	return searchChatAction;
    }

    public RenderedWebElement getHeader() {
	return header;
    }

    public RenderedWebElement getRemoveBuddyAction() {
	return searchRemoveBuddyAction;
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

    public void waitForMenuAddAction() {
	waitForId(GWT_DEBUG_SEARCH_LOGIC_ADD_ITEM);
    }

    public void waitForMenuRemoveAction() {
	waitForId(GWT_DEBUG_SEARCH_LOGIC_REMOVE_ITEM);
    }

    public void waitForResult(final String resultsMsg) {
	waitFor(message, resultsMsg);
    }

    public void waitForResultMenu(final String jid) {
	waitForId("gwt-debug-" + Idify.uriId(jid) + "-search-menu");
    }

    private RenderedWebElement findJid(final String jid) {
	return findElement(new ByIdOrName("gwt-debug-" + Idify.uriId(jid) + "-search-menu"));
    }

}
