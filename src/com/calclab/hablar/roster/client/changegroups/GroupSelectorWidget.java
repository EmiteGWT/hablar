package com.calclab.hablar.roster.client.changegroups;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class GroupSelectorWidget extends Composite implements GroupSelectorDisplay {

    interface GroupSelectorWidgetUiBinder extends UiBinder<Widget, GroupSelectorWidget> {
    }

    private static GroupSelectorWidgetUiBinder uiBinder = GWT.create(GroupSelectorWidgetUiBinder.class);

    @UiField
    CheckBox select;

    @UiField
    Label staticName, icon;
    @UiField
    TextBox editableName;

    public GroupSelectorWidget() {
	initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public Widget asWidget() {
	return this;
    }

    @Override
    public HasText getEditableName() {
	return editableName;
    }

    @Override
    public HasValue<Boolean> getSelected() {
	return select;
    }

    @Override
    public HasText getStaticName() {
	return staticName;
    }

    @Override
    public void setEditable(final boolean editable) {
	editableName.setVisible(editable);
	staticName.setVisible(!editable);
    }

    @Override
    public void setIconStyle(final String style) {
	icon.addStyleName(style);
    }

}
