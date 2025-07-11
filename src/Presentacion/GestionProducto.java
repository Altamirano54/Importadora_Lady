/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Presentacion;

import Entidades.Producto;
import Entidades.Proveedor;
import Logica.ProductoManager;
import Presentacion.Modelos.ModeloTablaProducto;
import Presentacion.Modelos.ModeloComboboxProveedores;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.datatransfer.DataFlavor;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.TransferHandler;
import Logica.ProveeedorManager;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import javax.swing.table.DefaultTableCellRenderer;

import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableCellRenderer;
import java.awt.Component;
/**
 *
 * @author Amir Altamirano
 */
public class GestionProducto extends javax.swing.JInternalFrame {

    private static GestionProducto producto;

    private ProductoManager pm;
    private ProveeedorManager proveeedorManager;
    private ModeloTablaProducto modeloTablaProducto = new ModeloTablaProducto();
    private ModeloComboboxProveedores comboboxProveedor = new ModeloComboboxProveedores();
    private Producto productoSeleccionado = null;

    /**
     * Creates new form GestionProducto
     */
    private GestionProducto() {

        /*BasicInternalFrameUI uli = (BasicInternalFrameUI) this.getUI();
        uli.setNorthPane(null);*/
        //this.setBorder(null);

        this.comboboxProveedor = new ModeloComboboxProveedores();
        initComponents();
        pm = new ProductoManager();
        proveeedorManager = new ProveeedorManager();

        /*FUNCION DE GUARDADO DE IMAGEN*/
        lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
        lblImagen.setText("Arrastra aquí una imagen");
        lblImagen.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        lblImagen.setTransferHandler(new TransferHandler() {
            @Override
            public boolean canImport(TransferHandler.TransferSupport support) {
                return support.isDataFlavorSupported(DataFlavor.javaFileListFlavor);
            }

            @Override
            public boolean importData(TransferHandler.TransferSupport support) {
                try {
                    List<File> archivos = (List<File>) support.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
                    File archivo = archivos.get(0);
                    BufferedImage imagen = ImageIO.read(archivo);

                    if (imagen == null) {
                        JOptionPane.showMessageDialog(null, "Archivo no es una imagen válida.");
                        return false;
                    }
                    String nombre = archivo.getName().toLowerCase();
                    if (!(nombre.endsWith(".png") || nombre.endsWith(".jpg") || nombre.endsWith(".jpeg") || nombre.endsWith(".bmp"))) {
                        JOptionPane.showMessageDialog(null, "Solo se permiten imágenes PNG, JPG, BMP.");
                        return false;
                    }

                    // Redimensionar imagen
                    Image escalada = imagen.getScaledInstance(lblImagen.getWidth(), lblImagen.getHeight(), Image.SCALE_SMOOTH);
                    lblImagen.setIcon(new ImageIcon(escalada));
                    lblImagen.setText("");

                    return true;
                } catch (Exception ex) {
                    ex.printStackTrace();
                    return false;
                }
            }
        });
        TBListaProductos.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                // No hacemos nada aquí directamente, los botones "Modificar" y "Eliminar" usarán la selección.
            }
        });
        cargarTabla();
        CargarCombobox();
        this.LayeredRegistro_producto.setVisible(false);
        this.LayeredRegistro_producto.setSize(0, 499);
        this.LayeredVerProducto.setVisible(false);
        this.LayeredVerProducto.setSize(0, 450);
        Dimension sizeDimension = CalcularDimenciones();
        this.setSize(sizeDimension.width, sizeDimension.height);
        System.out.println("se esta maximisado" + this.isMaximum());

    }

    public static GestionProducto getProducto() {

        if (producto == null || producto.isClosed()) {
            producto = new GestionProducto();
        }
        return producto;

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
        LayeredRegistro_producto = new javax.swing.JLayeredPane();
        jLabel1 = new javax.swing.JLabel();
        TFNombre = new javax.swing.JTextField();
        lblImagen = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        SPPrecioCompra = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        SPPrecioVenta = new javax.swing.JSpinner();
        Registrar = new javax.swing.JButton();
        BTCancelarRegistro = new javax.swing.JButton();
        CBProveedores = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        SPStock = new javax.swing.JSpinner();
        LayeredListaProductos = new javax.swing.JLayeredPane();
        jLabel2 = new javax.swing.JLabel();
        BTModificar = new javax.swing.JButton();
        BTEliminar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TBListaProductos = new javax.swing.JTable();
        BTNuevo = new javax.swing.JButton();
        LayeredVerProducto = new javax.swing.JLayeredPane();
        LBNombre_producto = new javax.swing.JLabel();
        LBVerImagen = new javax.swing.JLabel();
        LBPrecioVenta = new javax.swing.JLabel();
        LBPrecioCompra = new javax.swing.JLabel();
        BTCerrarVer = new javax.swing.JButton();
        LBProveedor = new javax.swing.JLabel();
        LBStock = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setClosable(true);
        setMaximizable(true);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        LayeredRegistro_producto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel1.setText("Nombre:");

        TFNombre.setBackground(new java.awt.Color(204, 204, 255));
        TFNombre.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        TFNombre.setForeground(new java.awt.Color(255, 255, 255));
        TFNombre.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        lblImagen.setBackground(new java.awt.Color(255, 255, 255));
        lblImagen.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        lblImagen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/add_16750539.png"))); // NOI18N
        lblImagen.setToolTipText("");
        lblImagen.setBorder(new javax.swing.border.MatteBorder(null));
        lblImagen.setRequestFocusEnabled(false);
        lblImagen.setVerifyInputWhenFocusTarget(false);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel3.setText("Precion Compra:");
        jLabel3.setToolTipText("");

        SPPrecioCompra.setModel(new javax.swing.SpinnerNumberModel(0.0f, 0.0f, null, 0.5f));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel4.setText("Precio Venta:");

        SPPrecioVenta.setModel(new javax.swing.SpinnerNumberModel(0.0f, 0.0f, null, 0.5f));

        Registrar.setBackground(new java.awt.Color(153, 153, 255));
        Registrar.setText("Registrar");
        Registrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegistrarActionPerformed(evt);
            }
        });

        BTCancelarRegistro.setBackground(new java.awt.Color(153, 153, 255));
        BTCancelarRegistro.setText("x");
        BTCancelarRegistro.setPreferredSize(new java.awt.Dimension(25, 25));
        BTCancelarRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTCancelarRegistroActionPerformed(evt);
            }
        });

        CBProveedores.setModel(this.comboboxProveedor);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel5.setText("Proveedor");
        jLabel5.setToolTipText("");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel6.setText("Stock:");
        jLabel6.setToolTipText("");

        SPStock.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        LayeredRegistro_producto.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        LayeredRegistro_producto.setLayer(TFNombre, javax.swing.JLayeredPane.DEFAULT_LAYER);
        LayeredRegistro_producto.setLayer(lblImagen, javax.swing.JLayeredPane.DEFAULT_LAYER);
        LayeredRegistro_producto.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        LayeredRegistro_producto.setLayer(SPPrecioCompra, javax.swing.JLayeredPane.DEFAULT_LAYER);
        LayeredRegistro_producto.setLayer(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        LayeredRegistro_producto.setLayer(SPPrecioVenta, javax.swing.JLayeredPane.DEFAULT_LAYER);
        LayeredRegistro_producto.setLayer(Registrar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        LayeredRegistro_producto.setLayer(BTCancelarRegistro, javax.swing.JLayeredPane.DEFAULT_LAYER);
        LayeredRegistro_producto.setLayer(CBProveedores, javax.swing.JLayeredPane.DEFAULT_LAYER);
        LayeredRegistro_producto.setLayer(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        LayeredRegistro_producto.setLayer(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        LayeredRegistro_producto.setLayer(SPStock, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout LayeredRegistro_productoLayout = new javax.swing.GroupLayout(LayeredRegistro_producto);
        LayeredRegistro_producto.setLayout(LayeredRegistro_productoLayout);
        LayeredRegistro_productoLayout.setHorizontalGroup(
            LayeredRegistro_productoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LayeredRegistro_productoLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(LayeredRegistro_productoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(LayeredRegistro_productoLayout.createSequentialGroup()
                        .addComponent(Registrar, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(LayeredRegistro_productoLayout.createSequentialGroup()
                        .addGroup(LayeredRegistro_productoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(LayeredRegistro_productoLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(TFNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LayeredRegistro_productoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(LayeredRegistro_productoLayout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addGap(27, 27, 27)
                                    .addComponent(SPStock, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(LayeredRegistro_productoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(LayeredRegistro_productoLayout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(CBProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LayeredRegistro_productoLayout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(SPPrecioVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LayeredRegistro_productoLayout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(SPPrecioCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addComponent(BTCancelarRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        LayeredRegistro_productoLayout.setVerticalGroup(
            LayeredRegistro_productoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LayeredRegistro_productoLayout.createSequentialGroup()
                .addGroup(LayeredRegistro_productoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(LayeredRegistro_productoLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(LayeredRegistro_productoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(TFNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(BTCancelarRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(LayeredRegistro_productoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(SPPrecioCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(LayeredRegistro_productoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SPPrecioVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(LayeredRegistro_productoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(SPStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(LayeredRegistro_productoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(CBProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(Registrar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Lista de productos");

        BTModificar.setBackground(new java.awt.Color(153, 153, 255));
        BTModificar.setText("Modificar");
        BTModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTModificarActionPerformed(evt);
            }
        });

        BTEliminar.setBackground(new java.awt.Color(153, 153, 255));
        BTEliminar.setText("Eliminar");
        BTEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTEliminarActionPerformed(evt);
            }
        });

        TBListaProductos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TBListaProductos.setModel(this.modeloTablaProducto);
        TBListaProductos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        TBListaProductos.setGridColor(new java.awt.Color(204, 204, 204));
        TBListaProductos.setInheritsPopupMenu(true);
        TBListaProductos.setRowHeight(20);
        TBListaProductos.setShowHorizontalLines(true);
        TBListaProductos.setShowVerticalLines(true);
        TBListaProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TBListaProductosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TBListaProductos);

        BTNuevo.setBackground(new java.awt.Color(153, 153, 255));
        BTNuevo.setText("Nuevo");
        BTNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNuevoActionPerformed(evt);
            }
        });

        LayeredListaProductos.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        LayeredListaProductos.setLayer(BTModificar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        LayeredListaProductos.setLayer(BTEliminar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        LayeredListaProductos.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        LayeredListaProductos.setLayer(BTNuevo, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout LayeredListaProductosLayout = new javax.swing.GroupLayout(LayeredListaProductos);
        LayeredListaProductos.setLayout(LayeredListaProductosLayout);
        LayeredListaProductosLayout.setHorizontalGroup(
            LayeredListaProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LayeredListaProductosLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(BTNuevo)
                .addGap(18, 18, 18)
                .addComponent(BTModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BTEliminar))
            .addGroup(LayeredListaProductosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(LayeredListaProductosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 522, Short.MAX_VALUE)
                .addContainerGap())
        );
        LayeredListaProductosLayout.setVerticalGroup(
            LayeredListaProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LayeredListaProductosLayout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(39, 39, 39)
                .addComponent(jScrollPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(LayeredListaProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BTEliminar)
                    .addComponent(BTModificar)
                    .addComponent(BTNuevo))
                .addContainerGap())
        );

        LayeredVerProducto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        LayeredVerProducto.setAutoscrolls(true);
        LayeredVerProducto.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        LayeredVerProducto.setOpaque(true);

        LBNombre_producto.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        LBNombre_producto.setText("Nombre de producto");

        LBVerImagen.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        LBVerImagen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LBVerImagen.setText("Imagen de profucto");
        LBVerImagen.setBorder(new javax.swing.border.MatteBorder(null));
        LBVerImagen.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        LBVerImagen.setVerifyInputWhenFocusTarget(false);

        LBPrecioVenta.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        LBPrecioVenta.setText("Precio de venta: S/");

        LBPrecioCompra.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        LBPrecioCompra.setText("Precio de compra: S/");

        BTCerrarVer.setBackground(new java.awt.Color(153, 153, 255));
        BTCerrarVer.setText("X");
        BTCerrarVer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTCerrarVerActionPerformed(evt);
            }
        });

        LBProveedor.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        LBProveedor.setText("Proveedor:");

        LBStock.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        LBStock.setText("Stock:");

        LayeredVerProducto.setLayer(LBNombre_producto, javax.swing.JLayeredPane.DEFAULT_LAYER);
        LayeredVerProducto.setLayer(LBVerImagen, javax.swing.JLayeredPane.DEFAULT_LAYER);
        LayeredVerProducto.setLayer(LBPrecioVenta, javax.swing.JLayeredPane.DEFAULT_LAYER);
        LayeredVerProducto.setLayer(LBPrecioCompra, javax.swing.JLayeredPane.DEFAULT_LAYER);
        LayeredVerProducto.setLayer(BTCerrarVer, javax.swing.JLayeredPane.DEFAULT_LAYER);
        LayeredVerProducto.setLayer(LBProveedor, javax.swing.JLayeredPane.DEFAULT_LAYER);
        LayeredVerProducto.setLayer(LBStock, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout LayeredVerProductoLayout = new javax.swing.GroupLayout(LayeredVerProducto);
        LayeredVerProducto.setLayout(LayeredVerProductoLayout);
        LayeredVerProductoLayout.setHorizontalGroup(
            LayeredVerProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LayeredVerProductoLayout.createSequentialGroup()
                .addGroup(LayeredVerProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(LayeredVerProductoLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(LayeredVerProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LBPrecioCompra)
                            .addComponent(LBPrecioVenta)
                            .addComponent(LBNombre_producto)
                            .addComponent(LBProveedor)
                            .addComponent(LBStock)))
                    .addGroup(LayeredVerProductoLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(LBVerImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LayeredVerProductoLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(BTCerrarVer))
        );
        LayeredVerProductoLayout.setVerticalGroup(
            LayeredVerProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LayeredVerProductoLayout.createSequentialGroup()
                .addComponent(BTCerrarVer)
                .addGap(15, 15, 15)
                .addComponent(LBNombre_producto)
                .addGap(40, 40, 40)
                .addComponent(LBVerImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(LBPrecioVenta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LBPrecioCompra)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LBStock)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(LBProveedor)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(LayeredRegistro_producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LayeredListaProductos)
                .addGap(50, 50, 50)
                .addComponent(LayeredVerProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LayeredVerProducto)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LayeredRegistro_producto)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(LayeredListaProductos)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BTCancelarRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTCancelarRegistroActionPerformed
        this.LayeredRegistro_producto.setVisible(false);
        this.LayeredRegistro_producto.setSize(0, 499);
        if (!this.isMaximum()) {
            Dimension sizeDimension = CalcularDimenciones();
            this.setSize(sizeDimension.width, sizeDimension.height);
        }
        limpiarFormulario();
        //[281, 499]
    }//GEN-LAST:event_BTCancelarRegistroActionPerformed

    private void BTModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTModificarActionPerformed
        System.out.println("se esta maximisado" + this.isMaximum());
        int filaSeleccionada = TBListaProductos.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un producto de la tabla para modificar.", "Selección Requerida", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Carga los datos y luego muestra el panel
        cargarDatosProductoSeleccionado();
        LayeredRegistro_producto.setVisible(true);
        this.LayeredRegistro_producto.setSize(218, 499);
        if (!this.isMaximum()) {
            Dimension sizeDimension = CalcularDimenciones();
            this.setSize(sizeDimension.width, sizeDimension.height);
        }

    }//GEN-LAST:event_BTModificarActionPerformed

    private void BTCerrarVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTCerrarVerActionPerformed
        this.LayeredVerProducto.setVisible(false);
        //[328, 450]
        this.LayeredVerProducto.setSize(0, 450);
        if (!this.isMaximum()) {
            Dimension sizeDimension = CalcularDimenciones();
            this.setSize(sizeDimension.width, sizeDimension.height);
        }
    }//GEN-LAST:event_BTCerrarVerActionPerformed

    private void BTNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNuevoActionPerformed
        System.out.println("se esta maximisado" + this.isMaximum());
        limpiarFormulario();
        this.LayeredRegistro_producto.setVisible(true);
        this.LayeredRegistro_producto.setSize(218, 499);
        if (!this.isMaximum()) {
            Dimension sizeDimension = CalcularDimenciones();
            this.setSize(sizeDimension.width, sizeDimension.height);
        }
    }//GEN-LAST:event_BTNuevoActionPerformed

    private void RegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegistrarActionPerformed
        // --- Validación de campos ---
        if (TFNombre.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre del producto no puede estar vacío.", "Campo Requerido", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (productoSeleccionado == null) {
            // --- MODO REGISTRO ---
            Producto nuevoProducto = new Producto();
            nuevoProducto.setNombre(TFNombre.getText().trim());
            nuevoProducto.setPrecioCompra((float) SPPrecioCompra.getValue());
            nuevoProducto.setPrecioVenta((float) SPPrecioVenta.getValue());
            nuevoProducto.setProveedor(comboboxProveedor.getSeleccionado());
            nuevoProducto.setStock((int) SPStock.getValue());

            BufferedImage img = obtenerImagenDeLabel();
            if (img != null) {
                nuevoProducto.setUrl(pm.guardarImagen(img));
            }

            if (pm.registrarProducto(nuevoProducto)) {
                JOptionPane.showMessageDialog(this, "Producto registrado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                cargarTabla();
                limpiarFormulario();
                
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo registrar el producto.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } else {
            // --- MODO ACTUALIZACIÓN ---
            productoSeleccionado.setNombre(TFNombre.getText().trim());
            productoSeleccionado.setPrecioCompra((float) SPPrecioCompra.getValue());
            productoSeleccionado.setPrecioVenta((float) SPPrecioVenta.getValue());
            productoSeleccionado.setProveedor(comboboxProveedor.getSeleccionado());

            BufferedImage img = obtenerImagenDeLabel();
            if (img != null) {
                // Opcional: podrías querer borrar la imagen anterior antes de guardar la nueva
                productoSeleccionado.setUrl(pm.guardarImagen(img));
            }

            if (pm.actualizarProducto(productoSeleccionado.getId(), productoSeleccionado)) {
                JOptionPane.showMessageDialog(this, "Producto actualizado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                cargarTabla();
                limpiarFormulario();
                LayeredRegistro_producto.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo actualizar el producto.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_RegistrarActionPerformed

    private void BTEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTEliminarActionPerformed
        // TODO add your handling code here:
        int filaSeleccionada = TBListaProductos.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un producto de la tabla para eliminar.", "Selección Requerida", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Producto productoAEliminar = modeloTablaProducto.getProducto(filaSeleccionada);

        int confirmacion = JOptionPane.showConfirmDialog(
                this,
                "¿Está seguro de que desea eliminar el producto '" + productoAEliminar.getNombre() + "'?",
                "Confirmar Eliminación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (confirmacion == JOptionPane.YES_OPTION) {
            if (pm.eliminarProducto(productoAEliminar.getId())) {
                JOptionPane.showMessageDialog(this, "Producto eliminado exitosamente.", "Eliminación Exitosa", JOptionPane.INFORMATION_MESSAGE);
                cargarTabla();
                limpiarFormulario();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo eliminar el producto.", "Error de Eliminación", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_BTEliminarActionPerformed

    private void TBListaProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TBListaProductosMouseClicked
        if (evt.getClickCount() == 2 && TBListaProductos.getSelectedRow() != -1) {
            int fila = TBListaProductos.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(this, "Seleccione un producto de la tabla.");
                return;
            }
            Producto producto = modeloTablaProducto.getProductoEn(fila);
            LBNombre_producto.setText(producto.getNombre());
            LBPrecioCompra.setText("Precio de compra: S/ " + producto.getPrecioCompra());
            LBPrecioVenta.setText("Precio de venta: S/ " + producto.getPrecioVenta());
            LBProveedor.setText("Proveedor:" + producto.getProveedor().getNombre());
            LBStock.setText("Stock: "+ producto.getStock());

            // Cargar imagen si tiene
            if (producto.getUrl() != null && !producto.getUrl().isEmpty()) {
                File archivo = new File(producto.getUrl());
                if (archivo.exists()) {
                    try {
                        BufferedImage imagen = ImageIO.read(archivo);
                        Image escalada = imagen.getScaledInstance(LBVerImagen.getWidth(), LBVerImagen.getHeight(), Image.SCALE_SMOOTH);
                        LBVerImagen.setIcon(new ImageIcon(escalada));
                        LBVerImagen.setText("");
                    } catch (Exception e) {
                        LBVerImagen.setText("Error al cargar imagen");
                        LBVerImagen.setIcon(null);
                    }
                } else {
                    LBVerImagen.setText("Imagen no encontrada");
                    LBVerImagen.setIcon(null);
                }
            } else {
                LBVerImagen.setText("Sin imagen");
                LBVerImagen.setIcon(null);
            }
            LayeredVerProducto.setVisible(true);
            LayeredVerProducto.setSize(328, 450);
            if (!this.isMaximum()) {
                Dimension sizeDimension = CalcularDimenciones();
                this.setSize(sizeDimension.width, sizeDimension.height);
            }
        }
    }//GEN-LAST:event_TBListaProductosMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTCancelarRegistro;
    private javax.swing.JButton BTCerrarVer;
    private javax.swing.JButton BTEliminar;
    private javax.swing.JButton BTModificar;
    private javax.swing.JButton BTNuevo;
    private javax.swing.JComboBox<String> CBProveedores;
    private javax.swing.JLabel LBNombre_producto;
    private javax.swing.JLabel LBPrecioCompra;
    private javax.swing.JLabel LBPrecioVenta;
    private javax.swing.JLabel LBProveedor;
    private javax.swing.JLabel LBStock;
    private javax.swing.JLabel LBVerImagen;
    private javax.swing.JLayeredPane LayeredListaProductos;
    private javax.swing.JLayeredPane LayeredRegistro_producto;
    private javax.swing.JLayeredPane LayeredVerProducto;
    private javax.swing.JButton Registrar;
    private javax.swing.JSpinner SPPrecioCompra;
    private javax.swing.JSpinner SPPrecioVenta;
    private javax.swing.JSpinner SPStock;
    private javax.swing.JTable TBListaProductos;
    private javax.swing.JTextField TFNombre;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblImagen;
    // End of variables declaration//GEN-END:variables

    public BufferedImage obtenerImagenDeLabel() {
        if (lblImagen.getIcon() != null && lblImagen.getIcon() instanceof ImageIcon) {
            Image imagen = ((ImageIcon) lblImagen.getIcon()).getImage();

            // Crear un BufferedImage compatible con la imagen
            BufferedImage buffered = new BufferedImage(
                    imagen.getWidth(null),
                    imagen.getHeight(null),
                    BufferedImage.TYPE_INT_ARGB // Puedes usar TYPE_INT_RGB si no necesitas transparencia
            );

            // Dibujar la imagen en el BufferedImage
            buffered.getGraphics().drawImage(imagen, 0, 0, null);
            return buffered;
        }
        return null; // No hay imagen en el label
    }

    public Dimension CalcularDimenciones() {
        Dimension size = new Dimension(900, 600);
        this.setPreferredSize(size);
        this.setSize(size);
        return size;
    }

    public void cargarTabla() {
        try {
            modeloTablaProducto.setListadoProducto(pm.cargarProductosActivos());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            this.setSize(getPreferredSize());
        }
        centrarCeldas(TBListaProductos);
        ajustarAnchoColumnas(TBListaProductos);
    }

    public void CargarCombobox() {
        try {
            this.comboboxProveedor.setListadoProveedor(proveeedorManager.lista());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void limpiarCampos() {
        TFNombre.setText("");
        SPPrecioCompra.setValue(0.0f);
        SPPrecioVenta.setValue(0.0f);

        lblImagen.setIcon(null);
        lblImagen.setText("Arrastra aquí una imagen");
    }

    private void limpiarFormulario() {
        TFNombre.setText("");
        SPPrecioCompra.setValue(0.0f);
        SPPrecioVenta.setValue(0.0f);
        SPStock.setValue(0.0f);
        if (CBProveedores.getItemCount() > 0) {
            CBProveedores.setSelectedIndex(0);
        }

        // Restablecer el label de la imagen
        lblImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/add_16750539.png")));
        lblImagen.setText("");

        // MUY IMPORTANTE: Resetear el estado a "nuevo registro"
        this.productoSeleccionado = null;
        TBListaProductos.clearSelection();
        Registrar.setText("Registrar");
    }

    private void cargarDatosProductoSeleccionado() {
        int filaSeleccionada = TBListaProductos.getSelectedRow();
        if (filaSeleccionada != -1) {
            this.productoSeleccionado = modeloTablaProducto.getProducto(filaSeleccionada);

            TFNombre.setText(productoSeleccionado.getNombre());
            SPPrecioCompra.setValue(productoSeleccionado.getPrecioCompra());
            SPPrecioVenta.setValue(productoSeleccionado.getPrecioVenta());
            SPStock.setValue(productoSeleccionado.getStock());

            // Seleccionar el proveedor en el ComboBox
            Proveedor prov = productoSeleccionado.getProveedor();
            if (prov != null) {
                CBProveedores.setSelectedIndex(comboboxProveedor.getSelectProveedor(prov));
            }

            // Cargar la imagen
            cargarImagenEnLabel(productoSeleccionado.getUrl(), lblImagen);

            Registrar.setText("Guardar Cambios");
        }
    }

    private void cargarImagenEnLabel(String url, javax.swing.JLabel label) {
        if (url != null && !url.isEmpty()) {
            File archivo = new File(url);
            if (archivo.exists()) {
                try {
                    BufferedImage imagen = ImageIO.read(archivo);
                    Image escalada = imagen.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
                    label.setIcon(new ImageIcon(escalada));
                    label.setText("");
                } catch (Exception e) {
                    label.setText("Error al cargar imagen");
                    label.setIcon(null);
                }
            } else {
                label.setText("Imagen no encontrada");
                label.setIcon(null);
            }
        } else {
            label.setText("Sin imagen");
            label.setIcon(null);
        }
    }

    
    public void centrarCeldas(JTable tabla) {
        DefaultTableCellRenderer centrar = new DefaultTableCellRenderer();
        centrar.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < tabla.getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setCellRenderer(centrar);
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

