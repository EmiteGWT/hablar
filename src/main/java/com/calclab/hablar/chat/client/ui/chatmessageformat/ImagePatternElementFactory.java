package com.calclab.hablar.chat.client.ui.chatmessageformat;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Image;

/**
 * Generates an element given the image resource and the original text.
 */
public class ImagePatternElementFactory implements
		PatternElementFactory {

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