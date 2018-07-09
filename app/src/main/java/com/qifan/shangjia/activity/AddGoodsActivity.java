package com.qifan.shangjia.activity;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.IdRes;
import android.support.v7.widget.AppCompatSpinner;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.Transformation;
import com.foamtrace.photopicker.ImageCaptureManager;
import com.foamtrace.photopicker.PhotoPickerActivity;
import com.foamtrace.photopicker.PhotoPreviewActivity;
import com.foamtrace.photopicker.SelectModel;
import com.foamtrace.photopicker.intent.PhotoPickerIntent;
import com.foamtrace.photopicker.intent.PhotoPreviewIntent;
import com.github.androidtools.SPUtils;
import com.github.baseclass.rx.IOCallBack;
import com.github.customview.MyEditText;
import com.github.customview.MyImageView;
import com.github.customview.MyTextView;
import com.qifan.shangjia.Config;
import com.qifan.shangjia.Constant;
import com.qifan.shangjia.GetSign;
import com.qifan.shangjia.MainActivity;
import com.qifan.shangjia.R;
import com.qifan.shangjia.adapter.GridViewAddImgesAdpter;
import com.qifan.shangjia.base.BaseActivity;
import com.qifan.shangjia.base.BaseObj;
import com.qifan.shangjia.base.MyCallBack;
import com.qifan.shangjia.network.ApiRequest;
import com.qifan.shangjia.network.response.AddGoodsItem;
import com.qifan.shangjia.network.response.AddGoodsObj;
import com.qifan.shangjia.network.response.ImageData;
import com.qifan.shangjia.network.response.LoginObj;
import com.qifan.shangjia.network.response.Specification;
import com.qifan.shangjia.network.response.UploadImgItem;
import com.qifan.shangjia.tools.BitmapUtils;

import net.bither.util.NativeUtil;

import org.json.JSONArray;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import rx.Subscriber;
import top.zibin.luban.Luban;

/**
 * Created by Administrator on 2018/6/28 0028.
 */

public class AddGoodsActivity extends BaseActivity implements AdapterView.OnItemSelectedListener {
    private List<View> viewList;
    private List<ViewHolder> viewHolderList;
    private int mark = 0;
    private String classNames = "";
    private String proIds = "";

    @BindView(R.id.parent)
    LinearLayout parent;

    @BindView(R.id.gridView)
    GridView gridView;

    private int columnWidth;
    private static final int REQUEST_CAMERA_CODE = 10;
    private static final int REQUEST_PREVIEW_CODE = 20;
    private ArrayList<String> imagePaths = new ArrayList<>();
    private ImageCaptureManager captureManager; // 相机拍照处理类
    private File tempFile;
    private final String IMAGE_DIR = Environment.getExternalStorageDirectory() + "/gridview/";
    /* 头像名称 */
    private final String PHOTO_FILE_NAME = "temp_photo.jpg";
    private final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择private static final String PHOTO_FILE_NAME = "temp_photo.jpg";
    private Button mButton;
    private String depp;
    private EditText textView;
    private String TAG =AddGoodsActivity.class.getSimpleName();
    private LayoutInflater inflater;

    private List<Map<String, ImageData>> homeImagedatas; //存储主图图片地址
    private List<Map<String, ImageData>> mainImagedatas; //存储详情图片地址
    private GridViewAddImgesAdpter gridViewHomeAddImgesAdpter,gridViewMainAddImgesAdpter;

    private int flag = 0;

    // 下拉列表
    @BindView(R.id.sp1)
    Spinner sp1;
    private List<String>  list1Spinner;//父类型

    @BindView(R.id.sp2)
    Spinner sp2;
    private List<String>  list2Spinner; //子类型

    @BindView(R.id.sp3)
    Spinner sp3;
    private List<String>  list3Spinner; //子类型

    private ArrayAdapter<String> adapter1,adapter2,adapter3;
    private  AddGoodsObj  addGoodsData;

    private String goodsTypeParent;
    private String goodsTypeSon;
    private String storeType;

