package com.calclab.hablar.chat.client.ui;

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
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Image;

/**
 * Some formatting utilities
 */
public class ChatMessageFormatter {
    private static final String NEWLINE = "EmiteProtIniNEWLINEEmiteProtEnd";
    private static final String ANCHOR = "EmiteProtIniANCHOREmiteProtEnd";
    private static final String JOYFUL = "EmiteProtIniJOYFULEmiteProtEnd";
    private static final String ANGRY = "EmiteProtIniANGRYEmiteProtEnd";
    private static final String BLUSHING = "EmiteProtIniBLUSHINGEmiteProtEnd";
    private static final String CRYING = "EmiteProtIniCRYINGEmiteProtEnd";
    private static final String POUTY = "EmiteProtIniPOUTYEmiteProtEnd";
    private static final String SURPRISED = "EmiteProtIniSURPRISEDEmiteProtEnd";
    private static final String GRIN = "EmiteProtIniGRINEmiteProtEnd";
    private static final String ANGEL = "EmiteProtIniANGELEmiteProtEnd";
    private static final String KISSING = "EmiteProtIniKISSINGEmiteProtEnd";
    private static final String SMILE = "EmiteProtIniSMILEEmiteProtEnd";
    private static final String TONGUE = "EmiteProtIniTONGUEEmiteProtEnd";
    private static final String UNCERTAIN = "EmiteProtIniUNCERTAINEmiteProtEnd";
    private static final String COOL = "EmiteProtIniCOOLEmiteProtEnd";
    private static final String WINK = "EmiteProtIniWINKEmiteProtEnd";
    private static final String HAPPY = "EmiteProtIniHAPPYEmiteProtEnd";
    private static final String ALIEN = "EmiteProtIniALIENEmiteProtEnd";
    private static final String ANDY = "EmiteProtIniANDYEmiteProtEnd";
    private static final String DEVIL = "EmiteProtIniDEVILEmiteProtEnd";
    private static final String LOL = "EmiteProtIniLOLEmiteProtEnd";
    private static final String NINJA = "EmiteProtIniNINJAEmiteProtEnd";
    private static final String SAD = "EmiteProtIniSADEmiteProtEnd";
    private static final String SICK = "EmiteProtIniSICKEmiteProtEnd";
    private static final String SIDEWAYS = "EmiteProtIniSIDEWAYSEmiteProtEnd";
    private static final String SLEEPING = "EmiteProtIniSLEEPINGEmiteProtEnd";
    private static final String UNSURE = "EmiteProtIniUNSUREEmiteProtEnd";
    private static final String WONDERING = "EmiteProtIniWONDERINGEmiteProtEnd";
    private static final String LOVE = "EmiteProtIniLOVEEmiteProtEnd";
    private static final String PINCHED = "EmiteProtIniPINCHEDEmiteProtEnd";
    private static final String POLICEMAN = "EmiteProtIniPOLICEMANEmiteProtEnd";
    private static final String W00T = "EmiteProtIniWOOTEmiteProtEnd";
    private static final String WHISTLING = "EmiteProtIniWHISLINGEmiteProtEnd";
    private static final String WIZARD = "EmiteProtIniWIZARDEmiteProtEnd";
    private static final String BANDIT = "EmiteProtIniBANDITEmiteProtEnd";
    private static final String HEART = "EmiteProtIniHEARTEmiteProtEnd";
    private static Emoticons icons;

    private static List<PatternFactoryReplacement> patternImagePairs;

