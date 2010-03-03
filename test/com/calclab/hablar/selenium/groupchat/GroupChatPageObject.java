package com.calclab.hablar.selenium.groupchat;

import org.openqa.selenium.RenderedWebElement;
import org.openqa.selenium.support.ByIdOrName;

import com.calclab.hablar.core.client.Idify;
import com.calclab.hablar.groupchat.client.HablarGroupChat;
import com.calclab.hablar.selenium.PageObject;

public class GroupChatPageObject extends PageObject {

    String CONVERT_ACTION = "gwt-debug-" + HablarGroupChat.ACTION_ID_CONVERT;

    public RenderedWebElement getConvertAction(final String jid) {
	final String id = Idify.id(HablarGroupChat.ACTION_ID_CONVERT, Idify.uriId(jid));
	return findElement(new ByIdOrName("gwt-debug-" + id));
    }
}
