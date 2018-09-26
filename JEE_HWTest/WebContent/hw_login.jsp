<%@page import="tw.dh46.beans.ReCaptcha"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<jsp:useBean id="recaptcha" class="tw.dh46.beans.ReCaptcha"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HW_Login</title>
</head>
<body>
	<%
		String code = (String)session.getAttribute("code");
		
		
		if (ReCaptcha.checkVerifyCode(code, request.getParameter("userAns"))) {
			out.println("OK");
		} else {
			response.sendRedirect("hw_loginpic.html");
		}
	%>
</body>
</html>