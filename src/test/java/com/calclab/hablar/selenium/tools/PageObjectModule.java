package com.calclab.hablar.selenium.tools;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import com.calclab.suco.client.ioc.decorator.Singleton;
import com.calclab.suco.client.ioc.module.AbstractModule;
import com.calclab.suco.client.ioc.module.Factory;

public abstract class PageObjectModule extends AbstractModule {
    protected <T> void registerPageObject(final Class<T> componentType, final T object) {
	register(Singleton.class, new Factory<T>(componentType) {
	    @Override
	    public T create() {
		PageFactory.initElements($(ElementLocatorFactory.class), object);
		return object;
	    }
	});
    }
}
