package com.calclab.hablar.client.selenium;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class CustomPageObject {

    private static final long[] POLL_INTERVALS = { 10, 20, 30, 40, 50, 50, 50, 50, 100 };

    // Thanks to:
    // http://groups.google.com/group/webdriver/browse_frm/thread/6e705242cc6d75ed/f5f8dca438397254?lnk=gst#f5f8dca438397254
    protected void waitFor(final String waitForWhat, final Runnable runnable) {
	int i = 0;
	boolean success = false;
	final long timeout = System.currentTimeMillis() + 5000;
	while (i < POLL_INTERVALS.length && !success) {
	    try {
		runnable.run();
		success = true;
	    } catch (final Throwable e) {
		if (++i == POLL_INTERVALS.length) {
		    if (System.currentTimeMillis() > timeout) {
			throw new RuntimeException("Timeout while waiting for " + waitForWhat, e);
		    } else {
			--i;
		    }
		}
		try {
		    Thread.sleep(POLL_INTERVALS[i]);
		} catch (final InterruptedException e2) {
		    Thread.currentThread().interrupt();
		    throw new RuntimeException("Got interrupted while waiting for " + waitForWhat, e2);
		}
	    }
	}
    }

    protected void waitFor(final WebElement element, final String text) {
	waitFor(text, new Runnable() {
	    @Override
	    public void run() {
		final String elText = element.getText();
		Assert.assertTrue(elText.contains(text));
	    }
	});
    }
}
