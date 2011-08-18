package com.calclab.hablar.chat.client.ui.chatmessageformat;

import java.util.List;

import com.calclab.emite.core.client.packet.TextUtils;
import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;

/**
 * Adds the standard replacements for chat messages including transforming newlines and urls.
 */
public class StandardChatMessageFormatReplacements implements ChatMessageFormatReplacements {
	private static final String NEWLINE = "EmiteProtIniNEWLINEEmiteProtEnd";
	private static final String ANCHOR = "EmiteProtIniANCHOREmiteProtEnd";

	/**
	 * Generates a &lt;br&gt; element.
	 */
	private class NewlinePatternElementFactory implements
			PatternElementFactory {

		@Override
		public Element create(String originalText) {
			Document doc = Document.get();
			return doc.createBRElement();
		}
	}

	/**
	 * Generates an anchor, given the URL to point to.
	 */
	private class AnchorPatternElementFactory implements
			PatternElementFactory {

		@Override
		public Element create(String originalText) {
			Document doc = Document.get();
			AnchorElement element = doc.createAnchorElement();
			element.setHref(originalText);
			element.setTarget("_blank");
			element.appendChild(doc.createTextNode(originalText));
			return element;
		}
	}

	@Override
	public void addReplacements(final List<PatternFactoryReplacement> replacements) {
		replacements.add(new PatternFactoryReplacement("\\r\\n",
				new NewlinePatternElementFactory(), NEWLINE));
		replacements.add(new PatternFactoryReplacement("\\r|\\n",
				new NewlinePatternElementFactory(), NEWLINE));
		replacements
				.add(new PatternFactoryReplacement(TextUtils.URL_REGEXP,
						new AnchorPatternElementFactory(), ANCHOR));
	}

}
