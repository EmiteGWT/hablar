package com.calclab.hablar.basic.client.ui.page;

import com.calclab.hablar.basic.client.ui.page.PageView.Visibility;

public interface PageHeader {

    String getTitle();

    void requestFocus();

    void setLogic(PageLogic logic);

    void setStyles(HeaderStyles styles);

    void setVisibility(Visibility visibility);

}
