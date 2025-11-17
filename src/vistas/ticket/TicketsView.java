package vistas.ticket;

import entidades.TicketCompra;
import entidades.Comprador;
import entidades.Pelicula;
import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import persistencia.Context;
import persistencia.TicketCompraData;
import persistencia.CompradorData;
import persistencia.PeliculaData;

public class TicketsView extends javax.swing.JPanel {

    private final TicketCompraData ticketData = Context.getTicketCompraData();
    private final CompradorData compradorData = Context.getCompradorData();
    private final PeliculaData peliculaData = Context.getPeliculaData();

    private List<Comprador> listaCompradores;

    public TicketsView() {
        initComponents();
        cargarCompradores();
        cargarPeliculas();
        mostrarTicketsEnTabla(ticketData.listarTodos());
        configurarAutoCompletar();
    }

    private void cargarPeliculas() {
        List<Pelicula> peliculas = peliculaData.obtenerTodas();
        cbxPelicula.removeAllItems();
        cbxPelicula.addItem("Todas");

        for (Pelicula p : peliculas) {
            cbxPelicula.addItem(p.getTitulo());
        }
    }

    private void cargarCompradores() {
        listaCompradores = compradorData.obtenerTodosLosCompradores();
        actualizarListaSugerencias(
                listaCompradores.stream()
                        .map(Comprador::getNombre)
                        .collect(Collectors.toList())
        );
    }

    private void actualizarListaSugerencias(List<String> nombres) {
        DefaultListModel<String> model = new DefaultListModel<>();
        for (String n : nombres) {
            model.addElement(n);
        }
        lstComprador.setModel(model);
    }

