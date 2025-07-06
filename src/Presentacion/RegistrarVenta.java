/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Presentacion;

import Entidades.Venta;
import Entidades.VentaDetalles;
import Entidades.Cliente;
import Entidades.Producto;
import Logica.VentasManager;
import java.awt.Color;
import java.awt.IllegalComponentStateException;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JWindow;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import Logica.ClienteManager;
import Logica.ProductoManager;
import java.awt.Image;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import Presentacion.Modelos.ModeloTablaVentaDetalle;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;


/**
 *
 * @author Amir Altamirano
 */
public class RegistrarVenta extends javax.swing.JInternalFrame {
    private static RegistrarVenta r_ventas;
    private VentasManager ventasManager=new VentasManager();
    private ProductoManager pm=new  ProductoManager();
    private ClienteManager cm=new ClienteManager();
    private ModeloTablaVentaDetalle mtvd= new ModeloTablaVentaDetalle();
    
    
    private JList<String> listaSugerencias = new JList<>();
    private JScrollPane scrollPaneSugerencias = new JScrollPane(listaSugerencias);
    private JWindow ventanaSugerencias = new JWindow();
    private List<Producto> listaProductos = new ArrayList<>();
    private Producto productoSeleccionado = null;

    
    
    private JList<String> listaSugerenciasCliente = new JList<>();
    private JScrollPane scrollPaneSugerenciasCliente = new JScrollPane(listaSugerenciasCliente);
    private JWindow ventanaSugerenciasCliente = new JWindow();
    private List<Cliente> listaClientes = new ArrayList<>();
    private Cliente clienteSeleccionado=null;
    
    
    private ArrayList<VentaDetalles> ListaDetalles=new ArrayList<>();

