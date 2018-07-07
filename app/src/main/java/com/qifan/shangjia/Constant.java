package com.qifan.shangjia;

/**
 * Created by Administrator on 2017/7/13.
 */

public class Constant {

    public static final String url="http://47.104.102.17:8001";
    public static final String title="title";
//    public static final String url="url";
    public static final String YHXY ="YHXY";
    public static final String CJWT = "CJWT";
    public static final String GYWM = "GYWM";
    public static final String vouchersType_0="0";
    public static final String vouchersType_1="1";
    public static final String vouchersType_2="2";
    public static final String update_name="update_name";
    public static final String update_phone="update_phone";
    public static final String type="type";
    //类别(0全部 1待付款 2待发货 3待收货 4待评价   // 5已完成 6已取消)
    public static final int type_0=0;
    public static final int type_1=1;
    public static final int type_2=2;
    public static final int type_3=3;
    public static final int type_4=4;
    public static final int type_5=5;
    public static final int type_6=6;
    public static final String orderNo="orderNo";

    public static class RCode{
        public static final int register=100;
        public static final int update_name=200;
        public static final int update_phone=300;
        public static final int login=400;
        public static final int addTool=1000;
        public static final int addHomework=10001;
        public static final int deleteDefaultAccount=200;
        public static final int tiXianSuccess=2200;
    }
    public static class IParam{
        public static final String imgList="imgList";
        public static final String imgIndex="imgIndex";
        public static final String tiXianType="tiXianType";
        public static final String lookEvaluate="lookEvaluate";
        public static final String evaluateId="evaluateId";
        public static final String select_voucher="select_voucher";
        public static final String phone="phone";
        public static final String name="name";
        public static final String user_id="user_id";
        public static final String msgId="msgId";
        public static final String title="title";
        public static final String code="code";
        public static final String editCategory="editCategory";
        public static final String Category="Category";
        public static final String accountId="accountId";
        public static final String account="account";
        public static final String questionContent="questionContent";
        public static final String orderNo="orderNo";
        public static final String address="address";
        public static final String questionId="questionId";
        public static final String goodsId="goodsId";
        public static final String storeId="storeId";
        public static final String typeId="typeId";
        public static final String typeName="typeName";
        public static final String city="city";
        public static final String tuiHuoDetail="tuiHuoDetail";
        public static final String guiZeContent="guiZeContent";
        public static final String guiZeType="guiZeType";
        public static final String type="type";
        public static final int guiZeType_0=0;//签到规则
        public static final int guiZeType_1=1;//养生豆规则
    }

}
