package Presentacion;

import Presentacion.Compras.DettalesDeCompra;
import Presentacion.Compras.ListaCompras;
import Presentacion.Compras.EditarCompra;
import Entidades.Compra;
import Entidades.CompraDetalles;
import Entidades.Empleado;
import Presentacion.Empleados.DatosEmpleado;
import Presentacion.Ventas.DetallesDeVenta;
import Presentacion.Clientes.GestionClientes;
import Presentacion.Productos.GestionProducto;
import Presentacion.Proveedores.GestionProveedor;
import Presentacion.Ventas.ListaVentas;
import Presentacion.Ventas.RegistrarVenta;
import Presentacion.Reportes;
import Presentacion.Reportes;

import java.util.ArrayList;

import javax.swing.JInternalFrame;
import javax.swing.plaf.basic.BasicInternalFrameUI;

/**
 *
 * @author jheff
 */
public class Menu extends javax.swing.JFrame {
    private static Menu menu;
    private static Empleado empleado=new Empleado();
    /**
     * Creates new form Menu 
     */
    public Menu(Empleado empleado1) {
        initComponents();
        this.empleado=empleado1;
        menu=this;
    }
    public static Empleado getEmpleado(){
         return empleado;
    }
    
    public static  Menu getInstance(){
        if(menu==null ){
            menu=new Menu(empleado);
        }
        
        return menu;
    }
    
    private void mostrarSoloEste(JInternalFrame frame){
        for(JInternalFrame f : jpPanelMain.getAllFrames()){
            f.dispose();
        }

        frame.setBorder(null); // Elimina el borde
        /*BasicInternalFrameUI ui = (BasicInternalFrameUI) frame.getUI();
        ui.setNorthPane(null); // Oculta la barra superior*/


        jpPanelMain.add(frame);
        frame.setVisible(true);

        try {
            frame.setMaximum(true); // Este paso es CRUCIAL para ocupar todo el espacio
        } catch (java.beans.PropertyVetoException e) {
            e.printStackTrace();
        }
            frame.toFront();
    }

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btRegistroProveedores = new javax.swing.JButton();
        btReportes = new javax.swing.JButton();
        btAdministrarEmpleado1 = new javax.swing.JButton();
        btRegistroCliente1 = new javax.swing.JButton();
        btRegistroProducto1 = new javax.swing.JButton();
        btRegistroVenta = new javax.swing.JButton();
        btListaPedidos = new javax.swing.JButton();
        btListaCompras = new javax.swing.JButton();
        jpPanelMain = new javax.swing.JDesktopPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel4.setBackground(new java.awt.Color(153, 102, 255));

