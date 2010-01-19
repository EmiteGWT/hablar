package com.calclab.hablar.client.ui.debug;

import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.google.gwt.user.client.ui.UIObject;

public class Debug {

    public static final String PRE = UIObject.DEBUG_ID_PREFIX;

    public static String getIdFromJid(final String prefix, final String jid) {
	return join(prefix, XmppURI.jid(jid).toString());
    }

    public static String getIdFromJid(final String prefix, final XmppURI jid) {
	return join(prefix, jid.toString());
    }

    public static String gwtId(final String id) {
	return new StringBuffer().append(PRE).append(id).toString();
    }

    private static String join(final String prefix, final String jid) {
	return new StringBuffer().append(prefix).append(jid.replaceAll("@", "-")).toString();
    }

}
