package vistas.Comprador;

import entidades.Comprador;
import java.awt.Window;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import persistencia.CompradorData;
import persistencia.Context;
import vistas.CinemaCentro;

public class CompradorView extends javax.swing.JPanel {

    private CompradorData compradorData = Context.getCompradorData();
    private DefaultTableModel modelo;

    public CompradorView() {
        initComponents();
        modelo = (DefaultTableModel) jtbleCompradores.getModel();
        inicializarComboPago();
        cargarTabla();
    }

    private void inicializarComboPago() {
        cbxFormaDepago.removeAllItems();
        cbxFormaDepago.addItem("Todos");
        cbxFormaDepago.addItem("Efectivo");
        cbxFormaDepago.addItem("Tarjeta");
        cbxFormaDepago.addItem("Transferencia");
    }

    private void cargarTabla() {
        modelo.setRowCount(0);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for (Comprador c : compradorData.obtenerTodosLosCompradores()) {
            modelo.addRow(new Object[]{
                c.getIdComprador(),
                c.getDni(),
                c.getNombre(),
                c.getFechaNac().format(fmt),
                c.getMedioPago()
            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnAgregar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtbleCompradores = new javax.swing.JTable();
        btnModificar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        cbxFormaDepago = new javax.swing.JComboBox<>();
        lblFormaDePago = new javax.swing.JLabel();
        txtfBuscarDni = new javax.swing.JTextField();
        lblDni = new javax.swing.JLabel();
        txtfBuscarNombre = new javax.swing.JTextField();
        lblNombre = new javax.swing.JLabel();

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

        jtbleCompradores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "DNI", "Nombre", "Fecha Nacimiento", "Medio de pago"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jtbleCompradores);

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

        cbxFormaDepago.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxFormaDepago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxFormaDepagoActionPerformed(evt);
            }
        });

        lblFormaDePago.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblFormaDePago.setForeground(new java.awt.Color(255, 255, 255));
        lblFormaDePago.setText("Forma de pago");

        lblDni.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDni.setForeground(new java.awt.Color(255, 255, 255));
        lblDni.setText("DNI");

        lblNombre.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblNombre.setForeground(new java.awt.Color(255, 255, 255));
        lblNombre.setText("Nombre");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 567, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblDni)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtfBuscarDni, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblNombre)
                        .addGap(18, 18, 18)
                        .addComponent(txtfBuscarNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblFormaDePago)
                        .addGap(18, 18, 18)
                        .addComponent(cbxFormaDepago, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                                .addComponent(lblNombre)
                                .addComponent(txtfBuscarNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblFormaDePago)
                                .addComponent(cbxFormaDepago, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblDni)
                                .addComponent(txtfBuscarDni, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
        abrirYCentrar(new AgregarComprador());
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
        int fila = jtbleCompradores.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un comprador para modificar.");
            return;
        }

        int idComprador = (int) modelo.getValueAt(fila, 0);
        Comprador comprador = compradorData.obtenerCompradorPorId(idComprador);

        if (comprador == null) {
            JOptionPane.showMessageDialog(this, "No se pudo obtener el comprador.");
            return;
        }

        abrirYCentrar(new ModificarComprador(comprador));

    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        String dniFiltro = txtfBuscarDni.getText().trim();
        String nombreFiltro = txtfBuscarNombre.getText().trim().toLowerCase();
        String formaPagoFiltro = cbxFormaDepago.getSelectedItem().toString();

        modelo.setRowCount(0);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        List<Comprador> todos = compradorData.obtenerTodosLosCompradores();
        List<Comprador> filtrados = new java.util.ArrayList<>();

        for (Comprador c : todos) {
            boolean coincide = true;

            if (!dniFiltro.isEmpty() && !c.getDni().equalsIgnoreCase(dniFiltro)) {
                coincide = false;
            }

            if (!nombreFiltro.isEmpty()
                    && (c.getNombre() == null || !c.getNombre().toLowerCase().contains(nombreFiltro))) {
                coincide = false;
            }

            if (!formaPagoFiltro.equalsIgnoreCase("Todos")
                    && (c.getMedioPago() == null || !c.getMedioPago().equalsIgnoreCase(formaPagoFiltro))) {
                coincide = false;
            }

            if (coincide) {
                filtrados.add(c);
            }
        }

        if (filtrados.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se encontraron compradores con esos filtros.");
            return;
        }

        for (Comprador c : filtrados) {
            modelo.addRow(new Object[]{
                c.getIdComprador(),
                c.getDni(),
                c.getNombre(),
                c.getFechaNac().format(fmt),
                c.getMedioPago()
            });
        }

    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int fila = jtbleCompradores.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un comprador para eliminar.");
            return;
        }

        int id = (int) modelo.getValueAt(fila, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "¿Eliminar el comprador seleccionado?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            compradorData.bajaComprador(id);
            cargarTabla();
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void cbxFormaDepagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxFormaDepagoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxFormaDepagoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JComboBox<String> cbxFormaDepago;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jtbleCompradores;
    private javax.swing.JLabel lblDni;
    private javax.swing.JLabel lblFormaDePago;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JTextField txtfBuscarDni;
    private javax.swing.JTextField txtfBuscarNombre;
    // End of variables declaration//GEN-END:variables
}
