package com.calclab.hablar.rooms.client.open;

import com.calclab.hablar.core.client.mvp.Display;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;

public interface SelectRosterItemDisplay extends Display {

	HasText getJid();

	HasText getName();

	HasValue<Boolean> getSelected();

	HasText getStatus();

	void setIcon(ImageResource imageResource);

	void setSelectEnabled(boolean enabled);

}
