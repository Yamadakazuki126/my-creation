<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="msg" scope="request" class="java.lang.String" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ヤマカリ ログイン画面</title>
<link rel="stylesheet" href="/Yamakari_Project/view/account.css">
</head>
<body>
	<div class="main">
		<h1>Yamakari</h1>
		<div class="login">
			<h2>ログイン</h2>
			<p class="msg"><strong><%= msg %></strong></p>
			<form action="/Yamakari_Project/login" method="post">
				<p>アカウント名</p>
				<input type="text" name="account" placeholder="アカウント名を入力" /><br/>
				<p>パスワード</p>
				<input type="password" name="pass" placeholder="パスワードを入力" /><br/>
				 <input class="submit" type="submit" value="ログイン" /><br/>
				 <a href="/Yamakari_Project/view/account.jsp">アカウント作成ページへ</a> 
			</form>
		</div>
	</div>
</body>
</html>