package com.qifan.shangjia.base;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.TextView;

import com.github.androidtools.ClickUtils;
import com.github.androidtools.PhoneUtils;
import com.github.androidtools.SPUtils;
import com.github.baseclass.fragment.IBaseFragment;
import com.github.baseclass.rx.RxBus;
//import com.hyphenate.chat.ChatClient;
//import com.hyphenate.helpdesk.callback.Callback;
//import com.hyphenate.helpdesk.easeui.util.IntentBuilder;
import com.qifan.shangjia.Config;
import com.qifan.shangjia.GetSign;
import com.qifan.shangjia.R;
//import com.qifan.shangjia.service.LocationServices;
import com.qifan.shangjia.view.ProgressLayout;
//import com.sk.yangyu.Config;
//import com.sk.yangyu.GetSign;
//import com.sk.yangyu.module.my.network.ApiRequest;
//import com.sk.yangyu.module.my.network.response.FenXiangObj;
//import com.sk.yangyu.view.ProgressLayout;
//import com.umeng.socialize.ShareAction;
//import com.umeng.socialize.UMShareAPI;
//import com.umeng.socialize.UMShareListener;
//import com.umeng.socialize.bean.SHARE_MEDIA;
//import com.umeng.socialize.media.UMImage;
//import com.umeng.socialize.media.UMWeb;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;


/**
 * Created by Administrator on 2017/7/13.
 */

public abstract class BaseFragment extends IBaseFragment implements View.OnClickListener,ProgressLayout.OnAgainInter{
    protected int pageNum=2;
    protected int pageSize=20;

    private boolean isFirstLoadData=true;
    private boolean isPrepared;
    protected PtrClassicFrameLayout pcfl;
    /************************************************/
    protected abstract int getContentView();
    protected abstract void initView();
    protected abstract void initData();
    protected abstract void onViewClick(View v);
    protected void initRxBus(){};
    protected boolean isPause;

//    public LocationClient mLocationClient = null;
//    private MyLocationListener myListener = new MyLocationListener();
    protected void myReStart() {
    }
    protected Unbinder mUnBind;

    protected ProgressLayout pl_load;//进度框

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(getContentView(), container, false);
        mUnBind = ButterKnife.bind(this, view);

//        mLocationClient = new LocationClient(mContext);
//        //声明LocationClient类
//        mLocationClient.registerLocationListener(myListener);
//        //注册监听函数
//        LocationClientOption option = new LocationClientOption();
//
//        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
//        //可选，设置定位模式，默认高精度
////LocationMode.Hight_Accuracy：高精度；
////LocationMode. Battery_Saving：低功耗；
////LocationMode. Device_Sensors：仅使用设备；
//
//        option.setCoorType("bd09ll");
////可选，设置返回经纬度坐标类型，默认gcj02
////gcj02：国测局坐标；
////bd09ll：百度经纬度坐标；
////bd09：百度墨卡托坐标；
////海外地区定位，无需设置坐标类型，统一返回wgs84类型坐标
//
//        option.setScanSpan(5000);
////可选，设置发起定位请求的间隔，int类型，单位ms
////如果设置为0，则代表单次定位，即仅定位一次，默认为0
////如果设置非0，需设置1000ms以上才有效
//
//        option.setOpenGps(true);
////可选，设置是否使用gps，默认false
////使用高精度和仅用设备两种定位模式的，参数必须设置为true
//
//        option.setLocationNotify(true);
////可选，设置是否当GPS有效时按照1S/1次频率输出GPS结果，默认false
//
//        option.setIgnoreKillProcess(false);
////可选，定位SDK内部是一个service，并放到了独立进程。
////设置是否在stop的时候杀死这个进程，默认（建议）不杀死，即setIgnoreKillProcess(true)
//
//        option.SetIgnoreCacheException(false);
////可选，设置是否收集Crash信息，默认收集，即参数为false
//
//        option.setWifiCacheTimeOut(5*60*1000);
////可选，7.2版本新增能力
////如果设置了该接口，首次启动定位时，会先判断当前WiFi是否超出有效期，若超出有效期，会先重新扫描WiFi，然后定位
//
//        option.setEnableSimulateGps(false);
////可选，设置是否需要过滤GPS仿真结果，默认需要，即参数为false
//
//        mLocationClient.setLocOption(option);
////mLocationClient为第二步初始化过的LocationClient对象
////需将配置好的LocationClientOption对象，通过setLocOption方法传递给LocationClient对象使用
////更多LocationClientOption的配置，请参照类参考中LocationClientOption类的详细说明
//        mLocationClient.start();
        return view;
    }
