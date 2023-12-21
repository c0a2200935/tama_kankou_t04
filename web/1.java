import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TouristSpotSearch {

    public static void main(String[] args) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String db_id = "your_database_username"; // replace with your database username
            String db_password = "your_database_password"; // replace with your database password
            String sql = "SELECT 観光地名 FROM 観光地 WHERE タグ1 = ?"; // SQL query for tag search

            Class.forName("com.mysql.cj.jdbc.Driver");

            // Replace the connection URL with your database information
            con = DriverManager.getConnection("jdbc:mysql://your_database_host:3306/your_database_name?serverTimezone=JST",
                    db_id, db_password);

            // Prompt the user to enter the tag for search
            String tag = "山岳"; // replace with the desired tag
            stmt = con.prepareStatement(sql);
            stmt.setString(1, tag);

            rs = stmt.executeQuery();

            // Output the tourist spots based on the tag search
            while (rs.next()) {
                String touristSpotName = rs.getString("観光地名");
                System.out.println("観光地名: " + touristSpotName);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("An error occurred while accessing the database.");
            }
        }
    }
}
