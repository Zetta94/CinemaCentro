/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package vistas.ticket;

import entidades.DetalleTicket;
import entidades.Proyeccion;
import entidades.TicketCompra;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import persistencia.Context;
import persistencia.DetalleTicketData;
import persistencia.ProyeccionData;
import persistencia.TicketCompraData;

/**
 *
 * Zuñiga Manuel
 */
public class TicketEditarView extends javax.swing.JInternalFrame {

    private TicketCompra ticket;
    private DetalleTicketData detalleData = Context.getDetalleTicketData();
    private ProyeccionData proyeccionData = Context.getProyeccionData();
    private TicketCompraData ticketData = Context.getTicketCompraData();

    public TicketEditarView(TicketCompra ticket) {
        this.ticket = ticket;
        initComponents();
        setTitle("Editar Ticket #" + ticket.getIdTicket());
        cargarDatosTicket();
        cargarDetalles();
        txtCodigo.setEditable(false);
        txtFechaCompra.setEditable(false);
        txtFechaFuncion.setEditable(false);
    }

    private void cargarDatosTicket() {
        txtCodigo.setText(ticket.getCodigoTicket());
        txtFechaCompra.setText(ticket.getFechaCompra().toString());
        txtFechaFuncion.setText(ticket.getFechaFuncion().toString());
    }

    private void cargarDetalles() {
        List<DetalleTicket> detalles = detalleData.obtenerDetallesPorTicket(ticket.getIdTicket());
        DefaultTableModel model = (DefaultTableModel) tblDetalles.getModel();
        model.setRowCount(0);

        for (DetalleTicket d : detalles) {
            Proyeccion p = proyeccionData.obtenerProyeccionPorId(d.getIdDetalle());
            String proyeccionInfo = "Desconocida";
            if (p != null && p.getPelicula() != null && p.getSala() != null) {
                proyeccionInfo = p.getPelicula().getTitulo() + " | Sala " + p.getSala().getNroSala() + " | " + p.getFecha();
            }

            model.addRow(new Object[]{
                d.getIdDetalle(),
                proyeccionInfo,
                d.getCantidad(),
                String.format("$%.2f", d.getSubtotal())
            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblCodigo = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        lblFecCompra = new javax.swing.JLabel();
        lblFecFuncion = new javax.swing.JLabel();
        txtFechaCompra = new javax.swing.JTextField();
        txtFechaFuncion = new javax.swing.JTextField();
        btnSalir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDetalles = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        lblCodigo.setText("Código");

        txtCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoActionPerformed(evt);
            }
        });

        lblFecCompra.setText("Fecha de compra");

        lblFecFuncion.setText("Fecha de Funcion");

        txtFechaCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaCompraActionPerformed(evt);
            }
        });

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        tblDetalles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id", "Proyeccion", "Cantidad", "Subtotal"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblDetalles);
        if (tblDetalles.getColumnModel().getColumnCount() > 0) {
            tblDetalles.getColumnModel().getColumn(0).setPreferredWidth(2);
        }

        jLabel1.setText("Los detalles de un ticket no pueden ser modificados.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 685, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblCodigo)
                                        .addGap(79, 79, 79)
                                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(69, 69, 69)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblFecCompra)
                                    .addComponent(lblFecFuncion))
                                .addGap(37, 37, 37)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtFechaCompra)
                                    .addComponent(txtFechaFuncion, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(357, 357, 357)
                        .addComponent(btnSalir)))
                .addContainerGap(100, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(38, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblFecCompra)
                        .addComponent(lblCodigo)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtFechaCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblFecFuncion)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtFechaFuncion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSalir)
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoActionPerformed

    private void txtFechaCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaCompraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaCompraActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        dispose();
    }//GEN-LAST:event_btnSalirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblFecCompra;
    private javax.swing.JLabel lblFecFuncion;
    private javax.swing.JTable tblDetalles;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtFechaCompra;
    private javax.swing.JTextField txtFechaFuncion;
    // End of variables declaration//GEN-END:variables
}
