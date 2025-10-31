package vistas;

import entidades.Sala;
import javax.swing.JOptionPane;
import persistencia.Context;
import persistencia.SalaData;

public class ModificarSalas extends javax.swing.JInternalFrame {

    private Sala sala;
    private SalaData salaData = Context.getSalaData();

    public ModificarSalas(Sala sala) {
        initComponents();
        this.sala = sala;
        setTitle("Modificar Sala");
        configurarFormulario();
        cargarDatos();
    }

    private void configurarFormulario() {
        lblNroDeSala.setText("Número de Sala:");
        lblCapacidad.setText("Capacidad:");
        lbl3D.setText("Apta 3D:");
        lblEstado.setText("Estado:");

        bSi.setText("Sí");
        bNo.setText("No");
        btnEstado.add(bActiva);
        btnEstado.add(bInactiva);
    }

    private void cargarDatos() {
        txtNroSala.setText(String.valueOf(sala.getNroSala()));
        txtCapacidad.setText(String.valueOf(sala.getCapacidad()));
        
        if (sala.isApta3D()) {
            bSi.setSelected(true);
        } else {
            bNo.setSelected(true);
        }
        
        if (sala.isEstado()) {
            bActiva.setSelected(true);
        } else {
            bInactiva.setSelected(true);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        botones = new javax.swing.ButtonGroup();
        btnEstado = new javax.swing.ButtonGroup();
        panel1 = new java.awt.Panel();
        lblCapacidad = new javax.swing.JLabel();
        lblNroDeSala = new javax.swing.JLabel();
        txtNroSala = new javax.swing.JTextField();
        btnSalir = new javax.swing.JButton();
        btnGurardar = new javax.swing.JButton();
        txtCapacidad = new javax.swing.JTextField();
        lbl3D = new javax.swing.JLabel();
        bSi = new javax.swing.JRadioButton();
        bNo = new javax.swing.JRadioButton();
        lblEstado = new javax.swing.JLabel();
        bActiva = new javax.swing.JRadioButton();
        bInactiva = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();

        panel1.setBackground(new java.awt.Color(51, 51, 51));
        panel1.setForeground(new java.awt.Color(38, 64, 107));
        panel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCapacidad.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblCapacidad.setForeground(new java.awt.Color(255, 204, 0));
        lblCapacidad.setText("Capacidad:");
        panel1.add(lblCapacidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 250, -1, 20));

        lblNroDeSala.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblNroDeSala.setForeground(new java.awt.Color(255, 204, 0));
        lblNroDeSala.setText("Nro de sala:");
        panel1.add(lblNroDeSala, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 210, 70, 20));

        txtNroSala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNroSalaActionPerformed(evt);
            }
        });
        panel1.add(txtNroSala, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 200, 170, 30));

        btnSalir.setBackground(new java.awt.Color(102, 102, 102));
        btnSalir.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSalir.setForeground(new java.awt.Color(204, 204, 204));
        btnSalir.setText("Salir");
        btnSalir.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        panel1.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 410, 100, 30));

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
        panel1.add(btnGurardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 410, 100, 30));

        txtCapacidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCapacidadActionPerformed(evt);
            }
        });
        panel1.add(txtCapacidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 240, 170, 30));

        lbl3D.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl3D.setForeground(new java.awt.Color(255, 204, 0));
        lbl3D.setText("3D:");
        panel1.add(lbl3D, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 290, 30, 30));

        botones.add(bSi);
        bSi.setForeground(new java.awt.Color(255, 204, 51));
        bSi.setText("Si");
        bSi.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        bSi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSiActionPerformed(evt);
            }
        });
        panel1.add(bSi, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 290, -1, 30));

        botones.add(bNo);
        bNo.setForeground(new java.awt.Color(255, 204, 51));
        bNo.setText("No");
        bNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bNoActionPerformed(evt);
            }
        });
        panel1.add(bNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 290, -1, 30));

        lblEstado.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblEstado.setForeground(new java.awt.Color(255, 204, 0));
        lblEstado.setText("Estado:");
        lblEstado.setToolTipText("");
        panel1.add(lblEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 340, -1, 20));

        botones.add(bActiva);
        bActiva.setForeground(new java.awt.Color(255, 204, 51));
        bActiva.setText("Activa");
        bActiva.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        bActiva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bActivaActionPerformed(evt);
            }
        });
        panel1.add(bActiva, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 340, -1, 20));

        botones.add(bInactiva);
        bInactiva.setForeground(new java.awt.Color(255, 204, 51));
        bInactiva.setText("Inactiva");
        bInactiva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bInactivaActionPerformed(evt);
            }
        });
        panel1.add(bInactiva, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 340, -1, 20));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ModifSala.png"))); // NOI18N
        panel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-60, -200, -1, -1));

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

    private void txtNroSalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNroSalaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNroSalaActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnGurardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGurardarActionPerformed
            int nroSala = Integer.parseInt(txtNroSala.getText().trim());
            int capacidad = Integer.parseInt(txtCapacidad.getText().trim());
            boolean apta3D = bSi.isSelected();
            boolean estado = bActiva.isSelected();
            sala.setNroSala(nroSala);
            sala.setCapacidad(capacidad);
            sala.setApta3D(apta3D);
            sala.setEstado(estado);
            salaData.modificarSala(sala);
            this.dispose();
    }//GEN-LAST:event_btnGurardarActionPerformed

    private void bSiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bSiActionPerformed

    private void bNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bNoActionPerformed

    private void txtCapacidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCapacidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCapacidadActionPerformed

    private void bActivaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bActivaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bActivaActionPerformed

    private void bInactivaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bInactivaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bInactivaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton bActiva;
    private javax.swing.JRadioButton bInactiva;
    private javax.swing.JRadioButton bNo;
    private javax.swing.JRadioButton bSi;
    private javax.swing.ButtonGroup botones;
    private javax.swing.ButtonGroup btnEstado;
    private javax.swing.JButton btnGurardar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lbl3D;
    private javax.swing.JLabel lblCapacidad;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblNroDeSala;
    private java.awt.Panel panel1;
    private javax.swing.JTextField txtCapacidad;
    private javax.swing.JTextField txtNroSala;
    // End of variables declaration//GEN-END:variables
}
