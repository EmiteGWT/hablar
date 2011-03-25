package com.calclab.hablar.testing.display;

import com.google.gwt.user.client.ui.Focusable;

public class FocusableStub implements Focusable {

    private int tabIndex;
    private char accessKey;
    private boolean focused;

    public FocusableStub() {
	tabIndex = 0;
	focused = false;
    }

    public char getAccessKey() {
	return accessKey;
    }

    @Override
    public int getTabIndex() {
	return tabIndex;
    }

    public boolean isFocused() {
	return focused;
    }

    @Override
    public void setAccessKey(char key) {
	this.accessKey = key;
    }

    @Override
    public void setFocus(boolean focused) {
	this.focused = focused;
    }

    @Override
    public void setTabIndex(int index) {
	tabIndex = index;
    }
}
