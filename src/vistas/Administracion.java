/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package vistas;


import vistas.Proyeccion.ProyeccionesView;
import vistas.Comprador.CompradorView;
import vistas.Comprador.Compradores;
import vistas.Sala.SalasView;
import persistencia.PeliculaData;
import vistas.ticket.TicketsView;

/**
 *
 * @author Morbo
 */
public class Administracion extends javax.swing.JInternalFrame {


    public Administracion() {
        initComponents();

        tabAdmin.addTab("Peliculas", new Compradores());
        tabAdmin.addTab("Salas", new SalasView());
        tabAdmin.addTab("Proyecciones", new ProyeccionesView());
        tabAdmin.add("Compradores", new CompradorView());
        tabAdmin.add("Tickets" , new  TicketsView());
   
    }
     

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabAdmin = new javax.swing.JTabbedPane();

        setBackground(new java.awt.Color(51, 51, 51));
        setClosable(true);
        setMinimumSize(new java.awt.Dimension(900, 500));
        setPreferredSize(new java.awt.Dimension(900, 500));

        tabAdmin.setBackground(new java.awt.Color(102, 0, 0));
        tabAdmin.setForeground(new java.awt.Color(102, 0, 0));
        tabAdmin.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        tabAdmin.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, 876, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, 452, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane tabAdmin;
    // End of variables declaration//GEN-END:variables
}
