package com.calclab.hablar.core.client.pages;

import com.calclab.hablar.core.client.mvp.Presenter;
import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.page.PageInfoChangedEvent;
import com.calclab.hablar.core.client.page.PageInfoChangedHandler;
import com.calclab.hablar.core.client.page.PageState;
import com.calclab.hablar.core.client.page.PagePresenter.Visibility;
import com.calclab.hablar.core.client.page.events.VisibilityChangedEvent;
import com.calclab.hablar.core.client.page.events.VisibilityChangedHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class HeaderPresenter implements Presenter<HeaderDisplay> {

    private final HeaderDisplay display;
    private String currentStyle;
    private String currentExternalStyle;

    public HeaderPresenter(final Page<?> page, final HeaderDisplay display) {
	this.display = display;

	final PageState state = page.getState();
	display.getOpen().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		page.requestVisibility(Visibility.toggle);
	    }
	});

	display.getClose().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		event.preventDefault();
		page.requestVisibility(Visibility.hidden);
	    }
	});

	state.addVisibilityChangedHandler(new VisibilityChangedHandler() {
	    @Override
	    public void onVisibilityChanged(final VisibilityChangedEvent event) {
		visibilityChanged(state.getVisibility());
	    }

	});
	visibilityChanged(state.getVisibility());

	state.addInfoChangedHandler(new PageInfoChangedHandler() {
	    @Override
	    public void onPageInfoChanged(final PageInfoChangedEvent event) {
		update(state);
	    }
	});

	update(state);
    }

    public HeaderDisplay getDisplay() {
	return display;
    }

    private void setExternalState(final String externalState) {
	if (currentExternalStyle != null) {
	    display.removeStyle(currentExternalStyle);
	}
	currentExternalStyle = externalState;
	if (externalState != null) {
	    display.addStyle(externalState);
	}
    }

    private void update(final PageState state) {
	display.setIcon(state.getPageIcon());
	display.getHeaderTitle().setText(state.getPageTitle());
	display.setHeaderTooltip(state.getPageTitleTooltip());
	display.setCloseIconVisible(state.isCloseable());
	setExternalState(state.getExternalState());
    }

    private void visibilityChanged(final Visibility visibility) {
	if (currentStyle != null) {
	    display.removeStyle(currentStyle);
	}
	final String newStyle = visibility.toString();
	display.addStyle(newStyle);
	currentStyle = newStyle;
    }

}
