package com.calclab.hablar.rooms.client.room;

import static com.calclab.hablar.rooms.client.HablarRooms.i18n;

import com.calclab.emite.core.client.events.MessageEvent;
import com.calclab.emite.core.client.events.MessageHandler;
import com.calclab.emite.core.client.xmpp.session.XmppSession;
import com.calclab.emite.core.client.xmpp.stanzas.Message;
import com.calclab.emite.im.client.chat.ChatStates;
import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.emite.im.client.roster.XmppRoster;
import com.calclab.emite.xep.delay.client.Delay;
import com.calclab.emite.xep.delay.client.DelayHelper;
import com.calclab.emite.xep.muc.client.Occupant;
import com.calclab.emite.xep.muc.client.Room;
import com.calclab.hablar.chat.client.ui.ChatMessage;
import com.calclab.hablar.chat.client.ui.ChatMessageFormatter;
import com.calclab.hablar.chat.client.ui.ChatPresenter;
import com.calclab.hablar.chat.client.ui.ColorHelper;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.events.UserMessageEvent;
import com.calclab.hablar.core.client.ui.icon.Icons;
import com.calclab.hablar.core.client.ui.menu.Action;
import com.calclab.hablar.core.client.validators.Empty;
import com.calclab.hablar.rooms.client.RoomName;
import com.calclab.hablar.rooms.client.occupant.OccupantsPresenter;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;

public class RoomPresenter extends ChatPresenter implements RoomPage {
    public static final String TYPE = "Room";
    public static final String ROOM_MESSAGE = "RoomMessage";
    private static int id = 0;

    private final Room room;

    public RoomPresenter(final XmppSession session, final XmppRoster roster, final HablarEventBus eventBus,
	    final Room room, final RoomDisplay display) {
	super(TYPE, "" + ++id, eventBus, room, display);
	this.room = room;
	display.setId(getId());

	new RoomNotificationPresenter(session, roster, this, room);
	new OccupantsPresenter(room, display.createOccupantsDisplay(room.getID()));

	final String roomName = RoomName.decode(room.getURI().getNode());
	setVisibility(Visibility.notFocused);
	model.init(Icons.ROSTER, roomName, roomName);
	model.setCloseable(true);
	model.setCloseConfirmationMessage(i18n().confirmCloseRoom());
	model.setCloseConfirmationTitle(i18n().confirmCloseRoomTitle(roomName));

	room.addMessageReceivedHandler(new MessageHandler() {

	    @Override
	    public void onMessage(final MessageEvent event) {
		final Message message = event.getMessage();

		final String from;

		final Occupant occupant = room.getOccupantByOccupantUri(message.getFrom());
		RosterItem rosterItem;

		// Check if the occupant is found, and if they exist in the
		// roster. We can then use their nickname
		if ((occupant != null) && ((rosterItem = roster.getItemByJID(occupant.getJID())) != null)) {
		    if ((rosterItem.getName() != null) && (rosterItem.getName().equals(""))) {
			from = rosterItem.getName();
		    } else {
			from = rosterItem.getJID().getShortName();
		    }
		} else {
		    from = message.getFrom().getResource();
		}

		final Delay delay = DelayHelper.getDelay(message);
		if (!room.isComingFromMe(message) || (delay != null)) {
		    final String messageBody = message.getBody();
		    if (Empty.not(messageBody)) {
			final String color = ColorHelper.getColor(message.getFrom().getJID());
			final ChatMessage chatMessage = new ChatMessage(null, from, color, messageBody,
				ChatMessage.MessageType.incoming);
			if (delay != null) {
			    chatMessage.setDate(delay.getStamp());
			}
			addMessage(chatMessage);
			fireUserMessage(roomName, from, messageBody);
		    }
		}
	    }
	});

	if (ChatStates.locked.equals(room.getChatState())) {
	    addMessage(new ChatMessage(i18n().waitingForUnlockRoom()));
	}

	display.getAction().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		sendMessage(room, display);
	    }

	});
	display.getTextBox().addKeyDownHandler(new KeyDownHandler() {
	    @Override
	    public void onKeyDown(final KeyDownEvent event) {
		if (event.getNativeKeyCode() == 13) {
		    event.stopPropagation();
		    event.preventDefault();
		    sendMessage(room, display);
		}
	    }
	});
    }

    @Override
    public void addAction(final Action<RoomPage> action) {
	display.createAction(action).addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		action.execute(RoomPresenter.this);
	    }
	});
    }

    @Override
    public String getChatName() {
	return room.getID();
    }

    @Override
    public Room getRoom() {
	return room;
    }

    @Override
    public void setVisibility(final Visibility visibility) {
	if (visibility == Visibility.hidden) {
	    room.close();
	}
	super.setVisibility(visibility);
    }

    private void fireUserMessage(final String roomName, final String from, String body) {
	body = ChatMessageFormatter.ellipsis(body, 25);
	final String message = Empty.is(from) ? i18n().incommingAdminMessage(roomName, body) : i18n().incommingMessage(
		roomName, from, body);
	fireUserNotification(message);
    }

    void fireUserNotification(final String message) {
	eventBus.fireEvent(new UserMessageEvent(this, message, ROOM_MESSAGE));
    }

}
