package com.qifan.shangjia.activity;

import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;

import com.github.customview.MyEditText;
import com.qifan.shangjia.GetSign;
import com.qifan.shangjia.R;
import com.qifan.shangjia.base.BaseActivity;
import com.qifan.shangjia.tools.StatusBarUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by administartor on 2017/9/5.
 */

public class ForgetPasswordActivity extends BaseActivity {
    @BindView(R.id.et_register_phone)
    MyEditText et_register_phone;
    @BindView(R.id.et_register_pwd)
    MyEditText et_register_pwd;
    @BindView(R.id.et_register_repwd)
    MyEditText et_register_repwd;

    private String smsCode,yaoQingMa;
    @Override
    protected int getContentView() {
        setAppTitle("找回密码");
        return R.layout.activity_forget_password;
    }

    @Override
    protected void initView() {
        StatusBarUtil.setStatusBarColor(ForgetPasswordActivity.this,getResources().getColor(R.color.red));
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.app_right_tv,R.id.tv_register_commit})
    protected void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.app_right_tv:
                finish();
                break;
            case R.id.tv_register_getcode:
                if(TextUtils.isEmpty(getSStr(et_register_phone))){
                    showMsg("手机号不能为空");
                    return;
                }else if(!GetSign.isMobile(getSStr(et_register_phone))){
                    showMsg("请输入正确手机号");
                    return;
                }
                getMsgCode();
                break;
            case R.id.tv_register_commit:
                String phone=getSStr(et_register_phone);
                String pwd=getSStr(et_register_pwd);
                String rePwd=getSStr(et_register_repwd);

                if(TextUtils.isEmpty(getSStr(et_register_phone))){
                    showMsg("手机号不能为空");
                    return;
                }else if(!GetSign.isMobile(getSStr(et_register_phone))){
                    showMsg("请输入正确手机号");
                    return;
                }else if(TextUtils.isEmpty(pwd)){
                    showMsg("密码不能为空");
                    return;
                }else if(!pwd.equals(rePwd)){
                    showMsg("两次密码不一样");
                    return;
                }
                register(phone,pwd);
                break;
        }
    }
    private void register(String phone, String pwd) {
        showLoading();
//        Map<String,String>map=new HashMap<String,String>();
//        map.put("username",phone);
//        map.put("password",pwd);
//        map.put("distribution_yard",yaoQingMa);
//        map.put("sign",GetSign.getSign(map));
//        ApiRequest.register(map, new MyCallBack<BaseObj>(mContext) {
//            @Override
//            public void onSuccess(BaseObj obj) {
//                showMsg(obj.getMsg());
//                finish();
//            }
//        });
    }

    private void getMsgCode() {
        showLoading();
        Map<String, String> map = new HashMap<String, String>();
        map.put("mobile",getSStr(et_register_phone));
        map.put("rnd",getRnd());
        String sign = GetSign.getSign(map);
        map.put("sign", sign);
        showLoading();
//        ApiRequest.getSMSCode(map, new MyCallBack<BaseObj>(mContext) {
//            @Override
//            public void onSuccess(BaseObj obj) {
//                smsCode = obj.getSMSCode();
//                countDown();
//            }
//        });

    }


}
