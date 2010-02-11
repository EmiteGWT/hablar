package com.calclab.hablar.roster.client.ui.groups;

import com.calclab.hablar.core.client.ui.icon.HablarIcons;
import com.calclab.hablar.core.client.ui.icon.HablarIcons.IconType;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class GroupHeaderWidget extends Composite implements GroupHeaderDisplay {

    interface GroupHeaderWidgetUiBinder extends UiBinder<Widget, GroupHeaderWidget> {
    }

    private static GroupHeaderWidgetUiBinder uiBinder = GWT.create(GroupHeaderWidgetUiBinder.class);

    @UiField
    Label name, menu;

    public GroupHeaderWidget(final String id) {
	initWidget(uiBinder.createAndBindUi(this));
	name.ensureDebugId("GroupHeaderWidget-name-" + id);
	menu.ensureDebugId("GroupHeaderWidget-menu-" + id);
	menu.addStyleName(HablarIcons.get(IconType.menu));
    }

    @Override
    public Widget asWidget() {
	return this;
    }

    @Override
    public HasText getName() {
	return name;
    }

}
