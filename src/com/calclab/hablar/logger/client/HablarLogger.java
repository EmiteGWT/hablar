package com.calclab.hablar.logger.client;

import com.calclab.hablar.core.client.Hablar;
import com.calclab.hablar.core.client.container.PageAddedEvent;
import com.calclab.hablar.core.client.container.PageAddedHandler;
import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.page.PagePresenter.Visibility;
import com.calclab.hablar.core.client.ui.icon.HablarIcons;
import com.calclab.hablar.core.client.ui.icon.HablarIcons.IconType;
import com.calclab.hablar.roster.client.RosterPage;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class HablarLogger implements EntryPoint {

    @Override
    public void onModuleLoad() {
    }

    public static void install(Hablar hablar) {
	final LoggerPage loggerPage = new LoggerPage(hablar.getEventBus(), new LoggerWidget());
	hablar.addPage(loggerPage);
	
	hablar.addPageAddedHandler(new PageAddedHandler() {
	    @Override
	    public void onPageAdded(PageAddedEvent event) {
		Page<?> page = event.getPage();
		if (RosterPage.TYPE.equals(page.getType())) {
		    ((RosterPage) page).addAction(HablarIcons.get(IconType.buddyWait), "hablar-LoggerAction", new ClickHandler() {
		        @Override
		        public void onClick(ClickEvent event) {
		            loggerPage.requestVisibility(Visibility.focused);
		        }
		    });
		}
	    }
	}, true);
    }

}
