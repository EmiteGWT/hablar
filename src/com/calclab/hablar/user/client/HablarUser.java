package com.calclab.hablar.user.client;

import com.calclab.hablar.core.client.Hablar;
import com.calclab.hablar.core.client.Hablar.Chain;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.ui.menu.Menu;
import com.calclab.hablar.user.client.presence.PresencePage;
import com.calclab.hablar.user.client.presence.PresenceWidget;
import com.google.gwt.core.client.EntryPoint;

public class HablarUser implements EntryPoint {

    public static void install(final Hablar hablar) {
	final HablarEventBus eventBus = hablar.getEventBus();
	final UserPage user = new UserPage(eventBus, new UserWidget());
	hablar.addPage(user);

	final UserContainer container = new UserContainer(eventBus, user);
	hablar.addContainer(container, Chain.after);

	final PresenceWidget display = new PresenceWidget();
	final Menu<PresencePage> menu = new Menu<PresencePage>(display.newStatusMenuDisplay("hablar-StatusItemMenu"));
	final PresencePage presence = new PresencePage(eventBus, display, menu);
	hablar.addPage(presence, UserContainer.ROL);
    }

    @Override
    public void onModuleLoad() {
    }

}
