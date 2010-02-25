package com.calclab.hablar.core.client.pages.tabs;

import com.calclab.hablar.core.client.ui.icon.HablarIcons;
import com.calclab.hablar.core.client.ui.icon.HablarIcons.IconType;
import com.calclab.hablar.core.client.ui.menu.MenuDisplay;
import com.calclab.hablar.core.client.ui.menu.PopupMenu;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class TabsMenuWidget extends Composite implements TabsMenuDisplay {

    interface TabsMenuWidgetUiBinder extends UiBinder<Widget, TabsMenuWidget> {
    }

    private static TabsMenuWidgetUiBinder uiBinder = GWT.create(TabsMenuWidgetUiBinder.class);

    @UiField
    Label menu;

    public TabsMenuWidget() {
	initWidget(uiBinder.createAndBindUi(this));
	menu.addStyleName(HablarIcons.get(IconType.menu));
    }

    @Override
    public Widget asWidget() {
	return this;
    }

    @Override
    public HasClickHandlers getMenu() {
	return menu;
    }

    @Override
    public MenuDisplay<TabsMenuPage> newTabsMenu(final String menuId) {
	return new PopupMenu<TabsMenuPage>(menuId);
    }
}
