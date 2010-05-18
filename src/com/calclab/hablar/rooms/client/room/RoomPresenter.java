package com.calclab.hablar.rooms.client.room;

import static com.calclab.hablar.rooms.client.HablarRooms.i18n;

import java.util.ArrayList;

import com.calclab.emite.core.client.xmpp.session.Session;
import com.calclab.emite.core.client.xmpp.stanzas.Message;
import com.calclab.emite.im.client.chat.Chat;
import com.calclab.emite.im.client.chat.Chat.State;
import com.calclab.emite.xep.muc.client.Room;
import com.calclab.hablar.chat.client.ui.ChatDisplay;
import com.calclab.hablar.chat.client.ui.ChatMessageDisplay;
import com.calclab.hablar.chat.client.ui.ChatMessageFormatter;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.PagePresenter;
import com.calclab.hablar.core.client.page.events.UserMessageEvent;
import com.calclab.hablar.core.client.ui.icon.HablarIcons;
import com.calclab.hablar.core.client.ui.menu.Action;
import com.calclab.hablar.rooms.client.RoomName;
import com.calclab.hablar.rooms.client.occupant.OccupantsPresenter;
import com.calclab.suco.client.Suco;
import com.calclab.suco.client.events.Listener;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;

public class RoomPresenter extends PagePresenter<RoomDisplay> implements RoomPage {
    public static final String TYPE = "Room";
    public static final String ROOM_MESSAGE = "RoomMessage";
    private static int id = 0;

    private final Room room;

    public RoomPresenter(final HablarEventBus eventBus, final Room room, final RoomDisplay display) {
	super(TYPE, "" + ++id, eventBus, display);
	this.room = room;
	display.setId(getId());

	new OccupantsPresenter(room, display.createOccupantsDisplay(room.getID()));

	final Session session = Suco.get(Session.class);
	final String me = session.getCurrentUser().getNode();
	final String roomName = RoomName.decode(room.getURI().getNode());
	setVisibility(Visibility.notFocused);
	model.init(HablarIcons.getBundle().rosterIcon(), roomName, roomName);
	model.setCloseable(true);

	room.onMessageReceived(new Listener<Message>() {
	    @Override
	    public void onEvent(final Message message) {
		final String from = message.getFrom().getResource();
		String messageBody = message.getBody();
		final Element body = ChatMessageFormatter.format(from, messageBody);
		if (body != null) {
		    if (me.equals(from)) {
			display.addMessage("me", body, ChatDisplay.MessageType.sent);
		    } else {
			display.addMessage(from, body, ChatDisplay.MessageType.incoming);
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

    public Room getRoom() {
	return room;
    }

    public void showMessage(final String text) {
	Document doc = Document.get();
	Element element = doc.createSpanElement();
	element.appendChild(doc.createTextNode(text));
	display.addMessage(null, element, ChatDisplay.MessageType.info);
    }

    private void fireUserMessage(final String roomName, final String from, String body) {
	final String message;
	body = ChatMessageFormatter.ellipsis(body, 25);
	if (from == null) {
	    message = i18n().incommingAdminMessage(roomName, body);
	} else {
	    message = i18n().incommingMessage(roomName, from, body);
	}
	eventBus.fireEvent(new UserMessageEvent(this, message, ROOM_MESSAGE));
    }

    private void sendMessage(final Chat chat, final ChatDisplay display) {
	final String text = display.getBody().getText().trim();
	if (!text.isEmpty()) {
	    // final String body = ChatMessageFormatter.format(text);
	    // display.showMessage("me", body, ChatDisplay.MessageType.sent);
	    chat.send(new Message(text));
	    display.clearAndFocus();
	}
    }

    private void setState(final State state) {
	final boolean visible = state == State.ready;
	display.setControlsVisible(visible);
	display.setStatusVisible(visible);
    }

    @Override
    public String getChatName() {
	return room.getID();
    }

    @Override
    public ArrayList<ChatMessageDisplay> getMessages() {
	return display.getMessages();
    }
}
