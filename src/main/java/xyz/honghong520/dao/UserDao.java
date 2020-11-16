package xyz.honghong520.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import xyz.honghong520.entity.User;

@Repository
public interface UserDao {

    // 登录方法
    User login(User tbuser);

    // 通过用户id查询用户信息
    User queryUserById(Integer tbid);

    // 查询所有用户信息
    List<User> queryAll();

    // 通过用户登录名查询用户信息
    User queryUserByUser(String tbuser);

    // 添加用户
    int insertUser(User tbuser);

    // 批量添加用户
    int insertUsersBatch(List<User> user);

    // 修改用户信息
    int updateUser(User tbuser);

    // 删除用户
    int deleteUser(Integer tbid);

    // 批量删除用户
    int deleteUsersBatch(List<User> user);

    // 通过关键词模糊搜索用户登录名
    List<User> searchByKeyword(String q);

}
