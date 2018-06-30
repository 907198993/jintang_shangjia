package com.qifan.shangjia.activity;


import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.Transformation;
import com.foamtrace.photopicker.ImageCaptureManager;
import com.foamtrace.photopicker.PhotoPickerActivity;
import com.foamtrace.photopicker.PhotoPreviewActivity;
import com.foamtrace.photopicker.SelectModel;
import com.foamtrace.photopicker.intent.PhotoPickerIntent;
import com.foamtrace.photopicker.intent.PhotoPreviewIntent;
import com.github.customview.MyTextView;
import com.qifan.shangjia.R;
import com.qifan.shangjia.base.BaseActivity;

import org.json.JSONArray;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by Administrator on 2018/6/28 0028.
 */

public class AddGoodsActivity extends BaseActivity {
    private List<View> viewList;
    private List<ViewHolder> viewHolderList;
    private int mark = 0;
    private String classNames = "";
    private String proIds = "";

    @BindView(R.id.parent)
    LinearLayout parent;

    @BindView(R.id.btn_add_main_picture)
    MyTextView btn_add_main_picture;


    private int columnWidth;

    private static final int REQUEST_CAMERA_CODE = 10;
    private static final int REQUEST_PREVIEW_CODE = 20;
    private ArrayList<String> imagePaths = new ArrayList<>();
    private ImageCaptureManager captureManager; // 相机拍照处理类

    private GridView gridView;
    private GridAdapter gridAdapter;
    private Button mButton;
    private String depp;
    private EditText textView;
    private String TAG =AddGoodsActivity.class.getSimpleName();
    private LayoutInflater inflater;
    @Override
    protected int getContentView() {
        return R.layout.add_goods;
    }

    @Override
    protected void initView() {
        viewList = new ArrayList<>();
        viewHolderList = new ArrayList<>();
        View addView = LayoutInflater.from(AddGoodsActivity.this).inflate(R.layout.item_goods_guige, null);
        addView.setId(mark);
        parent.addView(addView, mark);
        getViewInstance(addView);

//        图片选择
        gridView = (GridView) findViewById(R.id.gridView);
        int cols = getResources().getDisplayMetrics().widthPixels / getResources().getDisplayMetrics().densityDpi;
        cols = cols < 3 ? 3 : cols;

        gridView.setNumColumns(cols);
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        int columnSpace = getResources().getDimensionPixelOffset(R.dimen.space_size);
        columnWidth = (screenWidth - columnSpace * (cols-1)) / cols;
        // preview
        btn_add_main_picture.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                PhotoPickerIntent intent1 = new PhotoPickerIntent(AddGoodsActivity.this);
                intent1.setSelectModel(SelectModel.MULTI);
                intent1.setShowCarema(false); // 是否显示拍照
                intent1.setMaxTotal(5); // 最多选择照片数量，默认为9
                intent1.setSelectedPaths(imagePaths); // 已选中的照片地址， 用于回显选中状态
                startActivityForResult(intent1, REQUEST_CAMERA_CODE);
            }
//            }
        });
//        gridAdapter = new GridAdapter(imagePaths);
//        gridView.setAdapter(gridAdapter);
//        mButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                depp =textView.getText().toString().trim()!=null?textView.getText().toString().trim():"woowoeo";
//                new Thread(){
//                    @Override
//                    public void run() {
//                        super.run();
//                        FileUploadManager.uploadMany(imagePaths, depp);
////                        FileUploadManager.upload(imagePaths,depp);
//                    }
//                }.start();
//            }
//        });
    }

    private void getViewInstance(View addView) {
        ViewHolder vh = new ViewHolder();
        vh.id = addView.getId();
        vh.iv_add_guige = (ImageView) addView.findViewById(R.id.iv_add_guige);
        viewHolderList.add(vh);
        viewList.add(addView);
    }

    //规格
    public class ViewHolder {
        private int id = -1;
        private ImageView iv_add_guige;

    }
    //上传图片
    public class ViewHolder1 {
        public final ImageView imageView;
        public final Button bt_del;
        public final View root;

        public ViewHolder1(View root) {
            imageView = (ImageView) root.findViewById(R.id.imageView);
            bt_del = (Button) root.findViewById(R.id.bt_del);
            this.root = root;
        }
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.btn_add_guige})
    protected void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_guige:
            mark++;
            View addView = LayoutInflater.from(AddGoodsActivity.this).inflate(R.layout.item_goods_guige, null);
            addView.setId(mark);
            parent.addView(addView);
            getViewInstance(addView);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            switch (requestCode) {
                // 选择照片
                case REQUEST_CAMERA_CODE:
                    ArrayList<String> list = data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT);
                    Log.d(TAG, "list: " + "list = [" + list.size());
                    loadAdpater(list);

