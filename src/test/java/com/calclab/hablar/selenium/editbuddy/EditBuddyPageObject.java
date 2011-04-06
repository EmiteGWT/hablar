package com.calclab.hablar.selenium.editbuddy;

import org.openqa.selenium.RenderedWebElement;
import org.openqa.selenium.support.FindBy;

import com.calclab.hablar.selenium.PageObject;

public class EditBuddyPageObject extends PageObject {

    @FindBy(id = "gwt-debug-EditBuddyWidget-nickname")
    private RenderedWebElement newNickname;
    @FindBy(id = "gwt-debug-EditBuddyWidget-save")
    private RenderedWebElement save;

    public RenderedWebElement getNewNickName() {
	return newNickname;
    }

    public RenderedWebElement getSave() {
	return save;
    }
}
