package com.qifan.shangjia;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.github.androidtools.SPUtils;
import com.github.androidtools.StatusBarUtils;
import com.github.baseclass.view.MyDialog;
import com.github.customview.MyRadioButton;
import com.qifan.shangjia.base.BaseActivity;
import com.qifan.shangjia.fragment.Message1Fragment;
import com.qifan.shangjia.fragment.MessageFragment;
import com.qifan.shangjia.fragment.MyFragment;


import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {



    MyFragment myFragment;
    MessageFragment messageFragment;
    Message1Fragment message1Fragment;

    @BindView(R.id.layout_main_content)
    FrameLayout layout_main_content;
    @BindView(R.id.rb_home)
    MyRadioButton rb_home;
    @BindView(R.id.rb_message)
    MyRadioButton rb_message;
    @BindView(R.id.rb_message1)
    MyRadioButton rb_message1;


    private MyRadioButton selectButton;

    private LocalBroadcastManager localBroadcastManager;
//    private MyOperationBro myOperationBro;

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }


//    @Override
//    protected void initRxBus() {
//        super.initRxBus();
//        getRxBusEvent(MoreCategoryEvent.class, new MySubscriber<MoreCategoryEvent>() {
//            @Override
//            public void onMyNext(MoreCategoryEvent event) {
////                Toast.makeText(MainActivity.this,"ConversationActivity",Toast.LENGTH_SHORT).show();
//                selectGoods();
//                RxBus.getInstance().postSticky(new SelectGoodsCategoryEvent(event.typeId,event.typeName));
//                selectButton.setChecked(true);
////                goodsClassFragment.setTypeId(event.typeId,event.typeName);
//            }
//        });
//        getRxBusEvent(SelectSheQuEvent.class, new MySubscriber() {
//            @Override
//            public void onMyNext(Object o) {
//                selectSheQu();
//                selectButton.setChecked(true);
//            }
//        });
//    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        if(intent==null){
            return;
        }else if(Config.backHome.equals(intent.getAction())){
            selectHome();
            selectButton.setChecked(true);
        }else if(Config.useVoucher.equals(intent.getAction())){
//            selectGoods();
            selectButton.setChecked(true);
        }
    }
    @Override
    protected void initView() {
//        // 推送
//        String registrationID = JPushInterface.getRegistrationID(mContext);
//        android.util.Log.i("registrationID","registrationID====="+registrationID);
//        if(!TextUtils.isEmpty(registrationID)){
//            SPUtils.setPrefString(mContext,Config.jiguangRegistrationId,registrationID);
//        }

        boolean isUpdatePwd = SPUtils.getPrefBoolean(mContext, Config.isUpdatePWD, false);
        if(isUpdatePwd){
            SPUtils.removeKey(mContext,Config.isUpdatePWD);
            SPUtils.removeKey(mContext,Config.user_id);
        }
//        setBroadcast();
//        int statusBarHeight = StatusBarUtils.getStatusBarHeight(this);
//        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
//        layoutParams.height = statusBarHeight;
//        status_bar.setLayoutParams(layoutParams);
//        status_bar.setBackgroundColor(getResources().getColor(R.color.red));//状态栏颜色

        selectButton = rb_home;
        myFragment= new MyFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.layout_main_content, myFragment).commitAllowingStateLoss();

    }
//    private void setBroadcast() {
//        localBroadcastManager = LocalBroadcastManager.getInstance(this);
//        myOperationBro = new MyOperationBro(new MyOperationBro.LoginBroInter() {
//            @Override
//            public void loginSuccess() {
//                selectMy();
//                selectButton.setChecked(true);
//
////                registerHuanXin();
//            }
//
//            @Override
//            public void exitLogin() {
//                selectHome();
//                selectButton.setChecked(true);
//                myFragment=null;
//            }
//        });
//        //注册广播
//        localBroadcastManager.registerReceiver(myOperationBro, new IntentFilter(Config.Bro.operation));
//    }

    //    private void registerHuanXin() {
//        RXStart(new IOCallBack<String>() {
//            @Override
//            public void call(Subscriber<? super String> subscriber) {
//                if(TextUtils.isEmpty(SPUtils.getPrefString(mContext,Config.phone,null))){
//                    return;
//                }
//                ChatClient.getInstance().createAccount(SPUtils.getPrefString(mContext,Config.phone,null), "123456", new Callback(){
//                    @Override
//                    public void onSuccess() {
//                    }
//                    @Override
//                    public void onError(int i, String s) {
//                        Log.i("===",i+"=onError=="+s);
//                    }
//                    @Override
//                    public void onProgress(int i, String s) {
//                        Log.i("===",i+"=onProgress=="+s);
//                    }
//                });
//            }
//
//            @Override
//            public void onMyNext(String s) {
//
//            }
//        });
//    }
    @Override
    protected void initData() {

    }
