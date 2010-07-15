package com.calclab.hablar.user.client;

import static com.calclab.hablar.user.client.HablarUser.i18n;

import java.util.ArrayList;

import com.calclab.emite.core.client.xmpp.session.Session;
import com.calclab.emite.core.client.xmpp.session.Session.State;
import com.calclab.emite.core.client.xmpp.stanzas.Presence;
import com.calclab.emite.core.client.xmpp.stanzas.Presence.Show;
import com.calclab.emite.im.client.presence.PresenceManager;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.page.PagePresenter;
import com.calclab.hablar.core.client.ui.icon.OldHablarIcons;
import com.calclab.suco.client.Suco;
import com.calclab.suco.client.events.Listener;
import com.google.gwt.core.client.GWT;
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
		updatePageState();
	    }
	});

	manager.onOwnPresenceChanged(new Listener<Presence>() {
	    @Override
	    public void onEvent(final Presence presence) {
		updatePageState();
	    }
	});

	updatePageState();
	model.setPageIcon(OldHablarIcons.getBundle().buddyIconOff());

	display.getClose().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		saveData();
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

    /**
     * Do not open the page if logged not logged in
     */
    @Override
    public void requestVisibility(final Visibility newVisibility) {
	final State state = session.getState();
	GWT.log("USERPAGE " + state + " " + newVisibility);
	if (state == State.ready || newVisibility == Visibility.hidden || newVisibility == Visibility.notFocused) {
	    super.requestVisibility(newVisibility);
	}
    }

    @Override
    public void setVisibility(final Visibility visibility) {
	for (final EditorPage<?> page : pages) {
	    page.setVisibility(visibility);
	    if (visibility == Visibility.focused) {
		page.showData();
	    }
	}
	super.setVisibility(visibility);
    }

    private void saveData() {
	for (final EditorPage<?> editor : pages) {
	    editor.saveData();
	}
	updatePageState();
	model.setCloseable(false);
    }

    private void setShow(final Show show) {
	if (show == Show.notSpecified || show == Show.chat) {
	    model.setPageIcon(OldHablarIcons.getBundle().buddyIconOn());
	} else if (show == Show.dnd) {
	    model.setPageIcon(OldHablarIcons.getBundle().buddyIconDnd());
	} else if (show == Show.xa) {
	    model.setPageIcon(OldHablarIcons.getBundle().buddyIconWait());
	}
    }

    private void updatePageState() {
	updatePageState(session.getState(), manager.getOwnPresence());
    }

    private void updatePageState(final State state, final Presence presence) {
	if (state == State.ready) {
	    String userStatus = presence != null ? presence.getStatus() : "";
	    userStatus = userStatus == null || userStatus.isEmpty() ? "" : " - " + userStatus;
	    model.setPageTitle(session.getCurrentUser().getShortName() + userStatus);
	    model.setPageTitleTooltip("Click here to change status");
	    setShow(presence != null ? presence.getShow() : Show.unknown);
	} else {
	    model.setPageTitle(i18n().notLoggedIn());
	    model.setPageIcon(OldHablarIcons.getBundle().buddyIconOff());
	    requestVisibility(Visibility.notFocused);
	}
    }

}
