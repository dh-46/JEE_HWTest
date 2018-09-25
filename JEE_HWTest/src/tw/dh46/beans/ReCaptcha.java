package tw.dh46.beans;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

/*	圖形驗證產生器
 * 	
 * 	功能(static 方法)
 * 	1. 隨機產生驗證碼(客製化的驗證碼? 自訂字串長度/自訂特殊詞)
 * 	2. 輸出驗證碼圖片(干擾線與點/隨機變換顏色?)
 *	3. 驗證碼驗證方法(servlet端取得驗證碼的方法)
 *
 *	
 */

public class ReCaptcha {
	final private static String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";

	public ReCaptcha() {
		// 建構式沒做事
	}

	// 產生驗證碼 (預設值)
	public static String createVerifyCode() {
		// 預設版本
		return createVerifyCode(5, chars);
	}

	// 產生驗證碼 (自訂長度)
	public static String createVerifyCode(int codeLength) {
		return createVerifyCode(codeLength, chars);
	}

	// 產生驗證碼 (自訂長度與特殊字)
	public static String createVerifyCode(int codeLength, String magicWord) {
		
		String verifyCode = "";
		if (magicWord == "") {
			magicWord = chars;
		}
		for (int i = 0; i < codeLength; i++) {
			int rand = (int)(Math.random()*magicWord.length());
			verifyCode = verifyCode.concat(magicWord.substring(rand, rand + 1));
		}
		return verifyCode;
	}

	// 產生圖片
	/*
	 * 以文字左下角為基準
	 * drawString("字串", X的起始點, Y的起始點) [數字越大越往右下]
	 * 單一字元寬高20*20
	 * 用一常數限定各字元間距避免過分重疊
	 * 第一個亂數決定字串在畫面上的基準點
	 * 第二個亂數決定各個字元上下的位移量
	 * 第三個亂數決定各字元旋轉的量
	 * 
	 */
	public static void generateVerifyPic(int height, int width, OutputStream out, String verifyCode) throws IOException {
		BufferedImage bimg = new BufferedImage(200, 80, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = bimg.createGraphics();

		// background pic
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, bimg.getWidth(), bimg.getHeight());

		// 字串預設高度
		int stringY = 60;
		int stringX = 20;

		// 儲存字串(解密用)
		String key = "";

		for (int i = 0; i < verifyCode.length(); i++) {
			int ranY = (int) (Math.random() * 20 - 20);
			String aChar = verifyCode.substring(i, i + 1);

			Font font = new Font("Monospaced", Font.ITALIC, 20);
			g2d.setFont(font);
			g2d.setColor(Color.BLACK);

			g2d.drawString(aChar, stringX + (i+1)*20, stringY + ranY);
			key = key.concat(aChar);
		}
		ImageIO.write(bimg, "png", out);
		out.flush();
		out.close();
	}


	public static void main(String[] args) {
		String a = ReCaptcha.createVerifyCode(5, "HelloWorld");
		System.out.println(a);
	}
}
