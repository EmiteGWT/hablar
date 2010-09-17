package com.calclab.hablar.search.client;

import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.im.client.roster.XmppRoster;
import com.calclab.emite.im.client.roster.events.RosterRetrievedEvent;
import com.calclab.emite.im.client.roster.events.RosterRetrievedHandler;
import com.calclab.emite.xep.search.client.SearchManager;
import com.calclab.hablar.core.client.Hablar;
import com.calclab.hablar.core.client.HablarWidget;
import com.calclab.hablar.core.client.container.PageAddedEvent;
import com.calclab.hablar.core.client.container.PageAddedHandler;
import com.calclab.hablar.core.client.page.PagePresenter.Visibility;
import com.calclab.hablar.core.client.ui.icon.Icons;
import com.calclab.hablar.core.client.ui.menu.SimpleAction;
import com.calclab.hablar.roster.client.page.RosterPage;
import com.calclab.hablar.roster.client.page.RosterPresenter;
import com.calclab.hablar.search.client.page.SearchPage;
import com.calclab.hablar.search.client.page.SearchWidget;
import com.calclab.suco.client.Suco;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

/**
 * Adds Search support to Hablar
 *
 */
public class HablarSearch implements EntryPoint {

    protected static final String ACTION_ID = "HablarLogic-searchAction";
    private static SearchMessages searchMessages;

    public static SearchMessages i18n() {
	return searchMessages;
    }

    public static void install(final Hablar hablar, final SearchConfig config) {
	final SearchManager searchManager = Suco.get(SearchManager.class);
	searchManager.setHost(XmppURI.uri(null, config.searchService, null));
	final Visibility visible = config.searchOnRoster ? Visibility.hidden : Visibility.notFocused;
	final SearchPage searchPage = new SearchPage(visible, config.searchCloseable, config.queryFactory, hablar
		.getEventBus(), new SearchWidget());
	hablar.addPage(searchPage);
	new SearchBasicActions(searchPage);

	if (config.searchOnRoster) {
	    addSearchActionToRoster(hablar, searchPage);
	}
	if (config.showSearchPageOnEmptyRoster) {
		XmppRoster roster = Suco.get(XmppRoster.class); // Cannot make it work with ImGinjector, strange.
		roster.addRosterRetrievedHandler(new RosterRetrievedHandler() {

			@Override
			public void onRosterRetrieved(RosterRetrievedEvent event) {
				if (event.getRosterItems().isEmpty()) {
					searchPage.requestVisibility(Visibility.focused);
				}
			}
		});
	}
    }

    public static void install(final HablarWidget widget) {
	install(widget.getHablar(), SearchConfig.getFromMeta());
    }

    public static void setMessages(final SearchMessages messages) {
	searchMessages = messages;
    }

    private static void addSearchActionToRoster(final Hablar hablar, final SearchPage searchPage) {
	hablar.addPageAddedHandler(new PageAddedHandler() {
	    @Override
	    public void onPageAdded(final PageAddedEvent event) {
		final RosterPage rosterPage = RosterPresenter.asRoster(event.getPage());
		if (rosterPage != null) {
		    final String name = i18n().openSearchPage();
		    final String icon = Icons.SEARCH;
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
	final SearchMessages messages = (SearchMessages) GWT.create(SearchMessages.class);
	setMessages(messages);
	SearchWidget.setMessages(messages);
    }

}
