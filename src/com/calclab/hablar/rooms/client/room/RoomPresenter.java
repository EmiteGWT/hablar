package com.calclab.hablar.rooms.client.room;

import static com.calclab.hablar.rooms.client.HablarRooms.i18n;

import com.calclab.emite.core.client.xmpp.session.Session;
import com.calclab.emite.core.client.xmpp.stanzas.Message;
import com.calclab.emite.im.client.chat.Chat.State;
import com.calclab.emite.xep.muc.client.Room;
import com.calclab.hablar.chat.client.ui.ChatMessage;
import com.calclab.hablar.chat.client.ui.ChatMessageFormatter;
import com.calclab.hablar.chat.client.ui.ChatPresenter;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.events.UserMessageEvent;
import com.calclab.hablar.core.client.ui.icon.HablarIcons;
import com.calclab.hablar.core.client.ui.menu.Action;
import com.calclab.hablar.core.client.validators.IsEmpty;
import com.calclab.hablar.rooms.client.RoomName;
import com.calclab.hablar.rooms.client.occupant.OccupantsPresenter;
import com.calclab.suco.client.Suco;
import com.calclab.suco.client.events.Listener;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;

public class RoomPresenter extends ChatPresenter implements RoomPage {
    public static final String TYPE = "Room";
    public static final String ROOM_MESSAGE = "RoomMessage";
    private static int id = 0;

    private final Room room;
    private final String me;

    public RoomPresenter(final HablarEventBus eventBus, final Room room, final RoomDisplay display) {
	super(TYPE, "" + ++id, eventBus, display);
	this.room = room;
	display.setId(getId());

	new RoomNotificationPresenter(this, room);
	new OccupantsPresenter(room, display.createOccupantsDisplay(room.getID()));

	final Session session = Suco.get(Session.class);
	me = session.getCurrentUser().getNode();
	final String roomName = RoomName.decode(room.getURI().getNode());
	setVisibility(Visibility.notFocused);
	model.init(HablarIcons.getBundle().rosterIcon(), roomName, roomName);
	model.setCloseable(true);

	room.onMessageReceived(new Listener<Message>() {
	    @Override
	    public void onEvent(final Message message) {
		final String from = message.getFrom().getResource();
		if (!me.equals(from)) {
		    final String messageBody = message.getBody();
		    if (IsEmpty.not(messageBody)) {
			addMessage(new ChatMessage(null, from, messageBody, ChatMessage.MessageType.incoming));
			fireUserMessage(roomName, from, messageBody);
		    }
		}
	    }

	});
	room.onStateChanged(new Listener<State>() {
	    @Override
	    public void onEvent(final State state) {
		setState(state);
	    }
	});

	setState(room.getState());

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

    public Room getRoom() {
	return room;
    }

    private void fireUserMessage(final String roomName, final String from, String body) {
	body = ChatMessageFormatter.ellipsis(body, 25);
	final String message = IsEmpty.is(from) ? i18n().incommingAdminMessage(roomName, body) : i18n()
		.incommingMessage(roomName, from, body);
	fireUserNotification(message);
    }

    void fireUserNotification(final String message) {
	eventBus.fireEvent(new UserMessageEvent(this, message, ROOM_MESSAGE));
    }

}
