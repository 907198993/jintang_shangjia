package com.qifan.shangjia.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.github.androidtools.PhoneUtils;
import com.github.baseclass.rx.IOCallBack;
import com.github.baseclass.view.pickerview.OptionsPopupWindow;
import com.github.customview.MyEditText;
import com.google.gson.Gson;
import com.qifan.shangjia.Constant;
import com.qifan.shangjia.GetSign;
import com.qifan.shangjia.R;
import com.qifan.shangjia.base.BaseActivity;
import com.qifan.shangjia.base.BaseObj;
import com.qifan.shangjia.base.MyCallBack;
import com.qifan.shangjia.network.ApiRequest;
import com.qifan.shangjia.network.response.CategoryObj;
import com.qifan.shangjia.network.response.ReturnObj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscriber;

/**
 * Created by administartor on 2017/9/2.
 */

public class AddGoodsCategoryActivity extends BaseActivity {
    @BindView(R.id.et_category_name)
    MyEditText et_category_name;
    @BindView(R.id.et_categoty_orderby)

    MyEditText et_categoty_orderby;

    private boolean isEdit;
    CategoryObj categoryObj;
    @Override
    protected int getContentView() {
        return R.layout.act_add_category;
    }

    @Override
    protected void initView() {
        isEdit = getIntent().getBooleanExtra(Constant.IParam.editCategory,false);
        if(isEdit){
            setAppTitle("编辑商品类型");
            categoryObj = (CategoryObj) getIntent().getParcelableExtra(Constant.IParam.Category);
            et_category_name.setText(categoryObj.getTypeName());
            et_categoty_orderby.setText(categoryObj.getSort());
        }else{
            setAppTitle("添加商品类型");
        }

    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.tv_editcategory_commit)
    public void onViewClick(View v) {
        switch (v.getId()){
            case R.id.tv_editcategory_commit:
                String categoryName = getSStr(et_category_name);
                String orderby = getSStr(et_categoty_orderby);
                if(TextUtils.isEmpty(categoryName)){
                    showMsg("类型不能为空");
                    return;
                }else if(TextUtils.isEmpty(orderby)){
                    showMsg("排序不能为空");
                    return;
                }
                if(isEdit){
                    editAddress(categoryName,orderby,categoryObj.getId());
                }else{
                    addAddress(categoryName,orderby);
                }
                break;
        }
    }

    private void addAddress(String name, String orderby) {
        showLoading();
        Map<String,String> map=new HashMap<String,String>();
        map.put("typeName",name);
        map.put("sort",orderby);
        map.put("userId",getUserId());
        map.put("sign", GetSign.getSign(map));
        ApiRequest.addGoodsCategory(map, new MyCallBack<Object>(mContext) {
            @Override
            public void onSuccess(Object obj) {
                showMsg("添加成功");
                et_category_name.setText(null);
                et_categoty_orderby.setText(null);
                setResult(RESULT_OK);
                finish();
            }
        });
    }

    private void editAddress(String name, String orderby,String typeid) {
        showLoading();
        Map<String,String>map=new HashMap<String,String>();
        map.put("typeName",name);
        map.put("sort",orderby);
        map.put("typeID",typeid);
        map.put("sign", GetSign.getSign(map));
        ApiRequest.updateGoodsCategory(map, new MyCallBack<Object>(mContext) {
            @Override
            public void onSuccess(Object obj) {

                setResult(RESULT_OK);
                finish();
            }
        });
    }


    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }


}
