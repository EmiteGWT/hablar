package com.calclab.hablar.roster.client.ui.move;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class MoveToGroupWidget extends Composite implements MoveToGroupDisplay {

    interface MoveToGroupWidgetUiBinder extends UiBinder<Widget, MoveToGroupWidget> {
    }

    private static MoveToGroupWidgetUiBinder uiBinder = GWT.create(MoveToGroupWidgetUiBinder.class);

    @UiField
    Button apply, cancel;

    @UiField
    TextBox newGroupName;

    public MoveToGroupWidget() {
	initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public Widget asWidget() {
	return this;
    }

    public Button getApply() {
	return apply;
    }

    public Button getCancel() {
	return cancel;
    }

    public TextBox getNewGroupName() {
	return newGroupName;
    }
}
