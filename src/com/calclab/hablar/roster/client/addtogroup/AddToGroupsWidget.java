package com.calclab.hablar.roster.client.addtogroup;

import com.calclab.hablar.core.client.ui.icon.HablarIcons;
import com.calclab.hablar.core.client.ui.icon.HablarIcons.IconType;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

public class AddToGroupsWidget extends Composite implements AddToGroupsDisplay {

    interface AddToGroupsWidgetUiBinder extends UiBinder<Widget, AddToGroupsWidget> {
    }

    private static AddToGroupsWidgetUiBinder uiBinder = GWT.create(AddToGroupsWidgetUiBinder.class);

    @UiField
    Button accept, cancel, newGroup;
    @UiField
    FlowPanel groupList;

    public AddToGroupsWidget() {
	initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public GroupSelectorDisplay addGroupSelector(final String name, final boolean editable, final boolean selected) {
	final GroupSelectorDisplay selector = new GroupSelectorWidget();
	selector.setIconStyle(HablarIcons.get(IconType.groupChat));
	selector.setEditable(editable);
	selector.getSelected().setValue(selected);
	selector.getStaticName().setText(name);
	selector.getEditableName().setText(name);
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
    public HasClickHandlers getNewGroup() {
	return newGroup;
    }

}
