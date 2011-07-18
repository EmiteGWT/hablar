package com.calclab.hablar.roster.client.selection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.hablar.core.client.ui.selectionlist.DoubleList;
import com.calclab.hablar.roster.client.RosterMessages;
import com.calclab.hablar.roster.client.groups.RosterItemWidget;

/**
 * An implementation of {@link RosterItemSelector} that uses a double list for
 * the selection.
 */
public class DoubleListRosterItemSelector implements RosterItemSelector {

	private DoubleList selectionList;

	public DoubleListRosterItemSelector(DoubleList selectionList) {
		this.selectionList = selectionList;
		selectionList.setAvailableLabelText(RosterMessages.msg.availableUsersLabelText());
		selectionList.setSelectedLabelText(RosterMessages.msg.selectedUsersLabelText());
		selectionList.setSelectAllTooltip(RosterMessages.msg.selectAllTooltip());
		selectionList.setSelectSomeTooltip(RosterMessages.msg.selectSomeTooltip());
		selectionList.setDeselectAllTooltip(RosterMessages.msg.deselectAllTooltip());
		selectionList.setDeselectSomeTooltip(RosterMessages.msg.deselectSomeTooltip());
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
