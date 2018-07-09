package com.qifan.shangjia.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.androidtools.PhoneUtils;
import com.github.androidtools.SPUtils;
import com.github.androidtools.inter.MyOnClickListener;
import com.github.baseclass.adapter.BaseRecyclerAdapter;
import com.github.baseclass.adapter.LoadMoreAdapter;
import com.github.baseclass.adapter.LoadMoreViewHolder;
import com.github.baseclass.adapter.RecyclerViewHolder;
import com.github.customview.MyTextView;
import com.qifan.shangjia.Constant;
import com.qifan.shangjia.GetSign;
import com.qifan.shangjia.R;
import com.qifan.shangjia.base.BaseActivity;
import com.qifan.shangjia.base.MyCallBack;
import com.qifan.shangjia.network.ApiRequest;
import com.qifan.shangjia.network.response.AddGoodsItem;
import com.qifan.shangjia.network.response.OrderDetailObj;
import com.qifan.shangjia.tools.StatusBarUtil;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;


public class GoodsListActivity extends BaseActivity implements LoadMoreAdapter.OnLoadMoreListener{
    @BindView(R.id.rv_order_detail)
    RecyclerView rv_order_detail;

    LoadMoreAdapter adapter;
    @Override
    protected int getContentView() {
        setAppTitle("商品列表");
        return R.layout.act_order_detail;
    }

    @Override
    protected void initView() {
        StatusBarUtil.setStatusBarColor(GoodsListActivity.this,getResources().getColor(R.color.red));
        adapter=new LoadMoreAdapter<OrderDetailObj>(mContext,R.layout.item_goods_list,pageSize) {
            @Override
            public void bindData(LoadMoreViewHolder viewHolder, int position, OrderDetailObj item) {

                ImageView imageView = viewHolder.getImageView(R.id.iv_order_goods);
                MyTextView  textView = (MyTextView) viewHolder.getView(R.id.tv_goods_editor);
                textView.setVisibility(View.VISIBLE);
                TextView  tv_order_goods_origin_price = (TextView) viewHolder.getView(R.id.tv_order_goods_origin_price);
                tv_order_goods_origin_price.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG);
                Glide.with(mContext).load(Constant.url+item.getGoods_image()).error(R.color.c_press).into(imageView);
                viewHolder.setText(R.id.tv_order_goods_name, item.getGoods_name())
                        .setText(R.id.tv_order_goods_origin_price, "¥" + item.getOriginal_price())
                        .setText(R.id.tv_order_goods_price, "¥" + item.getPrice())
                        .setText(R.id.tv_order_goods_num, "销量：" + item.getSales_volume());

                MyTextView tv_goods_editor=  (MyTextView) viewHolder.getView(R.id.tv_goods_editor);
                tv_goods_editor.setOnClickListener(new MyOnClickListener() {//编辑
                    @Override
                    protected void onNoDoubleClick(View view) {
                       String goodsId = item.getGoods_id()+"" ;
                        showLoading();
                        Map<String,String>map=new HashMap<String,String>();
                        map.put("goodsId",item.getGoods_id()+"");
                        map.put("sign",GetSign.getSign(map));
                        ApiRequest.getGoodsDetail(map, new MyCallBack<AddGoodsItem>(mContext) {
                            @Override
                            public void onSuccess(AddGoodsItem obj) {
                                Intent intent=new Intent();
                                intent.putExtra(Constant.IParam.editGoods,true);
                                intent.putExtra(Constant.IParam.addGoodsItem,obj);
                                intent.putExtra(Constant.IParam.goodsId,goodsId);
                                STActivityForResult(intent,AddGoodsActivity.class,101);
                            }
                        });

                    }
                });

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
        ApiRequest.getGoodsList(map, new MyCallBack<List<OrderDetailObj>>(mContext,pcfl,pl_load) {
            @Override
            public void onSuccess(List<OrderDetailObj> list) {
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
                case 101:
                    initData();
                    break;
            }
        }
    }
}
