package com.calclab.hablar.vcard.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

public class HablarVCardEntryPoint implements EntryPoint {
    @Override
    public void onModuleLoad() {
	final VCardMessages messages = GWT.create(VCardMessages.class);
	I18nVCard.set(messages);
	VCardWidget.setMessages(messages);
	HablarVCard.setMessages(messages);
    }

}
