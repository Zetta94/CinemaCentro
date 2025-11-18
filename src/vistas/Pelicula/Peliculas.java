package vistas.Pelicula;

import entidades.Pelicula;
import java.awt.Window;
import java.util.List;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import org.mariadb.jdbc.Connection;
import persistencia.Conexion;
import persistencia.Context;
import static persistencia.Context.getPeliculaData;
import persistencia.PeliculaData;
import vistas.CinemaCentro;
import listeners.RefreshListener;

/**
 *
 * @author Morbo
 */
public class Peliculas extends javax.swing.JPanel {

    /**
     * Creates new form Compradores
     */
    private PeliculaData peliculaData = Context.getPeliculaData();
    private RefreshListener listener;
    private DefaultTableModel modelo;
    private Connection connection;

    public Peliculas() {
        initComponents();
        System.out.println("peliculas");
        cargarComboGeneros();
        modelo = (DefaultTableModel) jtblePeliculas.getModel();
        cargarTabla();
    }

    private void cargarTabla() {

        //DefaultTableModel modelo = (DefaultTableModel) jtblePeliculas.getModel();
        modelo.setRowCount(0);
        for (Pelicula p : peliculaData.obtenerTodas()) {
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

        btnAgregar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtblePeliculas = new javax.swing.JTable();
        btnModificar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        cbxGenero = new javax.swing.JComboBox<>();
        cbxCartelera = new javax.swing.JComboBox<>();
        lblGenero = new javax.swing.JLabel();
        lblCartelera = new javax.swing.JLabel();
        txtfTitulo = new javax.swing.JTextField();
        lbltitulo = new javax.swing.JLabel();

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

        lbltitulo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbltitulo.setForeground(new java.awt.Color(255, 255, 255));
        lbltitulo.setText("Titulo:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 567, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbltitulo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtfTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblGenero)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxGenero, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(lblCartelera)
                        .addGap(18, 18, 18)
                        .addComponent(cbxCartelera, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbltitulo)
                            .addComponent(txtfTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblGenero)
                            .addComponent(cbxGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCartelera)
                            .addComponent(cbxCartelera, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        AgregarPeliculaOk agregarPeli = new AgregarPeliculaOk();
        agregarPeli.setRefreshListener(new RefreshListener() {
            @Override
            public void actualizarLista() {
                cargarTabla();
            }
        });
        abrirYCentrar(agregarPeli);

    }//GEN-LAST:event_btnAgregarActionPerformed

    private void abrirYCentrar(JInternalFrame frame) {
        JDesktopPane escritorio = null;

        java.awt.Container parent = this.getParent();
        while (parent != null) {
            if (parent instanceof JDesktopPane) {
                escritorio = (JDesktopPane) parent;
                break;
            }
            parent = parent.getParent();
        }

        if (escritorio == null) {
            JOptionPane.showMessageDialog(this,
                    "No se encontró el escritorio para abrir la ventana.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        escritorio.add(frame);
        frame.setVisible(true);
        int x = (escritorio.getWidth() - frame.getWidth()) / 2;
        int y = (escritorio.getHeight() - frame.getHeight()) / 2;
        frame.setLocation(x, y);
    }

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        int filaSeleccionada = jtblePeliculas.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una película para modificar.");
            return;
        }

        int idPelicula = (int) jtblePeliculas.getValueAt(filaSeleccionada, 0);

        Pelicula peliculaSeleccionada = peliculaData.obtenerPorId(idPelicula);

        if (peliculaSeleccionada == null) {
            JOptionPane.showMessageDialog(this, "No se pudo obtener la información de la película.");
            return;
        }

        ModificarPelicula modificarPelicula = new ModificarPelicula(peliculaSeleccionada);
        modificarPelicula.setPeliculasListener(new RefreshListener() {
            @Override
            public void actualizarLista() {
                cargarTabla();
            }
        });

        abrirYCentrar(modificarPelicula);

    }//GEN-LAST:event_btnModificarActionPerformed

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
        String[] generos = {"Todas", "Acción", "Animación", "Aventura", "Ciencia Ficción",
            "Comedia", "Documental", "Drama", "Fantasía",
            "Terror", "Romance", "Suspenso"
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
    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed

        int filaSeleccionada = jtblePeliculas.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione una película para eliminar.");
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(
                this,
                "¿Está seguro de eliminar esta película?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION
        );

        if (confirmacion == JOptionPane.YES_OPTION) {

            int idPelicula = (int) modelo.getValueAt(filaSeleccionada, 0);

            peliculaData.eliminarPelicula(idPelicula);

            modelo.removeRow(filaSeleccionada);
        }

    }//GEN-LAST:event_btnEliminarActionPerformed

    private void cbxGeneroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxGeneroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxGeneroActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
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
