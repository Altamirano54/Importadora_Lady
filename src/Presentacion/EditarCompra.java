/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Presentacion;

import Entidades.CompraDetalles;
import Entidades.Producto;
import Entidades.Proveedor;
import Logica.ComprasManager;
import Logica.ProductoManager;
import Logica.ProveeedorManager;
import java.awt.Color;
import java.awt.IllegalComponentStateException;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JWindow;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author Amir Altamirano
 */
public class EditarCompra extends javax.swing.JInternalFrame {
    private ComprasManager cm=new ComprasManager();
    private ProductoManager pm=new  ProductoManager();
    private ProveeedorManager proveeedorManager=new ProveeedorManager();
    
    private JList<String> listaSugerencias = new JList<>();
    private JScrollPane scrollPaneSugerencias = new JScrollPane(listaSugerencias);
    private JWindow ventanaSugerencias = new JWindow();
    private List<Producto> listaProductos = new ArrayList<>();
    private Producto productoSeleccionado = null;
    
    private JList<String> listaSugerenciasProveedor = new JList<>();
    private JScrollPane scrollPaneSugerenciasProveedor = new JScrollPane(listaSugerenciasProveedor);
    private JWindow ComprasSugerenciasProveedor = new JWindow();
    private List<Proveedor> listaProveedores = new ArrayList<>();
    private Proveedor ProveedorSeleccionado=null;
    
    
    
    
    private ArrayList<CompraDetalles> ListaDetalles=new ArrayList<>();
    /**
     * Creates new form EditarCompra
     */
    public EditarCompra() {
        initComponents();
        
        
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
                    if (prod.getNombre().toLowerCase().startsWith(texto) && ProveedorSeleccionado!=null) {
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
        
        try {
            setListaProductos(pm.cargarProductosActivos());
        } catch (Exception ex) {
            Logger.getLogger(RegistrarVenta.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ComprasSugerenciasProveedor.setFocusableWindowState(false);
        scrollPaneSugerenciasProveedor.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        ComprasSugerenciasProveedor.add(scrollPaneSugerenciasProveedor);

        // Listener para txtCliente
        txtProveedor.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { actualizarSugerenciasCliente(); }
            public void removeUpdate(DocumentEvent e) { actualizarSugerenciasCliente(); }
            public void changedUpdate(DocumentEvent e) {}

            private void actualizarSugerenciasCliente() {
                String texto = txtProveedor.getText().toLowerCase();
                if (texto.isEmpty()) {
                    ComprasSugerenciasProveedor.setVisible(false);
                    return;
                }

                List<String> sugerencias = new ArrayList<>();
                for (Proveedor c : listaProveedores) {
                    if (c.getNombre().toLowerCase().startsWith(texto)) {
                        sugerencias.add(c.getNombre());
                    }
                }


                if (sugerencias.isEmpty()) {
                    ComprasSugerenciasProveedor.setVisible(false);
                    return;
                }

                listaSugerenciasProveedor.setListData(sugerencias.toArray(new String[0]));
                listaSugerenciasProveedor.setSelectedIndex(0);

                try {
                    Point location = txtProveedor.getLocationOnScreen();
                    ComprasSugerenciasProveedor.setLocation(location.x, location.y + txtProveedor.getHeight());
                    ComprasSugerenciasProveedor.setSize(txtProveedor.getWidth(), Math.min(100, sugerencias.size() * 20));
                    ComprasSugerenciasProveedor.setVisible(true);
                } catch (IllegalComponentStateException ex) {
                    ComprasSugerenciasProveedor.setVisible(false);
                }
            }
        });
        
        listaSugerenciasProveedor.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                seleccionarSugerenciaCliente();
            }
        });

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
        jLayeredPane1 = new javax.swing.JLayeredPane();
        LBImagenProducto = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtProducto = new javax.swing.JTextField();
        BTNuevoProducto = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        SPCantidad = new javax.swing.JSpinner();
        BTAgregar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        textTotal = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jLabel3 = new javax.swing.JLabel();
        txtProveedor = new javax.swing.JTextField();
        BTNuevoProveedor = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(190, 147, 234));

        LBImagenProducto.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        LBImagenProducto.setForeground(new java.awt.Color(255, 255, 255));
        LBImagenProducto.setText("Imagen");
        LBImagenProducto.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        LBImagenProducto.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        LBImagenProducto.setRequestFocusEnabled(false);
        LBImagenProducto.setVerifyInputWhenFocusTarget(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Nombre:");

        BTNuevoProducto.setText("+");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Cantidad");

        SPCantidad.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        BTAgregar.setText("Agregar");
        BTAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTAgregarActionPerformed(evt);
            }
        });

        jLayeredPane1.setLayer(LBImagenProducto, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(txtProducto, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(BTNuevoProducto, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(SPCantidad, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(BTAgregar, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(LBImagenProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                .addComponent(txtProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(BTNuevoProducto))
                            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(BTAgregar)
                                .addComponent(SPCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(LBImagenProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BTNuevoProducto))
                .addGap(30, 30, 30)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(SPCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addComponent(BTAgregar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Total");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Proveedor");

        BTNuevoProveedor.setText("+");

        jButton1.setText("-");

        jButton2.setText("✍");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(146, 146, 146)
                        .addComponent(textTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BTNuevoProveedor))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(430, 430, 430)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(421, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPane1)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BTNuevoProveedor))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(textTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(494, 494, 494)
                    .addComponent(jLabel4)
                    .addContainerGap(21, Short.MAX_VALUE)))
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
                CompraDetalles detalle=new CompraDetalles();
                detalle.setProducto(productoSeleccionado);
                detalle.setCantidad((int) SPCantidad.getValue());
                float precioTotal=detalle.getCantidad()*detalle.getProducto().getPrecioVenta();
                detalle.setPrecioTotal(precioTotal);

                ListaDetalles.add(detalle);
                float Total=cm.CalcularTotal(ListaDetalles);
                textTotal.setText( String.valueOf(Total));
                limpiarAgregarProducto();
            }
            CargarTabla();
        }
        
        
    }//GEN-LAST:event_BTAgregarActionPerformed

    
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
    
    public void setListaProductos(List<Producto> productos) {
        this.listaProductos = productos;
    }
    
    private void seleccionarSugerenciaCliente() {
        String seleccionado = listaSugerenciasProveedor.getSelectedValue();
        if (seleccionado != null) {
            txtProveedor.setText(seleccionado);
            ComprasSugerenciasProveedor.setVisible(false);
            
            for (Proveedor c : listaProveedores) {
                if (c.getNombre().equals(seleccionado)) {
                    ProveedorSeleccionado = c;
                    break;
                }
            }
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTAgregar;
    private javax.swing.JButton BTNuevoProducto;
    private javax.swing.JButton BTNuevoProveedor;
    private javax.swing.JLabel LBImagenProducto;
    private javax.swing.JSpinner SPCantidad;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField textTotal;
    private javax.swing.JTextField txtProducto;
    private javax.swing.JTextField txtProveedor;
    // End of variables declaration//GEN-END:variables

    public void limpiarAgregarProducto() {
        txtProducto.setText("");                       
        SPCantidad.setValue(0);                        
        productoSeleccionado = null;                   
        LBImagenProducto.setIcon(null);                
        ventanaSugerencias.setVisible(false);         
    }


}
