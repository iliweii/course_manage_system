package xyz.honghong520.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.honghong520.dao.AdminDao;
import xyz.honghong520.entity.Admin;
import xyz.honghong520.service.AdminService;


@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;

    @Override
    public Admin login(Admin admin) {
        return this.adminDao.login(admin);
    }

}
