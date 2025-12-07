package quanly.douong.ui;

import javax.swing.JFrame;
import quanly.douong.controller.ManagerDrinkController;
import quanly.douong.util.XAuth;

import java.awt.*;
import javax.swing.JOptionPane;
import quanly.douong.ui.manager.ProductJPanel;
import quanly.douong.ui.user.BillJPanel;

public class ManagerDrinkJFrame extends javax.swing.JFrame implements ManagerDrinkController {

    private CardLayout cardLayout;
    private ProductJPanel cardProduct = new ProductJPanel();
    private BillJPanel cardBill = new BillJPanel();

    public ManagerDrinkJFrame() {
        initComponents();
        this.init();
    }

    @Override
    public void init() {
        this.setLocationRelativeTo(null);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);       //full-screen
        updateUser();

        setCardLayout();

        updateActiveLabel(lblProduct);

        if (!XAuth.user.isRole()) {
            lblBillMana.setVisible(false);
            lblPromo.setVisible(false);
            lblTotalMn.setVisible(false);
            lblUserMana.setVisible(false);
        }
    }

    private void updateActiveLabel(javax.swing.JLabel activeLabel) {
        lblProduct.setForeground(java.awt.Color.BLACK);
        lblBill.setForeground(java.awt.Color.BLACK);
        lblBillMana.setForeground(java.awt.Color.BLACK);
        lblUserMana.setForeground(java.awt.Color.BLACK);
        lblPromo.setForeground(java.awt.Color.BLACK);
        lblTotalMn.setForeground(java.awt.Color.BLACK);

        activeLabel.setForeground(new java.awt.Color(153, 51, 0));
    }

    private void setCardLayout() {
        cardLayout = (CardLayout) pnlCenter.getLayout();
        pnlCenter.add(cardProduct, "product");
        pnlCenter.add(cardBill, "bill");
        // add pnlCenter các form
        
        cardLayout.show(pnlCenter, "product");
    }

    private void updateUser() {
        String roleText = XAuth.user.isRole() ? "Quản lý" : "Nhân viên";
        lblRole.setText(XAuth.user.getFullname()+ " - " + roleText);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        pnlHeader = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblRole = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        btnChange = new javax.swing.JButton();
        pnlLeft = new javax.swing.JPanel();
        lblProduct = new javax.swing.JLabel();
        lblBill = new javax.swing.JLabel();
        lblBillMana = new javax.swing.JLabel();
        lblUserMana = new javax.swing.JLabel();
        lblPromo = new javax.swing.JLabel();
        lblTotalMn = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        pnlCenter = new javax.swing.JPanel();
        pnlProduct = new javax.swing.JPanel();
        pnlBill = new javax.swing.JPanel();
        pnlBillMana = new javax.swing.JPanel();
        pnlUserMana = new javax.swing.JPanel();
        pnlPromo = new javax.swing.JPanel();
        pnlTotalMn = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Hình ảnh");

        lblRole.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblRole.setForeground(new java.awt.Color(255, 0, 0));
        lblRole.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        btnLogout.setText("Đăng xuất");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        btnChange.setText("Đổi mật khẩu");
        btnChange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlHeaderLayout = new javax.swing.GroupLayout(pnlHeader);
        pnlHeader.setLayout(pnlHeaderLayout);
        pnlHeaderLayout.setHorizontalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeaderLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addComponent(lblRole, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(btnChange)
                .addGap(18, 18, 18)
                .addComponent(btnLogout)
                .addContainerGap())
        );
        pnlHeaderLayout.setVerticalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeaderLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblRole, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnLogout)
                        .addComponent(btnChange)))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jPanel1.add(pnlHeader, java.awt.BorderLayout.PAGE_START);

        pnlLeft.setBackground(new java.awt.Color(102, 255, 204));

        lblProduct.setBackground(new java.awt.Color(255, 255, 255));
        lblProduct.setForeground(new java.awt.Color(0, 0, 0));
        lblProduct.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblProduct.setText("Sản phẩm");
        lblProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblProductMouseClicked(evt);
            }
        });

        lblBill.setBackground(new java.awt.Color(255, 255, 255));
        lblBill.setForeground(new java.awt.Color(0, 0, 0));
        lblBill.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBill.setText("Lịch sử đơn hàng");
        lblBill.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBillMouseClicked(evt);
            }
        });

        lblBillMana.setBackground(new java.awt.Color(255, 255, 255));
        lblBillMana.setForeground(new java.awt.Color(0, 0, 0));
        lblBillMana.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBillMana.setText("Quản lý đơn hàng");
        lblBillMana.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBillManaMouseClicked(evt);
            }
        });

        lblUserMana.setBackground(new java.awt.Color(255, 255, 255));
        lblUserMana.setForeground(new java.awt.Color(0, 0, 0));
        lblUserMana.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUserMana.setText("Quản lý nhân viên");
        lblUserMana.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblUserManaMouseClicked(evt);
            }
        });

        lblPromo.setBackground(new java.awt.Color(255, 255, 255));
        lblPromo.setForeground(new java.awt.Color(0, 0, 0));
        lblPromo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPromo.setText("Quản lý khuyến mãi");
        lblPromo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPromoMouseClicked(evt);
            }
        });

        lblTotalMn.setBackground(new java.awt.Color(255, 255, 255));
        lblTotalMn.setForeground(new java.awt.Color(0, 0, 0));
        lblTotalMn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotalMn.setText("Doanh thu");
        lblTotalMn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTotalMnMouseClicked(evt);
            }
        });

        jButton3.setText("Thoát");

        javax.swing.GroupLayout pnlLeftLayout = new javax.swing.GroupLayout(pnlLeft);
        pnlLeft.setLayout(pnlLeftLayout);
        pnlLeftLayout.setHorizontalGroup(
            pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLeftLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblProduct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblBill, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblBillMana, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblUserMana, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblPromo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTotalMn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlLeftLayout.setVerticalGroup(
            pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLeftLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblProduct)
                .addGap(18, 18, 18)
                .addComponent(lblBill)
                .addGap(18, 18, 18)
                .addComponent(lblBillMana)
                .addGap(18, 18, 18)
                .addComponent(lblUserMana)
                .addGap(18, 18, 18)
                .addComponent(lblPromo)
                .addGap(18, 18, 18)
                .addComponent(lblTotalMn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 177, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addContainerGap())
        );

        jPanel1.add(pnlLeft, java.awt.BorderLayout.LINE_START);

        pnlCenter.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout pnlProductLayout = new javax.swing.GroupLayout(pnlProduct);
        pnlProduct.setLayout(pnlProductLayout);
        pnlProductLayout.setHorizontalGroup(
            pnlProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 670, Short.MAX_VALUE)
        );
        pnlProductLayout.setVerticalGroup(
            pnlProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 398, Short.MAX_VALUE)
        );

        pnlCenter.add(pnlProduct, "card2");

        javax.swing.GroupLayout pnlBillLayout = new javax.swing.GroupLayout(pnlBill);
        pnlBill.setLayout(pnlBillLayout);
        pnlBillLayout.setHorizontalGroup(
            pnlBillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 670, Short.MAX_VALUE)
        );
        pnlBillLayout.setVerticalGroup(
            pnlBillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 398, Short.MAX_VALUE)
        );

        pnlCenter.add(pnlBill, "card3");

        javax.swing.GroupLayout pnlBillManaLayout = new javax.swing.GroupLayout(pnlBillMana);
        pnlBillMana.setLayout(pnlBillManaLayout);
        pnlBillManaLayout.setHorizontalGroup(
            pnlBillManaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 670, Short.MAX_VALUE)
        );
        pnlBillManaLayout.setVerticalGroup(
            pnlBillManaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 398, Short.MAX_VALUE)
        );

        pnlCenter.add(pnlBillMana, "card4");

        javax.swing.GroupLayout pnlUserManaLayout = new javax.swing.GroupLayout(pnlUserMana);
        pnlUserMana.setLayout(pnlUserManaLayout);
        pnlUserManaLayout.setHorizontalGroup(
            pnlUserManaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 670, Short.MAX_VALUE)
        );
        pnlUserManaLayout.setVerticalGroup(
            pnlUserManaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 398, Short.MAX_VALUE)
        );

        pnlCenter.add(pnlUserMana, "card5");

        javax.swing.GroupLayout pnlPromoLayout = new javax.swing.GroupLayout(pnlPromo);
        pnlPromo.setLayout(pnlPromoLayout);
        pnlPromoLayout.setHorizontalGroup(
            pnlPromoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 670, Short.MAX_VALUE)
        );
        pnlPromoLayout.setVerticalGroup(
            pnlPromoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 398, Short.MAX_VALUE)
        );

        pnlCenter.add(pnlPromo, "card6");

        javax.swing.GroupLayout pnlTotalMnLayout = new javax.swing.GroupLayout(pnlTotalMn);
        pnlTotalMn.setLayout(pnlTotalMnLayout);
        pnlTotalMnLayout.setHorizontalGroup(
            pnlTotalMnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 670, Short.MAX_VALUE)
        );
        pnlTotalMnLayout.setVerticalGroup(
            pnlTotalMnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 398, Short.MAX_VALUE)
        );

        pnlCenter.add(pnlTotalMn, "card7");

        jPanel1.add(pnlCenter, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblProductMouseClicked
        cardLayout.show(pnlCenter, "product");
        updateActiveLabel(lblProduct);
    }//GEN-LAST:event_lblProductMouseClicked

    private void lblBillMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBillMouseClicked
        cardLayout.show(pnlCenter, "bill");
        updateActiveLabel(lblBill);
    }//GEN-LAST:event_lblBillMouseClicked

    private void lblBillManaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBillManaMouseClicked
        cardLayout.show(pnlBillMana, "card4");
        updateActiveLabel(lblBillMana);
    }//GEN-LAST:event_lblBillManaMouseClicked

    private void lblUserManaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUserManaMouseClicked
        cardLayout.show(pnlUserMana, "card5");
        updateActiveLabel(lblUserMana);
    }//GEN-LAST:event_lblUserManaMouseClicked

    private void lblPromoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPromoMouseClicked
        cardLayout.show(pnlPromo, "card6");
        updateActiveLabel(lblPromo);
    }//GEN-LAST:event_lblPromoMouseClicked

    private void lblTotalMnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTotalMnMouseClicked
        cardLayout.show(pnlTotalMn, "card7");
        updateActiveLabel(lblTotalMn);    }//GEN-LAST:event_lblTotalMnMouseClicked

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có muốn đăng xuất?", "Đăng xuất", JOptionPane.YES_NO_OPTION);
        if(confirm == JOptionPane.YES_NO_OPTION) {
            this.dispose();
            LoginJDialog login = new LoginJDialog(null, true);
            login.open();
            login.setVisible(true);
        }
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnChangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangeActionPerformed
       ChangePasswordJDialog changePassword = new ChangePasswordJDialog(null, true);
       changePassword.open();
       changePassword.setVisible(true);
    }//GEN-LAST:event_btnChangeActionPerformed

    public static void main(String[] args) {
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
            java.util.logging.Logger.getLogger(ManagerDrinkJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManagerDrinkJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManagerDrinkJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManagerDrinkJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManagerDrinkJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChange;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblBill;
    private javax.swing.JLabel lblBillMana;
    private javax.swing.JLabel lblProduct;
    private javax.swing.JLabel lblPromo;
    private javax.swing.JLabel lblRole;
    private javax.swing.JLabel lblTotalMn;
    private javax.swing.JLabel lblUserMana;
    private javax.swing.JPanel pnlBill;
    private javax.swing.JPanel pnlBillMana;
    private javax.swing.JPanel pnlCenter;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JPanel pnlLeft;
    private javax.swing.JPanel pnlProduct;
    private javax.swing.JPanel pnlPromo;
    private javax.swing.JPanel pnlTotalMn;
    private javax.swing.JPanel pnlUserMana;
    // End of variables declaration//GEN-END:variables
}
