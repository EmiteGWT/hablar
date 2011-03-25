package com.calclab.hablar.selenium.vcard;

import org.openqa.selenium.RenderedWebElement;

public interface VCardPageObject {

    public RenderedWebElement getEmail();

    public RenderedWebElement getFamilyName();

    public RenderedWebElement getGivenName();

    public RenderedWebElement getHomepage();

    public RenderedWebElement getMiddleName();

    public RenderedWebElement getName();

    public RenderedWebElement getNickName();

    public RenderedWebElement getOrganizationName();

}
