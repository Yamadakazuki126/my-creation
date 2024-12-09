<%@page contentType="text/html;charset=utf-8" %>
<%@page import="bean.*" %>
<jsp:useBean id ="sdto" scope="request" class="bean.StudentDTO" />
<jsp:useBean id ="msg" scope="request" class="java.lang.String" />
<html>
  <head>
    <title>表示画面</title>
  </head>
<body>
<h2><%= msg %></h2>
<table border="0">
  <tr>
    <th width="50">番号</th>
    <th width="50">名前</th>
    <th width="50">点数</th>
  </tr>
<%
  for(int i = 0; i < sdto.size(); i++){
    StudentBean sb = sdto.get(i);
%>
  <tr>
    <td align="center"><%= sb.getNo() %></td>
    <td align="center"><%= sb.getName() %></td>
    <td align="center"><%= sb.getScore() %></td>
  </tr>
<% } %>
</table><br />
<a href="/Sampledb/dbweb/editstudent.html">戻る</a>
</body>
</html>
