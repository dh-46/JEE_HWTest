package tw.dh46.jeetest;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tw.dh46.jeetest.HW18;

/*
 *	20180909PM2 HW17v2
 *	試做MVC架構
 * 	假設要處理複雜的運算, 將工作交給18號
 * 	Model => 18 : java class
 * 	View => 19 改成hw55.jsp : 顯示資料出去
 * 	Control => 17 : 對外營業 & 負責控制 (改用setAttribute)
 */
@WebServlet("/HW17v2")
public class HW17v2 extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		//	0.	接收資訊
		String x = request.getParameter("x");
		String y = request.getParameter("y");
		
		//	0.	處理資訊 (預先準備)
		if ( x == null) {
			x = "0";
		}
		
		if (y == null) {
			y = "0";
		}
		
		//	1.	資料交給Model處理
		HW18 model = new HW18(x, y);
		int result = model.add();
		
		//	2.	資料交給View(19)呈現
		//	19號現在擁有x,y,result
		RequestDispatcher rd = request.getRequestDispatcher("hw55_view.jsp");
		request.setAttribute("x", x);
		request.setAttribute("y", y);
		request.setAttribute("result", result+"");
		
		rd.forward(request, response);
	}
}
