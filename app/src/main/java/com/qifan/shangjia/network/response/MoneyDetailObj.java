package com.qifan.shangjia.network.response;

/**
 * Created by Administrator on 2018/7/5 0005.
 */

public class MoneyDetailObj {


    /**
     * id : 1
     * order_no : 180529152405728037
     * account : 222
     * userid : 1
     * withdrawal : 500
     * createDate : 2018-05-29T15:24:11.183
     * status : 2
     * balance : 5000
     * completeDate : null
     * remark :
     * isDel : 0
     */

    private int id;
    private String order_no;
    private String account;
    private int userid;
    private String withdrawal;
    private String createDate;
    private int status;
    private int balance;
    private Object completeDate;
    private String remark;
    private int isDel;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getWithdrawal() {
        return withdrawal;
    }

    public void setWithdrawal(String withdrawal) {
        this.withdrawal = withdrawal;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public Object getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(Object completeDate) {
        this.completeDate = completeDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getIsDel() {
        return isDel;
    }

    public void setIsDel(int isDel) {
        this.isDel = isDel;
    }
}
