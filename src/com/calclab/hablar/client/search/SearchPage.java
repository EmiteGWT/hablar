package com.calclab.hablar.client.search;

import static com.google.gwt.dom.client.Style.Unit.PX;

import com.calclab.emite.xep.search.client.Item;
import com.calclab.hablar.client.pages.HeaderStyles;
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
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class SearchPage extends PageWidget {

    interface HeaderStyle extends HeaderStyles {

    }

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
    LayoutPanel self;

    @UiField
    ScrollPanel scroll;

    @UiField
    FlowPanel results, messagePanel;

    @UiField
    public Styles style;

    @UiField
    HeaderStyle header;

    @UiField
    TextBox term;

    @UiField
    Label message;

    public SearchPage() {
	super(false);
	initWidget(uiBinder.createAndBindUi(this));
	logic = new SearchLogic(this);
	setHeaderTitle("Search users");
	setHeaderStyles(header);
    }

    public void addResult(Item item) {
	results.add(new SearchResult(item));
    }

    public void clearResults() {
	results.clear();
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
	self.setWidgetTopHeight(messagePanel, 0, PX, 25, PX);
	self.setWidgetTopBottom(scroll, 33, PX, 33, PX);
	self.animate(250);

	message.setText(body);
	message.getElement().addClassName(style);
    }
}
