<%@page contentType="text/html;charset=utf-8" %>
<%@page import="java.util.Collections" %>
<%@page import="java.util.Comparator" %>
<%@page import="bean.StudentBean" %>
<%@page import="java.util.ArrayList" %>
<%@page import="bean.StudentDTO" %>

<jsp:useBean id="sdto" scope="request" class="bean.StudentDTO" />
<jsp:useBean id="msg" scope="request" class="java.lang.String" />
<html>
  <head>
    <title>表示画面</title>
  </head>
<body>
<h2><%= msg %></h2>

<%
  // sdtoからlistを取得
  ArrayList<StudentBean> students = sdto.getList();

  // id順に並べ替え
  Collections.sort(students, new Comparator<StudentBean>() {
      @Override
      public int compare(StudentBean sb1, StudentBean sb2){
          return Integer.compare(sb1.getNo(), sb2.getNo()); // id順に並べ替え
      }
  });
%>

<table border="0">
  <tr>
    <th width="50">番号</th>
    <th width="50">名前</th>
    <th width="50">教室</th>
    <th width="50">点数</th>
  </tr>
<%
  for (int i = 0; i < sdto.size(); i++) {
    StudentBean sb = sdto.get(i);
    /*if (sb.getNo() == 0) {
    	continue;
    }*/
%>
  <tr>
    <td align="center"><%= sb.getNo() %></td>
    <td align="center"><%= sb.getName() %></td>
    <td align="center"><%= sb.getRoom() %></td>
    <td align="center"><%= sb.getScore() %></td>
  </tr>
<% } %>
</table><br />
<a href="/SQLiteWeb/dbweb/editstudent.html">戻る</a>
</body>
</html>
