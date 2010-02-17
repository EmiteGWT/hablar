package com.calclab.hablar.user.client;

import java.util.ArrayList;

import com.calclab.emite.core.client.xmpp.session.Session;
import com.calclab.emite.core.client.xmpp.session.Session.State;
import com.calclab.emite.core.client.xmpp.stanzas.Presence;
import com.calclab.emite.core.client.xmpp.stanzas.Presence.Show;
import com.calclab.emite.im.client.presence.PresenceManager;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.page.PagePresenter;
import com.calclab.hablar.core.client.ui.icon.HablarIcons;
import com.calclab.hablar.core.client.ui.icon.HablarIcons.IconType;
import com.calclab.suco.client.Suco;
import com.calclab.suco.client.events.Listener;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class UserPage extends PagePresenter<UserDisplay> {

    private static int index = 0;
    public static final String TYPE = "User";
    protected final Session session;
    protected final PresenceManager manager;
    private final ArrayList<EditorPage<?>> pages;

    public UserPage(final HablarEventBus eventBus, final UserDisplay display) {
	super(TYPE, "" + ++index, eventBus, display);
	session = Suco.get(Session.class);
	manager = Suco.get(PresenceManager.class);
	pages = new ArrayList<EditorPage<?>>();

	setVisibility(Visibility.notFocused);

	session.onStateChanged(new Listener<Session>() {
	    @Override
	    public void onEvent(final Session session) {
		updatePresence();
	    }
	});

	manager.onOwnPresenceChanged(new Listener<Presence>() {
	    @Override
	    public void onEvent(final Presence presence) {
		setState(presence);
	    }
	});

	updatePresence();
	model.setPageIcon(HablarIcons.get(IconType.buddyOff));

	display.getUpdate().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		saveData();
		requestVisibility(Visibility.notFocused);
	    }
	});
	display.getCancel().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		requestVisibility(Visibility.notFocused);
	    }
	});
    }

    public void addPage(final EditorPage<?> page) {
	display.addPage(page);
	pages.add(page);
    }

    public boolean contains(final Page<?> page) {
	return pages.contains(page);
    }

    @Override
    public void setVisibility(final Visibility visibility) {
	for (final EditorPage<?> page : pages) {
	    page.setVisibility(visibility);
	}
	super.setVisibility(visibility);
    }

    private void saveData() {
	for (final EditorPage<?> editor : pages) {
	    editor.saveData();
	}
	updatePresence();
	model.setCloseable(false);
    }

    private void setShow(final Show show) {
	if (show == Show.notSpecified || show == Show.chat) {
	    model.setPageIcon(HablarIcons.get(HablarIcons.IconType.buddyOn));
	} else if (show == Show.dnd) {
	    model.setPageIcon(HablarIcons.get(HablarIcons.IconType.buddyDnd));
	} else if (show == Show.xa) {
	    model.setPageIcon(HablarIcons.get(HablarIcons.IconType.buddyWait));
	}
    }

    private void setState(final Presence presence) {
	final State s = session.getState();
	if (s == State.disconnected) {
	    model.setPageIcon(HablarIcons.get(HablarIcons.IconType.buddyOff));
	} else if (s == State.ready) {
	    String userStatus = presence != null ? presence.getStatus() : "";
	    userStatus = userStatus == null || userStatus.isEmpty() ? "" : " - " + userStatus;
	    model.setPageTitle(session.getCurrentUser().getShortName() + userStatus);
	    model.setPageTitleTooltip("Click here to change status");
	    setShow(presence.getShow());
	}
    }

    private void updatePresence() {
	setState(manager.getOwnPresence());
    }

}
