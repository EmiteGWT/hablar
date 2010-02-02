package com.calclab.hablar.basic.client.i18n;

import com.google.gwt.i18n.client.Messages;
import com.google.gwt.i18n.client.LocalizableResource.DefaultLocale;
import com.google.gwt.i18n.client.LocalizableResource.GenerateKeys;

/**
 * 
 * Emite i18n messages
 * 
 * @see <a href=
 *      'http://code.google.com/intl/es-ES/webtoolkit/doc/latest/DevGuideI18n.ht
 *      m l # D e v G u i d e A n n o t a t i o n s ' > I18nAnnotations</a>
 * @see <a href=
 *      'http://code.google.com/intl/en/webtoolkit/doc/latest/DevGuideUiBinderI1
 *      8 n . h t m l ' >UiBinderAnnotations</a>
 */

@DefaultLocale("en")
// Line below defaults to I18N_default.xlf, I18N_de.xlf, etc
// GenerateKeys defaults to MD5 hash of text and meaning
@GenerateKeys
public interface Msg extends Messages {

    @DefaultMessage("Add to Contacts")
    String addToContacts();

    @DefaultMessage("Change nick name")
    String changeNickName();

    @DefaultMessage("Write the new nick name:")
    String changeNickNameMessage();

    @DefaultMessage("Chat")
    String chat();

    @DefaultMessage("Connected as {0}")
    @Description("The specified user has connected")
    String connectedAs(@Example("john.doe") String userName);

    @DefaultMessage("Contacts")
    @Description("The roster panel title")
    String contacts();

    @DefaultMessage("Disconnected")
    String disconnected();

    @DefaultMessage("Sign in")
    String login();

    @DefaultMessage("Sign out")
    String logout();

    @DefaultMessage("User `{0}` says «{1}»")
    String newChatFrom(String user, String msg);

    @DefaultMessage("Remove from Contacts")
    String removeFromContacts();

    @DefaultMessage("Sign in to see the contacts")
    String rosterDisabled();

    @DefaultMessage("Results for «{0}»: {1} users found.")
    @Description("Results for a users search")
    @PluralText( { "one", "Results for «{0}»: One user found." })
    String searchResultsFor(String term, @PluralCount @Optional int count);

    @DefaultMessage("Type to search users")
    String typeToSearchUsers();

    @DefaultMessage("{0} chats unread")
    @PluralText( { "one", "1 chat unread" })
    String unreadChats(@PluralCount int users);

    @DefaultMessage("Wait...")
    String waitDots();

}