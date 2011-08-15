package com.calclab.hablar.rooms.client.room;

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
import com.calclab.hablar.rooms.client.RoomMessages;

public class RoomNotificationPresenter {

	private final RoomPresenter roomPresenter;

	private final String me;

	public RoomNotificationPresenter(final XmppSession session, final XmppRoster roster, final RoomPresenter roomPresenter, final Room room) {
		this.roomPresenter = roomPresenter;
		me = session.getCurrentUserURI().getNode();

		room.addOccupantChangedHandler(new OccupantChangedHandler() {

			@Override
			public void onOccupantChanged(final OccupantChangedEvent event) {
				if (event.isAdded()) {
					final String body = RoomMessages.msg.occupantHasJoined(event.getOccupant().getNick());
					show(body);
				} else if (event.isRemoved()) {
					final XmppURI occupantUserUri = event.getOccupant().getUserUri();
					final String node = occupantUserUri != null ? occupantUserUri.getNode() : null;
					if (!me.equals(node)) {
						final String body = RoomMessages.msg.occupantHasLeft(event.getOccupant().getNick());
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
					message = RoomMessages.msg.roomSubjectChanged(occupant.getNick(), event.getSubject());
				} else { // The subject has been changed by a room rule.
					message = RoomMessages.msg.roomSubjectChangedAnonymous(event.getSubject());
				}
				show(message);
			}
		});

		room.addRoomInvitationSentHandler(new RoomInvitationSentHandler() {

			@Override
			public void onRoomInvitationSent(final RoomInvitationSentEvent event) {
				final XmppURI userJid = event.getUserJid();
				final RosterItem item = roster.getItemByJID(userJid);
				final String name = ((item != null) && (item.getName() != null)) ? item.getName() : userJid.getNode();

				final String body = isEmpty(event.getReasonText()) ? RoomMessages.msg.invitationSent(name) : RoomMessages.msg.invitationSentWithReason(name,
						event.getReasonText());
				show(body);
			}

			private boolean isEmpty(final String reason) {
				return (reason == null) || reason.trim().equals("");
			}
		});

	}

	private void show(final String body) {
		roomPresenter.addMessage(new ChatMessage(body));
		roomPresenter.fireUserNotification(body);
	}
}
