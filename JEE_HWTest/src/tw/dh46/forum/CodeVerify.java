package tw.dh46.forum;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tw.dh46.beans.ReCaptcha;

/**
 * 	Servlet implementation class CodeVerify
 * 	負責驗證驗證碼是否正確
 */
@WebServlet("/CodeVerify")
public class CodeVerify extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		response.setContentType("text/html; charset=UTF-8");
		
		
//		HttpSession session = request.getSession(false);
//		String code = (String)session.getAttribute("code");
//		
//		
//		if (ReCaptcha.checkVerifyCode(code, request.getParameter("verifycode"))) {
//			//	驗證通過後轉給資料庫確認資料並寫入資料庫
//			RequestDispatcher rd = request.getRequestDispatcher("DBRegister");
//			rd.forward(request, response);
//		} else {
//			response.sendRedirect("index.jsp");
//		}
		
		
	}

}
