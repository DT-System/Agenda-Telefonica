package connection;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.sql.SQLException;

public class InitTable {

    private Connection conexion;
    private ResultSet resultset;
    private PreparedStatement pstm;
    private String sql;

    public void createDB() {
        this.conexion = ConnectionDB.initDB();
        try {
            sql = "DROP DATABASE IF EXISTS agenda;";
            pstm = null;
            pstm = conexion.prepareStatement(sql);
            pstm.executeUpdate();

            sql = "CREATE DATABASE agenda OWNER postgres;";

            pstm = null;
            pstm = conexion.prepareStatement(sql);
            pstm.executeUpdate();
            JOptionPane.showMessageDialog(null, "¡Base de datos fue creada correctamente! \nPresione Aceptar para continuar.");

        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        Disconect();
    }

    public void createTable() {
        this.conexion = ConnectionDB.Conect();
        sql = "DROP TABLE IF EXISTS telefonos;";
        pstm = null;
        try {
            pstm = conexion.prepareStatement(sql);
            pstm.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        try {
            sql = "CREATE TABLE telefonos(tel_id serial NOT NULL,"
                    + "tel_nombre character varying(30),"
                    + "tel_apellido character varying(30),"
                    + "tel_telefono integer," + "tel_celular integer,"
                    + "tel_observaciones character varying(75),"
                    + "CONSTRAINT PKeytelefonos PRIMARY KEY (tel_id))WITH (OIDS=FALSE);"
                    + "ALTER TABLE telefonos ADD CONSTRAINT tel_unico UNIQUE(tel_telefono);"
                    + "ALTER TABLE telefonos ADD CONSTRAINT cel_unico UNIQUE(tel_celular);"
                    + "ALTER TABLE telefonos OWNER TO postgres;";
            pstm = null;
            pstm = conexion.prepareStatement(sql);
            pstm.executeUpdate();

            JOptionPane.showMessageDialog(null, "¡La tabla fue creada correctamente!");
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error :" + e.getMessage());
        }
        Disconect();
    }

    public void Disconect() {
        try {
            if (resultset != null) {
                resultset.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        } catch (SQLException excepcion) {
            JOptionPane.showMessageDialog(null, "Exception: " + excepcion.getMessage());
        }
    }
}