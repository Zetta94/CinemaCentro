/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vistas.estadisticas;

import entidades.Pelicula;
import entidades.TicketCompra;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import persistencia.Context;
import persistencia.PeliculaData;
import persistencia.TicketCompraData;

/**
 *
 * @author Manuel Zuñiga
 */
public class EstadisticasView extends javax.swing.JPanel {

    private PeliculaData peliculaData = Context.getPeliculaData();
    private TicketCompraData ticketData = Context.getTicketCompraData();

    public EstadisticasView() {
        initComponents();
        cargarMasVistas();
        cargarListaPeliculas();
        configurarBuscador();
    }

    private void cargarMasVistas() {
        List<Pelicula> peliculas = peliculaData.obtenerPeliculasMasVistas(6);
        DefaultTableModel model = (DefaultTableModel) tblMasVistas.getModel();
        model.setRowCount(0);
        for (Pelicula p : peliculas) {
            int espectadores = ticketData.contarTicketsPorPelicula(p.getIdPelicula());
            model.addRow(new Object[]{p.getTitulo(), espectadores});
        }
    }

    private void cargarListaPeliculas() {
        DefaultListModel<String> model = new DefaultListModel<>();
        for (Pelicula p : peliculaData.obtenerTodas()) {
            model.addElement(p.getTitulo());
        }
        jlistPeliculas.setModel(model);
    }

