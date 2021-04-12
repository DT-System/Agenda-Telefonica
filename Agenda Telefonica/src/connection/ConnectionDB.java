package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class ConnectionDB {

    public static Connection conexion;
    public static Statement stm;

    public static Connection Conect() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        try {
            conexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/agenda?characterEncoding=UTF8",
                    "postgres", "Admin**$$##!!");
            stm = conexion.createStatement();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return conexion;
    }

    public static Connection initDB() {
        Connection c = null;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        try {
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/", "postgres", "Admin**$$##!!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return c;
    }

    public static void Disconect() {
        try {
            conexion.close();
            stm.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}