package com.calclab.hablar.vcard.client;

import com.calclab.emite.browser.client.PageAssist;

public class VCardConfig {
    /**
     * <meta name="hablar.vcard.read-only" value="{true|false}" />
     * 
     */
    public boolean vCardReadOnly = false;

    public static VCardConfig getFromMeta() {
	VCardConfig config = new VCardConfig();
	config.vCardReadOnly = PageAssist.isMetaFalse("hablar.vcard.read-only");
	return config;
    }

}
