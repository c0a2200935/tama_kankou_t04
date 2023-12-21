import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 入力されたタグを取得
        String tags = request.getParameter("tags");

        // タグをカンマで分割
        String[] tagArray = tags.split(",");

        // データベースから検索結果を取得
        List<TouristSpot> results = searchDatabase(tagArray);

        // 検索結果をリクエストにセット
        request.setAttribute("results", results);

        // JSPに転送
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    private List<TouristSpot> searchDatabase(String[] tags) {
        List<TouristSpot> results = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(getServletContext().getRealPath("/data/tourist_spots.txt")))) {
            String line;
            while ((line = br.readLine()) != null) {
                // 1行ごとにデータを解析し、TouristSpotオブジェクトを作成
                String[] parts = line.split("\\|");
                String name = parts[0].trim();
                String description = parts[1].trim();
                String spotTags = parts[2].trim();
                
                TouristSpot spot = new TouristSpot();
                spot.setName(name);
                spot.setDescription(description);
                spot.setTags(spotTags);

                // タグにマッチするか確認
                if (containsAllTags(spotTags, tags)) {
                    results.add(spot);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return results;
    }

    private boolean containsAllTags(String documentTags, String[] searchTags) {
        // タグにマッチするか確認
        for (String searchTag : searchTags) {
            if (!documentTags.contains(searchTag.trim())) {
                return false;
            }
        }
        return true;
    }
}
