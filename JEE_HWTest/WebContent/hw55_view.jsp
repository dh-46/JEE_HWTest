<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String x = (String)request.getAttribute("x");
	String y = (String)request.getAttribute("y");
	String result = (String)request.getAttribute("result");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>hw55_MVC2_View</title>
</head>
<body>
	<h1>HW17v2</h1>
	<hr>
	<form action="HW17v2">
		<input type="text" name="x" value="<%= x %>"> + 
		<input type="text" name="y" value="<%= y %>">
		<input type="submit" value="=">
		<%= result %>
	</form>
	<hr>
	Copyright. NONE
</body>
</html>