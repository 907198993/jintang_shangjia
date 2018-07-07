package com.qifan.shangjia.fragment;

import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.androidtools.SPUtils;
import com.github.customview.MyTextView;
import com.qifan.shangjia.Config;
import com.qifan.shangjia.R;
import com.qifan.shangjia.activity.AddGoodsActivity;
import com.qifan.shangjia.activity.ForgetPasswordActivity;
import com.qifan.shangjia.activity.GoodsCategoryActivity;
import com.qifan.shangjia.activity.GoodsListActivity;
import com.qifan.shangjia.activity.MyOrderActivity;
import com.qifan.shangjia.activity.WithdrawalsActivity;
import com.qifan.shangjia.base.BaseFragment;
import com.qifan.shangjia.tools.StatusBarUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by administartor on 2017/8/4.
 */

public class MyFragment extends BaseFragment {
    @BindView(R.id.gridview)
    GridView gridView;

    @BindView(R.id.gridview_order)
    GridView gridview_order;

    @BindView(R.id.ll_title)
    RelativeLayout ll_title;

    @BindView(R.id.my_wallet)
    TextView my_wallet; // 余额

    @BindView(R.id.tv_cash)
    MyTextView tv_cash;   //取现按钮




    private List<Map<String, Object>> dataList1,dataList2;
    private SimpleAdapter adapter1,adapter2;

    int icno1[] = { R.mipmap.add_goods, R.mipmap.goods_list, R.mipmap.goods_category,
                  };
    //图标下的文字
    String name1[]={"添加商品","商品列表","商品分类"};

    int icno2[] = { R.mipmap.order_list};
    //图标下的文字
    String name2[]={"订单列表"};
    @Override
    protected int getContentView() {
        return R.layout.frag_my;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(TextUtils.isEmpty(SPUtils.getPrefString(mContext, Config.user_id,null))){
            return;
        }
        getData();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden){
            if(TextUtils.isEmpty(SPUtils.getPrefString(mContext,Config.user_id,null))){
                return;
            }
            getData();
        }
    }

    private void getData() {
        if(TextUtils.isEmpty(SPUtils.getPrefString(mContext,Config.user_id,null))){
            return;
        }
//        Map<String,String> map=new HashMap<String,String>();
//        map.put("user_id",getUserId());
//        map.put("sign", GetSign.getSign(map));
//        ApiRequest.getUserInfo(map, new MyCallBack<UserInfoObj>(mContext) {
//            @Override
//            public void onSuccess(UserInfoObj obj) {
//                SPUtils.setPrefString(mContext, Config.mobile,obj.getMobile());
//                SPUtils.setPrefString(mContext, Config.sex,obj.getSex());
//                SPUtils.setPrefString(mContext, Config.avatar,obj.getAvatar());
//                SPUtils.setPrefString(mContext, Config.birthday,obj.getBirthday());
//                SPUtils.setPrefString(mContext, Config.nick_name,obj.getNick_name());
//                SPUtils.setPrefString(mContext, Config.user_name,obj.getUser_name());
//                SPUtils.setPrefString(mContext, Config.amount,obj.getAmount()+"");
//                SPUtils.setPrefInt(mContext, Config.count_wsy,obj.getCount_wsy());
//                SPUtils.setPrefInt(mContext, Config.keeping_bean,obj.getKeeping_bean());
//                SPUtils.setPrefInt(mContext, Config.news_is_check,obj.getNews_is_check());
//                getInfo();
//            }
//        });
    }

//    private void getInfo() {
//        String nick_name = SPUtils.getPrefString(mContext, Config.nick_name, null);
//        String sex = SPUtils.getPrefString(mContext, Config.sex, null);
//        String avatar = SPUtils.getPrefString(mContext, Config.avatar, null);
//
//        int keeping_bean = SPUtils.getPrefInt(mContext, Config.keeping_bean, 0);
//        int count_wsy = SPUtils.getPrefInt(mContext, Config.count_wsy, 0);
//        int isShowPoint = SPUtils.getPrefInt(mContext, Config.news_is_check, 0);
//
//    }
    private void floatStatusBar() {
        StatusBarUtil.fullScreen(getActivity());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            RelativeLayout.LayoutParams titleParams = (RelativeLayout.LayoutParams) ll_title.getLayoutParams();
            // 标题栏在上方留出一段距离，看起来仍在状态栏下方
            titleParams.topMargin = StatusBarUtil.getStatusBarHeight(getActivity());
            ll_title.setLayoutParams(titleParams);
        }
    }
    @Override
    protected void initView() {
        String amount = SPUtils.getPrefString(mContext, Config.amount, null);
        my_wallet.setText(amount);  //余额
        floatStatusBar();
        String[] from={"img","text"};
        int[] to={R.id.img,R.id.text};
        dataList1 = new ArrayList<Map<String, Object>>();
        for (int i = 0; i <icno1.length; i++) {
            Map<String, Object> map=new HashMap<String, Object>();
            map.put("img", icno1[i]);
            map.put("text",name1[i]);
            dataList1.add(map);
        }
        adapter1=new SimpleAdapter(getActivity(), dataList1, R.layout.gridview_item, from, to);
        gridView.setAdapter(adapter1);
        //添加消息处理
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(position==0){
                     STActivity(AddGoodsActivity.class);
                }else if(position==1){
                    STActivity(GoodsListActivity.class);
                }else{
                    STActivity(GoodsCategoryActivity.class);
                }
            }
        });
        dataList2 = new ArrayList<Map<String, Object>>();
        for (int i = 0; i <icno2.length; i++) {
            Map<String, Object> map=new HashMap<String, Object>();
            map.put("img", icno2[i]);
            map.put("text",name2[i]);
            dataList2.add(map);
        }
        adapter2=new SimpleAdapter(getActivity(), dataList2, R.layout.gridview_item, from, to);
        gridview_order.setAdapter(adapter2);
        gridview_order.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(position==0){
                    STActivity(MyOrderActivity.class);
                }
            }
        });
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.tv_cash})
    protected void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_cash:
                STActivity(WithdrawalsActivity.class);
                break;

        }
    }
}
