package ua.gudkov.fp.util;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.util.HashMap;
import java.util.Map;

/**
 * Implements drawer. Contains methods to draw images using given properties.
 * 
 * @author A.Gudkov
 *
 */
public final class Drawer {

	private Drawer() {
	}

	/**
	 * Creates captcha image.
	 *
	 * @param captchaValue
	 *            the captcha value
	 */
	public static RenderedImage drawCaptcha(String captchaValue) {
		BufferedImage image = new BufferedImage(200, 20, BufferedImage.TYPE_BYTE_INDEXED);
		Graphics2D graphics = image.createGraphics();
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, 200, 20);
		Font font = new Font("Comic Sans MS", Font.BOLD, 18);
		Map<TextAttribute, Object> attributes = new HashMap<TextAttribute, Object>();
		attributes.put(TextAttribute.TRACKING, 1.2);
		Font font2 = font.deriveFont(attributes);
		graphics.setColor(Color.GRAY);
		graphics.setFont(font2);
		graphics.drawString(captchaValue, 5, 16);
		return image;
	}
}
