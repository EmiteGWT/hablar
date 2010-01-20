package com.calclab.hablar.signals.client;

import com.calclab.hablar.basic.client.ui.HablarWidget;
import com.calclab.hablar.basic.client.ui.page.PageView;
import com.calclab.suco.client.events.Listener;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

/**
 * Esta es la clase para hacer las cosas del window
 */
public class HablarSignals implements EntryPoint {

    /**
     * Este método se llama despues de crear el HablarWidget por si necesitases
     * interaccionar con él
     */
    public static void install(HablarWidget hablar) {
	hablar.getPages().onStatusMessageChanged(new Listener<PageView>() {
	    @Override
	    public void onEvent(PageView page) {
		GWT.log("GROWL: " + page.getStatusMessage(), null);
	    }
	});

	hablar.getPages().onPageOpened(new Listener<PageView>() {
	    @Override
	    public void onEvent(PageView parameter) {
		// con esto ya sabes qué ventana (página)
		// está activa
	    }
	});
    }

    @Override
    public void onModuleLoad() {
	// esto se llama cuando se carga el módulo
    }

}
