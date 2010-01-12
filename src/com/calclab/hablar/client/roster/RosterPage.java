package com.calclab.hablar.client.roster;

import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.hablar.client.pages.HeaderStyles;
import com.calclab.hablar.client.pages.PageWidget;
import com.calclab.suco.client.events.Listener;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class RosterPage extends PageWidget {

    public static interface Styles extends HeaderStyles {
    }

    interface RosterWidgetUiBinder extends UiBinder<Widget, RosterPage> {
    }

    private static RosterWidgetUiBinder uiBinder = GWT.create(RosterWidgetUiBinder.class);

    @UiField
    LayoutPanel roster;

    @UiField
    Styles header;

    @UiField
    FlowPanel list;
    @UiField
    TextBox addBuddyBox;
    @UiField
    Label addBuddyIcon, questionBody;
    @UiField
    HTMLPanel addBuddyPanel, questionPanel;
    @UiField
    Button btnYes, btnNo;

    private final RosterLogic logic;
    private Listener<Boolean> answer;

    private boolean isAddBuddyPanelVisible;
    private boolean isQuestionPanelVisible;

    public RosterPage() {
	super(false);
	this.isAddBuddyPanelVisible = false;
	this.isQuestionPanelVisible = false;
	initWidget(uiBinder.createAndBindUi(this));
	this.logic = new RosterLogic(this);
	setHeaderTitle("Roster");
	setHeaderStyles(header);
    }

    public void addItem(RosterItemWidget itemWidget) {
	list.add(itemWidget);
    }

    public void ask(String body, Listener<Boolean> answer) {
	this.answer = answer;
	questionBody.setText(body);
    }

    @UiHandler("addBuddyBox")
    public void handleChange(ChangeEvent event) {
	try {
	    logic.addBuddy(XmppURI.uri(addBuddyBox.getText()));
	} catch (RuntimeException e) {

	}
    }

    public boolean isAddBuddyPanelVisible() {
	return isAddBuddyPanelVisible;
    }

    @UiHandler("addBuddyIcon")
    public void onAddBuddy(ClickEvent event) {
	logic.toggleAddBuddyAction();
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

    public void setAddBuddyPanelVisible(boolean visible) {
	if (visible != isAddBuddyPanelVisible) {
	    isAddBuddyPanelVisible = visible;
	    if (visible) {
		roster.setWidgetBottomHeight(addBuddyPanel, 20, Unit.PX, 30, Unit.PX);
	    } else {
		roster.setWidgetBottomHeight(addBuddyPanel, 0, Unit.PX, 0, Unit.PX);
	    }
	    roster.animate(250);
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
