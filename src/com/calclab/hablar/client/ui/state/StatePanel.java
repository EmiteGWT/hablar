package com.calclab.hablar.client.ui.state;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class StatePanel extends Composite {

    interface Icons extends CssResource {
	String iconDnd();

	String iconOff();

	String iconOn();

	String iconWait();
    }

    interface StatePanelUiBinder extends UiBinder<Widget, StatePanel> {
    }

    private static StatePanelUiBinder uiBinder = GWT.create(StatePanelUiBinder.class);

    @UiField
    Icons style;

    @UiField
    Label btnState, nick, status;

    @UiField
    TextBox statusBox;

    private String iconClass;

    private final StatePanelLogic logic;

    public StatePanel() {
	initWidget(uiBinder.createAndBindUi(this));
	logic = new StatePanelLogic(this);
	statusBox.setVisible(false);
    }

    @UiHandler("btnState")
    public void onChangeState(ClickEvent e) {
	logic.onShowChange();
    }

    @UiHandler("status")
    public void onChangeStatus(ClickEvent e) {

    }

    public void setIconClass(String iconClass) {
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

}
