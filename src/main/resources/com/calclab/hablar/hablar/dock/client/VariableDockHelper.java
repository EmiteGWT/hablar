package com.calclab.hablar.dock.client;

import static com.google.gwt.dom.client.Style.Unit.PX;

import com.calclab.hablar.core.client.HablarDisplay;
import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.pages.HeaderDisplay;
import com.google.gwt.user.client.ui.LayoutPanel;

public class VariableDockHelper {

    public HeaderDisplay createHeaderWidget(final Page<?> page, final String position) {
	final DockHeaderWidget headerWidget = new DockHeaderWidget(page.getId(), position);
	return headerWidget;
    }

    public void slideDown(final HablarDisplay display, final LayoutPanel panel, final int headerSize) {
	// show the second panel (content)...
	panel.getWidget(1).setVisible(true);
	display.setWidgetTopBottom(panel, 0, PX, headerSize, PX);
	display.forceLayout();
	display.setWidgetTopBottom(panel, 0, PX, 0, PX);
	display.animate(500);
    }

    public void slideUp(final HablarDisplay display, final LayoutPanel panel, final int headerSize) {
	display.setWidgetTopBottom(panel, 0, PX, 0, PX);
	display.forceLayout();
	display.setWidgetTopHeight(panel, 0, PX, headerSize, PX);
	display.animate(500);
    }

}
