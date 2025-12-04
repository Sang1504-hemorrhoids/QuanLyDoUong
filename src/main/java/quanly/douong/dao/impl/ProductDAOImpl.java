package quanly.douong.dao.impl;

import java.util.List;
import quanly.douong.dao.ProductDAO;
import quanly.douong.entity.Product;
import quanly.douong.util.XQuery;

public class ProductDAOImpl implements ProductDAO{
    String createSql = "INSERT INTO Product() VALUES()";
    String findAllSql = "SELECT * FROM Products ";
    String findByIdSql = "";

    @Override
    public Product create(Product entity) {
        
        return entity;
    }

    @Override
    public void update(Product entity) {
    
    }

    @Override
    public void deleteById(String id) {
    
    }

    @Override
    public List<Product> findAll() {
        return XQuery.getBeanList(Product.class, findAllSql);
    }

    @Override
    public Product findById(String id) {
        return XQuery.getSingleBean(Product.class, findByIdSql, id);
    }
    
}
