package com.calclab.hablar.rooms.client;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.xep.muc.client.Room;
import com.calclab.hablar.core.client.page.PagePresenter.Visibility;
import com.calclab.hablar.rooms.client.occupant.OccupantsDisplay;
import com.calclab.hablar.rooms.client.room.RoomDisplay;
import com.calclab.hablar.rooms.client.room.RoomPresenter;
import com.calclab.hablar.testing.EmiteTester;
import com.calclab.hablar.testing.HablarTester;

public class RoomPresenterTests {

    private RoomPresenter presenter;
    private RoomDisplay display;
    private Room room;

    @Before
    public void setup() {
	EmiteTester emiteTester = new EmiteTester();
	emiteTester.setSessionReady("user@domain/res");
	HablarTester tester = new HablarTester();
	display = tester.newDisplay(RoomDisplay.class);
	OccupantsDisplay occupantsDisplay = tester.newDisplay(OccupantsDisplay.class);
	when(display.createOccupantsDisplay(anyString())).thenReturn(occupantsDisplay);
	room = Mockito.mock(Room.class);
	when(room.getURI()).thenReturn(XmppURI.uri("room@domain"));
	presenter = new RoomPresenter(tester.getEventBus(), room, display);
    }

    /**
     * Fixes issue 288
     */
    @Test
    public void shouldCloseRoomWhenPageIsClosed() {
	presenter.setVisibility(Visibility.hidden);
	verify(room).close();
    }

}
