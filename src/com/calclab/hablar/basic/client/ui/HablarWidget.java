package com.calclab.hablar.basic.client.ui;

import static com.google.gwt.dom.client.Style.Unit.PCT;
import static com.google.gwt.dom.client.Style.Unit.PX;

import com.calclab.hablar.basic.client.DefaultEventBus;
import com.calclab.hablar.basic.client.Hablar;
import com.calclab.hablar.basic.client.HablarEventBus;
import com.calclab.hablar.basic.client.ui.page.HeaderStyles;
import com.calclab.hablar.basic.client.ui.page.PageHeader;
import com.calclab.hablar.basic.client.ui.page.PageView;
import com.calclab.hablar.basic.client.ui.page.PageView.Visibility;
import com.calclab.hablar.basic.client.ui.page.events.UserMessageEvent;
import com.calclab.hablar.basic.client.ui.page.events.UserMessageHandler;
import com.calclab.hablar.basic.client.ui.pages.Pages;
import com.calclab.hablar.basic.client.ui.pages.PagesPanel;
import com.calclab.hablar.basic.client.ui.pages.PagesWidget;
import com.calclab.hablar.basic.client.ui.pages.panel.AccordionPages;
import com.calclab.hablar.basic.client.ui.pages.panel.TabPages;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * The main HablarWidget
 * 
 */
public class HablarWidget extends Composite implements Hablar {

    public static enum Layout {
	accordion, tabs
    }

    interface ChatPanelUiBinder extends UiBinder<Widget, HablarWidget> {
    }

    interface HeaderStyle extends HeaderStyles {

    }

    private static final int ANIMATION_TIME = 250;

    private static ChatPanelUiBinder uiBinder = GWT.create(ChatPanelUiBinder.class);

    @UiField
    LayoutPanel container;

    @UiField
    Label status;

    @UiField
    HeaderStyle headerStyle;

    private final Pages pages;
    private PageView dockedPageView;

    private SimplePanel overlay;

    private final DefaultEventBus eventBus;

    @UiConstructor
    public HablarWidget(HablarWidget.Layout layout) {
	this(layout == HablarWidget.Layout.accordion ? new AccordionPages() : new TabPages());
    }

    private HablarWidget(PagesPanel panel) {
	this.eventBus = new DefaultEventBus();
	this.pages = new PagesWidget(eventBus, panel);
	initWidget(uiBinder.createAndBindUi(this));
	Widget center = (Widget) pages;
	container.add(center);
	container.setWidgetLeftWidth(center, 0, Unit.PX, 100, Unit.PCT);
	container.setWidgetTopBottom(center, 20, Unit.PX, 20, Unit.PX);
	container.forceLayout();

	eventBus.addHandler(UserMessageEvent.TYPE, new UserMessageHandler() {
	    @Override
	    public void onUserMessage(UserMessageEvent event) {
		status.setText(event.getMessage());
	    }
	});

	new HablarLogic(this);
    }

    /**
     * FIXME Problem with animation
     * http://code.google.com/p/google-web-toolkit/issues/detail?id=4360
     */
    @Override
    public void closeOverlay() {
	getOverlay();
	container.setWidgetTopHeight(overlay, 0, PX, 0, PCT);
	container.forceLayout();
	// container.animate(ANIMATION_TIME);
	overlay.clear();
    }

    public HablarEventBus getHablarEventBus() {
	return eventBus;
    }

    @Override
    public Pages getPages() {
	return pages;
    }

    @Override
    public void setDocked(PageView pageView, int size) {
	assert dockedPageView == null : "Only one docked page in hablar widget is allowed.";
	this.dockedPageView = pageView;

	PageHeader pageHeader = pageView.getHeader();
	pageHeader.setStyles(headerStyle);
	pageView.setVisibility(Visibility.focused);

	Widget docked = (Widget) pageView;
	Widget header = (Widget) pageHeader;

	container.add(header);
	container.setWidgetTopHeight(header, 20, PX, 24, PX);
	container.setWidgetLeftWidth(header, 0, PX, size, PX);

	container.add(docked);
	container.setWidgetTopBottom(docked, 44, Unit.PX, 20, Unit.PX);
	container.setWidgetLeftWidth(docked, 0, PX, size, PX);

	container.setWidgetLeftRight((Widget) pages, size + 3, PX, 0, PX);
	container.animate(ANIMATION_TIME);
    }

    @Override
    public void showOverlay(Display display) {
	getOverlay();
	overlay.clear();
	overlay.setWidget(display.asWidget());
	container.setWidgetTopHeight(overlay, 0, PX, 100, PCT);
	container.forceLayout();
    }

    private SimplePanel getOverlay() {
	if (overlay == null) {
	    overlay = new SimplePanel();
	    overlay.setStyleName("hablar-Overlay");
	    container.add(overlay);
	    container.setWidgetTopHeight(overlay, 0, PX, 0, PX);
	    container.setWidgetLeftRight(overlay, 0, PX, 0, PX);
	    container.forceLayout();
	}
	return overlay;
    }

}
