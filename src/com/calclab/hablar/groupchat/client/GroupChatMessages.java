package com.calclab.hablar.groupchat.client;

import com.google.gwt.i18n.client.Messages;
import com.google.gwt.i18n.client.LocalizableResource.DefaultLocale;
import com.google.gwt.i18n.client.LocalizableResource.GenerateKeys;

@DefaultLocale("en")
// Line below defaults to I18N_default.xlf, I18N_de.xlf, etc
// GenerateKeys defaults to MD5 hash of text and meaning
@GenerateKeys
public interface GroupChatMessages extends Messages {
    @DefaultMessage("Convert this chat to group chat")
    String convertPageTitle();

    @DefaultMessage("Convert this chat into a group chat")
    String convertToGroupAction();

    @DefaultMessage("talk-{0}-{1}")
    String defaultRoomName(String user1, String user2);

    @DefaultMessage("Open group chat")
    String openGroupChatAction();

}
