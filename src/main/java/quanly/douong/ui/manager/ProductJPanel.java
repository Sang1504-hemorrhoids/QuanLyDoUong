package quanly.douong.ui.manager;

import java.util.Date;
import java.util.List;
import javax.swing.table.DefaultTableModel;

import quanly.douong.dao.BillDAO;
import quanly.douong.dao.ProductDAO;
import quanly.douong.dao.impl.BillDAOImpl;
import quanly.douong.dao.impl.BillDetailDAOImpl;
import quanly.douong.dao.impl.ProductDAOImpl;
import quanly.douong.entity.Bill;
import quanly.douong.entity.BillDetail;
import quanly.douong.entity.Product;
import quanly.douong.util.XAuth;
import quanly.douong.util.XDialog;
import quanly.douong.util.XQuery;

public class ProductJPanel extends javax.swing.JPanel {
    ProductDAO productDAO = new ProductDAOImpl();
    List<Product> products = List.of();
    List<Bill> bills = List.of();
    BillDAO billDAO = new BillDAOImpl();
    Bill currentBill;

    public ProductJPanel() {
        initComponents();
        fillToProduct();
    }
    
    public void fillToProduct() {
        DefaultTableModel model = (DefaultTableModel) tblProduct.getModel();
        model.setRowCount(0);

        products = productDAO.findAll();
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
    
    public void fillBillDetail() {
        DefaultTableModel model = (DefaultTableModel) tbl.getModel();
        model.setRowCount(0);
    }
    
    public void order() {
        Product entity = products.get(tblProduct.getSelectedRow());
        newBill();
        if(!entity.isStatus()) {
            XDialog.alert("Hiện đã hết hàng!");
        } else {
            String quantityStr = XDialog.prompt("Số lượng?");
            if(quantityStr == null || quantityStr.isBlank()) {
                return;
            } else{
                int quantity = Integer.parseInt(quantityStr);
                BillDetail detail = new BillDetail();
                detail.setProductId(entity.getProductId());
                detail.setQuantity(quantity);
                detail.setUnitPrice(entity.getCostPrice());
                detail.setBillId(currentBill.getBillId());
                detail.setProductId(entity.getProductId());
                new BillDetailDAOImpl().create(detail);

                /* chưa add mã khuyến mãi
                chưa gộp số lượng nếu cùng loại nước
                 */
                double total = currentBill.getTotal() + quantity * entity.getCostPrice();
                currentBill.setTotal(total);
                billDAO.update(currentBill);
                XDialog.alert("Đã thêm vào đơn hàng thành công!");
            }
        }
    }

    /*
     Tạo bill mới (kiểm tra nếu query chưa có 1 hóa đơn nào)
     bill = null -> BillId + 1
     bill != null -> (last BillId) + 1
     */
    public void newBill() {
        if(currentBill == null) {
            currentBill = new Bill();
            currentBill.setTotal(0d);
            currentBill.setStartDate(new Date());
            currentBill.setStatus(false);
            currentBill.setUsername(XAuth.user.getUsername());
            currentBill.setPromotionId("KM01");
            currentBill.setCustomerId(1);
            billDAO.create(currentBill);

            String findLastIdSql = "SELECT TOP 1 BillId FROM Bills ORDER BY BillId DESC";
            Bill bill = XQuery.getSingleBean(Bill.class, findLastIdSql);

            Long newId = bill.getBillId();
            currentBill.setBillId(newId);
            System.out.println(currentBill.getBillId());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabs = new javax.swing.JTabbedPane();
        pnlProduct = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        rdoCate = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProduct = new javax.swing.JTable();
        pnlBillDetail = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        pnl = new javax.swing.JPanel();
        header = new javax.swing.JPanel();
        btnPay = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl = new javax.swing.JTable();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("Sản phẩm");

        rdoCate.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        tblProduct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Tên sản phẩm", "Số lượng còn lại", "Giá bán", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblProduct);

        jLabel3.setText("Hóa đơn");

        javax.swing.GroupLayout pnlBillDetailLayout = new javax.swing.GroupLayout(pnlBillDetail);
        pnlBillDetail.setLayout(pnlBillDetailLayout);
        pnlBillDetailLayout.setHorizontalGroup(
            pnlBillDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBillDetailLayout.createSequentialGroup()
                .addContainerGap(249, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(188, 188, 188))
        );
        pnlBillDetailLayout.setVerticalGroup(
            pnlBillDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBillDetailLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlProductLayout = new javax.swing.GroupLayout(pnlProduct);
        pnlProduct.setLayout(pnlProductLayout);
        pnlProductLayout.setHorizontalGroup(
            pnlProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProductLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlProductLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(278, 278, 278)
                        .addComponent(rdoCate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 656, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(pnlBillDetail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(7, Short.MAX_VALUE))
        );
        pnlProductLayout.setVerticalGroup(
            pnlProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProductLayout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(pnlProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoCate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlProductLayout.createSequentialGroup()
                .addComponent(pnlBillDetail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabs.addTab("Sản phẩm", pnlProduct);

        pnl.setLayout(new java.awt.BorderLayout());

        btnPay.setText("Thanh toán");

        jLabel1.setText("Hóa đơn");

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerLayout.createSequentialGroup()
                .addContainerGap(432, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(343, 343, 343)
                .addComponent(btnPay, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerLayout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPay, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap())
        );

        pnl.add(header, java.awt.BorderLayout.LINE_END);

        tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Tên sản phẩm", "Tổng tiền", "Thanh toán"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tbl);

        pnl.add(jScrollPane2, java.awt.BorderLayout.PAGE_END);

        tabs.addTab("Hóa đơn", pnl);

        add(tabs, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1170, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void tblProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductMouseClicked
        if(evt.getClickCount() == 2) {
            this.order();
        }
    }//GEN-LAST:event_tblProductMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPay;
    private javax.swing.JPanel header;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel pnl;
    private javax.swing.JPanel pnlBillDetail;
    private javax.swing.JPanel pnlProduct;
    private javax.swing.JComboBox<String> rdoCate;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tbl;
    private javax.swing.JTable tblProduct;
    // End of variables declaration//GEN-END:variables
}
