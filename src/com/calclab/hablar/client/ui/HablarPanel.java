package com.calclab.hablar.client.ui;

import com.calclab.hablar.client.HablarConfig;
import com.calclab.hablar.client.pages.PagesWidget;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.Widget;

public class HablarPanel extends Composite {

    interface ChatPanelUiBinder extends UiBinder<Widget, HablarPanel> {
    }

    private static ChatPanelUiBinder uiBinder = GWT.create(ChatPanelUiBinder.class);

    @UiField
    LayoutPanel panel;

    public HablarPanel(HablarConfig config, PagesWidget pages) {
	initWidget(uiBinder.createAndBindUi(this));
	panel.add(pages);
	panel.setWidgetLeftWidth(pages, 0, Unit.PX, 100, Unit.PCT);
	panel.setWidgetTopHeight(pages, 0, Unit.PX, 100, Unit.PCT);
	panel.forceLayout();
    }

}
