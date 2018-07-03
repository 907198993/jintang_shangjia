package com.qifan.shangjia.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.github.androidtools.PhoneUtils;
import com.github.baseclass.adapter.LoadMoreAdapter;
import com.github.baseclass.rx.MySubscriber;
import com.github.baseclass.rx.RxBus;
import com.qifan.shangjia.Constant;
import com.qifan.shangjia.GetSign;
import com.qifan.shangjia.R;
import com.qifan.shangjia.adapter.AllOrderAdapter;
import com.qifan.shangjia.base.BaseFragment;
import com.qifan.shangjia.base.MyCallBack;
import com.qifan.shangjia.event.GetOrderEvent;
import com.qifan.shangjia.network.ApiRequest;
import com.qifan.shangjia.network.response.OrderListObj;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

import static android.app.Activity.RESULT_OK;
/**
 * Created by administartor on 2017/8/2.
 */

public class AllOrderFragment extends BaseFragment implements LoadMoreAdapter.OnLoadMoreListener {
    @BindView(R.id.rv_all_order)
    RecyclerView rv_all_order;

    public interface PayInter{
        void aliPay();
        void onlinePay(Map<String, String> map);
    }
    AllOrderAdapter adapter;
    private String type="0";

    @Override
    protected int getContentView() {
        return R.layout.frag_all_order;
    }

    public static AllOrderFragment newInstance(int type) {
        Bundle args = new Bundle();
        args.putString(Constant.type,type+"");
        AllOrderFragment fragment = new AllOrderFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected void initView() {
        adapter =new AllOrderAdapter(mContext, R.layout.item_order_list, pageSize);
        adapter.setOnLoadMoreListener(this);

        rv_all_order.setLayoutManager(new LinearLayoutManager(mContext));
        rv_all_order.addItemDecoration(getItemDivider(PhoneUtils.dip2px(mContext,10)));
        rv_all_order.setAdapter(adapter);

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

    @Override
    protected void initRxBus() {
        super.initRxBus();
        RxBus.getInstance().getEvent(GetOrderEvent.class, new MySubscriber() {
            @Override
            public void onMyNext(Object o) {
                showLoading();
                getData(1,false);
            }
        });

    }

    private void getData(int page, boolean isLoad) {
        Map<String,String> map=new HashMap<String,String>();
        map.put("userId",getUserId());
        map.put("type",getArguments().getString(Constant.type,"0"));
        map.put("pageIndex",page+"");
        map.put("pageSize",pageSize+"");
        map.put("sign", GetSign.getSign(map));
        ApiRequest.orderList(map, new MyCallBack<List<OrderListObj>>(mContext,pcfl,pl_load) {
            @Override
            public void onSuccess(List<OrderListObj> list) {
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            switch (requestCode){
                case 100:
                    getData(1,false);
                break;
            }
        }
    }

    @Override
    protected void onViewClick(View v) {

    }

    @Override
    public void loadMore() {
        getData(pageNum,true);
    }
}
