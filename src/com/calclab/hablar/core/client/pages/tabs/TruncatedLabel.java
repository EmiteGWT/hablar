package com.calclab.hablar.core.client.pages.tabs;

import com.calclab.emite.core.client.packet.TextUtils;
import com.google.gwt.user.client.ui.Label;

public class TruncatedLabel extends Label {

    private int trim;

    public TruncatedLabel() {
	this.setTrim(0);
    }

    public int getTrim() {
	return trim;
    }

    @Override
    public void setText(final String text) {
	super.setText(TextUtils.ellipsis(text, getTrim()));
    }

    public void setTrim(final int trim) {
	this.trim = trim;
    }
}