//    public class MyLocationListener extends BDAbstractLocationListener {
//        @Override
//        public void onReceiveLocation(BDLocation location) {
//            //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
//            //以下只列举部分获取经纬度相关（常用）的结果信息
//            //更多结果信息获取说明，请参照类参考中BDLocation类中的说明
//
//            double latitude = location.getLatitude();    //获取纬度信息
//            double longitude = location.getLongitude();    //获取经度信息
//            float radius = location.getRadius();    //获取定位精度，默认值为0.0f
//            Log.d("tag", "latlng.lat111111111111111="+latitude);
//            Log.d("tag", "latlng.lng2222222222222222="+longitude);
//            RxBus.getInstance().post(new LocationEvent(longitude+","+latitude));
//            String coorType = location.getCoorType();
//            //获取经纬度坐标类型，以LocationClientOption中设置过的坐标类型为准
//
//            int errorCode = location.getLocType();
//            //获取定位类型、定位错误返回码，具体信息可参照类参考中BDLocation类中的说明
//        }
//    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        Intent startIntent = new Intent(getActivity(), LocationServices.class);
//        getActivity().startService(startIntent);


        if(null!=view.findViewById(R.id.pcfl_refresh)){
            pcfl = (PtrClassicFrameLayout) view.findViewById(R.id.pcfl_refresh);
            pcfl.setLastUpdateTimeRelateObject(this);
            pcfl.setOffsetXFlag(3);
            pcfl.setHorizontalMoveFlag(3);
            pcfl.setScaledTouchSlopFlag(1);
            pcfl.disableWhenHorizontalMove(true);
        }
        if(null!=view.findViewById(R.id.pl_load)){
            pl_load = (ProgressLayout) view.findViewById(R.id.pl_load);
            pl_load.setInter(this);
        }
        initView();
        initRxBus();
        isPrepared=true;
        setUserVisibleHint(true);
    }
    public void showProgress(){
        if (pl_load != null) {
            pl_load.showProgress();
        }
    }
    public void showContent(){
        if (pl_load != null) {
            pl_load.showContent();
        }
    }
    public void showErrorText(){
        if (pl_load != null) {
            pl_load.showErrorText();
        }
    }
    @Override
    public void onPause() {
        super.onPause();
        isPause =true;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(hidden){
            isPause =true;
        }else{
            isPause =false;
            myReStart();
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        if(isPause){
            isPause =false;
            myReStart();
        }
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isFirstLoadData&&isPrepared&&getUserVisibleHint()&&isVisibleToUser){
            initData();
            isFirstLoadData=false;
        }
    }
    protected String getSStr(View view){
        if(view instanceof TextView){
            return ((TextView)view).getText().toString();
        } else if (view instanceof EditText) {
            return ((EditText)view).getText().toString();
        }else{
            return null;
        }
    }
    @Override
    public void onClick(View v) {
        if(!ClickUtils.isFastClick(v)){
            onViewClick(v);
        }
    }
    public void onDestroy() {
        super.onDestroy();
        mUnBind.unbind();
        RxBus.getInstance().removeAllStickyEvents();
    }

    @Override
    public void again() {
        initData();
    }
    public int getUserType(){
        return SPUtils.getPrefInt(mContext, Config.userType,-1);
    }
    protected String getUserId(){
        return SPUtils.getPrefString(mContext,Config.user_id,null);
    }
    protected String getUsersId(){
        return SPUtils.getPrefString(mContext,Config.user_id,"0");
    }
    protected String getSign(){
        return getSign("user_id",getUserId());
    }
    protected String getSign(String key,String value){
        return GetSign.getSign(key,value);
    }
    protected boolean isEmpty(List list){
        return list == null || list.size() == 0;
    }
    protected boolean notEmpty(List list){
        return !(list == null || list.size() == 0);
    }
    protected String getRnd(){
        Random random = new Random();
        int rnd = random.nextInt(9000) + 1000;
        return rnd+"";
    }
    protected BaseDividerListItem getItemDivider(){
        return new BaseDividerListItem(mContext,BaseDividerListItem.VERTICAL_LIST,2,R.color.background_f2);
    }
    protected BaseDividerListItem getItemDivider(int height){
        return new BaseDividerListItem(mContext,BaseDividerListItem.VERTICAL_LIST,height,R.color.background_f2);
    }
    protected BaseDividerListItem getItemDivider(int height,int color){
        return new BaseDividerListItem(mContext,BaseDividerListItem.VERTICAL_LIST,height,color);
    }


    protected void initWebViewForContent(WebView webview, String content) {
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setTextSize(WebSettings.TextSize.NORMAL);
        //自适应屏幕  
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setLoadWithOverviewMode(true);
        webview.getSettings().setDefaultTextEncodingName("UTF -8");//设置默认为utf-8
        //设置Web视图
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
//                initWebTopView();
            }
        });

        webview.loadDataWithBaseURL(null, getNewContent(content), "text/html", "utf-8",null);
