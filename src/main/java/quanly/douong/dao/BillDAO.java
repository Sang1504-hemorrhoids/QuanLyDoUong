package quanly.douong.dao;

import quanly.douong.entity.Bill;

import java.util.List;

public interface BillDAO extends CrudDAO<Bill, String>{
    List<Bill> findByUserName(String username);
}
