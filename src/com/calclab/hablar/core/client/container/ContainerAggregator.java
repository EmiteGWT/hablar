package com.calclab.hablar.core.client.container;

import java.util.ArrayList;
import java.util.List;

import com.calclab.hablar.core.client.Hablar.Chain;
import com.calclab.hablar.core.client.page.Page;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;

public class ContainerAggregator implements PagesContainer {
    private static final String ROL = "Aggregator";
    private final ArrayList<PagesContainer> containers;
    private final ArrayList<Page<?>> pages;

    public ContainerAggregator() {
	this.containers = new ArrayList<PagesContainer>();
	this.pages = new ArrayList<Page<?>>();
    }

    @Override
    public boolean add(Page<?> page) {
	for (PagesContainer container : containers) {
	    if (container.add(page)) {
		pages.add(page);
		return true;
	    }
	}
	return false;
    }

    public void addContainer(PagesContainer container, Chain chain) {
	GWT.log("Add container: " + container.getRol() + " (" + chain + ")", null);
	if (chain == Chain.before) {
	    containers.add(0, container);
	} else {
	    containers.add(container);
	}
    }

    public void addPage(Page<?> page, String containerType) {
	PagesContainer container = getContainer(containerType);
	if (container.add(page)) {
	    pages.add(page);
	}
    }

    public PagesContainer getContainer(String type) {
	for (PagesContainer c : containers) {
	    if (type.equals(c.getRol())) {
		return c;
	    }
	}
	assert false : "Container not found.";
	return null;
    }

    public List<Page<?>> getPages() {
	return pages;
    }

    public List<Page<?>> getPagesOfType(String type) {
	ArrayList<Page<?>> list = new ArrayList<Page<?>>();
	for (Page<?> page : getPages()) {
	    if (type.equals(page.getType())) {
		list.add(page);
	    }
	}
	return list;
    }

    @Override
    public String getRol() {
	return ROL;
    }

    @Override
    public Widget getWidget() {
	return null;
    }

    public boolean hasPage(Page<?> page) {
	return pages.contains(page);
    }

}
