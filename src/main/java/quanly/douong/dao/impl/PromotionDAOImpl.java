package quanly.douong.dao.impl;

import quanly.douong.dao.PromotionDAO;
import quanly.douong.entity.Promotion;
import quanly.douong.util.XQuery;

import java.util.List;

public class PromotionDAOImpl implements PromotionDAO {
    String findAllSql = "SELECT * FROM Promotion";
    String findPromotionSql = "SELECT * FROM Promotion " +
            "WHERE Status = 1 AND GETDATE() BETWEEN StartDate AND EndDate " +
                                                            "AND ? >= MinOrderTotal ORDER BY Discount DESC";

    @Override
    public Promotion create(Promotion entity) {
        return null;
    }

    @Override
    public void update(Promotion entity) {

    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public List<Promotion> findAll() {
        return XQuery.getBeanList(Promotion.class, findAllSql);
    }

    @Override
    public Promotion findPromotion(double total) {
        return XQuery.getSingleBean(Promotion.class, findPromotionSql, total);
    }

    @Override
    public Promotion findById(String s) {
        return null;
    }
}
