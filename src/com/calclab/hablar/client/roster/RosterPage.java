package com.calclab.hablar.client.roster;

import com.calclab.hablar.client.ui.page.PageWidget;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.Widget;

public class RosterPage extends PageWidget implements RosterView {

    public static interface Icons extends CssResource {

	String action();

	String rosterIcon();
    }

    interface RosterWidgetUiBinder extends UiBinder<Widget, RosterPage> {
    }

    public static final String ID = "RosterPage";

    private static RosterWidgetUiBinder uiBinder = GWT.create(RosterWidgetUiBinder.class);

    @UiField
    LayoutPanel roster;

    @UiField
    Icons icons;

    @UiField
    FlowPanel list, actions, disabledPanel;

    private final RosterLogic logic;

    public RosterPage() {
	super(false);
	setId(ID);
	initWidget(uiBinder.createAndBindUi(this));
	logic = new RosterLogic(this);
	setHeaderTitle("Roster");
	setHeaderIconClass(icons.rosterIcon());
    }

    public void addAction(final String iconStyle, final ClickHandler clickHandler) {
	final Label label = new Label();
	label.getElement().addClassName(icons.action());
	label.getElement().addClassName(iconStyle);
	label.addClickHandler(clickHandler);
	actions.add(label);
    }

    @Override
    public RosterItemView createItemView() {
	RosterItemWidget view = new RosterItemWidget(logic);
	list.add(view);
	return view;
    }

    public void setActive(final boolean active) {
	GWT.log("ROSTER: " + active, null);
	if (active) {
	    roster.setWidgetTopHeight(actions, 0, Unit.PX, 20, Unit.PX);
	    roster.setWidgetTopHeight(disabledPanel, 0, Unit.PX, 0, Unit.PX);
	    roster.setWidgetTopBottom(list, 20, Unit.PX, 0, Unit.PX);
	} else {
	    roster.setWidgetTopHeight(actions, 0, Unit.PX, 0, Unit.PX);
	    roster.setWidgetTopBottom(disabledPanel, 0, Unit.PX, 0, Unit.PX);
	    roster.setWidgetBottomHeight(list, 0, Unit.PX, 0, Unit.PX);
	}
	roster.animate(500);
    }
}
