package com.calclab.hablar.selenium.search;

import org.openqa.selenium.RenderedWebElement;
import org.openqa.selenium.support.ByIdOrName;
import org.openqa.selenium.support.FindBy;

import com.calclab.hablar.basic.client.ui.utils.DebugId;
import com.calclab.hablar.search.client.SearchPageLogic;
import com.calclab.hablar.search.client.SearchPageWidget;
import com.calclab.hablar.search.client.SearchResultItemWidget;
import com.calclab.hablar.selenium.tools.PageObject;

public class SearchPageObject extends PageObject {
    @FindBy(id = DebugId.PRE + SearchPageWidget.TERM_DEB_ID)
    private RenderedWebElement term;

    @FindBy(id = DebugId.PRE + SearchPageWidget.ID)
    private RenderedWebElement header;

    @FindBy(id = DebugId.PRE + SearchPageWidget.MESSAGE_DEB_ID)
    private RenderedWebElement message;

    @FindBy(id = "gwt-debug-SearchPageWidget-search")
    private RenderedWebElement searchButton;

    public RenderedWebElement ChatMenuItem() {
	return findElement(new ByIdOrName(DebugId.getGwtId(SearchPageLogic.CHAT_DEB_ID)));
    }

    public RenderedWebElement getHeader() {
	return header;
    }

    public RenderedWebElement getResultMenu(final String jid) {
	return findJid(SearchResultItemWidget.SEARCHRESULT_ITEM_MENU_DEB_ID, jid);
    }

    public RenderedWebElement getResultName(final String jid) {
	return findJid(SearchResultItemWidget.SEARCHRESULT_ITEM_NAME_DEB_ID, jid);
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

    private RenderedWebElement findJid(final String prefix, final String jid) {
	return findElement(new ByIdOrName(DebugId.getGwtId(DebugId.getFromJid(prefix, jid))));
    }

}
