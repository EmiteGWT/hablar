package com.calclab.hablar.clipboard.client;

import com.calclab.hablar.core.client.mvp.Display;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasText;

public interface CopyToClipboardDisplay extends Display {

    HasClickHandlers getCopyButton();

    HasClickHandlers getCloseButton();

    HasText getContentField();

    void setCopyButtonVisible(boolean visible);

}
