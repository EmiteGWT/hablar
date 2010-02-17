package com.calclab.hablar.search.client.page;

import static com.google.gwt.dom.client.Style.Unit.PX;

import com.calclab.emite.xep.search.client.SearchResultItem;
import com.calclab.hablar.core.client.ui.menu.MenuDisplay;
import com.calclab.hablar.core.client.ui.menu.PopupMenu;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.HasChangeHandlers;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Focusable;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class SearchWidget extends Composite implements SearchDisplay {

    interface SearchWidgetUiBinder extends UiBinder<Widget, SearchWidget> {
    }

    public static final String ID = "SearchPageWidget";
    public static final String TERM_DEB_ID = "SearchPageWidget-term";
    public static final String MESSAGE_DEB_ID = "SearchPageWidget-message";
    public static final String TYPE = "Search";

    private static SearchWidgetUiBinder uiBinder = GWT.create(SearchWidgetUiBinder.class);

    @UiField
    LayoutPanel self;
    @UiField
    ScrollPanel scroll;
    @UiField
    FlowPanel results, messagePanel;
    @UiField
    TextBox term;
    @UiField
    Label message;
    @UiField
    Button search;

    public SearchWidget() {
	initWidget(uiBinder.createAndBindUi(this));

	term.ensureDebugId("SearchWidget-term");
	message.ensureDebugId("SearchWidget-message");
	search.ensureDebugId("SearchWidget-search");
    }

    @Override
    public void addResult(final SearchResultItemDisplay item) {
	results.add(item.asWidget());
    }

    @Override
    public Widget asWidget() {
	return this;
    }

    public void clearResults() {
	results.clear();
    }

    @Override
    public MenuDisplay<SearchResultItem> createMenu(final String debugId) {
	return new PopupMenu<SearchResultItem>(debugId);
    }

    @Override
    public HasClickHandlers getSearchButton() {
	return search;
    }

    @Override
    public HasChangeHandlers getSearchChange() {
	return term;
    }

    @Override
    public Focusable getSearchFocus() {
	return term;
    }

    @Override
    public HasText getSearchTerm() {
	return term;
    }

    @Override
    public SearchResultItemDisplay newSearchResultItemDisplay() {
	return new SearchResultItemWidget();
    }

    @UiHandler("term")
    public void onFocus(final FocusEvent evt) {
	term.setText("");
	term.getElement().removeClassName("unfocus");
	term.getElement().addClassName("focus");
    }

    @Override
    public void showMessage(final String body, final Level level) {
	self.setWidgetTopHeight(messagePanel, 0, PX, 22, PX);
	self.setWidgetTopBottom(scroll, 22, PX, 33, PX);
	self.animate(250);

	message.setText(body);
	message.getElement().addClassName(level.toString());
    }
}
