package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;


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

        try (BufferedReader br = new BufferedReader(new FileReader(getServletContext().getRealPath("/data/kannkouti.txt")))) {
            String line;
            while ((line = br.readLine()) != null) {
                // 1行ごとにデータを解析し、TouristSpotオブジェクトを作成
                String[] parts = line.split("\\|");
                String name = parts[0].trim();
                List<String> spotTags = Arrays.asList(parts[1].trim().split(","));

                TouristSpot spot = new TouristSpot();
                spot.setName(name);
                spot.setTags(spotTags);

                // タグにマッチするか確認
                if (containsAllTags(spotTags, Arrays.asList(tags))) {
                    results.add(spot);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return results;
    }

    private boolean containsAllTags(List<String> spotTags, List<String> searchTags) {
        // タグにマッチするか確認
        return spotTags.containsAll(searchTags);
    }
}
