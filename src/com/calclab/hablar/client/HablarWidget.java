package com.calclab.hablar.client;

import com.calclab.hablar.client.HablarConfig.Layout;
import com.calclab.hablar.client.chat.ChatManagerLogic;
import com.calclab.hablar.client.pages.DockPages;
import com.calclab.hablar.client.pages.PagesAccordion;
import com.calclab.hablar.client.pages.TabPages;
import com.calclab.hablar.client.pages.PagesWidget;
import com.calclab.hablar.client.ui.HablarPanel;
import com.google.gwt.user.client.ui.Composite;

public class HablarWidget extends Composite {

    public HablarWidget(HablarConfig config) {
	PagesWidget pages = null;
	if (config.layout == Layout.accordion) {
	    pages = new PagesAccordion();
	} else if (config.layout == Layout.dock) {
	    pages = new DockPages();
	} else {
	    pages = new TabPages();
	}
	new HablarLogic(config, pages);
	new ChatManagerLogic(pages);
	HablarPanel panel = new HablarPanel(config, pages);
	initWidget(panel);
    }

}
