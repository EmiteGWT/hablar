package com.calclab.hablar.rooms.client.existing;

import java.util.List;

import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.xep.mucdisco.client.ExistingRoom;
import com.calclab.hablar.core.client.ui.selectionlist.Selectable;
import com.calclab.hablar.core.client.ui.selectionlist.SelectionList;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class OpenExistingRoomWidget extends Composite implements OpenExistingRoomDisplay {

	interface OpenExistingRoomWidgetUiBinder extends UiBinder<Widget, OpenExistingRoomWidget> {
	}

	private static OpenExistingRoomWidgetUiBinder uiBinder = GWT.create(OpenExistingRoomWidgetUiBinder.class);

	@UiField
	SelectionList roomList;

	@UiField
	Button accept, cancel;

	public OpenExistingRoomWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		roomList.addSelectionHandler(new SelectionHandler<Selectable>() {

			@Override
			public void onSelection(final SelectionEvent<Selectable> event) {
				accept.setEnabled(true);
			}
		});
	}

	@Override
	public HasClickHandlers getAccept() {
		return accept;
	}

	@Override
	public HasClickHandlers getCancel() {
		return cancel;
	}

	@Override
	public XmppURI getSelectedRoom() {
		final RoomSelectable selectable = (RoomSelectable) roomList.getSelectedSelectable();
		if (selectable != null) {
			return ((ExistingRoom) selectable.getItem()).getUri();
		}
		return null;
	}

	@Override
	public void setRooms(final List<ExistingRoom> rooms) {
		accept.setEnabled(false);
		roomList.clear();
		for (final ExistingRoom uri : rooms) {
			roomList.add(new RoomSelectable(uri));
		}
	}
}
