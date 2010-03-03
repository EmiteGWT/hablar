package com.calclab.hablar.selenium.openchat;

import org.openqa.selenium.RenderedWebElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.calclab.hablar.selenium.PageObject;

public class OpenChatPageObject extends PageObject {

    @FindBy(id = "gwt-debug-OpenChatWidget-jabberId")
    private RenderedWebElement jabberId;

    @FindBy(id = "gwt-debug-OpenChatWidget-open")
    private RenderedWebElement open;

    @FindBy(id = "gwt-debug-OpenChatWidget-cancel")
    private RenderedWebElement cancel;

    @FindBy(id = "gwt-debug-HablarOpenChat-openAction")
    private RenderedWebElement action;

    @FindBy(className = "hablar-OpenChatWidget")
    private RenderedWebElement widget;

    @FindBy(id = "gwt-debug-OpenChatWidget-addToRoster-input")
    private RenderedWebElement addToRoster;

    public RenderedWebElement getAction() {
	return action;
    }

    public WebElement getAddToRoster() {
	return addToRoster;
    }

    public RenderedWebElement getCancel() {
	return cancel;
    }

    public RenderedWebElement getJabberId() {
	return jabberId;
    }

    public RenderedWebElement getOpen() {
	return open;
    }

    public RenderedWebElement getWidget() {
	return widget;
    }
}
