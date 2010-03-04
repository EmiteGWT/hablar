package com.calclab.hablar.roster.client.addtogroup;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasFocusHandlers;
import com.google.gwt.event.dom.client.HasKeyDownHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class AddToGroupWidget extends Composite implements AddToGroupDisplay {

    interface MoveToGroupWidgetUiBinder extends UiBinder<Widget, AddToGroupWidget> {
    }

    private static MoveToGroupWidgetUiBinder uiBinder = GWT.create(MoveToGroupWidgetUiBinder.class);

    @UiField
    Button accept, cancel;
    @UiField
    RadioButton existingGroup, newGroup;
    @UiField
    TextBox newGroupName;
    @UiField
    Label groupNameError, contact;
    @UiField
    ListBox groupList;

    public AddToGroupWidget() {
	initWidget(uiBinder.createAndBindUi(this));
	newGroupName.ensureDebugId("AddToGroupWidget-newgroupname");
	accept.ensureDebugId("AddToGroupWidget-accept");
    }

    @Override
    public void addToGroupList(final String groupName) {
	groupList.addItem(groupName);
    }

    @Override
    public Widget asWidget() {
	return this;
    }

    @Override
    public void clearGroupList() {
	groupList.clear();
    }

    @Override
    public HasValue<Boolean> getAddToExisting() {
	return existingGroup;
    }

    @Override
    public HasValue<Boolean> getAddToNew() {
	return newGroup;
    }

    public Button getApply() {
	return accept;
    }

    public Button getCancel() {
	return cancel;
    }

    @Override
    public HasText getContact() {
	return contact;
    }

    public TextBox getGroupName() {
	return newGroupName;
    }

    @Override
    public HasFocusHandlers getNewGroupFocus() {
	return newGroupName;
    }

    @Override
    public HasKeyDownHandlers getNewGroupKeys() {
	return newGroupName;
    }

    @Override
    public HasText getNewGroupNameError() {
	return groupNameError;
    }

    @Override
    public String getSelectedGroupName() {
	return groupList.getItemText(groupList.getSelectedIndex());
    }

    @Override
    public HasFocusHandlers getSelectGroupFocus() {
	return groupList;
    }

    @Override
    public void setAcceptEnabled(final boolean enabled) {
	accept.setEnabled(enabled);
    }

    @Override
    public void setActionEnabled(final Action action, final boolean enabled) {
	getActionButton(action).setEnabled(enabled);
    }

    @Override
    public void setActiveAction(final Action action) {
	getActionButton(action).setValue(true);
    }

    private RadioButton getActionButton(final Action action) {
	final RadioButton radio = action == Action.addToExisting ? existingGroup : newGroup;
	return radio;
    }

}
