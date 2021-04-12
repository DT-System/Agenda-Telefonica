package forms;

import com.mxrck.autocompleter.TextAutoCompleter;
import dao.DAOtelefonos;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import model.telephone;
import report.PdfReportes;

/**
 *
 * @author Delfino Tipol
 */
@SuppressWarnings("serial")
public class AgendaTelefonica extends javax.swing.JFrame {

    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("img/agenda.png"));
        return retValue;
    }

    private boolean tip0busq = false;
    private final TextAutoCompleter autocompletar;
    public static List<telephone> listphone;
    public static DefaultTableModel modelTable = new DefaultTableModel(null, new String[]{"ID", "NOMBRE", "APELLIDO", "TELEFONO", "CELULAR", "OBSERVACIONES"}) {
        boolean[] columnEditables = new boolean[]{false, false, false, false, false, false};

        @Override
        public boolean isCellEditable(int row, int column) {
            return columnEditables[column];
        }
    };

    public AgendaTelefonica() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent arg0) {
                Exit();
            }
        });
        initComponents();
        autocompletar = new TextAutoCompleter(tfBuscar);
        setLocationRelativeTo(null);
        tabletelefonos.setModel(modelTable);
        ConsultAll();
        DefSizaColumn();
    }

    private void DefSizaColumn() {
        tabletelefonos.getColumnModel().getColumn(0).setPreferredWidth(15);
        tabletelefonos.getColumnModel().getColumn(0).setResizable(false);
        tabletelefonos.getColumnModel().getColumn(1).setPreferredWidth(150);
        tabletelefonos.getColumnModel().getColumn(1).setResizable(false);
        tabletelefonos.getColumnModel().getColumn(2).setPreferredWidth(150);
        tabletelefonos.getColumnModel().getColumn(2).setResizable(false);
        tabletelefonos.getColumnModel().getColumn(3).setPreferredWidth(50);
        tabletelefonos.getColumnModel().getColumn(3).setResizable(false);
        tabletelefonos.getColumnModel().getColumn(4).setPreferredWidth(50);
        tabletelefonos.getColumnModel().getColumn(4).setResizable(false);
        tabletelefonos.getColumnModel().getColumn(5).setPreferredWidth(250);
        tabletelefonos.getColumnModel().getColumn(5).setResizable(false);
    }

    private void New() {
        tfBuscarTelefono.setText("");
        tfBuscarTelefono.setEnabled(false);
        tfBuscarTelefono.setBackground(Color.red);
        tfBuscarCelular.setText("");
        tfBuscarCelular.setEnabled(false);
        tfBuscarCelular.setBackground(Color.red);
        tfId.setText("*Listo*");
        tfId.setEnabled(false);
        tfNombre.requestFocus();
        btnNuevo.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnModificar.setEnabled(false);
        btnCancelar.setEnabled(true);
        btnGuardar.setEnabled(true);
        tabletelefonos.setEnabled(false);
        tfNombre.setText("");
        tfNombre.setEnabled(true);
        tfApellido.setText("");
        tfApellido.setEnabled(true);
        tfTelefono.setText("");
        tfTelefono.setEnabled(true);
        tfCelular.setText("");
        tfCelular.setEnabled(true);
        tfObservaciones.setText("");
        tfObservaciones.setEnabled(true);
    }

    private void Cancel() {
        tfBuscarTelefono.setText("");
        tfBuscarTelefono.setEnabled(true);
        tfBuscarTelefono.setBackground(Color.white);
        tfBuscarCelular.setText("");
        tfBuscarCelular.setEnabled(true);
        tfBuscarCelular.setBackground(Color.white);
        tfId.setText("");
        tfId.setEnabled(true);
        tfNombre.setText("");
        tfNombre.setEnabled(false);
        tfApellido.setText("");
        tfApellido.setEnabled(false);
        tfTelefono.setText("");
        tfTelefono.setEnabled(false);
        tfCelular.setText("");
        tfCelular.setEnabled(false);
        tfObservaciones.setText("");
        tfObservaciones.setEnabled(false);
        tfId.requestFocus();
        chkActive.setSelected(false);
        btnReporte.setEnabled(false);
        btnNuevo.setEnabled(true);
        btnEliminar.setEnabled(false);
        btnModificar.setEnabled(false);
        btnCancelar.setEnabled(false);
        btnGuardar.setText("Guardar");
        btnGuardar.setEnabled(false);
        tabletelefonos.setEnabled(true);
    }

    private void Save() {
        telephone telefonos = new telephone();
        //telefonos.setId(Integer.parseInt(tfId.getText()));
        telefonos.setNombre(tfNombre.getText().toUpperCase());
        telefonos.setApellido(tfApellido.getText().toUpperCase());
        telefonos.setTelefono(Integer.parseInt(tfTelefono.getText()));
        telefonos.setCelular(Integer.parseInt(tfCelular.getText()));
        telefonos.setObservaciones(tfObservaciones.getText().toUpperCase());

        DAOtelefonos.insertPhone(telefonos);
        JOptionPane.showMessageDialog(null, "¡Se han guardado los datos con éxito!");
        ConsultAll();
    }

    private void Update() {
        telephone telefonos = new telephone();
        telefonos.setId(Integer.parseInt(tfId.getText()));
        telefonos.setNombre(tfNombre.getText().toUpperCase());
        telefonos.setApellido(tfApellido.getText().toUpperCase());
        telefonos.setTelefono(Integer.parseInt(tfTelefono.getText()));
        telefonos.setCelular(Integer.parseInt(tfCelular.getText()));
        telefonos.setObservaciones(tfObservaciones.getText().toUpperCase());

        DAOtelefonos.updatePhone(telefonos);
        JOptionPane.showMessageDialog(null, "¡Se han guardado los cambios con éxito!");
        ConsultAll();
    }

    private void Delete() {
        int seleccion = tabletelefonos.getSelectedRow();
        try {
            String id, nombre;
            id = tabletelefonos.getValueAt(seleccion, 0).toString();
            nombre = tabletelefonos.getValueAt(seleccion, 1).toString();
            int yes = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea eliminar a: " + nombre + " del registro de telefonos?", "Aviso", JOptionPane.YES_NO_OPTION);
            if (yes == 0) {
                DAOtelefonos.deletePhone(id);
                ConsultAll();
            }
        } catch (HeadlessException e) {
            e.getMessage();
        }
        ConsultAll();
    }

    private void Upload() {
        if (tabletelefonos.isEnabled()) {

            int fila = tabletelefonos.getSelectedRow();
            tfId.setText(String.valueOf(tabletelefonos.getValueAt(fila, 0)));
            tfNombre.setText(String.valueOf(tabletelefonos.getValueAt(fila, 1)));
            tfApellido.setText(String.valueOf(tabletelefonos.getValueAt(fila, 2)));
            tfTelefono.setText(String.valueOf(tabletelefonos.getValueAt(fila, 3)));
            tfCelular.setText(String.valueOf(tabletelefonos.getValueAt(fila, 4)));
            tfObservaciones.setText(String.valueOf(tabletelefonos.getValueAt(fila, 5)));
            btnEliminar.setEnabled(true);
            btnModificar.setEnabled(true);
            btnNuevo.setEnabled(false);
            btnCancelar.setEnabled(true);
            btnGuardar.setEnabled(false);
        }
    }

    private void EnabledForUpdate() {
        tfId.setEnabled(false);
        tfNombre.setEnabled(true);
        tfNombre.requestFocus();
        tfNombre.selectAll();
        btnNuevo.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnModificar.setEnabled(false);
        btnCancelar.setEnabled(true);
        btnGuardar.setText("Guardar ");
        btnGuardar.setEnabled(true);
        tabletelefonos.setEnabled(false);
        tfApellido.setEnabled(true);
        tfTelefono.setEnabled(true);
        tfCelular.setEnabled(true);
        tfObservaciones.setEnabled(true);
    }

    public void ConsultAll() {
        listphone = new ArrayList<>();
        listphone = DAOtelefonos.searchByOrder("tel_id");
        UpdateTable();
        AutoCompletarBusqtelefonos();
        Cancel();
    }

    public void ConsultByName() {
        listphone = new ArrayList<>();
        listphone = DAOtelefonos.searchByName("tel_nombre", tfBuscar.getText());
        UpdateTable();
    }

    private static void UpdateTable() {
        while (modelTable.getRowCount() > 0) {
            modelTable.removeRow(0);
        }
        String[] campos = {null, null, null, null, null};
        for (int i = 0; i < listphone.size(); i++) {
            modelTable.addRow(campos);
            telephone telefonos = listphone.get(i);

            modelTable.setValueAt(telefonos.getId(), i, 0);
            modelTable.setValueAt(telefonos.getNombre(), i, 1);
            modelTable.setValueAt(telefonos.getApellido(), i, 2);
            modelTable.setValueAt(telefonos.getTelefono(), i, 3);
            modelTable.setValueAt(telefonos.getCelular(), i, 4);
            modelTable.setValueAt(telefonos.getObservaciones(), i, 5);
        }
    }

    private boolean Validation() {
        if (tfId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo Id no puede quedar vacío", "Aviso", JOptionPane.WARNING_MESSAGE);
            tfId.requestFocus();
            return false;
        }
        if (tfNombre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo Nombre no puede quedar vacío", "Aviso", JOptionPane.WARNING_MESSAGE);
            tfNombre.requestFocus();
            return false;
        }
        if (tfApellido.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo Apellido no puede quedar vacío", "Aviso", JOptionPane.WARNING_MESSAGE);
            tfApellido.requestFocus();
            return false;
        }
        if (tfTelefono.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo Numero no puede quedar vacío", "Aviso", JOptionPane.WARNING_MESSAGE);
            tfTelefono.requestFocus();
            return false;
        }
        if (tfCelular.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo Otro numero no puede quedar vacío", "Aviso", JOptionPane.WARNING_MESSAGE);
            tfCelular.requestFocus();
            return false;
        }
        if (tfObservaciones.getText().isEmpty()) {
            if (JOptionPane.showConfirmDialog(null, "¿Esta seguro de dejar vacio el campo de observaciones?", "Confirmacion", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.YES_NO_OPTION) {
                return true;
            } else {
                tfObservaciones.requestFocus();
                return false;
            }
        }
        return true;
    }

    public void Search() {
        if (tip0busq == false) {
            ConsultByName();
        }
    }

    public void AutoCompletarBusqtelefonos() {
        telephone telefonos = null;
        for (int i = 0; i < listphone.size(); i++) {
            telefonos = listphone.get(i);
            autocompletar.addItem(telefonos.getNombre());
        }
    }

    private void SearchByPhone() {
        telephone bus = DAOtelefonos.searchByPhone(tfBuscarTelefono.getText());
        if (bus == null) {
            JOptionPane.showMessageDialog(null, "No se encontró ningun registro con el numero: " + tfBuscarTelefono.getText(), "Alerta!", JOptionPane.WARNING_MESSAGE);
            ConsultAll();
        } else {
            tfId.setText(String.valueOf(bus.getId()));
            tfNombre.setText(bus.getNombre());
            tfApellido.setText(bus.getApellido());
            tfTelefono.setText(String.valueOf(bus.getTelefono()));
            tfCelular.setText(String.valueOf(bus.getCelular()));
            tfObservaciones.setText(bus.getObservaciones());
            tfBuscarTelefono.setText("");
            tfBuscarCelular.setText("");
            btnEliminar.setEnabled(true);
            btnModificar.setEnabled(true);
            btnNuevo.setEnabled(false);
            btnCancelar.setEnabled(true);
            btnGuardar.setEnabled(false);
            tfBuscarCelular.requestFocus();
        }
    }

    private void SearchByMobile() {
        telephone bus = DAOtelefonos.searchBYMobile(tfBuscarCelular.getText());
        if (bus == null) {
            JOptionPane.showMessageDialog(null, "No se encontró ningun registro con el numero: " + tfBuscarCelular.getText(), "Alerta!", JOptionPane.WARNING_MESSAGE);
            ConsultAll();
        } else {
            tfId.setText(String.valueOf(bus.getId()));
            tfNombre.setText(bus.getNombre());
            tfApellido.setText(bus.getApellido());
            tfTelefono.setText(String.valueOf(bus.getTelefono()));
            tfCelular.setText(String.valueOf(bus.getCelular()));
            tfObservaciones.setText(bus.getObservaciones());
            tfBuscarTelefono.setText("");
            tfBuscarCelular.setText("");
            btnEliminar.setEnabled(true);
            btnModificar.setEnabled(true);
            btnNuevo.setEnabled(false);
            btnCancelar.setEnabled(true);
            btnGuardar.setEnabled(false);
            tfBuscarTelefono.requestFocus();
        }
    }

    private void SearchById() {
        telephone bus = DAOtelefonos.searchById(tfId.getText());
        if (bus == null) {
            JOptionPane.showMessageDialog(null, "No se encontró ningun registro con el Id: " + tfId.getText(), "Alerta!", JOptionPane.WARNING_MESSAGE);
            ConsultAll();
        } else {
            tfId.setText(String.valueOf(bus.getId()));
            tfNombre.setText(bus.getNombre());
            tfApellido.setText(bus.getApellido());
            tfTelefono.setText(String.valueOf(bus.getTelefono()));
            tfCelular.setText(String.valueOf(bus.getCelular()));
            tfObservaciones.setText(bus.getObservaciones());
            tfBuscarTelefono.setText("");
            tfBuscarCelular.setText("");
            btnEliminar.setEnabled(true);
            btnModificar.setEnabled(true);
            btnNuevo.setEnabled(false);
            btnCancelar.setEnabled(true);
            btnGuardar.setEnabled(false);
            tfId.requestFocus();
        }
    }

    private void Exit() {
        if (btnGuardar.isEnabled()) {
            int rs = JOptionPane.showConfirmDialog(null, "¿Desea guardar los cambios efectuados antes de salir?",
                    "Pregunta", JOptionPane.YES_NO_CANCEL_OPTION);
            switch (rs) {
                case 0:
                    if (btnGuardar.getText().equals("Guardar")) {
                        Save();
                    } else {
                        Update();
                    }
                    dispose();
                case 1:
                    dispose();
                default:
            }
        } else {
            dispose();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlFondo = new Fondo();
        sCrll = new javax.swing.JScrollPane();
        tabletelefonos = new javax.swing.JTable();
        lblBuscar = new javax.swing.JLabel();
        tfBuscar = new javax.swing.JTextField();
        pnlBuscar = new javax.swing.JPanel();
        lblBuscarNumero = new javax.swing.JLabel();
        tfBuscarTelefono = new javax.swing.JTextField();
        tfBuscarCelular = new javax.swing.JTextField();
        lblOtro = new javax.swing.JLabel();
        pnlData = new javax.swing.JPanel();
        lblId = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        tfNombre = new javax.swing.JTextField();
        lblApellido = new javax.swing.JLabel();
        lblTelefon = new javax.swing.JLabel();
        lblCelular = new javax.swing.JLabel();
        tfCelular = new javax.swing.JTextField();
        tfTelefono = new javax.swing.JTextField();
        tfApellido = new javax.swing.JTextField();
        tfId = new javax.swing.JTextField();
        lblObservaciones = new javax.swing.JLabel();
        tfObservaciones = new javax.swing.JTextField();
        pnlButton = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        pnlTitle = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        pnlBtn = new javax.swing.JPanel();
        pnlBtn1 = new javax.swing.JPanel();
        chkActive = new javax.swing.JCheckBox();
        btnReporte = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("*****AGENDA TELEFONICA");
        setIconImage(getIconImage());
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlFondo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabletelefonos.setBackground(new java.awt.Color(255, 255, 255));
        tabletelefonos.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        tabletelefonos.setForeground(new java.awt.Color(0, 0, 0));
        tabletelefonos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabletelefonos.setSelectionBackground(new java.awt.Color(0, 0, 153));
        tabletelefonos.setSelectionForeground(new java.awt.Color(255, 255, 0));
        tabletelefonos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabletelefonosMouseClicked(evt);
            }
        });
        sCrll.setViewportView(tabletelefonos);

        pnlFondo.add(sCrll, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 181, 1000, 490));

        lblBuscar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblBuscar.setForeground(new java.awt.Color(255, 255, 255));
        lblBuscar.setText("BUSCAR:");
        pnlFondo.add(lblBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 140, -1, 30));

        tfBuscar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        tfBuscar.setForeground(new java.awt.Color(0, 0, 0));
        tfBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfBuscarKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfBuscarKeyTyped(evt);
            }
        });
        pnlFondo.add(tfBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 140, 290, 30));

        pnlBuscar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlBuscar.setOpaque(false);
        pnlBuscar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblBuscarNumero.setForeground(new java.awt.Color(255, 255, 255));
        lblBuscarNumero.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBuscarNumero.setText("BUSCAR POR TELEFONO");
        pnlBuscar.add(lblBuscarNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 150, 30));

        tfBuscarTelefono.setBackground(new java.awt.Color(255, 255, 255));
        tfBuscarTelefono.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        tfBuscarTelefono.setForeground(new java.awt.Color(0, 0, 0));
        tfBuscarTelefono.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfBuscarTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfBuscarTelefonoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfBuscarTelefonoKeyTyped(evt);
            }
        });
        pnlBuscar.add(tfBuscarTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 150, 30));

        tfBuscarCelular.setBackground(new java.awt.Color(255, 255, 255));
        tfBuscarCelular.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        tfBuscarCelular.setForeground(new java.awt.Color(0, 0, 0));
        tfBuscarCelular.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfBuscarCelular.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfBuscarCelularKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfBuscarCelularKeyTyped(evt);
            }
        });
        pnlBuscar.add(tfBuscarCelular, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 120, 150, 30));

        lblOtro.setForeground(new java.awt.Color(255, 255, 255));
        lblOtro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblOtro.setText("BUSCAR POR CELULAR");
        pnlBuscar.add(lblOtro, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 90, 150, 30));

        pnlFondo.add(pnlBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 330, 160));

        pnlData.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlData.setOpaque(false);
        pnlData.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblId.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblId.setForeground(new java.awt.Color(255, 255, 255));
        lblId.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblId.setText("ID:");
        pnlData.add(lblId, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, -1, 30));

        lblNombre.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblNombre.setForeground(new java.awt.Color(255, 255, 255));
        lblNombre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombre.setText("NOMBRE:");
        pnlData.add(lblNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 70, -1, 30));

        tfNombre.setBackground(new java.awt.Color(255, 255, 255));
        tfNombre.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        tfNombre.setForeground(new java.awt.Color(0, 0, 0));
        tfNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfNombreKeyTyped(evt);
            }
        });
        pnlData.add(tfNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 210, 30));

        lblApellido.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblApellido.setForeground(new java.awt.Color(255, 255, 255));
        lblApellido.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblApellido.setText("APELLIDO:");
        pnlData.add(lblApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 120, -1, 30));

        lblTelefon.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblTelefon.setForeground(new java.awt.Color(255, 255, 255));
        lblTelefon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTelefon.setText("TELEFONO:");
        pnlData.add(lblTelefon, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 170, -1, 30));

        lblCelular.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblCelular.setForeground(new java.awt.Color(255, 255, 255));
        lblCelular.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCelular.setText("CELULAR:");
        pnlData.add(lblCelular, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 220, -1, 30));

        tfCelular.setBackground(new java.awt.Color(255, 255, 255));
        tfCelular.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        tfCelular.setForeground(new java.awt.Color(0, 0, 0));
        tfCelular.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfCelularKeyTyped(evt);
            }
        });
        pnlData.add(tfCelular, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 220, 210, 30));

        tfTelefono.setBackground(new java.awt.Color(255, 255, 255));
        tfTelefono.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        tfTelefono.setForeground(new java.awt.Color(0, 0, 0));
        tfTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfTelefonoKeyTyped(evt);
            }
        });
        pnlData.add(tfTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 170, 210, 30));

        tfApellido.setBackground(new java.awt.Color(255, 255, 255));
        tfApellido.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        tfApellido.setForeground(new java.awt.Color(0, 0, 0));
        tfApellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfApellidoKeyTyped(evt);
            }
        });
        pnlData.add(tfApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, 210, 30));

        tfId.setBackground(new java.awt.Color(255, 255, 255));
        tfId.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        tfId.setForeground(new java.awt.Color(0, 0, 0));
        tfId.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfIdKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfIdKeyTyped(evt);
            }
        });
        pnlData.add(tfId, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 20, 90, 30));

        lblObservaciones.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblObservaciones.setForeground(new java.awt.Color(255, 255, 255));
        lblObservaciones.setText("OBS.");
        pnlData.add(lblObservaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 270, -1, 30));

        tfObservaciones.setBackground(new java.awt.Color(255, 255, 255));
        tfObservaciones.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        tfObservaciones.setForeground(new java.awt.Color(0, 0, 0));
        tfObservaciones.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfObservacionesKeyTyped(evt);
            }
        });
        pnlData.add(tfObservaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, 270, 30));

        pnlFondo.add(pnlData, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 330, 310));

        pnlButton.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlButton.setOpaque(false);
        pnlButton.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnNuevo.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        btnNuevo.setForeground(new java.awt.Color(0, 0, 0));
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/NuevoReg.gif"))); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        pnlButton.add(btnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 150, 45));

        btnGuardar.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(0, 0, 0));
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/guardar.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        btnGuardar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnGuardarKeyPressed(evt);
            }
        });
        pnlButton.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, 150, 45));

        btnModificar.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        btnModificar.setForeground(new java.awt.Color(0, 0, 0));
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/modificar.png"))); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        pnlButton.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 60, 150, 45));

        btnCancelar.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(0, 0, 0));
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cancelar.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        pnlButton.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 150, 45));

        btnSalir.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        btnSalir.setForeground(new java.awt.Color(0, 0, 0));
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/del.png"))); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        pnlButton.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 110, 150, 45));

        btnEliminar.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(0, 0, 0));
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/eliminar.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        pnlButton.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 150, 45));

        pnlFondo.add(pnlButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 500, 330, 170));

        pnlTitle.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlTitle.setOpaque(false);
        pnlTitle.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitle.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(255, 255, 0));
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("<html><center>REGISTRO & CONTROL DE <p>AGENDA TELEFONICA");
        pnlTitle.add(lblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 820, 100));

        pnlFondo.add(pnlTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, 840, 120));

        pnlBtn.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlBtn.setOpaque(false);
        pnlBtn.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlBtn1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlBtn1.setOpaque(false);
        pnlBtn1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        chkActive.setForeground(new java.awt.Color(0, 0, 0));
        chkActive.setText("Activar");
        chkActive.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkActiveStateChanged(evt);
            }
        });
        pnlBtn1.add(chkActive, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 110, 30));

        btnReporte.setForeground(new java.awt.Color(0, 0, 0));
        btnReporte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/agenda.png"))); // NOI18N
        btnReporte.setText("Reporte");
        btnReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteActionPerformed(evt);
            }
        });
        pnlBtn1.add(btnReporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 110, 40));

        pnlBtn.add(pnlBtn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 130, 100));

        pnlFondo.add(pnlBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 10, 150, 120));

        getContentPane().add(pnlFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1360, 680));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        if (evt.getSource() == btnSalir) {
            if (JOptionPane.showConfirmDialog(null, "Esta seguro de salir?", "Confirmacion", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
                System.exit(0);
            }
        }
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnGuardarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnGuardarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (Validation()) {
                if (btnGuardar.getText().equals("Guardar")) {
                    Save();
                } else {
                    Update();
                }
            }
        }
    }//GEN-LAST:event_btnGuardarKeyPressed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (evt.getSource() == btnGuardar) {
            if (Validation()) {
                if (btnGuardar.getText().equals("Guardar")) {
                    Save();
                } else {
                    Update();
                }
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        if (evt.getSource() == btnModificar) {
            EnabledForUpdate();
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        if (evt.getSource() == btnCancelar) {
            ConsultAll();
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        if (evt.getSource() == btnEliminar) {
            Delete();
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        if (evt.getSource() == btnNuevo) {
            New();
        }
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void tabletelefonosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabletelefonosMouseClicked
        if (evt.getSource() == tabletelefonos) {
            Upload();
        }
    }//GEN-LAST:event_tabletelefonosMouseClicked

    private void tfBuscarTelefonoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfBuscarTelefonoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (tfBuscarTelefono.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Ingrese el numero a buscar por favor!", "Alerta!", JOptionPane.WARNING_MESSAGE);
            } else {
                SearchByPhone();
            }
        }
    }//GEN-LAST:event_tfBuscarTelefonoKeyReleased

    private void tfBuscarCelularKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfBuscarCelularKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (tfBuscarCelular.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Ingrese el numero a buscar por favor!", "Alerta!", JOptionPane.WARNING_MESSAGE);
            } else {
                SearchByMobile();
            }
        }
    }//GEN-LAST:event_tfBuscarCelularKeyReleased

    private void tfBuscarTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfBuscarTelefonoKeyTyped
        char num = evt.getKeyChar();
        if (!Character.isDigit(num)) {
            evt.consume();
        }
    }//GEN-LAST:event_tfBuscarTelefonoKeyTyped

    private void tfBuscarCelularKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfBuscarCelularKeyTyped
        char num = evt.getKeyChar();
        if (!Character.isDigit(num)) {
            evt.consume();
        }
    }//GEN-LAST:event_tfBuscarCelularKeyTyped

    private void tfBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfBuscarKeyTyped
        char valor = evt.getKeyChar();
        tip0busq = Character.isDigit(valor);
    }//GEN-LAST:event_tfBuscarKeyTyped

    private void tfBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfBuscarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Search();
        }
    }//GEN-LAST:event_tfBuscarKeyPressed

    private void tfTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfTelefonoKeyTyped
        char valor = evt.getKeyChar();
        if (!Character.isDigit(valor)) {
            evt.consume();
        }
    }//GEN-LAST:event_tfTelefonoKeyTyped

    private void tfCelularKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfCelularKeyTyped
        char valor = evt.getKeyChar();
        if (!Character.isDigit(valor)) {
            evt.consume();
        }
    }//GEN-LAST:event_tfCelularKeyTyped

    private void chkActiveStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkActiveStateChanged
        if (evt.getSource() == chkActive) {
            if (chkActive.isSelected() == true) {
                chkActive.setText("Desactivar");
                btnReporte.setEnabled(true);
            } else {
                chkActive.setText("Activar");
                btnReporte.setEnabled(false);
            }
        }
    }//GEN-LAST:event_chkActiveStateChanged

    private void btnReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteActionPerformed
        if (evt.getSource() == btnReporte) {
            if (JOptionPane.showConfirmDialog(null, "Esta seguro de generar este reporte?", "Confirmacion", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
                PdfReportes.ReporteAgenda("tel_nombre");
                Cancel();
            }
        }
    }//GEN-LAST:event_btnReporteActionPerformed

    private void tfIdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfIdKeyTyped
        char numeros = evt.getKeyChar();
        if (!Character.isDigit(numeros)) {
            evt.consume();
        }
    }//GEN-LAST:event_tfIdKeyTyped

    private void tfNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfNombreKeyTyped
        char caracteres = evt.getKeyChar();
        if (!Character.isLetter(caracteres) && !Character.isSpace(caracteres)) {
            evt.consume();
        }
    }//GEN-LAST:event_tfNombreKeyTyped

    private void tfApellidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfApellidoKeyTyped
        char caracteres = evt.getKeyChar();
        if (!Character.isLetter(caracteres) && !Character.isSpace(caracteres)) {
            evt.consume();
        }
    }//GEN-LAST:event_tfApellidoKeyTyped

    private void tfObservacionesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfObservacionesKeyTyped
        char caracteres = evt.getKeyChar();
        if (!Character.isLetter(caracteres) && !Character.isDigit(caracteres) && !Character.isSpace(caracteres)) {
            evt.consume();
        }
    }//GEN-LAST:event_tfObservacionesKeyTyped

    private void tfIdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfIdKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (tfId.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Ingrese el id para buscar por favor!", "Alerta!", JOptionPane.ERROR_MESSAGE);
            } else {
                SearchById();
            }
        }
    }//GEN-LAST:event_tfIdKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Dialog".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AgendaTelefonica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new AgendaTelefonica().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnReporte;
    private javax.swing.JButton btnSalir;
    private javax.swing.JCheckBox chkActive;
    private javax.swing.JLabel lblApellido;
    private javax.swing.JLabel lblBuscar;
    private javax.swing.JLabel lblBuscarNumero;
    private javax.swing.JLabel lblCelular;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblObservaciones;
    private javax.swing.JLabel lblOtro;
    private javax.swing.JLabel lblTelefon;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnlBtn;
    private javax.swing.JPanel pnlBtn1;
    private javax.swing.JPanel pnlBuscar;
    private javax.swing.JPanel pnlButton;
    private javax.swing.JPanel pnlData;
    private javax.swing.JPanel pnlFondo;
    private javax.swing.JPanel pnlTitle;
    private javax.swing.JScrollPane sCrll;
    private javax.swing.JTable tabletelefonos;
    private javax.swing.JTextField tfApellido;
    private javax.swing.JTextField tfBuscar;
    private javax.swing.JTextField tfBuscarCelular;
    private javax.swing.JTextField tfBuscarTelefono;
    private javax.swing.JTextField tfCelular;
    private javax.swing.JTextField tfId;
    private javax.swing.JTextField tfNombre;
    private javax.swing.JTextField tfObservaciones;
    private javax.swing.JTextField tfTelefono;
    // End of variables declaration//GEN-END:variables
    class Fondo extends JPanel {

        private Image img;

        @Override
        public void paint(Graphics g) {
            img = new ImageIcon(getClass().getResource("/img/f814.jpg")).getImage();
            g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            setOpaque(false);
            super.paint(g);
        }
    }
}
