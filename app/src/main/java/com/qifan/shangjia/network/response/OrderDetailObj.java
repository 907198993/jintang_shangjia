package com.qifan.shangjia.network.response;

/**
 * Created by Administrator on 2018/7/3 0003.
 */

public class OrderDetailObj {
        /**
         * id : 1141
         * order_no : DS180612152743943285
         * goods_id : 435
         * goods_name : 好看哦
         * goods_images : /upload/201806/01/201806011452042485.png
         * goods_specification : 34
         * goods_specification_id : 2262
         * goods_unitprice : 0.01
         * goods_number : 1
         */

        private int id;
        private String order_no;
        private int goods_id;
        private String goods_name;
        private String goods_images;
        private String goods_specification;
        private int goods_specification_id;
        private double goods_unitprice;
        private int goods_number;
        private int sales_volume;
        private double original_price;
        private double price;
       private String goods_image;

    public String getGoods_image() {
        return goods_image;
    }

    public void setGoods_image(String goods_image) {
        this.goods_image = goods_image;
    }

    public int getSales_volume() {
        return sales_volume;
    }

    public void setSales_volume(int sales_volume) {
        this.sales_volume = sales_volume;
    }

    public double getOriginal_price() {
        return original_price;
    }

    public void setOriginal_price(double original_price) {
        this.original_price = original_price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

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

        public int getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(int goods_id) {
            this.goods_id = goods_id;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getGoods_images() {
            return goods_images;
        }

        public void setGoods_images(String goods_images) {
            this.goods_images = goods_images;
        }

        public String getGoods_specification() {
            return goods_specification;
        }

        public void setGoods_specification(String goods_specification) {
            this.goods_specification = goods_specification;
        }

        public int getGoods_specification_id() {
            return goods_specification_id;
        }

        public void setGoods_specification_id(int goods_specification_id) {
            this.goods_specification_id = goods_specification_id;
        }

        public double getGoods_unitprice() {
            return goods_unitprice;
        }

        public void setGoods_unitprice(double goods_unitprice) {
            this.goods_unitprice = goods_unitprice;
        }

        public int getGoods_number() {
            return goods_number;
        }

        public void setGoods_number(int goods_number) {
            this.goods_number = goods_number;
        }

}
