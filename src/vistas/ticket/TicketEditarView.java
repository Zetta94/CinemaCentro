/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package vistas.ticket;

import entidades.TicketCompra;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JOptionPane;
import persistencia.Context;
import persistencia.DetalleTicketData;
import persistencia.ProyeccionData;
import persistencia.TicketCompraData;


public class TicketEditarView extends javax.swing.JInternalFrame {

    private TicketCompra ticket;
    private DetalleTicketData detalleData = Context.getDetalleTicketData();
    private ProyeccionData proyeccionData = Context.getProyeccionData();
    private TicketCompraData ticketData = Context.getTicketCompraData();
    private List<entidades.Comprador> listaCompradores;

    public TicketEditarView(TicketCompra ticket) {
        this.ticket = ticket;
        initComponents();
        cargarDatosTicket();
        cargarCompradores();

        txtBuscaPorNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                filtrarCompradores(txtBuscaPorNombre.getText());
            }
        });
    }

    private void cargarDatosTicket() {

        lblCodigoValor.setText(ticket.getCodigoTicket());

        if (ticket.getNombreComprador() != null && ticket.getDniComprador() != null) {
            lblCompradorValor.setText(ticket.getNombreComprador() + " (DNI: " + ticket.getDniComprador() + ")");
        } else {
            lblCompradorValor.setText("Sin información");
        }
        if (ticket.getFechaCompra() != null) {
            jDateCompra.setDate(java.sql.Date.valueOf(ticket.getFechaCompra()));
        }

        if (ticket.getFechaFuncion() != null) {
            jDateFuncion.setDate(java.sql.Date.valueOf(ticket.getFechaFuncion()));
        }

        txtMonto.setText(String.valueOf(ticket.getMonto()));
    }

    private void cargarCompradores() {
        listaCompradores = Context.getCompradorData().obtenerTodosLosCompradores();

        javax.swing.DefaultListModel<String> modelo = new javax.swing.DefaultListModel<>();

        for (entidades.Comprador c : listaCompradores) {
            modelo.addElement(c.getNombre() + " - DNI: " + c.getDni());
        }

        jList1.setModel(modelo);
    }

    private void filtrarCompradores(String texto) {

        javax.swing.DefaultListModel<String> modelo = new javax.swing.DefaultListModel<>();

        for (entidades.Comprador c : listaCompradores) {
            if (c.getNombre().toLowerCase().contains(texto.toLowerCase())) {
                modelo.addElement(c.getNombre() + " - DNI: " + c.getDni());
            }
        }

        jList1.setModel(modelo);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblCodigo = new javax.swing.JLabel();
        lblFecCompra = new javax.swing.JLabel();
        lblFecFuncion = new javax.swing.JLabel();
        btnSalir = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        jDateCompra = new com.toedter.calendar.JDateChooser();
        jDateFuncion = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        lblCodigoValor = new java.awt.Label();
        jLabel2 = new javax.swing.JLabel();
        txtBuscaPorNombre = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        lblCompradorValor = new javax.swing.JLabel();
        txtMonto = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        lblCodigo.setText("Código");

        lblFecCompra.setText("Fecha de compra");

        lblFecFuncion.setText("Fecha de Funcion");

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        jLabel1.setText("Comprador");

        jLabel2.setText("Seleccionar nuevo comprador");

        txtBuscaPorNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscaPorNombreActionPerformed(evt);
            }
        });

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        jLabel3.setText("Monto total");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(193, 193, 193)
                        .addComponent(btnGuardar)
                        .addGap(129, 129, 129)
                        .addComponent(btnSalir)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(202, 202, 202)
                                .addComponent(txtBuscaPorNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblCodigo)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel3))
                                .addGap(39, 39, 39)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtMonto, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                                    .addComponent(lblCodigoValor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblCompradorValor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblFecCompra)
                                    .addComponent(lblFecFuncion))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jDateFuncion, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                                    .addComponent(jDateCompra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jScrollPane1))
                        .addGap(109, 109, 109))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblFecCompra)
                        .addComponent(lblCodigo))
                    .addComponent(jDateCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCodigoValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFecFuncion)
                    .addComponent(jDateFuncion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lblCompradorValor, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtBuscaPorNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(72, 72, 72)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalir)
                    .addComponent(btnGuardar))
                .addGap(84, 84, 84))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed

        try {
            if (jDateCompra.getDate() != null) {
                LocalDate fechaCompra = new java.sql.Date(jDateCompra.getDate().getTime()).toLocalDate();
                ticket.setFechaCompra(fechaCompra);
            }

            if (jDateFuncion.getDate() != null) {
                LocalDate fechaFuncion = new java.sql.Date(jDateFuncion.getDate().getTime()).toLocalDate();
                ticket.setFechaFuncion(fechaFuncion);
            }

            try {
                double monto = Double.parseDouble(txtMonto.getText().trim());
                ticket.setMonto(monto);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Monto inválido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int index = jList1.getSelectedIndex();
            if (index != -1) {
                String seleccionado = jList1.getSelectedValue();

                for (entidades.Comprador c : listaCompradores) {
                    if (seleccionado.contains(c.getDni())) {
                        ticket.setIdComprador(c.getIdComprador());
                        ticket.setNombreComprador(c.getNombre());
                        ticket.setDniComprador(c.getDni());
                        break;
                    }
                }
            }

            ticketData.modificarTicket(ticket);

            dispose();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Error al guardar los cambios:\n" + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtBuscaPorNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscaPorNombreActionPerformed
        filtrarCompradores(txtBuscaPorNombre.getText().trim());
    }//GEN-LAST:event_txtBuscaPorNombreActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnSalir;
    private com.toedter.calendar.JDateChooser jDateCompra;
    private com.toedter.calendar.JDateChooser jDateFuncion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCodigo;
    private java.awt.Label lblCodigoValor;
    private javax.swing.JLabel lblCompradorValor;
    private javax.swing.JLabel lblFecCompra;
    private javax.swing.JLabel lblFecFuncion;
    private javax.swing.JTextField txtBuscaPorNombre;
    private javax.swing.JTextField txtMonto;
    // End of variables declaration//GEN-END:variables
}
