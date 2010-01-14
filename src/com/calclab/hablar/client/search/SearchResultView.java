package com.calclab.hablar.client.search;

import com.calclab.emite.xep.search.client.SearchResultItem;

public interface SearchResultView {

    SearchResultItem getItem();

    void setAddToRosterVisible(boolean visible);

}
