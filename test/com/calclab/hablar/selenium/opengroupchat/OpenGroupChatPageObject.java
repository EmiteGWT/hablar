package com.calclab.hablar.selenium.opengroupchat;

import org.openqa.selenium.By;
import org.openqa.selenium.RenderedWebElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.calclab.hablar.selenium.PageObject;

public class OpenGroupChatPageObject extends PageObject {

    private final static String USER_LIST_WIDGET_ID = "gwt-debug-InviteToRoomWidget-list";

    @FindBy(id = "gwt-debug-HablarRooms-openRoom")
    private RenderedWebElement action;

    @FindBy(id = "gwt-debug-InviteToRoomWidget-roomName")
    private RenderedWebElement roomName;

    @FindBy(id = "gwt-debug-InviteToRoomWidget-message")
    private RenderedWebElement message;

    @FindBy(id = "gwt-debug-InviteToRoomWidget-invite")
    private RenderedWebElement invite;

    @FindBy(id = "gwt-debug-InviteToRoomWidget-cancel")
    private RenderedWebElement cancel;

    @FindBy(className = "hablar-OpenRoomWidget")
    private RenderedWebElement widget;

    public RenderedWebElement getAction() {
	return action;
    }

    public RenderedWebElement getCancel() {
	return cancel;
    }

    public RenderedWebElement getInvite() {
	return invite;
    }

    public RenderedWebElement getMessage() {
	return message;
    }

    public RenderedWebElement getRoomName() {
	return roomName;
    }

    public WebElement getUserSelect(final String jid) {
	// TODO This is a bit brittle - there must be a better way!
	return findElement(By.xpath("//*[@id='" + USER_LIST_WIDGET_ID
		+ "']//input[@type='checkbox' and (../../div[@class='jid']) = '" + jid + "']"));
    }

    public RenderedWebElement getWidget() {
	return widget;
    }
}
