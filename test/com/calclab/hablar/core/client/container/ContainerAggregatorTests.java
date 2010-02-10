package com.calclab.hablar.core.client.container;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.calclab.hablar.core.client.Hablar.Chain;
import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.page.PagePresenter.Visibility;
import com.calclab.hablar.testing.HablarTester;

public class ContainerAggregatorTests {

    private ContainerAggregator aggregator;
    private HablarTester tester;

    @Before
    public void setup() {
	tester = new HablarTester();

	aggregator = new ContainerAggregator();

    }

    @Test
    public void shouldAddPages() {
	addDefaultContainer();
	Page<?> page1 = newPage(Visibility.focused, "page");
	assertTrue(aggregator.add(page1));
	Page<?> page2 = newPage(Visibility.focused, "page");
	assertTrue(aggregator.add(page2));
	assertTrue(aggregator.hasPage(page1));
	assertTrue(aggregator.hasPage(page2));
    }

    private void addDefaultContainer() {
	PagesContainer container = mock(PagesContainer.class);
	when(container.add((Page<?>) anyObject())).thenReturn(true);
	aggregator.addContainer(container, Chain.after);
    }

    private Page<?> newPage(Visibility visibility, String type) {
	Page<?> page = tester.newPage(visibility);
	when(page.getType()).thenReturn(type);
	return page;
    }
}
