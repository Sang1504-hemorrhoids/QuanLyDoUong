package quanly.douong.dao.impl;

import quanly.douong.dao.UserDAO;
import quanly.douong.entity.User;
import quanly.douong.util.XJdbc;
import quanly.douong.util.XQuery;

import java.util.List;

public class UserDAOImpl implements UserDAO {
    String createSql = "INSERT INTO Users (Username, Password, FullName, CitizenNumber,Role, Status) " +
            "VALUES (?, ?, ?, ?, ?, ?)";
    String updateSql = "UPDATE USERS SET Password = ?, FullName = ?, CitizenNumber = ?, Role = ?, Status = ? " +
            "WHERE Username = ?";
    String deleteSql = "DELETE FROM USERS WHERE Username = ?";
    String findAllSql = "SELECT * FROM Users";
    String findByIdSql = "SELECT * FROM Users WHERE Username = ?";

    @Override
    public User create(User entity) {
        Object[] values = {
                entity.getUsername(),
                entity.getPassword(),
                entity.getFullname(),
                entity.getCitizenNumber(),
                entity.isRole(),
                entity.isStatus()
        };
        XJdbc.executeUpdate(createSql, values);
        return entity;
    }

    @Override
    public void update(User entity) {
        Object[] values = {
                entity.getPassword(),
                entity.getFullname(),
                entity.isRole(),
                entity.isStatus(),
                entity.getUsername()
        };
        XJdbc.executeUpdate(updateSql, values);
    }

    @Override
    public void deleteById(String username) {
        XJdbc.executeUpdate(deleteSql, username);
    }

    @Override
    public List<User> findAll() {
        return XQuery.getBeanList(User.class, findAllSql);
    }

    @Override
    public User findById(String username) {
        return XQuery.getSingleBean(User.class, findByIdSql, username);
    }
}
