<%@ page import="java.sql.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>観光地検索</title>
</head>
<body>

<%
    // データベース接続情報
    String url = "jdbc:mysql://localhost:3306/kankouti";
    String user = "root";  // ユーザー名（適切なものに変更してください）
    String password = "password";
    
    // タグの選択肢を取得
    Set<String> tags = new HashSet<>();
    try {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, user, password);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT DISTINCT タグ1 FROM 観光地");
        while (resultSet.next()) {
            tags.add(resultSet.getString("タグ1"));
        }
        connection.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
%>

<h1>観光地検索</h1>
<form action="SearchResult.jsp" method="post">
    <label for="tag">タグ:</label>
    <select id="tag" name="tag">
        <% for (String tag : tags) { %>
            <option value="<%= tag %>"><%= tag %></option>
        <% } %>
    </select>
    <br>
    <input type="submit" value="検索">
</form>

</body>
</html>
