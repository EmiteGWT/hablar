package com.calclab.hablar.group.client.manage;

import java.util.Collection;
import java.util.List;

import com.calclab.emite.im.client.roster.Roster;
import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.PagePresenter;
import com.calclab.hablar.core.client.ui.selectionlist.Selectable;
import com.calclab.hablar.core.client.validators.CompositeValidatorChecker;
import com.calclab.hablar.core.client.validators.ListNotEmptyValidator;
import com.calclab.hablar.core.client.validators.Validators;
import com.calclab.hablar.group.client.GroupMessages;
import com.calclab.suco.client.Suco;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.DeferredCommand;

public class ManageGroupPresenter extends PagePresenter<ManageGroupDisplay> {

    private CompositeValidatorChecker groupNameValidator;
    private static GroupMessages groupMessages;

    public static GroupMessages i18n() {
	return groupMessages;
    }

    public static void setMessages(GroupMessages groupMessages) {
	ManageGroupPresenter.groupMessages = groupMessages;
    }

    public ManageGroupPresenter(HablarEventBus eventBus, final ManageGroupDisplay display, String pageTitle) {
	super("ManageGroup", eventBus, display);

	display.getCancel().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		requestVisibility(Visibility.hidden);
	    }
	});

	display.getApply().addClickHandler(new ClickHandler() {

	    @Override
	    public void onClick(ClickEvent event) {
		String groupName = display.getGroupNameText();
		Collection<RosterItem> items = display.getSelectedItems();
		Roster roster = Suco.get(Roster.class);
		for (RosterItem item : items) {
		    item.addToGroup(groupName);
		}
		roster.requestUpdateItems(items);
		requestVisibility(Visibility.hidden);
	    }
	});
	display.setPageTitle(pageTitle);
	groupNameValidator = new CompositeValidatorChecker(display
		.getGroupNameError(), display.getAcceptEnabled());
	groupNameValidator.add(display.getGroupName(), Validators.notEmpty(i18n().groupNameEmptyErrorMessage()));
	groupNameValidator.add(display.getSelectionList(), new ListNotEmptyValidator<Selectable>(i18n()
		.selectionEmptyErrorMessage()));
	display.getGroupNameKeys().addKeyDownHandler(new KeyDownHandler() {

	    @Override
	    public void onKeyDown(KeyDownEvent event) {
		DeferredCommand.addCommand(groupNameValidator.getDeferredCommand());
	    }
	});
	display.getSelectionList().addValueChangeHandler(new ValueChangeHandler<List<Selectable>>() {

	    @Override
	    public void onValueChange(ValueChangeEvent<List<Selectable>> event) {
		DeferredCommand.addCommand(groupNameValidator.getDeferredCommand());
	    }
	});
    }

    @Override
    protected void onBeforeFocus() {
	display.clearSelectionList();
	Roster roster = Suco.get(Roster.class);
	for (RosterItem item: roster.getItems()) {
	    display.addRosterItem(item);
	}
	groupNameValidator.validate();
    }
}
