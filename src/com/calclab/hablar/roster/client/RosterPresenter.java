package com.calclab.hablar.roster.client;

import static com.calclab.hablar.core.client.i18n.Translator.i18n;

import java.util.Collection;
import java.util.HashMap;

import com.calclab.emite.core.client.xmpp.session.Session;
import com.calclab.emite.core.client.xmpp.session.Session.State;
import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.im.client.chat.ChatManager;
import com.calclab.emite.im.client.roster.Roster;
import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.PagePresenter;
import com.calclab.hablar.core.client.ui.icon.HablarIcons;
import com.calclab.hablar.core.client.ui.icon.HablarIcons.IconType;
import com.calclab.hablar.core.client.ui.menu.PopupMenu;
import com.calclab.suco.client.Suco;
import com.calclab.suco.client.events.Listener;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class RosterPresenter extends PagePresenter<RosterDisplay> {
    private static int index = 0;
    public static final String TYPE = "Roster";
    private boolean active;
    private final Roster roster;
    private final HashMap<XmppURI, RosterItemPresenter> items;
    private final ChatManager manager;
    private PopupMenu<RosterItem> itemMenu;

    public RosterPresenter(HablarEventBus eventBus, RosterDisplay display) {
	super(TYPE, "" + (++index), eventBus, display);
	manager = Suco.get(ChatManager.class);
	roster = Suco.get(Roster.class);
	items = new HashMap<XmppURI, RosterItemPresenter>();
	active = true;

	getState().init(HablarIcons.get(IconType.roster), i18n().contacts());

	addRosterListeners();
	addSessionListeners();

    }

    public void addAction(String iconStyle, String debugId, ClickHandler clickHandler) {
	display.addAction(iconStyle, debugId, clickHandler);
    }

    public PopupMenu<RosterItem> getItemMenu() {
	if (itemMenu == null) {
	    itemMenu = new PopupMenu<RosterItem>("hablar-RosterPresenterMenu");
	}
	return itemMenu;
    }

    private void addRosterListeners() {
	roster.onRosterRetrieved(new Listener<Collection<RosterItem>>() {
	    @Override
	    public void onEvent(final Collection<RosterItem> items) {
		GWT.log("Retrieved roster", null);
		for (final RosterItem item : items) {
		    getPresenter(item);
		}
	    }

	});

	roster.onItemAdded(new Listener<RosterItem>() {
	    @Override
	    public void onEvent(final RosterItem item) {
		getPresenter(item);
		// FIXME: i18n
		String msg = item.getName() + " has been added to Contacts.";
		getState().setUserMessage(msg);
	    }
	});

	roster.onItemChanged(new Listener<RosterItem>() {
	    @Override
	    public void onEvent(final RosterItem item) {
		getPresenter(item).setItem(item);
	    }
	});

	roster.onItemRemoved(new Listener<RosterItem>() {
	    @Override
	    public void onEvent(final RosterItem item) {
		display.remove(getPresenter(item).getDisplay());
		// FIXME: i18n
		String msg = item.getJID().getNode() + " has been removed from Contacts.";
		getState().setUserMessage(msg);
	    }
	});
    }

    private void addSessionListeners() {
	final Session session = Suco.get(Session.class);
	session.onStateChanged(new Listener<Session>() {
	    @Override
	    public void onEvent(final Session session) {
		State state = session.getState();
		setSessionState(state);
	    }

	});
	setSessionState(session.getState());
    }

    private RosterItemPresenter createRosterItem(final RosterItem item) {
	RosterItemDisplay itemDisplay = display.newRosterItemDisplay();
	RosterItemPresenter presenter = new RosterItemPresenter(itemDisplay);
	display.add(itemDisplay);
	items.put(item.getJID(), presenter);
	itemDisplay.getAction().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		manager.open(item.getJID());
	    }
	});
	itemDisplay.getMenuAction().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		event.preventDefault();
		Element element = event.getRelativeElement();
		int width = element.getClientWidth();
		getItemMenu().show(element.getAbsoluteLeft() - width, element.getAbsoluteTop());
	    }
	});
	return presenter;
    }

    private RosterItemPresenter getPresenter(final RosterItem item) {
	RosterItemPresenter presenter = items.get(item.getJID());
	if (presenter == null) {
	    presenter = createRosterItem(item);
	}
	presenter.setItem(item);
	return presenter;
    }

    private void setSessionState(State state) {
	final boolean isActive = state == State.ready;
	if (active != isActive) {
	    active = isActive;
	    display.setActive(active);
	}
    }

}
