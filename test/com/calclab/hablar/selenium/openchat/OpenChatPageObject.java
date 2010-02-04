package com.calclab.hablar.selenium.openchat;

import org.openqa.selenium.RenderedWebElement;
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

    public RenderedWebElement getAction() {
	return action;
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
