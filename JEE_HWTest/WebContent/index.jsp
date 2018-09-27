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
	<form action="Controller" method="get" id="register">
		<label for="account">帳號:</label>
		<input type="text" name="account"><br><br>
		信箱: <input type="email" name="email"><br><br> 
		密碼: <input type="password" name="password"><br><br> 
		真實姓名: <input type=text name="realname"><br><br>
		是否同意本討論區使用規範<input type="checkbox" name="agree" value="true"><br><br>
		<hr>
		<img alt="驗證碼" src="HW_reCaptcha"><br>
		請輸入驗證碼(不分大小寫) :    <input type="text" name="verifycode"><br>
		<input type="submit" value="送出">
	</form>
</body>
</html>