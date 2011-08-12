package com.calclab.hablar.search.client.page;

import com.calclab.emite.xep.search.client.SearchResultItem;
import com.calclab.hablar.core.client.mvp.Presenter;
import com.calclab.hablar.core.client.ui.menu.Menu;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class SearchResultItemPresenter implements Presenter<SearchResultItemDisplay> {

	private final SearchResultItemDisplay display;
	private final SearchResultItem item;

	public SearchResultItemPresenter(final SearchResultItem item, final Menu<SearchResultItem> itemMenu, final SearchResultItemDisplay display) {
		this.item = item;
		this.display = display;
		display.getName().setText(item.getNick());
		display.getJid().setText(item.getJid().toString());
		ClickHandler handler = new ClickHandler() {
			@Override
			public void onClick(final ClickEvent event) {
				event.preventDefault();
				final Element element = event.getRelativeElement();
				itemMenu.setTarget(item);
				itemMenu.show(element.getAbsoluteLeft(), element.getAbsoluteTop());
			}
		};
		display.getMenu().addClickHandler(handler);
		display.getBuddyIcon().addClickHandler(handler);
		display.getClickableJid().addClickHandler(handler);
		display.getClickableName().addClickHandler(handler);
	}

	@Override
	public SearchResultItemDisplay getDisplay() {
		return display;
	}

	public SearchResultItem getItem() {
		return item;
	}

}
