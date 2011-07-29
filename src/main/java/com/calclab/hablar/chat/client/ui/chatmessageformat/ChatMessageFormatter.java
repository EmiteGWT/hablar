package com.calclab.hablar.chat.client.ui.chatmessageformat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.calclab.emite.core.client.packet.TextUtils;
import com.calclab.hablar.core.client.ui.emoticons.Emoticons;
import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.SpanElement;

/**
 * Some formatting utilities
 */
public class ChatMessageFormatter {

	private static List<PatternFactoryReplacement> patternReplacementPairs = new ArrayList<PatternFactoryReplacement>();

	/**
	 * Adds a {@link PatternFactoryReplacement} to the set of message
	 * replacements.
	 * 
	 * @param replacement
	 *            the replacement to add.
	 */
	public static void addReplacement(
			final PatternFactoryReplacement replacement) {
		patternReplacementPairs.add(replacement);
	}

	/**
	 * Adds all the replacements within a {@link ChatMessageFormatReplacements}
	 * object to the set of message replacements.
	 * 
	 * @param replacements
	 *            the replacements to add.
	 */
	public static void addReplacements(
			final ChatMessageFormatReplacements replacements) {
		replacements.addReplacements(patternReplacementPairs);
	}

	/**
	 * Ellipsis.
	 * 
	 * @param text
	 *            the string to truncate
	 * @param length
	 *            the size (if 0 returns the same text, if null return an empty
	 *            string)
	 * 
	 * @return the result string
	 */
	public static String ellipsis(final String text, final int length) {
		return text == null ? "" : length == 0 ? text
				: text.length() > length ? text.substring(0, length - 3)
						+ "..." : text;
	}

	public static Element format(String name, String message) {
		if (name == null || name.length() <= 0) {
			// Workaround for #207
			message = message.replaceAll(" room ", " group chat ");
			message = message.replaceAll("Room ", "Group chat ");
		}
		List<ReplaceMatch> matchesToReplace = new ArrayList<ReplaceMatch>();
		message = replaceAndFindEmoticonMatches(message, matchesToReplace);
		List<ReplacePosition> positions = findEmoticonPositions(message,
				matchesToReplace);
		SpanElement element = createFormattedMessageElement(message, positions);
		return element;
	}

	private ChatMessageFormatter() {
	}

	private static SpanElement createFormattedMessageElement(String message,
			List<ReplacePosition> positions) {
		Document doc = Document.get();
		SpanElement element = doc.createSpanElement();
		int previousPosition = 0;
		for (ReplacePosition position : positions) {
			element.appendChild(doc.createTextNode(message.substring(
					previousPosition, position.start)));
			element.appendChild(position.image.create(position.text));
			previousPosition = position.end;
		}
		if (previousPosition < message.length()) {
			element.appendChild(doc.createTextNode(message
					.substring(previousPosition)));
		}
		return element;
	}

	private static List<ReplacePosition> findEmoticonPositions(String message,
			List<ReplaceMatch> matchesToReplace) {
		List<ReplacePosition> positions = new ArrayList<ReplacePosition>();
		for (ReplaceMatch replaceMatch : matchesToReplace) {
			int pos = message.indexOf(replaceMatch.replacement, 0);
			int length = replaceMatch.replacement.length();
			int index = 0;
			while (pos >= 0) {
				positions.add(new ReplacePosition(pos, pos + length,
						replaceMatch.matches[index], replaceMatch.image));
				pos = message.indexOf(replaceMatch.replacement, pos + length);
			}
		}
		Collections.sort(positions);
		return positions;
	}

	private static String replaceAndFindEmoticonMatches(String message,
			List<ReplaceMatch> matchesToReplace) {
		for (PatternFactoryReplacement pair : patternReplacementPairs) {
			JsArrayString jsMatches = getMatches(message, pair.pattern);
			if (jsMatches != null && jsMatches.length() > 0) {
				int length = jsMatches.length();
				String[] matches = new String[length];
				for (int i = 0; i < length; i++) {
					matches[i] = jsMatches.get(i);
				}
				message = message.replaceAll(pair.pattern, pair.replacement);
				matchesToReplace.add(new ReplaceMatch(matches,
						pair.replacement, pair.factory));
			}
		}
		return message;
	}

	private static native JsArrayString getMatches(String text, String pattern) /*-{
		var regexp = new RegExp(pattern);
		return text.match(regexp);
	}-*/;

	/**
	 * Contains the array of the matches and the replacements.
	 */
	private static class ReplaceMatch {
		private String[] matches;
		private String replacement;

		private PatternElementFactory image;

		public ReplaceMatch(String[] matches, String replacement,
				PatternElementFactory image) {
			this.matches = matches;
			this.replacement = replacement;
			this.image = image;
		}
	}

	/**
	 * Contains the placement of a replace activity that must be done. Stores
	 * the start and end of the string to replace, the text to replace and the
	 * pattern element factory to use.
	 */
	private static class ReplacePosition implements Comparable<ReplacePosition> {
		private int start;
		private int end;
		private String text;

		private PatternElementFactory image;

		public ReplacePosition(int start, int end, String text,
				PatternElementFactory image) {
			this.start = start;
			this.end = end;
			this.text = text;
			this.image = image;
		}

		@Override
		public int compareTo(ReplacePosition o) {
			return start - o.start;
		}
	}
}
