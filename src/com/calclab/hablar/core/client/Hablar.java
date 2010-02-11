package com.calclab.hablar.core.client;

import java.util.List;

import com.calclab.hablar.core.client.container.PageAddedHandler;
import com.calclab.hablar.core.client.container.PagesContainer;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.mvp.Presenter;
import com.calclab.hablar.core.client.page.Page;

/**
 * Hablar interface used to interact with HablarWidget<br/>
 * 
 * Hablar is basically a collection of pages. Each page is placed in the widget
 * inside a pages container.
 * 
 */
public interface Hablar extends Presenter<HablarDisplay> {

    public static enum Chain {
	before, after
    }

    /**
     * Add a container to Hablar
     * 
     * @param container
     * @param chain
     *            The priority indicates the position of the container in the
     *            chain.
     * 
     */
    public void addContainer(PagesContainer container, Chain chain);

    /**
     * Add a page to hablar.
     * 
     * @param page
     */
    public void addPage(Page<?> page);

    /**
     * Add a page to hablar using the specified container
     * 
     * @param page
     *            the page to be added
     * @param containerRol
     *            the Rol name of the container to use
     */
    public void addPage(Page<?> page, String containerRol);

    public void addPageAddedHandler(PageAddedHandler handler, boolean fireAlreadyAdded);

    public PagesContainer getContainer(String rol);

    public HablarDisplay getDisplay();

    public HablarEventBus getEventBus();

    public List<Page<?>> getPagesOfType(String type);

}