package com.calclab.hablar.search.client;

import com.calclab.hablar.basic.client.ui.HablarView;
import com.calclab.hablar.basic.client.ui.icons.HablarIcons;
import com.calclab.hablar.basic.client.ui.icons.HablarIcons.IconType;
import com.calclab.hablar.basic.client.ui.page.Page.Visibility;
import com.calclab.hablar.basic.client.ui.pages.Pages;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class HablarSearch {

    public static void install(HablarView view) {
	final Pages pages = view.getPages();
	final SearchPageWidget page = new SearchPageWidget(Visibility.hidden);
	pages.add(page);

	String iconStyle = HablarIcons.get(IconType.search);
	String debugId = "HablarLogic-searchAction";

	view.getRosterPage().addAction(iconStyle, debugId, new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		pages.open(page);
	    }
	});

    }
}
