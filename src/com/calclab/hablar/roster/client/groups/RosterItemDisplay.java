package com.calclab.hablar.roster.client.groups;

import com.calclab.hablar.core.client.mvp.Display;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasText;

public interface RosterItemDisplay extends Display {

	void addStyleName(String styleName);

	HasClickHandlers getAction();

	HasText getJid();

	HasClickHandlers getMenuAction();

	HasText getName();

	HasText getStatus();

	void setIcon(String icon);

	void setMenuVisible(boolean visible);

	void setStatusVisible(boolean visible);

	void setWidgetTitle(String title);

	/**
	 * Triggers the display to force a re-layout of any LayoutPanels contained
	 * within it. This is in order to provide a fix for Issue #333.
	 */
	void forceLayout();
}
