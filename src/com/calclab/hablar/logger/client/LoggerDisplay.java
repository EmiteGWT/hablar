package com.calclab.hablar.logger.client;

import com.calclab.hablar.core.client.mvp.Display;
import com.google.gwt.event.dom.client.HasClickHandlers;

public interface LoggerDisplay extends Display {

    void add(String message, String styleName, String sessionStyle);

    void clear();

    HasClickHandlers getClear();

}
