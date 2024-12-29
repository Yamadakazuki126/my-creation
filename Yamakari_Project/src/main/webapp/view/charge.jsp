<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ヤマカリ　ポイントチャージ</title>
<link rel="stylesheet" href="/Yamakari_Project/view/style.css">
<link rel="stylesheet" href="/Yamakari_Project/view/listing.css">
</head>
<body>
	<header>
		<div class="top">
			<h1>Yamakari</h1>
			<small>〜山田がつくるECサイト〜</small>
		</div>
	</header>
	<div class="main">
		<ul class="main-upper">
			<h2>出品</h2>
			<li><a href="/Yamakari_Project/view/index.jsp">HOME</a></li>
			<li>ACCOUNT</li>
			<li>CHARGE</li>
			<li><a href="/Yamakari_Project/view/listing.html">LISTING</a></li>
			<li><a href="/Yamakari_Project/view/login.jsp">LOGIN</a></li>
			<li>INFOMATION</li>
			<form action="javaのサーブレット" method="post">
				<input class="search" type="text" name="search" placeholder=商品名を検索 size="20" />
				<input class="submit" type="submit" name="btn" value="検索" />
			</form>
		</ul>	
		<div class="main-middle">
			<div class="listing_form">
				<form action="/Yamakari_Project/index" method="post">
					<li class="listing_list">
						<h3>チャージする額を入力してください</h3>
						<h4>${msg}</h4>
						<p>チャージ</p><br/>
						<input class="list_input" type="number" name="incomPoint" placeholder="チャージポイントを入力" required/><br/>
						<input id="list" class="listing_btn" type="submit" value="チャージ" />
					</li>
				</form>
			</div>
			<div class="sidebar">
				<aside class="categories">
					<h3>カテゴリー</h3>
					<li class="category">fashion</li>
					<li class="category">toys</li>
					<li class="category">book</li>
					<li class="category">foods</li>
					<li class="category">history</li>
					<li class="category">stationery</li>
					<li class="category">musical instruments</li>
					<li class="category">cars</li>
					<li class="category">houses</li>
					<li class="category">planets</li>
				</aside>
			</div>
		</div>
	</div>
</body>
</html>