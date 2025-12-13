package quanly.douong.ui.manager;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import lombok.Setter;
import quanly.douong.dao.BillDAO;
import quanly.douong.dao.ProductDAO;
import quanly.douong.dao.PromotionDAO;
import quanly.douong.dao.impl.BillDAOImpl;
import quanly.douong.dao.impl.BillDetailDAOImpl;
import quanly.douong.dao.impl.ProductDAOImpl;
import quanly.douong.dao.impl.PromotionDAOImpl;
import quanly.douong.entity.*;
import quanly.douong.ui.user.CustomerJDialog;
import quanly.douong.util.XAuth;
import quanly.douong.util.XDialog;
import quanly.douong.util.XQuery;

public class ProductJPanel extends javax.swing.JPanel {
    ProductDAO productDAO = new ProductDAOImpl();
    BillDAO billDAO = new BillDAOImpl();
    BillDetailDAOImpl billDetailDAO = new BillDetailDAOImpl();
    PromotionDAO promotionDAO = new PromotionDAOImpl();

    List<Product> products = new ArrayList<>();
    List<Bill> bills = List.of();
    List<BillDetail> cart = new ArrayList<>();
    List<Promotion> promotions = new ArrayList<>();

    Bill currentBill;
    Customer currentCustomer;
    Promotion currentPromotion = new Promotion();

    public ProductJPanel() {
        initComponents();
        initLoading();
        fillToProduct();
    }

    private void initLoading() {
        products = productDAO.findAll();
        fillBillDetail();
    }

    private void fillToProduct() {
        DefaultTableModel model = (DefaultTableModel) tblProduct.getModel();
        model.setRowCount(0);

        products.forEach(item -> {
            Object[] row = {
                    item.getName(),
                    item.getQuantity() + " thùng",
                    item.getCostPrice(),
                    item.isStatus() ? "Còn hàng" : "Hết hàng"
            };
            model.addRow(row);
        });
    }

    private void fillBillDetail() {
        DefaultTableModel model = (DefaultTableModel) tblBillDetails.getModel();
        model.setRowCount(0);

        for (BillDetail item : cart) {
            Product product = productDAO.findById(item.getProductId());
            double total = item.getQuantity() * item.getUnitPrice();
            model.addRow(new Object[]{
                    product.getName(),
                    item.getUnitPrice(),
                    item.getQuantity(),
                    total,
                    "+",
                    "-"
            });
        }
    }

    private void order() {
        Product entity = products.get(tblProduct.getSelectedRow());
        if (!entity.isStatus()) {
            XDialog.alert("Hiện đã hết hàng!");
        } else {
            String quantityStr = XDialog.prompt("Số lượng?");
            if (quantityStr == null || quantityStr.isBlank()) {
                return;
            } else {
                try {
                    int quantity = Integer.parseInt(quantityStr);
                    addToCart(entity, quantity);
                } catch (NumberFormatException e) {
                    XDialog.alert("Số lượng không hợp lệ!");
                    return;
                }
            }
        }
    }

    private void addToCart(Product p, int quantity) {
        if (quantity > p.getQuantity()) {
            XDialog.alert("Sản phẩm không đủ số lượng!");
            return;
        }

        // Trừ kho
        p.setQuantity(p.getQuantity() - quantity);

        // Check kho
        if (p.getQuantity() <= 0) {
            p.setStatus(false);
        }
        for (BillDetail item : cart) {
            if (item.getProductId().equals(p.getProductId())) {
                item.setQuantity(item.getQuantity() + quantity);
                fillToProduct();
                fillBillDetail();
                return;
            }
        }

        BillDetail detail = new BillDetail();
        detail.setProductId(p.getProductId());
        detail.setQuantity(quantity);
        detail.setUnitPrice(p.getCostPrice());

        cart.add(detail);
        fillToProduct();
        fillBillDetail();
        updateTotal();
    }

    private void handleQuantityButtons(int row, int col) {
        if (row < 0) return;
        if (row >= cart.size()) return;

        BillDetail item = cart.get(row);
        Product p = findProduct(item.getProductId());
        if (col == 4) {         // nút +
            if (p.getQuantity() == 0) {
                XDialog.alert("Hàng đã hết!");
                return;
            }
            item.setQuantity(item.getQuantity() + 1);
            p.setQuantity(p.getQuantity() - 1);
        } else if (col == 5) {    // nút -
            if (item.getQuantity() >= 1) {
                item.setQuantity(item.getQuantity() - 1);
                p.setQuantity(p.getQuantity() + 1);
                p.setStatus(true);

                if (item.getQuantity() == 0) {
                    cart.remove(row);
                }
            }
        }

        fillToProduct();
        fillBillDetail();
        updateTotal();
    }

    private void updateTotal() {
        double total = calculateTotal();

        DecimalFormat format = new DecimalFormat("#,###");
        String formatted = format.format(total).replace(',', '.') + " VNĐ";
        lblTotal.setText(formatted);
    }

    private double calculateTotal() {
        double total = 0.0;
        for (BillDetail item : cart) {
            Product product = productDAO.findById(item.getProductId());
            if (product != null) {
                total += item.getQuantity() * item.getUnitPrice();
            }
        }
        return total;
    }

    private Product findProduct(String productId) {
        for (Product p : products) {
            if (p.getProductId().equals(productId)) {
                return p;
            }
        }
        return null;
    }

    public void setCustomer(Customer customer) {
        this.currentCustomer = customer;
    }

