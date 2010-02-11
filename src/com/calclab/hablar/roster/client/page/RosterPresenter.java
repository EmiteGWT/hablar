package com.calclab.hablar.roster.client.page;

import static com.calclab.hablar.core.client.i18n.Translator.i18n;

import java.util.Collection;
import java.util.HashMap;

import com.calclab.emite.core.client.xmpp.session.Session;
import com.calclab.emite.core.client.xmpp.session.Session.State;
import com.calclab.emite.im.client.roster.Roster;
import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.page.PagePresenter;
import com.calclab.hablar.core.client.ui.icon.HablarIcons;
import com.calclab.hablar.core.client.ui.icon.HablarIcons.IconType;
import com.calclab.hablar.core.client.ui.menu.Action;
import com.calclab.hablar.core.client.ui.menu.Menu;
import com.calclab.hablar.core.client.ui.menu.MenuDisplay;
import com.calclab.hablar.roster.client.ui.groups.RosterGroupDisplay;
import com.calclab.hablar.roster.client.ui.groups.RosterGroupPresenter;
import com.calclab.suco.client.Suco;
import com.calclab.suco.client.events.Listener;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

/**
 * The roster page presenter.
 * 
 * @see RosterPage
 */
public class RosterPresenter extends PagePresenter<RosterDisplay> implements RosterPage {
    private static int index = 0;

    public static RosterPage asRoster(final Page<?> page) {
	if (TYPE.equals(page.getType())) {
	    return (RosterPage) page;
	} else {
	    return null;
	}
    }
    private boolean active;
    private final Roster roster;
    private final Menu<RosterItem> itemMenu;
    private final HashMap<String, RosterGroupPresenter> groups;

    public RosterPresenter(final HablarEventBus eventBus, final RosterDisplay display) {
	super(TYPE, "" + ++index, eventBus, display);
	roster = Suco.get(Roster.class);

	groups = new HashMap<String, RosterGroupPresenter>();
	active = true;

	final MenuDisplay<RosterItem> menuDisplay = display.newRosterItemMenuDisplay("hablar-RosterPresenterMenu");
	itemMenu = new Menu<RosterItem>(menuDisplay);

	addRosterListeners();
	addSessionListeners();
	getState().init(HablarIcons.get(IconType.roster), i18n().contacts());
    }

    @Override
    public void addAction(final Action<RosterPage> action) {
	display.addAction(action.getIconStyle(), action.getId(), new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		action.execute(RosterPresenter.this);
	    }
	});
    }

    private void addRosterListeners() {
	roster.onRosterRetrieved(new Listener<Collection<RosterItem>>() {
	    @Override
	    public void onEvent(final Collection<RosterItem> items) {
		loadRoster();
	    }

	});

	roster.onItemAdded(new Listener<RosterItem>() {
	    @Override
	    public void onEvent(final RosterItem item) {
		// getPresenter(item);
		final String msg = item.getName() + " has been added to Contacts.";
		getState().setUserMessage(msg);
	    }
	});

	roster.onItemChanged(new Listener<RosterItem>() {
	    @Override
	    public void onEvent(final RosterItem item) {
		// getPresenter(item).setItem(item);
	    }
	});

	roster.onItemRemoved(new Listener<RosterItem>() {
	    @Override
	    public void onEvent(final RosterItem item) {
		// display.remove(getPresenter(item).getDisplay());
		// FIXME: i18n
		final String msg = item.getJID().getNode() + " has been removed from Contacts.";
		getState().setUserMessage(msg);
	    }
	});

	if (roster.isRosterReady()) {
	    loadRoster();
	}
    }

    private void addSessionListeners() {
	final Session session = Suco.get(Session.class);
	session.onStateChanged(new Listener<Session>() {
	    @Override
	    public void onEvent(final Session session) {
		final State state = session.getState();
		setSessionState(state);
	    }

	});
	setSessionState(session.getState());
    }

    @Override
    public Menu<RosterItem> getItemMenu() {
	return itemMenu;
    }

    private void loadRoster() {
	GWT.log("LOAD ROSTER");
	groups.clear();
	final RosterGroupPresenter all = new RosterGroupPresenter("", itemMenu, display.newRosterGroupDisplay());
	groups.put("", all);
	display.addGroup(all);
	for (final String groupName : roster.getGroups()) {
	    final RosterGroupDisplay groupDisplay = display.newRosterGroupDisplay();
	    final RosterGroupPresenter group = new RosterGroupPresenter(groupName, itemMenu, groupDisplay);
	    groups.put(groupName, group);
	    display.addGroup(group);
	}
    }

    private void setSessionState(final State state) {
	final boolean isActive = state == State.ready;
	if (active != isActive) {
	    active = isActive;
	    display.setActive(active);
	}
    }

}
