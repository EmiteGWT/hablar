package com.calclab.hablar.chat.client.ui;

import com.calclab.emite.core.client.packet.TextUtils;
import com.calclab.hablar.core.client.ui.emoticons.EmoticonStyles;
import com.calclab.hablar.core.client.ui.emoticons.Emoticons;

/**
 * Some formatting utilities
 */
public class ChatMessageFormatter {
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

    public static String ellipsis(final String text, final int length) {
	return text.length() > length ? text.substring(0, length - 3) + "..." : text;
    }

    public static String format(final String message) {
	if (message != null) {
	    String formatted = TextUtils.escape(message);
	    formatted = formatNewLines(formatted);
	    formatted = formatUrls(formatted);
	    formatted = formatEmoticons(formatted);
	    return formatted;
	} else {
	    return null;
	}
    }

    static String formatNewLines(final String formatted) {
	return formatted.replaceAll("\n", "<br>\n");
    }

    static String formatUrls(String message) {
	return message = message.replaceAll(TextUtils.URL_REGEXP, "<a href=\"$1\" target=\"_blank\">$1</a>");
    }

    static String preFormatEmoticons(String message) {
	message = replace(message, new String[] { "&gt;:\\)" }, DEVIL);
	message = replace(message, new String[] { "O:\\)", "o:\\)", "o:-\\)", "O:-\\)", "0:\\)", "0:-\\)" }, ANGEL);
	message = replace(message, new String[] { "\\^_\\^", "\\^-\\^", "\\^\\^", ":\\)\\)", ":-\\)\\)" }, JOYFUL);
	message = replace(message, new String[] { "\\(police\\)", "\\(cop\\)", "\\{\\):\\)" }, POLICEMAN);
	message = replace(message, new String[] { "=:\\)", "\\(alien\\)" }, ALIEN);
	message = replace(message, new String[] { "o_O", "o_0", "O_O", "o_o", "O_o", "0_o", "o0", "0o", "oO", "Oo",
		"0_0" }, ANDY);
	message = replace(message, new String[] { "&gt;:o", "&gt;:-o", "&gt;:O", "&gt;:-O", "X\\(", "X-\\(", "x\\(",
		"x-\\(", ":@", "&lt;_&lt;" }, ANGRY);
	message = replace(message, new String[] { "\\(bandit\\)", ":\\(&gt;" }, BANDIT);
	message = replace(message, new String[] { ":&quot;&gt;", ":\\*&gt;", ":-\\$", ":\\$" }, BLUSHING);
	message = replace(message, new String[] { "B\\)", "B-\\)", "8\\)" }, COOL);
	message = replace(message, new String[] { ":\'\\(", "=\'\\(" }, CRYING);
	message = replace(message, new String[] { ":-d", ":d", ":-D", ":D", ":d", "=D", "=-D" }, GRIN);
	message = replace(message, new String[] { "=\\)", "=-\\)" }, HAPPY);
	message = replace(message, new String[] { "\\(L\\)", "\\(h\\)", "\\(H\\)" }, HEART);
	message = replace(message, new String[] { ":-\\*", ":\\*" }, KISSING);
	message = replace(message, new String[] { "\\(LOL\\)", "lol" }, LOL);
	message = replace(message, new String[] { ":-X", ":-xX", ":x", "\\(wubya\\)", "\\(wubyou\\)", "\\(wub\\)" },
		LOVE);
	message = replace(message, new String[] { "\\(:\\)", "\\(ph33r\\)", "\\(ph34r\\)" }, NINJA);
	message = replace(message, new String[] { "&gt;_&lt;" }, PINCHED);
	message = replace(message, new String[] { ":\\|", "=\\|", ":-\\|" }, POUTY);
	message = replace(message, new String[] { ":\\(", "=\\(", "=-\\(", ":-\\(" }, SAD);
	message = replace(message, new String[] { ":&amp;", ":-&amp;" }, SICK);
	message = replace(message, new String[] { "=]" }, SIDEWAYS);
	message = replace(message, new String[] { "\\(-.-\\)", "\\|\\)", "\\|-\\)", "I-\\)", "I-\\|" }, SLEEPING);
	message = replace(message, new String[] { ":-O", ":O", ":-o", ":o", ":-0", "=-O", "=-o", "=o", "=O" },
		SURPRISED);
	message = replace(message, new String[] { ":P", "=P", "=p", ":-P", ":p", ":-p", ":b" }, TONGUE);
	message = replace(message, new String[] { ":-\\\\", ":-/", ":/", ":\\\\" }, UNCERTAIN);
	message = replace(message, new String[] { ":s", ":-S", ":-s", ":S" }, UNSURE);
	message = replace(message, new String[] { "\\(woot\\)", "\\(w00t\\)", "\\(wOOt\\)" }, W00T);
	message = replace(message, new String[] { ":-&quot;" }, WHISTLING);
	message = replace(message, new String[] { ";\\)", ";-\\)", ";&gt;" }, WINK);
	message = replace(message, new String[] { "\\(wizard\\)" }, WIZARD);
	message = replace(message, new String[] { ":\\?" }, WONDERING);
	message = replace(message, new String[] { ":-\\)", ":\\)" }, SMILE);
	return message;
    }

