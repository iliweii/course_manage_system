package xyz.honghong520.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.honghong520.dao.UserDao;
import xyz.honghong520.entity.User;
import xyz.honghong520.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User login(User user) {
        return this.userDao.login(user);
    }

    @Override
    public User queryUserById(Integer tbid) {
        return this.userDao.queryUserById(tbid);
    }

    @Override
    public List<User> queryAll() {
        return this.userDao.queryAll();
    }

    @Override
    public User queryUserByUser(String tbuser) {
        return this.userDao.queryUserByUser(tbuser);
    }

    @Override
    public int updateUser(User user) {
        return this.userDao.updateUser(user);
    }

    @Override
    public int deleteUser(Integer tbid) {
        return this.userDao.deleteUser(tbid);
    }

    @Override
    public int deleteUsersBatch(List<User> user) {
        return this.userDao.deleteUsersBatch(user);
    }

    @Override
    public int insertUser(User tbuser) {
        return this.userDao.insertUser(tbuser);
    }

    @Override
    public int insertUsersBatch(List<User> user) {
        return this.userDao.insertUsersBatch(user);
    }

    @Override
    public List<User> searchByKeyword(String q) {
        return this.userDao.searchByKeyword(q);
    }
}
