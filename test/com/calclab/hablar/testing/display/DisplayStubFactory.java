/**
 * 
 */
package com.calclab.hablar.testing.display;

import com.calclab.suco.client.ioc.Provider;
import com.google.gwt.dev.util.collect.HashMap;
import com.google.gwt.event.dom.client.HasChangeHandlers;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasFocusHandlers;
import com.google.gwt.event.dom.client.HasKeyPressHandlers;
import com.google.gwt.user.client.ui.Focusable;
import com.google.gwt.user.client.ui.HasHTML;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;

public class DisplayStubFactory {

    public static DisplayStubFactory instance = new DisplayStubFactory();

    private final HashMap<Class<?>, Provider<?>> providers;

    private DisplayStubFactory() {
	providers = new HashMap<Class<?>, Provider<?>>();
	providers.put(HasHTML.class, new Provider<HasHTML>() {
	    @Override
	    public HasHTML get() {
		return new HasHTMLStub();
	    }
	});
	providers.put(HasText.class, new Provider<HasText>() {
	    @Override
	    public HasText get() {
		return new HasTextStub();
	    }
	});
	providers.put(Focusable.class, new Provider<Focusable>() {
	    @Override
	    public Focusable get() {
		return new FocusableStub();
	    }
	});
	providers.put(HasClickHandlers.class, new Provider<HasClickHandlers>() {
	    @Override
	    public HasClickHandlers get() {
		return new HasClickHandlersStub();
	    }
	});
	providers.put(HasWidgets.class, new Provider<HasWidgets>() {
	    @Override
	    public HasWidgets get() {
		return new HasWidgetsStub();
	    }
	});
	providers.put(HasChangeHandlers.class, new Provider<HasChangeHandlers>() {
	    @Override
	    public HasChangeHandlers get() {
		return new HasChangeHandlersStub();
	    }
	});

	providers.put(HasFocusHandlers.class, new Provider<HasFocusHandlers>() {
	    @Override
	    public HasFocusHandlers get() {
		return new HasFocusHandlerStub();
	    }
	});

	providers.put(HasValue.class, new Provider<HasValue<?>>() {
	    @Override
	    public HasValue<?> get() {
		return new HasValueStub();
	    }
	});

	providers.put(HasKeyPressHandlers.class, new Provider<HasKeyPressHandlers>() {
	    @Override
	    public HasKeyPressHandlers get() {
		return new HasKeyPressHandlersStub();
	    }
	});
    }

    @SuppressWarnings("unchecked")
    public <T> T create(final Class<T> mockType) {
	final Provider<?> provider = providers.get(mockType);
	if (provider == null) {
	    return null;
	}
	return (T) provider.get();
    }
}