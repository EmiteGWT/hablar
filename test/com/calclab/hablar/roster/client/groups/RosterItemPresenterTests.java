/**
 * 
 */
package com.calclab.hablar.roster.client.groups;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.core.client.xmpp.stanzas.Presence.Show;
import com.calclab.emite.core.client.xmpp.stanzas.Presence.Type;
import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.emite.im.client.roster.SubscriptionState;
import com.calclab.hablar.core.client.ui.menu.Menu;
import com.calclab.hablar.roster.client.RosterConfig;
import com.calclab.hablar.testing.display.HasClickHandlersStub;
import com.calclab.hablar.testing.display.HasTextStub;

/**
 * @author Ash
 * 
 */
public class RosterItemPresenterTests {

    private static final String GROUP_NAME = "groupname";

    @Mock
    private RosterItemDisplay display;

    @Mock
    private Menu<RosterItemPresenter> itemMenu;

    private RosterConfig rosterConfig;

    /**
     * The class under test.
     */
    private RosterItemPresenter presenter;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
	MockitoAnnotations.initMocks(this);

	when(display.getMenuAction()).thenReturn(new HasClickHandlersStub());
	when(display.getAction()).thenReturn(new HasClickHandlersStub());
	when(display.getName()).thenReturn(new HasTextStub());
	when(display.getJid()).thenReturn(new HasTextStub());

	rosterConfig = new RosterConfig();
	presenter = new RosterItemPresenter(GROUP_NAME, itemMenu, display, rosterConfig);
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
	display = null;
	itemMenu = null;
	rosterConfig = null;
	presenter = null;
    }

    /**
     * Test method for
     * {@link com.calclab.hablar.roster.client.groups.RosterItemPresenter#setItem(com.calclab.emite.im.client.roster.RosterItem)}
     * . Tests the return value if the roster item hasn't changed.
     */
    @Test
    public void testSetItemReturnDifferentName() {
	final RosterItem item = new RosterItem(XmppURI.jid("test@test"), SubscriptionState.both, "name",
		Type.subscribed);
	assertTrue(presenter.setItem(item));

	item.setName("new name");
	assertTrue(presenter.setItem(item));
    }

    /**
     * Test method for
     * {@link com.calclab.hablar.roster.client.groups.RosterItemPresenter#setItem(com.calclab.emite.im.client.roster.RosterItem)}
     * . Tests the return value if the roster item hasn't changed.
     */
    @Test
    public void testSetItemReturnDifferentObject() {
	final RosterItem item = new RosterItem(XmppURI.jid("test@test"), SubscriptionState.both, "name",
		Type.subscribed);
	final RosterItem item2 = new RosterItem(XmppURI.jid("test@test"), SubscriptionState.both, "name",
		Type.subscribed);

	assertTrue(presenter.setItem(item));
	assertFalse(presenter.setItem(item2));
    }

    /**
     * Test method for
     * {@link com.calclab.hablar.roster.client.groups.RosterItemPresenter#setItem(com.calclab.emite.im.client.roster.RosterItem)}
     * . Tests the return value if the roster item hasn't changed.
     */
    @Test
    public void testSetItemReturnDifferentShow() {
	final RosterItem item = new RosterItem(XmppURI.jid("test@test"), SubscriptionState.both, "name",
		Type.subscribed);
	assertTrue(presenter.setItem(item));

	item.setShow(Show.dnd);
	assertTrue(presenter.setItem(item));
    }

    /**
     * Test method for
     * {@link com.calclab.hablar.roster.client.groups.RosterItemPresenter#setItem(com.calclab.emite.im.client.roster.RosterItem)}
     * . Tests the return value if the roster item hasn't changed.
     */
    @Test
    public void testSetItemReturnNotChanged() {
	final RosterItem item = new RosterItem(XmppURI.jid("test@test"), SubscriptionState.both, "name",
		Type.subscribed);

	assertTrue(presenter.setItem(item));
	assertFalse(presenter.setItem(item));
    }

    /**
     * Test method for
     * {@link com.calclab.hablar.roster.client.groups.RosterItemPresenter#setItem(com.calclab.emite.im.client.roster.RosterItem)}
     * . Tests the return value if the roster item hasn't changed when the name
     * is null.
     */
    @Test
    public void testSetItemReturnNotChangedNullName() {
	final RosterItem item = new RosterItem(XmppURI.jid("test@test"), SubscriptionState.both, null, Type.subscribed);

	assertTrue(presenter.setItem(item));
	assertFalse(presenter.setItem(item));
    }
}
