package com.calclab.hablar.selenium.chat;

import org.openqa.selenium.RenderedWebElement;
import org.openqa.selenium.support.FindBy;

import com.calclab.hablar.basic.client.ui.utils.DebugId;
import com.calclab.hablar.chat.client.ui.ChatPageWidget;
import com.calclab.hablar.selenium.tools.PageObject;

public class ChatPageObject extends PageObject {
    @FindBy(id = DebugId.PRE + ChatPageWidget.ID)
    private RenderedWebElement header;
    @FindBy(id = DebugId.PRE + ChatPageWidget.TALKBOX_DEB_ID)
    private RenderedWebElement talkbox;
    @FindBy(id = DebugId.PRE + ChatPageWidget.LIST_DEB_ID)
    private RenderedWebElement list;

    @FindBy(id = "ChatPageWidget-send")
    private RenderedWebElement sendButton;

    public RenderedWebElement getHeader() {
	return header;
    }

    public RenderedWebElement getList() {
	return list;
    }

    public RenderedWebElement getSendButton() {
	return sendButton;
    }

    public RenderedWebElement getTalkBox() {
	return talkbox;
    }

    public RenderedWebElement Header() {
	return getHeader();
    }

    public RenderedWebElement TalkBox() {
	return getTalkBox();
    }

    public void waitFor(final String text) {
	waitFor(list, text);
    }
}
