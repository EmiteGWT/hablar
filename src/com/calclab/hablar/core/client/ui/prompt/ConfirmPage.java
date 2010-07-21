package com.calclab.hablar.core.client.ui.prompt;

import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.PagePresenter;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class ConfirmPage extends PagePresenter<ConfirmPageDisplay> {

    private static final String TYPE = "ConfirmPage";
    protected UserConfirmationHandler currentHandler;

    public ConfirmPage(HablarEventBus eventBus, final ConfirmPageDisplay display) {
	super(TYPE, "1", eventBus, display);

	eventBus.addHandler(ConfirmEvent.TYPE, new ConfirmHandler() {
	    @Override
	    public void onConfirm(ConfirmEvent event) {
		requestVisibility(Visibility.focused);
		display.getMessage().setText(event.message);
		display.setPageTitle(event.title);
		ConfirmPage.this.currentHandler = event.handler;
	    }
	});

	display.getYes().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		requestVisibility(Visibility.hidden);
		currentHandler.accept();
	    }
	});

	display.getNo().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		requestVisibility(Visibility.hidden);
		currentHandler.cancel();
	    }
	});

    }

    public static void show(HablarEventBus eventBus, String title, String message, UserConfirmationHandler handler) {
	eventBus.fireEvent(new ConfirmEvent(title, message, handler));
    }

}
