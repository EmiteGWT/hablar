package com.calclab.hablar.chat.client.ui;

import java.util.HashMap;

import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;

public class ColorHelper {
    private static final double BRIGHTNESS = 0.5;
    private static final double SATURATION = 0.80;
    public static final String ME = "#0A0A0A";
    public static final String NONE = "#0A0A0A";
    private static final double GOLDEN_RATIO = 0.618033988749895;
    private static HashMap<XmppURI, String> colors = new HashMap<XmppURI, String>();
    private static double current = Math.random();

    public static String getColor(final XmppURI from) {
	String color = colors.get(from);
	if (color == null) {
	    color = generateNext();
	    colors.put(from, color);
	}
	return color;
    }

    public static String HSBtoRGB(final double hue, final double saturation, final double brightness) {
	int r = 0, g = 0, b = 0;
	if (saturation == 0) {
	    r = g = b = (int) (brightness * 255.0f + 0.5f);
	} else {
	    final double h = (hue - Math.floor(hue)) * 6.0f;
	    final double f = h - java.lang.Math.floor(h);
	    final double p = brightness * (1.0f - saturation);
	    final double q = brightness * (1.0f - saturation * f);
	    final double t = brightness * (1.0f - (saturation * (1.0f - f)));
	    switch ((int) h) {
	    case 0:
		r = (int) (brightness * 255.0f + 0.5f);
		g = (int) (t * 255.0f + 0.5f);
		b = (int) (p * 255.0f + 0.5f);
		break;
	    case 1:
		r = (int) (q * 255.0f + 0.5f);
		g = (int) (brightness * 255.0f + 0.5f);
		b = (int) (p * 255.0f + 0.5f);
		break;
	    case 2:
		r = (int) (p * 255.0f + 0.5f);
		g = (int) (brightness * 255.0f + 0.5f);
		b = (int) (t * 255.0f + 0.5f);
		break;
	    case 3:
		r = (int) (p * 255.0f + 0.5f);
		g = (int) (q * 255.0f + 0.5f);
		b = (int) (brightness * 255.0f + 0.5f);
		break;
	    case 4:
		r = (int) (t * 255.0f + 0.5f);
		g = (int) (p * 255.0f + 0.5f);
		b = (int) (brightness * 255.0f + 0.5f);
		break;
	    case 5:
		r = (int) (brightness * 255.0f + 0.5f);
		g = (int) (p * 255.0f + 0.5f);
		b = (int) (q * 255.0f + 0.5f);
		break;
	    }
	}
	return "rgb(" + r + "," + g + "," + b + ")";
	// return 0xff000000 | (r << 16) | (g << 8) | (b << 0);
    }

    private static String generateNext() {
	current += GOLDEN_RATIO;
	current %= 1;
	return HSBtoRGB(current, SATURATION, BRIGHTNESS);
    }

}
