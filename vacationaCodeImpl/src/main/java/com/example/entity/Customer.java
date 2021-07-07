package com.example.entity;

public class Customer {
    private int patientNo;
    private String Pname;
    private String Psex;
    private int Page;
    private String Ptel;
    private String Pkind;
    private String Queuenum;

    public Customer(){
        super();
    }

    public Customer(int patientNo,String Pname,String Psex,int Page,String Ptel,String Pkind,String Queuenum) {
        this.patientNo = patientNo;
        this.Pname = Pname;
        this.Psex = Psex;
        this.Page = Page;
        this.Ptel = Ptel;
        this.Pkind = Pkind;
        this.Queuenum  = Queuenum;
    }


    public int getPatientNo() {
        return patientNo;
    }

    public void setPatientNo(int patientNo) {
        this.patientNo = patientNo;
    }

    public String getPname() {
        return Pname;
    }

    public void setPname(String Pname) {
        this.Pname = Pname;
    }



    public String getPsex() {
        return Psex;
    }

    public void setPsex(String Psex) {
        this.Psex = Psex;
    }

    public int getPage() {
        return Page;
    }

    public void setPage(int Page) {
        this.Page = Page;
    }

    public String getPtel() {
        return Ptel;
    }

    public void setPtel(String Ptel) {
        this.Ptel = Ptel;
    }

    public String getPkind() {
        return Pkind;
    }

    public void setPkind(String Pkind) {
        this.Pkind = Pkind;
    }

    public String getQueuenum() {
        return Queuenum;
    }

    public void setQueuenum(String Queuenum) {
        this.Queuenum = Queuenum;
    }
}

