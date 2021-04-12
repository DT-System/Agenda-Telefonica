package report;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JOptionPane;
import model.ServerDB;

/**
 *
 * @author Delfino Tipol
 */
public class CreateFile {

    public static void Create(ServerDB ser) {
        try {
            FileOutputStream con = new FileOutputStream("src/connection/ConnectionDB.java");
            FileOutputStream tab = new FileOutputStream("src/connection/InitTable.java");

            String conexion = "package connection;\n"
                    + "\n"
                    + "import java.sql.Connection;\n"
                    + "import java.sql.DriverManager;\n"
                    + "import java.sql.SQLException;\n"
                    + "import java.sql.Statement;\n"
                    + "import javax.swing.JOptionPane;\n"
                    + "\n"
                    + "public class ConnectionDB {\n"
                    + "\n"
                    + "    public static Connection conexion;\n"
                    + "    public static Statement stm;\n"
                    + "\n"
                    + "    public static Connection Conect() {\n"
                    + "        try {\n"
                    + "            Class.forName(\"org.postgresql.Driver\");\n"
                    + "        } catch (ClassNotFoundException e) {\n"
                    + "            JOptionPane.showMessageDialog(null, e.getMessage());\n"
                    + "        }\n"
                    + "\n"
                    + "        try {\n"
                    + "            conexion = DriverManager.getConnection(\"jdbc:postgresql://" + ser.getHost() + ":" + ser.getPort() + "/" + ser.getDatabase() + "?characterEncoding=UTF8\",\n"
                    + "                    \"" + ser.getUser() + "\", \"" + ser.getPassword() + "\");\n"
                    + "            stm = conexion.createStatement();\n"
                    + "        } catch (SQLException e) {\n"
                    + "            JOptionPane.showMessageDialog(null, e.getMessage());\n"
                    + "        }\n"
                    + "        return conexion;\n"
                    + "    }\n"
                    + "\n"
                    + "    public static Connection initDB() {\n"
                    + "        Connection c = null;\n"
                    + "        try {\n"
                    + "            Class.forName(\"org.postgresql.Driver\");\n"
                    + "        } catch (ClassNotFoundException e) {\n"
                    + "            JOptionPane.showMessageDialog(null, e.getMessage());\n"
                    + "        }\n"
                    + "\n"
                    + "        try {\n"
                    + "            c = DriverManager.getConnection(\"jdbc:postgresql://" + ser.getHost() + ":" + ser.getPort() + "/\", \"postgres\", \"" + ser.getPassword() + "\");\n"
                    + "        } catch (SQLException e) {\n"
                    + "            JOptionPane.showMessageDialog(null, e.getMessage());\n"
                    + "        }\n"
                    + "        return c;\n"
                    + "    }\n"
                    + "\n"
                    + "    public static void Disconect() {\n"
                    + "        try {\n"
                    + "            conexion.close();\n"
                    + "            stm.close();\n"
                    + "\n"
                    + "        } catch (SQLException e) {\n"
                    + "            JOptionPane.showMessageDialog(null, e.getMessage());\n"
                    + "        }\n"
                    + "    }\n"
                    + "}";

            String table = "package connection;\n"
                    + "\n"
                    + "import java.awt.HeadlessException;\n"
                    + "import java.sql.Connection;\n"
                    + "import java.sql.PreparedStatement;\n"
                    + "import java.sql.ResultSet;\n"
                    + "import javax.swing.JOptionPane;\n"
                    + "import java.sql.SQLException;\n"
                    + "\n"
                    + "public class InitTable {\n"
                    + "\n"
                    + "    private Connection conexion;\n"
                    + "    private ResultSet resultset;\n"
                    + "    private PreparedStatement pstm;\n"
                    + "    private String sql;\n"
                    + "\n"
                    + "    public void createDB() {\n"
                    + "        this.conexion = ConnectionDB.initDB();\n"
                    + "        try {\n"
                    + "            sql = \"DROP DATABASE IF EXISTS " + ser.getDatabase() + ";\";\n"
                    + "            pstm = null;\n"
                    + "            pstm = conexion.prepareStatement(sql);\n"
                    + "            pstm.executeUpdate();\n"
                    + "\n"
                    + "            sql = \"CREATE DATABASE " + ser.getDatabase() + " OWNER postgres;\";\n"
                    + "\n"
                    + "            pstm = null;\n"
                    + "            pstm = conexion.prepareStatement(sql);\n"
                    + "            pstm.executeUpdate();\n"
                    + "            JOptionPane.showMessageDialog(null, \"¡Base de datos fue creada correctamente! \\nPresione Aceptar para continuar.\");\n"
                    + "\n"
                    + "        } catch (HeadlessException | SQLException e) {\n"
                    + "            JOptionPane.showMessageDialog(null, \"Error: \" + e.getMessage());\n"
                    + "        }\n"
                    + "        Disconect();\n"
                    + "    }\n"
                    + "\n"
                    + "    public void createTable() {\n"
                    + "        this.conexion = ConnectionDB.Conect();\n"
                    + "        sql = \"DROP TABLE IF EXISTS telefonos;\";\n"
                    + "        pstm = null;\n"
                    + "        try {\n"
                    + "            pstm = conexion.prepareStatement(sql);\n"
                    + "            pstm.execute();\n"
                    + "        } catch (SQLException e) {\n"
                    + "            JOptionPane.showMessageDialog(null, \"Error: \" + e.getMessage());\n"
                    + "        }\n"
                    + "        try {\n"
                    + "            sql = \"CREATE TABLE telefonos(tel_id serial NOT NULL,\"\n"
                    + "                    + \"tel_nombre character varying(30),\"\n"
                    + "                    + \"tel_apellido character varying(30),\"\n"
                    + "                    + \"tel_telefono integer,\" + \"tel_celular integer,\"\n"
                    + "                    + \"tel_observaciones character varying(75),\"\n"
                    + "                    + \"CONSTRAINT PKeytelefonos PRIMARY KEY (tel_id))WITH (OIDS=FALSE);\"\n"
                    + "                    + \"ALTER TABLE telefonos ADD CONSTRAINT tel_unico UNIQUE(tel_telefono);\"\n"
                    + "                    + \"ALTER TABLE telefonos ADD CONSTRAINT cel_unico UNIQUE(tel_celular);\"\n"
                    + "                    + \"ALTER TABLE telefonos OWNER TO postgres;\";\n"
                    + "            pstm = null;\n"
                    + "            pstm = conexion.prepareStatement(sql);\n"
                    + "            pstm.executeUpdate();\n"
                    + "\n"
                    + "            JOptionPane.showMessageDialog(null, \"¡La tabla fue creada correctamente!\");\n"
                    + "        } catch (HeadlessException | SQLException e) {\n"
                    + "            JOptionPane.showMessageDialog(null, \"Error :\" + e.getMessage());\n"
                    + "        }\n"
                    + "        Disconect();\n"
                    + "    }\n"
                    + "\n"
                    + "    public void Disconect() {\n"
                    + "        try {\n"
                    + "            if (resultset != null) {\n"
                    + "                resultset.close();\n"
                    + "            }\n"
                    + "            if (pstm != null) {\n"
                    + "                pstm.close();\n"
                    + "            }\n"
                    + "            if (conexion != null) {\n"
                    + "                conexion.close();\n"
                    + "            }\n"
                    + "        } catch (SQLException excepcion) {\n"
                    + "            JOptionPane.showMessageDialog(null, \"Exception: \" + excepcion.getMessage());\n"
                    + "        }\n"
                    + "    }\n"
                    + "}";

            byte connection[] = conexion.getBytes();
            byte tables[] = table.getBytes();

            con.write(connection);
            tab.write(tables);

            JOptionPane.showMessageDialog(null, "La configuracion del ficheo de conexion se guardo correctamente");
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
}
