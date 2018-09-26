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
	//	扣掉容易搞混的Oo0l
	final private static String chars = "ABCDEFGHIJKLMNPQRSTUVWXYZabcdefghijkmnpqrstuvwxyz123456789";

	public ReCaptcha() {
		// 建構式沒做事
	}

	// 產生驗證碼 (預設值)
	public static String createVerifyCode() {
		// 預設版本
		return createVerifyCode(4, chars);
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
		BufferedImage bimg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = bimg.createGraphics();

		//  背景圖片
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, bimg.getWidth(), bimg.getHeight());
		//	圖片邊框
		g2d.setColor(Color.WHITE);
		g2d.fillRect(1, 1, bimg.getWidth()-2, bimg.getHeight()-2);
		
		//	干擾點
		
		
		
		
		// 字串預設高度
		int stringY = (int)(height*0.75);
		int stringX = (int)(width*0.05);
		
		//	字體隨圖片大小變化的最大值
		int fontMaxRand = (int)(height/2);
		
		//	字體最小值
		int fontMin = (int)(height*0.75);
		
		//	字體間隔
		int fontGap = (int)(width/verifyCode.length()*0.8);
		
		//	字串變化控制
		for (int i = 0; i < verifyCode.length(); i++) {
			//	隨機顏色
			int r = (int)(Math.random()*200);
			int g = (int)(Math.random()*200); 
			int b = (int)(Math.random()*200);
			Color randColor = new Color(r, g, b);
			
			int fontSize = (int)(Math.random()*fontMaxRand+fontMin);
			//int ranY = (int)(Math.random()*fontMin-fontMin);
			String aChar = verifyCode.substring(i, i + 1);

			Font font = new Font("Monospaced", Font.ITALIC, fontSize);
			g2d.setFont(font);
			g2d.setColor(randColor);

			g2d.drawString(aChar, stringX + (i)*fontGap, stringY);
		}
		
		//	寫出至串流response.getOutputStream?
		ImageIO.write(bimg, "png", out);
		out.flush();
		out.close();
	}
	
	
	//	驗證驗證碼是否正確
	public static boolean checkVerifyCode(String code, String userInput) {
		boolean codeIsCorrect = false;
		if (userInput != "" || userInput != null) {
			code = code.toLowerCase();
			userInput = userInput.toLowerCase();
			if (code.equals(userInput)) {
				codeIsCorrect = true;
			}
		}
		return codeIsCorrect;
	}
}
