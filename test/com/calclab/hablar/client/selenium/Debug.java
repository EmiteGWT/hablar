package com.calclab.hablar.client.selenium;

import com.google.gwt.user.client.ui.UIObject;

public class Debug {

    public static final String PRE = UIObject.DEBUG_ID_PREFIX;

    public static String gwt(final String id) {
	return new StringBuffer().append(PRE).append(id).toString();
    }

}
