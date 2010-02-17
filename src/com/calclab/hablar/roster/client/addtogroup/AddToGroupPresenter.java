package com.calclab.hablar.roster.client.addtogroup;

import java.util.ArrayList;

import com.calclab.emite.im.client.roster.Roster;
import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.PagePresenter;
import com.calclab.hablar.roster.client.addtogroup.AddToGroupDisplay.Action;
import com.calclab.suco.client.Suco;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;

public class AddToGroupPresenter extends PagePresenter<AddToGroupDisplay> {
    private static final String TYPE = "MoveToGroup";
    private static int id = 0;
    private RosterItem item;

    public AddToGroupPresenter(final HablarEventBus eventBus, final AddToGroupDisplay display) {
	super(TYPE, "" + ++id, eventBus, display);

	display.getCancel().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		requestVisibility(Visibility.hidden);
	    }
	});

	display.getApply().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		moveToNewGroup();
		requestVisibility(Visibility.hidden);
	    }
	});

	display.getSelectGroupFocus().addFocusHandler(new FocusHandler() {
	    @Override
	    public void onFocus(final FocusEvent event) {
		display.getAddToExisting().setValue(true, true);
	    }
	});

	display.getNewGroupFocus().addFocusHandler(new FocusHandler() {
	    @Override
	    public void onFocus(final FocusEvent event) {
		display.getAddToNew().setValue(true, true);
		updateAcceptButton();
	    }
	});

	display.getAddToExisting().addValueChangeHandler(new ValueChangeHandler<Boolean>() {
	    @Override
	    public void onValueChange(final ValueChangeEvent<Boolean> event) {
		updateAcceptButton();
	    }
	});

	display.getNewGroupKeys().addKeyPressHandler(new KeyPressHandler() {
	    @Override
	    public void onKeyPress(final KeyPressEvent event) {
		DeferredCommand.addCommand(new Command() {
		    @Override
		    public void execute() {
			updateAcceptButton();
		    }
		});
	    }
	});

    }

    public void setItem(final RosterItem item) {
	this.item = item;
    }

    private void updateAcceptButton() {
	if (display.getAddToExisting().getValue() == true) {
	    display.setAcceptEnabled(true);
	} else {
	    final String groupName = display.getNewGroupName().getValue().trim();
	    GWT.log("NEW NAME VALUE: " + groupName);
	    display.setAcceptEnabled(groupName.length() > 0);
	}
    }

    protected void moveToNewGroup() {
	final Roster roster = Suco.get(Roster.class);

	final String groupName;
	if (display.getAddToExisting().getValue() == true) {
	    groupName = display.getSelectedGroupName();
	} else {
	    groupName = display.getNewGroupName().getValue();
	}

	item.addToGroup(groupName);
	roster.updateItem(item);
    }

    @Override
    protected void onBeforeFocus() {
	final Roster roster = Suco.get(Roster.class);
	display.clearGroupList();
	final ArrayList<String> groups = new ArrayList<String>(roster.getGroups());
	groups.removeAll(item.getGroups());
	for (final String name : groups) {
	    display.addToGroupList(name);
	}
	if (groups.size() == 0) {
	    display.setActiveAction(Action.addToNew);
	    display.setActionEnabled(Action.addToExisting, false);
	} else {
	    display.setActiveAction(Action.addToExisting);
	}
    }

}