    private void configurarAutoCompletar() {

        txtComprador.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filtrarSugerencias();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filtrarSugerencias();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });

        lstComprador.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String seleccionado = lstComprador.getSelectedValue();
                if (seleccionado != null) {
                    txtComprador.setText(seleccionado);
                }
            }
        });
    }

    private void filtrarSugerencias() {
        String texto = txtComprador.getText().trim().toLowerCase();

        List<String> filtrados = listaCompradores.stream()
                .map(Comprador::getNombre)
                .filter(n -> n.toLowerCase().contains(texto))
                .collect(Collectors.toList());

        actualizarListaSugerencias(filtrados);
    }

    private void mostrarTicketsEnTabla(List<TicketCompra> tickets) {
        DefaultTableModel model = (DefaultTableModel) tblTickets.getModel();
        model.setRowCount(0);

        for (TicketCompra t : tickets) {
            Comprador comprador = compradorData.obtenerCompradorPorId(t.getIdComprador());
            String nombreComprador = (comprador != null) ? comprador.getNombre() : "Desconocido";

            model.addRow(new Object[]{
                t.getIdTicket(),
                t.getCodigoTicket(),
                t.getFechaCompra(),
                t.getFechaFuncion(),
                String.format("$%.2f", t.getMonto()),
                nombreComprador
            });
        }
    }

    private void buscarTickets() {
        String codigo = txtCodigo.getText().trim();
        String nombreComprador = txtComprador.getText().trim();
        String peliculaSel = cbxPelicula.getSelectedItem().toString();

        if (!codigo.isEmpty()) {
            TicketCompra ticket = ticketData.obtenerTicketPorCodigo(codigo);
            if (ticket != null) {
                mostrarTicketsEnTabla(java.util.Collections.singletonList(ticket));
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró ningún ticket con ese código.");
                ((DefaultTableModel) tblTickets.getModel()).setRowCount(0);
            }
            return;
        }

        if (!nombreComprador.isEmpty()) {
            List<TicketCompra> todos = ticketData.listarTodos();
            List<TicketCompra> filtrados = new ArrayList<>();

            for (TicketCompra t : todos) {
                Comprador c = compradorData.obtenerCompradorPorId(t.getIdComprador());
                if (c != null && c.getNombre().equalsIgnoreCase(nombreComprador)) {
                    filtrados.add(t);
                }
            }

            mostrarTicketsEnTabla(filtrados);
            return;
        }

        if (!"Todas".equals(peliculaSel)) {
            List<Pelicula> pelis = peliculaData.buscarPeliculas(peliculaSel, null, null);
            if (pelis == null || pelis.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No se encontró la película.");
                return;
            }
            int id = pelis.get(0).getIdPelicula();
            mostrarTicketsEnTabla(ticketData.listarTicketsPorPelicula(id));
            return;
        }

        mostrarTicketsEnTabla(ticketData.listarTodos());
    }

    private void abrirYCentrar(javax.swing.JInternalFrame frame) {
        java.awt.Window window = javax.swing.SwingUtilities.getWindowAncestor(this);
        if (window instanceof vistas.CinemaCentro) {
            vistas.CinemaCentro main = (vistas.CinemaCentro) window;
            javax.swing.JDesktopPane escritorio = main.getEscritorio();

            escritorio.add(frame);
            frame.pack();
            frame.setVisible(true);

            int x = (escritorio.getWidth() - frame.getWidth()) / 2;
            int y = (escritorio.getHeight() - frame.getHeight()) / 2;
            frame.setLocation(x, y);
            frame.toFront();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo acceder al escritorio principal.");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnAgregar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        srcProyecciones = new javax.swing.JScrollPane();
        tblTickets = new javax.swing.JTable();
        btnModificar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        lbl3d = new javax.swing.JLabel();
        lblSala = new javax.swing.JLabel();
        lblSubtitulada = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        cbxPelicula = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstComprador = new javax.swing.JList<>();
        txtComprador = new javax.swing.JTextField();

        setBackground(new java.awt.Color(33, 33, 33));

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

        tblTickets.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Código", "Compra", "Función", "Monto", "Comprador"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        srcProyecciones.setViewportView(tblTickets);

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

        lbl3d.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl3d.setForeground(new java.awt.Color(255, 255, 255));
        lbl3d.setText("Comprador:");

        lblSala.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblSala.setForeground(new java.awt.Color(255, 255, 255));
        lblSala.setText("Codigo");

        lblSubtitulada.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblSubtitulada.setForeground(new java.awt.Color(255, 255, 255));
        lblSubtitulada.setText("Pelicula");

        cbxPelicula.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todas", "Si", "No" }));

        lstComprador.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(lstComprador);

        txtComprador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCompradorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbl3d)
                        .addGap(125, 125, 125)
                        .addComponent(lblSala)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(lblSubtitulada, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxPelicula, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtComprador, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                            .addComponent(jScrollPane1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(srcProyecciones, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(28, 28, 28))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblSala)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSubtitulada)
                            .addComponent(cbxPelicula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl3d))
                        .addGap(11, 11, 11)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(33, 33, 33)
                            .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(34, 34, 34)
                            .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(srcProyecciones, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtComprador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(46, 46, 46))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int fila = tblTickets.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un ticket para eliminar.");
            return;
        }

        int idTicket = (int) tblTickets.getValueAt(fila, 0);
        int confirm = JOptionPane.showConfirmDialog(
                this,
                "¿Desea eliminar el ticket con ID " + idTicket + "?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            ticketData.eliminarTicket(idTicket);
            buscarTickets();
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        int fila = tblTickets.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un ticket para modificar.");
            return;
        }

        int idTicket = (int) tblTickets.getValueAt(fila, 0);
        TicketCompra ticket = ticketData.buscarTicketPorId(idTicket);

        if (ticket != null) {
            TicketEditarView editarView = new TicketEditarView(ticket);

            editarView.addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
                @Override
                public void internalFrameClosed(javax.swing.event.InternalFrameEvent e) {
                    buscarTickets();
                }
            });

            abrirYCentrar(editarView);
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        buscarTickets();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        JOptionPane.showMessageDialog(this, "Los tickets se generan automáticamente al realizar una compra.");
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void txtCompradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCompradorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCompradorActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JComboBox<String> cbxPelicula;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl3d;
    private javax.swing.JLabel lblSala;
    private javax.swing.JLabel lblSubtitulada;
    private javax.swing.JList<String> lstComprador;
    private javax.swing.JScrollPane srcProyecciones;
    private javax.swing.JTable tblTickets;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtComprador;
    // End of variables declaration//GEN-END:variables
}
