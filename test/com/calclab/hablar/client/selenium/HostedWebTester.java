package com.calclab.hablar.client.selenium;

public class HostedWebTester extends AbstractWebTester {

    public HostedWebTester() {
	initUrl("http://localhost:8888/Hablar.html?gwt.codesvr=127.0.1.1:9997");
    }

}
