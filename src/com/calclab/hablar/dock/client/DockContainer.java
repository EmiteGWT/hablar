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

/**
 * A dock pages container
 */
public class DockContainer implements PagesContainer {
    public static final String ROL = "RosterDock";
    private static final double HEADER_SIZE = 24;
    private final HashMap<Position, LayoutPanel> panels;
    private final HashMap<Position, Page<?>> pages;
    private final DockConfig config;
    private final DockLayout layout;

    public DockContainer(final HablarEventBus eventBus, final DockConfig config, final DockLayout layout) {
	this.config = config;
	this.layout = layout;
	panels = new HashMap<Position, LayoutPanel>();
	pages = new HashMap<Position, Page<?>>();

	layoutCenter();

	for (final Position pos : DockConfig.Position.values()) {
	    addDock(pos, config.get(pos));
	}

	eventBus.addHandler(VisibilityChangeRequestEvent.TYPE, new VisibilityChangeRequestHandler() {
	    @Override
	    public void onVisibilityChangeRequest(final VisibilityChangeRequestEvent event) {
		final Page<?> page = event.getPage();
		if (pages.containsValue(page)) {
		    changeVisibility(page, event.getNewVisibility());
		}
	    }
	});
    }

    @Override
    public boolean add(final Page<?> page) {
	for (final Position position : DockConfig.Position.values()) {
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

    private void addDock(final Position position, final Dock dock) {
	final LayoutPanel panel = layout.addNewPanel("hablar-DockContainer-" + position);
	panels.put(position, panel);

	panel.addStyleName("hablar-DockContainer-" + position);

	final Dock top = config.get(Position.top);
	final Dock bottom = config.get(Position.bottom);

	layout.layoutPanel(panel, position, dock, top, bottom);
    }

    private void hideTopPage(final Page<?> page) {
	final Dock dock = config.get(Position.top);
	final LayoutPanel panel = panels.get(Position.top);
	layout.slideUp(panel, dock);
	// TODO: IE z-index problem
	panels.get(Position.right).setVisible(true);
	panels.get(Position.left).setVisible(true);

	page.setVisibility(Visibility.notFocused);
    }

    private void layoutCenter() {
	final Dock top = config.get(Position.top);
	final Dock bottom = config.get(Position.bottom);
	final Dock left = config.get(Position.left);
	final Dock right = config.get(Position.right);
	layout.setCenterPosition(left, top, right, bottom);
    }

    private void setPage(final LayoutPanel panel, final Page<?> page, final Position position) {
	final Widget widget = page.getDisplay().asWidget();
	final HeaderDisplay headerDisplay = layout.createHeaderWidget(page, position);
	new HeaderPresenter(page, headerDisplay);
	final Widget headerWidget = headerDisplay.asWidget();
	panel.add(headerWidget);
	panel.setWidgetTopHeight(headerWidget, 0, PX, HEADER_SIZE, PX);
	panel.setWidgetLeftRight(headerWidget, 0, PX, 0, PX);
	panel.add(widget);
	panel.setWidgetTopBottom(widget, HEADER_SIZE, PX, 0, PX);
	panel.setWidgetLeftRight(widget, 0, PX, 0, PX);
    }

    private void showTopPage(final Page<?> page) {
	final Dock dock = config.get(Position.top);
	final LayoutPanel panel = panels.get(Position.top);

	// TODO: IE z-index problem
	panels.get(Position.right).setVisible(false);
	panels.get(Position.left).setVisible(false);
	layout.slideDown(panel, dock);
	page.setVisibility(Visibility.focused);
    }

    protected void changeVisibility(final Page<?> page, final Visibility newVisibility) {
	if (page == (Page<?>) pages.get(Position.top)) {
	    if (newVisibility == Visibility.toggle && page.getVisibility() == Visibility.notFocused) {
		showTopPage(page);
	    } else if (newVisibility == Visibility.hidden || newVisibility == Visibility.notFocused) {
		hideTopPage(page);
	    }
	}
    }

}
