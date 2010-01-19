package com.calclab.hablar.client.ui;

import static com.google.gwt.dom.client.Style.Unit.PX;

import com.calclab.hablar.client.HablarConfig;
import com.calclab.hablar.client.HablarWidget;
import com.calclab.hablar.client.roster.RosterPageWidget;
import com.calclab.hablar.client.ui.page.Page;
import com.calclab.hablar.client.ui.pages.PagesWidget;
import com.calclab.suco.client.events.Listener;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.SplitLayoutPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * WARNING: You should call init to initialize
 * 
 * Usually you dont use this class (use HablarWidget instead)
 * 
 * @see HablarWidget
 */
public class HablarPanel extends Composite implements HablarView {

    interface ChatPanelUiBinder extends UiBinder<Widget, HablarPanel> {
    }

    private static ChatPanelUiBinder uiBinder = GWT.create(ChatPanelUiBinder.class);

    @UiField
    LayoutPanel panel;

    @UiField
    Label status;

    private final Widget pages;
    private Page docked;

    public HablarPanel(HablarConfig config, PagesWidget pages) {
	initWidget(uiBinder.createAndBindUi(this));
	this.pages = pages;
	pages.onStatusMessageChanged(new Listener<Page>() {
	    @Override
	    public void onEvent(Page page) {
		status.setText(page.getStatusMessage());
	    }
	});
    }

    @Override
    public void addDocked(RosterPageWidget rosterPageWidget) {
	this.docked = rosterPageWidget;
    }

    public void init() {
	Widget center;

	if (docked == null) {
	    center = pages;
	} else {
	    center = createSplitPanel();
	}

	panel.add(center);
	panel.setWidgetLeftWidth(center, 0, Unit.PX, 100, Unit.PCT);
	panel.setWidgetTopBottom(center, 20, Unit.PX, 20, Unit.PX);
	panel.forceLayout();
    }

    private SplitLayoutPanel createSplitPanel() {
	SplitLayoutPanel split = new SplitLayoutPanel();
	DockLayoutPanel dock = new DockLayoutPanel(PX);
	dock.addNorth((Widget) docked.getHeader(), 23);
	dock.add((Widget) docked);
	split.addWest(dock, 200);
	split.add(pages);
	return split;
    }

}
