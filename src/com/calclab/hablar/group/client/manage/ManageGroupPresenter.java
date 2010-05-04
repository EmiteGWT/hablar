package com.calclab.hablar.group.client.manage;

import java.util.Collection;

import com.calclab.emite.im.client.roster.Roster;
import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.PagePresenter;
import com.calclab.hablar.core.client.validators.TextValidator;
import com.calclab.hablar.core.client.validators.Validators;
import com.calclab.suco.client.Suco;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class ManageGroupPresenter extends PagePresenter<ManageGroupDisplay> {

    private TextValidator groupNameValidator;

    public ManageGroupPresenter(HablarEventBus eventBus, final ManageGroupDisplay display) {
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
	groupNameValidator = new TextValidator(display.getGroupNameKeys(), display.getGroupName(), display
		.getGroupNameError(), display.getAcceptEnabled());
	groupNameValidator.add(Validators.notEmpty("The group name cannot be empty"));
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
