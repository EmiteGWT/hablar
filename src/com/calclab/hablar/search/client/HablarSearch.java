package com.calclab.hablar.search.client;

import java.util.List;

import com.calclab.hablar.core.client.Hablar;
import com.calclab.hablar.core.client.HablarWidget;
import com.calclab.hablar.core.client.container.PageAddedEvent;
import com.calclab.hablar.core.client.container.PageAddedHandler;
import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.page.PagePresenter.Visibility;
import com.calclab.hablar.core.client.ui.icon.HablarIcons;
import com.calclab.hablar.core.client.ui.icon.HablarIcons.IconType;
import com.calclab.hablar.core.client.ui.menu.SimpleAction;
import com.calclab.hablar.roster.client.page.RosterPage;
import com.calclab.hablar.roster.client.page.RosterPresenter;
import com.google.gwt.core.client.EntryPoint;

/**
 * Adds Search support to Hablar
 * 
 */
public class HablarSearch implements EntryPoint {

    protected static final String ACTION_ID = "HablarLogic-searchAction";

    public static void install(final Hablar hablar) {
	final SearchPage searchPage = new SearchPage(hablar.getEventBus(), new SearchWidget());
	final List<Page<?>> rosters = hablar.getPagesOfType(RosterPage.TYPE);
	final boolean visible = rosters.size() == 0;
	final Visibility visibility = visible ? Visibility.notFocused : Visibility.hidden;
	searchPage.setVisibility(visibility);
	searchPage.getState().setCloseable(!visible);
	hablar.addPage(searchPage);
	new SearchBasicActions(searchPage);

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

    public static void install(final HablarWidget widget) {
	install(widget.getHablar());
    }

    @Override
    public void onModuleLoad() {
    }

}
