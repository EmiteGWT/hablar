package com.calclab.hablar.roster.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;

public class RosterWidget extends Composite implements RosterDisplay {

    interface RosterWidgetUiBinder extends UiBinder<Widget, RosterWidget> {
    }

    private static RosterWidgetUiBinder uiBinder = GWT.create(RosterWidgetUiBinder.class);
    @UiField
    LayoutPanel roster;
    @UiField
    ScrollPanel scroll;
    @UiField
    FlowPanel list, actions, disabledPanel;

    @UiField
    Label disabledLabel;

    public RosterWidget() {
	initWidget(uiBinder.createAndBindUi(this));
	roster.ensureDebugId("RosterWidget-roster");
	scroll.ensureDebugId("RosterWidget-scroll");
	list.ensureDebugId("RosterWidget-list");
	actions.ensureDebugId("RosterWidget-actions");
	disabledLabel.ensureDebugId("RosterWidget-disabledPanel");
    }

    @Override
    public void add(RosterItemDisplay itemDisplay) {
	list.add(itemDisplay.asWidget());
    }

    @Override
    public void addAction(final String iconStyle, final String id, final ClickHandler clickHandler) {
	final Label label = new Label();
	label.addStyleName(iconStyle);
	label.addClickHandler(clickHandler);
	label.ensureDebugId(id);
	actions.add(label);
    }

    @Override
    public Widget asWidget() {
	return this;
    }

    @Override
    public RosterItemDisplay newRosterItemDisplay() {
	return new RosterItemWidget();
    }

    @Override
    public void remove(RosterItemDisplay itemDisplay) {
	list.remove(itemDisplay.asWidget());
    }

    public void setActive(final boolean active) {
	GWT.log("ROSTER: " + active, null);
	if (active) {
	    roster.setWidgetTopHeight(actions, 0, Unit.PX, 20, Unit.PX);
	    roster.setWidgetTopHeight(disabledPanel, 0, Unit.PX, 0, Unit.PX);
	    roster.setWidgetTopBottom(scroll, 20, Unit.PX, 0, Unit.PX);
	} else {
	    roster.setWidgetTopHeight(actions, 0, Unit.PX, 0, Unit.PX);
	    roster.setWidgetTopBottom(disabledPanel, 0, Unit.PX, 0, Unit.PX);
	    roster.setWidgetBottomHeight(scroll, 0, Unit.PX, 0, Unit.PX);
	}
	roster.animate(active ? 500 : 0);
    }

}
