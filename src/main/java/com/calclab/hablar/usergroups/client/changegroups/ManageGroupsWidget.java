package com.calclab.hablar.usergroups.client.changegroups;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

public class ManageGroupsWidget extends Composite implements ManageGroupsDisplay {

	private static AddToGroupsWidgetUiBinder uiBinder = GWT.create(AddToGroupsWidgetUiBinder.class);

	interface AddToGroupsWidgetUiBinder extends UiBinder<Widget, ManageGroupsWidget> {
	}

	@UiField
	Button accept, cancel, newGroup;
	@UiField
	FlowPanel groupList;
	@UiField
	HasText contactName;

	public ManageGroupsWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		accept.ensureDebugId("ManageGroupsWidget-accept");
		newGroup.ensureDebugId("ManageGroupsWidget-newGroup");
	}

	@Override
	public GroupSelectorDisplay addGroupSelector() {
		final GroupSelectorDisplay selector = new GroupSelectorWidget();
		groupList.add(selector.asWidget());
		return selector;
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
