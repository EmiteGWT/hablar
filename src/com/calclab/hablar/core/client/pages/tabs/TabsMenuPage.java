package com.calclab.hablar.core.client.pages.tabs;

import java.util.HashMap;

import com.calclab.emite.core.client.packet.TextUtils;
import com.calclab.hablar.chat.client.ui.ChatPresenter;
import com.calclab.hablar.core.client.ui.menu.Action;
import com.calclab.hablar.core.client.ui.menu.Menu;
import com.calclab.hablar.core.client.ui.menu.SimpleAction;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Widget;

public class TabsMenuPage {

    public static final String TABS_MENU_PAGE_MENU_ITEM_ID_PREF = "TabsMenuPage-MenuItem-";
    private final Menu<TabsMenuPage> tabsMenu;
    private final TabsMenuDisplay display;
    private final HashMap<Widget, Action<TabsMenuPage>> items;
    private final TabsLayout tabsLayout;

    public TabsMenuPage(final TabsMenuDisplay display, final TabsLayout tabsLayout) {
	this.display = display;
	this.tabsLayout = tabsLayout;
	this.items = new HashMap<Widget, Action<TabsMenuPage>>();
	tabsMenu = new Menu<TabsMenuPage>(display.newTabsMenu("hablar-tabsChatMenu"));
	display.setVisible(false);
	final ClickHandler handler = new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		event.preventDefault();
		final Element element = event.getRelativeElement();
		tabsMenu.setTarget(TabsMenuPage.this);
		tabsMenu.show(element.getAbsoluteLeft(), element.getAbsoluteTop());
	    }
	};
	display.getMenu().addClickHandler(handler);
    }

    public void add(final Widget pageWidget, final Widget headWidget) {
	display.setVisible(true);
	final String title = headWidget.getElement().getInnerText();
	final SimpleAction<TabsMenuPage> newAction = new SimpleAction<TabsMenuPage>(TextUtils.ellipsis(title, 20),
		TABS_MENU_PAGE_MENU_ITEM_ID_PREF + ChatPresenter.createId(title)) {
	    @Override
	    public void execute(final TabsMenuPage target) {
		tabsLayout.focus(pageWidget);
	    }
	};
	tabsMenu.addAction(newAction);
	items.put(pageWidget, newAction);
    }

    public void remove(final Widget pageWidget) {
	tabsMenu.removeAction(items.get(pageWidget));
	items.remove(pageWidget);
	if (items.size() < 1) {
	    display.setVisible(false);
	}
    }

}
