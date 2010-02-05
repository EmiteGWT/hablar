package com.calclab.hablar.core.client.pages;

import java.util.ArrayList;

import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.page.PagePresenter;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;

public class ContainerAggregator implements PagesContainer {
    private static final String ROL = "Aggregator";
    private final ArrayList<PagesContainer> containers;

    public ContainerAggregator() {
	this.containers = new ArrayList<PagesContainer>();
    }

    @Override
    public boolean add(Page<?> page) {
	for (PagesContainer container : containers) {
	    GWT.log("Check: " + container.getRol(), null);
	    if (container.add(page)) {
		GWT.log("ADD " + page.getId() + " to " + container.getRol(), null);
		return true;
	    }
	}
	return false;
    }

    public void addContainer(PagesContainer container) {
	GWT.log("Insert container: " + container.getRol(), null);
	containers.add(0, container);
    }

    public void addPage(PagePresenter<?> page, String containerType) {
	PagesContainer container = getContainer(containerType);
	container.add(page);
    }

    @Override
    public boolean focus(Page<?> page) {
	for (PagesContainer container : containers) {
	    if (container.focus(page)) {
		return true;
	    }
	}
	return false;
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

    @Override
    public String getRol() {
	return ROL;
    }

    @Override
    public Widget getWidget() {
	return null;
    }

    @Override
    public boolean hide(Page<?> page) {
	for (PagesContainer container : containers) {
	    if (container.hide(page)) {
		return true;
	    }
	}
	return false;
    }

    @Override
    public boolean remove(Page<?> page) {
	for (PagesContainer container : containers) {
	    if (container.remove(page)) {
		return true;
	    }
	}
	return false;
    }

    @Override
    public boolean unfocus(Page<?> page) {
	for (PagesContainer container : containers) {
	    if (container.unfocus(page)) {
		return true;
	    }
	}
	return false;
    }

}
