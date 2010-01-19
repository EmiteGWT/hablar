package com.calclab.hablar.basic.client.ui;

import com.calclab.hablar.basic.client.login.LoginView;
import com.calclab.hablar.basic.client.roster.RosterView;
import com.calclab.hablar.basic.client.ui.page.PageView;
import com.calclab.hablar.basic.client.ui.pages.Pages;

public interface HablarView {

    LoginView getLoginPage();

    Pages getPages();

    RosterView getRosterPage();

    boolean hasLogin();

    boolean hasRoster();

    void setDocked(PageView pageView, int size);

}
