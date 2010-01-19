package com.calclab.hablar.client.selenium;

import org.openqa.selenium.RenderedWebElement;
import org.openqa.selenium.support.FindBy;

import com.calclab.hablar.client.chat.ChatPage;
import com.calclab.hablar.client.ui.debug.Debug;

public class ChatPageObject extends AbstractPageObject {
    @FindBy(id = Debug.PRE + ChatPage.ID)
    private RenderedWebElement header;
    @FindBy(id = Debug.PRE + ChatPage.TALKBOX_DEB_ID)
    private RenderedWebElement talkbox;
    @FindBy(id = Debug.PRE + ChatPage.LIST_DEB_ID)
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
}
