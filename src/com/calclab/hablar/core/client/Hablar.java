package com.calclab.hablar.core.client;

import java.util.List;

import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.mvp.Presenter;
import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.page.PagePresenter;
import com.calclab.hablar.core.client.pages.PagesContainer;

public interface Hablar extends Presenter<HablarDisplay> {

    public void addContainer(PagesContainer container);

    public void addPage(PagePresenter<?> page);

    public void addPage(PagePresenter<?> page, String containerType);

    public PagesContainer getContainer(String rol);

    public HablarDisplay getDisplay();

    public HablarEventBus getEventBus();

    public List<PagePresenter<?>> getPagePresentersOfType(String type);

    public void removePage(Page<?> page);

}