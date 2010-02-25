package com.calclab.hablar.core.client.pages.tabs;

import java.util.HashMap;

import com.calclab.emite.core.client.packet.TextUtils;
import com.calclab.hablar.chat.client.ui.ChatPresenter;
import com.calclab.hablar.core.client.mvp.Presenter;
import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.page.PagePresenter.Visibility;
import com.calclab.hablar.core.client.ui.menu.Action;
import com.calclab.hablar.core.client.ui.menu.Menu;
import com.calclab.hablar.core.client.ui.menu.SimpleAction;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class TabsMenuPresenter implements Presenter<TabsMenuDisplay> {

    public static final String TABS_MENU_PAGE_MENU_ITEM_ID_PREF = "TabsMenuPage-MenuItem-";
    private final Menu<TabsMenuPresenter> tabsMenu;
    private final TabsMenuDisplay display;
    private final HashMap<String, Action<TabsMenuPresenter>> items;

    public TabsMenuPresenter(final TabsMenuDisplay display, final TabsLayout tabsLayout) {
	this.display = display;
	items = new HashMap<String, Action<TabsMenuPresenter>>();
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

    public void add(final Page<?> page) {
	if (items.get(page.getId()) == null) {
	    display.setVisible(true);
	    final String title = TextUtils.ellipsis(page.getState().getPageTitle(), 20);
	    final String icon = page.getState().getPageIcon();
	    final String actionId = TABS_MENU_PAGE_MENU_ITEM_ID_PREF
		    + ChatPresenter.createId(page.getState().getPageTitle());
	    final SimpleAction<TabsMenuPresenter> newAction = new SimpleAction<TabsMenuPresenter>(title, actionId, icon) {
		@Override
		public void execute(final TabsMenuPresenter target) {
		    page.requestVisibility(Visibility.focused);
		}
	    };
	    tabsMenu.addAction(newAction);
	    items.put(page.getId(), newAction);
	}
    }

    @Override
    public TabsMenuDisplay getDisplay() {
	return display;
    }

    public void remove(final Page<?> page) {
	final Action<TabsMenuPresenter> action = items.remove(page.getId());
	tabsMenu.removeAction(action);
	display.setVisible(items.size() > 0);
    }

}
