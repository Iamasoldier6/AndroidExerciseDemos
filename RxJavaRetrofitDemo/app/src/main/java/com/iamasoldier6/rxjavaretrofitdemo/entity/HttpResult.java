package com.iamasoldier6.rxjavaretrofitdemo.entity;

/**
 * @author: Iamasoldier6
 * @date: 06/12/2016
 */

public class HttpResult<T> {

    private int count;
    private int start;
    private int total;
    private String title;

    // 模仿 Data
    private T subjects;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public T getSubjects() {
        return subjects;
    }

    public void setSubjects(T subjects) {
        this.subjects = subjects;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {

        StringBuffer sb = new StringBuffer();
        sb.append("title = " + title + "count = " + count + "start = " + start);
        if (subjects != null) {
            sb.append("subjects: " + subjects.toString());
        }
        return sb.toString();
    }
}