//    private void updateApp() {
//        Map<String,String>map=new HashMap<String,String>();
//        map.put("rnd",getRnd());
//        map.put("sign",GetSign.getSign(map));
//        ApiRequest.getAppVersion(map, new MyCallBack<AppVersionObj>(mContext) {
//            @Override
//            public void onSuccess(AppVersionObj obj) {
//                if(obj.getAndroid_version()>getAppVersionCode()){
//                    SPUtils.setPrefString(mContext,Config.appDownloadUrl,obj.getAndroid_vs_url());
//                    SPUtils.setPrefBoolean(mContext,Config.appHasNewVersion,true);
//                    MyDialog.Builder mDialog=new MyDialog.Builder(mContext);
//                    mDialog.setMessage("检测到app有新版本是否更新?");
//                    mDialog.setNegativeButton(new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            dialog.dismiss();
//                        }
//                    });
//                    mDialog.setPositiveButton(new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            dialog.dismiss();
//                            AppInfo info = new AppInfo();
//                            info.setUrl(obj.getAndroid_vs_url());
//                            info.setHouZhui(".apk");
//                            info.setFileName("jintang");
//                            info.setId(obj.getAndroid_version() + "");
//                            downloadApp(info);
//                        }
//                    });
//                    mDialog.create().show();
//                }else{
//                    SPUtils.setPrefBoolean(mContext,Config.appHasNewVersion,false);
//                }
//            }
//        });
//    }
//
//    private void downloadApp(AppInfo info) {
//        MyAPPDownloadService.intentDownload(mContext, info);
//    }
//    public int getAppVersionCode() {
//        Context context=mContext;
//        int versioncode = 1;
//        try {
//            PackageManager pm = context.getPackageManager();
//            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
//            String versionName = pi.versionName;
//            versioncode = pi.versionCode;
//            return versioncode;
//        } catch (Exception e) {
//            android.util.Log.e("VersionInfo", "Exception", e);
//        }
//        return versioncode;
//    }

    //    public void saveImg(String url){
//        FutureTarget<File> future = Glide.with(mContext)
//                .load(url)
//                .downloadOnly(PhoneUtils.getScreenWidth(this),PhoneUtils.getScreenHeight(this));
//        try {
//            File cacheFile = future.get();
//            String path = cacheFile.getAbsolutePath();
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//    }
//    private void getZhiFuNotifyUrl(String type) {
//        Map<String,String> map=new HashMap<String,String>();
//        map.put("payment_type",type);
//        map.put("sign", GetSign.getSign(map));
//        ApiRequest.getPayNotifyUrl(map, new MyCallBack<BaseObj>(mContext) {
//            @Override
//            public void onSuccess(BaseObj obj) {
//                if(obj.getPayment_type()==1){
//                    SPUtils.setPrefString(mContext,Config.payType_ZFB,obj.getPayment_url());
//                }else{
//                    SPUtils.setPrefString(mContext,Config.payType_WX,obj.getPayment_url());
//                }
//            }
//        });
//    }
    @OnClick({R.id.rb_home, R.id.rb_message, R.id.rb_message1})
    protected void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.rb_home:
                selectHome();
                break;
            case R.id.rb_message://去分类
                selectMessage();
                break;
            case R.id.rb_message1:
                selectMessage1();
                break;
        }
    }

    private void selectHome() {
        selectButton = rb_home;
        if (myFragment == null) {
            myFragment = new MyFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.layout_main_content, myFragment).commitAllowingStateLoss();
        } else {
            showFragment(myFragment);
        }
        hideFragment(messageFragment);
        hideFragment(message1Fragment);

    }

    private void selectMessage() {
        selectButton = rb_message;
        if (messageFragment == null) {
            messageFragment = new MessageFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.layout_main_content, messageFragment).commitAllowingStateLoss();
        } else {
            showFragment(messageFragment);
        }
        hideFragment(myFragment);
        hideFragment(message1Fragment);
    }

    private void selectMessage1() {
        selectButton = rb_message1;
        if (message1Fragment == null) {
            message1Fragment = new Message1Fragment();
            getSupportFragmentManager().beginTransaction().add(R.id.layout_main_content, message1Fragment).commitAllowingStateLoss();
        } else {
            showFragment(message1Fragment);
        }
        hideFragment(myFragment);
        hideFragment(messageFragment);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //x 取消注册广播,防止内存泄漏
//        if (localBroadcastManager != null) {
//            localBroadcastManager.unregisterReceiver(myOperationBro);
//        }
    }

    private long mExitTime;

    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - mExitTime) > 1500) {
            showToastS("再按一次退出程序");
            mExitTime = System.currentTimeMillis();
        } else {
            super.onBackPressed();
        }
    }

    private boolean isHorMove=false;
    private float moveX;
    private float moveY;
    public int getScaledTouchSlop(){
        ViewConfiguration conf = ViewConfiguration.get(mContext);
        return conf.getScaledTouchSlop() * 2;
    }

    //判断刷新否
    @Override
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                moveX=motionEvent.getX();
                moveY=motionEvent.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                if(!isHorMove){
                    if(isHorMove==false&&(Math.abs(moveY)-Math.abs(motionEvent.getY())>75||Math.abs(Math.abs(moveX)-Math.abs(motionEvent.getX()))<95)){
                        isHorMove=false;
                    }else if(Math.abs(moveX-motionEvent.getX())>=65&&moveY-motionEvent.getY()<=55){
                        isHorMove=true;
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                isHorMove=false;
                break;
        }
        return super.dispatchTouchEvent(motionEvent);
    }
}
