package com.qifan.shangjia.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

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
import com.qifan.shangjia.tools.StatusBarUtil;

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
    @BindView(R.id.ll_title)
    RelativeLayout ll_title;

    @Override
    protected int getContentView() {
        return R.layout.activity_login;
    }


    private void floatStatusBar() {
        StatusBarUtil.fullScreen(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            RelativeLayout.LayoutParams titleParams = (RelativeLayout.LayoutParams) ll_title.getLayoutParams();
            // 标题栏在上方留出一段距离，看起来仍在状态栏下方
            titleParams.topMargin = StatusBarUtil.getStatusBarHeight(this);
            ll_title.setLayoutParams(titleParams);
        }
    }
    @Override
    protected void initView() {
//        setStatusBarColor(this,getResources().getColor(android.R.color.holo_red_dark));
        floatStatusBar();
//        Window window = getWindow();
//        int color = getResources().getColor(android.R.color.holo_blue_light);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
//                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            //设置状态栏颜色
//            window.setStatusBarColor(color);
//            //设置导航栏颜色
//            window.setNavigationBarColor(color);
//            ViewGroup contentView = ((ViewGroup) findViewById(android.R.id.content));
//            View childAt = contentView.getChildAt(0);
//            if (childAt != null) {
//                childAt.setFitsSystemWindows(true);
//            }
//        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            //透明状态栏
//            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            //透明导航栏
//            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//            //设置contentview为fitsSystemWindows
//            ViewGroup contentView = (ViewGroup) findViewById(android.R.id.content);
//            View childAt = contentView.getChildAt(0);
//            if (childAt != null) {
//                childAt.setFitsSystemWindows(true);
//            }
//            //给statusbar着色
//            View view = new View(this);
//            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight(this)));
//            view.setBackgroundColor(color);
//            contentView.addView(view);
//        }



    }
    /**
     * 获取状态栏高度
     *
     * @param context context
     * @return 状态栏高度
     */
    private static int getStatusBarHeight(Context context) {
        // 获得状态栏高度
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return context.getResources().getDimensionPixelSize(resourceId);
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
        Map<String,String> map=new HashMap<String,String>();
        map.put("username",getSStr(etLoginPhone));
        map.put("password",getSStr(etLoginPwd));
        map.put("sign", GetSign.getSign(map));
        ApiRequest.userLogin(map, new MyCallBack<LoginObj>(mContext) {
            @Override
            public void onSuccess(LoginObj obj) {
                SPUtils.setPrefString(mContext, Config.user_name,getSStr(etLoginPhone));
                STActivity(MainActivity.class);
                finish();
            }
        });
    }


}
