<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="bean.GoodsBean" %>
<%@page import="java.util.ArrayList" %>
<%@page import="bean.GoodsDTO" %>

<jsp:useBean id="gdto" scope="request" class="bean.GoodsDTO" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ヤマカリ ホーム画面</title>
<link rel="stylesheet" href="/Yamakari_Project/view/style.css">
<link rel="stylesheet" href="/Yamakari_Project/view/styleJs.css">
</head>
<body>
<header>
		<div class="top">
			<h1>Yamakari</h1>
			<small>〜山田がつくるECサイト〜</small>
			<li class="userInfo">
				<p>user: ${name}</p>
				<p>残高: ${money}Ypoint</p>
			</li>
		</div>
	</header>
	<div class="main">
		<ul class="main-upper">
			<h2>ホーム</h2>
			<li>HOME</li>
			<li>ACCOUNT</li>
			<li><a href="/Yamakari_Project/view/charge.jsp">CHARGE</a></li>
			<li><a href="/Yamakari_Project/view/listing.html">LISTING</a></li>
			<li><a href="/Yamakari_Project/view/login.jsp">LOGIN</a></li>
			<li>INFOMATION</li>
			<form action="javaのサーブレット" method="post" >
				<input class="search" type="text" name="search" placeholder=商品名を検索 size="20" />
				<input class="submit" type="submit" name="btn" value="検索" />
			</form>
		</ul>
		<div class="main-middle">
			<div class="goodses">
			
				<%
					ArrayList<GoodsBean> list = gdto.getList();
				
					for(int i = 0; i < gdto.size(); i++) {
						GoodsBean gb = gdto.get(i);
				%>
					<li class="goods">
						<article>
							<form>
								<img src="/Yamakari_Project/view/img/<%= gb.getFileName() %>">
								<p class="name"><%= gb.getName() %></p>
								<p class="price"><%= gb.getPrice() %>Ypoint</p>
								<input  id="buy" class="buy" type="submit" value="BUY" name="id_<%= gb.getID() %>"/>
							</form>
						</article>
					</li>
				<% } %>
				
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
	<!--購入ボタンが押された時-->
	<div id="modal" class="modal">
	    <div class="modal-content">
	        <h2>購入確認</h2>
	        <p>この商品を購入しますか？</p>
			<form id="buy-form" action="/Yamakari_Project/index" method="post">
    			<button type="submit" id="confirm-buy" name="product_id">購入を確定する</button>
			</form>
	    </div>
	</div>
	<script src="/Yamakari_Project/view/index.js"></script>
</body>
</html>