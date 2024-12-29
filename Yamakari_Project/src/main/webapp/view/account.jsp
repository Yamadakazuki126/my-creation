<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ヤマカリ アカウント作成画面</title>
<link rel="stylesheet" href="/Yamakari_Project/view/account.css">
<style>

</style>
</head>
<body>
	<div class="main">
		<div class="login">
			<h2>アカウント作成</h2>
			<p class="msg"><strong>${msg}</strong></p>
			<form action="/Yamakari_Project/account" method="post">
				<p>アカウント名</p>
				<input type="text" name="account" placeholder="アカウント名を作成" required/><br/>			
				<p>パスワード</p>
				<input type="password" name="pass" placeholder="パスワードを作成" required/><br/>
				 <input class="submit" type="submit" value="作成" /><br/>
				 <a href="/Yamakari_Project/view/login.jsp">ログイン画面へ</a> 
			</form>
		</div>
	</div>
</body>
</html>