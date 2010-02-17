package com.calclab.hablar.roster.client.page;

import static com.calclab.hablar.roster.client.HablarRoster.i18n;

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
import com.calclab.hablar.roster.client.groups.RosterGroupDisplay;
import com.calclab.hablar.roster.client.groups.RosterGroupPresenter;
import com.calclab.hablar.roster.client.groups.RosterItemPresenter;
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
    private final Menu<RosterItemPresenter> itemMenu;
    private final HashMap<String, RosterGroupPresenter> groups;
    private final Menu<RosterGroupPresenter> groupMenu;

    public RosterPresenter(final HablarEventBus eventBus, final RosterDisplay display) {
	super(TYPE, "" + ++index, eventBus, display);
	roster = Suco.get(Roster.class);

	groups = new HashMap<String, RosterGroupPresenter>();
	active = true;

	itemMenu = new Menu<RosterItemPresenter>(display.newRosterItemMenuDisplay("hablar-RosterItemMenu"));
	groupMenu = new Menu<RosterGroupPresenter>(display.newRosterGroupMenuDisplay("hablar-RosterGroupMenu"));

	addRosterListeners();
	addSessionListeners();
	getState().init(HablarIcons.get(IconType.roster), i18n().contacts());
    }

    @Override
    public void addAction(final Action<RosterPage> action) {

	display.createAction(action).addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		action.execute(RosterPresenter.this);
	    }
	});

    }

    @Override
    public Menu<RosterGroupPresenter> getGroupMenu() {
	return groupMenu;
    }

    @Override
    public Menu<RosterItemPresenter> getItemMenu() {
	return itemMenu;
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
		for (final String name : item.getGroups()) {
		    if (groups.get(name) == null) {
			createGroup(name);
		    }
		}
		final String msg = i18n().userAdded(item.getName());
		getState().setUserMessage(msg);
	    }
	});

	roster.onItemChanged(new Listener<RosterItem>() {
	    @Override
	    public void onEvent(final RosterItem item) {
	    }
	});

	roster.onItemRemoved(new Listener<RosterItem>() {
	    @Override
	    public void onEvent(final RosterItem item) {
		final String msg = i18n().userRemoved(item.getName());
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

    private void createGroup(final String groupName) {
	final RosterGroupDisplay groupDisplay = display.newRosterGroupDisplay();
	final RosterGroupPresenter group = new RosterGroupPresenter(groupName, itemMenu, groupDisplay);
	groups.put(groupName, group);
	display.addGroup(group, groupMenu);
    }

    private void loadRoster() {
	GWT.log("LOAD ROSTER");
	groups.clear();
	final RosterGroupPresenter all = new RosterGroupPresenter("", itemMenu, display.newRosterGroupDisplay());
	groups.put("", all);
	display.addGroup(all, groupMenu);
	for (final String groupName : roster.getGroups()) {
	    createGroup(groupName);
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
