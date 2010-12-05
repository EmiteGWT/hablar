package com.calclab.hablar.user.client;

import static com.calclab.hablar.user.client.HablarUser.i18n;

import java.util.ArrayList;

import com.calclab.emite.core.client.events.StateChangedEvent;
import com.calclab.emite.core.client.events.StateChangedHandler;
import com.calclab.emite.core.client.xmpp.session.SessionStates;
import com.calclab.emite.core.client.xmpp.session.XmppSession;
import com.calclab.emite.core.client.xmpp.stanzas.Presence;
import com.calclab.emite.core.client.xmpp.stanzas.Presence.Show;
import com.calclab.emite.im.client.presence.PresenceManager;
import com.calclab.emite.im.client.presence.events.OwnPresenceChangedEvent;
import com.calclab.emite.im.client.presence.events.OwnPresenceChangedHandler;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.page.PagePresenter;
import com.calclab.hablar.core.client.ui.icon.Icons;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class UserPage extends PagePresenter<UserDisplay> {

    private static int index = 0;
    public static final String TYPE = "User";
    protected final XmppSession session;
    protected final PresenceManager manager;
    private final ArrayList<EditorPage<?>> pages;

    public UserPage(final XmppSession session, final PresenceManager manager, final HablarEventBus eventBus,
	    final UserDisplay display) {
	super(TYPE, "" + ++index, eventBus, display);

	this.session = session;
	this.manager = manager;
	pages = new ArrayList<EditorPage<?>>();

	setVisibility(Visibility.notFocused);

	session.addSessionStateChangedHandler(true, new StateChangedHandler() {
	    @Override
	    public void onStateChanged(final StateChangedEvent event) {
		updatePageState();
	    }
	});

	manager.addOwnPresenceChangedHandler(new OwnPresenceChangedHandler() {
	    @Override
	    public void onOwnPresenceChanged(final OwnPresenceChangedEvent event) {
		updatePageState();
	    }
	});

	model.setPageIcon(Icons.BUDDY_OFF);

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
	final boolean isReady = SessionStates.ready.equals(session.getSessionState());
	if (isReady || (newVisibility == Visibility.hidden) || (newVisibility == Visibility.notFocused)) {
	    super.requestVisibility(newVisibility);
	}
    }

    private void saveData() {
	for (final EditorPage<?> editor : pages) {
	    editor.saveData();
	}
	updatePageState();
	model.setCloseable(false);
    }

    private void setShow(final Show show) {
	if ((show == Show.notSpecified) || (show == Show.chat)) {
	    model.setPageIcon(Icons.BUDDY_ON);
	} else if (show == Show.dnd) {
	    model.setPageIcon(Icons.BUDDY_DND);
	} else if (show == Show.xa) {
	    model.setPageIcon(Icons.BUDDY_WAIT);
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

    private void updatePageState() {
	updatePageState(session.getSessionState(), manager.getOwnPresence());
    }

    private void updatePageState(final String sessionState, final Presence presence) {
	if (SessionStates.ready.equals(sessionState)) {
	    String userStatus = presence != null ? presence.getStatus() : "";
	    userStatus = (userStatus == null) || userStatus.isEmpty() ? "" : " - " + userStatus;
	    model.setPageTitle(session.getCurrentUserURI().getShortName() + userStatus);
	    model.setPageTitleTooltip("Click here to change status");
	    setShow(presence != null ? presence.getShow() : Show.unknown);
	} else {
	    model.setPageTitle(i18n().notLoggedIn());
	    model.setPageIcon(Icons.BUDDY_OFF);
	    requestVisibility(Visibility.notFocused);
	}
    }

}
