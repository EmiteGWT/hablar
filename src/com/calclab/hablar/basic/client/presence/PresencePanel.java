package com.calclab.hablar.basic.client.presence;

import com.calclab.hablar.basic.client.ui.styles.HablarStyles;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.BlurEvent;
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
	    doStatusChange();
	    event.stopPropagation();
	    event.preventDefault();
	}
    }

    @UiHandler("btnState")
    public void onChangeState(final ClickEvent e) {
	logic.onShowChange();
    }

    @UiHandler("status")
    public void onChangeStatus(final ClickEvent e) {
	logic.onChangeStatusMessage();
    }

    @Override
    public void setIcon(final HablarStyles.IconType icon) {
	final String iconClass = HablarStyles.get(icon);
	if (this.iconClass != null) {
	    btnState.getElement().removeClassName(this.iconClass);
	}
	this.iconClass = iconClass;
	btnState.getElement().addClassName(iconClass);
    }

    public void setName(final String name) {
	if (name != null) {
	    nick.setText(name);
	    nick.setVisible(true);
	} else {
	    nick.setVisible(false);
	}
    }

    @Override
    public void setStatusBoxFocus(final boolean focus) {
	statusBox.setFocus(focus);
    }

    @Override
    public void setStatusBoxVisible(final boolean visible) {
	statusBox.setVisible(visible);
    }

    @Override
    public void setStatusMessage(final String statusMessage) {
	status.setText(statusMessage);
    }

    @Override
    public void setStatusMessageVisible(final boolean visible) {
	status.setVisible(visible);
    }

    @UiHandler("statusBox")
    void onBlur(final BlurEvent event) {
	doStatusChange();
    }

    private void doStatusChange() {
	final String message = statusBox.getText();
	logic.onStatusMessageChanged(message);
    }

}
