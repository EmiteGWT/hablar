package com.calclab.hablar.roster.client.addtogroup;

import java.util.ArrayList;

import com.calclab.emite.im.client.roster.Roster;
import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.PagePresenter;
import com.calclab.hablar.core.client.validators.Empty;
import com.calclab.suco.client.Suco;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class AddToGroupsPresenter extends PagePresenter<AddToGroupsDisplay> {

    public static String TYPE = "AddToGroups";
    private RosterItem item;
    private final ArrayList<GroupSelectorDisplay> groupSelectors;

    public AddToGroupsPresenter(final HablarEventBus eventBus, final AddToGroupsDisplay display) {
	super(TYPE, eventBus, display);

	groupSelectors = new ArrayList<GroupSelectorDisplay>();

	display.getCancel().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		requestVisibility(Visibility.hidden);
	    }
	});

	display.getApply().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		addToNewGroups();
		requestVisibility(Visibility.hidden);
	    }
	});

	display.getNewGroup().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		addNewGroup("", true, true);
	    }
	});
    }

    public void setItem(final RosterItem item) {
	this.item = item;
    }

    private void addNewGroup(final String name, final boolean editable, final boolean selected) {
	groupSelectors.add(display.addGroupSelector(name, editable, selected));
    }

    private void addToNewGroups() {
	final Roster roster = Suco.get(Roster.class);

	for (final GroupSelectorDisplay selector : groupSelectors) {
	    if (isValidGroup(selector)) {
		item.addToGroup(selector.getEditableName().getText());
	    }
	}

	roster.requestUpdateItem(item);
    }

    private boolean isValidGroup(final GroupSelectorDisplay selector) {
	return selector.getSelected().getValue() == true && !Empty.is(selector.getEditableName().getText());
    }

    @Override
    protected void onBeforeFocus() {
	final Roster roster = Suco.get(Roster.class);
	display.clearGroupList();
	groupSelectors.clear();
	final ArrayList<String> groups = new ArrayList<String>(roster.getGroupNames());
	groups.removeAll(item.getGroups());
	for (final String name : groups) {
	    if (!Empty.is(name)) {
		addNewGroup(name, false, false);
	    }
	}
    }

}
