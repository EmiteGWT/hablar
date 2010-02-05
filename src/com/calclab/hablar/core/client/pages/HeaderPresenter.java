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
    private String currentStyle, currentIconStyle;

    public HeaderPresenter(final Page<?> page, final HeaderDisplay display) {
	this.display = display;

	final PageState state = page.getState();
	display.getOpen().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		if (page.getVisibility() == Visibility.focused) {
		    page.requestVisibility(Visibility.notFocused);
		} else {
		    page.requestVisibility(Visibility.focused);
		}
	    }
	});

	display.getClose().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		event.preventDefault();
		page.requestVisibility(Visibility.hidden);
	    }
	});

	state.addVisibilityChangedHandler(new VisibilityChangedHandler() {
	    @Override
	    public void onVisibilityChanged(VisibilityChangedEvent event) {
		visibilityChanged(state.getVisibility());
	    }

	});
	visibilityChanged(state.getVisibility());

	state.addInfoChangedHandler(new PageInfoChangedHandler() {
	    @Override
	    public void onPageInfoChanged(PageInfoChangedEvent event) {
		setIconStyle(state.getPageIcon());
		display.getHeaderTitle().setText(state.getPageTitle());
		display.setCloseIconVisible(state.isCloseable());
	    }
	});

	setIconStyle(state.getPageIcon());
	display.getHeaderTitle().setText(state.getPageTitle());
	display.setCloseIconVisible(state.isCloseable());
    }

    public HeaderDisplay getDisplay() {
	return display;
    }

    private void setIconStyle(String pageIcon) {
	if (currentIconStyle != null) {
	    display.removeIconStyle(currentIconStyle);
	}
	currentIconStyle = pageIcon;
	display.addIconStyle(currentIconStyle);
    }

    private void visibilityChanged(Visibility visibility) {
	if (currentStyle != null)
	    display.removeStyle(currentStyle);
	String newStyle = visibility.toString();
	display.addStyle(newStyle);
	currentStyle = newStyle;
    }

}
