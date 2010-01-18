package com.calclab.hablar.client.selenium;

import java.text.MessageFormat;

import com.calclab.hablar.client.i18n.Msg;
import com.google.gwt.i18n.client.Messages.DefaultMessage;

public class I18nHelper {

    public String get(final String method, final Object... params) {
	// TODO get rid of plurals
	final Class<?>[] classes = new Class<?>[params.length];
	for (int i = 0; i < params.length; i++) {
	    final Class<? extends Object> class1 = params[i].getClass();
	    classes[i] = class1 == Integer.class ? int.class : class1;
	}
	final String defaultValue = i18nDefaultValue(method, classes);
	return MessageFormat.format(defaultValue, params);
    }

    private String i18nDefaultValue(final String method, final Class<?>... params) {
	try {
	    return Msg.class.getMethod(method, params).getAnnotation(DefaultMessage.class).value();
	} catch (final Exception e) {
	    e.printStackTrace();
	    return "Error: Inexistent i18n String definition for method: " + method;
	}
    }
}
