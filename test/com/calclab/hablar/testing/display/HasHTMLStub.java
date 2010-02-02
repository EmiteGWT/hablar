/**
 * 
 */
package com.calclab.hablar.testing.display;

import com.google.gwt.user.client.ui.HasHTML;

public class HasHTMLStub implements HasHTML {

    private String text;
    private String html;

    @Override
    public String getHTML() {
        return html;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setHTML(String html) {
        this.html = html;

    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

}