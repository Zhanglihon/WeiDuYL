package zhang.bw.com.open_my.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;
import java.io.IOException;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import butterknife.BindView;
import butterknife.ButterKnife;
import zhang.bw.com.common.DaoMaster;
import zhang.bw.com.common.LoginBeanDao;
import zhang.bw.com.common.bean.CXBean;
import zhang.bw.com.common.bean.LoginBean;
import zhang.bw.com.common.core.DataCall;
import zhang.bw.com.common.core.WDActivity;
import zhang.bw.com.common.core.exception.ApiException;
import zhang.bw.com.common.util.Constant;
import zhang.bw.com.common.util.RealPathFromUriUtils;
import zhang.bw.com.open_my.BuildConfig;
import zhang.bw.com.open_my.R;
import zhang.bw.com.open_my.R2;
import zhang.bw.com.open_my.presenter.MyPresenter;
import zhang.bw.com.open_my.presenter.WdxxPresenter;

public class WdxxActivity extends WDActivity {
    @BindView(R2.id.wdxx_image_tx)
    ImageView wdxxImageTx;
    @BindView(R2.id.wdxx_name)
    TextView wdxxName;
    @BindView(R2.id.wdxx_sg)
    TextView wdxxSg;
    @BindView(R2.id.wdxx_tz)
    TextView wdxxTz;
    @BindView(R2.id.wdxx_age)
    TextView wdxxAge;
    @BindView(R2.id.wdxx_nickname)
    ImageView wdxxNickname;
    @BindView(R2.id.wdxx_sex)
    ImageView wdxxSex;
    @BindView(R2.id.wdxx_image_sex)
    ImageView wdxxImageSex;
    @BindView(R2.id.wdxx_email)
    TextView wdxxEmail;
    @BindView(R2.id.wdxx_image_back)
    ImageView wdxxImageBack;
    @BindView(R2.id.wdxx_tizheng)
    ImageView wdxxTizheng;
    @BindView(R2.id.wdxx_smrz)
    ImageView wdxxSmrz;
    @BindView(R2.id.wdxx_yhk)
    ImageView wdxxYhk;
    private TextView my_pop_xc;
    private TextView my_pop_xj;
    private PopupWindow popupWindow;
    private MyPresenter myPresenter;
    private LoginBean loginBean;
    private WdxxPresenter wdxxPresenter;
    private TextView my_cancle;
    private RelativeLayout my_bj;
    private String path = Environment.getExternalStorageDirectory() + "/hh.jpg";
    @Override
    protected int getLayoutId() {
        return R.layout.activity_wdxx;
    }