    ///////////出售状态
    @BindView(R.id.radiogroup_status)
    RadioGroup radiogroup_status;
    private String goodsStatus="1";
    //是否店铺首页推荐
    @BindView(R.id.radio_button_main_recommend)
    RadioGroup radio_button_main_recommend;
    private String is_homeRecommend="1";

    //首页推荐顺序
    @BindView(R.id.et__homeRecommend_num)
    MyEditText et__homeRecommend_num;

    //商品名称
    @BindView(R.id.goods_name)
    MyEditText goods_name;

    @BindView(R.id.tv_home_pic)
    MyImageView tv_home_pic;
    private  String goods_image; //封面图


   //详情图片
    @BindView(R.id.gridview_detail_pic)
    GridView gridview_detail_pic;

    //产品属性标题
    @BindView(R.id.et_property_title)
    MyEditText et_property_title;

    //产品属性内容
    @BindView(R.id.et_property_content)
    MyEditText et_property_content;

    //产品原价
    @BindView(R.id.goods_price)
    MyEditText goods_price;

    //折扣比例
    @BindView(R.id.et_sale_percent)
    MyEditText et_sale_percent;

    //排序数字
    @BindView(R.id.et_orderby_number)
    MyEditText et_orderby_number;
    
    public  int   SpecificationIndex = 0;
    private  List<Specification> specifications;
    private  List<String> guigeImage = new ArrayList<>();//只存储图片的地址
    ImageData imageData = new ImageData();
    @BindView(R.id.tv_add_goods_commit)
    MyTextView tv_add_goods_commit;
    @Override
    protected int getContentView() {
        return R.layout.add_goods;
    }
    ImageView imageView ;

    private AddGoodsItem addGoodsItem = new AddGoodsItem();

    @Override
    protected void initView() {
        setAppTitle("添加商品");
//        viewList = new ArrayList<>();
//        viewHolderList = new ArrayList<>();
//        View addView = LayoutInflater.from(AddGoodsActivity.this).inflate(R.layout.item_goods_guige, null);
//        addView.setId(mark);
//        parent.addView(addView, mark);
//        getViewInstance(addView);


        tv_home_pic.setOnClickListener(v -> {
            flag = 1;
            Intent intent = new Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
        });

        //主图图片
        homeImagedatas = new ArrayList<>();
        gridViewHomeAddImgesAdpter = new GridViewAddImgesAdpter(homeImagedatas, this);
        gridView.setAdapter(gridViewHomeAddImgesAdpter);
        gridViewHomeAddImgesAdpter.setMaxImages(6);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                flag = 2;
//                PhotoPickerIntent intent1 = new PhotoPickerIntent(AddGoodsActivity.this);
//                intent1.setSelectModel(SelectModel.MULTI);
//                intent1.setShowCarema(false); // 是否显示拍照
//                intent1.setMaxTotal(5); // 最多选择照片数量，默认为9
//                intent1.setSelectedPaths(imagePaths); // 已选中的照片地址， 用于回显选中状态
//                startActivityForResult(intent1, REQUEST_CAMERA_CODE);
                    Intent intent = new Intent(
                            Intent.ACTION_PICK,
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
            }
        });

        //详情图片
        mainImagedatas = new ArrayList<>();
        gridViewMainAddImgesAdpter = new GridViewAddImgesAdpter(mainImagedatas, this);
        gridview_detail_pic.setAdapter(gridViewMainAddImgesAdpter);
        gridViewMainAddImgesAdpter.setMaxImages(6);
        gridview_detail_pic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                flag = 3;
//                PhotoPickerIntent intent1 = new PhotoPickerIntent(AddGoodsActivity.this);
//                intent1.setSelectModel(SelectModel.MULTI);
//                intent1.setShowCarema(false); // 是否显示拍照
//                intent1.setMaxTotal(5); // 最多选择照片数量，默认为9
//                intent1.setSelectedPaths(imagePaths); // 已选中的照片地址， 用于回显选中状态
//                startActivityForResult(intent1, REQUEST_CAMERA_CODE);
                Intent intent = new Intent(
                        Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
            }
        });
