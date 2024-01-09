package Bean;

public class TableNameBean { // TableNameは接続するデータベースのテーブル名で作る
    /*
     * データベースから取得した要素をオブジェクトとしてまとめる
     */

    /*
     * データベースのカラムを入れる変数を定義する
     */
    private String kankoutimei; // 
    private String setumei; // 
    private String gazou; //
    private String tagu; // 
    private String kibun;
    public String getKankoutimei() {
        return kankoutimei;
    }
    public void setKankoutimei(String kankoutimei) {
        this.kankoutimei = kankoutimei;
    }
    public String getSetumei() {
        return setumei;
    }
    public void setSetumei(String setumei) {
        this.setumei = setumei;
    }
    public String getGazou() {
        return gazou;
    }
    public void setGazou(String gazou) {
        this.gazou = gazou;
    }
    public String getTagu() {
        return tagu;
    }
    public void setTagu(String tagu) {
        this.tagu = tagu;
    }
    public String getKibun() {
        return kibun;
    }
    public void setKibun(String kibun) {
        this.kibun = kibun;
    }
    public TableNameBean() {
    }
    public TableNameBean(String kankoutimei, String setumei, String gazou, String tagu, String kibun) {
        this.kankoutimei = kankoutimei;
        this.setumei = setumei;
        this.gazou = gazou;
        this.tagu = tagu;
        this.kibun = kibun;
    }
}

    