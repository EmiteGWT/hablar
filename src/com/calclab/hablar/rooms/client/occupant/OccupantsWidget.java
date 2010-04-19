package com.calclab.hablar.rooms.client.occupant;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasMouseOutHandlers;
import com.google.gwt.event.dom.client.HasMouseOverHandlers;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;

public class OccupantsWidget extends Label implements OccupantsDisplay {
    private final PopupPanel popup;
    private final FlowPanel occupants;

    public OccupantsWidget(final String roomId) {
	this.setStyleName("hablar-OccupantsWidget");
	this.ensureDebugId("hablar-OccupantsWidget-" + roomId);
	popup = new PopupPanel(true);
	popup.addStyleName("occupants");
	occupants = new FlowPanel();
	popup.setWidget(occupants);
	popup.setAnimationEnabled(false);
	popup.addAutoHidePartner(getElement());
    }

    @Override
    public OccupantDisplay addOccupant() {
	final OccupantWidget occupantWidget = new OccupantWidget();
	occupants.add(occupantWidget);
	return occupantWidget;
    }

    @Override
    public Widget asWidget() {
	return this;
    }

    @Override
    public void clearPanel() {
	occupants.clear();
    }

    @Override
    public HasClickHandlers getAction() {
	return this;
    }

    @Override
    public HasText getLabel() {
	return this;
    }

    @Override
    public HasMouseOutHandlers getOutAction() {
	return this;
    }

    @Override
    public HasMouseOverHandlers getOverAction() {
	return this;
    }

    @Override
    public boolean isPanelVisible() {
	return popup.isShowing();
    }

    @Override
    public void setPanelVisible(final boolean visible) {
	if (visible) {
	    popup.showRelativeTo(this);
	} else {
	    popup.hide();
	}
    }

}
