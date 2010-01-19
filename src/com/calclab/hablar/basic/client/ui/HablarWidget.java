package com.calclab.hablar.basic.client.ui;

import static com.google.gwt.dom.client.Style.Unit.PX;

import com.calclab.hablar.basic.client.HablarLogic;
import com.calclab.hablar.basic.client.login.LoginPage;
import com.calclab.hablar.basic.client.login.LoginView;
import com.calclab.hablar.basic.client.roster.RosterPageWidget;
import com.calclab.hablar.basic.client.roster.RosterView;
import com.calclab.hablar.basic.client.ui.page.Page;
import com.calclab.hablar.basic.client.ui.page.Page.Visibility;
import com.calclab.hablar.basic.client.ui.pages.Pages;
import com.calclab.hablar.basic.client.ui.pages.PagesWidget;
import com.calclab.suco.client.events.Listener;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * The main HablarWidget
 * 
 */
public class HablarWidget extends Composite implements HablarView {

    interface ChatPanelUiBinder extends UiBinder<Widget, HablarWidget> {
    }

    private static ChatPanelUiBinder uiBinder = GWT.create(ChatPanelUiBinder.class);

    @UiField
    LayoutPanel container;

    @UiField
    Label status;

    private final Pages pages;
    private Page dockedPage;

    private LoginView loginPage;

    private RosterPageWidget rosterPage;

    public HablarWidget(PagesWidget pages) {
	initWidget(uiBinder.createAndBindUi(this));
	this.pages = pages;
	Widget center = pages;
	container.add(center);
	container.setWidgetLeftWidth(center, 0, Unit.PX, 100, Unit.PCT);
	container.setWidgetTopBottom(center, 20, Unit.PX, 20, Unit.PX);
	container.forceLayout();

	pages.onStatusMessageChanged(new Listener<Page>() {
	    @Override
	    public void onEvent(Page page) {
		status.setText(page.getStatusMessage());
	    }
	});
	new HablarLogic(this);
    }

    @Override
    public LoginView getLoginPage() {
	if (this.loginPage == null) {
	    loginPage = new LoginPage(Visibility.open);
	}
	return loginPage;
    }

    @Override
    public Pages getPages() {
	return pages;
    }

    @Override
    public RosterView getRosterPage() {
	if (rosterPage == null) {
	    rosterPage = new RosterPageWidget(Visibility.closed);
	}
	return rosterPage;
    }

    @Override
    public boolean hasLogin() {
	return loginPage != null;
    }

    @Override
    public boolean hasRoster() {
	return rosterPage != null;
    }

    @Override
    public void setDocked(Page page, int size) {
	assert dockedPage == null : "Only one docked page in hablar widget is allowed.";
	this.dockedPage = page;
	Widget docked = (Widget) page;
	Widget header = (Widget) page.getHeader();

	container.add(header);
	container.setWidgetTopHeight(header, 21, PX, 23, PX);
	container.setWidgetLeftWidth(header, 0, PX, size, PX);

	container.add(docked);
	container.setWidgetTopBottom(docked, 44, Unit.PX, 20, Unit.PX);
	container.setWidgetLeftWidth(docked, 0, PX, size, PX);

	container.setWidgetLeftRight((Widget) pages, size + 3, PX, 0, PX);
	container.animate(250);
    }

}
