package com.calclab.hablar.group.client.manage;

import java.util.Collection;
import java.util.List;

import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.hablar.core.client.ui.selectionlist.DoubleList;
import com.calclab.hablar.core.client.ui.selectionlist.Selectable;
import com.calclab.hablar.core.client.validators.HasState;
import com.calclab.hablar.roster.client.selection.DoubleListRosterItemSelector;
import com.calclab.hablar.roster.client.selection.RosterItemSelector;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasKeyDownHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * A form to manage a group in the roster.
 */
public class ManageGroupWidget extends Composite implements ManageGroupDisplay {

	interface ManageGroupWidgetUiBinder extends UiBinder<Widget, ManageGroupWidget> {
	}

	private static ManageGroupWidgetUiBinder uiBinder = GWT.create(ManageGroupWidgetUiBinder.class);

	@UiField
	SpanElement title;

	@UiField
	TextBox groupName;

	@UiField
	HasText groupNameError;

	@UiField
	Button accept, cancel;

	@UiField
	DoubleList selectionList;

	private RosterItemSelector selector;

	public ManageGroupWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		selector = new DoubleListRosterItemSelector(selectionList);
	}

	@Override
	public HasClickHandlers getApply() {
		return accept;
	}

	@Override
	public HasClickHandlers getCancel() {
		return cancel;
	}

	@Override
	public void addRosterItem(RosterItem rosterItem) {
		selector.addRosterItem(rosterItem);
	}

	@Override
	public void addSelectedRosterItem(RosterItem rosterItem) {
		selector.addSelectedRosterItem(rosterItem);
	}

	@Override
	public void clearSelectionList() {
		groupName.setText("");
		selector.clearSelectionList();
	}

	@Override
	public String getGroupNameText() {
		return groupName.getText();
	}

	@Override
	public Collection<RosterItem> getSelectedItems() {
		return selector.getSelectedItems();
	}

	@Override
	public HasValue<String> getGroupName() {
		return groupName;
	}

	@Override
	public HasValue<List<Selectable>> getSelectionList() {
		return selectionList;
	}

	@Override
	public HasKeyDownHandlers getGroupNameKeys() {
		return groupName;
	}

	@Override
	public HasState<Boolean> getAcceptEnabled() {
		return new HasState<Boolean>() {
			@Override
			public void setState(final Boolean state) {
				accept.setEnabled(state);
			}
		};
	}

	@Override
	public HasText getGroupNameError() {
		return groupNameError;
	}

	@Override
	public void setPageTitle(String title) {
		this.title.setInnerText(title);
	}
}
