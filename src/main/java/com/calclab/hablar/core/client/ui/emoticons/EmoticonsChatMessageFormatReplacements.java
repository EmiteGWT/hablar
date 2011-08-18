package com.calclab.hablar.core.client.ui.emoticons;

import java.util.ArrayList;
import java.util.List;

import com.calclab.hablar.chat.client.ui.chatmessageformat.ChatMessageFormatReplacements;
import com.calclab.hablar.chat.client.ui.chatmessageformat.ImagePatternElementFactory;
import com.calclab.hablar.chat.client.ui.chatmessageformat.PatternFactoryReplacement;

/**
 * {@link ChatMessageFormatReplacements} which will do replacements for a bunch of emoticons.
 */
public class EmoticonsChatMessageFormatReplacements implements
		ChatMessageFormatReplacements {
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
	private static Emoticons icons = Emoticons.Instance.get();

	@Override
	public void addReplacements(final List<PatternFactoryReplacement> replacements) {
		replacements.add(new PatternFactoryReplacement("&gt;:\\)",
				new ImagePatternElementFactory(icons.devil()), DEVIL));
		replacements.add(new PatternFactoryReplacement(
				"O:\\)|o:\\)|o:-\\)|O:-\\)|0:\\)|0:-\\)",
				new ImagePatternElementFactory(icons.angel()), ANGEL));
		replacements.add(new PatternFactoryReplacement(
				"\\^_\\^|\\^-\\^|\\^\\^|:\\)\\)|:-\\)\\)",
				new ImagePatternElementFactory(icons.joyful()), JOYFUL));
		replacements.add(new PatternFactoryReplacement(
				"\\(police\\)|\\(cop\\)|\\{\\):\\)",
				new ImagePatternElementFactory(icons.policeman()), POLICEMAN));
		replacements.add(new PatternFactoryReplacement(
				"=:\\)|\\(alien\\)", new ImagePatternElementFactory(icons
						.alien()), ALIEN));
		replacements.add(new PatternFactoryReplacement(
				"o_O|o_0|O_O|o_o|O_o|0_o|o0|0o|oO|Oo|0_0",
				new ImagePatternElementFactory(icons.andy()), ANDY));
		replacements
				.add(new PatternFactoryReplacement(
						"&gt;:o|&gt;:-o|&gt;:O|&gt;:-O|X\\(|X-\\(|x\\(|x-\\(|:@|&lt;_&lt;",
						new ImagePatternElementFactory(icons.angry()), ANGRY));
		replacements.add(new PatternFactoryReplacement(
				"\\(bandit\\)|:\\(&gt;", new ImagePatternElementFactory(icons
						.bandit()), BANDIT));
		replacements.add(new PatternFactoryReplacement(
				":&quot;&gt;|:\\*&gt;|:-\\$|:\\$",
				new ImagePatternElementFactory(icons.blushing()), BLUSHING));
		replacements.add(new PatternFactoryReplacement(
				"B\\)|B-\\)|8\\)",
				new ImagePatternElementFactory(icons.cool()), COOL));
		replacements.add(new PatternFactoryReplacement(
				":\'\\(|=\'\\(",
				new ImagePatternElementFactory(icons.crying()), CRYING));
		replacements.add(new PatternFactoryReplacement(
				":-d|:d|:-D|:D|:d|=D|=-D", new ImagePatternElementFactory(icons
						.grin()), GRIN));
		replacements.add(new PatternFactoryReplacement("=\\)|=-\\)",
				new ImagePatternElementFactory(icons.happy()), HAPPY));
		replacements.add(new PatternFactoryReplacement(
				"\\(L\\)|\\(h\\)|\\(H\\)", new ImagePatternElementFactory(icons
						.heart()), HEART));
		replacements.add(new PatternFactoryReplacement(":-\\*|:\\*",
				new ImagePatternElementFactory(icons.kissing()), KISSING));
		replacements.add(new PatternFactoryReplacement(
				"\\(LOL\\)|lol", new ImagePatternElementFactory(icons.lol()),
				LOL));
		replacements.add(new PatternFactoryReplacement(
				":-X|:-xX|:x|\\(wubya\\)|\\(wubyou\\)|\\(wub\\)",
				new ImagePatternElementFactory(icons.love()), LOVE));
		replacements.add(new PatternFactoryReplacement(
				"\\(:\\)|\\(ph33r\\)|\\(ph34r\\)",
				new ImagePatternElementFactory(icons.ninja()), NINJA));
		replacements.add(new PatternFactoryReplacement("&gt;_&lt;",
				new ImagePatternElementFactory(icons.pinched()), PINCHED));
		replacements.add(new PatternFactoryReplacement(
				":\\||=\\||:-\\|",
				new ImagePatternElementFactory(icons.pouty()), POUTY));
		replacements.add(new PatternFactoryReplacement(
				":\\(|=\\(|=-\\(|:-\\(", new ImagePatternElementFactory(icons
						.sad()), SAD));
		replacements.add(new PatternFactoryReplacement(
				":&amp;|:-&amp;", new ImagePatternElementFactory(icons.sick()),
				SICK));
		replacements.add(new PatternFactoryReplacement("=]",
				new ImagePatternElementFactory(icons.sideways()), SIDEWAYS));
		replacements.add(new PatternFactoryReplacement(
				"\\(-.-\\)|\\|\\)|\\|-\\)|I-\\)|I-\\|",
				new ImagePatternElementFactory(icons.sleeping()), SLEEPING));
		replacements.add(new PatternFactoryReplacement(
				":-O|:O|:-o|:o|:-0|=-O|=-o|=o|=O",
				new ImagePatternElementFactory(icons.surprised()), SURPRISED));
		replacements.add(new PatternFactoryReplacement(
				":P|=P|=p|:-P|:p|:-p|:b", new ImagePatternElementFactory(icons
						.tongue()), TONGUE));
		replacements.add(new PatternFactoryReplacement(
				":-\\\\|:-/|:/|:\\\\", new ImagePatternElementFactory(icons
						.uncertain()), UNCERTAIN));
		replacements.add(new PatternFactoryReplacement(
				":s|:-S|:-s|:S",
				new ImagePatternElementFactory(icons.unsure()), UNSURE));
		replacements.add(new PatternFactoryReplacement(
				"\\(woot\\)|\\(w00t\\)|\\(wOOt\\)",
				new ImagePatternElementFactory(icons.w00t()), W00T));
		replacements.add(new PatternFactoryReplacement(":-&quot;",
				new ImagePatternElementFactory(icons.whistling()), WHISTLING));
		replacements.add(new PatternFactoryReplacement(
				";\\)|;-\\)|;&gt;",
				new ImagePatternElementFactory(icons.wink()), WINK));
		replacements.add(new PatternFactoryReplacement(
				"\\(wizard\\)", new ImagePatternElementFactory(icons.wizard()),
				WIZARD));
		replacements.add(new PatternFactoryReplacement(":\\?",
				new ImagePatternElementFactory(icons.wondering()), WONDERING));
		replacements.add(new PatternFactoryReplacement(":-\\)|:\\)",
				new ImagePatternElementFactory(icons.smile()), SMILE));
		
	}
}
