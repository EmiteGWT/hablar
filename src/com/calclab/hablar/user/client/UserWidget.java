package com.calclab.hablar.user.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class UserWidget extends Composite implements UserDisplay {

    interface UserWidgetUiBinder extends UiBinder<Widget, UserWidget> {
    }

    private static UserWidgetUiBinder uiBinder = GWT.create(UserWidgetUiBinder.class);

    public UserWidget() {
	initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public Widget asWidget() {
	return this;
    }

}
