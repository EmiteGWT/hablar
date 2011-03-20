package com.calclab.hablar.login.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

public class HablarLoginEntryPoint implements EntryPoint {

    @Override
    public void onModuleLoad() {
	final LoginMessages messages = (LoginMessages) GWT.create(LoginMessages.class);
	HablarLogin.setMessages(messages);
	LoginWidget.setMessages(messages);

    }

}
