/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Presentacion;

import javax.swing.plaf.basic.BasicInternalFrameUI;

/**
 *
 * @author jheff
 */
public class Reportes extends javax.swing.JInternalFrame {
   private static Reportes reportes;
    /**
     * Creates new form Reportes
     */
    private Reportes() {
        initComponents();
        /*BasicInternalFrameUI uli = (BasicInternalFrameUI)this.getUI();
        uli.setNorthPane(null);*/
        //this.setBorder(null);
    }

    
    public static Reportes getReportes(){
    if (reportes==null||reportes.isClosed()){
    reportes = new Reportes();
    }
    return reportes;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        BTProductoMasVendido = new javax.swing.JButton();
        BTMayoresCompradores = new javax.swing.JButton();
        BTClientesFrecuentes = new javax.swing.JButton();
        BTProductosXproveedor = new javax.swing.JButton();
        BTGananciasXmes = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();


        jPanel2.setBackground(new java.awt.Color(204, 204, 255));


        BTProductoMasVendido.setBackground(new java.awt.Color(228, 225, 225));
        BTProductoMasVendido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Productos mas vendidos.png"))); // NOI18N
        BTProductoMasVendido.setText("Productos mas vendidos");
        BTProductoMasVendido.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 20, 0, new java.awt.Color(255, 255, 0)));
        BTProductoMasVendido.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BTProductoMasVendido.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BTProductoMasVendido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTProductoMasVendidoActionPerformed(evt);
            }
        });

        BTMayoresCompradores.setBackground(new java.awt.Color(228, 225, 225));
        BTMayoresCompradores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Mayores compradores.png"))); // NOI18N
        BTMayoresCompradores.setText("Mayores compradores");
        BTMayoresCompradores.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 20, 0, new java.awt.Color(255, 0, 0)));
        BTMayoresCompradores.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BTMayoresCompradores.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BTMayoresCompradores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTMayoresCompradoresActionPerformed(evt);
            }
        });

        BTClientesFrecuentes.setBackground(new java.awt.Color(228, 225, 225));
        BTClientesFrecuentes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Clientes frecuentes.png"))); // NOI18N
        BTClientesFrecuentes.setText("Clientes frecuentes");
        BTClientesFrecuentes.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 20, 0, new java.awt.Color(102, 204, 255)));
        BTClientesFrecuentes.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BTClientesFrecuentes.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        BTProductosXproveedor.setBackground(new java.awt.Color(228, 225, 225));
        BTProductosXproveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Productos por proveedor mas vendido.png"))); // NOI18N
        BTProductosXproveedor.setText("Productos por proveedor mas vendido");
        BTProductosXproveedor.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 20, 0, new java.awt.Color(0, 153, 153)));
        BTProductosXproveedor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BTProductosXproveedor.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        BTGananciasXmes.setBackground(new java.awt.Color(228, 225, 225));
        BTGananciasXmes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Ganancias x mes.png"))); // NOI18N
        BTGananciasXmes.setText("Ganancias x mes");
        BTGananciasXmes.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 20, 0, new java.awt.Color(0, 204, 0)));
        BTGananciasXmes.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BTGananciasXmes.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BTProductosXproveedor, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                    .addComponent(BTProductoMasVendido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BTMayoresCompradores, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                    .addComponent(BTGananciasXmes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BTClientesFrecuentes, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                .addGap(26, 26, 26))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(BTProductoMasVendido, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                    .addComponent(BTMayoresCompradores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BTClientesFrecuentes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(BTProductosXproveedor, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                    .addComponent(BTGananciasXmes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(191, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));

        jLabel1.setFont(new java.awt.Font("Roboto Black", 2, 24)); // NOI18N

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Reportes");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BTProductoMasVendidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTProductoMasVendidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BTProductoMasVendidoActionPerformed

    private void BTMayoresCompradoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTMayoresCompradoresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BTMayoresCompradoresActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTClientesFrecuentes;
    private javax.swing.JButton BTGananciasXmes;
    private javax.swing.JButton BTMayoresCompradores;
    private javax.swing.JButton BTProductoMasVendido;
    private javax.swing.JButton BTProductosXproveedor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
