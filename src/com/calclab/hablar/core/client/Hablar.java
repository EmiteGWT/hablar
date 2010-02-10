package com.calclab.hablar.core.client;

import java.util.List;

import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.mvp.Presenter;
import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.page.PagePresenter;
import com.calclab.hablar.core.client.pages.PagesContainer;

/**
 * Hablar interface used to interact with HablarWidget<br/>
 * 
 * Hablar is basically a collection of pages. Each page is placed in the widget
 * inside a pages container.
 * 
 */
public interface Hablar extends Presenter<HablarDisplay> {

    public static enum Priority {
	highest, lowest
    }

    /**
     * Add a container to Hablar
     * 
     * @param container
     * @param priority
     *            The priority indicates the position of the container in the
     *            chain.
     * 
     */
    public void addContainer(PagesContainer container, Priority priority);

    /**
     * Add a page to hablar.
     * 
     * @param page
     */
    public void addPage(PagePresenter<?> page);

    /**
     * Add a page to hablar using the specified container
     * 
     * @param page
     *            the page to be added
     * @param containerRol
     *            the Rol name of the container to use
     */
    public void addPage(PagePresenter<?> page, String containerRol);

    public PagesContainer getContainer(String rol);

    public HablarDisplay getDisplay();

    public HablarEventBus getEventBus();

    public List<PagePresenter<?>> getPagePresentersOfType(String type);

    public void removePage(Page<?> page);

}