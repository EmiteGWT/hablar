package com.calclab.hablar.selenium.search;

import org.openqa.selenium.RenderedWebElement;
import org.openqa.selenium.support.FindBy;

import com.calclab.hablar.search.client.page.SearchResultItemWidget;
import com.calclab.hablar.selenium.PageObject;

public class SearchPageObject extends PageObject {
    @FindBy(id = "")
    private RenderedWebElement term;

    @FindBy(id = "")
    private RenderedWebElement header;

    @FindBy(id = "")
    private RenderedWebElement message;

    @FindBy(id = "")
    private RenderedWebElement searchButton;

    public RenderedWebElement ChatMenuItem() {
	return null;
    }

    public RenderedWebElement getAction() {
	return null;
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
	return null;
    }

}
