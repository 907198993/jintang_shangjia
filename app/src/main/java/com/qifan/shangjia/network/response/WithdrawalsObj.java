package com.qifan.shangjia.network.response;

/**
 * Created by Administrator on 2018/5/14 0014.
 */

public class WithdrawalsObj {


        /**
         * result : 1
         * account : 13541108951
         * usable : 300
         * realName : 张三
         */

        private String balance;
        private String auditBalance;

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getAuditBalance() {
        return auditBalance;
    }

    public void setAuditBalance(String auditBalance) {
        this.auditBalance = auditBalance;
    }
}
