package com.calclab.hablar.editbuddy.client;

import com.google.gwt.i18n.client.Messages;
import com.google.gwt.i18n.client.LocalizableResource.DefaultLocale;

@DefaultLocale("en")
public interface EditBuddyMessages extends Messages {
    @DefaultMessage("Change nick name")
    String changeNickName();

    @DefaultMessage("Write the new nick name:")
    String changeNickNameMessage();

    @DefaultMessage("Nick name can''t be empty")
    String nickNameEmpty();
}
