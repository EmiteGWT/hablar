package com.calclab.hablar.client.roster;

import org.mockito.Mockito;

import com.calclab.hablar.client.i18n.Msg;
import com.calclab.suco.client.Suco;
import com.calclab.suco.client.ioc.decorator.Singleton;
import com.calclab.suco.client.ioc.module.AbstractModule;
import com.calclab.suco.client.ioc.module.Factory;

public class AbstractLogicTest {
    public static void registerI18n() {
	// FIXME: While we decide to use this solution
	Suco.install(new AbstractModule() {
	    @Override
	    protected void onInstall() {
		register(Singleton.class, new Factory<Msg>(Msg.class) {
		    @Override
		    public Msg create() {
			return Mockito.mock(Msg.class);
		    }
		});
	    }
	});
    }

}
