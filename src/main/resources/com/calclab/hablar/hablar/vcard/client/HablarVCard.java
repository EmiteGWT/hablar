package com.calclab.hablar.vcard.client;

import com.calclab.emite.core.client.xmpp.session.XmppSession;
import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.emite.im.client.roster.XmppRoster;
import com.calclab.emite.im.client.roster.events.RosterItemChangedEvent;
import com.calclab.emite.im.client.roster.events.RosterItemChangedHandler;
import com.calclab.emite.xep.vcard.client.VCard;
import com.calclab.emite.xep.vcard.client.VCardManager;
import com.calclab.emite.xep.vcard.client.VCardResponse;
import com.calclab.emite.xep.vcard.client.events.VCardResponseEvent;
import com.calclab.emite.xep.vcard.client.events.VCardResponseHandler;
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
import com.calclab.suco.client.Suco;

public class HablarVCard {

    private static VCardMessages messages;

    private static final String ACTION_ID_VIEW_VCARD = "HablarVCard-seeVCardAction";

    public static VCardMessages i18n() {
	return messages;
    }

    public static void setMessages(final VCardMessages messages) {
	HablarVCard.messages = messages;
    }

    private final Hablar hablar;

    private final VCardConfig vCardConfig;

    private final XmppRoster roster;

    private final VCardManager vCardManager;

    private final XmppSession session;

    // FIXME: move to gin
    @SuppressWarnings("deprecation")
    public HablarVCard(final Hablar hablar, final VCardConfig vCardConfig) {
	this.hablar = hablar;
	this.vCardConfig = vCardConfig;
	session = Suco.get(XmppSession.class);
	roster = Suco.get(XmppRoster.class);
	vCardManager = Suco.get(VCardManager.class);
	install();
    }

    private void install() {
	final VCardWidget vCardWidget = new VCardWidget(vCardConfig.vCardReadOnly, "OwnVCardWidget");
	final OwnVCardPresenter ownVCardPage = new OwnVCardPresenter(session, vCardManager, hablar.getEventBus(),
		vCardWidget);
	hablar.addPage(ownVCardPage, UserContainer.ROL);

	final OthersVCardPresenter othersVCardPage = new OthersVCardPresenter(vCardManager, hablar.getEventBus(),
		new OtherVCardWidget(true));
	hablar.addPage(othersVCardPage, OverlayContainer.ROL);

	hablar.addPageAddedHandler(new PageAddedHandler() {
	    @Override
	    public void onPageAdded(final PageAddedEvent event) {
		final RosterPage rosterPage = RosterPresenter.asRoster(event.getPage());
		if (rosterPage != null) {
		    rosterPage.getItemMenu().addAction(newViewVCardAction(othersVCardPage));
		}
	    }
	}, true);

	prepareDefaultNicknameListener();
    }

    private Action<RosterItemPresenter> newViewVCardAction(final OthersVCardPresenter othersVCardPage) {
	return new SimpleAction<RosterItemPresenter>(i18n().seeUserProfileAction(), ACTION_ID_VIEW_VCARD) {
	    @Override
	    public void execute(final RosterItemPresenter target) {
		othersVCardPage.setUser(target.getItem().getJID());
		othersVCardPage.requestVisibility(Visibility.focused);
	    }
	};
    }

    private void prepareDefaultNicknameListener() {

	roster.addRosterItemChangedHandler(new RosterItemChangedHandler() {

	    @Override
	    public void onRosterItemChanged(final RosterItemChangedEvent event) {
		if (event.isAdded()) {
		    final RosterItem rosterItem = event.getRosterItem();
		    final String itemName = rosterItem.getName();
		    final XmppURI jid = rosterItem.getJID();
		    if ((itemName == null) || "".equals(itemName) || itemName.equals(jid.getNode())) {

			vCardManager.getUserVCard(jid, new VCardResponseHandler() {
			    @Override
			    public void onVCardResponse(final VCardResponseEvent event) {
				final VCardResponse vCardResponse = event.getVCardResponse();
				final VCard vcard = vCardResponse != null ? vCardResponse.getVCard() : null;
				if (vcard != null) {
				    rosterItem.setName(vcard.getDisplayName());
				    roster.requestUpdateItem(rosterItem);
				}
			    }
			});

		    }

		}
	    }
	});
    }

}
