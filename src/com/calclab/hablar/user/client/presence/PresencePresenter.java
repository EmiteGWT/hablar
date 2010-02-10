package com.calclab.hablar.user.client.presence;

import com.calclab.emite.core.client.xmpp.stanzas.Presence;
import com.calclab.emite.im.client.presence.PresenceManager;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.PagePresenter;
import com.calclab.hablar.core.client.ui.icon.HablarIcons;
import com.calclab.hablar.core.client.ui.icon.HablarIcons.IconType;
import com.calclab.hablar.user.client.UserPage;
import com.calclab.suco.client.Suco;
import com.google.gwt.core.client.GWT;

public class PresencePresenter extends PagePresenter<PresenceDisplay> implements UserPage<PresenceDisplay> {
    public static final String TYPE = "Presence";
    private static int id = 0;
    private final PresenceManager manager;

    public PresencePresenter(HablarEventBus eventBus, PresenceDisplay display) {
	super(TYPE, "" + (++id), eventBus, display);
	manager = Suco.get(PresenceManager.class);
	String style = HablarIcons.get(IconType.buddy);
	model.init(style, "Your status");
    }

    @Override
    public void afterClosed() {
	GWT.log("CHANGE PRESENCE!", null);
	final Presence presence = manager.getOwnPresence();
	presence.setStatus(display.getStatus().getText());
	manager.changeOwnPresence(presence);
    }

    @Override
    public void beforeOpen() {
	GWT.log("LOAD PRESENCE!", null);
	Presence presence = manager.getOwnPresence();
	display.getStatus().setText(presence.getStatus());
    }

}
