package com.calclab.hablar.user.client;

import com.calclab.hablar.core.client.mvp.Display;
import com.google.gwt.user.client.ui.HasText;

public interface UserDisplay extends Display {
    HasText getStatus();
}
