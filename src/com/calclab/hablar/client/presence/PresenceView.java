package com.calclab.hablar.client.presence;

import com.calclab.hablar.client.ui.icons.Icons.IconType;

public interface PresenceView {

    void setIcon(IconType icon);

    void setName(String name);

    void setStatusBoxVisible(boolean visible);

    void setStatusMessage(String statusMessage);

    void setStatusMessageVisible(boolean visible);

}
