package com.calclab.hablar.client.search;

import com.calclab.emite.xep.search.client.Item;
import com.calclab.hablar.client.pages.PageWidget;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class SearchPage extends PageWidget {

    interface SearchPageUiBinder extends UiBinder<Widget, SearchPage> {
    }

    interface Styles extends CssResource {
	String alert();

	String focus();

	String info();

	String success();

	String unfocus();

    }

    private static SearchPageUiBinder uiBinder = GWT.create(SearchPageUiBinder.class);
    private final SearchLogic logic;

    @UiField
    FlowPanel results;

    @UiField
    public Styles style;

    @UiField
    TextBox term;

    @UiField
    Label message;

    public SearchPage() {
	super(false);
	initWidget(uiBinder.createAndBindUi(this));
	logic = new SearchLogic(this);
	setHeaderTitle("Search users");
    }

    public void addResult(Item item) {
	results.add(new SearchResult(item));
    }

    @UiHandler("term")
    public void onFocus(FocusEvent evt) {
	term.setText("");
	term.getElement().removeClassName(style.unfocus());
	term.getElement().addClassName(style.focus());
    }

    @UiHandler("term")
    public void onSearch(ChangeEvent evt) {
	logic.search(term.getText());
	term.setText("");
    }

    public void showMessage(String body, String style) {
	message.setText(body);
	message.getElement().addClassName(style);
    }

}
