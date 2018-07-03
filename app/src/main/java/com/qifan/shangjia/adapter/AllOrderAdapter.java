package com.qifan.shangjia.adapter;

import android.app.Activity;
import android.content.Context;
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
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.daimajia.swipe.SwipeLayout;
import com.github.androidtools.SPUtils;
import com.github.androidtools.ToastUtils;
import com.github.androidtools.inter.MyOnClickListener;
import com.github.baseclass.adapter.BaseRecyclerAdapter;
import com.github.baseclass.adapter.LoadMoreAdapter;
import com.github.baseclass.adapter.LoadMoreViewHolder;
import com.github.baseclass.adapter.RecyclerViewHolder;
import com.github.baseclass.rx.MySubscriber;
import com.github.baseclass.rx.RxBus;
import com.github.baseclass.utils.ActUtils;
import com.github.baseclass.view.Loading;
import com.github.baseclass.view.MyDialog;
import com.github.customview.MyTextView;
import com.qifan.shangjia.Constant;
import com.qifan.shangjia.R;
import com.qifan.shangjia.activity.OrderDetailActivity;
import com.qifan.shangjia.base.BaseDividerListItem;
import com.qifan.shangjia.network.response.OrderListObj;


import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by administartor on 2017/8/2.
 */

public class AllOrderAdapter extends LoadMoreAdapter<OrderListObj> {

    private final BaseDividerListItem baseDividerListItem;
    public AllOrderAdapter(Context mContext, int layoutId, int pageSize ) {
        super(mContext, layoutId, pageSize);
        baseDividerListItem = new BaseDividerListItem(mContext, BaseDividerListItem.VERTICAL_LIST, 2, R.color.background_f2);
    }

    @Override
    public void bindData(LoadMoreViewHolder holder, int i, OrderListObj bean) {
        holder.getView(R.id.tv_order_detail).setOnClickListener(new MyOnClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                Intent intent=new Intent();
                intent.putExtra(Constant.IParam.orderNo,bean.getOrder_no());
                ActUtils.STActivityForResult((Activity) mContext, intent,OrderDetailActivity.class,202);
            }
        });
        SwipeLayout sl_my_order =  (SwipeLayout)holder.getView(R.id.sl_my_order);
        TextView tv_my_order_delete = holder.getTextView(R.id.tv_my_order_delete);

        if(bean.getOrder_status()==6){
            sl_my_order.setLeftSwipeEnabled(true);
            sl_my_order.setRightSwipeEnabled(true);
        }else{
            sl_my_order.setLeftSwipeEnabled(false);
            sl_my_order.setRightSwipeEnabled(false);
        }
//        tv_my_order_delete.setOnClickListener(new MyOnClickListener() {
//            @Override
//            protected void onNoDoubleClick(View view) {
//                MyDialog.Builder mDialog=new MyDialog.Builder(mContext);
//                mDialog.setMessage("是否确认删除订单?");
//                mDialog.setNegativeButton(new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                });
//                mDialog.setPositiveButton(new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                        deleteOrder(bean.getOrder_no());
//                    }
//                });
//                mDialog.create().show();
//            }
//        });


//        RecyclerView rv_order_goods = (RecyclerView) holder.getView(R.id.rv_order_goods);
//        rv_order_goods.setNestedScrollingEnabled(false);
//        rv_order_goods.setLayoutManager(new LinearLayoutManager(mContext));
//        rv_order_goods.removeItemDecoration(baseDividerListItem);
//        rv_order_goods.addItemDecoration(baseDividerListItem);
//        BaseRecyclerAdapter adapter=new BaseRecyclerAdapter<OrderListObj>(mContext,R.layout.item_order_detail) {
//            @Override
//            public void bindData(RecyclerViewHolder viewHolder, int i, OrderListObj item) {
//                ImageView imageView = viewHolder.getImageView(R.id.iv_order_goods);
//                Glide.with(mContext).load(item.getGoods_images()).error(R.color.c_press).into(imageView);
//                viewHolder.setText(R.id.tv_order_goods_name,item.getGoods_name())
//                        .setText(R.id.tv_order_goods_guige,item.getGoods_specification())
//                        .setText(R.id.tv_order_goods_price,"¥"+item.getGoods_unitprice())
//                        .setText(R.id.tv_order_goods_num,"x"+item.getGoods_number());
//                viewHolder.itemView.setOnClickListener(new MyOnClickListener() {
//                    @Override
//                    protected void onNoDoubleClick(View view) {
//                        Intent intent=new Intent();
//                        if(bean.getOrder_status()==6){
//                            intent.setAction("canDelete");
//                        }
//                        intent.putExtra(Constant.IParam.orderNo,bean.getOrder_no());
//                        ActUtils.STActivityForResult((Activity) mContext, intent,OrderDetailActivity.class,202);
//                    }
//                });
//            }
//        };
//        adapter.setList(bean.getGoods_list());
//        rv_order_goods.setAdapter(adapter);
          holder.setText(R.id.tv_order_no,bean.getOrder_no())
                .setText(R.id.tv_order_create_time,bean.getCreate_add_time())
                .setText(R.id.tv_order_price,bean.getPay_money())
                  .setText(R.id.tv_order_create_time,bean.getCreate_add_time())
                  .setText(R.id.tv_order_username,bean.getAddress_recipient())
                  .setText(R.id.tv_order_phone,bean.getAddress_phone())
                    .setText(R.id.tv_order_address,bean.getShipping_address());

        TextView tv_order_type = holder.getTextView(R.id.tv_order_type);





