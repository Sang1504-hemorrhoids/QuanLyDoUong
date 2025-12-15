package quanly.douong.dao.impl;

import quanly.douong.dao.CategoryDAO;
import quanly.douong.entity.Category;
import quanly.douong.util.XQuery;

import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {
    String findAllSql = "SELECT * FROM Categories";

    @Override
    public Category create(Category entity) {
        return null;
    }

    @Override
    public void update(Category entity) {

    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public List<Category> findAll() {
        return XQuery.getBeanList(Category.class, findAllSql);
    }

    @Override
    public Category findById(String s) {
        return null;
    }
}
