package com.calclab.hablar.vcard.client;

import com.calclab.hablar.core.client.Hablar;
import com.calclab.hablar.user.client.UserContainer;
import com.google.gwt.core.client.EntryPoint;

public class HablarVCard implements EntryPoint {

    public static void install(Hablar hablar) {
	VCardPresenter vcardPage = new VCardPresenter(hablar.getEventBus(), new VCardWidget());
	hablar.addPage(vcardPage, UserContainer.ROL);
    }

    @Override
    public void onModuleLoad() {
    }

}
