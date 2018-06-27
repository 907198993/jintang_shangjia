package com.qifan.shangjia.fragment;

import android.text.TextUtils;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.androidtools.SPUtils;
import com.qifan.shangjia.Config;
import com.qifan.shangjia.R;
import com.qifan.shangjia.base.BaseFragment;

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

    private void getInfo() {
        String nick_name = SPUtils.getPrefString(mContext, Config.nick_name, null);
        String sex = SPUtils.getPrefString(mContext, Config.sex, null);
        String avatar = SPUtils.getPrefString(mContext, Config.avatar, null);
        String amount = SPUtils.getPrefString(mContext, Config.amount, null);
        int keeping_bean = SPUtils.getPrefInt(mContext, Config.keeping_bean, 0);
        int count_wsy = SPUtils.getPrefInt(mContext, Config.count_wsy, 0);
        int isShowPoint = SPUtils.getPrefInt(mContext, Config.news_is_check, 0);

    }

    @Override
    protected void initView() {
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

        dataList2 = new ArrayList<Map<String, Object>>();
        for (int i = 0; i <icno2.length; i++) {
            Map<String, Object> map=new HashMap<String, Object>();
            map.put("img", icno2[i]);
            map.put("text",name2[i]);
            dataList2.add(map);
        }
        adapter2=new SimpleAdapter(getActivity(), dataList2, R.layout.gridview_item, from, to);
        gridview_order.setAdapter(adapter2);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onViewClick(View v) {

    }

//    @OnClick({R.id.tv_my_address,R.id.ll_my_account,R.id.ll_my_yangshengdou,R.id.ll_my_info,R.id.ll_my_vouchers,R.id.iv_my_seting, R.id.iv_my_msg, R.id.tv_my_qiandao, R.id.tv_my_order, R.id.tv_my_shequ, R.id.tv_my_evaluate, R.id.tv_my_fenxiao, R.id.tv_my_tuihuo, R.id.tv_my_collect, R.id.tv_my_zixun})
//    public void onViewClick(View view) {
//        switch (view.getId()) {
//            case R.id.ll_my_account:
//                STActivity(MyBalanceActivity.class);
//                break;
//            case R.id.ll_my_yangshengdou:
//                STActivity(YangShengDouActivity.class);
//                break;
//            case R.id.ll_my_info:
//                STActivity(MyDataActivity.class);
//                break;
//            case R.id.ll_my_vouchers:
//                STActivity(MyVouchersActivity.class);
//                break;
//            case R.id.iv_my_seting:
//                STActivity(SetingActivity.class);
//                break;
//            case R.id.iv_my_msg:
//                STActivity(MyMessageActivity.class);
//                break;
//            case R.id.tv_my_qiandao:
//                STActivity(QianDaoActivity.class);
//                break;
//            case R.id.tv_my_order:
//                STActivity(MyOrderListActivity.class);
//                break;
//            case R.id.tv_my_address:
//                STActivity(AddressListActivity.class);
//                break;
//            case R.id.tv_my_shequ:
//                STActivity(MySheQuActivity.class);
//                break;
//            case R.id.tv_my_evaluate:
//                STActivity(MyEvaluateActivity.class);
//                break;
//            case R.id.tv_my_fenxiao:
//                STActivity(MyFenXiaoActivity.class);
//                break;
//            case R.id.tv_my_tuihuo:
//                STActivity(TuiHuoListActivity.class);
//                break;
//            case R.id.tv_my_collect:
//                STActivity(MyAllCollectActivity.class);
//                break;
//            case R.id.tv_my_zixun:
//                goHX();
//                break;
//        }
//    }


}
