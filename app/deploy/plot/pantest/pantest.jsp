<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, Bean.*"%>
<html>

<body>
hello <br>
<%
    // javaプログラムの埋め込み
    ArrayList<PanTestBean> list = (ArrayList<PanTestBean>)request.getAttribute("pantest");
    Iterator<PanTestBean> ite = list.iterator();

    while(ite.hasNext()){
        PanTestBean bean = ite.next();
%>

<%=bean.getPanId()%> <br>

<%
    }
%>



</body>