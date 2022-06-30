package Ventanas;

import Proyecto_progra.Hospital;


public class Ventana_MenuDisponibilidad extends javax.swing.JFrame {
    Hospital hospital;
    
    public Ventana_MenuDisponibilidad(Hospital hospital) {
        initComponents();
        this.hospital= hospital;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        Turno = new javax.swing.JButton();
        Disponibilidad = new javax.swing.JButton();
        TurnoDisponibilidad = new javax.swing.JButton();
        Volver = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Seleccione el cambio que desea hacer:");

        Turno.setText("Modificar Turno");
        Turno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TurnoActionPerformed(evt);
            }
        });

        Disponibilidad.setText("Modificar Disponibilidad");
        Disponibilidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DisponibilidadActionPerformed(evt);
            }
        });

        TurnoDisponibilidad.setText("Modificar Turno y Disponibilidad");
        TurnoDisponibilidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TurnoDisponibilidadActionPerformed(evt);
            }
        });

        Volver.setText("Volver");
        Volver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VolverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(135, 135, 135)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(116, 116, 116)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Turno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Disponibilidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(TurnoDisponibilidad, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(Volver, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(126, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Turno)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Disponibilidad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TurnoDisponibilidad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                .addComponent(Volver)
                .addGap(40, 40, 40))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Llamada de boton a ventana de modificar turno
    private void TurnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TurnoActionPerformed
       java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana_ModificarTurno(hospital).setVisible(true);
            }
        });
    
    }//GEN-LAST:event_TurnoActionPerformed
    
    //Llamada de boton a ventana de modificar disponibilidad
    private void DisponibilidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DisponibilidadActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana_ModificarDisponibilidad(hospital).setVisible(true);
            }
        });
    }//GEN-LAST:event_DisponibilidadActionPerformed

    //Llamada de boton a ventana de modificar disponibilidad
    private void TurnoDisponibilidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TurnoDisponibilidadActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana_ModificarTurnoDisponibilidad(hospital).setVisible(true);
            }
        });
    }//GEN-LAST:event_TurnoDisponibilidadActionPerformed
    
    //Llamada de boton a cerrar la ventana
    private void VolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VolverActionPerformed
        super.dispose();
        
    }//GEN-LAST:event_VolverActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Disponibilidad;
    private javax.swing.JButton Turno;
    private javax.swing.JButton TurnoDisponibilidad;
    private javax.swing.JButton Volver;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
