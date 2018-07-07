package com.qifan.shangjia.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.github.androidtools.PhoneUtils;
import com.github.androidtools.inter.MyOnClickListener;
import com.github.baseclass.adapter.LoadMoreAdapter;
import com.github.baseclass.adapter.LoadMoreViewHolder;
import com.github.baseclass.view.MyDialog;
import com.qifan.shangjia.Constant;
import com.qifan.shangjia.GetSign;
import com.qifan.shangjia.R;
import com.qifan.shangjia.base.BaseActivity;
import com.qifan.shangjia.base.MyCallBack;
import com.qifan.shangjia.network.ApiRequest;
import com.qifan.shangjia.network.response.CategoryObj;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by administartor on 2017/9/2.
 */

public class GoodsCategoryActivity extends BaseActivity implements LoadMoreAdapter.OnLoadMoreListener{
    @BindView(R.id.rv_address_list)
    RecyclerView rv_address_list;


    LoadMoreAdapter adapter;
    @Override
    protected int getContentView() {
        setAppTitle("商品分类");
        return R.layout.act_goods_category;
    }

    @Override
    protected void initView() {

        adapter=new LoadMoreAdapter<CategoryObj>(mContext,R.layout.item_category_list,pageSize) {
            @Override
            public void bindData(LoadMoreViewHolder holder, int position, CategoryObj bean) {
                holder.setText(R.id.tv_category_name,bean.getTypeName())
                        .setText(R.id.tv_category_orderby,bean.getSort());
                TextView tv_address_edit=  holder.getTextView(R.id.tv_category_edit);
                TextView tv_address_delete=  holder.getTextView(R.id.tv_category_delete);
                tv_address_edit.setOnClickListener(new MyOnClickListener() {//编辑
                    @Override
                    protected void onNoDoubleClick(View view) {
                        Intent intent=new Intent();
                        intent.putExtra(Constant.IParam.editCategory,true);
                        intent.putExtra(Constant.IParam.Category,bean);
                        STActivityForResult(intent,AddGoodsCategoryActivity.class,101);
                    }
                });
                tv_address_delete.setOnClickListener(new MyOnClickListener() {
                    @Override
                    protected void onNoDoubleClick(View view) {
                        MyDialog.Builder mDialog=new MyDialog.Builder(mContext);
                        mDialog.setMessage("是否确认删除当前商品分类?");
                        mDialog.setNegativeButton(new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        mDialog.setPositiveButton(new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                deleteCategory(bean);
                            }
                        });
                        mDialog.create().show();
                    }
                });

            }
        };
        adapter.setOnLoadMoreListener(this);

        rv_address_list.setLayoutManager(new LinearLayoutManager(mContext));
        rv_address_list.addItemDecoration(getItemDivider(PhoneUtils.dip2px(mContext,5)));
        rv_address_list.setAdapter(adapter);

    }

    private void deleteCategory(CategoryObj bean) {
        showLoading();
        Map<String,String>map=new HashMap<String,String>();
        map.put("typeId",bean.getId()+"");
        map.put("sign",GetSign.getSign(map));
        ApiRequest.deleteGoodsCategory(map, new MyCallBack<Object>(mContext,true) {
            @Override
            public void onSuccess(Object obj) {

                getData();
            }
        });

    }

    @Override
    protected void initData() {
        showProgress();
        getData();
    }

    private void getData() {
        Map<String,String> map=new HashMap<String,String>();
        map.put("userId",getUserId());
        map.put("sign", GetSign.getSign(map));
        ApiRequest.getGoodsCategory(map, new MyCallBack<List<CategoryObj>>(mContext,pcfl,pl_load) {
            @Override
            public void onSuccess(List<CategoryObj> list) {
                adapter.setList(list,true);
            }
        });

    }

    @OnClick({R.id.tv_address_add})
    protected void onViewClick(View v) {
        switch (v.getId()){
            case R.id.tv_address_add:
                STActivityForResult(AddGoodsCategoryActivity.class,100);
            break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            switch (requestCode){
                case 100:
                case 101:
                    getData();
                break;
            }
        }
    }

    @Override
    public void loadMore() {
    }
}
