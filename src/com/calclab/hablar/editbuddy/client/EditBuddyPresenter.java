package com.calclab.hablar.editbuddy.client;

import static com.calclab.hablar.core.client.i18n.Translator.i18n;

import com.calclab.emite.im.client.roster.Roster;
import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.PagePresenter;
import com.calclab.hablar.core.client.ui.menu.MenuAction;
import com.calclab.hablar.editbuddy.client.ui.EditBuddyDisplay;
import com.calclab.suco.client.Suco;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

/**
 * Presenter in MVP pattern. Controls the EditBuddy form
 */
public class EditBuddyPresenter extends PagePresenter<EditBuddyDisplay> {
    private static int index = 0;
    protected static final String[] EMPTY_ARRAY = new String[0];
    private final MenuAction<RosterItem> action;
    private final Roster roster;
    private RosterItem currentItem;

    public EditBuddyPresenter(HablarEventBus eventBus, EditBuddyDisplay display) {
	super("EditButty", "" + (++index), eventBus, display);
	roster = Suco.get(Roster.class);

	this.action = new MenuAction<RosterItem>(i18n().changeNickName(), "EditBuddy-editAction") {
	    @Override
	    public void execute(RosterItem target) {
		showEditBuddyPanel(target);
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
		GWT.log("Close!", null);
		requestVisibility(Visibility.hidden);
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

    private void showEditBuddyPanel(RosterItem target) {
	GWT.log("SHOW EDIT", null);
	GWT.log("EDIT:" + target, null);

	this.currentItem = target;
	String nickName = target.getName();
	display.getOldNickName().setText(nickName);
	display.getNewNickName().setText(nickName);
	requestVisibility(Visibility.focused);
	display.getFirstFocusable().setFocus(true);
    }

    private void updateCurrentItem() {
	assert currentItem != null;
	String newName = display.getNewNickName().getText();
	if (!currentItem.getName().equals(newName)) {
	    roster.updateItem(currentItem.getJID(), newName, currentItem.getGroups().toArray(EMPTY_ARRAY));
	}
	requestVisibility(Visibility.hidden);
    }
}
