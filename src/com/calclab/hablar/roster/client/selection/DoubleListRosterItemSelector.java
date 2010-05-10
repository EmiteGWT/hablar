package com.calclab.hablar.roster.client.selection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.hablar.core.client.ui.selectionlist.DoubleList;
import com.calclab.hablar.group.client.userlist.RosterItemSelectable;
import com.calclab.hablar.roster.client.RosterMessages;
import com.calclab.hablar.roster.client.groups.RosterItemWidget;

/**
 * An implementation of {@link RosterItemSelector} that uses a double list for the selection.
 */
public class DoubleListRosterItemSelector implements RosterItemSelector {

    private DoubleList selectionList;

    private static RosterMessages messages;

    public static void setMessages(final RosterMessages messages) {
	DoubleListRosterItemSelector.messages = messages;
    }

    public static RosterMessages i18n() {
	return messages;
    }

    public DoubleListRosterItemSelector(DoubleList selectionList) {
	this.selectionList = selectionList;
	selectionList.setAvailableLabelText(i18n().availableUsersLabelText());
	selectionList.setSelectedLabelText(i18n().selectedUsersLabelText());
	selectionList.setSelectAllTooltip(i18n().selectAllTooltip());
	selectionList.setSelectSomeTooltip(i18n().selectSomeTooltip());
	selectionList.setDeselectAllTooltip(i18n().deselectAllTooltip());
	selectionList.setDeselectSomeTooltip(i18n().deselectSomeTooltip());
    }

    @Override
    public void addRosterItem(RosterItem rosterItem) {
	RosterItemWidget widget = new RosterItemWidget("selection", rosterItem);
	widget.setMenuVisible(false);
	RosterItemSelectable selectable = new RosterItemSelectable(rosterItem, widget);
	selectionList.add(selectable);
    }

    @Override
    public void addSelectedRosterItem(RosterItem rosterItem) {
	RosterItemWidget widget = new RosterItemWidget("selection", rosterItem);
	widget.setMenuVisible(false);
	RosterItemSelectable selectable = new RosterItemSelectable(rosterItem, widget);
	selectionList.addSelected(selectable);
    }

    @Override
    public void clearSelectionList() {
	selectionList.clear();
    }

    @Override
    public Collection<RosterItem> getSelectedItems() {
	List<Object> items = selectionList.getSelectedItems();
	ArrayList<RosterItem> retValue = new ArrayList<RosterItem>(items.size());
	for (Object item : items) {
	    retValue.add((RosterItem) item);
	}
	return retValue;
    }
}
