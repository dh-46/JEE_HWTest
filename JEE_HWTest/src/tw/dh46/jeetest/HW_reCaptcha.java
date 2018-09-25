package tw.dh46.jeetest;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tw.dh46.beans.ReCaptcha;

/*
 *	HW_reCaptcha
 */

@WebServlet("/HW_reCaptcha")
public class HW_reCaptcha extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("image/png");
		
//		BufferedImage bimg = new BufferedImage(200, 80, BufferedImage.TYPE_INT_RGB);
//		Graphics2D g2d = bimg.createGraphics();
//		
//		//	background pic
//		g2d.setColor(Color.BLUE);
//		g2d.fillRect(0, 0, bimg.getWidth(), bimg.getHeight());
//		
//		//	好像其實可以使用ASCII Code?
////		String[] alphabets = {
////			"A", "B", "C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z",
////			"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"
////		};
//		
//		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
//		
//		
//		
//		//	字串預設高度
//		int stringY = 60;
//		int stringX = 20;
//		
//		//	儲存字串(解密用)
//		String key = "";
//		
//		for (int i=20; i <= 120; i = i + 20) {
//			int ranC = (int)(Math.random()*62);
//			int ranY = (int)(Math.random()*20-20);
//			String aChar = chars.substring(ranC, ranC+1);
//			
//			Font font = new Font("Monospaced", Font.ITALIC, 20);
//			g2d.setFont(font);
//			g2d.setColor(Color.YELLOW);
//			
//			g2d.drawString(aChar, stringX + i, stringY + ranY);
//			//	以文字左下角為基準
//			//	drawString("字串", X的起始點, Y的起始點) [數字越大越往右下]
//			//	單一字元寬高20*20
//			//	用一常數限定各字元間距避免過分重疊
//			//	第一個亂數決定字串在畫面上的基準點
//			//	第二個亂數決定各個字元上下的位移量
//			//	第三個亂數決定各字元旋轉的量
//			key = key.concat(aChar);
//		}
//		System.out.println(key);
//		
//		OutputStream out = response.getOutputStream();
//		ImageIO.write(bimg, "png", out);
//		out.flush();
//		out.close();
		
		ReCaptcha.reCaptcha(response);
	}

}