    @Override
    protected void initView() {
        //绑定银行卡
        wdxxYhk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(Constant.ACTIVITY_URL_YINGHK).navigation();
            }
        });
        //实名认证
        wdxxSmrz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(Constant.ACTIVITY_URL_SHENGZ).navigation();
            }
        });
        //我的体征
        wdxxTizheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WdxxActivity.this, WdtzActivity.class);
                startActivity(intent);
            }
        });
        wdxxImageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        wdxxSex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WdxxActivity.this, WdsexActivity.class);
                startActivity(intent);
            }
        });
        wdxxNickname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WdxxActivity.this, WdncActivity.class);
                startActivity(intent);
            }
        });
        loginBean = DaoMaster.newDevSession(WdxxActivity.this, LoginBeanDao.TABLENAME).getLoginBeanDao().loadAll().get(0);
        myPresenter = new MyPresenter(new Txsc());
        //加载popupwindow的子布局
        View view = View.inflate(WdxxActivity.this, R.layout.my_popupwindow, null);
        my_pop_xc = view.findViewById(R.id.my_pop_xc);
        my_pop_xj = view.findViewById(R.id.my_pop_xj);
        my_cancle = view.findViewById(R.id.my_cancle);
        my_bj = view.findViewById(R.id.my_bj);
        my_bj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                my_bj.setVisibility(View.GONE);
            }
        });
        my_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                my_bj.setVisibility(View.GONE);
            }
        });
        //创建popupwindow
        //contentView, 布局   width, 宽    height 高
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置背景
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        //设置焦点
        popupWindow.setFocusable(true);
        //设置可触摸
        popupWindow.setTouchable(true);
        //相机
        my_pop_xj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //去寻找是否已经有了相机的权限
                if (ContextCompat.checkSelfPermission(WdxxActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){

                    //Toast.makeText(MainActivity.this,"您申请了动态权限",Toast.LENGTH_SHORT).show();
                    //如果有了相机的权限有调用相机
                    startCamera();

                }else{
                    //否则去请求相机权限
                    ActivityCompat.requestPermissions(WdxxActivity.this,new String[]{Manifest.permission.CAMERA},100);

                }

            }
        });
        //相册
        my_pop_xc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 200);
            }
        });
        wdxxImageTx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //根据父窗体X Y轴的偏移改变位置
                popupWindow.showAtLocation(View.inflate(WdxxActivity.this, R.layout.activity_wdxx, null), Gravity.BOTTOM, 0, 0);
                my_bj.setVisibility(View.VISIBLE);
            }
        });
    }
    private void startCamera() {
        //创建拍照的隐式意图对象
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //把拍完的照片,输出到指定路径上
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(path)));

        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //开启页面
        startActivityForResult(intent, 100);
    }

    @Override
    protected void destoryData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
    private File tempFile = null;
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            //取出拍照的照片
            Uri uri = Uri.fromFile(new File(path));
            File file=new File(path);
            wdxxImageTx.setImageURI(uri);
            myPresenter.reqeust(loginBean.getId(),loginBean.getSessionId(),file);
        }
        if (requestCode == 200) {
            String realPathFromUri=RealPathFromUriUtils.getRealPathFromUri(WdxxActivity.this,data.getData());
            File filea=new File(realPathFromUri);
            Uri uri=data.getData();
            wdxxImageTx.setImageURI(uri);
            Glide.with(WdxxActivity.this).load(uri).apply(RequestOptions.circleCropTransform()).into(wdxxImageTx);
            myPresenter.reqeust(loginBean.getId(),loginBean.getSessionId(),filea);
        }
    }

    private static String[] PERMISSIONS_STORAGE = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE };
    public boolean verifyStoragePermissions(WdxxActivity activity) {
        // 检查是否有写权限
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            // 我们没有权限，所以提示用户
            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, 1);
            return false;
        }else{
            return true;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        wdxxPresenter = new WdxxPresenter(new wdxx());
        wdxxPresenter.reqeust(loginBean.getId(), loginBean.getSessionId());
    }

    class Txsc implements DataCall<String> {

        @Override
        public void success(String data, Object... args) {
            Toast.makeText(WdxxActivity.this, "头像上传成功", Toast.LENGTH_SHORT).show();
            Glide.with(WdxxActivity.this).load(data).apply(RequestOptions.circleCropTransform()).into(wdxxImageTx);
        }

        @Override
        public void fail(ApiException data, Object... args) {
            Toast.makeText(WdxxActivity.this, "头像上传失败", Toast.LENGTH_SHORT).show();
        }
    }

    class wdxx implements DataCall<CXBean> {

        @Override
        public void success(CXBean data, Object... args) {
            Glide.with(WdxxActivity.this).load(data.headPic).apply(RequestOptions.circleCropTransform()).into(wdxxImageTx);
            wdxxName.setText(data.nickName);
            wdxxSg.setText(data.height + "cm");
            wdxxTz.setText(data.weight + "kg");
            wdxxAge.setText(data.age + "");
            wdxxEmail.setText(data.email);
            if (data.sex == 1) {
                Glide.with(WdxxActivity.this).load(R.mipmap.common_icon_boy_n).into(wdxxImageSex);
            } else {
                Glide.with(WdxxActivity.this).load(R.mipmap.common_icon_girl_n).into(wdxxImageSex);
            }
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}
