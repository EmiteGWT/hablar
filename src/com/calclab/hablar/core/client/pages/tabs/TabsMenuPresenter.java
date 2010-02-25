package com.calclab.hablar.core.client.pages.tabs;

import java.util.HashMap;

import com.calclab.emite.core.client.packet.TextUtils;
import com.calclab.hablar.chat.client.ui.ChatPresenter;
import com.calclab.hablar.core.client.mvp.Presenter;
import com.calclab.hablar.core.client.ui.menu.Action;
import com.calclab.hablar.core.client.ui.menu.Menu;
import com.calclab.hablar.core.client.ui.menu.SimpleAction;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Widget;

public class TabsMenuPresenter implements Presenter<TabsMenuDisplay> {

    public static final String TABS_MENU_PAGE_MENU_ITEM_ID_PREF = "TabsMenuPage-MenuItem-";
    private final Menu<TabsMenuPresenter> tabsMenu;
    private final TabsMenuDisplay display;
    private final HashMap<Widget, Action<TabsMenuPresenter>> items;
    private final TabsLayout tabsLayout;

    public TabsMenuPresenter(final TabsMenuDisplay display, final TabsLayout tabsLayout) {
	this.display = display;
	this.tabsLayout = tabsLayout;
	this.items = new HashMap<Widget, Action<TabsMenuPresenter>>();
	tabsMenu = new Menu<TabsMenuPresenter>(display.newTabsMenu("hablar-tabsChatMenu"));
	display.setVisible(false);
	final ClickHandler handler = new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		event.preventDefault();
		final Element element = event.getRelativeElement();
		tabsMenu.setTarget(TabsMenuPresenter.this);
		tabsMenu.show(element.getAbsoluteLeft(), element.getAbsoluteTop());
	    }
	};
	display.getMenu().addClickHandler(handler);
    }

    public void add(final Widget pageWidget, final Widget headWidget) {
	display.setVisible(true);
	final String title = headWidget.getElement().getInnerText();
	final SimpleAction<TabsMenuPresenter> newAction = new SimpleAction<TabsMenuPresenter>(TextUtils.ellipsis(title,
		20), TABS_MENU_PAGE_MENU_ITEM_ID_PREF + ChatPresenter.createId(title)) {
	    @Override
	    public void execute(final TabsMenuPresenter target) {
		tabsLayout.focus(pageWidget);
	    }
	};
	tabsMenu.addAction(newAction);
	items.put(pageWidget, newAction);
    }

    @Override
    public TabsMenuDisplay getDisplay() {
	return display;
    }

    public void remove(final Widget pageWidget) {
	tabsMenu.removeAction(items.get(pageWidget));
	items.remove(pageWidget);
	if (items.size() < 1) {
	    display.setVisible(false);
	}
    }

}
