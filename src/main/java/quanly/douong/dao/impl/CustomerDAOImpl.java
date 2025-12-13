package quanly.douong.dao.impl;

import quanly.douong.dao.CustomerDAO;
import quanly.douong.entity.Customer;
import quanly.douong.util.XJdbc;
import quanly.douong.util.XQuery;

import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    String createSql = "INSERT INTO Customers (CustomerName, PhoneNumber, Address, Email) VALUES(?, ?, ?, ?)";
    String findByPhoneNumberSql = "SELECT * FROM Customers WHERE PhoneNumber = ?";

    @Override
    public Customer create(Customer entity) {
        Object[] values = {
                entity.getCustomerName(),
                entity.getPhoneNumber(),
                entity.getAddress(),
                entity.getEmail()
        };
//        XJdbc.executeUpdate(createSql, values);

        int generatedId = XJdbc.executeInsertAndGetId(createSql, values);
        entity.setCustomerId(generatedId);
        return entity;
    }

    @Override
    public void update(Customer entity) {

    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public List<Customer> findAll() {
        return List.of();
    }

    @Override
    public Customer findById(String s) {
        return null;
    }

    @Override
    public Customer findByPhoneNumber(Integer phone) {
        return XQuery.getSingleBean(Customer.class, findByPhoneNumberSql, phone);
    }
}
