package com.example.open_show;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyboardShortcutInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;

import com.bigkoo.pickerview.TimePickerView;
import com.bigkoo.pickerview.listener.CustomListener;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.suke.widget.SwitchButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import adapter.GridImageAdapter;
import adapter.MingAdapter;
import adapter.MingAdapter1;
import adapter.MingAdapter2;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import presenter.MingPresenter;
import zhang.bw.com.common.bean.BingBean;
import zhang.bw.com.common.bean.ShowBean;
import zhang.bw.com.common.core.DataCall;
import zhang.bw.com.common.core.FindDiseaseCategory;
import zhang.bw.com.common.core.WDActivity;
import zhang.bw.com.common.core.exception.ApiException;
import zhang.bw.com.common.util.Constant;

/**
 * @Author：郭强
 * @E-mail： 69666501@163.com
 * @Date：2019/7/23
 * @Description：XXXX
 */
@Route(path = Constant.ACTIVITY_URL_FABIAOPINGLUN)
public class FaBiaoPingLun extends WDActivity {

   // private final static String TAG = FaBiaoPingLun.class.getSimpleName();
    private List<LocalMedia> selectList = new ArrayList<>();

    private GridImageAdapter adapter;
    private int maxSelectNum = 9;
    private int themeId;
    RelativeLayout relativeLayout;
    SwitchButton switchButton;
    ImageView image_ks,image_bz,image_kstime,image_endtime,image_tjtp;
    RecyclerView recyc_view5 , recyclerView;
    MingAdapter1 mingAdapter1;
    TextView text_hong,  text_keshi_xia,text_bingzheng_xia;
    EditText text_keshi_f,text_bingzheng_f,text_kaishitime_f,text_endtime_f;
     MingAdapter2 mingAdapter2;
     FindDiseaseCategory findDiseaseCategory;
    GridLayoutManager gridLayoutManager;
    TimePickerView pvTime;
    TimePickerView pvTime1;

     int ss=-1;
//    TimePickerBuilder pvCustomTime;
    @Override
    protected int getLayoutId() {
        return R.layout.layout_fabiaopinglun;
    }

