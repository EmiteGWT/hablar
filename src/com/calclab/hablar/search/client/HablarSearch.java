package com.calclab.hablar.search.client;

import java.util.List;

import com.calclab.hablar.basic.client.Hablar;
import com.calclab.hablar.basic.client.ui.EventBus;
import com.calclab.hablar.basic.client.ui.icon.HablarIcons;
import com.calclab.hablar.basic.client.ui.icon.HablarIcons.IconType;
import com.calclab.hablar.basic.client.ui.page.PageView;
import com.calclab.hablar.basic.client.ui.page.PageView.Visibility;
import com.calclab.hablar.basic.client.ui.pages.Pages;
import com.calclab.hablar.roster.client.RosterView;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class HablarSearch {

    public static void install(Hablar view) {

	installSearch(view.getEventBus(), view.getPages());

    }

    private static void installSearch(EventBus eventBus, final Pages pages) {
	GWT.log("INSTALL SEARCH", null);

	List<PageView> rosters = pages.getPagesOfType(RosterView.TYPE);
	boolean isRoster = rosters.size() > 0;

	String iconStyle = HablarIcons.get(IconType.search);
	String debugId = "HablarLogic-searchAction";
	Visibility visibility = isRoster ? Visibility.closed : Visibility.notFocused;
	final SearchPageWidget searchPage = new SearchPageWidget(eventBus, visibility, isRoster);
	pages.add(searchPage);

	for (PageView page : rosters) {
	    RosterView rosterView = (RosterView) page;
	    rosterView.addAction(iconStyle, debugId, new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
		    pages.open(searchPage);
		}
	    });
	}
    }
}
