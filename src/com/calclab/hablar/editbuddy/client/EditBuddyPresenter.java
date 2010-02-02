package com.calclab.hablar.editbuddy.client;

import com.calclab.emite.im.client.roster.Roster;
import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.hablar.basic.client.i18n.Msg;
import com.calclab.hablar.basic.client.ui.HablarView;
import com.calclab.hablar.basic.client.ui.menu.MenuAction;
import com.calclab.hablar.editbuddy.client.ui.EditBuddyDisplay;
import com.calclab.suco.client.Suco;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

/**
 * Presenter in MVP pattern. Controls the EditBuddy form
 */
public class EditBuddyPresenter {
    private static final String DEBUGID_EDITBUDDY = "Action-EditBuddy";
    protected static final String[] EMPTY_ARRAY = new String[0];
    private final HablarView hablar;
    private final Msg i18n;
    private final MenuAction<RosterItem> action;
    private final EditBuddyDisplay display;
    private final Roster roster;
    private RosterItem currentItem;

    public EditBuddyPresenter(HablarView hablar, EditBuddyDisplay display) {
	this.hablar = hablar;
	this.display = display;
	i18n = Suco.get(Msg.class);
	roster = Suco.get(Roster.class);

	this.action = new MenuAction<RosterItem>(i18n.changeNickName(), DEBUGID_EDITBUDDY) {
	    @Override
	    public void execute(RosterItem target) {
		onChangeNickName(target);
	    }
	};
	bind();
    }

    public MenuAction<RosterItem> getAction() {
	return action;
    }

    private void bind() {
	display.getCancel().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		cancelEdit();
	    }
	});
	display.getSave().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		updateCurrentItem();
	    }
	});
	display.getEnterAction().addChangeHandler(new ChangeHandler() {
	    @Override
	    public void onChange(ChangeEvent event) {
		updateCurrentItem();
	    }
	});
    }

    private void cancelEdit() {
	hablar.closeOverlay();
    }

    private void onChangeNickName(RosterItem target) {
	this.currentItem = target;
	String nickName = target.getName();
	display.getOldNickName().setText(nickName);
	display.getNewNickName().setText(nickName);
	hablar.showOverlay(display);
	display.getFirstFocusable().setFocus(true);
    }

    private void updateCurrentItem() {
	assert currentItem != null;
	String newName = display.getNewNickName().getText();
	if (!currentItem.getName().equals(newName)) {
	    roster.updateItem(currentItem.getJID(), newName, currentItem.getGroups().toArray(EMPTY_ARRAY));
	}
	hablar.closeOverlay();
    }
}
