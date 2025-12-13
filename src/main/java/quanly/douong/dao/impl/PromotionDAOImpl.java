package quanly.douong.dao.impl;

import quanly.douong.dao.PromotionDAO;
import quanly.douong.entity.Promotion;
import quanly.douong.util.XQuery;

import java.util.List;

public class PromotionDAOImpl implements PromotionDAO {
    String findAllSql = "SELECT * FROM Promotion";

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
    public Promotion findById(String s) {
        return null;
    }
}
