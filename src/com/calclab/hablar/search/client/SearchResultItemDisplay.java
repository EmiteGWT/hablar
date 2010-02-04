package com.calclab.hablar.search.client;

import com.calclab.emite.xep.search.client.SearchResultItem;
import com.calclab.hablar.core.client.mvp.Display;

public interface SearchResultItemDisplay extends Display {

    void setItem(SearchResultItem item);
}
