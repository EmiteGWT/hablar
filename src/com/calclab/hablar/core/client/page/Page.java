package com.calclab.hablar.core.client.page;

import com.calclab.hablar.core.client.mvp.Display;
import com.calclab.hablar.core.client.mvp.Presenter;
import com.calclab.hablar.core.client.page.PagePresenter.Visibility;

/**
 * All page presenters must implements this interface
 * 
 */
public interface Page<T extends Display> extends Presenter<T> {

    /**
     * All pages have a unique id reference
     */
    public String getId();

    public PageState getState();

    /**
     * Retrive the page type
     * 
     * @return the page's type
     */
    public String getType();

    /**
     * Retrieve the page's visibility
     * 
     * @return the current page visibility
     */
    public Visibility getVisibility();

    /**
     * Sets the visibility of this page. Will fire a VisibilityChangedEvent
     * 
     * @param visibility
     *            the new visibility
     */
    public void setVisibility(Visibility visibility);

    /**
     * Make this page fires a VisibilityChangeRequestEvent
     * 
     * @param newVisibility
     *            the desired new visibility
     */
    void requestVisibility(Visibility newVisibility);

}