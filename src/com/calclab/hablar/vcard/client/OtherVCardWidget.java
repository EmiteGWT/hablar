package com.calclab.hablar.vcard.client;

import com.google.gwt.user.client.ui.ScrollPanel;

public class OtherVCardWidget extends VCardWidget implements VCardDisplay {

    public OtherVCardWidget(final boolean readOnly) {
	final ScrollPanel scroll = new ScrollPanel();
	scroll.add(uiBinder.createAndBindUi(this));
	initWidget(scroll);
	init(readOnly, "OtherVCardWidget");
    }

}
