package com.calclab.hablar.rooms.client.room;

import static com.calclab.hablar.rooms.client.HablarRooms.i18n;

import com.calclab.emite.core.client.xmpp.session.XmppSession;
import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.emite.im.client.roster.XmppRoster;
import com.calclab.emite.xep.muc.client.Occupant;
import com.calclab.emite.xep.muc.client.Room;
import com.calclab.emite.xep.muc.client.events.OccupantChangedEvent;
import com.calclab.emite.xep.muc.client.events.OccupantChangedHandler;
import com.calclab.emite.xep.muc.client.events.RoomInvitationSentEvent;
import com.calclab.emite.xep.muc.client.events.RoomInvitationSentHandler;
import com.calclab.emite.xep.muc.client.subject.RoomSubject;
import com.calclab.emite.xep.muc.client.subject.RoomSubjectChangedEvent;
import com.calclab.emite.xep.muc.client.subject.RoomSubjectChangedHandler;
import com.calclab.hablar.chat.client.ui.ChatMessage;

public class RoomNotificationPresenter {

    private final RoomPresenter roomPresenter;

    private final String me;

    public RoomNotificationPresenter(final XmppSession session, final XmppRoster roster,
	    final RoomPresenter roomPresenter, final Room room) {
	this.roomPresenter = roomPresenter;
	me = session.getCurrentUserURI().getNode();

	room.addOccupantChangedHandler(new OccupantChangedHandler() {

	    @Override
	    public void onOccupantChanged(final OccupantChangedEvent event) {
		if (event.isAdded()) {
		    final String body = i18n().occupantHasJoined(event.getOccupant().getNick());
		    show(body);
		} else if (event.isRemoved()) {
		    if (!me.equals(event.getOccupant().getUserUri().getNode())) {
			final String body = i18n().occupantHasLeft(event.getOccupant().getNick());
			show(body);
		    }
		}
	    }
	});

	/*
	 * room.onOccupantModified(new Listener<Occupant>() {
	 * 
	 * @Override public void onEvent(final Occupant occupant) {
	 * show(i18n().occupantModified(occupant.getNick())); } });
	 */

	RoomSubject.addRoomSubjectChangedHandler(room, new RoomSubjectChangedHandler() {

	    @Override
	    public void onSubjectChanged(final RoomSubjectChangedEvent event) {
		final Occupant occupant = room.getOccupantByOccupantUri(event.getOccupantUri());

		String message;
		if (occupant != null) {
		    message = i18n().roomSubjectChanged(occupant.getNick(), event.getSubject());
		} else { // The subject has been changed by a room rule.
		    message = i18n().roomSubjectChangedAnonymous(event.getSubject());
		}
		show(message);
	    }
	});

	room.addRoomInvitationSentHandler(new RoomInvitationSentHandler() {

	    private boolean isEmpty(final String reason) {
		return (reason == null) || reason.trim().equals("");
	    }

	    @Override
	    public void onRoomInvitationSent(final RoomInvitationSentEvent event) {
		final XmppURI userJid = event.getUserJid();
		final RosterItem item = roster.getItemByJID(userJid);
		final String name = ((item != null) && (item.getName() != null)) ? item.getName() : userJid.getNode();

		final String body = isEmpty(event.getReasonText()) ? i18n().invitationSent(name) : i18n()
			.invitationSentWithReason(name, event.getReasonText());
		show(body);
	    }
	});

    }

    private void show(final String body) {
	roomPresenter.addMessage(new ChatMessage(null, null, body, ChatMessage.MessageType.info));
	roomPresenter.fireUserNotification(body);
    }
}
