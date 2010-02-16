package com.calclab.hablar.groupchat.client;

import static com.calclab.hablar.groupchat.client.HablarGroupChat.i18n;

import com.calclab.emite.core.client.xmpp.session.Session;
import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.im.client.chat.Chat;
import com.calclab.emite.im.client.roster.Roster;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.rooms.client.ui.open.OpenRoomPresenter;
import com.calclab.hablar.rooms.client.ui.open.OpenRoomWidget;
import com.calclab.hablar.rooms.client.ui.open.SelectRosterItemPresenter;
import com.calclab.suco.client.Suco;

public class ConvertToGroupChatPresenter extends OpenRoomPresenter {

    private static final String TYPE = "ConvertToGroupChat";
    private Chat chat;

    public ConvertToGroupChatPresenter(final String roomsService, final HablarEventBus eventBus,
	    final OpenRoomWidget openRoomWidget) {
	super(TYPE, eventBus, openRoomWidget);
	display.setPageTitle(i18n().convertPageTitle());
    }

    public void setChat(final Chat chat) {
	this.chat = chat;
    }

    @Override
    protected void onAccept() {

    }

    @Override
    protected void onPageOpen() {

	final Roster roster = Suco.get(Roster.class);
	final Session session = Suco.get(Session.class);

	final XmppURI currentJid = session.getCurrentUser().getJID();
	final XmppURI chatJid = chat.getURI();
	final String name = i18n().defaultRoomName(currentJid.toString(), chatJid.toString());
	display.getRoomName().setText(name);

	setItems(roster.getItems(), true);
	final SelectRosterItemPresenter other = getItem(chatJid);
	if (other == null) {
	} else {
	    other.setSelected(true);
	    other.setEnabled(false);
	}
	final SelectRosterItemPresenter self = getItem(currentJid);
	if (self != null) {
	    self.setSelected(true);
	    other.setEnabled(false);
	}
    }
}
