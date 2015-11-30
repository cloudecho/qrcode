package com.github.cloudecho.qrcode;

import java.io.IOException;

import org.junit.Test;

import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;

public class ZxingCodeTest {

	/**
	 * TEST: 生成二维码、解析二维码
	 */
	public void testZxingCode() throws WriterException, IOException, NotFoundException {
		ZxingEntry zxing = new ZxingEntry();
		zxing.setContent("http://weixin.qq.com/r/hello?a=123");
		zxing.setCharset("UTF-8");
		// zxing.setErrorCorrectionLevel(ErrorCorrectionLevel.H);
		zxing.setFormat("png");
		zxing.setMargin(0);
		zxing.setWidth(430);
		zxing.setHeight(430);
		zxing.setDir("/Users/echo/Desktop/");
		zxing.setName("logo_qrcode");
		zxing.setLogoPath("/Users/echo/Desktop/antbox_logo.jpeg");
		zxing.setLogoCornerRadius(16);

		// 生成二维码
		ZxingCode code = new ZxingCode();
		code.encode(zxing);
		System.out.println("生成二维码成功");

		// 解析二维码
		Result result = code.decode(zxing.getPath(), "utf-8");
		System.out.println("解析结果: " + result.getText());

		result = code.decode("/Users/echo/Desktop/wx_qrcode_for_antbox_8cm.jpg", "utf-8");
		System.out.println("解析结果: " + result.getText());
	}

	/**
	 * TEST: 生成二维码
	 */
	@Test
	public void createQrcode() throws WriterException, IOException {
		ZxingEntry zxing = new ZxingEntry();
		zxing.setContent("http://weixin.qq.com/r/hello?a=456");
		zxing.setCharset("UTF-8");
		// zxing.setErrorCorrectionLevel(ErrorCorrectionLevel.H);
		zxing.setFormat("png");
		zxing.setMargin(0);
		zxing.setWidth(430);
		zxing.setHeight(430);
		zxing.setDir("/images/qrcode");
		zxing.setName("qrcode456");
		zxing.setLogoPath("/images/qrcode/antbox_logo.jpeg");
		zxing.setLogoCornerRadius(16);

		// 生成二维码
		ZxingCode code = new ZxingCode();
		code.encode(zxing);
		System.out.println("生成二维码成功");
	}

}
