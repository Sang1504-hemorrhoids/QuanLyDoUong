package quanly.douong.dao.impl;

import quanly.douong.dao.BillDetailDAO;
import quanly.douong.entity.BillDetail;
import quanly.douong.util.XJdbc;
import quanly.douong.util.XQuery;

import java.util.List;

public class BillDetailDAOImpl implements BillDetailDAO {
    String createSql = "INSERT INTO BillDetails (Quantity, UnitPrice, BillId, ProductId) VALUES (?, ?, ?, ?)";
    String findAllSql = "SELECT * FROM BillDetails";

    @Override
    public BillDetail create(BillDetail entity) {
        Object[] values = {
                entity.getQuantity(),
                entity.getUnitPrice(),
                entity.getBillId(),
                entity.getProductId(),
        };
        XJdbc.executeUpdate(createSql, values);
        return entity;
    }

    @Override
    public void update(BillDetail entity) {

    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public List<BillDetail> findAll() {
        return XQuery.getBeanList(BillDetail.class, findAllSql);
    }

    @Override
    public BillDetail findById(String s) {
        return null;
    }
}