////        图片选择
////        gridView = (GridView) findViewById(R.id.gridView);
//        int cols = getResources().getDisplayMetrics().widthPixels / getResources().getDisplayMetrics().densityDpi;
//        cols = cols < 3 ? 3 : cols;
//
//        gridView.setNumColumns(cols);
//        int screenWidth = getResources().getDisplayMetrics().widthPixels;
//        int columnSpace = getResources().getDimensionPixelOffset(R.dimen.space_size);
//        columnWidth = (screenWidth - columnSpace * (cols-1)) / cols;
    }

//    //上传图片
//    public class ViewHolder1 {
//        public final ImageView imageView;
//        public final Button bt_del;
//        public final View root;
//
//        public ViewHolder1(View root) {
//            imageView = (ImageView) root.findViewById(R.id.imageView);
//            bt_del = (Button) root.findViewById(R.id.bt_del);
//            this.root = root;
//        }
//    }

//    private void getViewInstance(View addView) {
//        ViewHolder vh = new ViewHolder();
//        vh.id = addView.getId();
//        vh.iv_add_guige = (ImageView) addView.findViewById(R.id.iv_add_guige);
//        vh.iv_add_guige.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                View view = (View) v.getParent();
//                for (int i = 0; i < parent.getChildCount(); i++) {
//                    ViewHolder viewHolder = viewHolderList.get(i);
//                    Log.d("createclass", "view.getId()==" + v.getId() + "  viewHolder.id==" + viewHolder.id);
//                    if (view.getId() == viewHolder.id) {
////                        viewHolder.iv_add_guige.
//                    }
//                }
//            }
//        });
//
//        viewHolderList.add(vh);
//        viewList.add(addView);
//    }


    //规格
    public class ViewHolder {
        private int id ;
        private ImageView iv_add_guige;

    }
    @Override
    protected void initData() {
       getSpinnerData();
       radiogroupSaleStatus();
       radiogroupMainRecommend();
    }

    private void radiogroupMainRecommend() {
        radio_button_main_recommend.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.radio_button_main_suggest:
                        is_homeRecommend = "1";
                        showMsg("1");
                        break;
                    case R.id.radio_button_main_unsuggest:
                        is_homeRecommend = "0";
                        showMsg("0");
                        break;

                }
            }
        });
    }


    private void radiogroupSaleStatus() {
        radiogroup_status.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.radio_button_sale:
                        goodsStatus = "1";
                        showMsg("1");
                        break;
                    case R.id.radio_button_delisting:
                        goodsStatus = "0";
                        showMsg("0");
                        break;

                }
            }
        });
    }


    //下拉列表
    private void setSpinner1(){
        list1Spinner=new ArrayList<String>();
        for (AddGoodsObj.GoodsTypesBean   goodsTypeBean : addGoodsData.getGoodsTypes()) {
            String typeName = goodsTypeBean.getGoods_type_name();
            list1Spinner.add(typeName);
        }
        //      数据源与第一级的适配器
        adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                list1Spinner);
        sp1.setAdapter(adapter1);
        sp1.setOnItemSelectedListener(this);
    }
    //下拉列表
    private void setSpinner2(){
        list3Spinner=new ArrayList<String>();
        for (AddGoodsObj.StoreTypeBean   storeTypeBean : addGoodsData.getStoreType()) {
            String typeName = storeTypeBean.getTypeName();
            list3Spinner.add(typeName);
        }
        //      数据源与第一级的适配器
        adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                list3Spinner);
        sp3.setAdapter(adapter3);
        sp3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                storeType = addGoodsData.getStoreType().get(position).getId()+"";
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        AddGoodsObj.GoodsTypesBean goodsTypesBean  =  addGoodsData.getGoodsTypes().get(position);
        goodsTypeParent =goodsTypesBean.getGoods_type_id()+""; //选中的父类的值
        showMsg(goodsTypeParent+"父类");
        list2Spinner=new ArrayList<String>();
        for (AddGoodsObj.GoodsTypesBean.SonTypeBean sonTypesBean : goodsTypesBean.getSonType()) {
            String typeName = sonTypesBean.getGoods_type_name();
            list2Spinner.add(typeName);
        }
        adapter2=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                list2Spinner);
        sp2.setAdapter(adapter2);
        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                goodsTypeSon =  goodsTypesBean.getSonType().get(position).getGoods_type_id()+"";
