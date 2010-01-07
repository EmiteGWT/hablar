package com.calclab.hablar.client;

import com.calclab.hablar.client.chat.ChatManagerLogic;
import com.calclab.hablar.client.pages.PagesPanel;
import com.google.gwt.user.client.ui.Composite;

public class HablarWidget extends Composite {

    public HablarWidget(HablarConfig config) {
	PagesPanel panel = new PagesPanel();
	new HablarLogic(config, panel);
	new ChatManagerLogic(panel);
	initWidget(panel);
    }

}
