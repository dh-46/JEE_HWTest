<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>資策會留言版</title>
</head>
<body>
	<h2>資策會留言版</h2>
	<hr>
	<h3>會員註冊</h3>
	<form action="Register" method="get" id="registerForm">
		<label for="account">帳號:</label> <input type="text" name="account"><br>
		<br> 信箱: <input type="email" name="email"><br>
		<br> 密碼: <input type="password" name="password"><br>
		<br> 真實姓名: <input type=text name="realname"><br>
		<br> 是否同意本討論區使用規範<input type="checkbox" name="agree" value="true"><br>
		<br> <br> <img alt="驗證碼" src="HW_reCaptcha">
		請輸入驗證碼(不分大小寫) : <input type="text" name="verifycode"><br>
		<input type="submit" value="送出">
	</form>
	<hr>
	<h3>會員登入</h3>
	<form action="Login" method="get" id="loginForm">
		<label for="account">帳號:</label> <input type="text" name="account"><br>
		<br> 密碼: <input type="password" name="password"><br>
		<br> 記住我 <input type="checkbox" name="rememberMe" value="true"><br>
		<br> <input type="submit"
			value="登入">
	</form>
</body>
</html>