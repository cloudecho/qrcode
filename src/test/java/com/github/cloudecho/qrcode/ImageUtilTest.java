package com.github.cloudecho.qrcode;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;

public class ImageUtilTest {

	@Test
	public void test() throws IOException {
		// 圆角
		BufferedImage icon = ImageIO.read(new File(
				"/Users/echo/Desktop/antbox_logo.jpeg"));
		BufferedImage rounded = ImageUtil.makeRoundedCorner(icon, 30);
		ImageIO.write(rounded, "png", new File(
				"/Users/echo/Desktop/antbox_logo_rounded.png"));

		// resize
		BufferedImage resized = ImageUtil.createResizedCopy(icon, 100, 100,
				true);
		ImageIO.write(resized, "png", new File(
				"/Users/echo/Desktop/antbox_logo_resized.png"));

	}

}
