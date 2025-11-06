/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vistas.Cartelera;

import entidades.Pelicula;
import java.util.List;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import org.mariadb.jdbc.Connection;
import persistencia.Context;
import persistencia.PeliculaData;
import listeners.RefreshListener;
import vistas.CinemaCentro;
import vistas.DetallePelicula;

/**
 *
 * @author tomas
 */
public class PeliculasEnCartelera extends javax.swing.JPanel {

    private PeliculaData peliculaData = Context.getPeliculaData();
    private RefreshListener listener;
    private DefaultTableModel modelo;
    private Connection connection;

    public PeliculasEnCartelera() {
        initComponents();
        cargarComboGeneros();
        modelo = (DefaultTableModel) jtblePeliculas.getModel();
        cargarTabla();

        jtblePeliculas.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2 && javax.swing.SwingUtilities.isLeftMouseButton(e)) {
                    int fila = jtblePeliculas.rowAtPoint(e.getPoint());
                    if (fila >= 0) {
                        int idPelicula = (int) modelo.getValueAt(fila, 0);
                        Pelicula peliculaSeleccionada = peliculaData.obtenerPorId(idPelicula);
                        DetallePelicula detalle = new DetallePelicula(peliculaSeleccionada);
                        abrirYCentrar(detalle);
                    }
                }
            }
        });
    }

    private void cargarTabla() {

        //DefaultTableModel modelo = (DefaultTableModel) jtblePeliculas.getModel();
        modelo.setRowCount(0);
        for (Pelicula p : peliculaData.obtenerPeliculasEnCartelera()) {
            modelo.addRow(new Object[]{
                p.getIdPelicula(),
                p.getTitulo(),
                p.getDirector(),
                p.getActores(),
                p.getOrigen(),
                p.getGenero(),
                p.getEstreno(),
                p.isEnCartelera() ? "Si" : "No"
            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtfTitulo = new javax.swing.JTextField();
        lbltitulo = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtblePeliculas = new javax.swing.JTable();
        btnBuscar = new javax.swing.JButton();
        cbxGenero = new javax.swing.JComboBox<>();
        cbxCartelera = new javax.swing.JComboBox<>();
        lblGenero = new javax.swing.JLabel();
        lblCartelera = new javax.swing.JLabel();
        btnDetalles = new javax.swing.JButton();

        setBackground(new java.awt.Color(33, 33, 33));
        setForeground(new java.awt.Color(33, 33, 33));

        lbltitulo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbltitulo.setForeground(new java.awt.Color(255, 255, 255));
        lbltitulo.setText("Titulo:");

        jtblePeliculas.setForeground(new java.awt.Color(33, 33, 33));
        jtblePeliculas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Título", "Director", "Actores", "Origen", "Género", "Estreno", "En cartelera"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jtblePeliculas);

        btnBuscar.setBackground(new java.awt.Color(7, 10, 20));
        btnBuscar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Buscar.png"))); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.setToolTipText("");
        btnBuscar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnBuscar.setMaximumSize(new java.awt.Dimension(130, 76));
        btnBuscar.setMinimumSize(new java.awt.Dimension(130, 76));
        btnBuscar.setPreferredSize(new java.awt.Dimension(130, 76));
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        cbxGenero.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxGenero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxGeneroActionPerformed(evt);
            }
        });

        cbxCartelera.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todas", "Si", "No" }));

        lblGenero.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblGenero.setForeground(new java.awt.Color(255, 255, 255));
        lblGenero.setText("Genero:");

        lblCartelera.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblCartelera.setForeground(new java.awt.Color(255, 255, 255));
        lblCartelera.setText("Cartelera");

        btnDetalles.setBackground(new java.awt.Color(7, 10, 20));
        btnDetalles.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnDetalles.setForeground(new java.awt.Color(255, 255, 255));
        btnDetalles.setText("Detalles");
        btnDetalles.setToolTipText("");
        btnDetalles.setMaximumSize(new java.awt.Dimension(130, 76));
        btnDetalles.setMinimumSize(new java.awt.Dimension(130, 76));
        btnDetalles.setPreferredSize(new java.awt.Dimension(130, 76));
        btnDetalles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetallesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lbltitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtfTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblGenero)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(lblCartelera)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbxCartelera, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(98, 98, 98)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 830, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnDetalles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbltitulo)
                    .addComponent(txtfTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblGenero)
                    .addComponent(cbxGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCartelera)
                    .addComponent(cbxCartelera, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDetalles, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed

        try {
            String titulo = txtfTitulo.getText().trim();
            String genero = (String) cbxGenero.getSelectedItem();
            String carteleraSeleccion = (String) cbxCartelera.getSelectedItem();
            Integer enCartelera = null;

            if (genero.equals("Todas")) {
                genero = "";
            }

            if (carteleraSeleccion.equalsIgnoreCase("si")) {
                enCartelera = 1;
            } else if (carteleraSeleccion.equalsIgnoreCase("no")) {
                enCartelera = 0;
            }

            List<Pelicula> peliculasBuscadas = peliculaData.buscarPeliculas(titulo, genero, enCartelera);

            cargarResultados(peliculasBuscadas);

            if (peliculasBuscadas.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No se encontraron películas con esos filtros.");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al buscar películas: " + ex.getMessage());
        }

    }//GEN-LAST:event_btnBuscarActionPerformed

    private void cargarComboGeneros() {
        String[] generos = {
            "Todas",
            "Acción",
            "Animación",
            "Aventura",
            "Ciencia Ficción",
            "Comedia",
            "Comedia Romántica",
            "Documental",
            "Drama",
            "Fantasía",
            "Histórico",
            "Terror",
            "Romance",
            "Suspenso"
        };
        cbxGenero.setModel(new javax.swing.DefaultComboBoxModel<>(generos));
    }

    private void cargarResultados(List<Pelicula> resultados) {
        modelo.setRowCount(0);
        for (Pelicula p : resultados) {
            modelo.addRow(new Object[]{
                p.getIdPelicula(),
                p.getTitulo(),
                p.getDirector(),
                p.getActores(),
                p.getOrigen(),
                p.getGenero(),
                p.getEstreno(),
                p.isEnCartelera()
            });
        }
    }
    private void cbxGeneroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxGeneroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxGeneroActionPerformed

    private void btnDetallesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetallesActionPerformed
        // TODO add your handling code here:
        int fila = jtblePeliculas.getSelectedRow();
        if (fila >= 0) {
            int idPelicula = (int) modelo.getValueAt(fila, 0);
            Pelicula peliculaSeleccionada = peliculaData.obtenerPorId(idPelicula);
            DetallePelicula detalle = new DetallePelicula(peliculaSeleccionada);
            abrirYCentrar(detalle);
        }
    }//GEN-LAST:event_btnDetallesActionPerformed

    private void abrirYCentrar(JInternalFrame frame) {
        java.awt.Window window = SwingUtilities.getWindowAncestor(this);
        if (window instanceof CinemaCentro) {
            CinemaCentro main = (CinemaCentro) window;
            JDesktopPane escritorio = main.getEscritorio();
            escritorio.add(frame);
            frame.pack();
            frame.setVisible(true);
            int x = (escritorio.getWidth() - frame.getWidth()) / 2;
            int y = (escritorio.getHeight() - frame.getHeight()) / 2;
            frame.setLocation(x, y);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnDetalles;
    private javax.swing.JComboBox<String> cbxCartelera;
    private javax.swing.JComboBox<String> cbxGenero;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jtblePeliculas;
    private javax.swing.JLabel lblCartelera;
    private javax.swing.JLabel lblGenero;
    private javax.swing.JLabel lbltitulo;
    private javax.swing.JTextField txtfTitulo;
    // End of variables declaration//GEN-END:variables
}
