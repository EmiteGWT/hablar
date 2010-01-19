package com.calclab.hablar.basic.client;

import com.calclab.hablar.basic.client.i18n.Msg;
import com.calclab.suco.client.ioc.decorator.Singleton;
import com.calclab.suco.client.ioc.module.AbstractModule;
import com.calclab.suco.client.ioc.module.Factory;
import com.google.gwt.core.client.GWT;

public class HablarModule extends AbstractModule {

    @Override
    protected void onInstall() {
	register(Singleton.class, new Factory<Msg>(Msg.class) {
	    @Override
	    public Msg create() {
		return (Msg) GWT.create(Msg.class);
	    }
	});
    }
}
