package quanly.douong.dao.impl;

import java.util.List;
import quanly.douong.dao.ProductDAO;
import quanly.douong.entity.Product;
import quanly.douong.util.XJdbc;
import quanly.douong.util.XQuery;

public class ProductDAOImpl implements ProductDAO{
    String createSql = "INSERT INTO Product() VALUES()";
    String updateSql = "UPDATE Products SET Name = ?, Quantity = ?, CostPrice = ?, Status = ?, CategoryId = ? WHERE ProductId = ?";
    String deleteSql = "DELETE FROM Products WHERE ProductId = ?";
    String findAllSql = "SELECT * FROM Products ";
    String findByIdSql = "SELECT * FROM Products WHERE ProductId = ?";

    @Override
    public Product create(Product entity) {
        
        return entity;
    }

    @Override
    public void update(Product entity) {
        Object[] values = {
          entity.getName(),
          entity.getQuantity(),
          entity.getCostPrice(),
          entity.isStatus(),
          entity.getCategoryId(),
          entity.getProductId()
        };
        XJdbc.executeUpdate(updateSql, values);
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
