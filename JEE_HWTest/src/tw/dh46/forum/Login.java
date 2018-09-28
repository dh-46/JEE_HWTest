package tw.dh46.forum;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



/**
 * Servlet implementation class Login 負責處理登入的controller
 */
@WebServlet("/Login")
public class Login extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 登入後要做的事
		//	1. 	驗證登入的帳號密碼(寫成static方法)
		//	2.	驗證通過且有設定記住我的	=> 設定session for Remember Me
		//	3.	設定完後forward給view(會員登入後會看到的畫面)
		//	4.	會員畫面如何動態呈現訊息?
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		System.out.println(account + ":" + password);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Properties info = new Properties();
			info.setProperty("user", "root");
			info.setProperty("password", "root");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/mysqlhw", info);
			
			// 確認帳號是否存在, 如果帳號存在就去比對
			if (checkAccount(account, conn)) {
				out.println("Account exist");
				String sql = "SELECT account, password FROM `forum_account` WHERE account = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, account);
				ResultSet rs = pstmt.executeQuery();
				rs.next();
				String hashed = rs.getString("password");
				String email = rs.getString("email");
				String realname = rs.getString("realname");
						
				if (BCrypt.checkpw(password, hashed)) {
					//	產生Member物件 以利登入後的servlet拿資料 不用再到資料庫撈?
					MemberInfo member = new MemberInfo(account, email, realname);
					request.setAttribute("member", member);
					
					RequestDispatcher rd = request.getRequestDispatcher("MemberView");
					rd.forward(request, response);
				}
			} else {
					 response.sendRedirect("index.jsp");
//					 out.println("Password invalid!");
			}
			
		
		}catch(

	Exception e)
	{
		out.print(e.toString());
	}

	// 確認帳號是否存在
	// 文字密碼轉換成編碼=>比對
	// 確認是否有RM 有=>儲存session&cookie

	}

	private static boolean checkAccount(String account, Connection conn) throws SQLException {
		String sql = "SELECT count(*) FROM `forum_account` WHERE account = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, account);
		ResultSet rs = pstmt.executeQuery();
		rs.next();

		if (rs.getInt(1) == 1) {
			return true;
		}

		return false;
	}

	private static boolean checkPassword(String account, String password, Connection conn) throws SQLException {
		boolean isCurrect = false;

		String sql = "SELECT account, password FROM `forum_account` WHERE account = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, account);
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		String hashed = rs.getString("password");
		String email = rs.getString("email");
		String realname = rs.getString("realname");

		if (BCrypt.checkpw(password, hashed)) {
			// 產生Member物件 以利登入後的servlet拿資料 不用再到資料庫撈?
			MemberInfo member = new MemberInfo(account, email, realname);
			isCurrect = true;
		}

		return isCurrect;
	}
}
