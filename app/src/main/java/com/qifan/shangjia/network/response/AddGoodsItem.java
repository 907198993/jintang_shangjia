package com.qifan.shangjia.network.response;

import java.util.List;

/**
 * Created by Administrator on 2018/7/9 0009.
 */

public class AddGoodsItem {


    /**
     * goodsTypeParent : 37
     * goodsTypeSon : 38
     * storeType : 67
     * is_homeRecommend : 0
     * goodsStatus : 1
     * is_homeRecommend_num : 99
     * goodsName : 麦卢卡咖啡磨砂膏2
     * goods_image : /upload/201806/19/201806191751198651.jpg
     * zhutu_image : /upload/201806/19/201806191751264341.jpg|/upload/201806/20/201806201428157757.jpg|/upload/201806/20/201806201428154730.jpg|/upload/201806/20/201806201428162923.jpg|/upload/201806/20/201806201428359766.jpg|/upload/201806/20/201806201428363144.jpg|
     * xiangqing_image : /upload/201806/20/201806201428484631.jpg
     * specification : [{"specificationName":"220g（蜂蜜）","price":118,"images":"/upload/201806/20/201806201429173860.jpg","stock":100},{"specificationName":"220g（椰子）","price":118,"images":"/upload/201806/20/201806201429509981.jpg","stock":100},{"specificationName":"220g（柑橘）","price":118,"images":"/upload/201806/20/201806201430226455.jpg","stock":100}]
     * property_title : 品牌
     * property_content : Bean Body
     * original_price : 118
     * discount : 95
     * orderBy : 99
     */

    private String goodsTypeParent;
    private String goodsTypeSon;
    private String storeType;
    private String is_homeRecommend;
    private String goodsStatus;
    private String is_homeRecommend_num;
    private String goodsName;
    private String goods_image;
    private String zhutu_image;
    private String xiangqing_image;
    private String property_title;
    private String property_content;
    private String original_price;
    private String discount;
    private String orderBy;
    private List<Specification> specification;


    public String getGoodsTypeParent() {
        return goodsTypeParent;
    }

    public void setGoodsTypeParent(String goodsTypeParent) {
        this.goodsTypeParent = goodsTypeParent;
    }

    public String getGoodsTypeSon() {
        return goodsTypeSon;
    }

    public void setGoodsTypeSon(String goodsTypeSon) {
        this.goodsTypeSon = goodsTypeSon;
    }

    public String getStoreType() {
        return storeType;
    }

    public void setStoreType(String storeType) {
        this.storeType = storeType;
    }

    public String getIs_homeRecommend() {
        return is_homeRecommend;
    }

    public void setIs_homeRecommend(String is_homeRecommend) {
        this.is_homeRecommend = is_homeRecommend;
    }

    public String getGoodsStatus() {
        return goodsStatus;
    }

    public void setGoodsStatus(String goodsStatus) {
        this.goodsStatus = goodsStatus;
    }

    public String getIs_homeRecommend_num() {
        return is_homeRecommend_num;
    }

    public void setIs_homeRecommend_num(String is_homeRecommend_num) {
        this.is_homeRecommend_num = is_homeRecommend_num;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoods_image() {
        return goods_image;
    }

    public void setGoods_image(String goods_image) {
        this.goods_image = goods_image;
    }

    public String getZhutu_image() {
        return zhutu_image;
    }

    public void setZhutu_image(String zhutu_image) {
        this.zhutu_image = zhutu_image;
    }

    public String getXiangqing_image() {
        return xiangqing_image;
    }

    public void setXiangqing_image(String xiangqing_image) {
        this.xiangqing_image = xiangqing_image;
    }

    public String getProperty_title() {
        return property_title;
    }

    public void setProperty_title(String property_title) {
        this.property_title = property_title;
    }

    public String getProperty_content() {
        return property_content;
    }

    public void setProperty_content(String property_content) {
        this.property_content = property_content;
    }

    public String getOriginal_price() {
        return original_price;
    }

    public void setOriginal_price(String original_price) {
        this.original_price = original_price;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public List<Specification> getSpecification() {
        return specification;
    }

    public void setSpecification(List<Specification> specification) {
        this.specification = specification;
    }
}
