package tw.dh46.forum;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 	Servlet implementation class Register
 * 	自製討論區
 * 	會員註冊
 * 	1. 使用者輸入資料(前端JS驗證格式)
 * 	2.資料送到後端Register(當成註冊的Controller: 分派工作)
 * 
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	
    public Register() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		Enumeration<String> pNames = request.getParameterNames();
		
		while(pNames.hasMoreElements()) {
			out.println(pNames.nextElement());
		}
	}

}
