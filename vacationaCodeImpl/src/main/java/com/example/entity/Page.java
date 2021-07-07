package com.example.entity;

import java.util.List;

public class Page<C> {
    private int pageIndex;
    private int pageSize;
    private long totalRowCount;

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public long getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(long totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public List<C> getData() {
        return data;
    }

    public void setData(List<C> data) {
        this.data = data;
    }

    private long totalPageCount;
    private List<C> data;


    public Page(int pageIndex,int pageSize,long totalRowCount,List<C> data){
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.totalRowCount = totalRowCount;
        this.data = data;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageInsex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalRowCount() {
        return totalRowCount;
    }

    public void setTotalRowCount(long totalRowCount) {
        this.totalRowCount = totalRowCount;
    }
}
