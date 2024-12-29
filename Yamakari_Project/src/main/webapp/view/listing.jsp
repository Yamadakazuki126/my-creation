<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ヤマカリ</title>
<style>
@charset "UTF-8";
body {
	background-color: lightgoldenrodyellow;
}
.main-middle {
	backGround-color: white;
	margin: 0 auto;
	width: 500px;
}
h2 {
	color: gray;
}
.msg {
	color: red;
}
p{
	margin-bottom: 0%;
}
.listing_btn {
	background-color: orangered;
	width: 150px;
	height: 25px;
	margin-left: 170px;
	margin-top: 20px;
}
</style>
</head>
<body>
<div class="main-middle">
	<h2>出品情報</h2>
	<p class="name">商品名: <strong>${name}</strong></p>
	<p class="price">価格: <strong>${price}</strong></p>
	<p class="category">カテゴリー: <strong>${category}</strong></p>
	<p class="msg">入力情報を確認し,商品の写真をアップロードしてください</p>
	<form action="/Yamakari_Project/upload" method="post" enctype="multipart/form-data">
		<p>商品の写真</p><br/>
		<input class="list_input" type="file" name="image" placeholder="写真をアップロード" required/><br/>
		<input class="listing_btn" type="submit" value="確定" />
	</form>
</div>
</body>
</html>