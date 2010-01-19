package com.calclab.hablar.client.roster;

import java.util.Collection;
import java.util.HashMap;

import com.calclab.emite.core.client.xmpp.session.Session;
import com.calclab.emite.core.client.xmpp.session.Session.State;
import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.im.client.chat.ChatManager;
import com.calclab.emite.im.client.roster.Roster;
import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.hablar.client.i18n.Msg;
import com.calclab.hablar.client.ui.lists.ListItemView;
import com.calclab.hablar.client.ui.lists.ListLogic;
import com.calclab.hablar.client.ui.menu.MenuAction;
import com.calclab.hablar.client.ui.menu.PopupMenuView;
import com.calclab.hablar.client.ui.styles.HablarStyles;
import com.calclab.suco.client.Suco;
import com.calclab.suco.client.events.Listener;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.UIObject;

public class RosterLogic implements ListLogic {

    public static final String CHAT_START_DEB_ID = "RosterLogic-chatStart-Action";
    public static final String CHAT_DEB_ID = "RosterLogic-chat";
    public static final String REMOVE_ROSTERITEM_DEB_ID = "RosterLogic-remove-rosteritem";
    public static final String ROSTERITEM_MENU_DEB_ID = "RosterLogic-remove-menu";

    private final Roster roster;
    private final ChatManager chatManager;
    private final HashMap<XmppURI, RosterItemView> items;
    private final RosterView view;
    private boolean active;
    private PopupMenuView<RosterItem> menu;
    private final Msg i18n;

    public RosterLogic(final RosterView view) {
	this.view = view;
	this.roster = Suco.get(Roster.class);
	this.items = new HashMap<XmppURI, RosterItemView>();
	this.chatManager = Suco.get(ChatManager.class);
	i18n = Suco.get(Msg.class);
	createMenu();

	addRosterListeners();
	addSessionListeners();

	view.addAction(HablarStyles.get(HablarStyles.IconType.chatAdd), CHAT_START_DEB_ID, new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		final String jid = Window.prompt("Write the JID of the person you want to chat with", "");
		chatManager.open(XmppURI.jid(jid));
	    }
	});

    }

    public void onItemClick(final ListItemView view, final Event event) {
	final RosterItemView rosterView = (RosterItemView) view;
	chatManager.open(rosterView.getItem().getJID());
    }

    @Override
    public void onMenuClicked(final ListItemView view, final UIObject ui) {
	final RosterItem item = ((RosterItemView) view).getItem();
	if (menu.isVisible()) {
	    GWT.log("menu visible!", null);
	    menu.hide();
	} else {
	    menu.setTarget(item);
	    menu.showRelativeToMenu(ui);
	}
    }

    @Override
    public void onMouseOver(final ListItemView view, final boolean over) {
	view.setSelected(over);
	view.setMenuVisible(over);
    }

    public void openChat(final XmppURI uri) {
	chatManager.open(uri);
    }

    /**
     * Returns a roster item view ALWAYS
     * 
     * @param item
     * @return
     */
    RosterItemView getOrCreateWidget(final RosterItem item) {
	RosterItemView itemWidget = items.get(item.getJID());
	if (itemWidget == null) {
	    itemWidget = view.createItemView();
	    items.put(item.getJID(), itemWidget);
	}
	RosterItemLogic.setItem(item, itemWidget);
	return itemWidget;
    }

    void onChatWith(final RosterItem item) {
	chatManager.open(item.getJID());
    }

    void onRemoveItem(final RosterItem item) {
	roster.removeItem(item.getJID());
    }

    private void addRosterListeners() {
	roster.onRosterRetrieved(new Listener<Collection<RosterItem>>() {
	    @Override
	    public void onEvent(final Collection<RosterItem> items) {
		GWT.log("Retrieved roster", null);
		for (final RosterItem item : items) {
		    getOrCreateWidget(item);
		}
	    }

	});

	roster.onItemAdded(new Listener<RosterItem>() {
	    @Override
	    public void onEvent(final RosterItem item) {
		getOrCreateWidget(item);
		view.setStatusMessage(item.getName() + " has been added to Contacts.");
	    }
	});

	roster.onItemChanged(new Listener<RosterItem>() {
	    @Override
	    public void onEvent(final RosterItem item) {
		getOrCreateWidget(item);
	    }
	});

	roster.onItemRemoved(new Listener<RosterItem>() {
	    @Override
	    public void onEvent(final RosterItem item) {
		view.removeItemView(getOrCreateWidget(item));
		// item.getName() returns null in a removed item
		view.setStatusMessage(item.getJID().getNode() + " has been removed from Contacts.");
	    }
	});
    }

    private void addSessionListeners() {
	final Session session = Suco.get(Session.class);
	session.onStateChanged(new Listener<Session>() {
	    @Override
	    public void onEvent(final Session session) {
		final boolean isActive = session.getState() == State.ready;
		if (active != isActive) {
		    active = isActive;
		    view.setActive(isActive);
		}
	    }
	});

	this.active = session.getState() == State.ready;
	view.setActive(active);
    }

    @SuppressWarnings("unchecked")
    private void createMenu() {
	menu = view.createMenu(ROSTERITEM_MENU_DEB_ID, new MenuAction<RosterItem>(i18n.chat(), CHAT_DEB_ID) {
	    @Override
	    public void execute(final RosterItem target) {
		onChatWith(target);
	    }
	}, new MenuAction<RosterItem>(i18n.removeFromContacts(), REMOVE_ROSTERITEM_DEB_ID) {
	    @Override
	    public void execute(final RosterItem target) {
		onRemoveItem(target);
	    }
	});
    }

}
