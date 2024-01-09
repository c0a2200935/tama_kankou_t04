package DAO;

import java.sql.*;
import java.util.ArrayList;

import Bean.TableNameBean;

public class TableNameDAO { // TableNameは接続するデータベースのテーブル名で作る
    /*
     * データベースとJavaを接続するクラス
     */

    /*
     * 接続するデータベースのURL, ユーザとパスワードを設定する
     */
    
    private final String URL = "jdbc:mysql://pan_3db/pan_3db"; // jdbc:mysql://接続するサーバー名/接続するデータベース名 
                                                                                 // ↑↑接続するURLの指定方法
    private final String USER = "pan"; // データベースサーバにアクセスするユーザ名
    private final String PASS = "pan"; // 上で接続するユーザのパスワード
    private Connection con = null;

    public void connect() {
        try {
            // Java->MySQLへお話をするための準備
            Class.forName("com.mysql.jdbc.Driver"); // com.mysql.jdbc.Driver com.mysql.cj.jdbc.Driver
            con = DriverManager.getConnection(URL, USER, PASS);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<TableNameBean> select() {
        /*
         * データベースの要素を全て取得する関数
         */
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<TableNameBean> dto = new ArrayList<TableNameBean>(); // データベースから取得した要素達を入れておくリスト
        String sql = "select * from 観光地"; // データベースサーバで実行するSQL文
        try {
            connect();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);                        // ここでSQLを実行
            while (rs.next()) {                                 // 要素が見つかった場合、1つずつ要素を取り出す
                TableNameBean bean = new TableNameBean();       // 要素を入れるオブジェクトを作成
                bean.setKankoutimei(rs.getString("観光地名"));        // データベースのカラム名から値を取得してセット
                bean.setSetumei(rs.getString("説明")); // 上に同じく
                bean.setGazou(rs.getString("画像")); // 上に同じく
                bean.setTagu(rs.getString("タグ1"));
                bean.setKibun(rs.getString("気分"));
                dto.add(bean);                                  // データベースから取得した要素をリストに加える
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally { // データベースとの接続を解除したりする
            try {
                if (rs != null)
                    rs.close();
                if (stmt != null)
                    stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        disconnect();

        return dto;
    }

    public ArrayList<TableNameBean> selectWithTagu(String tagu, String kibun) {
        /*
         * データベースから、引数で指定したidの要素を取得する関数
         */
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<TableNameBean> dto = new ArrayList<TableNameBean>();
        String sql = "select * from 観光地 WHERE タグ1 = \"" + tagu +"\" OR 気分 = \"" + kibun+"\""; // 実行するSQL文。
        try {
            connect();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);                        // ここでSQLを実行
            while (rs.next()) {                                 // 要素が見つかった場合、1つずつ要素を取り出す
                TableNameBean bean = new TableNameBean();       // 要素を入れるオブジェクトを作成
                bean.setKankoutimei(rs.getString("観光地名"));        // データベースのカラム名から値を取得してセット
                bean.setSetumei(rs.getString("説明")); // 上に同じく
                bean.setGazou(rs.getString("画像")); // 上に同じく
                bean.setTagu(rs.getString("タグ1"));
                bean.setKibun(rs.getString("気分"));  
                dto.add(bean);                                  // データベースから取得した要素をリストに加える
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (stmt != null)
                    stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        disconnect();
        return dto;
    }



    public void disconnect() {
        /*
         * データベースの接続を切断する
         */
        try {
            if (con != null)
                con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
