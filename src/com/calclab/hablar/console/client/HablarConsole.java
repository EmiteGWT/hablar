package com.calclab.hablar.console.client;

import com.calclab.hablar.core.client.Hablar;
import com.calclab.hablar.core.client.container.PageAddedEvent;
import com.calclab.hablar.core.client.container.PageAddedHandler;
import com.calclab.hablar.core.client.page.PagePresenter.Visibility;
import com.calclab.hablar.core.client.ui.icon.HablarIcons;
import com.calclab.hablar.core.client.ui.menu.SimpleAction;
import com.calclab.hablar.roster.client.page.RosterPage;
import com.calclab.hablar.roster.client.page.RosterPresenter;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.resources.client.ImageResource;

public class HablarConsole implements EntryPoint {

    protected static final String ACTION_ID = "hablar-ConsoleAction";

    public static void install(final Hablar hablar) {
	final ConsolePresenter loggerPage = new ConsolePresenter(hablar.getEventBus(), new ConsoleWidget());
	hablar.addPage(loggerPage);

	hablar.addPageAddedHandler(new PageAddedHandler() {
	    @Override
	    public void onPageAdded(final PageAddedEvent event) {
		final RosterPage rosterPage = RosterPresenter.asRoster(event.getPage());
		if (rosterPage != null) {

		    final String name = "Open Console";
		    final ImageResource icon = HablarIcons.getBundle().consoleIcon();
		    rosterPage.addAction(new SimpleAction<RosterPage>(name, ACTION_ID, icon) {
			@Override
			public void execute(final RosterPage page) {
			    loggerPage.requestVisibility(Visibility.focused);
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
