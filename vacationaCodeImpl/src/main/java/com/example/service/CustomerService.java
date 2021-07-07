package com.example.service;
import com.example.entity.Customer;
import com.example.dao.CustomerDao;
import com.example.entity.Page;

import java.util.List;

public class CustomerService {
    private CustomerDao customerDao = new CustomerDao();

    public CustomerService() {
        super();
    }

    public Customer findById(int id){
        return customerDao.query(id);
    }

    public List<Customer> findAll(){
        return customerDao.queryAll();
    }

    public Page<Customer> findListByPage(int pageIndex,int pageSize){
        return customerDao.queryByPage(pageIndex,pageSize);
    }

    public int save(Customer customer){
        return customerDao.insert(customer);
    }

    public int modify(Customer customer){
        return customerDao.update(customer);
    }

    public int remove(int id){
        return customerDao.delete(id);
    }
}
