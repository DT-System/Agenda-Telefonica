package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import connection.ConnectionDB;
import javax.swing.JOptionPane;
import model.telephone;

public class DAOtelefonos {

    public static void insertPhone(telephone tel) {

        String sql = "INSERT INTO telefonos (tel_nombre, tel_apellido, tel_telefono, tel_celular, tel_observaciones) VALUES ('" + tel.getNombre() + "', '" + tel.getApellido() + "', '" + tel.getTelefono() + "', '" + tel.getCelular() + "', '" + tel.getObservaciones() + "')";
        try {
            ConnectionDB.Conect();
            ConnectionDB.stm.execute(sql);
            ConnectionDB.Disconect();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void updatePhone(telephone tel) {

        String sql = "UPDATE telefonos SET tel_nombre='" + tel.getNombre() + "', tel_apellido='" + tel.getApellido() + "', tel_telefono='" + tel.getTelefono() + "', tel_celular='" + tel.getCelular() + "', tel_observaciones='" + tel.getObservaciones() + "' " + " WHERE tel_id='" + tel.getId() + "'";
        try {
            ConnectionDB.Conect();
            ConnectionDB.stm.executeUpdate(sql);
            ConnectionDB.Disconect();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void deletePhone(String id) {
        String sql = "DELETE FROM telefonos WHERE tel_id=" + id + "";

        try {
            ConnectionDB.Conect();
            ConnectionDB.stm.execute(sql);
            ConnectionDB.Disconect();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static List<telephone> searchByOrder(String orden) {
        String sql = "SELECT * FROM telefonos ORDER BY " + orden + "";
        telephone tel = null;
        List<telephone> lista = new ArrayList<>();
        ConnectionDB.Conect();
        ResultSet rs;
        try {
            rs = ConnectionDB.stm.executeQuery(sql);
            while (rs.next()) {
                tel = new telephone();
                tel.setId(rs.getInt("tel_id"));
                tel.setNombre(rs.getString("tel_nombre"));
                tel.setApellido(rs.getString("tel_apellido"));
                tel.setTelefono(rs.getInt("tel_telefono"));
                tel.setCelular(rs.getInt("tel_celular"));
                tel.setObservaciones(rs.getString("tel_observaciones"));

                lista.add(tel);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);
        }
        ConnectionDB.Disconect();
        return lista;
    }

    public static List<telephone> searchByName(String orden, String pista) {
        String sql = "SELECT * FROM telefonos WHERE tel_nombre ILIKE '" + pista + "%' ORDER BY " + orden + "";
        telephone tel = null;
        List<telephone> lista = new ArrayList<>();
        ConnectionDB.Conect();
        ResultSet rs;
        try {
            rs = ConnectionDB.stm.executeQuery(sql);
            while (rs.next()) {
                tel = new telephone();
                tel.setId(rs.getInt("tel_id"));
                tel.setNombre(rs.getString("tel_nombre"));
                tel.setApellido(rs.getString("tel_apellido"));
                tel.setTelefono(rs.getInt("tel_telefono"));
                tel.setCelular(rs.getInt("tel_celular"));
                tel.setObservaciones(rs.getString("tel_observaciones"));

                lista.add(tel);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);
        }
        ConnectionDB.Disconect();
        return lista;
    }

    public static telephone searchById(String id) {
        String sql = "SELECT * FROM telefonos WHERE tel_id = '" + id + "';";
        telephone tel = null;
        ConnectionDB.Conect();
        ResultSet rs;
        try {
            rs = ConnectionDB.stm.executeQuery(sql);
            while (rs.next()) {
                tel = new telephone();
                tel.setId(rs.getInt("tel_id"));
                tel.setNombre(rs.getString("tel_nombre"));
                tel.setApellido(rs.getString("tel_apellido"));
                tel.setTelefono(rs.getInt("tel_telefono"));
                tel.setCelular(rs.getInt("tel_celular"));
                tel.setObservaciones(rs.getString("tel_observaciones"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);
        }
        ConnectionDB.Disconect();
        return tel;
    }

    public static telephone searchByPhone(String phone) {
        String sql = "SELECT * FROM telefonos WHERE tel_telefono = '" + phone + "';";
        telephone tel = null;
        ConnectionDB.Conect();
        ResultSet rs;
        try {
            rs = ConnectionDB.stm.executeQuery(sql);
            while (rs.next()) {
                tel = new telephone();
                tel.setId(rs.getInt("tel_id"));
                tel.setNombre(rs.getString("tel_nombre"));
                tel.setApellido(rs.getString("tel_apellido"));
                tel.setTelefono(rs.getInt("tel_telefono"));
                tel.setCelular(rs.getInt("tel_celular"));
                tel.setObservaciones(rs.getString("tel_observaciones"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);
        }
        ConnectionDB.Disconect();
        return tel;
    }

    public static telephone searchBYMobile(String mobile) {
        String sql = "SELECT * FROM telefonos WHERE tel_celular= '" + mobile + "'";
        telephone tel = null;
        ConnectionDB.Conect();
        ResultSet rs;
        try {
            rs = ConnectionDB.stm.executeQuery(sql);
            while (rs.next()) {
                tel = new telephone();
                tel.setId(rs.getInt("tel_id"));
                tel.setNombre(rs.getString("tel_nombre"));
                tel.setApellido(rs.getString("tel_apellido"));
                tel.setTelefono(rs.getInt("tel_telefono"));
                tel.setCelular(rs.getInt("tel_celular"));
                tel.setObservaciones(rs.getString("tel_observaciones"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);
        }
        ConnectionDB.Disconect();
        return tel;
    }
}