    static {
	icons = Emoticons.Instance.get();
	patternImagePairs = new ArrayList<PatternFactoryReplacement>();
	patternImagePairs.add(new PatternFactoryReplacement("\\r\\n", new NewlinePatternElementFactory(), NEWLINE));
	patternImagePairs.add(new PatternFactoryReplacement("\\r|\\n", new NewlinePatternElementFactory(), NEWLINE));
	patternImagePairs.add(new PatternFactoryReplacement(TextUtils.URL_REGEXP, new AnchorPatternElementFactory(), ANCHOR));
	patternImagePairs.add(new PatternFactoryReplacement("&gt;:\\)", new ImagePatternElementFactory(icons.devil()), DEVIL));
	patternImagePairs.add(new PatternFactoryReplacement("O:\\)|o:\\)|o:-\\)|O:-\\)|0:\\)|0:-\\)", new ImagePatternElementFactory(icons.angel()), ANGEL));
	patternImagePairs.add(new PatternFactoryReplacement("\\^_\\^|\\^-\\^|\\^\\^|:\\)\\)|:-\\)\\)", new ImagePatternElementFactory(icons.joyful()), JOYFUL));
	patternImagePairs.add(new PatternFactoryReplacement("\\(police\\)|\\(cop\\)|\\{\\):\\)", new ImagePatternElementFactory(icons.policeman()), POLICEMAN));
	patternImagePairs.add(new PatternFactoryReplacement("=:\\)|\\(alien\\)", new ImagePatternElementFactory(icons.alien()), ALIEN));
	patternImagePairs.add(new PatternFactoryReplacement("o_O|o_0|O_O|o_o|O_o|0_o|o0|0o|oO|Oo|0_0", new ImagePatternElementFactory(icons.andy()), ANDY));
	patternImagePairs.add(new PatternFactoryReplacement("&gt;:o|&gt;:-o|&gt;:O|&gt;:-O|X\\(|X-\\(|x\\(|x-\\(|:@|&lt;_&lt;",
		new ImagePatternElementFactory(icons.angry()), ANGRY));
	patternImagePairs.add(new PatternFactoryReplacement("\\(bandit\\)|:\\(&gt;", new ImagePatternElementFactory(icons.bandit()), BANDIT));
	patternImagePairs.add(new PatternFactoryReplacement(":&quot;&gt;|:\\*&gt;|:-\\$|:\\$", new ImagePatternElementFactory(icons.blushing()), BLUSHING));
	patternImagePairs.add(new PatternFactoryReplacement("B\\)|B-\\)|8\\)", new ImagePatternElementFactory(icons.cool()), COOL));
	patternImagePairs.add(new PatternFactoryReplacement(":\'\\(|=\'\\(", new ImagePatternElementFactory(icons.crying()), CRYING));
	patternImagePairs.add(new PatternFactoryReplacement(":-d|:d|:-D|:D|:d|=D|=-D", new ImagePatternElementFactory(icons.grin()), GRIN));
	patternImagePairs.add(new PatternFactoryReplacement("=\\)|=-\\)", new ImagePatternElementFactory(icons.happy()), HAPPY));
	patternImagePairs.add(new PatternFactoryReplacement("\\(L\\)|\\(h\\)|\\(H\\)", new ImagePatternElementFactory(icons.heart()), HEART));
	patternImagePairs.add(new PatternFactoryReplacement(":-\\*|:\\*", new ImagePatternElementFactory(icons.kissing()), KISSING));
	patternImagePairs.add(new PatternFactoryReplacement("\\(LOL\\)|lol", new ImagePatternElementFactory(icons.lol()), LOL));
	patternImagePairs
		.add(new PatternFactoryReplacement(":-X|:-xX|:x|\\(wubya\\)|\\(wubyou\\)|\\(wub\\)", new ImagePatternElementFactory(icons.love()), LOVE));
	patternImagePairs.add(new PatternFactoryReplacement("\\(:\\)|\\(ph33r\\)|\\(ph34r\\)", new ImagePatternElementFactory(icons.ninja()), NINJA));
	patternImagePairs.add(new PatternFactoryReplacement("&gt;_&lt;", new ImagePatternElementFactory(icons.pinched()), PINCHED));
	patternImagePairs.add(new PatternFactoryReplacement(":\\||=\\||:-\\|", new ImagePatternElementFactory(icons.pouty()), POUTY));
	patternImagePairs.add(new PatternFactoryReplacement(":\\(|=\\(|=-\\(|:-\\(", new ImagePatternElementFactory(icons.sad()), SAD));
	patternImagePairs.add(new PatternFactoryReplacement(":&amp;|:-&amp;", new ImagePatternElementFactory(icons.sick()), SICK));
	patternImagePairs.add(new PatternFactoryReplacement("=]", new ImagePatternElementFactory(icons.sideways()), SIDEWAYS));
	patternImagePairs.add(new PatternFactoryReplacement("\\(-.-\\)|\\|\\)|\\|-\\)|I-\\)|I-\\|", new ImagePatternElementFactory(icons.sleeping()), SLEEPING));
	patternImagePairs.add(new PatternFactoryReplacement(":-O|:O|:-o|:o|:-0|=-O|=-o|=o|=O", new ImagePatternElementFactory(icons.surprised()), SURPRISED));
	patternImagePairs.add(new PatternFactoryReplacement(":P|=P|=p|:-P|:p|:-p|:b", new ImagePatternElementFactory(icons.tongue()), TONGUE));
	patternImagePairs.add(new PatternFactoryReplacement(":-\\\\|:-/|:/|:\\\\", new ImagePatternElementFactory(icons.uncertain()), UNCERTAIN));
	patternImagePairs.add(new PatternFactoryReplacement(":s|:-S|:-s|:S", new ImagePatternElementFactory(icons.unsure()), UNSURE));
	patternImagePairs.add(new PatternFactoryReplacement("\\(woot\\)|\\(w00t\\)|\\(wOOt\\)", new ImagePatternElementFactory(icons.w00t()), W00T));
	patternImagePairs.add(new PatternFactoryReplacement(":-&quot;", new ImagePatternElementFactory(icons.whistling()), WHISTLING));
	patternImagePairs.add(new PatternFactoryReplacement(";\\)|;-\\)|;&gt;", new ImagePatternElementFactory(icons.wink()), WINK));
	patternImagePairs.add(new PatternFactoryReplacement("\\(wizard\\)", new ImagePatternElementFactory(icons.wizard()), WIZARD));
	patternImagePairs.add(new PatternFactoryReplacement(":\\?", new ImagePatternElementFactory(icons.wondering()), WONDERING));
	patternImagePairs.add(new PatternFactoryReplacement(":-\\)|:\\)", new ImagePatternElementFactory(icons.smile()), SMILE));
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
	return text == null ? "" : length == 0 ? text : text.length() > length ? text.substring(0, length - 3) + "..."
		: text;
    }

