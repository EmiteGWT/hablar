package com.calclab.hablar.search.client;

import com.calclab.hablar.search.client.page.SearchWidget;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

public class HablarSearchEntryPoint implements EntryPoint {

    @Override
    public void onModuleLoad() {
	final SearchMessages messages = (SearchMessages) GWT.create(SearchMessages.class);
	HablarSearch.setMessages(messages);
	SearchWidget.setMessages(messages);
    }

}