//                showMsg(goodsTypeSon+"子类");
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    private void getSpinnerData() {
        showLoading();
        Map<String,String> map=new HashMap<String,String>();
        map.put("goodTypeParentId","0");
        map.put("userId",getUserId());
        map.put("sign", GetSign.getSign(map));
        ApiRequest.getSpinnerData(map, new MyCallBack<AddGoodsObj>(mContext) {
            @Override
            public void onSuccess(AddGoodsObj obj) {
                addGoodsData = obj;
                setSpinner1();
                setSpinner2();
            }
        });

    }


    //获取所有动态添加的Item，找到控件的id，获取数据
    private  List<Specification> printData() {
        specifications = new ArrayList<>();
        for (int i = 0; i < parent.getChildCount(); i++) {
            Specification specification = new Specification();
            View childAt = parent.getChildAt(i);
            MyEditText tv_price = (MyEditText) childAt.findViewById(R.id.tv_price);
            MyEditText tv_guige = (MyEditText) childAt.findViewById(R.id.tv_guige);
            MyEditText tv_inventory = (MyEditText) childAt.findViewById(R.id.tv_inventory);
            specification.setPrice(tv_price.getText().toString());
            specification.setSpecificationName(tv_guige.getText().toString());
            specification.setStock(tv_inventory.getText().toString());
            if(guigeImage.get(i)==""){

            }

            specification.setImages(guigeImage.get(i));
            specifications.add(specification);
            Log.e(TAG, "tv_price：" + tv_price.getText().toString() + "-----tv_guige："
                    + tv_guige.getText().toString() + "-----tv_inventory：" + tv_inventory.getText().toString());
        }
        return specifications;
    }


    @OnClick({R.id.btn_add_guige,R.id.tv_add_goods_commit})
    protected void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_guige:
                addItem();
                break;
            case R.id.tv_add_goods_commit:
                homeImagedatas.size();
                mainImagedatas.size();
                showMsg(goodsTypeParent+"父类"+goodsTypeSon+"storeType"+storeType);
                specifications = printData();

                if(TextUtils.isEmpty(getSStr(et__homeRecommend_num))){
                    showToastS("请输入首页推荐顺序");
                    return;
                }else if(TextUtils.isEmpty(getSStr(goods_name))){
                    showToastS("请输入商品名称");
                    return;
                }else if(TextUtils.isEmpty(getSStr(et_property_title))){
                    showToastS("请输入标题");
                    return;
                }else if(TextUtils.isEmpty(getSStr(et_property_content))){
                    showToastS("请输入内容");
                    return;
                }else if(TextUtils.isEmpty(getSStr(goods_price))){
                    showToastS("请输入商品原价");
                    return;
                }else if(TextUtils.isEmpty(getSStr(goods_price))){
                    showToastS("请输入折扣比例");
                    return;
                }else if(TextUtils.isEmpty(getSStr(et_sale_percent))){
                    showToastS("请输入排序数字");
                    return;
                }
                addGoodsItem.setGoodsTypeParent(goodsTypeParent);//
                addGoodsItem.setGoodsTypeSon(goodsTypeSon);
                addGoodsItem.setStoreType(storeType);
                addGoodsItem.setIs_homeRecommend(is_homeRecommend);
                addGoodsItem.setGoodsStatus(goodsStatus);
                addGoodsItem.setIs_homeRecommend_num(getSStr(et__homeRecommend_num));
                addGoodsItem.setGoodsName(getSStr(goods_name));//商品名称
                addGoodsItem.setGoods_image(goods_image);//封面图

                String paraZhutu="";
                for(int i=0;i< homeImagedatas.size();i++){
                    paraZhutu += homeImagedatas.get(i).get("ImageData").getImgName()+"|";
                }
                addGoodsItem.setZhutu_image(paraZhutu);//主图

                String paraXiangqing="";
                for(int i=0;i< mainImagedatas.size();i++){
                    paraXiangqing += mainImagedatas.get(i).get("ImageData").getImgName()+"|";
                }
                addGoodsItem.setXiangqing_image(paraXiangqing);//详情图
                addGoodsItem.setSpecification(specifications);
                addGoodsItem.setProperty_title(getSStr(et_property_title));
                addGoodsItem.setProperty_content(getSStr(et_property_content));
                addGoodsItem.setOriginal_price(getSStr(goods_price));
                addGoodsItem.setDiscount(getSStr(goods_price));
                addGoodsItem.setOrderBy(getSStr(et_sale_percent));
                addGoodsData();
                break;
        }
    }

    private void addGoodsData() {
        showLoading();
        Map<String,String> map=new HashMap<String,String>();
        map.put("userId",getUserId());
        map.put("sign", GetSign.getSign(map));
        ApiRequest.addGoods(map, addGoodsItem,new MyCallBack<LoginObj>(mContext) {
            @Override
            public void onSuccess(LoginObj obj) {

            }
        });
    }

    /**
     * 添加新view
     */
    private void addItem()
    {
        SpecificationIndex++;
        Log.d(TAG, "添加view");
        final ViewGroup newView = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.item_goods_guige, parent, false);

