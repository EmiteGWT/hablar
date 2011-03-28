package com.calclab.hablar.core.client.pages.tabs;

import com.calclab.hablar.core.client.ui.icon.Icons;
import com.calclab.hablar.core.client.ui.menu.MenuDisplay;
import com.calclab.hablar.core.client.ui.menu.PopupMenu;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

public class TabsMenuWidget extends Composite implements TabsMenuDisplay {

    interface TabsMenuWidgetUiBinder extends UiBinder<Widget, TabsMenuWidget> {
    }

    private static TabsMenuWidgetUiBinder uiBinder = GWT.create(TabsMenuWidgetUiBinder.class);

    @UiField
    Image menu;

    public TabsMenuWidget() {
	initWidget(uiBinder.createAndBindUi(this));
	Icons.set(menu, Icons.MENU);
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
    public MenuDisplay<TabsMenuPresenter> newTabsMenu(final String menuId) {
	return new PopupMenu<TabsMenuPresenter>(menuId);
    }
}
