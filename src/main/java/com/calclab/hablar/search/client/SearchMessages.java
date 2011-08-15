package com.calclab.hablar.search.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;
import com.google.gwt.i18n.client.LocalizableResource.DefaultLocale;

@DefaultLocale("en")
public interface SearchMessages extends Messages {
	public static final SearchMessages msg = GWT.create(SearchMessages.class);

	@DefaultMessage("Add to Contacts")
	String addToContacts();

	@DefaultMessage("Chat")
	String chat();

	@DefaultMessage("Search users")
	String openSearchPage();

	@DefaultMessage("Remove Contact")
	String removeFromContacts();

	@Description("Results for a users search")
	@DefaultMessage("Results for «{0}»: {1} users found.")
	@AlternateMessage({ "one", "Results for «{0}»: One user found." })
	String searchResultsFor(String term, @PluralCount @Optional int count);

	@DefaultMessage("Search users")
	String searchUsers();

	@DefaultMessage("Couldn''t retrieve results")
	String searchError();

	@DefaultMessage("Searching {0}...")
	String searchingTerm(String term);

	@DefaultMessage("Search")
	String searchAction();

	@DefaultMessage("Type the name, or part of it, and press \"Search\"")
	String searchTooltip();
}
