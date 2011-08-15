package com.calclab.hablar.search.client;

import com.calclab.emite.core.client.xmpp.session.XmppSession;
import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.im.client.chat.ChatManager;
import com.calclab.emite.im.client.roster.XmppRoster;
import com.calclab.emite.im.client.roster.events.RosterRetrievedEvent;
import com.calclab.emite.im.client.roster.events.RosterRetrievedHandler;
import com.calclab.emite.xep.search.client.SearchManager;
import com.calclab.hablar.core.client.Hablar;
import com.calclab.hablar.core.client.container.PageAddedEvent;
import com.calclab.hablar.core.client.container.PageAddedHandler;
import com.calclab.hablar.core.client.page.PagePresenter.Visibility;
import com.calclab.hablar.core.client.ui.menu.SimpleAction;
import com.calclab.hablar.icons.client.IconsBundle;
import com.calclab.hablar.roster.client.page.RosterPage;
import com.calclab.hablar.roster.client.page.RosterPresenter;
import com.calclab.hablar.search.client.page.SearchPage;
import com.calclab.hablar.search.client.page.SearchWidget;

/**
 * Adds Search support to Hablar
 * 
 */
public class HablarSearch {

	protected static final String ACTION_ID = "HablarLogic-searchAction";

	public HablarSearch(final Hablar hablar, final SearchConfig searchConfig, final XmppSession session, final XmppRoster roster,
			final ChatManager chatManager, final SearchManager searchManager) {

		searchManager.setHost(XmppURI.uri(null, searchConfig.searchService, null));
		final Visibility visible = searchConfig.searchOnRoster ? Visibility.hidden : Visibility.notFocused;
		final SearchPage searchPage = new SearchPage(session, searchManager, visible, searchConfig.searchCloseable, searchConfig.queryFactory,
				hablar.getEventBus(), new SearchWidget());
		hablar.addPage(searchPage);

		if (searchConfig.searchActions != null) {
			searchConfig.searchActions.addMenuOptions(searchPage.getItemMenu());
		} else {
			new SearchBasicActions(roster, chatManager).addMenuOptions(searchPage.getItemMenu());
		}

		if (searchConfig.searchOnRoster) {
			addSearchActionToRoster(hablar, searchPage);
		}
		if (searchConfig.showSearchPageOnEmptyRoster) {
			roster.addRosterRetrievedHandler(new RosterRetrievedHandler() {
				@Override
				public void onRosterRetrieved(final RosterRetrievedEvent event) {
					if (event.getRosterItems().isEmpty()) {
						searchPage.requestVisibility(Visibility.focused);
					}
				}
			});
		}
	}

	private void addSearchActionToRoster(final Hablar hablar, final SearchPage searchPage) {
		hablar.addPageAddedHandler(new PageAddedHandler() {
			@Override
			public void onPageAdded(final PageAddedEvent event) {
				final RosterPage rosterPage = RosterPresenter.asRoster(event.getPage());
				if (rosterPage != null) {
					final String name = SearchMessages.msg.openSearchPage();
					rosterPage.addAction(new SimpleAction<RosterPage>(name, ACTION_ID, IconsBundle.bundle.searchIcon()) {
						@Override
						public void execute(final RosterPage page) {
							searchPage.requestVisibility(Visibility.focused);
						}
					});
				}
			}
		}, true);
	}

}
