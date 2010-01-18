package com.calclab.hablar.client;

import com.calclab.hablar.client.HablarConfig.Layout;
import com.calclab.hablar.client.chat.ChatConfig;
import com.calclab.hablar.client.chat.ChatManagerLogic;
import com.calclab.hablar.client.ui.HablarPanel;
import com.calclab.hablar.client.ui.pages.AbstractPages;
import com.calclab.hablar.client.ui.pages.AccordionPages;
import com.calclab.hablar.client.ui.pages.TabPages;
import com.google.gwt.user.client.ui.Composite;

public class HablarWidget extends Composite {

    public HablarWidget(HablarConfig config) {
	AbstractPages pages = null;
	if (config.layout == Layout.accordion) {
	    pages = new AccordionPages();
	} else if (config.layout == Layout.tabs) {
	    pages = new TabPages();
	} else {
	    throw new RuntimeException("Layout not configured.");
	}
	new HablarLogic(config, pages);
	new ChatManagerLogic(ChatConfig.getFromMeta(), pages);
	HablarPanel panel = new HablarPanel(config, pages);
	initWidget(panel);
    }

}
