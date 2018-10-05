package tw.dh46.jeetest;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MsSQLTest
 */
@WebServlet("/MsSQLTest")
public class MsSQLTest extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;"
					+ "databaseName=AddressBook_Pr", "root", "root");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from userinfo");
			if (rs.next()) {
				//String a = rs.getString("count");
				//System.out.println("count: " + a);
				out.println(rs.getString("cname"));
			}
			
			System.out.println("Driver OK");
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
		}
		
	}

}
