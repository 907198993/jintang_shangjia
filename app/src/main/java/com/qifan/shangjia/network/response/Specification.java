package com.qifan.shangjia.network.response;

/**
 * Created by Administrator on 2018/7/7 0007.
 */
//规格
public class Specification {

    private  String specificationName;
    private  String price;
    private  String images;
    private  String stock;

    public String getSpecificationName() {
        return specificationName;
    }

    public void setSpecificationName(String specificationName) {
        this.specificationName = specificationName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }
}
