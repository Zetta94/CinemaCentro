package vistas.Pelicula;

import entidades.Pelicula;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.ZoneId;
import javax.swing.JOptionPane;
import persistencia.Context;
import persistencia.PeliculaData;
import listeners.RefreshListener;

public class ModificarPelicula extends javax.swing.JInternalFrame {

    private Pelicula pelicula;
    private PeliculaData peliculaData = Context.getPeliculaData();
    private RefreshListener listener;

    public ModificarPelicula(Pelicula pelicula) {
        initComponents();
        this.pelicula = pelicula;
        cargarComboGeneros();
        cargarDatos();
    }

    public void setPeliculasListener(RefreshListener listener) {
        this.listener = listener;
    }

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

      private void cargarDatos() {
        txtTitulo.setText(pelicula.getTitulo());
        txtDirector.setText(pelicula.getDirector());
        txtActores.setText(pelicula.getActores());
        txtOrigen.setText(pelicula.getOrigen());
        cbxGenero.setSelectedItem(pelicula.getGenero());
        
        if (pelicula.getEstreno() != null) {
            java.util.Date date = java.sql.Date.valueOf(pelicula.getEstreno());
            dateEstreno.setDate(date);
        }
        
        if (pelicula.isEnCartelera()) {
            bSi.setSelected(true);
        } else {
            bNo.setSelected(true);
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        botones = new javax.swing.ButtonGroup();
        panel1 = new java.awt.Panel();
        lblDirector = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();
        lblActores = new javax.swing.JLabel();
        cbxGenero = new javax.swing.JComboBox<>();
        txtTitulo = new javax.swing.JTextField();
        btnSalir = new javax.swing.JButton();
        lblOrigen = new javax.swing.JLabel();
        dateEstreno = new com.toedter.calendar.JDateChooser();
        lblGenero = new javax.swing.JLabel();
        btnGurardar = new javax.swing.JButton();
        lblEstreno = new javax.swing.JLabel();
        txtDirector = new javax.swing.JTextField();
        lblCartelera = new javax.swing.JLabel();
        txtActores = new javax.swing.JTextField();
        bSi = new javax.swing.JRadioButton();
        bNo = new javax.swing.JRadioButton();
        txtOrigen = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        panel1.setBackground(new java.awt.Color(51, 51, 51));
        panel1.setForeground(new java.awt.Color(38, 64, 107));
        panel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblDirector.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDirector.setForeground(new java.awt.Color(255, 204, 0));
        lblDirector.setText("Director:");
        panel1.add(lblDirector, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 190, -1, 20));

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 204, 0));
        lblTitulo.setText("Titulo:");
        panel1.add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 150, -1, 20));

        lblActores.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblActores.setForeground(new java.awt.Color(255, 204, 0));
        lblActores.setText("Actores:");
        panel1.add(lblActores, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 230, -1, 20));

        cbxGenero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxGeneroActionPerformed(evt);
            }
        });
        panel1.add(cbxGenero, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 310, 170, 30));

        txtTitulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTituloActionPerformed(evt);
            }
        });
        panel1.add(txtTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 150, 170, 30));

        btnSalir.setBackground(new java.awt.Color(102, 102, 102));
        btnSalir.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        panel1.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 450, 100, 30));

        lblOrigen.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblOrigen.setForeground(new java.awt.Color(255, 204, 0));
        lblOrigen.setText("Origen:");
        panel1.add(lblOrigen, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 270, -1, 20));
        panel1.add(dateEstreno, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 350, 170, 30));

        lblGenero.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblGenero.setForeground(new java.awt.Color(255, 204, 0));
        lblGenero.setText("Genero:");
        panel1.add(lblGenero, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 310, -1, 20));

        btnGurardar.setBackground(new java.awt.Color(102, 0, 0));
        btnGurardar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnGurardar.setText("Guardar");
        btnGurardar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnGurardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGurardarActionPerformed(evt);
            }
        });
        panel1.add(btnGurardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 450, 100, 30));

        lblEstreno.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblEstreno.setForeground(new java.awt.Color(255, 204, 0));
        lblEstreno.setText("Estreno:");
        panel1.add(lblEstreno, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 350, -1, 20));

        txtDirector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDirectorActionPerformed(evt);
            }
        });
        panel1.add(txtDirector, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 190, 170, 30));

        lblCartelera.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblCartelera.setForeground(new java.awt.Color(255, 204, 0));
        lblCartelera.setText("En Cartelera:");
        panel1.add(lblCartelera, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 390, -1, 20));
        panel1.add(txtActores, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 230, 170, 30));

        botones.add(bSi);
        bSi.setForeground(new java.awt.Color(255, 204, 51));
        bSi.setText("Si");
        bSi.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        bSi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSiActionPerformed(evt);
            }
        });
        panel1.add(bSi, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 390, -1, 20));

        botones.add(bNo);
        bNo.setForeground(new java.awt.Color(255, 204, 51));
        bNo.setText("No");
        bNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bNoActionPerformed(evt);
            }
        });
        panel1.add(bNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 390, -1, 20));
        panel1.add(txtOrigen, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 270, 170, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ModificarPeli.png"))); // NOI18N
        panel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, -50, 420, 280));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbxGeneroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxGeneroActionPerformed

    }//GEN-LAST:event_cbxGeneroActionPerformed

    private void txtTituloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTituloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTituloActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose(); 
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnGurardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGurardarActionPerformed
    
            pelicula.setTitulo(txtTitulo.getText());
            pelicula.setDirector(txtDirector.getText());
            pelicula.setActores(txtActores.getText());
            pelicula.setOrigen(txtOrigen.getText());
            pelicula.setGenero(cbxGenero.getSelectedItem().toString());

            if (dateEstreno.getDate() != null) {
                LocalDate fecha = dateEstreno.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                pelicula.setEstreno(fecha);
            }

            pelicula.setEnCartelera(bSi.isSelected());

            boolean actualizado = peliculaData.editarPelicula(pelicula, pelicula.getIdPelicula());

            if (actualizado) {
                JOptionPane.showMessageDialog(this, "Película actualizada correctamente.");
                if (listener != null) {
                    listener.actualizarLista();
                }
                dispose();
            } 
        
        
    

    }//GEN-LAST:event_btnGurardarActionPerformed

    private void bSiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bSiActionPerformed

    private void bNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bNoActionPerformed

    private void txtDirectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDirectorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDirectorActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton bNo;
    private javax.swing.JRadioButton bSi;
    private javax.swing.ButtonGroup botones;
    private javax.swing.JButton btnGurardar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox<String> cbxGenero;
    private com.toedter.calendar.JDateChooser dateEstreno;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblActores;
    private javax.swing.JLabel lblCartelera;
    private javax.swing.JLabel lblDirector;
    private javax.swing.JLabel lblEstreno;
    private javax.swing.JLabel lblGenero;
    private javax.swing.JLabel lblOrigen;
    private javax.swing.JLabel lblTitulo;
    private java.awt.Panel panel1;
    private javax.swing.JTextField txtActores;
    private javax.swing.JTextField txtDirector;
    private javax.swing.JTextField txtOrigen;
    private javax.swing.JTextField txtTitulo;
    // End of variables declaration//GEN-END:variables
}
