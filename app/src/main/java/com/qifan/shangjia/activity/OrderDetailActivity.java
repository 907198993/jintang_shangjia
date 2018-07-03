package com.qifan.shangjia.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.androidtools.SPUtils;
import com.github.androidtools.ToastUtils;
import com.github.androidtools.inter.MyOnClickListener;
import com.github.baseclass.adapter.BaseRecyclerAdapter;
import com.github.baseclass.adapter.RecyclerViewHolder;
import com.github.baseclass.rx.MySubscriber;
import com.github.baseclass.rx.RxBus;
import com.github.baseclass.view.Loading;
import com.github.baseclass.view.MyDialog;
import com.github.customview.MyTextView;
import com.qifan.shangjia.Constant;
import com.qifan.shangjia.GetSign;
import com.qifan.shangjia.R;
import com.qifan.shangjia.base.BaseActivity;
import com.qifan.shangjia.base.MyCallBack;
import com.qifan.shangjia.network.ApiRequest;
import com.qifan.shangjia.network.response.OrderDetailObj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by administartor on 2017/9/7.
 */

public class OrderDetailActivity extends BaseActivity {

    @BindView(R.id.rv_order_detail)
    RecyclerView rv_order_detail;

    private BaseRecyclerAdapter adapter;
    private List<OrderDetailObj> orderDetailObj = new ArrayList<>();
    private int orderStatus;
    private String action;
    String orderNo;
    @Override
    protected int getContentView() {
        setAppTitle("商品信息");
        return R.layout.act_order_detail;
    }

    @Override
    protected void initView() {
        action = getIntent().getAction();
        orderNo = getIntent().getStringExtra(Constant.IParam.orderNo);
        rv_order_detail.setLayoutManager(new LinearLayoutManager(mContext));
        rv_order_detail.addItemDecoration(getItemDivider());
        rv_order_detail.setNestedScrollingEnabled(false);


        adapter = new BaseRecyclerAdapter<OrderDetailObj>(mContext, R.layout.item_order_detail) {
            @Override
            public void bindData(RecyclerViewHolder viewHolder, int i, OrderDetailObj item) {
                ImageView imageView = viewHolder.getImageView(R.id.iv_order_goods);
                Glide.with(mContext).load(Constant.url+item.getGoods_images()).error(R.color.c_press).into(imageView);
                viewHolder.setText(R.id.tv_order_goods_name, item.getGoods_name())
                        .setText(R.id.tv_order_goods_guige, "规格：" +item.getGoods_specification())
                        .setText(R.id.tv_order_goods_price, "单价¥" + item.getGoods_unitprice())
                        .setText(R.id.tv_order_goods_num, "数量：" + item.getGoods_number());

//                viewHolder.itemView.setOnClickListener(new MyOnClickListener() {
//                    @Override
//                    protected void onNoDoubleClick(View view) {
//                        //0商品不存在 1普通商品 2限时抢购 3团购),staus商品状态(0商品不存在或者活动已结束 1商品存在活动没结束
//                        Intent intent=new Intent();
//                        intent.putExtra(com.sk.jintang.module.orderclass.Constant.IParam.goodsId,item.getGoods_id()+"");
//                        if(item.getCode()==0||item.getCode()==1){
//                            STActivity(intent,GoodsDetailActivity.class);
//                        }else if(item.getCode()==2){
//                            intent.setAction(Config.IParam.xianShiQiangGou);
//                            STActivity(intent,GoodsDetailXianShiActivity.class);
//                        }else if(item.getCode()==3){
//                            STActivity(intent,GoodsDetailTuanGouActivity.class);
//                        }
//                    }
//                });
            }
        };
        rv_order_detail.setAdapter(adapter);

    }

    @Override
    protected void initData() {
        showProgress();
        getData();
    }

    @Override
    protected void onViewClick(View v) {

    }

    private void getData() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("orderNo", orderNo);
        map.put("sign", GetSign.getSign(map));
        ApiRequest.orderDetail(map, new MyCallBack<List<OrderDetailObj>>(mContext, pcfl, pl_load) {
            @Override
            public void onSuccess(List<OrderDetailObj> obj) {
                orderDetailObj = obj;
                adapter.setList(obj, true);




            }
        });

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            switch (requestCode){
                case 100:
                    setResult(RESULT_OK);
                    finish();
                break;
                case 201:
                    setResult(RESULT_OK);
                    finish();
                break;
            }
        }
    }
}
