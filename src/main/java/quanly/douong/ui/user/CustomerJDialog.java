/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package quanly.douong.ui.user;

import lombok.Getter;
import lombok.Setter;
import quanly.douong.controller.CustomerController;
import quanly.douong.dao.CustomerDAO;
import quanly.douong.dao.impl.CustomerDAOImpl;
import quanly.douong.entity.Customer;
import quanly.douong.util.XDialog;
import quanly.douong.util.XQuery;

/**
 * @author Sang
 */
public class CustomerJDialog extends javax.swing.JDialog implements CustomerController {
    CustomerDAO dao = new CustomerDAOImpl();

    @Getter
    @Setter
    Customer customer;
    boolean checked = false;
    boolean confirm = false;

    public CustomerJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        pnlInf.setVisible(false);
        this.customer = new Customer();
    }

    @Override
    public void open() {
        this.setLocationRelativeTo(null);
        txtCusName.setText("");
        txtAddress.setText("");
        txtPhone.setText("");
        txtEmail.setText("");
    }

    @Override
    public void check() {
        try {
            String phoneStr = txtPhone.getText();
            if (phoneStr.isBlank()) {
                XDialog.alert("Hãy nhập số điện thoại!");
                return;
            }

            int phone = Integer.parseInt(phoneStr.trim());
            Customer cus = dao.findByPhoneNumber(phone);
            if (cus != null) {
                setEditable(true);
                customer = cus;
                txtCusName.setText(customer.getCustomerName());
                txtAddress.setText(customer.getAddress());
                txtEmail.setText(customer.getEmail());
                checked = true;
            } else {
                XDialog.alert("Tạo mới khách hàng!");
                setEditable(true);
                txtCusName.setEnabled(true);
            }
        } catch (NumberFormatException e) {
            XDialog.alert("Vui lòng nhập đúng định dạng số điện thoại!");
        }
    }

    @Override
    public void save() {
        if (checked) {
            XDialog.alert("Tạo đơn hàng thành công");
            this.dispose();
        } else {
            String customerName = txtCusName.getText();
            String address = txtAddress.getText();
            String phone = txtPhone.getText();
            String email = txtEmail.getText();
            Customer entity = new Customer();
            entity.setCustomerName(customerName);
            entity.setAddress(address);
            try {
                long check = Long.parseLong(phone);
                entity.setPhoneNumber(phone);
            } catch (NumberFormatException e) {
                XDialog.alert("Số điện thoại phải là số!");
            }
            entity.setEmail(email);

            if (customerName.isBlank() || address.isBlank() || phone.isBlank() || email.isBlank()) {
                XDialog.alert("Không được để trống các trường!");
            } else {
                dao.create(entity);
                this.customer = entity;
                XDialog.alert("Tạo đơn hàng thành công");
                this.dispose();
            }
        }
        confirm = true;
    }

    public boolean isConfirm() {
        return confirm;
    }

    @Override
    public void close() {
        confirm = false;
        this.dispose();
    }

    public void setEditable(boolean editable) {
        btnCheck.setEnabled(!editable);
        pnlInf.setVisible(editable);
        txtCusName.setEnabled(!editable);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtPhone = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnCheck = new javax.swing.JButton();
        pnlInf = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtCusName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        txtAddress = new javax.swing.JTextField();
        btnConfirm = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Khách hàng");

        jLabel3.setText("Số điện thoại:");

        btnCheck.setText("Check");
        btnCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckActionPerformed(evt);
            }
        });

        jLabel2.setText("Tên khách hàng:");

        jLabel4.setText("Địa chỉ:");

        jLabel5.setText("Email:");

        javax.swing.GroupLayout pnlInfLayout = new javax.swing.GroupLayout(pnlInf);
        pnlInf.setLayout(pnlInfLayout);
        pnlInfLayout.setHorizontalGroup(
            pnlInfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInfLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlInfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlInfLayout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addGap(61, 61, 61))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlInfLayout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                    .addGroup(pnlInfLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(pnlInfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtCusName, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                    .addComponent(txtEmail)
                    .addComponent(txtAddress))
                .addContainerGap(83, Short.MAX_VALUE))
        );
        pnlInfLayout.setVerticalGroup(
            pnlInfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInfLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(pnlInfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtCusName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(pnlInfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addContainerGap())
        );

        btnConfirm.setText("Xác nhận");
        btnConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmActionPerformed(evt);
            }
        });

        btnCancel.setText("Hủy");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(143, 143, 143)
                .addComponent(jLabel1))
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel3)
                .addGap(31, 31, 31)
                .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(btnCheck))
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(pnlInf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(170, 170, 170)
                .addComponent(btnConfirm)
                .addGap(22, 22, 22)
                .addComponent(btnCancel))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel1)
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel3))
                    .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCheck))
                .addGap(18, 18, 18)
                .addComponent(pnlInf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnConfirm)
                    .addComponent(btnCancel)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmActionPerformed
        if (!pnlInf.isVisible()) {
            XDialog.alert("Vui lòng nhập thông tin trước!");
            return;
        }

        this.save();
    }//GEN-LAST:event_btnConfirmActionPerformed

    private void btnCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckActionPerformed
        check();
    }//GEN-LAST:event_btnCheckActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        close();
    }//GEN-LAST:event_btnCancelActionPerformed

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
            java.util.logging.Logger.getLogger(CustomerJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CustomerJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CustomerJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CustomerJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CustomerJDialog dialog = new CustomerJDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnCheck;
    private javax.swing.JButton btnConfirm;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel pnlInf;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtCusName;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtPhone;
    // End of variables declaration//GEN-END:variables
}
