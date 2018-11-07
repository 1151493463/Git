package utils;

import java.util.List;

public class Page<T> {

    private int currentPage;//当前页

    private int pageSize;//每页的记录数

    private int start;

    private int totalCount;//总记录数

    private int totalPage;//总页数

    private List<T> data;//当前页的数据

    private String key;

    private String value;

    public Page() {
    }

    public Page(int currentPage, int pageSize, String key, String value) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.key = key;
        this.value = value;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {

        return (int) Math.ceil(this.totalCount/pageSize+this.totalCount%pageSize*0.1);
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
