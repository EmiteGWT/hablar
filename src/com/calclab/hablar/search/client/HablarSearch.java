package com.calclab.hablar.search.client;

import java.util.List;

import com.calclab.hablar.basic.client.ui.HablarView;
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

    public static void install(HablarView view) {

	installSearch(view.getPages());

    }

    private static void installSearch(final Pages pages) {
	GWT.log("INSTALL SEARCH", null);

	List<PageView> rosters = pages.getPagesOfType(RosterView.TYPE);
	boolean isRoster = rosters.size() > 0;

	String iconStyle = HablarIcons.get(IconType.search);
	String debugId = "HablarLogic-searchAction";
	Visibility visibility = isRoster ? Visibility.closed : Visibility.notFocused;
	final SearchPageWidget searchPage = new SearchPageWidget(visibility, isRoster);
	pages.add(searchPage);

	for (PageView page : rosters) {
	    GWT.log("INSTALL ON ROSTER", null);
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
