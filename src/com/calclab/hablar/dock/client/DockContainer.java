package com.calclab.hablar.dock.client;

import static com.google.gwt.dom.client.Style.Unit.PX;

import java.util.HashMap;

import com.calclab.hablar.core.client.container.PagesContainer;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.page.PagePresenter.Visibility;
import com.calclab.hablar.core.client.page.events.VisibilityChangeRequestEvent;
import com.calclab.hablar.core.client.page.events.VisibilityChangeRequestHandler;
import com.calclab.hablar.core.client.pages.HeaderDisplay;
import com.calclab.hablar.core.client.pages.HeaderPresenter;
import com.calclab.hablar.dock.client.DockConfig.Dock;
import com.calclab.hablar.dock.client.DockConfig.Position;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.Widget;

public class DockContainer implements PagesContainer {
    public static final String ROL = "RosterDock";
    private static final double HEADER_SIZE = 24;
    private final HashMap<Position, LayoutPanel> panels;
    private final HashMap<Position, Page<?>> pages;
    private final DockConfig config;
    private final DockLayout layout;

    public DockContainer(HablarEventBus eventBus, DockConfig config, DockLayout layout) {
	this.config = config;
	this.layout = layout;
	panels = new HashMap<Position, LayoutPanel>();
	pages = new HashMap<Position, Page<?>>();

	layoutCenter();

	for (Position pos : DockConfig.Position.values()) {
	    addDock(pos, config.get(pos));
	}

	eventBus.addHandler(VisibilityChangeRequestEvent.TYPE, new VisibilityChangeRequestHandler() {
	    @Override
	    public void onVisibilityChangeRequest(VisibilityChangeRequestEvent event) {
		Page<?> page = event.getPage();
		if (pages.containsValue(page)) {
		    changeVisibility(page, event.getNewVisibility());
		}
	    }
	});
    }

    @Override
    public boolean add(Page<?> page) {
	for (Position position : DockConfig.Position.values()) {
	    if (page.getType().equals(config.get(position).pageType)) {
		pages.put(position, page);
		setPage(panels.get(position), page, position);
		if (position == Position.top || position == Position.bottom) {
		    page.setVisibility(Visibility.notFocused);
		} else {
		    page.setVisibility(Visibility.focused);
		}
		return true;
	    }
	}
	return false;
    }

    @Override
    public String getRol() {
	return ROL;
    }

    @Override
    public Widget getWidget() {
	return null;
    }

    private void addDock(Position position, Dock dock) {
	LayoutPanel panel = layout.addNewPanel("hablar-RosterDockContainer-" + position);
	panels.put(position, panel);

	panel.addStyleName("hablar-RosterDockContainer-" + position);

	Dock top = config.get(Position.top);
	Dock bottom = config.get(Position.bottom);

	layout.layoutPanel(panel, position, dock, top, bottom);
    }

    private void hideTopPage(Page<?> page) {
	Dock dock = config.get(Position.top);
	LayoutPanel panel = panels.get(Position.top);
	layout.slideUp(panel, dock);
	page.setVisibility(Visibility.notFocused);
    }

    private void layoutCenter() {
	Dock top = config.get(Position.top);
	Dock bottom = config.get(Position.bottom);
	Dock left = config.get(Position.left);
	Dock right = config.get(Position.right);
	layout.setCenterPosition(left, top, right, bottom);
    }

    private void setPage(LayoutPanel panel, Page<?> page, Position position) {
	Widget widget = page.getDisplay().asWidget();
	HeaderDisplay headerDisplay = layout.createHeaderWidget(page, position);
	new HeaderPresenter(page, headerDisplay);
	Widget headerWidget = headerDisplay.asWidget();
	panel.add(headerWidget);
	panel.setWidgetTopHeight(headerWidget, 0, PX, HEADER_SIZE, PX);
	panel.setWidgetLeftRight(headerWidget, 0, PX, 0, PX);
	panel.add(widget);
	panel.setWidgetTopBottom(widget, HEADER_SIZE, PX, 0, PX);
	panel.setWidgetLeftRight(widget, 0, PX, 0, PX);
    }

    private void showTopPage(Page<?> page) {
	Dock dock = config.get(Position.top);
	LayoutPanel panel = panels.get(Position.top);
	layout.slideDown(panel, dock);
	page.setVisibility(Visibility.focused);
    }

    protected void changeVisibility(Page<?> page, Visibility newVisibility) {
	if (newVisibility == Visibility.toggle && page == pages.get(Position.top)) {
	    if (page.getVisibility() == Visibility.notFocused) {
		showTopPage(page);
	    } else if (page.getVisibility() == Visibility.focused) {
		hideTopPage(page);
	    }
	}
    }

}
