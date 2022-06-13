package Proyecto_progra;

import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class Ventana_MostrarEnfermeras extends javax.swing.JFrame {

    Hospital hospital;
    public Ventana_MostrarEnfermeras(Hospital hospital) {
        initComponents();
        this.hospital=hospital;
        addRowToJtable();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Disponibilidad", "Turno", "Codigo Enfermera"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 533, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    //Relleno de tabla por cada fila con informacion de Enfermera
    public void addRowToJtable(){
        ArrayList aux = hospital.mostrarListadoEnfermeras();
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        Object row[]=new Object[4];
        for (int i=0; i<aux.size();i++){
            String[] parts=((String)aux.get(i)).split(",");
            row[0]=(parts[0]);
            row[1]=(parts[1]);
            row[2]=parts[2];
            row[3]=(parts[3]);
            
            model.addRow(row);
            //setText(parts);
        }
        JTable table2=new JTable();
        table2.setModel(model);
    
    
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
