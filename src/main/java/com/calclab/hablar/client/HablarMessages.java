package com.calclab.hablar.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.LocalizableResource.DefaultLocale;
import com.google.gwt.i18n.client.Messages;

@DefaultLocale("en")
public interface HablarMessages extends Messages {
	public static final HablarMessages msg = GWT.create(HablarMessages.class);

	@DefaultMessage("Accept")
	String acceptAction();

	@DefaultMessage("Close")
	String closeAction();
}
