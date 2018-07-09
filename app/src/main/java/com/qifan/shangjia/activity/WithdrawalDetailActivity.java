package com.qifan.shangjia.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.androidtools.PhoneUtils;
import com.github.baseclass.adapter.LoadMoreAdapter;
import com.github.baseclass.adapter.LoadMoreViewHolder;
import com.github.customview.MyTextView;
import com.qifan.shangjia.Constant;
import com.qifan.shangjia.GetSign;
import com.qifan.shangjia.R;
import com.qifan.shangjia.base.BaseActivity;
import com.qifan.shangjia.base.MyCallBack;
import com.qifan.shangjia.network.ApiRequest;
import com.qifan.shangjia.network.response.MoneyDetailObj;
import com.qifan.shangjia.network.response.OrderDetailObj;
import com.qifan.shangjia.tools.StatusBarUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;


public class WithdrawalDetailActivity extends BaseActivity implements LoadMoreAdapter.OnLoadMoreListener{
    @BindView(R.id.rv_order_detail)
    RecyclerView rv_order_detail;

    LoadMoreAdapter adapter;
    @Override
    protected int getContentView() {
        setAppTitle("提现明细");
        return R.layout.act_order_detail;
    }

    @Override
    protected void initView() {
        StatusBarUtil.setStatusBarColor(WithdrawalDetailActivity.this,getResources().getColor(R.color.red));
        adapter=new LoadMoreAdapter<MoneyDetailObj>(mContext,R.layout.item_money_list,pageSize) {
            @Override
            public void bindData(LoadMoreViewHolder viewHolder, int position, MoneyDetailObj item) {



                TextView tv_money_type = viewHolder.getTextView(R.id.tv_money_type);
                viewHolder.setText(R.id.tv_order_no, item.getOrder_no())
                        .setText(R.id.tv_zhifubao,  item.getAccount())
                        .setText(R.id.tv_money, item.getWithdrawal())
                        .setText(R.id.tv_time, item.getCreateDate());
                if(item.getStatus()==0){
                    tv_money_type.setText("处理中");
                    tv_money_type.setTextColor(getResources().getColor(R.color.blue));
                }else if(item.getStatus()==1){
                    tv_money_type.setText("成功");
                    tv_money_type.setTextColor(getResources().getColor(R.color.green));
                }else{
                    tv_money_type.setText("失败");
                    tv_money_type.setTextColor(getResources().getColor(R.color.red));
                }

            }
        };
        adapter.setOnLoadMoreListener(this);
        rv_order_detail.setLayoutManager(new LinearLayoutManager(mContext));
        rv_order_detail.addItemDecoration(getItemDivider(PhoneUtils.dip2px(mContext,10)));
        rv_order_detail.setAdapter(adapter);

        pcfl.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                getData(1,false);
            }
        });

    }

    @Override
    protected void initData() {
        showProgress();
        getData(1,false);
    }
    private void getData(int page, boolean isLoad) {
        Map<String,String> map=new HashMap<String,String>();
        map.put("userId",getUserId());
        map.put("pageIndex",page+"");
        map.put("pageSize",pageSize+"");
        map.put("sign", GetSign.getSign(map));
        ApiRequest.getWithdrawalsList(map, new MyCallBack<List<MoneyDetailObj>>(mContext,pcfl,pl_load) {
            @Override
            public void onSuccess(List<MoneyDetailObj> list) {
                if(isLoad){
                    pageNum++;
                    adapter.addList(list,true);
                }else{
                    pageNum=2;
                    adapter.setList(list,true);
                }
            }
        });

    }
    @Override
    protected void onViewClick(View v) {

    }

    @Override
    public void loadMore() {
        getData(pageNum,true);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            switch (requestCode){
                case 100:
                    initData();
                    break;
            }
        }
    }
}
