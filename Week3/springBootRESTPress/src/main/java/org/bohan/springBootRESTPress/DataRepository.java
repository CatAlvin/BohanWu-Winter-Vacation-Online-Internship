package org.bohan.springBootRESTPress;

import java.sql.*;

public class DataRepository {
    private final Connection conn;

    public DataRepository(String hostName, String username, String password, String databaseName) {
        this(hostName, username, password, databaseName, 3306);
    }

    public DataRepository(String hostName, String username, String password, String databaseName, int portNumber) {
        String url = String.format("jdbc:mysql://%s:%d/%s", hostName, portNumber, databaseName);
        try {
            this.conn = DriverManager.getConnection(url, username, password);
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //-- 用户获取主页新闻列表
    public Press fetchPressById(long id) throws SQLException {

        try (PreparedStatement pstmt = conn.prepareStatement(
                "SELECT id, title, content FROM press WHERE id = ?")) {
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Press(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("content")
                );
            }
            rs.close(); // 释放资源
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void close() throws SQLException {
        conn.close();
    }
}
