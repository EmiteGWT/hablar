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
import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.page.PagePresenter;
import com.calclab.hablar.core.client.ui.icon.HablarIcons;
import com.calclab.hablar.core.client.ui.icon.HablarIcons.IconType;
import com.calclab.hablar.core.client.ui.menu.Action;
import com.calclab.hablar.core.client.ui.menu.PopupMenu;
import com.calclab.suco.client.Suco;
import com.calclab.suco.client.events.Listener;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class RosterPage extends PagePresenter<RosterDisplay> {
    private static int index = 0;
    public static final String TYPE = "Roster";

    public static RosterPage asRoster(final Page<?> page) {
	if (TYPE.equals(page.getType())) {
	    return (RosterPage) page;
	} else {
	    return null;
	}
    }
    private boolean active;
    private final Roster roster;
    private final HashMap<XmppURI, RosterItemPresenter> items;
    private final ChatManager manager;

    private final PopupMenu<RosterItem> itemMenu;

    public RosterPage(final HablarEventBus eventBus, final RosterDisplay display) {
	super(TYPE, "" + ++index, eventBus, display);
	manager = Suco.get(ChatManager.class);
	roster = Suco.get(Roster.class);
	items = new HashMap<XmppURI, RosterItemPresenter>();
	active = true;
	itemMenu = new PopupMenu<RosterItem>("hablar-RosterPresenterMenu");

	addRosterListeners();
	addSessionListeners();
	getState().init(HablarIcons.get(IconType.roster), i18n().contacts());
    }

    public void addAction(final Action<RosterPage> action) {
	display.addAction(action.getIconStyle(), action.getId(), new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		action.execute(RosterPage.this);
	    }
	});
    }

    /**
     * Use addAction(Action ...)
     * 
     * @param iconStyle
     * @param debugId
     * @param clickHandler
     */
    @Deprecated
    public void addAction(final String iconStyle, final String debugId, final ClickHandler clickHandler) {
	display.addAction(iconStyle, debugId, clickHandler);
    }

    private void addRosterListeners() {
	roster.onRosterRetrieved(new Listener<Collection<RosterItem>>() {
	    @Override
	    public void onEvent(final Collection<RosterItem> items) {
		loadRoster(items);
	    }

	});

	roster.onItemAdded(new Listener<RosterItem>() {
	    @Override
	    public void onEvent(final RosterItem item) {
		getPresenter(item);
		// FIXME: i18n
		final String msg = item.getName() + " has been added to Contacts.";
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
		final String msg = item.getJID().getNode() + " has been removed from Contacts.";
		getState().setUserMessage(msg);
	    }
	});

	GWT.log("ROSTER READY: " + roster.isRosterReady());
	if (roster.isRosterReady()) {
	    loadRoster(roster.getItems());
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

    private RosterItemPresenter createRosterItem(final RosterItem item) {
	final RosterItemDisplay itemDisplay = display.newRosterItemDisplay();
	final RosterItemPresenter presenter = new RosterItemPresenter(itemDisplay);
	display.add(itemDisplay);
	items.put(item.getJID(), presenter);
	itemDisplay.getAction().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		manager.open(item.getJID());
	    }
	});
	itemDisplay.getMenuAction().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		event.preventDefault();
		final Element element = event.getRelativeElement();
		final int width = element.getClientWidth();
		itemMenu.setTarget(item);
		itemMenu.show(element.getAbsoluteLeft() - width, element.getAbsoluteTop());
	    }
	});
	return presenter;
    }

    public PopupMenu<RosterItem> getItemMenu() {
	return itemMenu;
    }

    private RosterItemPresenter getPresenter(final RosterItem item) {
	RosterItemPresenter presenter = items.get(item.getJID());
	if (presenter == null) {
	    presenter = createRosterItem(item);
	}
	presenter.setItem(item);
	return presenter;
    }

    private void loadRoster(final Collection<RosterItem> items) {
	GWT.log("ROSTER LOAD!");
	for (final RosterItem item : items) {
	    getPresenter(item);
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
