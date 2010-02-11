package com.calclab.hablar.editbuddy.client;

import java.util.List;

import com.calclab.hablar.core.client.Hablar;
import com.calclab.hablar.core.client.HablarWidget;
import com.calclab.hablar.core.client.container.overlay.OverlayContainer;
import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.editbuddy.client.ui.EditBuddyWidget;
import com.calclab.hablar.roster.client.RosterPage;
import com.google.gwt.core.client.EntryPoint;

/**
 * Adds the ability to edit a buddy in the roster
 */
public class HablarEditBuddy implements EntryPoint {

    public static void install(Hablar hablar) {
	EditBuddyPage editBuddy = new EditBuddyPage(hablar.getEventBus(), new EditBuddyWidget());
	hablar.addPage(editBuddy, OverlayContainer.ROL);
	List<Page<?>> rosters = hablar.getPagesOfType(RosterPage.TYPE);
	for (Page<?> page : rosters) {
	    RosterPage roster = (RosterPage) page;
	    roster.getItemMenu().addAction(editBuddy.getAction());
	}
    }

    public static void install(HablarWidget widget) {
	install(widget.getHablar());
    }

    @Override
    public void onModuleLoad() {
    }

}
