package quanly.douong.dao;

import quanly.douong.entity.Product;

import java.util.List;

public interface ProductDAO extends CrudDAO<Product, String>{
    List<Product> findProductByCategory(String categoryId);
}
