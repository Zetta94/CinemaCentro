package vistas.Comprador;

import entidades.Comprador;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javax.swing.JOptionPane;
import persistencia.CompradorData;
import persistencia.Context;


public class ModificarComprador extends javax.swing.JInternalFrame {

    private Comprador comprador;
    private CompradorData compradorData = Context.getCompradorData();

    public ModificarComprador(Comprador comprador) {
        initComponents();
        this.comprador = comprador;
        setTitle("Modificar Comprador");
        inicializarComboPago();
        cargarDatos();
    }

    private void inicializarComboPago() {
        cbxMedioPago.removeAllItems();
        cbxMedioPago.addItem("Efectivo");
        cbxMedioPago.addItem("Tarjeta");
        cbxMedioPago.addItem("Transferencia");
    }

    private void cargarDatos() {
        txtDni.setText(comprador.getDni());
        txtNombre.setText(comprador.getNombre());
        txtPassword.setText(comprador.getPassword());

        if (comprador.getFechaNac() != null) {
            Date fecha = Date.from(comprador.getFechaNac().atStartOfDay(ZoneId.systemDefault()).toInstant());
            jDateFecNac.setDate(fecha);
        }

        if (comprador.getMedioPago() != null) {
            cbxMedioPago.setSelectedItem(comprador.getMedioPago());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel1 = new java.awt.Panel();
        lblCapacidad = new javax.swing.JLabel();
        lblNroDeSala = new javax.swing.JLabel();
        txtDni = new javax.swing.JTextField();
        btnSalir = new javax.swing.JButton();
        btnGurardar = new javax.swing.JButton();
        txtNombre = new javax.swing.JTextField();
        lblMedioDePago = new javax.swing.JLabel();
        txtPassword = new javax.swing.JTextField();
        lblFecNac = new javax.swing.JLabel();
        cbxMedioPago = new javax.swing.JComboBox<>();
        lblPassword = new javax.swing.JLabel();
        jDateFecNac = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();

        panel1.setBackground(new java.awt.Color(51, 51, 51));
        panel1.setForeground(new java.awt.Color(38, 64, 107));
        panel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCapacidad.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblCapacidad.setForeground(new java.awt.Color(255, 204, 0));
        lblCapacidad.setText("Nombre:");
        panel1.add(lblCapacidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 240, -1, 20));

        lblNroDeSala.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblNroDeSala.setForeground(new java.awt.Color(255, 204, 0));
        lblNroDeSala.setText("DNI:");
        panel1.add(lblNroDeSala, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 200, -1, 20));

        txtDni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDniActionPerformed(evt);
            }
        });
        panel1.add(txtDni, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 190, 170, 30));

        btnSalir.setBackground(new java.awt.Color(102, 102, 102));
        btnSalir.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        panel1.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 460, 100, 30));

        btnGurardar.setBackground(new java.awt.Color(102, 0, 0));
        btnGurardar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnGurardar.setForeground(new java.awt.Color(204, 204, 204));
        btnGurardar.setText("Guardar");
        btnGurardar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnGurardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGurardarActionPerformed(evt);
            }
        });
        panel1.add(btnGurardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 460, 100, 30));

        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });
        panel1.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 230, 170, 30));

        lblMedioDePago.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblMedioDePago.setForeground(new java.awt.Color(255, 204, 0));
        lblMedioDePago.setText("Medio de Pago:");
        panel1.add(lblMedioDePago, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 380, 90, 20));

        txtPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPasswordActionPerformed(evt);
            }
        });
        panel1.add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 330, 170, 30));

        lblFecNac.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblFecNac.setForeground(new java.awt.Color(255, 204, 0));
        lblFecNac.setText("Fecha de Nacimiento:");
        panel1.add(lblFecNac, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 290, -1, 20));

        cbxMedioPago.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        panel1.add(cbxMedioPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 372, 170, 30));

        lblPassword.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblPassword.setForeground(new java.awt.Color(255, 204, 0));
        lblPassword.setText("Password:");
        panel1.add(lblPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 340, -1, 20));
        panel1.add(jDateFecNac, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 280, 170, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/MOdiCOmpra.png"))); // NOI18N
        panel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-50, -200, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, 588, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtDniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDniActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDniActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnGurardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGurardarActionPerformed
         String dni = txtDni.getText().trim();
        String nombre = txtNombre.getText().trim();
        Date fecha = jDateFecNac.getDate();
        String password = txtPassword.getText().trim();
        String medioPago = cbxMedioPago.getSelectedItem().toString();

        if (dni.isEmpty() || nombre.isEmpty() || password.isEmpty() || fecha == null) {
            JOptionPane.showMessageDialog(this, "Complete todos los campos.");
            return;
        }

        LocalDate fechaNac = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        comprador.setDni(dni);
        comprador.setNombre(nombre);
        comprador.setFechaNac(fechaNac);
        comprador.setPassword(password);
        comprador.setMedioPago(medioPago);

        boolean actualizado = compradorData.modificarComprador(comprador);

        if (actualizado) {
            JOptionPane.showMessageDialog(this, "Comprador modificado correctamente.");
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Error al modificar el comprador.");
        }
    }//GEN-LAST:event_btnGurardarActionPerformed

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPasswordActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGurardar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox<String> cbxMedioPago;
    private com.toedter.calendar.JDateChooser jDateFecNac;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblCapacidad;
    private javax.swing.JLabel lblFecNac;
    private javax.swing.JLabel lblMedioDePago;
    private javax.swing.JLabel lblNroDeSala;
    private javax.swing.JLabel lblPassword;
    private java.awt.Panel panel1;
    private javax.swing.JTextField txtDni;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPassword;
    // End of variables declaration//GEN-END:variables
}
