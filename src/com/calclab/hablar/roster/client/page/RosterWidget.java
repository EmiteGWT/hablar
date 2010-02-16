package com.calclab.hablar.roster.client.page;

import com.calclab.hablar.core.client.ui.actions.ActionWidget;
import com.calclab.hablar.core.client.ui.menu.Action;
import com.calclab.hablar.core.client.ui.menu.Menu;
import com.calclab.hablar.core.client.ui.menu.MenuDisplay;
import com.calclab.hablar.core.client.ui.menu.PopupMenu;
import com.calclab.hablar.roster.client.groups.RosterGroupDisplay;
import com.calclab.hablar.roster.client.groups.RosterGroupPresenter;
import com.calclab.hablar.roster.client.groups.RosterGroupWidget;
import com.calclab.hablar.roster.client.groups.RosterItemDisplay;
import com.calclab.hablar.roster.client.groups.RosterItemPresenter;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.HasClickHandlers;
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
    FlowPanel actions, disabledPanel;

    @UiField
    RosterListWidget list;

    @UiField
    Label disabledLabel;
    private final RosterListPresenter groups;

    public RosterWidget() {
	initWidget(uiBinder.createAndBindUi(this));
	roster.ensureDebugId("RosterWidget-roster");
	scroll.ensureDebugId("RosterWidget-scroll");
	actions.ensureDebugId("RosterWidget-actions");
	disabledLabel.ensureDebugId("RosterWidget-disabledPanel");
	groups = new RosterListPresenter(list);
    }

    @Override
    public void addGroup(final RosterGroupPresenter group, final Menu<RosterGroupPresenter> menu) {
	groups.add(group, menu);
    }

    @Override
    public Widget asWidget() {
	return this;
    }

    @Override
    public HasClickHandlers createAction(final Action<?> action) {
	final ActionWidget widget = new ActionWidget(action);
	actions.add(widget);
	return widget;

    }

    @Override
    public RosterGroupDisplay newRosterGroupDisplay() {
	return new RosterGroupWidget();
    }

    @Override
    public MenuDisplay<RosterGroupPresenter> newRosterGroupMenuDisplay(final String menuId) {
	return new PopupMenu<RosterGroupPresenter>(menuId);
    }

    @Override
    public MenuDisplay<RosterItemPresenter> newRosterItemMenuDisplay(final String menuId) {
	return new PopupMenu<RosterItemPresenter>(menuId);
    }

    @Override
    public void remove(final RosterItemDisplay itemDisplay) {
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
