package com.calclab.hablar.core.client.ui.emoticons;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface Emoticons extends ClientBundle {

    // This better using Suco
    public static class App {
	private static Emoticons ourInstance = null;

	public static synchronized Emoticons getInst() {
	    if (ourInstance == null) {
		ourInstance = (Emoticons) GWT.create(Emoticons.class);
	    }
	    return ourInstance;
	}
    }

    @Source("png/alien.png")
    ImageResource alien();

    @Source("png/andy.png")
    ImageResource andy();

    @Source("png/angel.png")
    ImageResource angel();

    @Source("png/angry.png")
    ImageResource angry();

    @Source("png/bandit.png")
    ImageResource bandit();

    @Source("png/blushing.png")
    ImageResource blushing();

    @Source("png/bullet_black.png")
    ImageResource bulletBlack();

    @Source("png/bullet_star.png")
    ImageResource bulletStar();

    @Source("png/cool.png")
    ImageResource cool();

    @Source("png/crying.png")
    ImageResource crying();

    @Source("emoticons.css")
    EmoticonStyles css();

    @Source("png/devil.png")
    ImageResource devil();

    @Source("png/grin.png")
    ImageResource grin();

    @Source("png/happy.png")
    ImageResource happy();

    @Source("png/heart.png")
    ImageResource heart();

    @Source("png/joyful.png")
    ImageResource joyful();

    @Source("png/kissing.png")
    ImageResource kissing();

    @Source("png/lol.png")
    ImageResource lol();

    @Source("png/love.png")
    ImageResource love();

    @Source("png/ninja.png")
    ImageResource ninja();

    @Source("png/pinched.png")
    ImageResource pinched();

    @Source("png/policeman.png")
    ImageResource policeman();

    @Source("png/pouty.png")
    ImageResource pouty();

    @Source("png/sad.png")
    ImageResource sad();

    @Source("png/sick.png")
    ImageResource sick();

    @Source("png/sideways.png")
    ImageResource sideways();

    @Source("png/sleeping.png")
    ImageResource sleeping();

    @Source("png/smile.png")
    ImageResource smile();

    @Source("png/surprised.png")
    ImageResource surprised();

    @Source("png/tongue.png")
    ImageResource tongue();

    @Source("png/uncertain.png")
    ImageResource uncertain();

    @Source("png/unsure.png")
    ImageResource unsure();

    @Source("png/w00t.png")
    ImageResource w00t();

    @Source("png/whistling.png")
    ImageResource whistling();

    @Source("png/wink.png")
    ImageResource wink();

    @Source("png/wizard.png")
    ImageResource wizard();

    @Source("png/wondering.png")
    ImageResource wondering();

}
