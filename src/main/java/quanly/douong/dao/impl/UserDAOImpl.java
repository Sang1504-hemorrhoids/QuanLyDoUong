package quanly.douong.dao.impl;

import quanly.douong.dao.UserDAO;
import quanly.douong.entity.User;
import quanly.douong.util.XJdbc;
import quanly.douong.util.XQuery;

import java.util.List;

public class UserDAOImpl implements UserDAO {
    String createSql = "INSERT INTO Users (Username, Password, Fullname, Role, Status) VALUES (?, ?, ?, ?, ?)";
    String findAllSql = "SELECT * FROM Users";
    String findByIdSql = "SELECT * FROM Users WHERE Id = ?";

    @Override
    public User create(User entity) {
        Object[] values = {
                entity.getUsername(),
                entity.getPassword(),
                entity.getFullName(),
                entity.isRole(),
                entity.isStatus()
        };
        XJdbc.executeUpdate(createSql, values);
        return entity;
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public List<User> findAll() {
        return XQuery.getBeanList(User.class, findAllSql);
    }

    @Override
    public User findById(String s) {
        return null;
    }
}
