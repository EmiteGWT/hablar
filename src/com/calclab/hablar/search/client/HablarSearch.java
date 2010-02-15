package com.calclab.hablar.search.client;

import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.xep.search.client.SearchManager;
import com.calclab.hablar.core.client.Hablar;
import com.calclab.hablar.core.client.HablarWidget;
import com.calclab.hablar.core.client.container.PageAddedEvent;
import com.calclab.hablar.core.client.container.PageAddedHandler;
import com.calclab.hablar.core.client.page.PagePresenter.Visibility;
import com.calclab.hablar.core.client.ui.icon.HablarIcons;
import com.calclab.hablar.core.client.ui.icon.HablarIcons.IconType;
import com.calclab.hablar.core.client.ui.menu.SimpleAction;
import com.calclab.hablar.roster.client.page.RosterPage;
import com.calclab.hablar.roster.client.page.RosterPresenter;
import com.calclab.suco.client.Suco;
import com.google.gwt.core.client.EntryPoint;

/**
 * Adds Search support to Hablar
 * 
 */
public class HablarSearch implements EntryPoint {

    protected static final String ACTION_ID = "HablarLogic-searchAction";

    public static void install(final Hablar hablar, final SearchConfig config) {
	final SearchManager searchManager = Suco.get(SearchManager.class);
	searchManager.setHost(XmppURI.uri(null, config.searchService, null));
	final Visibility visible = config.searchOnRoster ? Visibility.hidden : Visibility.notFocused;
	final SearchPage searchPage = new SearchPage(visible, config.searchCloseable, hablar.getEventBus(),
		new SearchWidget());
	hablar.addPage(searchPage);
	new SearchBasicActions(searchPage);

	if (config.searchOnRoster) {
	    addSearchActionToRoster(hablar, searchPage);
	}

    }

    public static void install(final HablarWidget widget) {
	install(widget.getHablar(), SearchConfig.getFromMeta());
    }

    private static void addSearchActionToRoster(final Hablar hablar, final SearchPage searchPage) {
	hablar.addPageAddedHandler(new PageAddedHandler() {
	    @Override
	    public void onPageAdded(final PageAddedEvent event) {
		final RosterPage rosterPage = RosterPresenter.asRoster(event.getPage());
		if (rosterPage != null) {
		    final String name = "Open Search Page";
		    final String icon = HablarIcons.get(IconType.search);
		    rosterPage.addAction(new SimpleAction<RosterPage>(name, ACTION_ID, icon) {
			@Override
			public void execute(final RosterPage page) {
			    searchPage.requestVisibility(Visibility.focused);
			}
		    });
		}
	    }
	}, true);
    }

    @Override
    public void onModuleLoad() {
    }

}
