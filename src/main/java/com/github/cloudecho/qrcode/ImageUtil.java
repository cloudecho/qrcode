package com.github.cloudecho.qrcode;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

/**
 * 图片处理工具类
 */
public class ImageUtil {
	/**
	 * 制作圆角图片
	 */
	public static BufferedImage makeRoundedCorner(BufferedImage image,
			int cornerRadius) {
		int w = image.getWidth();
		int h = image.getHeight();
		BufferedImage output = new BufferedImage(w, h,
				BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2 = output.createGraphics();

		// This is what we want, but it only does hard-clipping, i.e. aliasing
		// g2.setClip(new RoundRectangle2D ...)

		// so instead fake soft-clipping by first drawing the desired clip shape
		// in fully opaque white with antialiasing enabled...
		g2.setComposite(AlphaComposite.Src);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(Color.WHITE);
		g2.fill(new RoundRectangle2D.Float(0, 0, w, h, cornerRadius,
				cornerRadius));

		// ... then compositing the image on top,
		// using the white shape from above as alpha source
		g2.setComposite(AlphaComposite.SrcAtop);
		g2.drawImage(image, 0, 0, null);

		g2.dispose();

		return output;
	}

	/**
	 * 图片缩放
	 */
	public static BufferedImage createResizedCopy(Image originalImage,
			int scaledWidth, int scaledHeight, boolean preserveAlpha) {
		int imageType = preserveAlpha ? BufferedImage.TYPE_INT_RGB
				: BufferedImage.TYPE_INT_ARGB;
		BufferedImage scaledBI = new BufferedImage(scaledWidth, scaledHeight,
				imageType);
		Graphics2D g = scaledBI.createGraphics();
		if (preserveAlpha) {
			g.setComposite(AlphaComposite.Src);
		}
		g.drawImage(originalImage.getScaledInstance(scaledWidth, scaledHeight,
				Image.SCALE_SMOOTH), 0, 0, null);
		g.dispose();
		return scaledBI;
	}

}