//        webview.loadUrl(url);
        // 设置WevView要显示的网页
//        webview.loadDataWithBaseURL(null, content, "text/html", "utf-8",null);
        webview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webview.getSettings().setJavaScriptEnabled(true); //设置支持Javascript
        webview.requestFocus(); //触摸焦点起作用.如果不设置，则在点击网页文本输入框时，不能弹出软键盘及不响应其他的一些事件。
        webview.setWebViewClient(new WebViewClient()
        {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url)
            {
                view.loadUrl(url);
                return true;
            }
        });
    }
    protected void initWebViewForUrl(WebView webview,String url) {
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setTextSize(WebSettings.TextSize.NORMAL);
        //自适应屏幕  
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setLoadWithOverviewMode(true);
        webview.getSettings().setDefaultTextEncodingName("UTF -8");//设置默认为utf-8
        //设置Web视图
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
//                initWebTopView();
            }
        });

//        webview.loadDataWithBaseURL(null, getNewContent(url), "text/html", "utf-8",null);
        webview.loadUrl(url);
        // 设置WevView要显示的网页
//        webview.loadDataWithBaseURL(null, content, "text/html", "utf-8",null);
        webview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webview.getSettings().setJavaScriptEnabled(true); //设置支持Javascript
        webview.requestFocus(); //触摸焦点起作用.如果不设置，则在点击网页文本输入框时，不能弹出软键盘及不响应其他的一些事件。
        webview.setWebViewClient(new WebViewClient()
        {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url)
            {
                view.loadUrl(url);
                return true;
            }
        });
    }


    protected static String getNewContent(String htmltext){
        try {
            Document doc= Jsoup.parse(htmltext);
            Elements elements=doc.getElementsByTag("img");
            for (Element element : elements) {
                element.attr("width","100%").attr("height","auto");
            }
            return doc.toString();
        } catch (Exception e) {
            return htmltext;
        }
    }

//    public void goHX(){
//        if(ChatClient.getInstance().isLoggedInBefore()){
//            //已经登录，可以直接进入会话界面
//            OpenHuanXin();
//        }else{
//            showLoading();
//            hxName=SPUtils.getPrefString(mContext,Config.phone,null);
//            if(TextUtils.isEmpty(hxName)){
//                Random random = new Random();
//                int rn1=random.nextInt(9000) + 1000;
//                int rn2=random.nextInt(9000) + 1000;
//                hxName=rn1+""+rn2;
//                ChatClient.getInstance().createAccount(hxName, "123456", new Callback(){
//                    @Override
//                    public void onSuccess() {
//                        loginHXSuccess(hxName);
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
//            }else{
//                loginHXSuccess(hxName);
//            }
//        }
//    }
    String hxName;
