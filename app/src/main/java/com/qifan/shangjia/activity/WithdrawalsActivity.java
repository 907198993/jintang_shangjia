package com.qifan.shangjia.activity;

import android.text.InputFilter;
import android.view.View;
import android.widget.TextView;

import com.github.androidtools.SPUtils;
import com.github.customview.MyEditText;
import com.github.customview.MyTextView;
import com.qifan.shangjia.Config;
import com.qifan.shangjia.GetSign;
import com.qifan.shangjia.R;
import com.qifan.shangjia.base.BaseActivity;
import com.qifan.shangjia.base.MyCallBack;
import com.qifan.shangjia.network.ApiRequest;
import com.qifan.shangjia.network.response.GradObj;
import com.qifan.shangjia.network.response.WithdrawalsObj;
import com.qifan.shangjia.tools.MaxTextLengthFilter;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/5/4.
 */

public class WithdrawalsActivity extends BaseActivity {



    @BindView(R.id.et_money)
    MyEditText etMoney;

    @BindView(R.id.tv_commit)
    MyTextView tvCommit;

    @BindView(R.id.app_right_tv)
    TextView app_right_tv;

    @BindView(R.id.tv_wallet_price)
    TextView tv_wallet_price;

    @BindView(R.id.tv_in_transit_price)
    TextView tv_in_transit_price;


    @BindView(R.id.tv_enable_withdrawal)
    TextView tv_enable_withdrawal;

   private  double enable_money; //可取金额
    private static final int DEFAULT_DECIMAL_NUMBER = 2;
    /**
     * 保留小数点后多少位
     */
    private int mDecimalNumber = DEFAULT_DECIMAL_NUMBER;

    @Override
    protected int getContentView() {
        setAppTitle("钱包提现");
        return R.layout.activity_withdrawal;
    }

    @Override
    protected void initView() {
        app_right_tv.setText("明细");
        InputFilter[] filters = {new MaxTextLengthFilter(mContext,10,true,"请重新输入")};
        etMoney.setFilters(filters);


    }
    @Override
    protected void initData() {
        showProgress();
        Map<String, String> map = new HashMap<String, String>();
        map.put("userId", SPUtils.getPrefString(mContext, Config.user_id, null));
        map.put("sign", GetSign.getSign(map));
        ApiRequest.Gettixian(map, new MyCallBack<WithdrawalsObj>(mContext, pcfl, pl_load) {
            @Override
            public void onSuccess(WithdrawalsObj walletObj) {
                tv_wallet_price.setText(walletObj.getBalance());
                tv_in_transit_price.setText(walletObj.getAuditBalance());
                enable_money =  Double.valueOf(walletObj.getBalance());
            }
        });
    }

    @OnClick({R.id.tv_commit,R.id.app_right_tv})
    protected void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.app_right_tv:
                STActivity(WithdrawalDetailActivity.class);
                break;
            case R.id.tv_commit:
                double edit_money = Double.valueOf(etMoney.getText().toString());
                if(edit_money>enable_money){
                    showMsg("请输入正确的金额");
                }else if(enable_money<=0.01){
                    showMsg("请输入正确的金额");
                }else{
                    showProgress();
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("userId", SPUtils.getPrefString(mContext, Config.user_id, null));
                    map.put("withdrawal",etMoney.getText().toString());
                    map.put("sign", GetSign.getSign(map));
                    ApiRequest.startWithdrawals(map, new MyCallBack<Object>(mContext, pcfl, pl_load) {
                        @Override
                        public void onSuccess(Object obj) {
                               showMsg("申请提现成功");
                               finish();
                        }
                    });
                }
                break;
        }
    }

}
