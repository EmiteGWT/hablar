package com.calclab.hablar.client;

import com.calclab.hablar.client.chat.ChatManagerLogic;
import com.calclab.hablar.client.pages.PagesPanel;
import com.google.gwt.user.client.ui.Composite;

public class HablarWidget extends Composite {
    
    public HablarWidget() {
	PagesPanel panel = new PagesPanel();
	new HablarLogic(panel);
	new ChatManagerLogic(panel);
	initWidget(panel);
    }

}
