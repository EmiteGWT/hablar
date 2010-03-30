package com.calclab.hablar.editbuddy.client;

import java.util.List;

import com.calclab.hablar.core.client.Hablar;
import com.calclab.hablar.core.client.HablarWidget;
import com.calclab.hablar.core.client.container.overlay.OverlayContainer;
import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.page.PagePresenter.Visibility;
import com.calclab.hablar.core.client.ui.menu.Action;
import com.calclab.hablar.core.client.ui.menu.SimpleAction;
import com.calclab.hablar.editbuddy.client.ui.EditBuddyWidget;
import com.calclab.hablar.roster.client.groups.RosterItemPresenter;
import com.calclab.hablar.roster.client.page.RosterPage;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

/**
 * Adds the ability to edit a buddy in the roster
 */
public class HablarEditBuddy implements EntryPoint {

    private static EditBuddyMessages editBuddyMessages;

    public static EditBuddyMessages i18n() {
	return editBuddyMessages;
    }

    public static void install(final Hablar hablar) {
	final EditBuddyPage editBuddy = new EditBuddyPage(hablar.getEventBus(), new EditBuddyWidget());
	hablar.addPage(editBuddy, OverlayContainer.ROL);
	final List<Page<?>> rosters = hablar.getPagesOfType(RosterPage.TYPE);
	for (final Page<?> page : rosters) {
	    final RosterPage roster = (RosterPage) page;
	    roster.getItemMenu().addAction(createEditBuddyAction(editBuddy));
	}
    }

    public static void install(final HablarWidget widget) {
	install(widget.getHablar());
    }

    public static void setMessages(final EditBuddyMessages messages) {
	editBuddyMessages = messages;
    }

    private static Action<RosterItemPresenter> createEditBuddyAction(final EditBuddyPage editBuddy) {
	return new SimpleAction<RosterItemPresenter>(i18n().changeNickName(), "EditBuddy-editAction") {
	    @Override
	    public void execute(final RosterItemPresenter target) {
		editBuddy.setItem(target.getItem());
		editBuddy.requestVisibility(Visibility.focused);
	    }
	};
    }

    @Override
    public void onModuleLoad() {
	EditBuddyMessages messages = (EditBuddyMessages) GWT.create(EditBuddyMessages.class);
	setMessages(messages);
	EditBuddyWidget.setMessages(messages);
    }

}
