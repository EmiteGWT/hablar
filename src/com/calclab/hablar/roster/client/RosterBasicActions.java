package com.calclab.hablar.roster.client;

import com.calclab.emite.im.client.roster.Roster;
import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.hablar.core.client.ui.menu.SimpleAction;
import com.calclab.hablar.roster.client.page.RosterPage;
import com.calclab.suco.client.Suco;

public class RosterBasicActions {

    public RosterBasicActions(final RosterPage rosterPage) {
	final Roster roster = Suco.get(Roster.class);
	final String id = "HablarRoster-removeAction";
	rosterPage.getItemMenu().addAction(new SimpleAction<RosterItem>("Remove from roster", id) {
	    @Override
	    public void execute(final RosterItem target) {
		roster.removeItem(target.getJID());
	    }
	});

    }
}
