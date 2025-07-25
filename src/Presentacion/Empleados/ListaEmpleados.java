/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Presentacion.Empleados;

import Entidades.Empleado;
import Logica.EmpleadoManager;
import Presentacion.Modelos.ModeloTablaEmpleado;
import Presentacion.Principal;
import java.awt.Component;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

/**
 *
 * @author Amir Altamirano
 */
public class ListaEmpleados extends javax.swing.JInternalFrame {
    private static ListaEmpleados instancia;
    
    private EmpleadoManager empleadoManager=new EmpleadoManager();
    private ModeloTablaEmpleado mte=new ModeloTablaEmpleado();
    public Empleado empleadoSelecionado=null;
    private Principal menu= Principal.getInstance();
    /**
     * Creates new form ListaEmpleados
     */
    public ListaEmpleados() {
        initComponents();
        CargarTabla();
    }
    
    public static ListaEmpleados getInstancia(){
        if(instancia==null || instancia.isClosed()){
            instancia=new ListaEmpleados();
        }
        return instancia;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        BTEliminar = new javax.swing.JButton();
        BTModificar = new javax.swing.JButton();

        setClosable(true);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        jTable1.setModel(this.mte);
        jScrollPane1.setViewportView(jTable1);

        BTEliminar.setText("Despedir");

        BTModificar.setText("Modificar");
        BTModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTModificarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 905, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BTEliminar)
                    .addComponent(BTModificar))
                .addGap(19, 19, 19))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(141, 141, 141)
                        .addComponent(BTModificar)
                        .addGap(36, 36, 36)
                        .addComponent(BTEliminar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BTModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTModificarActionPerformed
        if(jTable1.getSelectedRow()>=0){
            int fila = jTable1.getSelectedRow();
            empleadoSelecionado = mte.getEmpleadoEn(fila);
            menu.ModificarEmpleado(empleadoSelecionado);
        }else{
            JOptionPane.showMessageDialog(null, "Seleccione un empleado primero");
        }
        
    }//GEN-LAST:event_BTModificarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTEliminar;
    private javax.swing.JButton BTModificar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    public void CargarTabla(){
        try {
            mte.setListaEmpleado(empleadoManager.Listar());
            ajustarAnchoColumnas(jTable1);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "error al cargar tabla", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void ajustarAnchoColumnas(JTable tabla) {
        for (int col = 0; col < tabla.getColumnCount(); col++) {
            TableColumn columna = tabla.getColumnModel().getColumn(col);
            int anchoMax = 0;

            // Comprobar ancho del header
            TableCellRenderer headerRenderer = columna.getHeaderRenderer();
            if (headerRenderer == null) {
                headerRenderer = tabla.getTableHeader().getDefaultRenderer();
            }
            Component headerComp = headerRenderer.getTableCellRendererComponent(
                tabla, columna.getHeaderValue(), false, false, 0, col);
            anchoMax = headerComp.getPreferredSize().width;

            // Comprobar ancho de las celdas
            for (int row = 0; row < tabla.getRowCount(); row++) {
                TableCellRenderer cellRenderer = tabla.getCellRenderer(row, col);
                Component comp = tabla.prepareRenderer(cellRenderer, row, col);
                int anchoCelda = comp.getPreferredSize().width;
                anchoMax = Math.max(anchoMax, anchoCelda);
            }

            columna.setPreferredWidth(anchoMax + 10); // margen adicional
        }
    }
}
