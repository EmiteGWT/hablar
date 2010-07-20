package com.calclab.hablar.core.client.browser;

/**
 * A helper class to make possible testing
 */
public class BrowserStub extends Browser {
    private boolean value;
    private int calledTimes;

    public BrowserStub() {
	calledTimes = 0;
	value = true;
	Browser.setInstance(this);
    }

    public void setConfirmationValue(boolean value) {
	this.value = value;
    }

    @Override
    public boolean confirm(String message) {
	calledTimes++;
	return value;
    }

    public void reset() {
	calledTimes = 0;
    }

    public int getCalledTimes() {
	return calledTimes;
    }
}
