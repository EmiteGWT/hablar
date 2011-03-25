package com.calclab.hablar.roster.client.selection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.Mockito;

import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.core.client.xmpp.stanzas.Presence.Show;
import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.hablar.roster.client.groups.RosterItemDisplay;

/**
 * Test case for the {@link RosterItemSelectable} class
 */
public class RosterItemSelectableTests {

    private static String TEST_JID = "a@b.com";

    @Test
    public void testCompareToAvailableSortsHigherThanAway() {
	testCompareTo(-1, "", TEST_JID, Show.chat, "", TEST_JID, Show.away);
    }

    @Test
    public void testCompareToDNDSortsHigherThanAway() {
	testCompareTo(-1, "", TEST_JID, Show.dnd, "", TEST_JID, Show.away);
    }

    @Test
    public void testCompareToAwaySortsHigherThanUnknown() {
	testCompareTo(-1, "", TEST_JID, Show.dnd, "", TEST_JID, Show.unknown);
    }

    @Test
    public void testCompareToNameSort() {
	testCompareTo(-1, "A Name", TEST_JID, Show.chat, "B Name", TEST_JID, Show.chat);
	testCompareTo(-1, "B Name", TEST_JID, Show.chat, "C Name", TEST_JID, Show.chat);
	testCompareTo(-1, "A Name", TEST_JID, Show.chat, "C Name", TEST_JID, Show.chat);
	testCompareTo(1, "B Name", TEST_JID, Show.chat, "A Name", TEST_JID, Show.chat);
	testCompareTo(1, "C Name", TEST_JID, Show.chat, "B Name", TEST_JID, Show.chat);
	testCompareTo(1, "C Name", TEST_JID, Show.chat, "A Name", TEST_JID, Show.chat);
    }

    @Test
    public void testCompareToNameSortsHigherThanNullName() {
	testCompareTo(-1, "A Name", TEST_JID, Show.chat, null, TEST_JID, Show.chat);
	testCompareTo(1, null, TEST_JID, Show.chat, "A Name", TEST_JID, Show.chat);
    }

    @Test
    public void testCompareToJIDSort() {
	testCompareTo(-1, "", "ajid@jid.com", Show.chat, "", "bjid@jid.com", Show.chat);
	testCompareTo(-1, "", "bjid@jid.com", Show.chat, "", "cjid@jid.com", Show.chat);
	testCompareTo(-1, "", "ajid@jid.com", Show.chat, "", "cjid@jid.com", Show.chat);
    }

    @Test
    public void testCompareToNullWhenNotEquals() {
	int actual = getCompareTo(null, TEST_JID, Show.chat, null, TEST_JID, Show.chat);
	
	assertTrue("Zero returned for different objects", actual != 0);
    }

    @Test
    public void testCompareToNullWhenEquals() {
	RosterItem user = Mockito.mock(RosterItem.class);
	RosterItemDisplay widget = Mockito.mock(RosterItemDisplay.class);

	when(user.getJID()).thenReturn(XmppURI.jid(TEST_JID));

	RosterItemSelectable item = new RosterItemSelectable(user, widget);

	int actual = item.compareTo(item);
	
	assertTrue("Zero not returned for equal objects", actual == 0);
    }
    
    /**
     * 
     * @param sign
     *            -1, 0, or 1 depending on whether user1 should compare lower,
     *            equals or higher than user2
     * @param user1Name
     *            user 1's name
     * @param user1Jid
     *            user 1's jid
     * @param user1Show
     *            user 1's status
     * @param user2Name
     *            user 2's name
     * @param user2Jid
     *            user 2's jid
     * @param user2Show
     *            user 2's status
     */
    private void testCompareTo(int expected, String user1Name, String user1Jid, Show user1Show, String user2Name,
	    String user2Jid, Show user2Show) {

	int actual = getCompareTo(user1Name, user1Jid, user1Show, user2Name, user2Jid, user2Show);

	assertEquals(String.format("Wrong signum returned for [%s, %s, %s] [%s, %s, %s]", user1Name, user1Jid,
		user1Show, user2Name, user2Jid, user2Show), Integer.signum(expected), Integer.signum(actual));
    }

    /**
     * 
     * @param user1Name
     *            user 1's name
     * @param user1Jid
     *            user 1's jid
     * @param user1Show
     *            user 1's status
     * @param user2Name
     *            user 2's name
     * @param user2Jid
     *            user 2's jid
     * @param user2Show
     *            user 2's status
     */
    private int getCompareTo(String user1Name, String user1Jid, Show user1Show, String user2Name, String user2Jid,
	    Show user2Show) {
	RosterItem user1 = Mockito.mock(RosterItem.class);
	RosterItemDisplay widget1 = Mockito.mock(RosterItemDisplay.class);

	RosterItem user2 = Mockito.mock(RosterItem.class);
	RosterItemDisplay widget2 = Mockito.mock(RosterItemDisplay.class);

	when(user1.getName()).thenReturn(user1Name);
	when(user1.getJID()).thenReturn(XmppURI.jid(user1Jid));
	when(user1.getShow()).thenReturn(user1Show);

	when(user2.getName()).thenReturn(user2Name);
	when(user2.getJID()).thenReturn(XmppURI.jid(user2Jid));
	when(user2.getShow()).thenReturn(user2Show);

	RosterItemSelectable item1 = new RosterItemSelectable(user1, widget1);
	RosterItemSelectable item2 = new RosterItemSelectable(user2, widget2);

	return item1.compareTo(item2);
    }
}
