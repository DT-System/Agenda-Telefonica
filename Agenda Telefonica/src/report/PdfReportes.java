package report;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import connection.ConnectionDB;
import java.awt.Font;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.telephone;

/**
 * @author Delfino Tipol
 */
public class PdfReportes {

    public static telephone ReporteAgenda(String orden) {
        String sql = "SELECT * FROM telefonos ORDER BY " + orden + "";
        ResultSet rs;
        Document pdf = new Document();
        pdf.setPageSize(PageSize.LETTER);
        try {
            String ruta = System.getProperty("user.home");
            PdfWriter.getInstance(pdf, new FileOutputStream(ruta + "/Documents/Agenda Telefonica.pdf"));

            Image cabecera = Image.getInstance("src/img/Entrar.png");
            cabecera.scaleToFit(100, 100);
            cabecera.setAlignment(Chunk.ALIGN_CENTER);

            Paragraph parrafo = new Paragraph();
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo.add("FORMATO CREADO POR: DELFINO TIPOL \n\n");
            parrafo.setFont(FontFactory.getFont("Tahoma", 14, Font.BOLD, BaseColor.DARK_GRAY));
            parrafo.add("REPORTE DE AGENDA TELEFONICA \n\n");

            pdf.open();
            pdf.add(cabecera);
            pdf.add(parrafo);

            PdfPTable tabla = new PdfPTable(4);
            tabla.setWidthPercentage(100);
            tabla.setHeaderRows(1);
            float[] medida = {/*10f, */45f, 45, 25f, 25f};
            //tabla.addCell("ID");
            tabla.addCell("NOMBRE");
            tabla.addCell("APELLIDO");
            tabla.addCell("TELEFONO");
            tabla.addCell("CELULAR");
            tabla.setWidths(medida);

            try {
                ConnectionDB.Conect();
                rs = ConnectionDB.stm.executeQuery(sql);
                if (rs.next()) {
                    do {
                        //tabla.addCell(rs.getString("tel_id"));
                        tabla.addCell(rs.getString("tel_nombre"));
                        tabla.addCell(rs.getString("tel_apellido"));
                        tabla.addCell(rs.getString("tel_telefono"));
                        tabla.addCell(rs.getString("tel_celular"));
                    } while (rs.next());
                    pdf.add(tabla);

                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
            pdf.close();
            ConnectionDB.Disconect();
            JOptionPane.showMessageDialog(null, "Reporte de agenda telefonica\nfue creado Correctamente!");
        } catch (DocumentException | IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return null;
    }
}