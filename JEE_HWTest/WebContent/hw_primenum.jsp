<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>0908HomeWork: 質數表</title>
</head>
<body>
	<h2>0908HomeWork: 質數表</h2>
	<hr>
	<form>
		請輸入質數表最大值: <input type="text" name="number" value="">
		<input type="submit" value="送出">
	</form>
	<br>
	<table border="2" width="100%">
		<tr>
			<%
			String input = request.getParameter("number");
			if (input == null) {
				input="100";
			}
			
			int magic = ((Integer.parseInt(input)-10)/10);
			
			
			for (int j = 0; j <=magic; j++) {
				out.println("<tr>");
				for (int i = 1; i <=10; i++) {
					boolean isPrime = true;
					for (int k = 2; k < i+(10*j); k++) {
						//	在2~自己之間能夠被某數整除 => 非質數
						if ((i+(10*j)) % k == 0) {
							isPrime = false;
							break;
						}
					}
					out.println("<td style='text-align:center; width:10%; background-color:"+ (isPrime?"yellow":"pink") +"'>");
					out.println(i+(10*j));
					out.println("</td>");
				}
				out.println("</tr>");
			}
				
			%>
		</tr>
	</table>
</body>
</html>