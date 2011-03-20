package com.calclab.hablar.core.mock;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.calclab.hablar.core.client.mvp.Display;
import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.testing.EventBusTester;

public class HablarMocksTests {

    @Test
    public void shouldCreateNicePages() {
	EventBusTester eventBus = new EventBusTester();
	Page<Display> page = HablarMocks.getPage(eventBus);
	assertNotNull(page);
	assertNotNull(page.getDisplay());
	assertNotNull(page.getDisplay().asWidget());
    }
}