        jLabel1.setFont(new java.awt.Font("Roboto Condensed Light", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Importaciones Lady");
        jLabel1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        btRegistroProveedores.setBackground(new java.awt.Color(135, 130, 255));
        btRegistroProveedores.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        btRegistroProveedores.setForeground(new java.awt.Color(255, 255, 255));
        btRegistroProveedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/group_10613633.png"))); // NOI18N
        btRegistroProveedores.setText("Registro de proveedores");
        btRegistroProveedores.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 10, 0, 0, new java.awt.Color(0, 0, 0)));
        btRegistroProveedores.setBorderPainted(false);
        btRegistroProveedores.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btRegistroProveedores.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btRegistroProveedores.setMargin(new java.awt.Insets(12, 14, 3, 14));
        btRegistroProveedores.setPreferredSize(new java.awt.Dimension(75, 35));
        btRegistroProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRegistroProveedoresActionPerformed(evt);
            }
        });

        btReportes.setBackground(new java.awt.Color(135, 130, 255));
        btReportes.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        btReportes.setForeground(new java.awt.Color(255, 255, 255));
        btReportes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/clipboard-list_12066966 (1).png"))); // NOI18N
        btReportes.setText("Reportes");
        btReportes.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 10, 0, 0, new java.awt.Color(0, 0, 0)));
        btReportes.setBorderPainted(false);
        btReportes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btReportes.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btReportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btReportesActionPerformed(evt);
            }
        });

        btAdministrarEmpleado1.setBackground(new java.awt.Color(135, 130, 255));
        btAdministrarEmpleado1.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        btAdministrarEmpleado1.setForeground(new java.awt.Color(255, 255, 255));
        btAdministrarEmpleado1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/folder_12067190.png"))); // NOI18N
        btAdministrarEmpleado1.setText("Administrar Empleados");
        btAdministrarEmpleado1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 10, 0, 0, new java.awt.Color(0, 0, 0)));
        btAdministrarEmpleado1.setBorderPainted(false);
        btAdministrarEmpleado1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btAdministrarEmpleado1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btAdministrarEmpleado1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAdministrarEmpleado1ActionPerformed(evt);
            }
        });

        btRegistroCliente1.setBackground(new java.awt.Color(135, 130, 255));
        btRegistroCliente1.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        btRegistroCliente1.setForeground(new java.awt.Color(255, 255, 255));
        btRegistroCliente1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/group_10613635 (1).png"))); // NOI18N
        btRegistroCliente1.setText("Registro Cliente");
        btRegistroCliente1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 10, 0, 0, new java.awt.Color(0, 0, 0)));
        btRegistroCliente1.setBorderPainted(false);
        btRegistroCliente1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btRegistroCliente1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btRegistroCliente1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRegistroCliente1ActionPerformed(evt);
            }
        });

        btRegistroProducto1.setBackground(new java.awt.Color(135, 130, 255));
        btRegistroProducto1.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        btRegistroProducto1.setForeground(new java.awt.Color(255, 255, 255));
        btRegistroProducto1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/edit_10613619.png"))); // NOI18N
        btRegistroProducto1.setText("Registro de productos");
        btRegistroProducto1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 10, 0, 0, new java.awt.Color(0, 0, 0)));
        btRegistroProducto1.setBorderPainted(false);
        btRegistroProducto1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btRegistroProducto1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btRegistroProducto1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRegistroProducto1ActionPerformed(evt);
            }
        });

        btRegistroVenta.setBackground(new java.awt.Color(135, 130, 255));
        btRegistroVenta.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        btRegistroVenta.setForeground(new java.awt.Color(255, 255, 255));
        btRegistroVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/clipboard-list_12066966 (1).png"))); // NOI18N
        btRegistroVenta.setText("Registrar Venta");
        btRegistroVenta.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 10, 0, 0, new java.awt.Color(0, 0, 0)));
        btRegistroVenta.setBorderPainted(false);
        btRegistroVenta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btRegistroVenta.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btRegistroVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRegistroVentaActionPerformed(evt);
            }
        });

        btListaPedidos.setBackground(new java.awt.Color(135, 130, 255));
        btListaPedidos.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        btListaPedidos.setForeground(new java.awt.Color(255, 255, 255));
        btListaPedidos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/edit_10613619.png"))); // NOI18N
        btListaPedidos.setText("Lista de ventas");
        btListaPedidos.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 10, 0, 0, new java.awt.Color(0, 0, 0)));
        btListaPedidos.setBorderPainted(false);
        btListaPedidos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btListaPedidos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btListaPedidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btListaPedidosActionPerformed(evt);
            }
        });

        btListaCompras.setBackground(new java.awt.Color(135, 130, 255));
        btListaCompras.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        btListaCompras.setForeground(new java.awt.Color(255, 255, 255));
        btListaCompras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/edit_10613619.png"))); // NOI18N
        btListaCompras.setText("Lista de Compras");
        btListaCompras.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 10, 0, 0, new java.awt.Color(0, 0, 0)));
        btListaCompras.setBorderPainted(false);
        btListaCompras.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btListaCompras.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btListaCompras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btListaComprasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(56, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btListaPedidos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btRegistroProducto1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btRegistroCliente1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btAdministrarEmpleado1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                    .addComponent(btReportes, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btRegistroVenta, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)

                    .addComponent(btRegistroProveedores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btListaCompras, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))

                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addComponent(btRegistroProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(btRegistroProducto1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btRegistroCliente1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(btAdministrarEmpleado1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(btReportes, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btRegistroVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btListaPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)

                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btListaCompras, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(93, Short.MAX_VALUE))

        );

        javax.swing.GroupLayout jpPanelMainLayout = new javax.swing.GroupLayout(jpPanelMain);
        jpPanelMain.setLayout(jpPanelMainLayout);
        jpPanelMainLayout.setHorizontalGroup(
            jpPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)

            .addGap(0, 698, Short.MAX_VALUE)

        );
        jpPanelMainLayout.setVerticalGroup(
            jpPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpPanelMain))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpPanelMain, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btRegistroProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRegistroProveedoresActionPerformed

         mostrarSoloEste(GestionProveedor.getProveedor());                              

        // TODO add your handling code here:
    }//GEN-LAST:event_btRegistroProveedoresActionPerformed
                                                  

    private void btAdministrarEmpleado1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAdministrarEmpleado1ActionPerformed
     mostrarSoloEste(DatosEmpleado.getEmpleados());
     DatosEmpleado.getEmpleados().setEmpleadoActual(empleado);
     DatosEmpleado.getEmpleados().CargarDatos();

    }//GEN-LAST:event_btAdministrarEmpleado1ActionPerformed
     
    private void btRegistroCliente1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRegistroCliente1ActionPerformed

    mostrarSoloEste(GestionClientes.getCliente());
    }//GEN-LAST:event_btRegistroCliente1ActionPerformed

 
    private void btRegistroProducto1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRegistroProducto1ActionPerformed
  mostrarSoloEste(GestionProducto.getProducto()); 
    }//GEN-LAST:event_btRegistroProducto1ActionPerformed

    private void btReportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btReportesActionPerformed
 mostrarSoloEste(Reportes_Lady.getReportes());   
    }//GEN-LAST:event_btReportesActionPerformed

    private void btRegistroVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRegistroVentaActionPerformed
  mostrarSoloEste(RegistrarVenta.getR_ventas());
    }//GEN-LAST:event_btRegistroVentaActionPerformed

    private void btListaPedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btListaPedidosActionPerformed
   mostrarSoloEste(ListaVentas.getVentanaListaVentas());

    }//GEN-LAST:event_btListaPedidosActionPerformed

    private void btListaComprasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btListaComprasActionPerformed
         mostrarSoloEste(ListaCompras.getListaCompra());
    }//GEN-LAST:event_btListaComprasActionPerformed

    public void detallesVenta(int id){

        DetallesDeVenta VentaDetalles = new DetallesDeVenta(id);
        jpPanelMain.add(VentaDetalles);
        VentaDetalles.setVisible(true);
    
    }
    
    public void detallesCompras(Compra compra, ArrayList<CompraDetalles>  detalles){
       DettalesDeCompra dettalesDeCompra=new DettalesDeCompra(compra, detalles);
       jpPanelMain.add(dettalesDeCompra);
       dettalesDeCompra.setVisible(true);
    }
    
    public void EditarCompra(Compra compra, ArrayList<CompraDetalles>  detalles){
        EditarCompra editarCompra=new EditarCompra(detalles, compra);
        jpPanelMain.add(editarCompra);
        editarCompra.setVisible(true);

    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu(empleado).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAdministrarEmpleado1;
    private javax.swing.JButton btListaCompras;
    private javax.swing.JButton btListaPedidos;
    private javax.swing.JButton btRegistroCliente1;
    private javax.swing.JButton btRegistroProducto1;
    private javax.swing.JButton btRegistroProveedores;
    private javax.swing.JButton btRegistroVenta;
    private javax.swing.JButton btReportes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JDesktopPane jpPanelMain;
    // End of variables declaration//GEN-END:variables
}