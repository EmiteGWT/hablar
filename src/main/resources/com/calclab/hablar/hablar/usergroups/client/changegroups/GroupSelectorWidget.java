package com.calclab.hablar.usergroups.client.changegroups;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Image;
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
    Label staticName;
    @UiField
    TextBox editableName;
    @UiField
    Image icon;

    public GroupSelectorWidget() {
	initWidget(uiBinder.createAndBindUi(this));
	editableName.ensureDebugId("GroupSelectorWidget-editableName");
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
    public void setIcon(final ImageResource icon) {
	String style = this.icon.getStyleName();
	this.icon.setResource(icon);
	this.icon.setStyleName(style);
    }

}
