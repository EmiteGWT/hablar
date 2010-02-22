package com.calclab.hablar.core.client;

import com.calclab.hablar.core.client.ui.icon.DefaultHablarIcons;
import com.calclab.hablar.core.client.ui.icon.HablarIcons;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

public class HablarCore implements EntryPoint {

    @Override
    public void onModuleLoad() {
	HablarIcons.setStyles((HablarIcons) GWT.create(DefaultHablarIcons.class));
    }
}
