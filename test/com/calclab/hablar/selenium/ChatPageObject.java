package com.calclab.hablar.selenium;

import org.openqa.selenium.RenderedWebElement;
import org.openqa.selenium.support.FindBy;

import com.calclab.hablar.basic.client.ui.utils.DebugId;
import com.calclab.hablar.chat.client.ChatPage;

public class ChatPageObject extends AbstractPageObject {
    @FindBy(id = DebugId.PRE + ChatPage.ID)
    private RenderedWebElement header;
    @FindBy(id = DebugId.PRE + ChatPage.TALKBOX_DEB_ID)
    private RenderedWebElement talkbox;
    @FindBy(id = DebugId.PRE + ChatPage.LIST_DEB_ID)
    private RenderedWebElement list;

    public ChatPageObject() {
    }

    public RenderedWebElement getHeader() {
	return header;
    }

    public RenderedWebElement getList() {
	return list;
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
