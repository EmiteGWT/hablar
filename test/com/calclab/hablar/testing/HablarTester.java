package com.calclab.hablar.testing;

import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.mock.HablarMocks;

public class HablarTester {

    public EventBusTester eventBus;

    public HablarTester() {
	HablarMocks.disarm();
	this.eventBus = new EventBusTester();
    }

    public EventBusTester getEventBus() {
	return eventBus;
    }

    public Page<?> getPage() {
	return HablarMocks.getPage(eventBus);
    }
}
