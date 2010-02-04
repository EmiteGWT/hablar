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
     * All pages have a unique PageID reference
     */
    public String getId();

    public PageState getState();

    public String getType();

    public void requestHide();

    public void requestOpen();

    public void setVisibility(Visibility closed);

}