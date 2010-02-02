package com.calclab.hablar.editbuddy.client;

import java.util.List;

import com.calclab.hablar.basic.client.Hablar;
import com.calclab.hablar.basic.client.ui.page.PageView;
import com.calclab.hablar.basic.client.ui.pages.Pages;
import com.calclab.hablar.editbuddy.client.ui.EditBuddyWidget;
import com.calclab.hablar.roster.client.RosterView;
import com.google.gwt.core.client.EntryPoint;

public class HablarEditBuddy implements EntryPoint {

    public static void install(Hablar hablar) {
	Pages pages = hablar.getPages();
	EditBuddyPresenter logic = new EditBuddyPresenter(hablar, new EditBuddyWidget());
	install(logic, pages);
    }

    private static void install(EditBuddyPresenter logic, Pages pages) {
	List<PageView> rosters = pages.getPagesOfType(RosterView.TYPE);
	for (PageView page : rosters) {
	    RosterView rosterView = (RosterView) page;
	    rosterView.getItemMenu().addAction(logic.getAction());
	}
    }

    @Override
    public void onModuleLoad() {
    }

}
