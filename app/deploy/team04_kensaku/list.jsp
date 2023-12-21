<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, pnw.*"%>
<html>  
    <head>
        <meta charset="UTF-8">
        <title>一覧と更新処理</title>
    </head>      
<body>
<h1>一覧と更新処理</h1>
<table border="1">
<tr>
    <td>ID</td>
    <td>UserID</td>
    <td>Password</td>
</tr>
<%
//Servletで設定されたリストを取得する．
ArrayList<UserInfoBean> list = (ArrayList<UserInfoBean>)request.getAttribute("userlist");
Iterator<UserInfoBean> ite = list.iterator();

//結果の表示
while(ite.hasNext()){
    UserInfoBean bean = ite.next();
%>
<%-- HTML内にJSPコードをスクリプト式として埋め込む--%>
    <tr>
    <td><%=bean.getID()%></td>
    <td><%=bean.getUserID()%></td>
    <td><%=bean.getPassword()%></td>
    </tr>
<%
}
%>
</table>
<hr/>
追加の場合は,IDは自動設定されるので指定しないでください．
更新と削除は，ID指定が必須．
<form action="/g21/ListProcessServlet" method="post">
ID: <input type="text" name="id"><br>
ユーザ名：<input type="text" name="userid" ><br>
パスワード：<input type="password" name="pass" ><br>
<input type="submit" name="btn" value="追加">
<input type="submit" name="btn" value="更新">
<input type="submit" name="btn" value="削除">
</form>
</body>
</html>
