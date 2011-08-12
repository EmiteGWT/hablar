package com.calclab.hablar.rooms.client.open;

import java.util.Collection;
import java.util.List;

import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.emite.xep.muc.client.Room;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.PagePresenter;
import com.calclab.hablar.core.client.ui.selectionlist.Selectable;
import com.calclab.hablar.core.client.validators.CompositeValidatorChecker;
import com.calclab.hablar.core.client.validators.ListNotEmptyValidator;
import com.calclab.hablar.core.client.validators.Validators;
import com.calclab.hablar.rooms.client.RoomMessages;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;

public abstract class EditRoomPresenter extends PagePresenter<EditRoomDisplay> {

	protected CompositeValidatorChecker roomNameValidator;

	public EditRoomPresenter(final String pageType, final HablarEventBus eventBus, final EditRoomDisplay display) {
		super(pageType, eventBus, display);
		display.getCancel().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(final ClickEvent event) {
				requestVisibility(Visibility.hidden);
			}
		});

		display.getInvite().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(final ClickEvent event) {
				onAccept();
				requestVisibility(Visibility.notFocused);
			}
		});

		roomNameValidator = new CompositeValidatorChecker(display.getRoomNameError(), display.getAcceptEnabled());
		roomNameValidator.add(display.getRoomName(), Validators.notEmpty(RoomMessages.msg.emptyGroupChatName()));
		roomNameValidator.add(display.getRoomName(), Validators.isValidRoomName(RoomMessages.msg.notValidGroupChatName()));
		roomNameValidator.add(display.getSelectionList(), new ListNotEmptyValidator<Selectable>(RoomMessages.msg.selectionEmptyErrorMessage()));

		display.getRoomNameKeys().addKeyDownHandler(new KeyDownHandler() {
			@Override
			public void onKeyDown(final KeyDownEvent event) {
				Scheduler.get().scheduleDeferred(roomNameValidator.getScheduledCommand());
			}
		});
		display.getSelectionList().addValueChangeHandler(new ValueChangeHandler<List<Selectable>>() {
			@Override
			public void onValueChange(final ValueChangeEvent<List<Selectable>> event) {
				Scheduler.get().scheduleDeferred(roomNameValidator.getScheduledCommand());
			}
		});

	}

	public Collection<RosterItem> getItems() {
		return display.getSelectedItems();
	}

	public void setItems(final Collection<RosterItem> items, final boolean selected) {
		display.clearList();
		for (final RosterItem item : items) {
			createItem(item, selected);
		}
	}

	protected void createItem(final RosterItem item, final boolean selected) {
		if (selected) {
			display.addSelectedRosterItem(item);
		} else {
			display.addRosterItem(item);
		}
	}

	protected abstract void onAccept();

	@Override
	protected void onBeforeFocus() {
		onPageOpen();
	}

	protected abstract void onPageOpen();

	protected void sendInvitations(final Room room) {
		final String reasonText = display.getMessage().getText();
		for (final RosterItem item : getItems()) {
			GWT.log("INVITING: " + item.getJID());
			room.sendInvitationTo(item.getJID(), reasonText);
		}
	}

}
