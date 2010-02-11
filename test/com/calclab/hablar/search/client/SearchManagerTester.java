package com.calclab.hablar.search.client;

import java.util.HashMap;
import java.util.List;

import com.calclab.emite.core.client.xmpp.session.ResultListener;
import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.xep.dataforms.client.Form;
import com.calclab.emite.xep.search.client.SearchFields;
import com.calclab.emite.xep.search.client.SearchManager;
import com.calclab.emite.xep.search.client.SearchResultItem;

public class SearchManagerTester implements SearchManager {

    private XmppURI host;
    private HashMap<String, String> query;
    private ResultListener<List<SearchResultItem>> listener;

    public void fireSearchSuccess(List<SearchResultItem> results) {
	listener.onSuccess(results);
    }

    public XmppURI getHost() {
	return host;
    }

    public HashMap<String, String> getLastQuery() {
	return query;
    }

    @Override
    public void requestSearchFields(ResultListener<SearchFields> listener) {

    }

    @Override
    public void search(HashMap<String, String> query, ResultListener<List<SearchResultItem>> listener) {
	this.query = query;
	this.listener = listener;
    }

    @Override
    public void setHost(XmppURI host) {
	this.host = host;
    }

    @Override
    public void requestSearchForm(ResultListener<Form> listener) {

    }

    @Override
    public void search(Form searchForm, ResultListener<Form> listener) {

    }

}
