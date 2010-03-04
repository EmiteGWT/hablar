package com.calclab.hablar.selenium.tools;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import com.calclab.hablar.selenium.chat.ChatPageObject;
import com.calclab.hablar.selenium.editbuddy.EditBuddyPageObject;
import com.calclab.hablar.selenium.groupchat.GroupChatPageObject;
import com.calclab.hablar.selenium.login.LoginPageObject;
import com.calclab.hablar.selenium.openchat.OpenChatPageObject;
import com.calclab.hablar.selenium.roster.RosterPageObject;
import com.calclab.hablar.selenium.search.SearchPageObject;
import com.calclab.suco.client.ioc.decorator.Singleton;
import com.calclab.suco.client.ioc.module.Factory;

public class SeleniumModule extends PageObjectModule {

    public SeleniumModule() {
    }

    @Override
    protected void onInstall() {

	register(Singleton.class, new Factory<WebDriver>(WebDriver.class) {
	    @Override
	    public WebDriver create() {
		return new FirefoxDriver(SeleniumConstants.FIREFOX_PROFILE_NAME);
	    }
	});

	register(Singleton.class, new Factory<GenericWebTester>(GenericWebTester.class) {
	    @Override
	    public GenericWebTester create() {
		return new GenericWebTester($(WebDriver.class),
			"http://localhost:8888/HablarSelenium.html?gwt.codesvr=127.0.0.1:9997");
	    }
	});

	register(Singleton.class, new Factory<ElementLocatorFactory>(ElementLocatorFactory.class) {
	    @Override
	    public ElementLocatorFactory create() {
		// The number is the timeout
		return new AjaxElementLocatorFactory($(WebDriver.class), SeleniumConstants.TIMEOUT);
	    }
	});

	registerPageObject(LoginPageObject.class, new LoginPageObject());
	registerPageObject(SearchPageObject.class, new SearchPageObject());
	registerPageObject(OpenChatPageObject.class, new OpenChatPageObject());
	registerPageObject(GroupChatPageObject.class, new GroupChatPageObject());
	registerPageObject(RosterPageObject.class, new RosterPageObject());
	registerPageObject(ChatPageObject.class, new ChatPageObject());
	registerPageObject(EditBuddyPageObject.class, new EditBuddyPageObject());

    }
}