//        tv_order_sqtk.setOnClickListener(new MyOnClickListener() {
//            @Override
//            protected void onNoDoubleClick(View view) {
//                Intent intent=new Intent();
//                intent.putExtra(Constant.IParam.orderNo,bean.getOrder_no());
//                ActUtils.STActivityForResult((Activity) mContext,intent, TuiKuanActivity.class,100);
//            }
//        });

//        tv_order_qrsh.setOnClickListener(new MyOnClickListener() {
//            @Override
//            protected void onNoDoubleClick(View view) {
//                MyDialog.Builder mDialog=new MyDialog.Builder(mContext);
//                mDialog.setMessage("是否确认收货?");
//                mDialog.setNegativeButton(new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                });
//                mDialog.setPositiveButton(new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                        sureOrder(bean.getOrder_no());
//                    }
//                });
//                mDialog.create().show();
//            }
//        });
//
//        tv_order_qpj.setOnClickListener(new MyOnClickListener() {
//            @Override
//            protected void onNoDoubleClick(View view) {
//                Intent intent=new Intent();
//                intent.putExtra(Constant.IParam.orderNo,bean.getOrder_no());
//                ActUtils.STActivityForResult((Activity) mContext,intent, FaBiaoEvaluateActivity.class,201);
//            }
//        });

        switch (bean.getOrder_status()){
            case 1:
                tv_order_type.setText("待付款");
            break;
            case 2:
                tv_order_type.setText("待收货");
            break;
            case 5:
                tv_order_type.setText("已完成");
            break;
            case 6:
                tv_order_type.setText("已取消");

                break;
            default:

                break;
        }
    }
//
//    private void deleteOrder(String order_no) {
//        Loading.show(mContext);
//        Map<String,String>map=new HashMap<String,String>();
//        map.put("user_id", SPUtils.getPrefString(mContext, Config.user_id,null));
//        map.put("order_no",order_no);
//        map.put("sign",GetSign.getSign(map));
//        ApiRequest.deleteOrder(map, new MyCallBack<BaseObj>(mContext) {
//            @Override
//            public void onSuccess(BaseObj obj) {
//                RxBus.getInstance().post(new GetOrderEvent());
//                RxBus.getInstance().post(new GetOrderNumEvent());
//                ToastUtils.showToast(mContext,obj.getMsg());
//            }
//        });
//
//    }