    public static Element format(String name, String message) {
	if (name == null || name.length() <= 0) {
	    // Workaround for #207
	    message = message.replaceAll(" room ", " group chat ");
	    message = message.replaceAll("Room ", "Group chat ");
	}
	List<ReplaceMatch> matchesToReplace = new ArrayList<ReplaceMatch>();
	message = replaceAndFindEmoticonMatches(message, matchesToReplace);
	List<ReplacePosition> positions = findEmoticonPositions(message, matchesToReplace);
	SpanElement element = createFormattedMessageElement(message, positions);
	return element;
    }

    private ChatMessageFormatter() {
    }

    private static SpanElement createFormattedMessageElement(String message, List<ReplacePosition> positions) {
	Document doc = Document.get();
	SpanElement element = doc.createSpanElement();
	int previousPosition = 0;
	for (ReplacePosition position : positions) {
	    element.appendChild(doc.createTextNode(message.substring(previousPosition, position.start)));
	    element.appendChild(position.image.create(position.text));
	    previousPosition = position.end;
	}
	if (previousPosition < message.length()) {
	    element.appendChild(doc.createTextNode(message.substring(previousPosition)));
	}
	return element;
    }

    private static List<ReplacePosition> findEmoticonPositions(String message, List<ReplaceMatch> matchesToReplace) {
	List<ReplacePosition> positions = new ArrayList<ReplacePosition>();
	for (ReplaceMatch replaceMatch : matchesToReplace) {
	    int pos = message.indexOf(replaceMatch.replacement, 0);
	    int length = replaceMatch.replacement.length();
	    int index = 0;
	    while (pos >= 0) {
		positions.add(new ReplacePosition(pos, pos + length, replaceMatch.matches[index], replaceMatch.image));
		pos = message.indexOf(replaceMatch.replacement, pos + length);
	    }
	}
	Collections.sort(positions);
	return positions;
    }

    private static String replaceAndFindEmoticonMatches(String message, List<ReplaceMatch> matchesToReplace) {
	for (PatternFactoryReplacement pair : patternImagePairs) {
	    JsArrayString jsMatches = getMatches(message, pair.pattern);
	    if (jsMatches != null && jsMatches.length() > 0) {
		int length = jsMatches.length();
		String[] matches = new String[length];
		for (int i = 0; i < length; i++) {
		    matches[i] = jsMatches.get(i);
		}
		message = message.replaceAll(pair.pattern, pair.replacement);
		matchesToReplace.add(new ReplaceMatch(matches, pair.replacement, pair.factory));
	    }
	}
	return message;
    }

    private static native JsArrayString getMatches(String text, String pattern) /*-{
        var regexp = new RegExp(pattern);
        return text.match(regexp);
    }-*/;

    /**
     * It represents a factory of elements, given the original text.
     */
    private static interface PatternElementFactory {
	Element create(String originalText);
    }

    /**
     * Generates an element given the image resource and the original text.
     */
    private static class ImagePatternElementFactory implements PatternElementFactory {

	private ImageResource image;

	public ImagePatternElementFactory(ImageResource image) {
	    this.image = image;
	}

	@Override
	public Element create(String originalText) {
	    Document doc = Document.get();
	    SpanElement imageContainer = doc.createSpanElement();
	    Image image = new Image(this.image);
	    image.setTitle(originalText);
	    image.addStyleName("hablar-EmoticonImage");
	    imageContainer.appendChild(image.getElement());
	    return imageContainer;
	}

    }

    /**
     * Generates an anchor, given the URL to point to.
     */
    private static class AnchorPatternElementFactory implements PatternElementFactory {

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

    /**
     * Generates a &lt;br&gt; element.
     */
    private static class NewlinePatternElementFactory implements PatternElementFactory {

	@Override
	public Element create(String originalText) {
	    Document doc = Document.get();
	    return doc.createBRElement();
	}
    }

    /**
     * Triad of a string pattern, an pattern element factory and the replacement
     * of found patterns in the messages.
     */
    private static class PatternFactoryReplacement {
	private String pattern;

	private PatternElementFactory factory;

	private String replacement;

	public PatternFactoryReplacement(String pattern, PatternElementFactory factory, String replacement) {
	    this.pattern = pattern;
	    this.factory = factory;
	    this.replacement = replacement;
	}
    }

    /**
     * Contains the array of the matches and the replacements.
     */
    private static class ReplaceMatch {
	private String[] matches;
	private String replacement;

	private PatternElementFactory image;

	public ReplaceMatch(String[] matches, String replacement, PatternElementFactory image) {
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

	public ReplacePosition(int start, int end, String text, PatternElementFactory image) {
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
