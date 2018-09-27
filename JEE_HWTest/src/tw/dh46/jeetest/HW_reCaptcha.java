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
import javax.servlet.http.HttpSession;

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
		
		
		String code = ReCaptcha.createVerifyCode();
		HttpSession session = request.getSession();
		session.setAttribute("code", code);
		ReCaptcha.generateVerifyPic(40, 80, response.getOutputStream(), code);
		
	}

}
