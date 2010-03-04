package com.calclab.hablar.selenium.groupchat;

import org.openqa.selenium.RenderedWebElement;
import org.openqa.selenium.support.ByIdOrName;
import org.openqa.selenium.support.FindBy;

import com.calclab.hablar.core.client.Idify;
import com.calclab.hablar.groupchat.client.HablarGroupChat;
import com.calclab.hablar.selenium.PageObject;

public class GroupChatPageObject extends PageObject {

    String CONVERT_ACTION = "gwt-debug-" + HablarGroupChat.ACTION_ID_CONVERT;

    @FindBy(id = "gwt-debug-InviteToRoomWidget-invite")
    private RenderedWebElement openGroupChatAccept;

    public RenderedWebElement getConvertAction(final String jid) {
	final String id = Idify.id(HablarGroupChat.ACTION_ID_CONVERT, Idify.uriId(jid));
	return findElement(new ByIdOrName("gwt-debug-" + id));
    }

    public RenderedWebElement getOpenGroupChatAccept() {
	return openGroupChatAccept;
    }

    public RenderedWebElement getRoomHeader(final String id) {
	return findElement(new ByIdOrName("gwt-debug-HeaderWidget-Room-" + id));
    }

    public RenderedWebElement getRoomScroll(final String id) {
	return findElement(new ByIdOrName("gwt-debug-ChatWidget-scroll-Room-" + id));
    }

    public RenderedWebElement getRoomTextBox(final String id) {
	return findElement(new ByIdOrName("gwt-debug-ChatWidget-talkBox-Room-" + id));
    }

    public void waitForTextInRoom(final String id, final String text) {
	waitFor(getRoomScroll(id), text);
    }
}
