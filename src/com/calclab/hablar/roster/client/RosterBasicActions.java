package com.calclab.hablar.roster.client;

import com.calclab.emite.im.client.roster.Roster;
import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.hablar.core.client.ui.menu.MenuAction;
import com.calclab.suco.client.Suco;

public class RosterBasicActions {

    public RosterBasicActions(RosterPage rosterPage) {
	final Roster roster = Suco.get(Roster.class);
	String id = "HablarRoster-removeAction";
	rosterPage.getItemMenu().addAction(new MenuAction<RosterItem>("Remove from roster", id) {
	    @Override
	    public void execute(RosterItem target) {
		roster.removeItem(target.getJID());
	    }
	});
    }
}
