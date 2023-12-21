<%@page contentType="text/html;charset=utf-8" %>
<%@page import="Bean.*" %>

<jsp:useBean id ="sdto" scope="request" class="Bean.ShopsDTO" />

<html>

<head>
  <title>店舗情報</title>
</head>

<body>

  <header></header>

  <% ShopsBean sb = sdto.get(0); %>

  <p>店舗名：<%= sb.getShopName()%></p>
  <p>カテゴリ：<%= sb.getCategory()%></p>
  <p>アクセス：<%= sb.getAccess()%></p>
  <p>住所：<%= sb.getAddress()%></p>
  <p>電話：<%= sb.getTel()%></p>
  <p>ホームページ：<%= sb.getUrl()%></p>
  <p><%= sb.getInfo()%></p>

  <footer></footer>

</body>
</html>
