package quanly.douong.dao.impl;

import quanly.douong.dao.BillDAO;
import quanly.douong.entity.Bill;
import quanly.douong.util.XJdbc;
import quanly.douong.util.XQuery;

import java.util.List;

public class BillDAOImpl implements BillDAO {
    String createSql = "INSERT INTO Bills (Total, StartDate, Status, Username, PromotionId, CustomerId) " +
            "VALUES (?, ?, ?, ?, ?, ?)";
    String updateSql = "UPDATE Bills set Total = ?, Status = ? WHERE BillId = ?";
    String deleteSql = "DELETE FROM Bills Where BillId=?";
    String findAllSql = "SELECT * FROM Bills";
    String findByIdSql = "SELECT * FROM Bills WHERE BillId=?";
    String findByUsernameSql = "SELECT * FROM Bills WHERE Username=?";

    @Override
    public Bill create(Bill entity) {
        Object[] values = {
                entity.getTotal(),
                entity.getStartDate(),
                entity.isStatus(),
                entity.getUsername(),
                entity.getPromotionId(),
                entity.getCustomerId()
        };
//        XJdbc.executeUpdate(createSql, values);

        int generatedId = XJdbc.executeInsertAndGetId(createSql, values);
        entity.setBillId((long) generatedId);
        return entity;
    }

    @Override
    public void update(Bill entity) {
        Object[] values = {
                entity.getTotal(),
                entity.isStatus(),
                entity.getBillId()
        };
        XJdbc.executeUpdate(updateSql, values);
    }

    @Override
    public void deleteById(String id) {
        XJdbc.executeUpdate(deleteSql, id);
    }

    @Override
    public List<Bill> findAll() {
        return XQuery.getBeanList(Bill.class, findAllSql);
    }

    @Override
    public Bill findById(String s) {
        return XQuery.getSingleBean(Bill.class, findByIdSql, s);
    }

    @Override
    public List<Bill> findByUserName(String username) {
        return XQuery.getBeanList(Bill.class, findByUsernameSql, username);
    }
}
