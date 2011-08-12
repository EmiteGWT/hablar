package com.calclab.hablar.console.client;

import com.calclab.emite.core.client.conn.XmppConnection;
import com.calclab.emite.core.client.xmpp.session.XmppSession;
import com.calclab.hablar.core.client.Hablar;
import com.calclab.hablar.core.client.container.PageAddedEvent;
import com.calclab.hablar.core.client.container.PageAddedHandler;
import com.calclab.hablar.core.client.page.PagePresenter.Visibility;
import com.calclab.hablar.core.client.ui.menu.SimpleAction;
import com.calclab.hablar.icons.client.IconsBundle;
import com.calclab.hablar.roster.client.page.RosterPage;
import com.calclab.hablar.roster.client.page.RosterPresenter;

public class HablarConsole {

	protected static final String ACTION_ID = "hablar-ConsoleAction";

	public HablarConsole(final Hablar hablar, final XmppConnection connection, final XmppSession session) {
		final ConsolePresenter loggerPage = new ConsolePresenter(connection, session, hablar.getEventBus(), new ConsoleWidget());
		hablar.addPage(loggerPage);

		hablar.addPageAddedHandler(new PageAddedHandler() {
			@Override
			public void onPageAdded(final PageAddedEvent event) {
				final RosterPage rosterPage = RosterPresenter.asRoster(event.getPage());
				if (rosterPage != null) {

					final String name = "Open Console";
					rosterPage.addAction(new SimpleAction<RosterPage>(name, ACTION_ID, IconsBundle.bundle.consoleIcon()) {
						@Override
						public void execute(final RosterPage page) {
							loggerPage.requestVisibility(Visibility.focused);
						}
					});
				}
			}
		}, true);
	}

}
