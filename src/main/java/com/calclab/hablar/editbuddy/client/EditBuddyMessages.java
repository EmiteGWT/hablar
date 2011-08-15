package com.calclab.hablar.editbuddy.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;
import com.google.gwt.i18n.client.LocalizableResource.DefaultLocale;

@DefaultLocale("en")
public interface EditBuddyMessages extends Messages {
	EditBuddyMessages msg = GWT.create(EditBuddyMessages.class);

	@DefaultMessage("Change buddy nickname")
	String changeBuddyNickname();

	@DefaultMessage("Change from:")
	String changeFromLabelText();

	@DefaultMessage("Change nickname")
	String changeNickName();

	@DefaultMessage("Write the new nickname:")
	String changeNickNameMessage();

	@DefaultMessage("Change to:")
	String changeToLabelText();

	@DefaultMessage("Nick name can''t be empty")
	String nickNameEmpty();
}
