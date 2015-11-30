package com.github.cloudecho.qrcode;


import java.io.Serializable;

import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * 二维码（QRCODE）配置类
 */
public class ZxingEntry implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 二维码内容
	 */
	private String content;
	/**
	 * 图片的宽度
	 */
	private int width = 430;
	/**
	 * 图片的高度
	 */
	private int height = 430;
	/**
	 * 生成图片的目录（不含图片名称）
	 */
	private String dir;

	/**
	 * 生成图片的名称（不含目录、后缀）
	 */
	private String name;

	/**
	 * 生成图片的格式（后缀，例如: "png"）
	 */
	private String format = "png";

	/**
	 * logo图地址
	 */
	private String logoPath;

	/**
	 * logo圆角半径（0表示不作圆角处理）
	 */
	private int logoCornerRadius = 0;

	/**
	 * 纠错级别
	 */
	private Object errorCorrectionLevel = ErrorCorrectionLevel.H;
	/**
	 * 编码格式
	 */
	private String charset = "UTF-8";
	/**
	 * 二维码边缘留白
	 */
	private int margin = 0;

	/**
	 * 生成图片的全路径（包括目录、文件名及后缀）
	 */
	public String getPath() {
		return "" + getDir() + "/" + getName() + "." + getFormat();
	}

	public int getLogoCornerRadius() {
		return logoCornerRadius;
	}

	public void setLogoCornerRadius(int logoCornerRadius) {
		this.logoCornerRadius = logoCornerRadius;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public Object getErrorCorrectionLevel() {
		return errorCorrectionLevel;
	}

	public void setErrorCorrectionLevel(Object errorCorrectionLevel) {
		this.errorCorrectionLevel = errorCorrectionLevel;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public int getMargin() {
		return margin;
	}

	public void setMargin(int margin) {
		this.margin = margin;
	}

	public String getLogoPath() {
		return logoPath;
	}

	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}
}
