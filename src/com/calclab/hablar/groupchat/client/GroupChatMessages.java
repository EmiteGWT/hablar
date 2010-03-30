package com.calclab.hablar.groupchat.client;

import com.google.gwt.i18n.client.Messages;
import com.google.gwt.i18n.client.LocalizableResource.DefaultLocale;

@DefaultLocale("en")
public interface GroupChatMessages extends Messages {
    @DefaultMessage("Convert this chat to group chat")
    String convertPageTitle();

    @DefaultMessage("Convert this chat into a group chat")
    String convertToGroupAction();

    @DefaultMessage("talk-{0}-{1}")
    String defaultRoomName(String user1, String user2);

    @DefaultMessage("Chat with this group")
    String openGroupChatAction();

}
