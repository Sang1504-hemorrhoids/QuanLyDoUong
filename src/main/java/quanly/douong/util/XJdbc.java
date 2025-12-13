package quanly.douong.util;

import java.sql.*;

public class XJdbc {
    private static Connection connection;

    public static Connection openConnection() {
        var dburl = "jdbc:sqlserver://localhost:1433;databaseName=QLDO;encrypt=false";
        var username = "sa";
        var password = "sang1504.";

        try {
            if (!XJdbc.isReady()) {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                connection = DriverManager.getConnection(dburl, username, password);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }

    // Đóng kết nối
    public static void closeConnection() {
        try {
            if (XJdbc.isReady()) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Kiểm tra kết nối đã sẵn sàng hay chưa
     *
     * @return true nếu kết nối đã được mở
     */
    public static boolean isReady() {
        try {
            return (connection != null && !connection.isClosed());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Thao tác dữ liệu
     *
     * @param sql    câu lệnh SQL (INSERT, UPDATE, DELETE)
     * @param values các giá trị cung cấp cho các tham số trong SQL
     * @return số lượng bản ghi đã thực hiện
     * @throws RuntimeException không thực thi được câu lệnh SQL
     */
    public static int executeUpdate(String sql, Object... values) {
        try {
            var stmt = XJdbc.getStmt(sql, values);
            return stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Truy vấn một giá trị
     *
     * @param <T>    kiểu dữ liệu kết quả
     * @param sql    câu lệnh SQL (SELECT)
     * @param values các giá trị cung cấp cho các tham số trong SQL
     * @return giá trị truy vấn hoặc null
     * @throws RuntimeException không thực thi được câu lệnh SQL
     */
    public static <T> T getValue(String sql, Object... values) {
        try {
            var resultSet = XJdbc.executeQuery(sql, values);
            if (resultSet.next()) {
                return (T) resultSet.getObject(1);
            }
            return null;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Truy vấn dữ liệu
     *
     * @param sql    câu lệnh SQL (SELECT)
     * @param values các giá trị cung cấp cho các tham số trong SQL
     * @return tập kết quả truy vấn
     * @throws RuntimeException không thực thi được câu lệnh SQL
     */
    public static ResultSet executeQuery(String sql, Object... values) {
        try {
            var stmt = XJdbc.getStmt(sql, values);
            return stmt.executeQuery();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Tạo PreparedStatement từ câu lệnh SQL/PROC
     *
     * @param sql    câu lệnh SQL/PROC
     * @param values các giá trị cung cấp cho các tham số trong SQL/PROC
     * @return đối tượng đã tạo
     * @throws SQLException không tạo được PreparedStatement
     */
    public static PreparedStatement getStmt(String sql, Object... values) throws SQLException {
        var conn = XJdbc.openConnection();
        var stmt = sql.trim().startsWith("{") ? conn.prepareCall(sql) : conn.prepareStatement(sql);
        for (int i = 0; i < values.length; i++) {
            stmt.setObject(i + 1, values[i]);
        }
        return stmt;
    }

    // Get key auto_increment
    public static int executeInsertAndGetId(String sql, Object... args) {
        try (var conn = XJdbc.openConnection();
             PreparedStatement ps = conn.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)) {

            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}
