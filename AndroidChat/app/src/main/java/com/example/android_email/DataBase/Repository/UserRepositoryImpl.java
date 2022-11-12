package com.example.android_email.DataBase.Repository;

import com.example.android_email.DataBase.DAO.UserDAO;
import com.example.android_email.DataBase.Entity.Users;

import java.util.List;

public class UserRepositoryImpl implements UserRepository {

   private UserDAO dao;

    public UserRepositoryImpl(UserDAO dao) {
        this.dao = dao;
    }

    @Override
    public List<Users> getAll() {
        return dao.getAll();
    }

    @Override
    public List<Users> loadAllByIds(String userId) {
        return dao.loadAllByIds(userId);
    }

    @Override
    public Users findByUsername(String username) {
        return dao.findByUsername(username);
    }

    @Override
    public void insert(Users user) {
        dao.insert(user);
    }

    @Override
    public void delete(Users user) {
        dao.delete(user);
    }

    @Override
    public void update(Users user) {
        dao.update(user);
    }
}
