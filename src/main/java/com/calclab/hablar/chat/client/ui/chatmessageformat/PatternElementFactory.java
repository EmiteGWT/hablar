package com.calclab.hablar.chat.client.ui.chatmessageformat;

import com.google.gwt.dom.client.Element;

/**
 * It represents a factory of elements, given the original text.
 */
interface PatternElementFactory {
	Element create(String originalText);
}