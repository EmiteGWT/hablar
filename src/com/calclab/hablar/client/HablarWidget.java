package com.calclab.hablar.client;

import com.calclab.hablar.client.chat.ChatManagerLogic;
import com.calclab.hablar.client.pages.PagesAccordion;
import com.calclab.hablar.client.pages.PagesTabs;
import com.calclab.hablar.client.pages.PagesWidget;
import com.calclab.hablar.client.ui.HablarPanel;
import com.google.gwt.user.client.ui.Composite;

public class HablarWidget extends Composite {

    public HablarWidget(HablarConfig config) {
	PagesWidget pages = null;
	if (config.isAccordion) {
	    pages = new PagesAccordion();
	} else {
	    pages = new PagesTabs();
	}
	new HablarLogic(config, pages);
	new ChatManagerLogic(pages);
	HablarPanel panel = new HablarPanel(config, pages);
	initWidget(panel);
    }

}
