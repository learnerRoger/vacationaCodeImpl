package com.example.service;

import com.example.dao.AdminDao;
import com.example.entity.Admin;

public class AdminService {
    private AdminDao adminDao = new AdminDao();
    public Admin login(Admin admin){
        return adminDao.queryByUserNameAndPwd(admin);
    }
}