//       newView.findViewById(android.R.id.iv_add_guige);
         imageView =  newView.findViewById(R.id.iv_add_guige);
         newView.findViewById(R.id.iv_delete).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                for (int i = 0; i < parent.getChildCount(); i++) {
                    View childAt = parent.getChildAt(i);
                    ImageView iv_delete = (ImageView) childAt.findViewById(R.id.iv_delete);
                    if (iv_delete== v) {
                        showMsg(i+"");
                         if(guigeImage.size()>=i&&guigeImage.size()!=0){
                             guigeImage.remove(i);
                             specifications.remove(i);
                         }
                        parent.removeView(newView);
                        break;
                    }
                }

//                parent.removeView(newView);

            }
        });
        imageView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                flag = 4;
                for (int i = 0; i < parent.getChildCount(); i++) {
                    View childAt = parent.getChildAt(i);
                    ImageView iv_add_guige = (ImageView) childAt.findViewById(R.id.iv_add_guige);
                    if (iv_add_guige== v) {
                        SpecificationIndex = i;  //索引
                        Intent intent = new Intent(
                                Intent.ACTION_PICK,
                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
                        break;
                    }
                }


            }
        });
        int count = parent.getChildCount();

        parent.addView(newView, count);

    }

    @SuppressWarnings({ "unchecked" })
    public <T> T $(int RESID)
    {
        return (T) findViewById(RESID);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {

            if (requestCode == PHOTO_REQUEST_GALLERY) {
                // 从相册返回的数据
                if (data != null) {
                    // 得到图片的全路径
                    Uri uri = data.getData();
                    String[] proj = {MediaStore.Images.Media.DATA};
                    //好像是android多媒体数据库的封装接口，具体的看Android文档
                    Cursor cursor = managedQuery(uri, proj, null, null, null);
                    //按我个人理解 这个是获得用户选择的图片的索引值
                    int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    //将光标移至开头 ，这个很重要，不小心很容易引起越界
                    cursor.moveToFirst();
                    //最后根据索引值获取图片路径
                    String path = cursor.getString(column_index);
                    uploadImage(path);
                }

            }
//            else if (requestCode == PHOTO_REQUEST_CAREMA) {
//                if (resultCode != RESULT_CANCELED) {
//                    // 从相机返回的数据
//                    if (hasSdcard()) {
//                        if (tempFile != null) {
//                            uploadImage(tempFile.getPath());
//                        } else {
//                            Toast.makeText(this, "相机异常请稍后再试！", Toast.LENGTH_SHORT).show();
//                        }
//
//                        Log.i("images", "拿到照片path=" + tempFile.getPath());
//                    } else {
//                        Toast.makeText(this, "未找到存储卡，无法存储照片！", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }
//            switch (requestCode) {
//                // 选择照片
//                case REQUEST_CAMERA_CODE:
////                    ArrayList<String> list = data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT);
////                    Log.d(TAG, "list: " + "list = [" + list.size());
////                    loadAdpater(list);
////
//////                    loadAdpater(data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT));
//
//
//                    // 从相册返回的数据
//                    if (data != null) {
//                        // 得到图片的全路径
//                        Uri uri = data.getData();
//                        String[] proj = {MediaStore.Images.Media.DATA};
//                        //好像是android多媒体数据库的封装接口，具体的看Android文档
//                        Cursor cursor = managedQuery(uri, proj, null, null, null);
//                        //按我个人理解 这个是获得用户选择的图片的索引值
//                        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//                        //将光标移至开头 ，这个很重要，不小心很容易引起越界
//                        cursor.moveToFirst();
//                        //最后根据索引值获取图片路径
//                        String path = cursor.getString(column_index);
//
//
//                        uploadImage(path);
//                    }
//                    break;
//                // 预览
//                case REQUEST_PREVIEW_CODE:
//                    ArrayList<String> ListExtra = data.getStringArrayListExtra(PhotoPreviewActivity.EXTRA_RESULT);
//                    Log.d(TAG, "ListExtra: " + "ListExtra = [" + ListExtra.size());
//                    loadAdpater(ListExtra);
//                    break;
//            }
        }
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0xAAAAAAAA) {
                photoPath((ImageData) msg.obj);
            }

        }
    };
