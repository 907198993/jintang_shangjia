package com.qifan.shangjia.activity;

import android.text.TextUtils;
import android.view.View;

import com.github.androidtools.SPUtils;
import com.github.customview.MyEditText;
import com.qifan.shangjia.Config;
import com.qifan.shangjia.GetSign;
import com.qifan.shangjia.MainActivity;
import com.qifan.shangjia.R;
import com.qifan.shangjia.base.BaseActivity;
import com.qifan.shangjia.base.MyCallBack;
import com.qifan.shangjia.network.ApiRequest;
import com.qifan.shangjia.network.response.LoginObj;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/4/12.
 */

public class LoginActivity extends BaseActivity {
    @BindView(R.id.et_login_phone)
    MyEditText etLoginPhone;
    @BindView(R.id.et_login_pwd)
    MyEditText etLoginPwd;

    @Override
    protected int getContentView() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.tv_login_commit,R.id.tv_login_change_pwd})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_login_commit:
                if(TextUtils.isEmpty(getSStr(etLoginPhone))){
                    showToastS("请输入手机号");
                    return;
                }else if(TextUtils.isEmpty(getSStr(etLoginPwd))){
                    showToastS("请输入密码");
                    return;
                }
                login();
                break;
            case R.id.tv_login_change_pwd:
                STActivity(ForgetPasswordActivity.class);
                break;
        }
    }

    private void login() {
        showLoading();
        STActivity(MainActivity.class);
        finish();
//        Map<String,String> map=new HashMap<String,String>();
//        map.put("username",getSStr(etLoginPhone));
//        map.put("password",getSStr(etLoginPwd));
//        map.put("sign", GetSign.getSign(map));
//        ApiRequest.userLogin(map, new MyCallBack<LoginObj>(mContext) {
//            @Override
//            public void onSuccess(LoginObj obj) {
//                SPUtils.setPrefString(mContext, Config.user_name,getSStr(etLoginPhone));
//                SPUtils.setPrefString(mContext, Config.user_id,obj.getUserid());
//                SPUtils.setPrefString(mContext, Config.user_status,"0");//0 代表休息状态
//                STActivity(MainActivity.class);
//                finish();
//            }
//        });
    }


}
