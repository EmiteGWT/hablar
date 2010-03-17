package com.calclab.hablar.selenium.clipboard;

import org.openqa.selenium.RenderedWebElement;
import org.openqa.selenium.support.FindBy;

import com.calclab.hablar.selenium.PageObject;

public class CopyToClipboardPageObject extends PageObject {

    @FindBy(id = "gwt-debug-CopyToClipboardAction")
    private RenderedWebElement action;
    @FindBy(id = "gwt-debug-CopyToClipboardWidget-cancel")
    private RenderedWebElement close;
    @FindBy(id = "gwt-debug-CopyToClipboardWidget-content")
    private RenderedWebElement content;

    public RenderedWebElement getAction() {
	return action;
    }

    public RenderedWebElement getClose() {
	return close;
    }

    public void waitForMessage(final String message) {
	waitFor(content, message);
    }

}
