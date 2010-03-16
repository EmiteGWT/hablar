package com.calclab.hablar.roster.client.changegroups;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class ManageGroupsWidget extends Composite implements ManageGroupsDisplay {

    interface AddToGroupsWidgetUiBinder extends UiBinder<Widget, ManageGroupsWidget> {
    }

    private static AddToGroupsWidgetUiBinder uiBinder = GWT.create(AddToGroupsWidgetUiBinder.class);

    @UiField
    Button accept, cancel, newGroup;
    @UiField
    FlowPanel groupList;
    @UiField
    Label contactName;

    public ManageGroupsWidget() {
	initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public GroupSelectorDisplay addGroupSelector() {
	final GroupSelectorDisplay selector = new GroupSelectorWidget();
	groupList.add(selector.asWidget());
	return selector;
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
    public HasClickHandlers getApply() {
	return accept;
    }

    @Override
    public HasClickHandlers getCancel() {
	return cancel;
    }

    @Override
    public HasText getContactNameField() {
	return contactName;
    }

    @Override
    public HasClickHandlers getNewGroup() {
	return newGroup;
    }

}
