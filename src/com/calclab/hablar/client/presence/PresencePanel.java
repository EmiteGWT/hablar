package com.calclab.hablar.client.presence;

import com.calclab.hablar.client.ui.styles.HablarStyles;
import com.calclab.hablar.client.ui.styles.HablarStyles.IconType;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class PresencePanel extends Composite implements PresenceView {

    interface StatePanelUiBinder extends UiBinder<Widget, PresencePanel> {
    }

    private static StatePanelUiBinder uiBinder = GWT.create(StatePanelUiBinder.class);

    @UiField
    Label btnState, nick, status;

    @UiField
    TextBox statusBox;

    private String iconClass;

    private final PresenceLogic logic;

    public PresencePanel() {
	initWidget(uiBinder.createAndBindUi(this));
	logic = new PresenceLogic(this);
    }

    @UiHandler("statusBox")
    public void handleKeys(final KeyDownEvent event) {
	if (event.getNativeKeyCode() == 13) {
	    final String message = statusBox.getText();
	    logic.onStatusMessageChanged(message);
	    event.stopPropagation();
	    event.preventDefault();
	}
    }

    @UiHandler("btnState")
    public void onChangeState(ClickEvent e) {
	logic.onShowChange();
    }

    @UiHandler("status")
    public void onChangeStatus(ClickEvent e) {
	logic.onChangeStatusMessage();
    }

    @Override
    public void setIcon(HablarStyles.IconType icon) {
	String iconClass = HablarStyles.get(icon);
	if (this.iconClass != null) {
	    btnState.getElement().removeClassName(this.iconClass);
	}
	this.iconClass = iconClass;
	btnState.getElement().addClassName(iconClass);
    }

    public void setName(String name) {
	if (name != null) {
	    nick.setText(name);
	    nick.setVisible(true);
	} else {
	    nick.setVisible(false);
	}
    }

    @Override
    public void setStatusBoxVisible(boolean visible) {
	statusBox.setVisible(visible);
    }

    @Override
    public void setStatusMessage(String statusMessage) {
	status.setText(statusMessage);
    }

    @Override
    public void setStatusMessageVisible(boolean visible) {
	status.setVisible(visible);
    }

}
