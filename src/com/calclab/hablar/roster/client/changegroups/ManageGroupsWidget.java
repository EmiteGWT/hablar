package com.calclab.hablar.roster.client.changegroups;

import com.calclab.hablar.roster.client.RosterMessages;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.LabelElement;
import com.google.gwt.dom.client.SpanElement;
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

    private static RosterMessages messages;

    public static void setMessages(final RosterMessages messages) {
	ManageGroupsWidget.messages = messages;
    }

    public static RosterMessages i18n() {
	return messages;
    }

    interface AddToGroupsWidgetUiBinder extends UiBinder<Widget, ManageGroupsWidget> {
    }

    private static AddToGroupsWidgetUiBinder uiBinder = GWT.create(AddToGroupsWidgetUiBinder.class);

    @UiField
    SpanElement title;
    @UiField
    Button accept, cancel, newGroup;
    @UiField
    FlowPanel groupList;
    @UiField
    Label contactName;
    @UiField
    LabelElement contactNameLabel, groupListLabel;

    public ManageGroupsWidget() {
	initWidget(uiBinder.createAndBindUi(this));
	accept.ensureDebugId("ManageGroupsWidget-accept");
	newGroup.ensureDebugId("ManageGroupsWidget-newGroup");
	title.setInnerText(i18n().changeContactGroupsTitle());
	accept.setText(i18n().acceptAction());
	cancel.setText(i18n().cancelAction());
	newGroup.setText(i18n().newGroupAction());
	contactNameLabel.setInnerText(i18n().contactNameLabelText());
	groupListLabel.setInnerText(i18n().rosterGroupsLabelText());
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
