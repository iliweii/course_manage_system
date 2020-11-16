package xyz.honghong520.dao;

import org.springframework.stereotype.Repository;
import xyz.honghong520.entity.Admin;

@Repository
public interface AdminDao {

	// 登录方法
	Admin login(Admin admin);
}