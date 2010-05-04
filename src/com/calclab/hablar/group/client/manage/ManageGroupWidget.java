package com.calclab.hablar.group.client.manage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.hablar.core.client.ui.selectionlist.DoubleList;
import com.calclab.hablar.core.client.validators.HasState;
import com.calclab.hablar.group.client.userlist.RosterItemSelectable;
import com.calclab.hablar.roster.client.groups.RosterItemWidget;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasKeyDownHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class ManageGroupWidget extends Composite implements ManageGroupDisplay {

    private static ManageGroupWidgetUiBinder uiBinder = GWT.create(ManageGroupWidgetUiBinder.class);

    @UiField
    TextBox groupName;

    @UiField
    Label groupNameError;

    @UiField
    Button accept, cancel;

    @UiField
    DoubleList selectionList;

    interface ManageGroupWidgetUiBinder extends UiBinder<Widget, ManageGroupWidget> {
    }

    public ManageGroupWidget() {
	initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public Widget asWidget() {
	return this;
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
	RosterItemWidget widget = new RosterItemWidget("selection", rosterItem);
	widget.setMenuVisible(false);
	RosterItemSelectable selectable = new RosterItemSelectable(rosterItem, widget);
	selectionList.add(selectable);
    }

    @Override
    public void clearSelectionList() {
	groupName.setText("");
	selectionList.clear();
    }

    @Override
    public String getGroupNameText() {
	return groupName.getText();
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

    @Override
    public HasText getGroupName() {
	return groupName;
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
}
