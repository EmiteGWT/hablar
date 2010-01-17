package com.calclab.hablar.client.selenium;

import org.openqa.selenium.RenderedWebElement;
import org.openqa.selenium.support.FindBy;

import com.calclab.hablar.client.search.SearchPageWidget;

public class SearchPageObject extends AbstractPageObject {
    @FindBy(id = Debug.PRE + SearchPageWidget.TERM)
    private RenderedWebElement term;

    @FindBy(id = Debug.PRE + SearchPageWidget.ID)
    private RenderedWebElement header;

    @FindBy(id = Debug.PRE + SearchPageWidget.MESSAGE)
    private RenderedWebElement message;

    public RenderedWebElement getHeader() {
	return header;
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

}
