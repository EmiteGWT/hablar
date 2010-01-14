package com.calclab.hablar.client.search;

import static com.google.gwt.dom.client.Style.Unit.PX;

import com.calclab.emite.xep.search.client.SearchResultItem;
import com.calclab.hablar.client.ui.page.PageWidget;
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

public class SearchPage extends PageWidget implements SearchView {

    public interface Icons extends CssResource {

	String searchIcon();

    }

    interface SearchPageUiBinder extends UiBinder<Widget, SearchPage> {
    }

    static interface Styles extends CssResource {
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
    public Icons icons;

    @UiField
    TextBox term;

    @UiField
    Label message;

    public SearchPage() {
	super(true);
	initWidget(uiBinder.createAndBindUi(this));
	logic = new SearchLogic(this);
	setHeaderTitle("Search users");
	setHeaderIconClass(icons.searchIcon());
    }

    public void addResult(SearchResultItem item, boolean addToRoster) {
	results.add(new SearchResult(logic, item, addToRoster));
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

    @Override
    public void showMessage(String body, Level level) {
	self.setWidgetTopHeight(messagePanel, 0, PX, 25, PX);
	self.setWidgetTopBottom(scroll, 33, PX, 33, PX);
	self.animate(250);

	message.setText(body);
	message.getElement().addClassName(getStyle(level));
    }

    private String getStyle(Level level) {
	String value = null;
	if (level == Level.success) {
	    value = style.success();
	} else if (level == Level.error) {
	    value = style.alert();
	} else {
	    value = style.success();
	}
	return value;
    }

}