//    private void selectPay(String orderNo,double totalPrice) {
//        BottomSheetDialog payDialog = new BottomSheetDialog(mContext);
//        payDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        payDialog.setCancelable(false);
//        payDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
//            @Override
//            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
//                if(payDialog.isShowing()&&keyCode== KeyEvent.KEYCODE_BACK){
//                    payDialog.dismiss();
//                    return true;
//                }
//                return false;
//            }
//        });
//        View payView = LayoutInflater.from(mContext).inflate(R.layout.popu_select_pay, null);
//        ImageView iv_pay_cancle = payView.findViewById(R.id.iv_pay_cancle);
//        iv_pay_cancle.setOnClickListener(new MyOnClickListener() {
//            @Override
//            protected void onNoDoubleClick(View view) {
//                payDialog.dismiss();
//            }
//        });
//        TextView tv_pay_total = payView.findViewById(R.id.tv_pay_total);
//        tv_pay_total.setText("¥" + totalPrice);
//        RadioGroup rg_select_pay = payView.findViewById(R.id.rg_select_pay);
//        TextView tv_pay_commit = payView.findViewById(R.id.tv_pay_commit);
////        tv_pay_commit.setOnClickListener(new MyOnClickListener() {
////            @Override
////            protected void onNoDoubleClick(View view) {
////                payDialog.dismiss();
////                int checkedRadioButtonId = rg_select_pay.getCheckedRadioButtonId();
////                switch (checkedRadioButtonId) {
////                    case R.id.rb_pay_zhifubao:
////                        zhiFuBaoPay(orderNo,totalPrice);
////                        break;
////                    case R.id.rb_pay_weixin:
////                        OrderBean orderBean =new OrderBean();
////                        orderBean.body="爱尚养御订单";
////                        orderBean.nonceStr=getRnd();
////                        orderBean.out_trade_no=orderNo;
//////                        orderBean.totalFee= AndroidUtils.mul(1,1);
////                        orderBean.totalFee= AndroidUtils.mul(totalPrice,100);
////                        orderBean.IP= AndroidUtils.getIP(mContext);
////                        weiXinPay(orderBean);
////                        break;
////                    case R.id.rb_pay_online:
////                        onLinePay(orderNo,totalPrice);
////                        break;
////                }
////            }
////
////
////        });
//        payDialog.setContentView(payView);
//        payDialog.show();
//    }
//    private void onLinePay(String orderNo, double totalPrice) {
//        Loading.show(mContext);
//        Map<String,String>map=new HashMap<String,String>();
//        map.put("user_id", SPUtils.getPrefString(mContext,Config.user_id,null));
//        map.put("order_no",orderNo);
//        map.put("money",totalPrice+"");
//        map.put("sign",GetSign.getSign(map));
//        com.sk.jintang.module.orderclass.network.ApiRequest.yuEPay(map, new MyCallBack<BaseObj>(mContext,true) {
//            @Override
//            public void onSuccess(BaseObj obj) {
//                RxBus.getInstance().post(new GetOrderEvent());
//                ToastUtils.showToast(mContext,obj.getMsg());
//                RxBus.getInstance().post(new GetOrderNumEvent());
//            }
//        });
//
//    }

//    private void zhiFuBaoPay(String orderNo,double totalPrice) {
//        double total;
//        if(BuildConfig.DEBUG){
//            total=0.01;
//        }else{
//            total =  totalPrice;
//        }
////        double total =  totalPrice;
////        double total=0.01;
//        AliPay bean = new AliPay();
//        bean.setTotal_amount(total );
//        bean.setOut_trade_no(orderNo);
//        bean.setSubject(orderNo + "订单交易");
//        bean.setBody("爱尚养御订单");
//        String notifyUrl = SPUtils.getPrefString(mContext, Config.payType_ZFB, null);
//        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(bean, notifyUrl);
//        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);
//
//        String sign = OrderInfoUtil2_0.getSign(params, Config.zhifubao_rsa2, true);
//        final String orderInfo = orderParam + "&" + sign;
//        Loading.show(mContext);
//        Observable.create(new Observable.OnSubscribe<Map>() {
//            @Override
//            public void call(Subscriber<? super Map> subscriber) {
//                PayTask payTask = new PayTask((Activity) mContext);
//                Map<String, String> result = payTask.payV2(orderInfo, true);
//                Log.i("msp=====", result.toString());
//                subscriber.onNext(result);
//                subscriber.onCompleted();
//            }
//        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new MySubscriber<Map>() {
//            @Override
//            public void onMyNext(Map map) {
//                PayResult payResult = new PayResult(map);
//                /**
//                 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
//                 */
//                String resultInfo = payResult.getResult();// 同步返回需要验证的信息
//                Log.i("==========", "1==========" + resultInfo);
//                String resultStatus = payResult.getResultStatus();
//                Log.i("==========", "2==========" + resultStatus);
//                // 判断resultStatus 为9000则代表支付成功
//                if (TextUtils.equals(resultStatus, "9000")) {
//                    RxBus.getInstance().post(new GetOrderEvent());
//                    RxBus.getInstance().post(new GetOrderNumEvent());
//                    // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
//                    Toast.makeText(mContext, "支付成功", Toast.LENGTH_SHORT).show();
//                } else if (TextUtils.equals(resultStatus, "6001")) {
//                    Loading.dismissLoading();
//                    // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
//                    Toast.makeText(mContext, "支付取消", Toast.LENGTH_SHORT).show();
//                } else {
//                    Loading.dismissLoading();
//                    // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
//                    Toast.makeText(mContext, "支付失败", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                super.onError(e);
//                Loading.dismissLoading();
//            }
//        });
//
//
//    }
//    private void weiXinPay(OrderBean orderBean) {
//        new WXPay(mContext).pay(orderBean);
//    }
    protected String getRnd(){
        Random random = new Random();
        int rnd = random.nextInt(9000) + 1000;
        return rnd+"";
    }
}