    /**
     * Creates new form RegistrarVenta
     */
    public RegistrarVenta() {
        initComponents();
        
        BasicInternalFrameUI ui = (BasicInternalFrameUI)this.getUI();
        ui.setNorthPane(null);
        this.setBorder(null);
        
        // Esto debe ir después de initComponents()
        ventanaSugerencias.setFocusableWindowState(false);
        scrollPaneSugerencias.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        ventanaSugerencias.add(scrollPaneSugerencias);

        // Listener de cambios en el texto
        txtProducto.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { actualizarSugerencias(); }
            public void removeUpdate(DocumentEvent e) { actualizarSugerencias(); }
            public void changedUpdate(DocumentEvent e) {}

            private void actualizarSugerencias() {
                String texto = txtProducto.getText().toLowerCase();
                if (texto.isEmpty()) {
                    ventanaSugerencias.setVisible(false);
                    return;
                }

                List<String> sugerencias = new ArrayList<>();
                for (Producto prod : listaProductos) {
                    if (prod.getNombre().toLowerCase().startsWith(texto)) {
                        sugerencias.add(prod.getNombre());
                    }
                }


                if (sugerencias.isEmpty()) {
                    ventanaSugerencias.setVisible(false);
                    return;
                }

                listaSugerencias.setListData(sugerencias.toArray(new String[0]));
                listaSugerencias.setSelectedIndex(0);

                try {
                    Point location = txtProducto.getLocationOnScreen();
                    ventanaSugerencias.setLocation(location.x, location.y + txtProducto.getHeight());
                    ventanaSugerencias.setSize(txtProducto.getWidth(), Math.min(100, sugerencias.size() * 20));
                    ventanaSugerencias.setVisible(true);
                } catch (IllegalComponentStateException ex) {
                    ventanaSugerencias.setVisible(false);
                }
            }
        });

        listaSugerencias.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                seleccionarSugerencia();
            }
        });
        
        
        //Lista de sugerencias de cliente
        
        // Configurar ventana de sugerencias para CLIENTES
        ventanaSugerenciasCliente.setFocusableWindowState(false);
        scrollPaneSugerenciasCliente.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        ventanaSugerenciasCliente.add(scrollPaneSugerenciasCliente);

        // Listener para txtCliente
        txtCliente.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { actualizarSugerenciasCliente(); }
            public void removeUpdate(DocumentEvent e) { actualizarSugerenciasCliente(); }
            public void changedUpdate(DocumentEvent e) {}

            private void actualizarSugerenciasCliente() {
                String texto = txtCliente.getText().toLowerCase();
                if (texto.isEmpty()) {
                    ventanaSugerenciasCliente.setVisible(false);
                    return;
                }

                List<String> sugerencias = new ArrayList<>();
                for (Cliente c : listaClientes) {
                    if (c.getNombre().toLowerCase().startsWith(texto)) {
                        sugerencias.add(c.getNombre());
                    }
                }


                if (sugerencias.isEmpty()) {
                    ventanaSugerenciasCliente.setVisible(false);
                    return;
                }

                listaSugerenciasCliente.setListData(sugerencias.toArray(new String[0]));
                listaSugerenciasCliente.setSelectedIndex(0);

                try {
                    Point location = txtCliente.getLocationOnScreen();
                    ventanaSugerenciasCliente.setLocation(location.x, location.y + txtCliente.getHeight());
                    ventanaSugerenciasCliente.setSize(txtCliente.getWidth(), Math.min(100, sugerencias.size() * 20));
                    ventanaSugerenciasCliente.setVisible(true);
                } catch (IllegalComponentStateException ex) {
                    ventanaSugerenciasCliente.setVisible(false);
                }
            }
        });
        
        listaSugerenciasCliente.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                seleccionarSugerenciaCliente();
            }
        });
        
        try {
            setListaClientes(cm.listarClientesActivos());
        } catch (Exception ex) {
            Logger.getLogger(RegistrarVenta.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            setListaProductos(pm.cargarProductosActivos());
        } catch (Exception ex) {
            Logger.getLogger(RegistrarVenta.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }
    
    
    public static RegistrarVenta getR_ventas(){
     if(r_ventas == null || r_ventas.isClosed()){
     r_ventas = new RegistrarVenta();
     }
     return r_ventas;
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
        jLabel2 = new javax.swing.JLabel();
        txtProducto = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        LBImagenProducto = new javax.swing.JLabel();
        SPCantidad = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        txtCliente = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        textTotal = new javax.swing.JTextField();
        BTEliminar = new javax.swing.JButton();
        BTCambiar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        BTNuevoProducto = new javax.swing.JButton();
        BTAgregar = new javax.swing.JButton();
        BTNuevoCliente = new javax.swing.JButton();
        Cancelar = new javax.swing.JButton();
        BTRegistrar = new javax.swing.JButton();

        setClosable(true);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Cantidad");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Nombre:");

        LBImagenProducto.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        LBImagenProducto.setForeground(new java.awt.Color(255, 255, 255));
        LBImagenProducto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LBImagenProducto.setText("Imagen");
        LBImagenProducto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        LBImagenProducto.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        LBImagenProducto.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        LBImagenProducto.setRequestFocusEnabled(false);
        LBImagenProducto.setVerifyInputWhenFocusTarget(false);

        SPCantidad.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Cliente");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Total");

        BTEliminar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BTEliminar.setText("-");
        BTEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTEliminarActionPerformed(evt);
            }
        });

        BTCambiar.setText("✍");

        jTable1.setModel(this.mtvd);
        jScrollPane1.setViewportView(jTable1);

        BTNuevoProducto.setText("+");

        BTAgregar.setBackground(new java.awt.Color(153, 153, 255));
        BTAgregar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BTAgregar.setForeground(new java.awt.Color(255, 255, 255));
        BTAgregar.setText("Agregar");
        BTAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTAgregarActionPerformed(evt);
            }
        });

        BTNuevoCliente.setText("+");

        Cancelar.setText("Cancelar");
        Cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelarActionPerformed(evt);
            }
        });

        BTRegistrar.setBackground(new java.awt.Color(153, 153, 255));
        BTRegistrar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BTRegistrar.setForeground(new java.awt.Color(255, 255, 255));
        BTRegistrar.setText("Registrar");
        BTRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTRegistrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LBImagenProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BTNuevoProducto))
                    .addComponent(jLabel2)
                    .addComponent(SPCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BTAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(157, 157, 157)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BTNuevoCliente)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(115, 115, 115)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 542, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(BTCambiar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BTEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(99, 99, 99)
                        .addComponent(BTRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(Cancelar)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Cancelar)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BTNuevoCliente))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(BTEliminar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(BTCambiar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(LBImagenProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addGap(3, 3, 3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BTNuevoProducto))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SPCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(BTAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(textTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4))
                    .addComponent(BTRegistrar))
                .addGap(24, 24, 24))
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

    private void BTAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTAgregarActionPerformed
        if(productoSeleccionado==null){
            seleccionarSugerencia();
        }
        
        if(productoSeleccionado != null){
            if((int) SPCantidad.getValue()!=0){
                VentaDetalles detalle=new VentaDetalles();
              detalle.setProducto(productoSeleccionado);
              detalle.setCantidad((int) SPCantidad.getValue());
              float precioTotal=detalle.getCantidad()*detalle.getProducto().getPrecioVenta();
              detalle.setPrecioTotal(precioTotal);

              ListaDetalles.add(detalle); 
              float Total=ventasManager.CalcularTotal(ListaDetalles);
              textTotal.setText( String.valueOf(Total));  
              limpiarAgregarProducto();
            }
            CargarTabla();
        }
        
        
    }//GEN-LAST:event_BTAgregarActionPerformed

    private void BTEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTEliminarActionPerformed
        int id=jTable1.getSelectedRow();
        ListaDetalles.remove(id);
        CargarTabla();
    }//GEN-LAST:event_BTEliminarActionPerformed

    private void BTRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTRegistrarActionPerformed
        if(clienteSeleccionado==null){
            seleccionarSugerenciaCliente();
        }
        if(clienteSeleccionado!=null){
            if(!ListaDetalles.isEmpty()){
               Venta venta=new Venta();
               venta.setCliente(clienteSeleccionado); 
               venta.setEmpleado(Menu.getEmpleado());
               venta.setTotal(ventasManager.CalcularTotal(ListaDetalles));
                try {
                    boolean exito = ventasManager.RegistrarVenta(venta, ListaDetalles);
                    if (exito) {
                        JOptionPane.showMessageDialog(null, "Venta registrada correctamente.");
                        limpiarFormulario(); // Puedes crear un método para limpiar todo
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo registrar la venta.");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }else{
                JOptionPane.showMessageDialog(null, "tines que registrar productos");
            }
        }else {
            JOptionPane.showMessageDialog(null, "Debes seleccionar un cliente válido.");
        }
    }//GEN-LAST:event_BTRegistrarActionPerformed

    private void CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelarActionPerformed
       limpiarFormulario();
    }//GEN-LAST:event_CancelarActionPerformed

    private void seleccionarSugerencia() {
        String nombreSeleccionado = listaSugerencias.getSelectedValue();
        if (nombreSeleccionado != null) {
            txtProducto.setText(nombreSeleccionado);
            ventanaSugerencias.setVisible(false);


            for (Producto p : listaProductos) {
                if (p.getNombre().equals(nombreSeleccionado)) {
                    productoSeleccionado = p;
                    mostrarImagenProducto(p.getUrl());
                    break;
                }
            }
        }
    }
    
    private void mostrarImagenProducto(String url) {
        if (url != null && !url.isEmpty()) {
            try {
                ImageIcon icono = new ImageIcon(url);
                Image imagen = icono.getImage().getScaledInstance(
                    LBImagenProducto.getWidth(),
                    LBImagenProducto.getHeight(),
                    Image.SCALE_SMOOTH
                );
                LBImagenProducto.setIcon(new ImageIcon(imagen));
            } catch (Exception e) {
                System.err.println("No se pudo cargar la imagen: " + e.getMessage());
                LBImagenProducto.setIcon(null);
            }
        } else {
            LBImagenProducto.setIcon(null);
        }
    }


    
    
    private void seleccionarSugerenciaCliente() {
        String seleccionado = listaSugerenciasCliente.getSelectedValue();
        if (seleccionado != null) {
            txtCliente.setText(seleccionado);
            ventanaSugerenciasCliente.setVisible(false);
            
            for (Cliente c : listaClientes) {
                if (c.getNombre().equals(seleccionado)) {
                    clienteSeleccionado = c;
                    break;
                }
            }
        }
    }

    public void setListaProductos(List<Producto> productos) {
        this.listaProductos = productos;
    }

    public void setListaClientes(List<Cliente> clientes) {
        this.listaClientes = clientes;
    }




    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTAgregar;
    private javax.swing.JButton BTCambiar;
    private javax.swing.JButton BTEliminar;
    private javax.swing.JButton BTNuevoCliente;
    private javax.swing.JButton BTNuevoProducto;
    private javax.swing.JButton BTRegistrar;
    private javax.swing.JButton Cancelar;
    private javax.swing.JLabel LBImagenProducto;
    private javax.swing.JSpinner SPCantidad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField textTotal;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtProducto;
    // End of variables declaration//GEN-END:variables
    public void CargarTabla(){
        mtvd.setListaDetalle(ListaDetalles);
    }
    
    public void limpiarAgregarProducto() {
        txtProducto.setText("");                       
        SPCantidad.setValue(0);                        
        productoSeleccionado = null;                   
        LBImagenProducto.setIcon(null);                
        ventanaSugerencias.setVisible(false);         
    }

    private void limpiarFormulario() {
        
        txtCliente.setText("");
        clienteSeleccionado=null;
        textTotal.setText("");
        ListaDetalles.clear();
        limpiarAgregarProducto();
        CargarTabla();
    }

}
