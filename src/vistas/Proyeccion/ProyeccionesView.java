package vistas.Proyeccion;

import vistas.Sala.AgregarSalas;
import vistas.Sala.ModificarSalas;
import entidades.Proyeccion;
import java.awt.Window;
import java.util.List;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import persistencia.Context;
import persistencia.SalaData;
import entidades.Sala;
import listeners.RefreshListener;
import persistencia.ProyeccionData;
import vistas.CinemaCentro;

public class ProyeccionesView extends javax.swing.JPanel {

    private ProyeccionData proyeccionData = Context.getProyeccionData();
    private SalaData salaData = Context.getSalaData();
    private DefaultTableModel modelo;

    public ProyeccionesView() {
        initComponents();
        modelo = (DefaultTableModel) tblProyecciones.getModel();
        cargarComboSalas();
        cargarTabla(proyeccionData.listarProyecciones());
    }

    private void cargarTabla(List<Proyeccion> lista) {
        modelo.setRowCount(0);
        for (Proyeccion p : lista) {
            modelo.addRow(new Object[]{
                p.getIdProyeccion(),
                p.getSala().getNroSala(),
                p.getPelicula().getTitulo(),
                p.getIdioma(),
                p.isSubtitulada() ? "Si" : "No",
                p.isEs3D() ? "Si" : "No",
                p.getFecha(),
                p.getHoraInicio(),
                p.getHoraFin(),
                p.getPrecio()
            });
        }
        limpiarBusqueda();
    }

    private void cargarComboSalas() {
        List<Sala> salas = salaData.listarSalas();
        Sala todas = new Sala();
        todas.setIdSala(0);
        cbxSala.addItem(todas);
        for (Sala s : salas) {
            cbxSala.addItem(s);
        }
    }

    private void cargarTablaFiltrada() {
        Sala sala = (Sala) cbxSala.getSelectedItem();
        Boolean subtitulada = null;
        Boolean es3d = null;

        if (cbx3d.getSelectedIndex() == 1) {
            es3d = true;
        } else if (cbx3d.getSelectedIndex() == 2) {
            es3d = false;
        }

        if (cbxSubtitulada.getSelectedIndex() == 1) {
            subtitulada = true;
        } else if (cbxSubtitulada.getSelectedIndex() == 2) {
            subtitulada = false;
        }

        List<Proyeccion> resultado = proyeccionData.buscarProyecciones(sala, subtitulada, es3d);

        cargarTabla(resultado);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnAgregar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        srcProyecciones = new javax.swing.JScrollPane();
        tblProyecciones = new javax.swing.JTable();
        btnModificar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        cbx3d = new javax.swing.JComboBox<>();
        lbl3d = new javax.swing.JLabel();
        lblSala = new javax.swing.JLabel();
        cbxSala = new javax.swing.JComboBox<>();
        lblSubtitulada = new javax.swing.JLabel();
        cbxSubtitulada = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(51, 51, 51));
        setForeground(new java.awt.Color(38, 64, 107));

        btnAgregar.setBackground(new java.awt.Color(7, 10, 20));
        btnAgregar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAgregar.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Agregar.png"))); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnEliminar.setBackground(new java.awt.Color(7, 10, 20));
        btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Eliminar.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        tblProyecciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Sala", "Pelicula", "Idioma", "Subtitulada", "3D", "Fecha", "Inicio", "Fin", "Precio"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        srcProyecciones.setViewportView(tblProyecciones);

        btnModificar.setBackground(new java.awt.Color(7, 10, 20));
        btnModificar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnModificar.setForeground(new java.awt.Color(255, 255, 255));
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Modificar.png"))); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

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

        cbx3d.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todas", "Si", "No" }));

        lbl3d.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl3d.setForeground(new java.awt.Color(255, 255, 255));
        lbl3d.setText("3D");

        lblSala.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblSala.setForeground(new java.awt.Color(255, 255, 255));
        lblSala.setText("Sala");

        lblSubtitulada.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblSubtitulada.setForeground(new java.awt.Color(255, 255, 255));
        lblSubtitulada.setText("Subtitulada");

        cbxSubtitulada.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todas", "Si", "No" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(srcProyecciones, javax.swing.GroupLayout.PREFERRED_SIZE, 567, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblSala)
                        .addGap(18, 18, 18)
                        .addComponent(cbxSala, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbl3d)
                        .addGap(18, 18, 18)
                        .addComponent(cbx3d, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74)
                        .addComponent(lblSubtitulada)
                        .addGap(18, 18, 18)
                        .addComponent(cbxSubtitulada, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblSubtitulada)
                                .addComponent(cbxSubtitulada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cbx3d, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbl3d))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblSala)
                                .addComponent(cbxSala, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(11, 11, 11)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(srcProyecciones, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        AgregarProyeccionOk agregar = new AgregarProyeccionOk();
        agregar.setRefreshListener(new RefreshListener() {
            @Override
            public void actualizarLista() {
                cargarTabla(proyeccionData.listarProyecciones());
            }
        });
        abrirYCentrar(agregar);
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void abrirYCentrar(JInternalFrame frame) {
        Window window = SwingUtilities.getWindowAncestor(this);
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

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        int fila = tblProyecciones.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una proyeccion para modificar.");
            return;
        }

        int idProyeccion = (int) tblProyecciones.getValueAt(fila, 0);
        Proyeccion p = proyeccionData.obtenerProyeccionPorId(idProyeccion);
        if (p == null) {
            JOptionPane.showMessageDialog(this, "No se pudo obtener la proyeccion.");
            return;
        }

        ModificarProyeccion modificar = new ModificarProyeccion(p);
        modificar.setRefreshListener(new RefreshListener() {
            @Override
            public void actualizarLista() {
                cargarTabla(proyeccionData.listarProyecciones());
            }
        });
        abrirYCentrar(modificar);

    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        cargarTablaFiltrada();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int fila = tblProyecciones.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una proyeccion para eliminar.");
            return;
        }

        int idProyeccion = (int) modelo.getValueAt(fila, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "¿Eliminar la proyeccion seleccionada?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            proyeccionData.eliminarProyeccion(idProyeccion);
            cargarTabla(proyeccionData.listarProyecciones());
            limpiarBusqueda();
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void limpiarBusqueda() {
        cbxSala.setSelectedIndex(0);
        cbx3d.setSelectedIndex(0);
        cbxSubtitulada.setSelectedIndex(0);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JComboBox<String> cbx3d;
    private javax.swing.JComboBox<Sala> cbxSala;
    private javax.swing.JComboBox<String> cbxSubtitulada;
    private javax.swing.JLabel lbl3d;
    private javax.swing.JLabel lblSala;
    private javax.swing.JLabel lblSubtitulada;
    private javax.swing.JScrollPane srcProyecciones;
    private javax.swing.JTable tblProyecciones;
    // End of variables declaration//GEN-END:variables
}