//                    loadAdpater(data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT));
                    break;
                // 预览
                case REQUEST_PREVIEW_CODE:
                    ArrayList<String> ListExtra = data.getStringArrayListExtra(PhotoPreviewActivity.EXTRA_RESULT);
                    Log.d(TAG, "ListExtra: " + "ListExtra = [" + ListExtra.size());
                    loadAdpater(ListExtra);
                    break;
            }
        }
    }

    private void loadAdpater(ArrayList<String> paths){
        if(imagePaths == null){
            imagePaths = new ArrayList<>();
        }
        imagePaths.clear();
        imagePaths.addAll(paths);
        if(gridAdapter == null){
            gridAdapter = new GridAdapter(imagePaths);
            gridView.setAdapter(gridAdapter);
        }else {
            gridAdapter.notifyDataSetChanged();
        }
    }
    private class GridAdapter extends BaseAdapter {
        private ArrayList<String> listUrls;

        public GridAdapter(ArrayList<String> listUrls) {
            this.listUrls = listUrls;
        }

        @Override
        public int getCount() {
            return listUrls.size();
        }

        @Override
        public String getItem(int position) {
            return listUrls.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            Button bt_del = null;
//            ViewHolder viewHolder = null;
//            if(convertView == null){
//                convertView = getLayoutInflater().inflate(R.layout.item_image, null);
//                imageView = (ImageView) convertView.findViewById(R.id.imageView);
//                bt_del = (Button) convertView.findViewById(R.id.bt_del);
//                convertView.setTag(imageView);
//                // 重置ImageView宽高
//                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(columnWidth, columnWidth);
//                imageView.setLayoutParams(params);
//            }else {
//                imageView = (ImageView) convertView.getTag();
//            }
//
            ViewHolder1 viewHolder1 = null;
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.item_image, null);
                viewHolder1 = new ViewHolder1(convertView);
                convertView.setTag(viewHolder1);
            } else {
                viewHolder1 = (ViewHolder1) convertView.getTag();
            }
            if (listUrls != null && position < listUrls.size()) {
//                final File file = new File(listUrls.get(position).get("path").toString());
                Glide.with(AddGoodsActivity.this)
                        .load(new File(getItem(position)))
                        .priority(Priority.HIGH)
                        .into(viewHolder1.imageView);
                viewHolder1.bt_del.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        if (file.exists()) {
//                            file.delete();
//                        }
                        listUrls.remove(position);
                        notifyDataSetChanged();
                    }
                });
            } else {
                Glide.with(AddGoodsActivity.this)
                        .load(R.mipmap.add_guige_image)
                        .priority(Priority.HIGH)
                        .centerCrop()
                        .into(viewHolder1.imageView);
                viewHolder1.imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                bt_del.setVisibility(View.GONE);
            }

//            Glide.with(AddGoodsActivity.this)
//                    .load(new File(getItem(position)))
//                    .placeholder(R.mipmap.default_error)
//                    .error(R.mipmap.default_error)
//                    .centerCrop()
//                    .crossFade()
//                    .into(imageView);
            return convertView;
        }
    }
}
