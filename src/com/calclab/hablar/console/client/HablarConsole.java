package com.calclab.hablar.console.client;

import com.calclab.emite.core.client.conn.XmppConnection;
import com.calclab.emite.core.client.xmpp.session.XmppSession;
import com.calclab.hablar.core.client.Hablar;
import com.calclab.hablar.core.client.container.PageAddedEvent;
import com.calclab.hablar.core.client.container.PageAddedHandler;
import com.calclab.hablar.core.client.page.PagePresenter.Visibility;
import com.calclab.hablar.core.client.ui.icon.Icons;
import com.calclab.hablar.core.client.ui.menu.SimpleAction;
import com.calclab.hablar.roster.client.page.RosterPage;
import com.calclab.hablar.roster.client.page.RosterPresenter;
import com.calclab.suco.client.Suco;

public class HablarConsole {

    protected static final String ACTION_ID = "hablar-ConsoleAction";

    // FIXME: move to gin
    @SuppressWarnings("deprecation")
    public HablarConsole(final Hablar hablar) {
	final XmppSession session = Suco.get(XmppSession.class);
	final XmppConnection connection = Suco.get(XmppConnection.class);

	final ConsolePresenter loggerPage = new ConsolePresenter(connection, session, hablar.getEventBus(),
		new ConsoleWidget());
	hablar.addPage(loggerPage);

	hablar.addPageAddedHandler(new PageAddedHandler() {
	    @Override
	    public void onPageAdded(final PageAddedEvent event) {
		final RosterPage rosterPage = RosterPresenter.asRoster(event.getPage());
		if (rosterPage != null) {

		    final String name = "Open Console";
		    final String icon = Icons.CONSOLE;
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

}
