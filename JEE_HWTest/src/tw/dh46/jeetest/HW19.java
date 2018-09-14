package tw.dh46.jeetest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 *	20180908AM2 HW19
 *	MVC2 實作練習 19
 *	負責畫面呈現
 *	=>	樣板使用部屬的方式, 讓使用者無法任意存取
 *
 *	Review Note:
 *	樣板檔案裡的註解如果也有使用到format specifier(e.g. %s) 如下
 *	<!-- %s => 代表要帶入的參數 -->
 *	記得要把註解拿掉, 否則String.format會誤以為那個也是其中一個參數
 *	導致MissingFormatArgumentException: Format specifier '%s' => 多一個沒告訴它
 */

@WebServlet("/HW19")
public class HW19 extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String x = request.getParameter("x");
		String y = request.getParameter("y");
		String result = request.getParameter("result");
		
		try {
			String htmlFile = loadTempView("mycal2.html");
			//	outHTML => format 把參數帶入到%s的位置 (參數位置要照順序)
			String outhtml = String.format(htmlFile, x, y, result);
			out.print(outhtml);
			//out.flush();
		} catch (Exception e) {
			out.println("Server Busy: " + e.toString());
		}
	}
	
	//	載入樣板畫面	(例外拋出)
	private String loadTempView(String fileName) throws Exception {
		//	先取得樣板載入的位置 (之前上傳檔案有用到類似的方法)
		String tempPath = getServletContext().getInitParameter("temp-dir");
		System.out.println(tempPath);
		//	載入樣板 (IO)
		File viewFile = new File(tempPath, fileName);
		
		//	寫出樣板html-1 
//		BufferedInputStream bin = new BufferedInputStream(new FileInputStream(viewFile));
//		byte[] buf = new byte[(int)viewFile.length()];
//		bin.read(buf);
//		bin.close();
//		return new String(buf);
		
		//	寫出樣板html-2 (BufferReader => 會去掉換列符號, 檔案不大的話 速度差異不大)
		BufferedReader reader = new BufferedReader(new FileReader(viewFile));
		StringBuffer sb = new StringBuffer();
		String temp = null;
		while ((temp = reader.readLine())!= null) {
			sb.append(temp);
		}
		reader.close();
		
		return sb.toString();
	}

}
