package com.calclab.hablar.rooms.client.room;

import static com.calclab.hablar.rooms.client.HablarRooms.i18n;

import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.im.client.roster.Roster;
import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.emite.xep.muc.client.Occupant;
import com.calclab.emite.xep.muc.client.Room;
import com.calclab.hablar.chat.client.ui.ChatMessage;
import com.calclab.suco.client.Suco;
import com.calclab.suco.client.events.Listener;
import com.calclab.suco.client.events.Listener2;

public class RoomNotificationPresenter {

    private final RoomPresenter roomPresenter;

    public RoomNotificationPresenter(final RoomPresenter roomPresenter, final Room room) {
	this.roomPresenter = roomPresenter;

	room.onOccupantAdded(new Listener<Occupant>() {
	    @Override
	    public void onEvent(final Occupant occupant) {
		final String body = i18n().occupantHasJoined(occupant.getNick());
		show(body);
	    }
	});

	room.onOccupantRemoved(new Listener<Occupant>() {
	    @Override
	    public void onEvent(final Occupant occupant) {
		final String body = i18n().occupantHasLeft(occupant.getNick());
		show(body);
	    }
	});

	room.onOccupantModified(new Listener<Occupant>() {
	    @Override
	    public void onEvent(final Occupant occupant) {
		show(i18n().occupantModified(occupant.getNick()));
	    }
	});

	room.onSubjectChanged(new Listener2<Occupant, String>() {
	    @Override
	    public void onEvent(final Occupant who, final String newSubject) {
		show(i18n().roomSubjectChanged(who.getNick(), newSubject));
	    }
	});

	room.onInvitationSent(new Listener2<XmppURI, String>() {
	    @Override
	    public void onEvent(final XmppURI uri, final String reason) {
		final Roster roster = Suco.get(Roster.class);
		final RosterItem item = roster.getItemByJID(uri);
		final String name = item != null ? item.getName() : uri.getNode();

		final String body = isEmpty(reason) ? i18n().invitationSent(name) : i18n().invitationSentWithReason(
			name, reason);
		show(body);
	    }

	    private boolean isEmpty(final String reason) {
		return reason == null || reason.trim().equals("");
	    }
	});
    }

    private void show(final String body) {
	roomPresenter.addMessage(new ChatMessage(null, null, body, ChatMessage.MessageType.info));
	roomPresenter.fireUserNotification(body);
    }
}