//    private void loginHXSuccess(String hxName) {
//        //未登录，需要登录后，再进入会话界面
//        ChatClient.getInstance().login(hxName,"123456", new Callback(){
//            @Override
//            public void onSuccess() {
//                dismissLoading();
//                OpenHuanXin();
//            }
//            @Override
//            public void onError(int i, String s) {
//                dismissLoading();
//
//            }
//            @Override
//            public void onProgress(int i, String s) {
//            }
//        });
//    }
//    private void OpenHuanXin() {
//        Intent intent = new IntentBuilder(mContext)
//                .setServiceIMNumber(Config.hx_fwh) //获取地址：kefu.easemob.com，“管理员模式 > 渠道管理 > 手机APP”页面的关联的“IM服务号”
//                .build();
//        startActivity(intent);
//    }
//    /*****************************************************************第三方分享********************************************************************************/
//    protected void fenXiang(SHARE_MEDIA shareMedia,String  fenXiangId) {
//        showLoading();
//        Map<String,String> map=new HashMap<String,String>();
//        map.put("goods_id",fenXiangId);
//        map.put("sign",GetSign.getSign(map));
//        ApiRequest.fenXiang(map, new MyCallBack<FenXiangObj>(mContext,true) {
//            @Override
//            public void onSuccess(FenXiangObj obj) {
//                UMWeb web = new UMWeb(obj.getShare_link());
//                UMImage image=new UMImage(mContext,R.drawable.app_default);
//                web.setTitle(obj.getTitle());//标题
//                web.setThumb(image);  //缩略图
//                web.setDescription(obj.getContent());//描述
//                new ShareAction(mContext)
//                        .setPlatform(shareMedia)//传入平台
//                        .withMedia(web)
////                      .withText(getSStr(tv_fenxiao_detail_code))//分享内容
//                        .setCallback(getListener())
//                        .share();
//            }
//            @Override
//            public void onError(Throwable e) {
//                super.onError(e);
//                dismissLoading();
//            }
//        });
//    }
//    protected UMShareListener getListener() {
//        return new UMShareListener() {
//            @Override
//            public void onStart(SHARE_MEDIA share_media) {
//                dismissLoading();
//                Log.i("============","============onStart");
//            }
//
//            @Override
//            public void onResult(SHARE_MEDIA share_media) {
//                Log.i("============","============onResult");
//            }
//
//            @Override
//            public void onError(SHARE_MEDIA share_media, Throwable throwable) {
//                showMsg(throwable.getMessage());
//                Log.i("============","============onError");
//            }
//
//            @Override
//            public void onCancel(SHARE_MEDIA share_media) {
//                Log.i("============","============onCancel");
//            }
//        };
//    }
//    BottomSheetDialog fenXiangDialog;
//    public void showFenXiang(String fenXiangId){
//        if (fenXiangDialog == null) {
//            View sexView= LayoutInflater.from(mContext).inflate(R.layout.popu_fen_xiang,null);
//            /*iv_yaoqing_wx
//iv_yaoqing_friend
//iv_yaoqing_qq
//iv_yaoqing_qzone
//iv_yaoqing_sina*/
//            sexView.findViewById(R.id.iv_yaoqing_wx).setOnClickListener(new MyOnClickListener() {
//                @Override
//                protected void onNoDoubleClick(View view) {
//                    if (!UMShareAPI.get(mContext).isInstall(mContext, SHARE_MEDIA.WEIXIN)) {
//                        showMsg("请安装微信之后再试");
//                        return;
//                    }
//                    fenXiang(SHARE_MEDIA.WEIXIN,fenXiangId);
//                    fenXiangDialog.dismiss();
//
//                }
//            });
//            sexView.findViewById(R.id.iv_yaoqing_friend).setOnClickListener(new MyOnClickListener() {
//                @Override
//                protected void onNoDoubleClick(View view) {
//                    if (!UMShareAPI.get(mContext).isInstall(mContext, SHARE_MEDIA.WEIXIN)) {
//                        showMsg("请安装微信之后再试");
//                        return;
//                    }
//                    fenXiang(SHARE_MEDIA.WEIXIN_CIRCLE,fenXiangId);
//                    fenXiangDialog.dismiss();
//
//                }
//            });
//            sexView.findViewById(R.id.iv_yaoqing_qq).setOnClickListener(new MyOnClickListener() {
//                @Override
//                protected void onNoDoubleClick(View view) {
//                    if (!UMShareAPI.get(mContext).isInstall(mContext, SHARE_MEDIA.QQ)) {
//                        showMsg("请安装QQ之后再试");
//                        return;
//                    }
//                    fenXiang(SHARE_MEDIA.QQ,fenXiangId);
//                    fenXiangDialog.dismiss();
//                }
//            });
//            sexView.findViewById(R.id.iv_yaoqing_qzone).setOnClickListener(new MyOnClickListener() {
//                @Override
//                protected void onNoDoubleClick(View view) {
//                    if (!UMShareAPI.get(mContext).isInstall(mContext, SHARE_MEDIA.QQ)) {
//                        showMsg("请安装QQ之后再试");
//                        return;
//                    }
//                    fenXiang(SHARE_MEDIA.QZONE,fenXiangId);
//                    fenXiangDialog.dismiss();
//                }
//            });
//           /* sexView.findViewById(R.id.iv_yaoqing_sina).setOnClickListener(new MyOnClickListener() {
//                @Override
//                protected void onNoDoubleClick(View view) {
//                    showMsg("正在开发中");
//                    fenXiangDialog.dismiss();
//                }
//            });*/
//            sexView.findViewById(R.id.tv_fenxiang_cancle).setOnClickListener(new MyOnClickListener() {
//                @Override
//                protected void onNoDoubleClick(View view) {
//                    fenXiangDialog.dismiss();
//                }
//            });
//            fenXiangDialog=new BottomSheetDialog(mContext);
//            fenXiangDialog.setCanceledOnTouchOutside(true);
//            fenXiangDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            fenXiangDialog.setContentView(sexView);
//        }
//        fenXiangDialog.show();
//    }
    public boolean keJian(View view){
        int screenWidth= PhoneUtils.getScreenWidth(mContext);
        int screenHeight=PhoneUtils.getScreenHeight(mContext);

        Rect rect=new Rect(0,0,screenWidth,screenHeight );
        int[] location = new int[2];
        view.getLocationInWindow(location);
        System.out.println(Arrays.toString(location));
        // Rect ivRect=new Rect(imageView.getLeft(),imageView.getTop(),imageView.getRight(),imageView.getBottom());
        if (view.getLocalVisibleRect(rect)) {/*rect.contains(ivRect)*/
            return true;
        } else {
            return false;
        }
    }

}
