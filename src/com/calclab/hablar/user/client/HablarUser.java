package com.calclab.hablar.user.client;

import com.calclab.hablar.core.client.Hablar;
import com.calclab.hablar.core.client.Hablar.Chain;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.user.client.presence.PresencePage;
import com.calclab.hablar.user.client.presence.PresenceWidget;
import com.google.gwt.core.client.EntryPoint;

public class HablarUser implements EntryPoint {

    public static void install(Hablar hablar) {
	HablarEventBus eventBus = hablar.getEventBus();
	UserPage user = new UserPage(eventBus, new UserWidget());
	hablar.addPage(user);

	UserContainer container = new UserContainer(eventBus, user);
	hablar.addContainer(container, Chain.after);
	PresencePage presence = new PresencePage(eventBus, new PresenceWidget());
	hablar.addPage(presence, UserContainer.ROL);
    }

    @Override
    public void onModuleLoad() {
    }

}
