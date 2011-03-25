package com.calclab.hablar.testing;

import com.calclab.suco.client.ioc.Container;
import com.calclab.suco.client.ioc.Provider;
import com.calclab.suco.client.ioc.decorator.ProviderCollection;
import com.calclab.suco.client.ioc.decorator.Singleton;
import com.google.gwt.core.client.GWT;

/**
 * All the providers/factories decorated by SessionComponent are called when a
 * new Session component is created.
 * 
 * Use this decorator if your component DEPENDS on Session to perform its tasks
 */
@Deprecated
public class SessionComponent extends ProviderCollection {
	private boolean initialized;

	public SessionComponent(final Container container) {
		super(container, Singleton.instance);
		initialized = false;
	}

	@Override
	public <T> Provider<T> decorate(final Class<T> type,
			final Provider<T> undecorated) {
		if (initialized == true) {
			undecorated.get();
		}
		return super.decorate(type, undecorated);
	}

	public void init() {
		if (initialized == false) {
			GWT.log("SESSION COMPONENTS!", null);
			for (final Provider<?> p : getProviders()) {
				p.get();
			}
			initialized = true;
		}
	}
}