//    private void uploadImg() {
//        showLoading();
//        RXStart(new IOCallBack<String>() {
//            @Override
//            public void call(Subscriber<? super String> subscriber) {
//                String newPath= ImageUtils.filePath;
//                ImageUtils.makeFolder(newPath);
//                FileInputStream fis = null;
//                try {
//                    List<File> files = Luban.with(mContext).load(imgSaveName).get();
//                    String imgStr = BitmapUtils.bitmapToString2(files.get(0));
//
//                    subscriber.onNext(imgStr);
//                    subscriber.onCompleted();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    subscriber.onError(e);
//                }
//                /*String newPath= ImageUtils.filePath;
//                String name=ImageUtils.fileName;
//                String smallBitmapPath = ImageUtils.getSmallBitmap(imgSaveName, newPath, name);
//                FileInputStream fis = null;
//                try {
//                    fis = new FileInputStream(smallBitmapPath);
//                    Bitmap bitmap  = BitmapFactory.decodeStream(fis);
//                    String imgStr = BitmapUtils.bitmapToString(bitmap);
//                    subscriber.onNext(imgStr);
//                    subscriber.onCompleted();
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                    subscriber.onError(e);
//                }*/
//            }
//            @Override
//            public void onMyNext(String baseImg) {
//                UploadImgItem item=new UploadImgItem();
//                item.setFile(baseImg);
//                String rnd = getRnd();
//                Map<String,String>map=new HashMap<String,String>();
//                map.put("rnd",rnd);
//                map.put("sign",GetSign.getSign(map));
//                ApiRequest.uploadImg(map,item, new MyCallBack<BaseObj>(mContext) {
//                    @Override
//                    public void onSuccess(BaseObj obj) {
//                        if(selectImgIndex ==-1){
//                            if(isEmpty(addImgAdapter.getList())){
//                                List<String> list=new ArrayList<String>();
//                                list.add(obj.getImg());
//                                addImgAdapter.setList(list);
//                            }else{
//                                addImgAdapter.getList().add(obj.getImg());
//                            }
//                        }else{
//                            addImgAdapter.getList().set(selectImgIndex,obj.getImg());
//                        }
//                        addImgAdapter.notifyDataSetChanged();
//                    }
//                });
//            }
//            @Override
//            public void onMyError(Throwable e) {
//                super.onMyError(e);
//                dismissLoading();
//                showToastS("图片处理失败");
//            }
//        });
//    }
    /**
     * 上传图片
     *
     * @param path
     */
    private void uploadImage(final String path) {
        new Thread() {
            @Override
            public void run() {
                if (new File(path).exists()) {
                    Log.d("images", "源文件存在" + path);
                } else {
                    Log.d("images", "源文件不存在" + path);
                }

                File dir = new File(IMAGE_DIR);
                if (!dir.exists()) {
                    dir.mkdir();
                }
                final File file = new File(dir + "/temp_photo" + System.currentTimeMillis() + ".jpg");
                NativeUtil.compressBitmap(path, file.getAbsolutePath(), 50);
                if (file.exists()) {
                    Log.d("images", "压缩后的文件存在" + file.getAbsolutePath());
                    String imgStr = BitmapUtils.bitmapToString2(file);
                    UploadImgItem item=new UploadImgItem();
                    item.setFile(imgStr);
                    String rnd = getRnd();
                    Map<String,String>map=new HashMap<String,String>();
                    map.put("rnd",rnd);
                    map.put("sign",GetSign.getSign(map));
                    ApiRequest.uploadImg(map,item, new MyCallBack<BaseObj>(mContext) {
                        @Override
                        public void onSuccess(BaseObj obj) {
                            if(flag == 1){ //点击封面图标添加图片
                                goods_image = obj.getImg();
                            }else if(flag == 2){

                            }else if(flag == 3){

                            }
                            imageData.setImagePath(file.getAbsolutePath());
                            imageData.setImgName(obj.getImg());
                            Message message = new Message();
                            message.what = 0xAAAAAAAA;
                            message.obj = imageData;
                            handler.sendMessage(message);
                        }
                    });

                } else {
                    Log.d("images", "压缩后的不存在" + file.getAbsolutePath());
                }


            }
        }.start();
    }

    public void photoPath(ImageData imageData) {
        if(flag == 1){
            final File file = new File(imageData.getImagePath());
            Glide.with(mContext)
                    .load(file)
                    .priority(Priority.HIGH)
                    .into(tv_home_pic);

        }else if(flag == 2){
            Map<String,ImageData> map=new HashMap<>();
            map.put("ImageData",imageData);
            homeImagedatas.add(map);
            gridViewHomeAddImgesAdpter.notifyDataSetChanged();
        }else if(flag == 3){
            Map<String,ImageData> map=new HashMap<>();
            map.put("ImageData",imageData);
            mainImagedatas.add(map);
            gridViewMainAddImgesAdpter.notifyDataSetChanged();
        }else{
            guigeImage.add(SpecificationIndex,imageData.getImgName());
            Glide.with(mContext).load(imageData.getImagePath()).error(R.color.c_press).into(imageView);
        }

    }


    public class GridViewAddImgesAdpter extends BaseAdapter {
        private List<Map<String, ImageData>> datas;
        private Context context;
        private LayoutInflater inflater;
        private  int flag ;
        /**
         * 可以动态设置最多上传几张，之后就不显示+号了，用户也无法上传了
         * 默认9张
         */
        private int maxImages = 9;

        public GridViewAddImgesAdpter(List<Map<String, ImageData>> datas, Context context) {
            this.datas = datas;
            this.context = context;
            inflater = LayoutInflater.from(context);
        }
        /**
         * 获取最大上传张数
         *
         * @return
         */
        public int getMaxImages() {
            return maxImages;
        }

        /**
         * 设置最大上传张数
         *
         * @param maxImages
         */
        public void setMaxImages(int maxImages) {
            this.maxImages = maxImages;
        }

        /**
         * 让GridView中的数据数目加1最后一个显示+号
         *
         * @return 返回GridView中的数量
         */
        @Override
        public int getCount() {
            int count = datas == null ? 1 : datas.size() + 1;
            if (count >= maxImages) {
                return datas.size();
            } else {
                return count;
            }
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        public void notifyDataSetChanged(List<Map<String, ImageData>> datas) {
            this.datas = datas;
            this.notifyDataSetChanged();

        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            ViewMainHolder viewHolder = null;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_published_grida, parent, false);
                viewHolder = new ViewMainHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewMainHolder) convertView.getTag();
            }
            if (datas != null && position < datas.size()) {
                final File file = new File(datas.get(position).get("ImageData").getImagePath());
                Glide.with(context)
                        .load(file)
                        .priority(Priority.HIGH)
                        .into(viewHolder.ivimage);
                viewHolder.btdel.setVisibility(View.VISIBLE);
                viewHolder.btdel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (file.exists()) {
                            file.delete();
                        }
                        datas.remove(position);
                        notifyDataSetChanged();
                    }
                });
            } else {

                Glide.with(context)
                        .load(R.mipmap.add_guige_image)
                        .priority(Priority.HIGH)
                        .centerCrop()
                        .into(viewHolder.ivimage);
                viewHolder.ivimage.setScaleType(ImageView.ScaleType.FIT_XY);
                viewHolder.btdel.setVisibility(View.GONE);
            }

            return convertView;

        }

        public class ViewMainHolder {
            public final ImageView ivimage;
            public final Button btdel;
            public final View root;

            public ViewMainHolder(View root) {
                ivimage = (ImageView) root.findViewById(R.id.iv_image);
                btdel = (Button) root.findViewById(R.id.bt_del);
                this.root = root;
            }
        }
    }







