package com.calclab.hablar.dock.client;

import static com.google.gwt.dom.client.Style.Unit.PX;

import com.calclab.hablar.core.client.HablarDisplay;
import com.calclab.hablar.core.client.container.PagesContainer;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.page.PagePresenter.Visibility;
import com.calclab.hablar.core.client.page.events.VisibilityChangeRequestEvent;
import com.calclab.hablar.core.client.page.events.VisibilityChangeRequestHandler;
import com.calclab.hablar.core.client.pages.HeaderDisplay;
import com.calclab.hablar.core.client.pages.HeaderPresenter;
import com.calclab.hablar.roster.client.page.RosterPage;
import com.calclab.hablar.user.client.UserPage;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.SplitLayoutPanel;
import com.google.gwt.user.client.ui.Widget;

public class VariableDockContainer implements PagesContainer {
    private final SplitLayoutPanel split;
    private final LayoutPanel rosterPanel;
    private final LayoutPanel userPanel;

    private final HablarEventBus eventBus;
    private final VariableDockHelper helper;
    private final HablarDisplay display;
    private final int headerSize;

    public VariableDockContainer(final HablarEventBus eventBus, final DockConfig config, final PagesContainer main,
	    final HablarDisplay display) {
	this.eventBus = eventBus;
	this.display = display;
	this.helper = new VariableDockHelper();

	this.headerSize = config.headerSize;
	final Widget mainWidget = main.getWidget();
	mainWidget.removeFromParent();
	this.split = new SplitLayoutPanel();
	display.add(split);
	display.setWidgetLeftRight(split, 0, Unit.PX, 0, Unit.PX);
	display.setWidgetTopBottom(split, headerSize, Unit.PX, 3, Unit.PX);

	this.rosterPanel = new LayoutPanel();
	if ("left".equals(config.rosterDock)) {
	    split.addWest(rosterPanel, config.rosterWidth);
	} else {
	    split.addEast(rosterPanel, config.rosterWidth);
	}

	split.add(mainWidget);
	this.userPanel = new LayoutPanel();
	this.userPanel.addStyleName("VaribleDockContainer-top");
	display.add(userPanel);
	display.setWidgetLeftRight(userPanel, 0, Unit.PX, 0, Unit.PX);
	display.setWidgetTopHeight(userPanel, 0, Unit.PX, 24, Unit.PX);

	eventBus.addHandler(VisibilityChangeRequestEvent.TYPE, new VisibilityChangeRequestHandler() {
	    @Override
	    public void onVisibilityChangeRequest(final VisibilityChangeRequestEvent event) {
		final Page<?> page = event.getPage();
		if (page.getType().equals(UserPage.TYPE)) {
		    changeVisibility(page, event.getNewVisibility());
		}
	    }
	});
    }

    @Override
    public boolean add(final Page<?> page) {
	final String pageType = page.getType();
	if (pageType.equals(RosterPage.TYPE)) {
	    rosterPanel.clear();
	    setPage(rosterPanel, page, "roster-dock");
	} else if (pageType.equals(UserPage.TYPE)) {
	    userPanel.clear();
	    setPage(userPanel, page, "user-dock");
	    hideTopPage(page);
	} else {
	    return false;
	}
	return true;
    }

    protected void changeVisibility(final Page<?> page, final Visibility newVisibility) {
	if ((newVisibility == Visibility.toggle) && (page.getVisibility() == Visibility.notFocused)) {
	    showTopPage(page);
	} else if ((newVisibility == Visibility.toggle) && (page.getVisibility() == Visibility.focused)) {
	    hideTopPage(page);
	} else if ((newVisibility == Visibility.hidden) || (newVisibility == Visibility.notFocused)) {
	    hideTopPage(page);
	}
    }

    @Override
    public String getRol() {
	return "VariableDock";
    }

    @Override
    public Widget getWidget() {
	return null;
    }

    private void hideTopPage(final Page<?> page) {
	helper.slideUp(display, userPanel, headerSize);
	page.setVisibility(Visibility.notFocused);
    }

    private void setPage(final LayoutPanel panel, final Page<?> page, final String position) {
	final Widget widget = page.getDisplay().asWidget();
	final HeaderDisplay headerDisplay = helper.createHeaderWidget(page, position);
	new HeaderPresenter(eventBus, page, headerDisplay);
	final Widget headerWidget = headerDisplay.asWidget();
	panel.add(headerWidget);
	panel.setWidgetTopHeight(headerWidget, 0, PX, headerSize, PX);
	panel.setWidgetLeftRight(headerWidget, 0, PX, 0, PX);
	panel.add(widget);
	panel.setWidgetTopBottom(widget, headerSize, PX, 0, PX);
	panel.setWidgetLeftRight(widget, 0, PX, 0, PX);
    }

    private void showTopPage(final Page<?> page) {
	helper.slideDown(display, userPanel, headerSize);
	page.setVisibility(Visibility.focused);
    }

}
