package com.calclab.hablar.core.client;

import com.calclab.hablar.core.client.ui.icon.DefaultHablarIcons;
import com.calclab.hablar.core.client.ui.icon.OldHablarIcons;
import com.calclab.hablar.core.client.ui.icon.HablarIconsBundle;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

public class HablarCore implements EntryPoint {

    @Override
    public void onModuleLoad() {
	OldHablarIcons.setStyles((OldHablarIcons) GWT.create(DefaultHablarIcons.class));
	OldHablarIcons.setBundle((HablarIconsBundle) GWT.create(HablarIconsBundle.class));
    }
}
