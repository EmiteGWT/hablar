package com.calclab.hablar.chat.client.ui.chatmessageformat;


/**
 * Triad of a string pattern, an pattern element factory and the replacement
 * of found patterns in the messages.
 */
public class PatternFactoryReplacement {
	String pattern;

	PatternElementFactory factory;

	String replacement;

	public PatternFactoryReplacement(String pattern,
			PatternElementFactory factory, String replacement) {
		this.pattern = pattern;
		this.factory = factory;
		this.replacement = replacement;
	}
}