package com.qifan.shangjia.network.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2018/7/4 0004.
 */

public class CategoryObj implements Parcelable {

        private String   typeName;
        private String    sort;
        private String    id;

    protected CategoryObj(Parcel in) {
        typeName = in.readString();
        sort = in.readString();
        id = in.readString();
    }

    public static final Creator<CategoryObj> CREATOR = new Creator<CategoryObj>() {
        @Override
        public CategoryObj createFromParcel(Parcel in) {
            return new CategoryObj(in);
        }

        @Override
        public CategoryObj[] newArray(int size) {
            return new CategoryObj[size];
        }
    };

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(typeName);
        dest.writeString(sort);
        dest.writeString(id);
    }
}
