package com.github.cloudecho.qrcode;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

/**
 * Zxing二维码实用类
 */
public class ZxingCode {
	static final int WHITE = 0xFFFFFFFF;

	static final int BLACK = 0xff000000;

	/**
	 * 生成QRCode二维码
	 * 
	 * @throws WriterException
	 * @throws IOException
	 */
	public void encode(ZxingEntry zxing) throws WriterException, IOException {
		Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
		// 设置纠错级别(L 7%~M 15%~Q 25%~H 30%),纠错级别越高存储的信息越少
		hints.put(EncodeHintType.ERROR_CORRECTION,
				zxing.getErrorCorrectionLevel());

		// 设置编码格式
		hints.put(EncodeHintType.CHARACTER_SET, zxing.getCharset());

		// 设置边缘空白
		hints.put(EncodeHintType.MARGIN, zxing.getMargin());

		BitMatrix bitMatrix = new MultiFormatWriter().encode(
				zxing.getContent(), BarcodeFormat.QR_CODE, zxing.getWidth(),
				zxing.getHeight(), hints);

		File dir = new File(zxing.getDir());
		if (!dir.exists()) {
			dir.mkdirs();
		}

		writeToFile(bitMatrix, zxing.getFormat(), new File(zxing.getPath()),
				zxing.getLogoPath(), zxing.getLogoCornerRadius());

	}

	/**
	 * 解析二维码
	 * 
	 * @param path
	 *            图片的绝对路径
	 * @param charset
	 *            字符集
	 * @throws IOException
	 * @throws NotFoundException
	 */
	public Result decode(String path, String charset) throws IOException,
			NotFoundException {
		Assert.hasText(path, "[decode]文件路径不能为空");
		File file = new File(path);
		BufferedImage image = ImageIO.read(file);
		// 判断是否是图片
		if (image == null) {
			throw new IOException("Could not decode image: " + path);
		}

		// 解析二维码用到的辅助类
		LuminanceSource source = new BufferedImageLuminanceSource(image);
		BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

		// 解码设置编码方式
		Hashtable<DecodeHintType, Object> hints = new Hashtable<DecodeHintType, Object>();
		hints.put(DecodeHintType.CHARACTER_SET, charset);

		return new MultiFormatReader().decode(bitmap, hints);
	}

	/**
	 * 生成二维码图片
	 * 
	 * @param bitMatrix
	 * @param format
	 *            图片格式
	 * @param file
	 *            生成二维码图片位置
	 * @param isLogo
	 *            是否要加logo图
	 * @param logoPath
	 *            logo图片地址
	 * @throws IOException
	 */
	static void writeToFile(BitMatrix bitMatrix, String format, File file,
			String logoPath, int logoCornerRadius) throws IOException {
		BufferedImage bi = toBufferedImage(bitMatrix);
		if (StringUtils.hasText(logoPath)) {
			int width_4 = bitMatrix.getWidth() / 4;
			int width_8 = width_4 / 2;
			int height_4 = bitMatrix.getHeight() / 4;
			int height_8 = height_4 / 2;

			/* 读取logo图片信息 */
			BufferedImage logoimg = ImageIO.read(new File(logoPath));// 实例化一个Image对象。

			/* 当前图片的宽与高 */
			int currentImgWidth = logoimg.getWidth();
			int currentImgHeight = logoimg.getHeight();
			/* 处理图片的宽与高 */
			int resultImgWidth = 0;
			int resultImgHeight = 0;
			if (currentImgWidth != width_4) {
				resultImgWidth = width_4;
			}
			if (currentImgHeight != width_4) {
				resultImgHeight = width_4;
			}

			/* 绘制LOGO */
			// 圆角处理
			if (logoCornerRadius > 0) {
				logoimg = ImageUtil
						.makeRoundedCorner(logoimg, logoCornerRadius);
			}
			/* 返回由指定矩形区域定义的子图像 */
			BufferedImage bi2 = bi.getSubimage(width_4 + width_8, height_4
					+ height_8, width_4, height_4);
			/* 获取一个绘图工具笔 */
			Graphics2D g2 = bi2.createGraphics();
			g2.drawImage(logoimg.getScaledInstance(resultImgWidth,
					resultImgHeight, Image.SCALE_SMOOTH), 0, 0, null);
			g2.dispose();
			bi.flush();
		}
		ImageIO.write(bi, format, file);
	}

	/**
	 * 生成二维码内容
	 * 
	 * @param bitMatrix
	 * @return BufferedImage
	 */
	static BufferedImage toBufferedImage(BitMatrix bitMatrix) {
		int width = bitMatrix.getWidth();
		int height = bitMatrix.getHeight();
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, bitMatrix.get(x, y) ? BLACK : WHITE);
			}
		}
		return image;
	}

}
