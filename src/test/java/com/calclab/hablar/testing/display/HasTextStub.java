package com.calclab.hablar.testing.display;

import com.google.gwt.user.client.ui.HasText;

public class HasTextStub implements HasText {

    private String text;

    @Override
    public String getText() {
	return text;
    }

    @Override
    public void setText(String text) {
	this.text = text;

    }

}
