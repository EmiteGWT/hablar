package com.calclab.hablar.vcard.client;

import com.calclab.hablar.core.client.Hablar;
import com.calclab.hablar.core.client.container.PageAddedEvent;
import com.calclab.hablar.core.client.container.PageAddedHandler;
import com.calclab.hablar.core.client.container.overlay.OverlayContainer;
import com.calclab.hablar.core.client.page.PagePresenter.Visibility;
import com.calclab.hablar.core.client.ui.menu.Action;
import com.calclab.hablar.core.client.ui.menu.SimpleAction;
import com.calclab.hablar.roster.client.groups.RosterItemPresenter;
import com.calclab.hablar.roster.client.page.RosterPage;
import com.calclab.hablar.roster.client.page.RosterPresenter;
import com.calclab.hablar.user.client.UserContainer;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

public class HablarVCard implements EntryPoint {

    private static VCardMessages messages;

    public static void setMessages(final VCardMessages messages) {
	HablarVCard.messages = messages;
    }

    public static VCardMessages i18n() {
	return messages;
    }

    private static final String ACTION_ID_VIEW_VCARD = "HablarVCard-seeVCardAction";

    public static void install(final Hablar hablar) {
	final OwnVCardPresenter ownVCardPage = new OwnVCardPresenter(hablar.getEventBus(), new VCardWidget(false,
		"OwnVCardWidget"));
	hablar.addPage(ownVCardPage, UserContainer.ROL);

	final OthersVCardPresenter othersVCardPage = new OthersVCardPresenter(hablar.getEventBus(),
		new OtherVCardWidget(true));
	hablar.addPage(othersVCardPage, OverlayContainer.ROL);

	hablar.addPageAddedHandler(new PageAddedHandler() {
	    @Override
	    public void onPageAdded(final PageAddedEvent event) {
		final RosterPage rosterPage = RosterPresenter.asRoster(event.getPage());
		if (rosterPage != null) {
		    rosterPage.getItemMenu().addAction(createViewVCardAction(othersVCardPage));
		}
	    }
	}, true);
    }

    protected static Action<RosterItemPresenter> createViewVCardAction(final OthersVCardPresenter othersVCardPage) {
	return new SimpleAction<RosterItemPresenter>(i18n().seeUserProfileAction(), ACTION_ID_VIEW_VCARD) {
	    @Override
	    public void execute(final RosterItemPresenter target) {
		othersVCardPage.setUser(target.getItem().getJID());
		othersVCardPage.requestVisibility(Visibility.focused);
	    }
	};
    }

    @Override
    public void onModuleLoad() {
	final VCardMessages messages = GWT.create(VCardMessages.class);
	I18nVCard.set(messages);
	VCardWidget.setMessages(messages);
	HablarVCard.setMessages(messages);
    }

}
