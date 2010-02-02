package com.calclab.hablar.openchat.client;

import java.util.List;

import com.calclab.hablar.basic.client.ui.HablarWidget;
import com.calclab.hablar.basic.client.ui.icon.HablarIcons;
import com.calclab.hablar.basic.client.ui.icon.HablarIcons.IconType;
import com.calclab.hablar.basic.client.ui.page.PageView;
import com.calclab.hablar.basic.client.ui.pages.Pages;
import com.calclab.hablar.openchat.client.ui.OpenChatPresenter;
import com.calclab.hablar.openchat.client.ui.OpenChatWidget;
import com.calclab.hablar.roster.client.RosterView;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class HablarOpenChat implements EntryPoint {

    public static void install(final HablarWidget hablar) {
	Pages pages = hablar.getPages();
	final OpenChatPresenter openChat = new OpenChatPresenter(hablar, new OpenChatWidget());

	String iconStyle = HablarIcons.get(IconType.chatAdd);
	List<PageView> rosters = pages.getPagesOfType(RosterView.TYPE);
	for (PageView roster : rosters) {
	    ((RosterView) roster).addAction(iconStyle, "HablarOpenChat-openAction", new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
		    hablar.showOverlay(openChat.getDisplay());
		}
	    });
	}
    }

    @Override
    public void onModuleLoad() {
    }

}
