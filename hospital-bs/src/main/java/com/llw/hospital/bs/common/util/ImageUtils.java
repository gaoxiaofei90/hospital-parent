package com.llw.hospital.bs.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 
 * @author zcb
 *
 */
public class ImageUtils {
	private static Logger log = LoggerFactory.getLogger(ImageUtils.class);

	/**
	 * 添加水印
	 * 
	 * @param type
	 *            1:文字水印 2:图片水印
	 * @param waterText
	 *            文字水印文本
	 * @param waterPath
	 *            图片水印路径
	 * @param plainPic
	 *            原始图片
	 */
	public static byte[] addWater(int type, String waterText, byte[] waterPic, byte[] plainPic) {
		byte[] res = null;
		try {
			// 目标文件
			Image image = ImageIO.read(new ByteArrayInputStream(plainPic));
			int width = image.getWidth(null);
			int height = image.getHeight(null);
			BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics g = bufferedImage.createGraphics();
			g.drawImage(image, 0, 0, width, height, null);
			if (type == 1) {
				// 图片水印
				g.setColor(Color.RED);
				g.setFont(new Font("Verdana", Font.BOLD, 30));
				g.drawString(waterText, 0, 0);
				g.dispose();
			} else {
				// 水印文件
				Image waterImage = ImageIO.read(new ByteArrayInputStream(waterPic));
				int waterWidth = waterImage.getWidth(null);
				int waterHeight = waterImage.getHeight(null);
				// 添加水印
				g.drawImage(waterImage, 0, 0, waterWidth, waterHeight, null);
			}
			g.dispose();
			// 输出为byte[]
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			ImageIO.write(bufferedImage, "jpg", outStream);
			res = outStream.toByteArray();
			outStream.close();
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
		}
		return res;
	}
	
	/**
	 * 添加水印
	 * 
	 * @param type
	 *            1:文字水印 2:图片水印
	 * @param waterText
	 *            文字水印文本
	 * @param waterPath
	 *            图片水印路径
	 * @param plainPic
	 *            原始图片
	 */
	public static byte[] addWater(int type, String waterText, String waterFilePath, byte[] plainPic) {
		byte[] res = null;
		try {
			// 目标文件
			Image image = ImageIO.read(new ByteArrayInputStream(plainPic));
			int width = image.getWidth(null);
			int height = image.getHeight(null);
			BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = bufferedImage.createGraphics();
			g.drawImage(image, 0, 0, width, height, null);
			if (type == 1) {
				// 图片水印
				g.setColor(Color.RED);
				g.setFont(new Font("Verdana", Font.BOLD, 30));
				g.drawString(waterText, 100, 100);
				g.dispose();
			} else {
				ImageIcon imgIcon = new ImageIcon(waterFilePath);   
				float alpha = 0.8f; // 透明度   
	            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,   
	                    alpha));   
	            g.setPaint(Color.black);
	            // 得到Image对象。   
	            Image waterImage = imgIcon.getImage();  
				int waterWidth = waterImage.getWidth(null);
				int waterHeight = waterImage.getHeight(null);
				int x = (int)(width * 0.5); //"0.5"小数越大，水印越向左移动。   
		        int y = (int)(height * 0.5); //"0.5"小数越大，水印越向上移动。
		        g.drawImage(waterImage, 0, 0, width,   
		        		height, null);
				// 添加水印
				//g.drawImage(waterImage, 0, 0, waterWidth, waterHeight, null);
				
			}
			g.dispose();
			// 输出为byte[]
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			ImageIO.write(bufferedImage, "jpg", outStream);
			res = outStream.toByteArray();
			outStream.close();
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
		}
		String ff = res.toString();
		return res;
	}

	/**
	 * 生成缩略图
	 * 
	 * @param srcImage
	 *            图片文件
	 * @param outImage
	 *            缩略文件
	 * @param width
	 *            缩略宽度
	 * @param hight
	 *            缩略高度
	 * @throws IOException
	 */
	public static byte[] makeThumbnail(byte[] srcImage, int width, int height, String mode) throws IOException {
		// 源文件读取
		InputStream inputStream = new ByteArrayInputStream(srcImage);
		BufferedImage bufferedImage = ImageIO.read(inputStream);

		// 缩略
		if (width > 0 || height > 0) {
			bufferedImage = resize(bufferedImage, width, height);
		}

		// 输出
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		ImageIO.write(bufferedImage, "jpg", outStream);
		return outStream.toByteArray();
	}

	/**
	 * 构建缩略图 数据流
	 * 
	 * @param source
	 *            源文件数据流
	 * @param targetW
	 *            缩略宽度
	 * @param targetH
	 *            缩略高度
	 */
	public static BufferedImage resize(BufferedImage source, int targetW, int targetH) {
		// targetW，targetH分别表示目标长和宽
		int type = source.getType();
		BufferedImage target = null;
		double sx = (double) targetW / source.getWidth();
		double sy = (double) targetH / source.getHeight();
		// 这里想实现在targetW，targetH范围内实现等比缩放。如果不需要等比缩放
		if (sx > sy) {
			sx = sy;
			targetW = (int) (sx * source.getWidth());
		} else {
			sy = sx;
			targetH = (int) (sy * source.getHeight());
		}
		if (type == BufferedImage.TYPE_CUSTOM) {
			ColorModel cm = source.getColorModel();
			WritableRaster raster = cm.createCompatibleWritableRaster(targetW, targetH);
			boolean alphaPremultiplied = cm.isAlphaPremultiplied();
			target = new BufferedImage(cm, raster, alphaPremultiplied, null);
		} else {
			target = new BufferedImage(targetW, targetH, type);
		}
		Graphics2D g = target.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g.drawRenderedImage(source, AffineTransform.getScaleInstance(sx, sy));
		g.dispose();
		return target;
	}

}
