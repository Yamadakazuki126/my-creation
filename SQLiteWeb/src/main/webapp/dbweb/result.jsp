<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="bean.*"%>
<jsp:useBean id="dto" scope="request" class="bean.DTO" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>表示画面</title>
</head>
<body>
	<h2>データを表示</h2>
	<table border="0">
  <tr>
    <th width="50">ID</th>
    <th width="50">名前</th>
    <th width="50">年齢</th>
  </tr>
<%
  for(int i = 0; i < dto.size(); i++){
    dbBean db = dto.get(i);
%>
  <tr>
    <td align="center"><%= db.getID() %></td>
    <td align="center"><%= db.getName() %></td>
    <td align="center"><%= db.getAge() %></td>
  </tr>
<% } %>
</table><br />
</body>
</html>