import java.sql.*;

public class clsCon {

    private static Connection conn = null;

    private clsCon() {

        String url = "jdbc:mysql://localhost:3306/dbkey";
        String driver = "com.mysql.jdbc.Driver";
        String usuario = "root";
        String password = "";

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, usuario, password);
        } catch (ClassNotFoundException | SQLException e) {

            System.out.print(e);
        }
    }

    public static Connection getConnection() {

        if (conn == null) {
            new clsCon();
        }

        return conn;
    }

    public static void desconectar() {

        conn = null;
    }

}
