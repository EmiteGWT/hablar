package com.calclab.hablar.search.client;

import com.google.gwt.i18n.client.Messages;
import com.google.gwt.i18n.client.LocalizableResource.DefaultLocale;

@DefaultLocale("en")
public interface SearchMessages extends Messages {

    @DefaultMessage("Add to Contacts")
    String addToContacts();

    @DefaultMessage("Chat")
    String chat();

    @DefaultMessage("Search users")
    String openSearchPage();

    @DefaultMessage("Results for «{0}»: {1} users found.")
    @Description("Results for a users search")
    @PluralText( { "one", "Results for «{0}»: One user found." })
    String searchResultsFor(String term, @PluralCount @Optional int count);

    @DefaultMessage("Search users")
    String searchUsers();
}
