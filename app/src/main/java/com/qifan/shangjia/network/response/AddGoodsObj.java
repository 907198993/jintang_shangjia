package com.qifan.shangjia.network.response;

import java.util.List;

/**
 * Created by Administrator on 2018/7/5 0005.
 */

public class AddGoodsObj {

        private List<GoodsTypesBean> goodsTypes;
        private List<StoreTypeBean> storeType;

        public List<GoodsTypesBean> getGoodsTypes() {
            return goodsTypes;
        }

        public void setGoodsTypes(List<GoodsTypesBean> goodsTypes) {
            this.goodsTypes = goodsTypes;
        }

        public List<StoreTypeBean> getStoreType() {
            return storeType;
        }

        public void setStoreType(List<StoreTypeBean> storeType) {
            this.storeType = storeType;
        }

        public static class GoodsTypesBean {
            /**
             * sonType : [{"goods_type_id":0,"goods_type_name":"所有分类","goods_type_img":null,"sort":null,"goods_type_parentId":0,"goods_type_parentName":null,"goods_type_Level":null}]
             * goods_type_id : 0
             * goods_type_name : 所有分类
             * goods_type_img : null
             * sort : null
             * goods_type_parentId : 0
             * goods_type_parentName : null
             * goods_type_Level : null
             */

            private int goods_type_id;
            private String goods_type_name;
            private Object goods_type_img;
            private Object sort;
            private int goods_type_parentId;
            private Object goods_type_parentName;
            private Object goods_type_Level;
            private List<SonTypeBean> sonType;

            public int getGoods_type_id() {
                return goods_type_id;
            }

            public void setGoods_type_id(int goods_type_id) {
                this.goods_type_id = goods_type_id;
            }

            public String getGoods_type_name() {
                return goods_type_name;
            }

            public void setGoods_type_name(String goods_type_name) {
                this.goods_type_name = goods_type_name;
            }

            public Object getGoods_type_img() {
                return goods_type_img;
            }

            public void setGoods_type_img(Object goods_type_img) {
                this.goods_type_img = goods_type_img;
            }

            public Object getSort() {
                return sort;
            }

            public void setSort(Object sort) {
                this.sort = sort;
            }

            public int getGoods_type_parentId() {
                return goods_type_parentId;
            }

            public void setGoods_type_parentId(int goods_type_parentId) {
                this.goods_type_parentId = goods_type_parentId;
            }

            public Object getGoods_type_parentName() {
                return goods_type_parentName;
            }

            public void setGoods_type_parentName(Object goods_type_parentName) {
                this.goods_type_parentName = goods_type_parentName;
            }

            public Object getGoods_type_Level() {
                return goods_type_Level;
            }

            public void setGoods_type_Level(Object goods_type_Level) {
                this.goods_type_Level = goods_type_Level;
            }

            public List<SonTypeBean> getSonType() {
                return sonType;
            }

            public void setSonType(List<SonTypeBean> sonType) {
                this.sonType = sonType;
            }

            public static class SonTypeBean {
                /**
                 * goods_type_id : 0
                 * goods_type_name : 所有分类
                 * goods_type_img : null
                 * sort : null
                 * goods_type_parentId : 0
                 * goods_type_parentName : null
                 * goods_type_Level : null
                 */

                private int goods_type_id;
                private String goods_type_name;
                private Object goods_type_img;
                private Object sort;
                private int goods_type_parentId;
                private Object goods_type_parentName;
                private Object goods_type_Level;

                public int getGoods_type_id() {
                    return goods_type_id;
                }

                public void setGoods_type_id(int goods_type_id) {
                    this.goods_type_id = goods_type_id;
                }

                public String getGoods_type_name() {
                    return goods_type_name;
                }

                public void setGoods_type_name(String goods_type_name) {
                    this.goods_type_name = goods_type_name;
                }

                public Object getGoods_type_img() {
                    return goods_type_img;
                }

                public void setGoods_type_img(Object goods_type_img) {
                    this.goods_type_img = goods_type_img;
                }

                public Object getSort() {
                    return sort;
                }

                public void setSort(Object sort) {
                    this.sort = sort;
                }

                public int getGoods_type_parentId() {
                    return goods_type_parentId;
                }

                public void setGoods_type_parentId(int goods_type_parentId) {
                    this.goods_type_parentId = goods_type_parentId;
                }

                public Object getGoods_type_parentName() {
                    return goods_type_parentName;
                }

                public void setGoods_type_parentName(Object goods_type_parentName) {
                    this.goods_type_parentName = goods_type_parentName;
                }

                public Object getGoods_type_Level() {
                    return goods_type_Level;
                }

                public void setGoods_type_Level(Object goods_type_Level) {
                    this.goods_type_Level = goods_type_Level;
                }
            }
        }

        public static class StoreTypeBean {
            /**
             * id : 0
             * storeId : null
             * typeName : 所有分类
             * typeImg : null
             * sort : null
             * typeLevel : null
             * createUser : null
             * createDate : null
             */

            private int id;
            private Object storeId;
            private String typeName;
            private Object typeImg;
            private Object sort;
            private Object typeLevel;
            private Object createUser;
            private Object createDate;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public Object getStoreId() {
                return storeId;
            }

            public void setStoreId(Object storeId) {
                this.storeId = storeId;
            }

            public String getTypeName() {
                return typeName;
            }

            public void setTypeName(String typeName) {
                this.typeName = typeName;
            }

            public Object getTypeImg() {
                return typeImg;
            }

            public void setTypeImg(Object typeImg) {
                this.typeImg = typeImg;
            }

            public Object getSort() {
                return sort;
            }

            public void setSort(Object sort) {
                this.sort = sort;
            }

            public Object getTypeLevel() {
                return typeLevel;
            }

            public void setTypeLevel(Object typeLevel) {
                this.typeLevel = typeLevel;
            }

            public Object getCreateUser() {
                return createUser;
            }

            public void setCreateUser(Object createUser) {
                this.createUser = createUser;
            }

            public Object getCreateDate() {
                return createDate;
            }

            public void setCreateDate(Object createDate) {
                this.createDate = createDate;
            }
        }

}
