<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*,Bean.*,DAO.*" %> <%-- javaでインポートするパッケージを指定 --%>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>テストページ</title>
        </head>

        <body>
        <%-- Javaのプログラム書く --%>
        <%
            String tagu = (String)request.getAttribute("test1");
            String kibun = (String)request.getAttribute("test2");
            %>
            <p>あなたのタグは<%=tagu%>、気分は<%=kibun%>です。</p>
            <%
                // Servletでセットした値を取得する　型指定忘れずに！
                ArrayList<TableNameBean> beanlist = (ArrayList<TableNameBean>)request.getAttribute("tamakankou");
                // iterator型に変換する
                Iterator<TableNameBean> beanite = beanlist.iterator();            
            %>

        <%-- 値を表示する --%>
            <table>
                <tr><td>観光地名</td><td>説明</td><td>画像</td></tr>

                <%-- 値を表示するためにjavaを書く --%>
                <% 
                while(beanite.hasNext()){ // 要素を一つずつ取っていく
                    TableNameBean bean = beanite.next(); // 変数beanに要素を入れる
                %>
                <%-- この下はHTML。tdの中にJavaのプログラムを埋め込む --%>
                    <tr>
                        <td> <%=bean.getKankoutimei()%> </td>
                        <td> <%=bean.getSetumei()%> </td>
                        <td> <img src= <%=bean.getGazou()%> alt ="画像" width="300"> </td>
                    </tr>

                <%
                } // 25行目の while(beanite.hasNext()){ を閉じる括弧
                %>
            
            </table>
            

        </body>

        </html>