    private void checkout() {
        if (cart.isEmpty()) {
            XDialog.alert("Giỏ hàng trống!");
            return;
        }

        promotions = promotionDAO.findAll();

        try {
            for (Promotion promotion : promotions) {
                if (promotion.isStatus()) {
                    if (calculateTotal() >= 1_000_000) {
                        currentPromotion = promotion;
                        System.out.println(currentPromotion.getDiscount());
                        break;
                    }
                }
            }

            //Tạo Bill
            Bill bill = new Bill();
            bill.setStartDate(new Date());
            bill.setStatus(true);
            bill.setTotal(0d);
            bill.setUsername(XAuth.user.getUsername());
            bill.setPromotionId(currentPromotion.getPromotionId());
            bill.setCustomerId(currentCustomer.getCustomerId());
            billDAO.create(bill);
            currentBill = bill;

            if (bill.getBillId() == null) {
                XDialog.alert("Không tạo được hóa đơn, thử lại.");
                return;
            }

            double total = 0;

            // 2) Lưu từng BillDetail và cập nhật product DB
            for (BillDetail item : new ArrayList<>(cart)) {
                // set bill id
                item.setBillId(bill.getBillId());

                billDetailDAO.create(item);

                // update product stock in DB
                Product prodDb = productDAO.findById(item.getProductId());
                if (prodDb != null) {
                    prodDb.setQuantity(prodDb.getQuantity() - item.getQuantity());
                    if (prodDb.getQuantity() <= 0) {
                        prodDb.setQuantity(0);
                        prodDb.setStatus(false);
                    }
                    productDAO.update(prodDb);
                }

                total += item.getQuantity() * item.getUnitPrice();
            }

            // 3) Update bill total
            bill.setTotal(total - (currentPromotion.getDiscount() * total));
            billDAO.update(bill);

            XDialog.alert("Thanh toán thành công! Tổng: " + total + "Đ");

            // 4) Reset memory and UI. Reload products from DB
            cart.clear();
            products = new ArrayList<>(productDAO.findAll());
            fillBillDetail();
            fillToProduct();

        } catch (Exception ex) {
            ex.printStackTrace();
            XDialog.alert("Lỗi khi thanh toán: " + ex.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlProduct = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        rdoCate = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProduct = new javax.swing.JTable();
        pnlBillDetail = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblBillDetails = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        btnPay = new javax.swing.JButton();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("Sản phẩm");

        rdoCate.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Item 1", "Item 2", "Item 3", "Item 4"}));

        tblProduct.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null}
                },
                new String[]{
                        "Tên sản phẩm", "Số lượng còn lại", "Giá bán", "Trạng thái"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                    false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        tblProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblProduct);

        javax.swing.GroupLayout pnlProductLayout = new javax.swing.GroupLayout(pnlProduct);
        pnlProduct.setLayout(pnlProductLayout);
        pnlProductLayout.setHorizontalGroup(
                pnlProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlProductLayout.createSequentialGroup()
                                .addGroup(pnlProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(pnlProductLayout.createSequentialGroup()
                                                .addGap(259, 259, 259)
                                                .addComponent(jLabel2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(rdoCate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(pnlProductLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        pnlProductLayout.setVerticalGroup(
                pnlProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlProductLayout.createSequentialGroup()
                                .addContainerGap(18, Short.MAX_VALUE)
                                .addGroup(pnlProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(rdoCate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(77, 77, 77))
        );

        add(pnlProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 640, -1));

        jLabel3.setText("Hóa đơn");

        tblBillDetails.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null}
                },
                new String[]{
                        "Tên sản phẩm", "Đơn giá", "Số lượng", "Tổng tiền", "+", "-"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                    false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        tblBillDetails.setSurrendersFocusOnKeystroke(true);
        tblBillDetails.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBillDetailsMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblBillDetails);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("TỔNG: ");

        lblTotal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTotal.setText("0.0Đ");

        btnPay.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnPay.setText("Thanh toán");
        btnPay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPayActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlBillDetailLayout = new javax.swing.GroupLayout(pnlBillDetail);
        pnlBillDetail.setLayout(pnlBillDetailLayout);
        pnlBillDetailLayout.setHorizontalGroup(
                pnlBillDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBillDetailLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addGap(226, 226, 226))
                        .addGroup(pnlBillDetailLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(pnlBillDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(pnlBillDetailLayout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addGap(96, 96, 96)
                                                .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnPay)))
                                .addContainerGap(15, Short.MAX_VALUE))
        );
        pnlBillDetailLayout.setVerticalGroup(
                pnlBillDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlBillDetailLayout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlBillDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(lblTotal)
                                        .addComponent(btnPay))
                                .addContainerGap(15, Short.MAX_VALUE))
        );

        add(pnlBillDetail, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 10, 500, 470));
    }// </editor-fold>//GEN-END:initComponents

    private void tblProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductMouseClicked
        if (evt.getClickCount() == 2) {
            this.order();
        }
    }//GEN-LAST:event_tblProductMouseClicked

    private void tblBillDetailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBillDetailsMouseClicked
        int row = tblBillDetails.getSelectedRow();
        int col = tblBillDetails.getSelectedColumn();
        handleQuantityButtons(row, col);
    }//GEN-LAST:event_tblBillDetailsMouseClicked

    private void btnPayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPayActionPerformed
        CustomerJDialog dialog = new CustomerJDialog(null, true);
        dialog.open();
        dialog.setVisible(true);
        setCustomer(dialog.getCustomer());

        if(!dialog.isConfirm()) {
            return;
        }
        checkout();
    }//GEN-LAST:event_btnPayActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPay;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JPanel pnlBillDetail;
    private javax.swing.JPanel pnlProduct;
    private javax.swing.JComboBox<String> rdoCate;
    private javax.swing.JTable tblBillDetails;
    private javax.swing.JTable tblProduct;
    // End of variables declaration//GEN-END:variables
}
