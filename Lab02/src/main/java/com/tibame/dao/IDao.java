package com.tibame.dao;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

/**
 * 資料存取介面規範
 *
 * @param <T> 泛型應用於不同的資料表。
 */
public interface IDao<T> {
    /**
     * 查詢 T 資料表單筆資料內容。
     *
     * @param sql  SQL 語法。
     * @param args SQL 條件。
     * @return 資料內容。
     * @throws SQLException 語法錯誤例外。
     */
    public T queryForObject(String sql, Object... args) throws SQLException;

    /**
     * 查詢 T 資料表多筆資料內容。
     *
     * @param sql  SQL 語法。
     * @param args SQL 條件。
     * @return 多筆資料內容。
     * @throws SQLException 語法錯誤例外。
     */
    public List<T> queryForList(String sql, Object... args) throws SQLException;

    //強迫注入依存關係的DataSource(連接工廠)setter

    /**
     * 設定強迫注入依存關係的DataSource(連接工廠)的setter。
     *
     * @param datasource 資料連接來源。
     */
    public void setDataSource(DataSource datasource);

    /**
     * 取得 DataSource。
     *
     * @return 資料庫連接源。
     */
    public DataSource getDataSource();

}
