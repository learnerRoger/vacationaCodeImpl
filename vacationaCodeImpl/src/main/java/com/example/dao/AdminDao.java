package com.example.dao;
import com.example.entity.Admin;


public class AdminDao extends BaseDao<Admin>{
    public Admin queryByUserNameAndPwd(Admin admin){
        String sql = "select * from p_admin where username = ? and password = ?";
        return queryBean(sql,admin.getUsername(),admin.getPassword());
    }
}