    private static String formatEmoticons(String message) {
	// FIXME: find a better way (for instance using Suco), taking care of
	// the tests (and GWT.create stuff)
	icons = Emoticons.App.getInst();
	final EmoticonStyles css = icons.css();
	css.ensureInjected();
	message = preFormatEmoticons(message);
	message = message.replaceAll(SMILE, getSpan(css.smile()));
	message = message.replaceAll(CRYING, getSpan(css.crying()));
	message = message.replaceAll(SURPRISED, getSpan(css.surprised()));
	message = message.replaceAll(ANGEL, getSpan(css.angel()));
	message = message.replaceAll(HAPPY, getSpan(css.happy()));
	message = message.replaceAll(GRIN, getSpan(css.grin()));
	message = message.replaceAll(JOYFUL, getSpan(css.joyful()));
	message = message.replaceAll(UNCERTAIN, getSpan(css.uncertain()));
	message = message.replaceAll(ANGRY, getSpan(css.angry()));
	message = message.replaceAll(TONGUE, getSpan(css.tongue()));
	message = message.replaceAll(LOVE, getSpan(css.love()));
	message = message.replaceAll(SLEEPING, getSpan(css.sleeping()));
	message = message.replaceAll(COOL, getSpan(css.cool()));
	message = message.replaceAll(KISSING, getSpan(css.kissing()));
	message = message.replaceAll(SAD, getSpan(css.sad()));
	message = message.replaceAll(ALIEN, getSpan(css.alien()));
	message = message.replaceAll(ANDY, getSpan(css.andy()));
	message = message.replaceAll(BANDIT, getSpan(css.bandit()));
	message = message.replaceAll(BLUSHING, getSpan(css.blushing()));
	message = message.replaceAll(DEVIL, getSpan(css.devil()));
	message = message.replaceAll(HEART, getSpan(css.heart()));
	message = message.replaceAll(LOL, getSpan(css.lol()));
	message = message.replaceAll(NINJA, getSpan(css.ninja()));
	message = message.replaceAll(PINCHED, getSpan(css.pinched()));
	message = message.replaceAll(POLICEMAN, getSpan(css.policeman()));
	message = message.replaceAll(POUTY, getSpan(css.pouty()));
	message = message.replaceAll(SICK, getSpan(css.sick()));
	message = message.replaceAll(SIDEWAYS, getSpan(css.sideways()));
	message = message.replaceAll(UNSURE, getSpan(css.unsure()));
	message = message.replaceAll(W00T, getSpan(css.w00t()));
	message = message.replaceAll(WINK, getSpan(css.wink()));
	message = message.replaceAll(WONDERING, getSpan(css.wondering()));
	message = message.replaceAll(WHISTLING, getSpan(css.whistling()));
	message = message.replaceAll(WIZARD, getSpan(css.wizard()));
	return message;
    }

    private static String getSpan(final String style) {
	return "<span class=\"" + icons.css().base() + " " + style + "\"><span>";
    }

    private static String replace(String message, final String[] from, final String to) {
	for (final String element : from) {
	    message = message.replaceAll("(^|[\\s])(" + element + ")([\\s]|$)", "$1" + to + "$2</span></span>$3");
	    // two times for: :) :) :) :)
	    message = message.replaceAll("(^|[\\s])(" + element + ")([\\s]|$)", "$1" + to + "$2</span></span>$3");
	}
	return message;
    }

    private ChatMessageFormatter() {
    }

}
