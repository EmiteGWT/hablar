package com.calclab.hablar.client;

import com.google.gwt.core.client.EntryPoint;

public class Hablar implements EntryPoint {

    @Override
    public void onModuleLoad() {
	HablarDialog hablarDialog = new HablarDialog();
	hablarDialog.show();
	hablarDialog.center();
    }
   
}
