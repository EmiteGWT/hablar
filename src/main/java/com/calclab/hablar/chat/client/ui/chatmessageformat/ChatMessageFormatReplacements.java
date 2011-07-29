package com.calclab.hablar.chat.client.ui.chatmessageformat;

import java.util.List;

public interface ChatMessageFormatReplacements {
	/**
	 * Add a number of chat message replacements to the list.
	 * @param replacements the current {@link List} of {@link PatternFactoryReplacement} instances
	 */
	void addReplacements(List<PatternFactoryReplacement> replacements);
}
