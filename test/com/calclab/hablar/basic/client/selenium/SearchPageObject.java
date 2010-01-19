package com.calclab.hablar.basic.client.selenium;

import org.openqa.selenium.RenderedWebElement;
import org.openqa.selenium.support.ByIdOrName;
import org.openqa.selenium.support.FindBy;

import com.calclab.hablar.basic.client.search.SearchLogic;
import com.calclab.hablar.basic.client.search.SearchPageWidget;
import com.calclab.hablar.basic.client.search.SearchResultItemWidget;
import com.calclab.hablar.basic.client.ui.utils.DebugId;

public class SearchPageObject extends AbstractPageObject {
    @FindBy(id = DebugId.PRE + SearchPageWidget.TERM_DEB_ID)
    private RenderedWebElement term;

    @FindBy(id = DebugId.PRE + SearchPageWidget.ID)
    private RenderedWebElement header;

    @FindBy(id = DebugId.PRE + SearchPageWidget.MESSAGE_DEB_ID)
    private RenderedWebElement message;

    public RenderedWebElement ChatMenuItem() {
	return findElement(new ByIdOrName(DebugId.getGwtId(SearchLogic.CHAT_DEB_ID)));
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

    public RenderedWebElement Header() {
	return getHeader();
    }

    public RenderedWebElement Message() {
	return message;
    }

    public void term(final String text) {
	term.clear();
	term.sendKeys(text + "\n");
    }

    public void waitForResult(final String resultsMsg) {
	waitFor(message, resultsMsg);
    }

    private RenderedWebElement findJid(final String prefix, final String jid) {
	return findElement(new ByIdOrName(DebugId.getGwtId(DebugId.getFromJid(prefix, jid))));
    }

}
