
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.TableNameBean;
import DAO.TableNameDAO;

import javax.servlet.RequestDispatcher;

@WebServlet("/tamakankou") // tamakankouでアクセスできるようにする
public class SearchkensakuServlet extends HttpServlet {
    public SearchkensakuServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // HTTP応答のエンコード設定
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        // 転送するURLを指定する
        String forwardURL = null; 
        String kibun = request.getParameter("kibun");
        String tagu = request.getParameter("tagu");


        // DAOオブジェクトを生成(データベースに接続する)
        TableNameDAO dao = new TableNameDAO();

        // // 接続したデータベースの要素をすべて取得する
        // ArrayList<TableNameBean> dto = dao.select();

        // データベースの要素を条件検索する。
        ArrayList<TableNameBean> dto = dao.selectWithTagu(tagu,kibun);


        // requestへセットする．
        request.setAttribute("tamakankou", dto);
        request.setAttribute("test1", tagu);
        request.setAttribute("test2", kibun);

        forwardURL = "/WEB-INF/jsp/tamakensaku.jsp";

        // 外部ファイルに転送する準備
        RequestDispatcher dispatcher = request.getRequestDispatcher(forwardURL);
        // 外部ファイルに表示処理を任せる
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
