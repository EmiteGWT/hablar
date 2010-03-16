package com.calclab.hablar.editbuddy.client;

import static com.calclab.hablar.editbuddy.client.HablarEditBuddy.i18n;

import com.calclab.emite.im.client.roster.Roster;
import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.PagePresenter;
import com.calclab.hablar.core.client.validators.TextValidator;
import com.calclab.hablar.core.client.validators.Validators;
import com.calclab.hablar.editbuddy.client.ui.EditBuddyDisplay;
import com.calclab.suco.client.Suco;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

/**
 * Presenter in MVP pattern. Controls the EditBuddy form
 */
public class EditBuddyPage extends PagePresenter<EditBuddyDisplay> {
    private static int index = 0;
    private final Roster roster;
    private RosterItem currentItem;
    private final TextValidator nickNameValidator;

    public EditBuddyPage(final HablarEventBus eventBus, final EditBuddyDisplay display) {
	super("EditButty", "" + ++index, eventBus, display);
	roster = Suco.get(Roster.class);

	nickNameValidator = new TextValidator(display.getNickNameKeys(), display.getNickName(), display
		.getNickNameError(), display.getAcceptState());
	nickNameValidator.add(Validators.notEmpty(i18n().nickNameEmpty()));
	bind();
    }

    public void setItem(final RosterItem item) {
	currentItem = item;
    }

    private void bind() {
	display.getCancel().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		requestVisibility(Visibility.hidden);
	    }
	});
	display.getSave().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		updateCurrentItem();
		requestVisibility(Visibility.hidden);
	    }
	});
	display.getEnterAction().addChangeHandler(new ChangeHandler() {
	    @Override
	    public void onChange(final ChangeEvent event) {
		updateCurrentItem();
		requestVisibility(Visibility.hidden);
	    }
	});
    }

    private void updateCurrentItem() {
	final String newName = display.getNickName().getText();
	assert newName != null : "Validation of new name field failed!";
	if (!newName.equals(currentItem.getName())) {
	    currentItem.setName(newName);
	    roster.requestUpdateItem(currentItem);
	}
    }

    @Override
    protected void onBeforeFocus() {
	final String nickName = currentItem.getName();
	display.getOldNickName().setText(nickName);
	display.getNickName().setText("");
	display.getFirstFocusable().setFocus(true);
	nickNameValidator.validate();
    }
}
