package com.calclab.hablar.client.roster;

import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.hablar.client.ui.page.PageWidget;
import com.calclab.suco.client.events.Listener;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
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

    private static RosterWidgetUiBinder uiBinder = GWT.create(RosterWidgetUiBinder.class);

    @UiField
    LayoutPanel roster;

    @UiField
    Icons icons;

    @UiField
    FlowPanel list, actions;
    @UiField
    Label questionBody;
    @UiField
    HTMLPanel questionPanel;
    @UiField
    Button btnYes, btnNo;

    private Listener<Boolean> answer;

    private final boolean isAddBuddyPanelVisible;
    private boolean isQuestionPanelVisible;
    private final RosterLogic logic;

    public RosterPage() {
	super(false);
	this.isAddBuddyPanelVisible = false;
	this.isQuestionPanelVisible = false;
	initWidget(uiBinder.createAndBindUi(this));
	logic = new RosterLogic(this);
	setHeaderTitle("Roster");
	setHeaderIconClass(icons.rosterIcon());
    }

    public void addAction(String iconStyle, ClickHandler clickHandler) {
	Label label = new Label();
	label.getElement().addClassName(icons.action());
	label.getElement().addClassName(iconStyle);
	label.addClickHandler(clickHandler);
	actions.add(label);
    }

    @Override
    public RosterItemView addItem(RosterItem item) {
	RosterItemWidget view = new RosterItemWidget(item, logic);
	list.add(view);
	return view;
    }

    public void ask(String body, Listener<Boolean> answer) {
	this.answer = answer;
	questionBody.setText(body);
    }

    public boolean isAddBuddyPanelVisible() {
	return isAddBuddyPanelVisible;
    }

    @UiHandler("btnNo")
    public void onNo(ClickEvent event) {
	if (this.answer != null) {
	    answer.onEvent(false);
	}
    }

    @UiHandler("btnYes")
    public void onYes(ClickEvent event) {
	if (this.answer != null) {
	    answer.onEvent(true);
	}
    }

    public void setQuestionVisible(boolean visible) {
	if (visible != isQuestionPanelVisible) {
	    isQuestionPanelVisible = visible;
	    if (visible) {
		roster.setWidgetTopHeight(questionPanel, 0, Unit.PX, 60, Unit.PX);
		roster.setWidgetTopBottom(list, 60, Unit.PX, 50, Unit.PX);
	    } else if (!visible) {
		roster.setWidgetTopHeight(questionPanel, 0, Unit.PX, 0, Unit.PX);
		roster.setWidgetTopBottom(list, 0, Unit.PX, 50, Unit.PX);
	    }
	    roster.animate(500);
	}
    }

}
