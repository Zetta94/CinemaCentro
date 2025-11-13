package vistas.ComprarEntradas;

import entidades.Comprador;
import entidades.Lugar;
import entidades.Proyeccion;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import persistencia.Context;
import servicios.CompraServicio;

/**
 *
 * @author Morbo
 */
public class Confirmar extends javax.swing.JPanel {

    private Comprador comprador;
    private Proyeccion proyeccion;
    private List<Lugar> lugares;

    private CompraServicio compraServicio = new CompraServicio();

    public Confirmar() {
        initComponents();
    }

    public void setData(Comprador comprador, Proyeccion proyeccion, List<Lugar> lugares) {
        this.comprador = comprador;
        this.proyeccion = proyeccion;
        this.lugares = lugares;
        mostrarResumen();
    }

    private void mostrarResumen() {
        if (comprador == null || proyeccion == null || lugares == null || lugares.isEmpty()) {
            txtInfo.setText("Faltan datos para mostrar el resumen de la compra.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("🎬  RESUMEN DE COMPRA\n");
        sb.append("───────────────────────────────\n");

        sb.append("🗓 Fecha de la función: ").append(proyeccion.getFecha()).append("\n");

        sb.append("🎞 Película: ").append(proyeccion.getPelicula().getTitulo()).append("\n");
        sb.append("🏛 Sala: ").append(proyeccion.getSala().getNroSala()).append("\n");
        sb.append("🕒 Horario: ").append(proyeccion.getHoraInicio()).append(" - ").append(proyeccion.getHoraFin()).append("\n\n");

        sb.append("🎟 Lugares seleccionados:\n");
        for (Lugar lugar : lugares) {
            sb.append(" - Fila ").append(lugar.getFila())
                    .append(", Asiento ").append(lugar.getNumero()).append("\n");
        }

        double total = proyeccion.getPrecio() * lugares.size();
        sb.append("\n💰 Total a pagar: $").append(String.format("%.2f", total)).append("\n");

        txtInfo.setText(sb.toString());
    }

    public void confirmarCompra() {
        if (comprador == null || proyeccion == null || lugares == null || lugares.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Faltan datos para finalizar la compra.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        String codigo = compraServicio.guardarCompra(comprador, lugares, proyeccion);

        if (codigo == null) {
            JOptionPane.showMessageDialog(this,
                    "Error al guardar la compra.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(
                this,
                "DNI: " + comprador.getDni()
                + "\nCódigo de compra: " + codigo
                + "\n\nRetire las entradas por boletería.",
                "Compra realizada con éxito",
                JOptionPane.INFORMATION_MESSAGE
        );

        java.awt.Container parent = this.getParent();

        while (parent != null && !(parent instanceof javax.swing.JInternalFrame)) {
            parent = parent.getParent();
        }

        if (parent != null) {
            ((javax.swing.JInternalFrame) parent).dispose();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtInfo = new javax.swing.JTextPane();

        setBackground(new java.awt.Color(51, 51, 51));
        setMinimumSize(new java.awt.Dimension(882, 396));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Confirmarcompra.png"))); // NOI18N

        txtInfo.setBackground(new java.awt.Color(77, 77, 77));
        txtInfo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jScrollPane2.setViewportView(txtInfo);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(256, 256, 256)
                .addComponent(jLabel1)
                .addContainerGap(382, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextPane txtInfo;
    // End of variables declaration//GEN-END:variables
}
