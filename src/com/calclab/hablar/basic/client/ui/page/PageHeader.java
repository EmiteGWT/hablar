package com.calclab.hablar.basic.client.ui.page;

import com.calclab.hablar.basic.client.ui.page.PageView.Visibility;
import com.google.gwt.event.dom.client.HasClickHandlers;

public interface PageHeader extends HasClickHandlers {

    HasClickHandlers getCloseIcon();

    String getTitle();

    void requestFocus();

    void setStyles(HeaderStyles styles);

    void setVisibility(Visibility visibility);

}
