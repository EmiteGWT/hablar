package com.calclab.hablar.basic.client.ui.pages;

import java.util.List;

import com.calclab.hablar.basic.client.ui.page.PageView;
import com.calclab.suco.client.events.Listener;

public interface Pages {

    void add(PageView pageView);

    /**
     * Close the given page.
     * 
     * @param pageView
     *            the page to be closed
     * @return true if closed
     */
    boolean close(PageView pageView);

    List<PageView> getPagesOfType(String pageType);

    /**
     * 
     * @param pageView
     * @return
     */
    boolean hasPageView(PageView pageView);

    /**
     * Hide a page (do not show it in the pages ui).
     * 
     * @param pageView
     *            the page to be hidden
     */
    void hide(PageView pageView);

    /**
     * Add a listener to know when a page has been opened
     * 
     * @param listener
     */
    void onPageClosed(Listener<PageView> listener);

    /**
     * Add a listener to know when a page has been opened
     * 
     * @param listener
     */
    void onPageOpened(Listener<PageView> listener);

    /**
     * Add a listener to know when a status message changed in any of the pages
     * 
     * @param listener
     */
    void onStatusMessageChanged(Listener<PageView> listener);

    /**
     * Open the given page. If the page is hidden, it whill be show.
     * 
     * @param pageView
     *            the page to be showed
     */
    void open(PageView pageView);
}