    @Override
    protected void initView() {
        initfindviewByid();
        initonclicke();

        //时间选择器
        pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                text_kaishitime_f.setText(getTime(date));
            }
        }).setType(new boolean[]{true,true,true,false,false,false})
                .build();

        pvTime1 = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                text_endtime_f.setText(getTime(date));
            }
        }).setType(new boolean[]{true,true,true,false,false,false})
                .build();

        //text_keshi_f变化监听
        text_keshi_f.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String textf = text_keshi_f.getText().toString();
                if(textf.length()==0){
                    text_hong.setTextColor(Color.parseColor("#ff0000"));
                    text_bingzheng_f.setText("");
                    ss=-1;
                }
            }
        });



        FullyGridLayoutManager manager = new FullyGridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        adapter = new GridImageAdapter(this, onAddPicClickListener);
        adapter.setList(selectList);
        adapter.setSelectMax(maxSelectNum);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                if (selectList.size() > 0) {
                    LocalMedia media = selectList.get(position);
                    String pictureType = media.getPictureType();
                    int mediaType = PictureMimeType.pictureToVideo(pictureType);
                    switch (mediaType) {
                        case 1:
                            // 预览图片 可自定长按保存路径
                            // PictureSelector.create(Feles_Ada_Activity.this).themeStyle(themeId).externalPicturePreview(position, "/custom_file", selectList);
                            PictureSelector.create(FaBiaoPingLun.this).themeStyle(themeId).openExternalPreview(position, selectList);
                            break;
                    }
                }
            }
        });




    }

    private void initfindviewByid() {
        switchButton = findViewById(R.id.switchbutton);
        relativeLayout =findViewById(R.id.relayout_1);
        image_bz =findViewById(R.id.image_bz);
        image_ks =findViewById(R.id.image_ks);
        text_hong =findViewById(R.id.text_hong);
        text_keshi_f =findViewById(R.id.text_keshi_f);
        text_bingzheng_f =findViewById(R.id.text_bingzheng_f);
        text_keshi_xia =findViewById(R.id.text_keshi_xia);
        text_bingzheng_xia =findViewById(R.id.text_bingzheng_xia);
        image_kstime =findViewById(R.id.image_kstime);
        text_endtime_f =findViewById(R.id.text_endtime_f);
        text_kaishitime_f =findViewById(R.id.text_kaishitime_f);
        image_endtime =findViewById(R.id.image_endtime);
        recyclerView = findViewById(R.id.fa_RecyclerViewdddd);

    }

    private void initonclicke() {
        //开关按钮事件
        switchButton.setChecked(false);
        switchButton.isChecked();
        switchButton.toggle();
        switchButton.toggle(true);
        switchButton.setShadowEffect(true);
        switchButton.setEnabled(true);
        switchButton.setEnableEffect(false);
        switchButton.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
//                boolean b = switchButton.isChecked() == false;
                if (isChecked) {
                    relativeLayout.setVisibility(View.VISIBLE);
                } else {
                    relativeLayout.setVisibility(View.GONE);
                }
            }
        });
        //第一个opupWindow
        // 用于PopupWindow的View
        View contentView = LayoutInflater.from(FaBiaoPingLun.this).inflate(R.layout.layout_popup_1, null, false);
        // 创建PopupWindow对象，其中：
        // 第一个参数是用于PopupWindow中的View，第二个参数是PopupWindow的宽度，
        // 第三个参数是PopupWindow的高度，第四个参数指定PopupWindow能否获得焦点
        PopupWindow window = new PopupWindow(contentView, RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT, true);
        // 设置PopupWindow的背景
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // 设置PopupWindow是否能响应外部点击事件
        window.setOutsideTouchable(true);
        // 设置PopupWindow是否能响应点击事件
        window.setTouchable(true);



        image_ks.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {
                // 显示PopupWindow，其中：
                // 第一个参数是PopupWindow的锚点，第二和第三个参数分别是PopupWindow相对锚点的x、y偏移
                window.showAsDropDown(text_keshi_xia);

                recyc_view5 =contentView.findViewById(R.id.recyc_view5);
                MingPresenter mingPresenter = new MingPresenter(new requestss());
                mingPresenter.reqeust();
                gridLayoutManager = new GridLayoutManager(FaBiaoPingLun.this,4);
                recyc_view5.setLayoutManager(gridLayoutManager);
                mingAdapter1 = new MingAdapter1(FaBiaoPingLun.this);
                recyc_view5.setAdapter(mingAdapter1);
                mingAdapter1.setMyCallBack(new MingAdapter1.MyCallBack() {




                    @Override
                    public void oncelicks(int id, String name) {
                        ss=id;
                        text_keshi_f.setText(name);
                        text_hong.setTextColor(Color.parseColor("#3078ea"));
                        window .dismiss();

                        findDiseaseCategory = new FindDiseaseCategory(new Backh());
                        findDiseaseCategory.reqeust(id+"");

                        window.showAsDropDown(text_bingzheng_xia);
                        recyc_view5.setLayoutManager(gridLayoutManager);
                        mingAdapter2 = new MingAdapter2(FaBiaoPingLun.this);
                        recyc_view5.setAdapter(mingAdapter2);

                        mingAdapter2.setMyCallBack(new MingAdapter2.MyCallBack() {
                            @Override
                            public void oncelicks(String name) {
                                text_bingzheng_f.setText(name);
                                window.dismiss();
                            }
                        });

                    }
                });

            }
        });
        image_bz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if(ss!=-1){
                        findDiseaseCategory = new FindDiseaseCategory(new Backh());
                        findDiseaseCategory.reqeust(ss+"");
                        window.showAsDropDown(text_bingzheng_xia);
                        recyc_view5.setLayoutManager(gridLayoutManager);
                        mingAdapter2 = new MingAdapter2(FaBiaoPingLun.this);
                        recyc_view5.setAdapter(mingAdapter2);
                        ss=-1;
                        mingAdapter2.setMyCallBack(new MingAdapter2.MyCallBack() {
                            @Override
                            public void oncelicks(String name) {
                                text_bingzheng_f.setText(name);
                                window.dismiss();
                            }
                        });
                    }else{
                        Toast.makeText(FaBiaoPingLun.this, "请选就诊科室", Toast.LENGTH_SHORT).show();
                    }

            }
        });
        image_kstime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pvTime.setDate(Calendar.getInstance());
                pvTime.show();
            }
        });

        image_endtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pvTime1.setDate(Calendar.getInstance());
                pvTime1.show();
            }
        });

    }



    @Override
    protected void destoryData() {
        switchButton.setChecked(false);
        switchButton.isChecked();
        switchButton.toggle();
        switchButton.toggle(true);
        switchButton.setShadowEffect(true);
        switchButton.setEnabled(true);
        switchButton.setEnableEffect(false);
        switchButton.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
//                boolean b = switchButton.isChecked() == false;
                if (isChecked) {
                    relativeLayout.setVisibility(View.VISIBLE);
                } else {
                    relativeLayout.setVisibility(View.GONE);
                }
            }
        });

    }

    private class requestss implements DataCall<List<ShowBean>>{
        @Override
        public void success(List<ShowBean> data, Object... args) {
            mingAdapter1.setsssss(data);
            mingAdapter1.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }

    private class Backh implements DataCall<List<BingBean>>{
        @Override
        public void success(List<BingBean> data, Object... args) {
            mingAdapter2.addAll(data);
            mingAdapter2.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }

    //时间格式转换
    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        //  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }


    private void commonAction(int type) {
        PictureSelector.create(this)
                .openGallery(type)// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .theme(R.style.picture_Sina_style)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
                //themeId = R.style.picture_default_style;   默认样式
                //themeId = R.style.picture_white_style;
                //themeId = R.style.picture_QQ_style;
                //themeId = R.style.picture_Sina_style;
                .maxSelectNum(9)// 最大图片选择数量
                .minSelectNum(1)// 最小选择数量
                .imageSpanCount(3)// 每行显示个数
                .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选
                .previewImage(true)// 是否可预览图片
                .previewVideo(true)// 是否可预览视频
                .enablePreviewAudio(true) // 是否可播放音频
                .isCamera(true)// 是否显示拍照按钮
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                //.imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                //.setOutputCameraPath("/CustomPath")// 自定义拍照保存路径
                .enableCrop(false)// 是否裁剪
                .compress(true)// 是否压缩
                .synOrAsy(true)//同步true或异步false 压缩 默认同步
                //.compressSavePath(getPath())//压缩图片保存地址
                //.sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                .withAspectRatio(0, 0)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                .hideBottomControls(true)// 是否显示uCrop工具栏，默认不显示
                .isGif(true)// 是否显示gif图片
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                .circleDimmedLayer(false)// 是否圆形裁剪
                .showCropFrame(true)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                .showCropGrid(true)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                .openClickSound(false)// 是否开启点击声音
                .selectionMedia(selectList)// 是否传入已选图片
                //.isDragFrame(false)// 是否可拖动裁剪框(固定)
                //.videoMaxSecond(15)
                //.videoMinSecond(10)
                //.previewEggs(false)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                //.cropCompressQuality(90)// 裁剪压缩质量 默认100
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                //.cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效
                //.rotateEnabled(true) // 裁剪是否可旋转图片
                //.scaleEnabled(true)// 裁剪是否可放大缩小图片
                //.videoQuality()// 视频录制质量 0 or 1
                //.videoSecond()//显示多少秒以内的视频or音频也可适用
                //.recordVideoSecond()//录制视频秒数 默认60s
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PictureConfig.CHOOSE_REQUEST:
                // 图片选择结果回调
                selectList = PictureSelector.obtainMultipleResult(data);
                // 例如 LocalMedia 里面返回三种path
                // 1.media.getPath(); 为原图path
                // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                // 如果裁剪并压缩了，已取压缩路径为准，因为是先裁剪后压缩的
                for (LocalMedia media : selectList) {
                    if (media.isCompressed()){
                        //pathList.add(media.getCompressPath());
                    }
                }
                adapter.setList(selectList);
                adapter.notifyDataSetChanged();
                break;
        }

    }

    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick() {
//            if (true) {
            // 进入相册 以下是例子：不需要的api可以不写
            commonAction(PictureMimeType.ofImage());
//            }
//            else {
            // 单独拍照
//                PictureSelector.create(MainActivity.this)
//                        .openCamera(chooseMode)// 单独拍照，也可录像或也可音频 看你传入的类型是图片or视频
//                        .theme(themeId)// 主题样式设置 具体参考 values/styles
//                        .maxSelectNum(maxSelectNum)// 最大图片选择数量
//                        .minSelectNum(1)// 最小选择数量
//                        .selectionMode(cb_choose_mode.isChecked() ?
//                                PictureConfig.MULTIPLE : PictureConfig.SINGLE)// 多选 or 单选
//                        .previewImage(cb_preview_img.isChecked())// 是否可预览图片
//                        .previewVideo(cb_preview_video.isChecked())// 是否可预览视频
//                        .enablePreviewAudio(cb_preview_audio.isChecked()) // 是否可播放音频
//                        .isCamera(cb_isCamera.isChecked())// 是否显示拍照按钮
//                        .enableCrop(cb_crop.isChecked())// 是否裁剪
//                        .compress(cb_compress.isChecked())// 是否压缩
//                        .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
//                        .withAspectRatio(aspect_ratio_x, aspect_ratio_y)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
//                        .hideBottomControls(cb_hide.isChecked() ? false : true)// 是否显示uCrop工具栏，默认不显示
//                        .isGif(cb_isGif.isChecked())// 是否显示gif图片
//                        .freeStyleCropEnabled(cb_styleCrop.isChecked())// 裁剪框是否可拖拽
//                        .circleDimmedLayer(cb_crop_circular.isChecked())// 是否圆形裁剪
//                        .showCropFrame(cb_showCropFrame.isChecked())// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
//                        .showCropGrid(cb_showCropGrid.isChecked())// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
//                        .openClickSound(cb_voice.isChecked())// 是否开启点击声音
//                        .selectionMedia(selectList)// 是否传入已选图片
//                        .previewEggs(false)//预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
//                        //.previewEggs(false)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
//                        //.cropCompressQuality(90)// 裁剪压缩质量 默认为100
//                        .minimumCompressSize(100)// 小于100kb的图片不压缩
//                        //.cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效
//                        //.rotateEnabled() // 裁剪是否可旋转图片
//                        //.scaleEnabled()// 裁剪是否可放大缩小图片
//                        //.videoQuality()// 视频录制质量 0 or 1
//                        //.videoSecond()////显示多少秒以内的视频or音频也可适用
//                        .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
//            }
//        }
        }
    };


}
