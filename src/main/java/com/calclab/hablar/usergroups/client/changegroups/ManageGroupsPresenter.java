package com.calclab.hablar.usergroups.client.changegroups;

import java.util.ArrayList;
import java.util.List;

import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.emite.im.client.roster.XmppRoster;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.PagePresenter;
import com.calclab.hablar.core.client.validators.Empty;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class ManageGroupsPresenter extends PagePresenter<ManageGroupsDisplay> {

	public static String TYPE = "AddToGroups";
	private RosterItem item;
	private final ArrayList<GroupSelectorPresenter> groupSelectors;
	private final XmppRoster roster;

	public ManageGroupsPresenter(final XmppRoster roster, final HablarEventBus eventBus, final ManageGroupsDisplay display) {
		super(TYPE, eventBus, display);
		this.roster = roster;

		groupSelectors = new ArrayList<GroupSelectorPresenter>();

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

	private void addNewGroup(final String name, final boolean editable, final boolean selected) {
		final GroupSelectorDisplay selectorDisplay = display.addGroupSelector();
		final GroupSelectorPresenter selector = new GroupSelectorPresenter(selectorDisplay);
		selector.setProperties(name, editable, selected);
		groupSelectors.add(selector);
	}

	private void addToNewGroups() {
		item.getGroups().clear();
		for (final GroupSelectorPresenter selector : groupSelectors) {
			if (selector.isSelected()) {
				item.addToGroup(selector.getName());
			}
		}

		roster.requestUpdateItem(item);
	}

	@Override
	protected void onBeforeFocus() {
		display.getContactNameField().setText(item.getJID().toString());
		display.clearGroupList();
		groupSelectors.clear();
		final List<String> belongingGroups = item.getGroups();
		for (final String name : belongingGroups) {
			if (!Empty.is(name)) {
				addNewGroup(name, false, true);
			}
		}
		final ArrayList<String> groups = new ArrayList<String>(roster.getGroupNames());
		groups.removeAll(belongingGroups);
		for (final String name : groups) {
			if (!Empty.is(name)) {
				addNewGroup(name, false, false);
			}
		}
	}

	public void setItem(final RosterItem item) {
		this.item = item;
	}

}