//    private void loadAdpater(ArrayList<String> paths){
//        if(imagePaths == null){
//            imagePaths = new ArrayList<>();
//        }
//        imagePaths.clear();
//        imagePaths.addAll(paths);
//        if(gridAdapter == null){
//            gridAdapter = new GridAdapter(imagePaths);
//            gridView.setAdapter(gridAdapter);
//        }else {
//            gridAdapter.notifyDataSetChanged();
//        }
//    }
//    private class GridAdapter extends BaseAdapter {
//        private ArrayList<String> listUrls;
//
//        public GridAdapter(ArrayList<String> listUrls) {
//            this.listUrls = listUrls;
//        }
//
//        @Override
//        public int getCount() {
//            return listUrls.size();
//        }
//
//        @Override
//        public String getItem(int position) {
//            return listUrls.get(position);
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return position;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            ImageView imageView;
//            Button bt_del = null;
////            ViewHolder viewHolder = null;
////            if(convertView == null){
////                convertView = getLayoutInflater().inflate(R.layout.item_image, null);
////                imageView = (ImageView) convertView.findViewById(R.id.imageView);
////                bt_del = (Button) convertView.findViewById(R.id.bt_del);
////                convertView.setTag(imageView);
////                // 重置ImageView宽高
////                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(columnWidth, columnWidth);
////                imageView.setLayoutParams(params);
////            }else {
////                imageView = (ImageView) convertView.getTag();
////            }
////
//            ViewHolder1 viewHolder1 = null;
//            if (convertView == null) {
//                convertView = getLayoutInflater().inflate(R.layout.item_image, null);
//                viewHolder1 = new ViewHolder1(convertView);
//                convertView.setTag(viewHolder1);
//               // 重置ImageView宽高
//                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(columnWidth, columnWidth);
//                viewHolder1.imageView.setLayoutParams(params);
//            } else {
//                viewHolder1 = (ViewHolder1) convertView.getTag();
//            }
//            if (listUrls != null && position < listUrls.size()) {
////                final File file = new File(listUrls.get(position).get("path").toString());
//                Glide.with(AddGoodsActivity.this)
//                        .load(new File(getItem(position)))
//                        .priority(Priority.HIGH)
//                        .into(viewHolder1.imageView);
//                viewHolder1.bt_del.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
////                        if (file.exists()) {
////                            file.delete();
////                        }
//                        listUrls.remove(position);
//                        notifyDataSetChanged();
//                    }
//                });
//            } else {
//                Glide.with(AddGoodsActivity.this)
//                        .load(R.mipmap.add_guige_image)
//                        .priority(Priority.HIGH)
//                        .centerCrop()
//                        .into(viewHolder1.imageView);
//                viewHolder1.imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//                bt_del.setVisibility(View.GONE);
//            }
//            return convertView;
//        }
//    }
}
