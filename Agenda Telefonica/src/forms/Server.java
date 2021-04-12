package forms;

import connection.InitTable;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import model.ServerDB;
import report.CreateFile;

@SuppressWarnings("serial")
public class Server extends javax.swing.JFrame {

    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("img/Entrar.png"));
        return retValue;
    }

    public Server() {
        initComponents();
        setLocationRelativeTo(null);
        Disabled();
    }

    private void Disabled() {
        tfHost.setText("");
        tfPort.setText("");
        tfDatabase.setText("");
        tfUser.setText("");
        tfPassword.setText("");
        btnSave.setEnabled(true);
        btnCreateDB.setEnabled(false);
        tfHost.requestFocus();
    }

    private boolean Validacion() {
        if (tfHost.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el Host o el IP de la maquina donde quiere conectarse\n "
                    + "y crear la base de datos con las tablas necesarias", "Advertencia", JOptionPane.WARNING_MESSAGE);
            tfHost.requestFocus();
            return false;
        }
        if (tfPort.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el puerto de la maquina donde quiere conectarse", "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            tfPort.requestFocus();
            return false;
        }
        if (tfDatabase.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el nombre de la Base de Datos que desea crear", "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            tfDatabase.requestFocus();
            return false;
        }
        if (tfUser.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el Usuario", "Advertencia", JOptionPane.WARNING_MESSAGE);
            tfUser.requestFocus();
            return false;
        }
        if (tfPassword.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese la contraseña", "Advertencia", JOptionPane.WARNING_MESSAGE);
            tfPassword.requestFocus();
            return false;
        }
        return true;
    }

    private void configServer() throws SQLException {
        ServerDB conf = new ServerDB();
        conf.setHost(tfHost.getText());
        conf.setPort(Integer.parseInt(tfPort.getText()));
        conf.setDatabase(tfDatabase.getText().toLowerCase());
        conf.setUser(tfUser.getText());
        conf.setPassword(tfPassword.getText());

        CreateFile.Create(conf);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblFondo = new javax.swing.JLabel();
        pnl1 = new Fondo();
        pnl2 = new javax.swing.JPanel();
        pnl3 = new javax.swing.JPanel();
        lblTitle1 = new javax.swing.JLabel();
        lblTitle3 = new javax.swing.JLabel();
        pnl4 = new javax.swing.JPanel();
        pnl5 = new javax.swing.JPanel();
        lblLocalhost = new javax.swing.JLabel();
        lblPuerto = new javax.swing.JLabel();
        lblDatabase = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        tfHost = new javax.swing.JTextField();
        tfPort = new javax.swing.JTextField();
        tfDatabase = new javax.swing.JTextField();
        tfUser = new javax.swing.JTextField();
        tfPassword = new javax.swing.JPasswordField();
        pnl6 = new javax.swing.JPanel();
        pnl7 = new javax.swing.JPanel();
        btnSave = new javax.swing.JButton();
        btnCreateDB = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        pnl8 = new javax.swing.JPanel();
        lblServer = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("CONFIGURACION DEL SERVIDOR");
        setBackground(new java.awt.Color(255, 255, 255));
        setIconImage(getIconImage());
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Entrar.png"))); // NOI18N
        lblFondo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(lblFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 410, 360));

        pnl1.setBackground(new java.awt.Color(255, 255, 255));
        pnl1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnl1.setForeground(new java.awt.Color(255, 255, 255));
        pnl1.setOpaque(false);
        pnl1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnl2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnl2.setOpaque(false);
        pnl2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnl3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnl3.setOpaque(false);
        pnl3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitle1.setFont(new java.awt.Font("Dialog", 1, 32)); // NOI18N
        lblTitle1.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle1.setText("CONFIGURACION DEL");
        pnl3.add(lblTitle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 350, -1));

        lblTitle3.setFont(new java.awt.Font("Dialog", 1, 32)); // NOI18N
        lblTitle3.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle3.setText("SERVIDOR POSTGRES");
        pnl3.add(lblTitle3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 350, -1));

        pnl2.add(pnl3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 390, 100));

        pnl1.add(pnl2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 410, 120));

        pnl4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnl4.setOpaque(false);
        pnl4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnl5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnl5.setOpaque(false);
        pnl5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblLocalhost.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblLocalhost.setForeground(new java.awt.Color(255, 255, 0));
        lblLocalhost.setText("HOST:");
        pnl5.add(lblLocalhost, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, 50, 30));

        lblPuerto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblPuerto.setForeground(new java.awt.Color(255, 255, 0));
        lblPuerto.setText("PUERTO:");
        pnl5.add(lblPuerto, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, 70, 30));

        lblDatabase.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblDatabase.setForeground(new java.awt.Color(255, 255, 0));
        lblDatabase.setText("BASE DE DATO:");
        pnl5.add(lblDatabase, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 120, 30));

        lblUsuario.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblUsuario.setForeground(new java.awt.Color(255, 255, 0));
        lblUsuario.setText("USUARIO:");
        pnl5.add(lblUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, 80, 30));

        lblPassword.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblPassword.setForeground(new java.awt.Color(255, 255, 0));
        lblPassword.setText("CONTRASEÑA:");
        pnl5.add(lblPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 110, 30));

        tfHost.setBackground(new java.awt.Color(255, 255, 255));
        tfHost.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tfHost.setForeground(new java.awt.Color(0, 0, 0));
        tfHost.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        pnl5.add(tfHost, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, 160, 30));

        tfPort.setBackground(new java.awt.Color(255, 255, 255));
        tfPort.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tfPort.setForeground(new java.awt.Color(0, 0, 0));
        tfPort.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        tfPort.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfPortKeyTyped(evt);
            }
        });
        pnl5.add(tfPort, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 60, 160, 30));

        tfDatabase.setBackground(new java.awt.Color(255, 255, 255));
        tfDatabase.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tfDatabase.setForeground(new java.awt.Color(0, 0, 0));
        tfDatabase.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        pnl5.add(tfDatabase, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, 160, 30));

        tfUser.setBackground(new java.awt.Color(255, 255, 255));
        tfUser.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tfUser.setForeground(new java.awt.Color(0, 0, 0));
        tfUser.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        pnl5.add(tfUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 140, 160, 30));

        tfPassword.setBackground(new java.awt.Color(255, 255, 255));
        tfPassword.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tfPassword.setForeground(new java.awt.Color(0, 0, 0));
        tfPassword.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        pnl5.add(tfPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 180, 160, 30));

        pnl4.add(pnl5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 360, 250));

        pnl1.add(pnl4, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 140, 380, 270));

        pnl6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnl6.setOpaque(false);
        pnl6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnl7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnl7.setOpaque(false);
        pnl7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnSave.setBackground(new java.awt.Color(0, 0, 153));
        btnSave.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        btnSave.setForeground(new java.awt.Color(255, 255, 0));
        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/guardar.png"))); // NOI18N
        btnSave.setText("GUARDAR");
        btnSave.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSave.setOpaque(false);
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        pnl7.add(btnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 10, 120, 50));

        btnCreateDB.setBackground(new java.awt.Color(0, 0, 153));
        btnCreateDB.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        btnCreateDB.setForeground(new java.awt.Color(255, 255, 0));
        btnCreateDB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Database.png"))); // NOI18N
        btnCreateDB.setText("CREAR BD");
        btnCreateDB.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCreateDB.setOpaque(false);
        btnCreateDB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateDBActionPerformed(evt);
            }
        });
        pnl7.add(btnCreateDB, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 120, 50));

        btnSalir.setBackground(new java.awt.Color(0, 0, 0));
        btnSalir.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        btnSalir.setForeground(new java.awt.Color(204, 0, 0));
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/del.png"))); // NOI18N
        btnSalir.setText("SALIR");
        btnSalir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSalir.setOpaque(false);
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        pnl7.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 110, 50));

        pnl6.add(pnl7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 360, 70));

        pnl1.add(pnl6, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 410, 380, 90));

        pnl8.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnl8.setOpaque(false);
        pnl8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblServer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Servidor.png"))); // NOI18N
        lblServer.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnl8.add(lblServer, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 7, 360, 100));

        pnl1.add(pnl8, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 10, 380, 120));

        getContentPane().add(pnl1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 820, 510));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        if (evt.getSource() == btnSave) {
            if (Validacion()) {
                try {
                    configServer();
                    btnSave.setEnabled(false);
                    btnCreateDB.setEnabled(true);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        if (evt.getSource() == btnSalir) {
            dispose();
        }
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnCreateDBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateDBActionPerformed
        if (evt.getSource() == btnCreateDB) {
            InitTable init = new InitTable();
            init.createDB();
            init.createTable();
            Disabled();
        }
    }//GEN-LAST:event_btnCreateDBActionPerformed

    private void tfPortKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfPortKeyTyped
        char num = evt.getKeyChar();
        if (!Character.isDigit(num)) {
            evt.consume();
        }
    }//GEN-LAST:event_tfPortKeyTyped

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Dialog".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> {
            new Server().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreateDB;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel lblDatabase;
    private javax.swing.JLabel lblFondo;
    private javax.swing.JLabel lblLocalhost;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblPuerto;
    private javax.swing.JLabel lblServer;
    private javax.swing.JLabel lblTitle1;
    private javax.swing.JLabel lblTitle3;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JPanel pnl1;
    private javax.swing.JPanel pnl2;
    private javax.swing.JPanel pnl3;
    private javax.swing.JPanel pnl4;
    private javax.swing.JPanel pnl5;
    private javax.swing.JPanel pnl6;
    private javax.swing.JPanel pnl7;
    private javax.swing.JPanel pnl8;
    private javax.swing.JTextField tfDatabase;
    private javax.swing.JTextField tfHost;
    private javax.swing.JPasswordField tfPassword;
    private javax.swing.JTextField tfPort;
    private javax.swing.JTextField tfUser;
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