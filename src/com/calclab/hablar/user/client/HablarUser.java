package com.calclab.hablar.user.client;

import com.calclab.hablar.core.client.Hablar;
import com.google.gwt.core.client.EntryPoint;

public class HablarUser implements EntryPoint {

    public static void install(Hablar hablar) {
	UserPresenter user = new UserPresenter(hablar.getEventBus(), new UserWidget());
	hablar.addPage(user);
    }

    @Override
    public void onModuleLoad() {
    }

}
