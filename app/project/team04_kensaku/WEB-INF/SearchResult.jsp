<%@ page import="java.sql.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>検索結果</title>
</head>
<body>

<%
    // 選択されたタグを取得
    String selectedTag = request.getParameter("tag");

    // データベース接続情報
    String url = "jdbc:mysql://localhost:3306/kankouti";
    String user = "root";
    String password = "password";

    // タグに該当する観光地を取得
    List<String[]> resultList = new ArrayList<>();
    try {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, user, password);
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM 観光地 WHERE タグ1 = ?");
        preparedStatement.setString(1, selectedTag);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            String[] result = new String[5];
            result[0] = resultSet.getString("観光地名");
            result[1] = resultSet.getString("説明");
            result[2] = resultSet.getString("画像");
            result[3] = resultSet.getString("タグ1");
            result[4] = resultSet.getString("気分");
            resultList.add(result);
        }

        connection.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
%>

<h2>検索結果</h2>
<p>選択されたタグ: <%= selectedTag %></p>

<% if (resultList.isEmpty()) { %>
    <p>該当する観光地はありません。</p>
<% } else { %>
    <table border="1">
        <tr>
            <th>観光地名</th>
            <th>説明</th>
            <th>画像</th>
            <th>タグ</th>
            <th>気分</th>
        </tr>
        <% for (String[] result : resultList) { %>
            <tr>
                <td><%= result[0] %></td>
                <td><%= result[1] %></td>
                <td><%= result[2] %></td>
                <td><%= result[3] %></td>
                <td><%= result[4] %></td>
            </tr>
        <% } %>
    </table>
<% } %>

</body>
</html>
