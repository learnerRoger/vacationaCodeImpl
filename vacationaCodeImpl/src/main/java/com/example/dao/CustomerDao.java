package com.example.dao;
import com.example.entity.Customer;
import com.example.entity.Page;

import java.util.List;

public class CustomerDao extends BaseDao<Customer>{
    public CustomerDao() {
        super();
    }

    public static void main(String[] args) {
        CustomerDao customerDao = new CustomerDao();
//        Customer customer = new Customer(19201211,"llq","男",19,"189283","1","1");
//            customerDao.queryAll();
//            customerDao.insert(customer);
//          update();
//          delete();

        Page<Customer> result = customerDao.queryByPage(2,5);
        System.out.println("pageIndex " + result.getPageIndex());
        System.out.println(result.getPageSize());
        System.out.println(result.getTotalRowCount());
        System.out.println("totalPageCount " + result.getTotalPageCount());
        List<Customer> customers = result.getData();
        for (Customer customer : customers){
            System.out.println(customer.getPatientNo());
        }

    }
    public Customer query(int id){
        String sql = "select * from patient where Psex like ?";
        Customer customer = queryBean(sql,id);
//        System.out.println("id: " + customer.getPatientNo() + " age " + customer.getPage());
        return customer;
    }

    public List<Customer> queryAll(){
        String sql = "select * from patient";
        List<Customer> customers = queryBeanList(sql);
//        for (Customer customer : customers){
//            System.out.println("id: " + customer.getPatientNo() + " age " + customer.getPage());
//            System.out.println();
//        }
        return customers;
    }

    public int insert(Customer customer){

        String sql = "insert into patient value(?,?,?,?,?,?,?)";
        int affectNum = update(sql,
                    customer.getPatientNo(),
                    customer.getPname(),
                    customer.getPsex(),
                    customer.getPage(),
                    customer.getPtel(),
                    customer.getPkind(),
                    customer.getQueuenum()
                    );
//            System.out.println(affectNum);
        return affectNum;
    }

    public int update(Customer customer) {

        String sql = "update patient set Pname = ? , Psex = ? , Page = ? , Ptel  = ? , Pkind = ? ,Queuenum = ? where patient_no = ?";
        int affectNum = update(sql,
                customer.getPname(),
                customer.getPsex(),
                customer.getPage(),
                customer.getPtel(),
                customer.getPkind(),
                customer.getQueuenum(),
                customer.getPatientNo()
        );
//        System.out.println(affectNum);
        return affectNum;
    }

    public int delete (int pno) {
        String sql = "delete from patient where patient_no = ?";
        int affectNum = update(sql,pno);
//        System.out.println(affectNum);
        return affectNum;
    }

    public long queryTotalCount(){
        String sql = "select count(*) from patient";
        return (long) queryValue(sql);
    }

    public Page<Customer> queryByPage(int pageIndex,int pageSize){
        String sql = "select * from patient limit ?,?";
        int start = (pageIndex - 1) * pageSize;
        long totalRowCount = this.queryTotalCount();
        List<Customer> customers = queryBeanList(sql,start,pageSize);
        return new Page<Customer>(pageIndex,pageSize,totalRowCount,customers);
    }
}