    private void configurarBuscador() {
        txtPeliculasBuscador.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                filtrarLista();
            }

            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                filtrarLista();
            }

            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                filtrarLista();
            }
        });
    }

    private void filtrarLista() {
        String filtro = txtPeliculasBuscador.getText().toLowerCase();
        DefaultListModel<String> model = new DefaultListModel<>();
        for (Pelicula p : peliculaData.obtenerTodas()) {
            if (p.getTitulo().toLowerCase().contains(filtro)) {
                model.addElement(p.getTitulo());
            }
        }
        jlistPeliculas.setModel(model);
    }

    private void mostrarInforme(String texto) {
        InformeView informe = new InformeView();
        informe.mostrarTexto(texto);

        javax.swing.JDesktopPane desktop = null;
        java.awt.Container parent = this.getParent();
        while (parent != null) {
            if (parent instanceof javax.swing.JDesktopPane) {
                desktop = (javax.swing.JDesktopPane) parent;
                break;
            }
            parent = parent.getParent();
        }

        if (desktop != null) {
            desktop.add(informe);
            informe.setVisible(true);
            try {
                informe.setSelected(true);
            } catch (java.beans.PropertyVetoException ex) {
                ex.printStackTrace();
            }
        } else {
            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "No se encontró el contenedor principal (JDesktopPane).",
                    "Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE
            );
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMasVistas = new javax.swing.JTable();
        btnTicketPorPelicula = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jlistPeliculas = new javax.swing.JList<>();
        jLabel3 = new javax.swing.JLabel();
        txtPeliculasBuscador = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btnTicketPorFecha = new javax.swing.JButton();
        TicketDate = new com.toedter.calendar.JDateChooser();
        btnMasVistas = new javax.swing.JButton();

        setBackground(new java.awt.Color(33, 33, 33));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 153, 0));
        jLabel1.setText("Ranking Peliculas mas vistas");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Estadisiticaas.png"))); // NOI18N

        tblMasVistas.setBackground(new java.awt.Color(204, 204, 204));
        tblMasVistas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Nombre", "Espectadores"
            }
        ));
        jScrollPane1.setViewportView(tblMasVistas);

        btnTicketPorPelicula.setBackground(new java.awt.Color(153, 0, 0));
        btnTicketPorPelicula.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTicketPorPelicula.setForeground(new java.awt.Color(204, 204, 204));
        btnTicketPorPelicula.setText("Generar Informe");
        btnTicketPorPelicula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTicketPorPeliculaActionPerformed(evt);
            }
        });

        jlistPeliculas.setBackground(new java.awt.Color(204, 204, 204));
        jlistPeliculas.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jlistPeliculas);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 153, 0));
        jLabel3.setText("Informe de tickets para una pelicula especifica");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(204, 153, 0));
        jLabel4.setText("Informe de tickets por fecha");

        btnTicketPorFecha.setBackground(new java.awt.Color(153, 0, 0));
        btnTicketPorFecha.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTicketPorFecha.setForeground(new java.awt.Color(204, 204, 204));
        btnTicketPorFecha.setText("Generar Informe");
        btnTicketPorFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTicketPorFechaActionPerformed(evt);
            }
        });

        btnMasVistas.setBackground(new java.awt.Color(153, 0, 0));
        btnMasVistas.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnMasVistas.setForeground(new java.awt.Color(204, 204, 204));
        btnMasVistas.setText("Generar Informe");
        btnMasVistas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMasVistasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TicketDate, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(btnMasVistas)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 208, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(txtPeliculasBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTicketPorPelicula))
                .addGap(150, 150, 150))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(btnTicketPorFecha))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(299, 299, 299)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtPeliculasBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnTicketPorPelicula)
                        .addGap(5, 5, 5))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnMasVistas)
                        .addGap(17, 17, 17)))
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(TicketDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnTicketPorFecha)
                .addGap(60, 60, 60))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnTicketPorPeliculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTicketPorPeliculaActionPerformed
        String titulo = jlistPeliculas.getSelectedValue();
        if (titulo == null) {
            JOptionPane.showMessageDialog(this, "Seleccione una película de la lista.");
            return;
        }

        Pelicula pelicula = peliculaData.buscarPorTitulo(titulo);
        if (pelicula == null) {
            JOptionPane.showMessageDialog(this, "Película no encontrada.");
            return;
        }

        List<TicketCompra> tickets = ticketData.listarTicketsPorPelicula(pelicula.getIdPelicula());
        if (tickets.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay tickets registrados para esta película.");
            return;
        }

        StringBuilder informe = new StringBuilder();
        informe.append("🎬 INFORME DE TICKETS PARA: ").append(pelicula.getTitulo()).append("\n\n");

        for (TicketCompra t : tickets) {
            String nombre = t.getComprador() != null ? t.getComprador().getNombre() : "Desconocido";
            String dni = t.getComprador() != null ? t.getComprador().getDni() : "-";
            String medioPago = t.getComprador() != null ? t.getComprador().getMedioPago() : "-";
            String genero = (t.getPelicula() != null) ? t.getPelicula().getGenero() : "-";
            String hora = (t.getHora() != null) ? t.getHora().toString() : "-";

            informe.append("🎟 Ticket N°").append(t.getIdTicket())
                    .append(" | Fecha Compra: ").append(t.getFechaCompra())
                    .append(" | Función: ").append(hora).append(" hs\n")
                    .append("👤 Comprador: ").append(nombre)
                    .append(" | DNI: ").append(dni)
                    .append(" | Medio de pago: ").append(medioPago).append("\n")
                    .append("💰 Monto: $").append(t.getMonto()).append("\n")
                    .append("────────────────────────────────────────────\n");
        }

        mostrarInforme(informe.toString());
    }//GEN-LAST:event_btnTicketPorPeliculaActionPerformed

    private void btnTicketPorFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTicketPorFechaActionPerformed
        Date fecha = TicketDate.getDate();
        if (fecha == null) {
            JOptionPane.showMessageDialog(this, "Seleccione una fecha válida.");
            return;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fechaStr = sdf.format(fecha);
        LocalDate localDate = LocalDate.parse(fechaStr);
        List<TicketCompra> tickets = ticketData.listarTicketsPorFecha(localDate);

        if (tickets.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay tickets para la fecha seleccionada.");
            return;
        }

        StringBuilder informe = new StringBuilder();
        informe.append("📅 INFORME DE TICKETS DEL DÍA ").append(fechaStr).append("\n\n");

        for (TicketCompra t : tickets) {
            String titulo = t.getPelicula() != null ? t.getPelicula().getTitulo() : "Sin título";
            String genero = t.getPelicula() != null ? t.getPelicula().getGenero() : "-";
            String idioma = (t.getPelicula() != null && t.getHora() != null) ? "Español" : "-";
            String nombreComprador = t.getComprador() != null ? t.getComprador().getNombre() : "Desconocido";
            String medioPago = t.getComprador() != null ? t.getComprador().getMedioPago() : "-";

            informe.append("🎬 Película: ").append(titulo).append(" (").append(genero).append(")\n")
                    .append("👤 Comprador: ").append(nombreComprador)
                    .append(" | Medio de pago: ").append(medioPago).append("\n")
                    .append("💰 Monto: $").append(t.getMonto()).append("\n")
                    .append("🕓 Hora función: ").append(t.getHora() != null ? t.getHora() : "-").append("\n")
                    .append("──────────────────────────────\n");
        }

        mostrarInforme(informe.toString());
    }//GEN-LAST:event_btnTicketPorFechaActionPerformed

    private void btnMasVistasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMasVistasActionPerformed
        List<Pelicula> peliculas = peliculaData.obtenerPeliculasMasVistas(4);
        if (peliculas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se encontraron películas registradas.");
            return;
        }

        StringBuilder informe = new StringBuilder();
        informe.append("📊 INFORME DE PELÍCULAS MÁS VISTAS\n\n");

        LocalDate hoy = LocalDate.now();

        for (Pelicula p : peliculas) {

            int espectadores = ticketData.contarTicketsPorPelicula(p.getIdPelicula());

            LocalDate proximaFuncion = peliculaData.obtenerProximaFuncion(p.getIdPelicula());

            String estadoFecha = "";
            if (proximaFuncion != null) {
                if (proximaFuncion.isAfter(hoy)) {
                    estadoFecha = "🔮 Función futura el " + proximaFuncion;
                } else {
                    estadoFecha = "✔ Última función el " + proximaFuncion;
                }
            } else {
                estadoFecha = "Sin funciones registradas";
            }

            informe.append("🎬 ").append(p.getTitulo()).append("\n")
                    .append("   • Género: ").append(p.getGenero()).append("\n")
                    .append("   • Director: ").append(p.getDirector()).append("\n")
                    .append("   • País de origen: ").append(p.getOrigen()).append("\n")
                    .append("   • Estreno: ").append(p.getEstreno()).append("\n")
                    .append("   • Espectadores: ").append(espectadores).append("\n")
                    .append("   • ").append(estadoFecha).append("\n")
                    .append("────────────────────────────\n");
        }

        mostrarInforme(informe.toString());
    }//GEN-LAST:event_btnMasVistasActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser TicketDate;
    private javax.swing.JButton btnMasVistas;
    private javax.swing.JButton btnTicketPorFecha;
    private javax.swing.JButton btnTicketPorPelicula;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> jlistPeliculas;
    private javax.swing.JTable tblMasVistas;
    private javax.swing.JTextField txtPeliculasBuscador;
    // End of variables declaration//GEN-END:variables
}
