package tw.dh46.forum;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DBRegister
 */
@WebServlet("/DBRegister")
public class DBRegister extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		//	處理手上資料 (Member物件?)
		String account = request.getParameter("account");
		String realname = request.getParameter("realname");
		String email = request.getParameter("email");
		
		//	密碼雜湊
		String password = BCrypt.hashpw(request.getParameter("password"), BCrypt.gensalt());
		
		//	資料寫入資料庫
		createAccount(account, realname, password, email);
		
		//	out.println("註冊成功!");
		//	註冊成功後跳轉到登入畫面
		response.sendRedirect("login.jsp");
	}
	
	//	把資料庫操作獨立成一個方法
	private void createAccount(String account, String realname, String password, String email) {
		
		//	資料庫連線
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Properties props = new Properties();
			props.setProperty("user", "root");
			props.setProperty("password", "root");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/mysqlhw", props);
				
			String sql = "INSERT INTO `forum_account` (`account`,`realname`,`password`,`email`) VALUES (?,?,?,?);";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, account);
			pstmt.setString(2, realname);
			pstmt.setString(3, password);
			pstmt.setString(4, email);
			int update = pstmt.executeUpdate();
			System.out.println("Update: " + update);
		} catch (ClassNotFoundException e) {
			System.out.println(e.toString());
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
	}

}
