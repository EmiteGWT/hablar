package com.calclab.hablar.logger.client;

import com.calclab.hablar.core.client.mvp.Display;

public interface LoggerDisplay extends Display{

    void add(String message, String styleName, String sessionStyle);

}
